package com.deloitte.tms.pl.security.security.authentication;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class Oauth2Authentication extends UsernamePasswordAuthenticationToken{
	 private final String oauthType="";
	/**
	 * 
	 */
	private static final long serialVersionUID = 3173330818312265282L;

	public Oauth2Authentication(Object principal) {
		super(principal, "");
	}

	public String getOauthType() {
		return oauthType;
	}


}
