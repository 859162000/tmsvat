package com.deloitte.tms.pl.security.security.provider;

import java.util.Collection;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.context.HttpRequestResponseHolder;

import com.deloitte.tms.pl.cache.CacheUtils;
import com.deloitte.tms.pl.core.commons.constant.LoginConstantDef;
import com.deloitte.tms.pl.core.commons.utils.CookieUtils;
import com.deloitte.tms.pl.core.context.ContextHolder;
import com.deloitte.tms.pl.core.context.IContextHolder;
import com.deloitte.tms.pl.security.model.SecurityUser;
import com.deloitte.tms.pl.security.security.ISecurityInterceptor;
import com.deloitte.tms.pl.security.security.InterceptorType;
import com.deloitte.tms.pl.security.security.SessionShareProvider;
import com.deloitte.tms.pl.security.security.authentication.Oauth2Authentication;
import com.deloitte.tms.pl.security.security.cookielogin.CookieLoginTokenService;
import com.deloitte.tms.pl.security.service.IFrameworkService;

/**
 * @since 2013-1-22
 * @author Jacky.gao
 */
public class FormLoginAuthenticationProvider extends DaoAuthenticationProvider implements ApplicationContextAware{
	private Collection<ISecurityInterceptor> securityInterceptors;
	private IFrameworkService frameworkService;
	CookieLoginTokenService cookieLoginTokenService;
	IContextHolder contextHolder;
	SessionShareProvider sessionShareProvider;
	
	
	
	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		SecurityUser user=(SecurityUser)userDetails;
		HttpRequestResponseHolder holder=new HttpRequestResponseHolder(ContextHolder.getRequest(),ContextHolder.getResponse());
		this.doInterceptor(InterceptorType.before, holder);
		try{
			Boolean isvalidatePassword=true;
			if(authentication instanceof Oauth2Authentication)
			{
				isvalidatePassword=false;
			}
			frameworkService.authenticate(user, authentication,isvalidatePassword);
		}catch(Exception ex){
			this.doInterceptor(InterceptorType.failure, holder);	
			CookieUtils.addCookie(ContextHolder.getRequest(),ContextHolder.getResponse(),LoginConstantDef.LOGIN_ERROKEY,ex.getMessage());
			throw new AuthenticationServiceException(ex.getMessage());
		}
    	ContextHolder.getHttpSession().setAttribute(ContextHolder.LOGIN_USER_SESSION_KEY,user);
    	CookieUtils.cancelCookie(ContextHolder.getRequest(),ContextHolder.getResponse(),LoginConstantDef.LOGIN_ERROKEY);
    	this.doInterceptor(InterceptorType.success, holder);
		contextHolder.initUserDetail(user, ContextHolder.getRequest(),ContextHolder.getResponse());
		cookieLoginTokenService.addCookie(ContextHolder.getRequest(), ContextHolder.getResponse(), authentication);
		CacheUtils.putCacheObject(sessionShareProvider.getSidCacheKey(ContextHolder.getSid()), user.getUsername());
		this.doInterceptor(InterceptorType.success, holder);
	}
	public IFrameworkService getFrameworkService() {
		return frameworkService;
	}
	public void setFrameworkService(IFrameworkService frameworkService) {
		this.frameworkService = frameworkService;
	}
	
	public Collection<ISecurityInterceptor> getSecurityInterceptors() {
		return securityInterceptors;
	}
	public void setSecurityInterceptors(
			Collection<ISecurityInterceptor> securityInterceptors) {
		this.securityInterceptors = securityInterceptors;
	}
	public IContextHolder getContextHolder() {
		return contextHolder;
	}
	public void setContextHolder(IContextHolder contextHolder) {
		this.contextHolder = contextHolder;
	}
	
	public void setSessionShareProvider(SessionShareProvider sessionShareProvider) {
		this.sessionShareProvider = sessionShareProvider;
	}
	public void setCookieLoginTokenService(
			CookieLoginTokenService cookieLoginTokenService) {
		this.cookieLoginTokenService = cookieLoginTokenService;
	}

	private void doInterceptor(InterceptorType type,HttpRequestResponseHolder holder){
		for(ISecurityInterceptor intercepor:securityInterceptors){
			if(type.equals(InterceptorType.before)){
				intercepor.beforeLogin(holder);
			}else if(type.equals(InterceptorType.success)){
				intercepor.loginSuccess(holder);
			}else if(type.equals(InterceptorType.failure)){
				intercepor.loginFailure(holder);
			}
		}
	}
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		securityInterceptors=applicationContext.getBeansOfType(ISecurityInterceptor.class).values();
	}
}
