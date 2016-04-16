package com.deloitte.tms.pl.security.service;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;

import com.deloitte.tms.pl.security.model.SecurityUser;

/**
 * @since 2013-1-22
 * @author Jacky.gao
 */
public interface IFrameworkService {
	public static final String BEAN_ID="ling2.frameworkService";
	void authenticate(SecurityUser user,UsernamePasswordAuthenticationToken authentication,Boolean isvalidatePassword) throws AuthenticationException;
}
