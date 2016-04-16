package com.deloitte.tms.pl.core.commons.utils;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.web.context.WebApplicationContext;

public class SpringUtil implements ApplicationContextAware{
	private static ApplicationContext context;

	public void setApplicationContext(ApplicationContext contex)
			throws BeansException {
		this.context = contex;
	}
	public static ApplicationContext getContext() {
		return context;
	}
	public static <X> X getBean(String beanname)
	{
		return (X) getContext().getBean(beanname);
	}
	public String getAppRootPath() {
		if (WebApplicationContext.class
				.isAssignableFrom(this.context.getClass())) {
			WebApplicationContext wac = (WebApplicationContext) this.context;
			String appRootPath = wac.getServletContext().getRealPath("/");
			if (appRootPath.endsWith("/") || appRootPath.endsWith("\\")) {
				appRootPath = appRootPath
						.substring(0, appRootPath.length() - 1);
			}
			return appRootPath;
		} else {
			return null;
		}
	}
	public static Collection getBeansOfType(Class type)
	{
		return context.getBeansOfType(type).values();
	}
	
	public static <T> Map<String, T> getBeansOfTypeMap(Class<T> type) {
		return context.getBeansOfType(type);
	}

}