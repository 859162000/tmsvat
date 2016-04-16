package com.deloitte.tms.pl.core.context;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.deloitte.tms.pl.security.model.SecurityUser;

public interface IContextHolder {
	public static final String BEAN_ID="contextHolder";
	public void initUserDetail(SecurityUser user,HttpServletRequest request,HttpServletResponse response);
}
