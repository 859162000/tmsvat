package com.deloitte.tms.pl.core.filter;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.StringTokenizer;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.security.model.SecurityUser;
import com.deloitte.tms.pl.security.security.ISecurityInterceptor;
import com.deloitte.tms.pl.security.service.impl.DefaultUserService;

import edu.yale.its.tp.cas.client.ProxyTicketValidator;
import edu.yale.its.tp.cas.client.Util;

@Component(CJSCFilter.BEAN_ID)
public class CJSCFilter implements Filter {
	public static final String BEAN_ID="cjscFilter";
	
	/* 用户设置或取出SESSION名称，此值，在不同应用中统一“com.cjsc.sso.username” */
	public final static String CAS_FILTER_USER = "com.cjsc.sso.username";
	
	public  String errorPage="/vat/index.html";
	public  String firstPgae="/vat/index.html";
	// 常用接口
	private String casLogin, casValidate, casServerName;
	// 暂时未启用接口
	private String casAuthorizedProxy, casServiceUrl, casRenew;
	private boolean wrapRequest;

	private Logger logger = Logger.getLogger(CJSCFilter.class);
	
	@Resource
	DefaultUserService defaultUserService;
	
	@Resource
	ISecurityInterceptor securityInterceptor;
	
	/**
	 * 根据WEB.xml配置，初始化CAS SEERVER接口信息
	 */
	public void init(FilterConfig config) throws ServletException {
		// 重定向到登录界面的接口
		casLogin = config.getInitParameter("com.cjsc.sso.loginUrl");

		// 进行ticket有效性验证接口，返回XML文件，可解析请求者的身份信息
		casValidate = config.getInitParameter("com.cjsc.sso.validateUrl");

		// 客户端应用的“服务名称”，默认端口为80，可以在XML文件中指定
		casServerName = config.getInitParameter("com.cjsc.sso.serverName");
	}

	/**
	 * 过滤器的处理过程
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain fc) throws ServletException, IOException {
		// 判断当前是 HTTP请求
		if (!(request instanceof HttpServletRequest)
				|| !(response instanceof HttpServletResponse))
			throw new ServletException("统一认证登录只支持HTTP资源请求");
		// 判断当前是否需要 Wrap请求；
		if (wrapRequest) {
			request = new CJSCFilterRequestWrapper((HttpServletRequest) request);
		}
		HttpSession session = ((HttpServletRequest) request).getSession();
		// 判断是否已建立有效会话，如果已建立，过滤器不做处理。
		if (session != null && session.getAttribute(CAS_FILTER_USER) != null) {
			// 响应请求
			fc.doFilter(request, response);
			return;
		}

		String ticket = request.getParameter("ticket");
		// 当前没有建立有效会话时，如果请求中不包含：ticket，重定向到长江证券统一登录界面
		if (ticket == null || ticket.equals("")) {
			if (casLogin == null) {
				throw new ServletException(
						"应用系统的web.xml中缺少统一认证登录界面的接口参数： com.cjsc.sso.loginUrl。");
			}
			// 重定向到长江证券统一登录界面
			((HttpServletResponse) response).sendRedirect(casLogin
					+ "?service=" + getService((HttpServletRequest) request));
			return;
		}

		// 请求中含有ticket，判断ticket是否有效，并返回请求者的身份信息。
		String user = getAuthenticatedUser((HttpServletRequest) request);
		if (user == null) {
			// 重定向到长江证券统一登录界面
			((HttpServletResponse) response).sendRedirect(casLogin
					+ "?service=" + getService((HttpServletRequest) request));
			return;
		}
		if (session != null) {
			session.setAttribute(CAS_FILTER_USER, user);
			System.out.println("完成首次登录，将用户ID：[" + user + "]存入当前会话session中。");
			login3rdApp(user, (HttpServletRequest) request, (HttpServletResponse)response);
		}
		// 继续响应请求
		fc.doFilter(request, response);
	}

	public void destroy() {
	}

	public void login3rdApp(String userName, HttpServletRequest request, HttpServletResponse response) {
		// TODO 此处可扩展完成与第三方JAVA应用登录集成,调用登录接口
		
		SecurityUser securityUser =  this.defaultUserService.getByUserName(userName);
		
		
		logger.info("from our DB get > userName:"+securityUser.getUsername()+";password:"+securityUser.getPassword()+";authorities:"+securityUser.getAuthorities());
		
		try{
		if(AssertHelper.empty(securityUser)){
			
			
			response.sendRedirect(errorPage);
			return;
			
		}else{
			
			HttpRequestResponseHolder holder = new HttpRequestResponseHolder(request, response);
			
						
			securityInterceptor.registerLoginInfo(securityUser, holder);
			
			holder.getResponse().sendRedirect(firstPgae); 
		}
		}
		catch (IOException e) {
			logger.error(e.getMessage());
		}
	}

	/**
	 * @描述： 根据ticket解析出请求者的“用户ID” <br>
	 */
	private String getAuthenticatedUser(HttpServletRequest request)
			throws ServletException {
		ProxyTicketValidator pv = null;
		try {
			pv = new ProxyTicketValidator();
			pv.setCasValidateUrl(casValidate);
			pv.setServiceTicket(request.getParameter("ticket"));
			pv.setService(getService(request));
			pv.setRenew(Boolean.valueOf(casRenew).booleanValue());
			pv.validate();
			if (!pv.isAuthenticationSuccesful())
				throw new ServletException("认证失败: " + pv.getErrorCode() + ": "
						+ pv.getErrorMessage());
			if (pv.getProxyList().size() != 0) {
				if (casAuthorizedProxy == null) {
					throw new ServletException("页面请求不支持代理方式的 tickets。");
				} else {
					boolean authorized = false;
					String proxy = (String) pv.getProxyList().get(0);
					StringTokenizer casProxies = new StringTokenizer(
							casAuthorizedProxy);
					while (casProxies.hasMoreTokens()) {
						if (proxy.equals(casProxies.nextToken())) {
							authorized = true;
							break;
						}
					}
					if (!authorized) {
						throw new ServletException("未通过代理认证: '"
								+ pv.getProxyList().get(0) + "'。");
					}
				}
			}
			return pv.getUser();
		} catch (SAXException ex) {
			String xmlResponse = "";
			if (pv != null)
				xmlResponse = pv.getResponse();
			throw new ServletException(ex + " " + xmlResponse);
		} catch (ParserConfigurationException ex) {
			throw new ServletException(ex);
		} catch (IOException ex) {
			throw new ServletException(ex);
		}
	}

	/**
	 * @描述：获取用户当前访问的资源URL <br>
	 */
	private String getService(HttpServletRequest request)
			throws ServletException {
		// 需求确保web.xml文件中已
		if (casServerName == null && casServiceUrl == null) {
			throw new ServletException(
					"web.xml中缺少配置参数: com.cjsc.sso.serviceUrl 或 "
							+ "com.cjsc.sso.serverName。");
		}

		if (casServiceUrl != null) {
			return URLEncoder.encode(casServiceUrl);
		} else {
			return Util.getService(request, casServerName);
		}
	}
}
