package com.deloitte.tms.pl.security.security.filter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationFilter;
import org.springframework.util.Assert;

import com.deloitte.tms.pl.core.context.ContextHolder;
import com.deloitte.tms.pl.core.context.IContextHolder;
import com.deloitte.tms.pl.security.model.impl.AbstractUser;
import com.deloitte.tms.pl.security.security.cookielogin.CookieLoginTokenService;
import com.deloitte.tms.pl.security.service.IUserService;

/**
 * @author Jacky.gao
 * @since 2013-2-24
 */
public class RememberMeLoginFilter extends RememberMeAuthenticationFilter {
	private IUserService userService;
	CookieLoginTokenService cookieLoginTokenService;
	IContextHolder contextHolder;
	private SimpleUrlAuthenticationFailureHandler authenticationFailureHandler;
	public RememberMeLoginFilter(AuthenticationManager authenticationManager,
			RememberMeServices rememberMeServices) {
		super(authenticationManager, rememberMeServices);
	}

	@Override
	protected void onSuccessfulAuthentication(HttpServletRequest request,
			HttpServletResponse response, Authentication authResult) {
		Object user=authResult.getPrincipal();
		Assert.notNull(user,"通过Remember Me方式登录成功后未获取到用户信息");
		if(user instanceof AbstractUser){
			ContextHolder.getHttpSession().setAttribute(ContextHolder.LOGIN_USER_SESSION_KEY, user);
		}else if(user instanceof String){
			ContextHolder.getHttpSession().setAttribute(ContextHolder.LOGIN_USER_SESSION_KEY, userService.loadUserByUsername((String)user));			
		}else{
			throw new RuntimeException("Unsupport current principal["+user+"]");
		}
		cookieLoginTokenService.addCookie(ContextHolder.getRequest(), ContextHolder.getResponse(), authResult);
	}

	@Override
	protected void onUnsuccessfulAuthentication(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException failed) {
		try {
			authenticationFailureHandler.onAuthenticationFailure(request, response, failed);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (ServletException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public void setAuthenticationFailureHandler(
			SimpleUrlAuthenticationFailureHandler authenticationFailureHandler) {
		this.authenticationFailureHandler = authenticationFailureHandler;
	}
	public void setContextHolder(IContextHolder contextHolder) {
		this.contextHolder = contextHolder;
	}
	
	public void setCookieLoginTokenService(
			CookieLoginTokenService cookieLoginTokenService) {
		this.cookieLoginTokenService = cookieLoginTokenService;
	}
}
