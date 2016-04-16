package com.deloitte.tms.pl.security.security;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import com.deloitte.tms.pl.security.model.SecurityUser;

/**
 * @since 2013-1-29
 * @author Jacky.gao
 */
public class UserAuthentication implements Authentication {
	private static final long serialVersionUID = 7275039159608358294L;
	private SecurityUser user;
	private boolean authenticated;
	public UserAuthentication(SecurityUser user){
		this.user=user;
	}
	public String getName() {
		return user.getUsername();
	}
	
	public Collection getAuthorities() {
		return user.getRoles();
	}

	public Object getCredentials() {
		return user.getAuthorities();
	}

	public Object getDetails() {
		return user;
	}

	public Object getPrincipal() {
		return user;
	}

	public boolean isAuthenticated() {
		return authenticated;
	}

	public void setAuthenticated(boolean isAuthenticated)throws IllegalArgumentException {
		this.authenticated=isAuthenticated;
	}
}
