package com.deloitte.tms.pl.security.security.handler;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.security.security.SessionShareProvider;
import com.deloitte.tms.pl.security.security.cookielogin.CookieLoginTokenService;

@Component
public class DefaultLogoutHandler implements LogoutHandler{
	@Resource
	CookieLoginTokenService cookieLoginTokenService;
	@Resource
	SessionShareProvider sessionShareProvider;
	@Override
	public void logout(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication) {
		cookieLoginTokenService.cancelCookie(request, response);
		sessionShareProvider.clearSid(request,response);
	}

}
