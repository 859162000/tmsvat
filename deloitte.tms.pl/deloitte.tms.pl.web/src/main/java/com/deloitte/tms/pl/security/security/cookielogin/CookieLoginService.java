package com.deloitte.tms.pl.security.security.cookielogin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;

import com.deloitte.tms.pl.security.model.SecurityUser;

public interface CookieLoginService {
	
	String cookieName = "LING_USERLOGININFO_COOKIE_KEY";
	String nickName = "LING_nickName";
	
	/**
	 * 通过cookie的信息进行自动登录,在Remember和ANONYMOUS前,返回UsernamePasswordAuthenticationToken以供FormLoginAuthenticationProvider处理
	 * @param request
	 * @param response
	 * @return
	 */
    Authentication autoLogin(HttpServletRequest request, HttpServletResponse response);
    /**
	 * 通过cookie的信息进行自动登录,在Remember和ANONYMOUS前,返回UsernamePasswordAuthenticationToken以供FormLoginAuthenticationProvider处理
	 * @param request
	 * @param response
	 * @return
	 */
    SecurityUser getLoginUser(HttpServletRequest request, HttpServletResponse response);
}
