package com.deloitte.tms.pl.core.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import com.deloitte.tms.pl.core.context.utils.ContextUtils;
import com.deloitte.tms.pl.core.controller.IController;

/**
 * @author Jacky.gao
 * @since 2013-2-21
 */
@Component(ControllerFilter.BEAN_ID)
public class ControllerFilter extends GenericFilterBean implements ApplicationContextAware{
	public static final String BEAN_ID="ling2.controllerFilter";
	@Value("${ling2.controllerSuffix}")
	private String controllerSuffix="action";
	private Collection<IController> controllers;
	public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest)request;
		HttpServletResponse res=(HttpServletResponse)response;
		String url=this.getUrl(req);
		if(url.indexOf("com.ling2.")!=-1){
			System.out.println(" not recommand begin with com.ling2");
		}
		if(url.endsWith(controllerSuffix)){
			IController target=null;
			url=url.substring(0,url.length()-controllerSuffix.length()-1);
			for(IController controller:controllers){
				if(controller.getUrl().equals(url)){
					target=controller;
					break;
				}
			}
			if(target==null){
				writeInfo(res, "action ["+url+"] was not found");
				return;
			}
			if(target.isDisabled()){
				writeInfo(res, "action ["+url+"] was disabled");
				return;
			}
			if(!target.anonymousAccess()){
				if(ContextUtils.getLoginUser()!=null){
					target.execute(req, (HttpServletResponse)response);					
				}else{
					writeInfo(res, "action ["+url+"] need login first");					
				}
			}else{
				target.execute(req, (HttpServletResponse)response);
			}
		}else{
			chain.doFilter(request, response);
		}
	}
	private void writeInfo(HttpServletResponse res, String info)
			throws IOException {
		PrintWriter pw=res.getWriter();
		try{
			pw.write("<font color='red'>"+info+"</font>");
		}finally{
			pw.flush();
			pw.close();
		}
	}
	private String getUrl(HttpServletRequest request){
		String contextPath=request.getContextPath();
		String uri = request.getRequestURI();
		int pathParamIndex = uri.indexOf(';');
		if (pathParamIndex > 0) {
			// strip everything from the first semi-colon
			uri = uri.substring(0, pathParamIndex);
		}
		int queryParamIndex = uri.indexOf('?');
		if (queryParamIndex > 0) {
			// strip everything from the first question mark
			uri = uri.substring(0, queryParamIndex);
		}
		if(contextPath.length()>1){
			uri=uri.substring(contextPath.length(),uri.length());
		}
		return uri;
	}
	public String getControllerSuffix() {
		return controllerSuffix;
	}
	public void setControllerSuffix(String controllerSuffix) {
		this.controllerSuffix = controllerSuffix;
	}
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		controllers=applicationContext.getBeansOfType(IController.class).values();
	}
}
