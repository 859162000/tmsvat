package com.deloitte.tms.pl.workflow.console.rest.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author Jacky.gao
 * @since 2013年9月20日
 */
public class UfloHandlerInterceptor extends HandlerInterceptorAdapter implements ApplicationContextAware{
	private List<User> users=new ArrayList<User>();
	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
		response.setContentType("text/xml;charset=UTF-8");
		if(users.size()==0){
			return true;
		}
		String username=request.getHeader("uflo.access.username");
		String password=request.getHeader("uflo.access.password");
		if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
			throw new SecurityException("Access denied.");
		}
		for(User user:users){
			if(username.equals(user.getUsername()) && password.equals(user.getPassword())){
				return true;
			}
		}
		throw new SecurityException("Access denied.");
	}

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		Collection<UserProvider> providers=new ArrayList<UserProvider>();
		Collection<UserProvider> coll=applicationContext.getBeansOfType(UserProvider.class).values();
		if(coll.size()>0){
			providers.addAll(coll);
		}
		ApplicationContext parentContext=applicationContext.getParent();
		if(parentContext!=null){
			Collection<UserProvider> providerColl=parentContext.getBeansOfType(UserProvider.class).values();
			if(providerColl.size()>0){
				providers.addAll(providerColl);				
			}
		}
		for(UserProvider provider:providers){
			if(provider.getUsers()!=null && provider.getUsers().size()>0){
				users.addAll(provider.getUsers());				
			}
		}
	}
}
