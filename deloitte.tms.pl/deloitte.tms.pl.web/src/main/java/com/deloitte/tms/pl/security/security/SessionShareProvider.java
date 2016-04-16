package com.deloitte.tms.pl.security.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.context.HttpRequestResponseHolder;

public interface SessionShareProvider {	
	public static final String COOKIE_SID="LING_sid";
	public static final int SIDLIFE = 1209600;
	public String getSid(HttpRequestResponseHolder holder);
	public void clearSid(HttpServletRequest request,HttpServletResponse response);
	public String getSidCacheKey(String sid);
}
