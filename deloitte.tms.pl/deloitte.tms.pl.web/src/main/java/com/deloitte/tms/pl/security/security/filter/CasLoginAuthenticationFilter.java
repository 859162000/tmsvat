package com.deloitte.tms.pl.security.security.filter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.cas.web.CasAuthenticationFilter;
import org.springframework.security.core.Authentication;

import com.deloitte.tms.pl.core.context.ContextHolder;
import com.deloitte.tms.pl.core.context.IContextHolder;
import com.deloitte.tms.pl.security.security.cookielogin.CookieLoginTokenService;

/**
 * @author Jacky.gao
 * @since 2013-5-23
 */
public class CasLoginAuthenticationFilter extends CasAuthenticationFilter {
	CookieLoginTokenService cookieLoginTokenService;
	IContextHolder contextHolder;
	@Override
	@Deprecated
	protected void successfulAuthentication(HttpServletRequest request,
			HttpServletResponse response, Authentication authResult)
			throws IOException, ServletException {
		super.successfulAuthentication(request, response, authResult);
		request.getSession().setAttribute(ContextHolder.LOGIN_USER_SESSION_KEY, authResult.getPrincipal());
		cookieLoginTokenService.addCookie(ContextHolder.getRequest(), ContextHolder.getResponse(), authResult);
	}
	public void setCookieLoginTokenService(
			CookieLoginTokenService cookieLoginTokenService) {
		this.cookieLoginTokenService = cookieLoginTokenService;
	}
	public void setContextHolder(IContextHolder contextHolder) {
		this.contextHolder = contextHolder;
	}
	
}
