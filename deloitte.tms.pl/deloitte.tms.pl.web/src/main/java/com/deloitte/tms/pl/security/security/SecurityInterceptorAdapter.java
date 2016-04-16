package com.deloitte.tms.pl.security.security;

import java.io.IOException;
import java.net.URLDecoder;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.cache.CacheUtils;
import com.deloitte.tms.pl.core.commons.utils.CookieUtils;
import com.deloitte.tms.pl.core.context.ContextHolder;
import com.deloitte.tms.pl.core.context.IContextHolder;
import com.deloitte.tms.pl.core.context.utils.ContextUtils;
import com.deloitte.tms.pl.security.model.SecurityUser;
import com.deloitte.tms.pl.security.security.cookielogin.CookieLoginTokenService;
import com.deloitte.tms.pl.security.service.IUserService;

/**
 * @author Jacky.gao
 * @since 2013-5-10
 */
@Component("securityInterceptor")
public class SecurityInterceptorAdapter implements ISecurityInterceptor {
	protected final Log logger = LogFactory.getLog(getClass());
	@Resource
	IContextHolder contextHolder;
	@Resource
	CookieLoginTokenService cookieLoginTokenService;
	@Resource
	SessionShareProvider sessionShareProvider;
	
	// 普通 beforeAuthorization-->authorizationSuccess--authorizationFailure
	// 登陆 beforeAuthorization-->beforeLogin--loginSuccess-->authorizationSuccess
	
	public void beforeLogin(HttpRequestResponseHolder holder) {
//		System.out.println("beforeLogin");
	}

	public void loginSuccess(HttpRequestResponseHolder holder) {
//		System.out.println("loginSuccess");
		/******************这样是不行的**********************/
//		String lasturl=CookieUtils.getLastJumpUrl(holder.getRequest());
//		if(lasturl!=null){
//			CookieUtils.cancelLastJumpUrl(holder.getRequest(),holder.getResponse());
//			try {
//				lasturl=URLDecoder.decode(lasturl);
//				if (holder.getResponse().isCommitted()) {
//		            logger.debug("Response has already been committed. Unable to redirect to " + lasturl);
//		        }else{
//		        	holder.getResponse().sendRedirect(lasturl);
//		        }				
//			} catch (IOException e) {
//				logger.error(" redirect :"+lasturl+"fail");
//			}
//		}
		/*********这样是不行的********************/
	}

	public void loginFailure(HttpRequestResponseHolder holder) {
//		System.out.println("loginFailure");
	}

	public void beforeAuthorization(HttpRequestResponseHolder holder) {
//		System.out.println("beforeAuthorization");
		if(isSidCheck(holder)){
			String sid=sessionShareProvider.getSid(holder);
			//System.out.println(sid);
			ContextHolder.setSid(sid);
		}
	}

	public void authorizationSuccess(HttpRequestResponseHolder holder) {
//		System.out.println("authorizationSuccess");
		//添加供跨服务器的cookie 老方式
	}

	public void authorizationFailure(HttpRequestResponseHolder holder) {
//		System.out.println("authorizationFailure");
	}
	
	/**
	 * 在用户通过其它途径取取到登录用户并认证成功之后，这里允许其提供一个IUser接口实现及HttpRequestResponseHolder对象后,<br>
	 * 这个方法可以将这个IUser对应的用户信息放到系统上下文当中，标明该用户登录成功
	 * @param user IUser接口实现类对象
	 * @param holder 一个包含Request及Response的HttpRequestResponseHolder对象
	 */
	public void registerLoginInfo(SecurityUser user,HttpRequestResponseHolder holder){
		holder.getRequest().getSession().setAttribute(ContextHolder.LOGIN_USER_SESSION_KEY, user);
		UsernamePasswordAuthenticationToken authentication=new UsernamePasswordAuthenticationToken(user,user.getPassword(),user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		RememberMeServices rememberMeServices=ContextHolder.getBean("ling2.tokenBasedRememberMeServices");
		rememberMeServices.loginSuccess(holder.getRequest(), holder.getResponse(), authentication);
		SessionAuthenticationStrategy sessionStrategy=ContextHolder.getBean("ling2.concurrentSessionControlStrategy");
		sessionStrategy.onAuthentication(authentication, holder.getRequest(), holder.getResponse());
		contextHolder.initUserDetail(user, holder.getRequest(), holder.getResponse());
		cookieLoginTokenService.addCookie(ContextHolder.getRequest(), ContextHolder.getResponse(), authentication);
	}

	public void setContextHolder(IContextHolder contextHolder) {
		this.contextHolder = contextHolder;
	}

	public void setCookieLoginTokenService(
			CookieLoginTokenService cookieLoginTokenService) {
		this.cookieLoginTokenService = cookieLoginTokenService;
	}
	private boolean isSidCheck(HttpRequestResponseHolder holder){
//		if(holder.getRequest().getRequestURI().indexOf(".ljson")<0){
			return true;
//		}
//		return false;
	}
}
