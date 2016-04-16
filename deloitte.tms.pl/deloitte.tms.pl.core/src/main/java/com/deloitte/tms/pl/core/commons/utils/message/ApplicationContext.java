package com.deloitte.tms.pl.core.commons.utils.message;

import java.util.Locale;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class ApplicationContext {

	@SuppressWarnings("unchecked")
	public static final <T> Object getBean(String beanId) {

		WebApplicationContext appContext = WebApplicationContextUtils
				.getWebApplicationContext(SessionContext.getSession()
						.getServletContext());

		return (T) appContext.getBean(beanId);
	}

	public static final String getMessage(String key) {
		return getMessage(key, new Object[0]);
	}
	
	public static final String getMessage(String key, Object[] args) {
		WebApplicationContext appContext = WebApplicationContextUtils
				.getWebApplicationContext(SessionContext.getSession()
						.getServletContext());

		return appContext.getMessage(key, args, Locale.SIMPLIFIED_CHINESE);
	}
}
