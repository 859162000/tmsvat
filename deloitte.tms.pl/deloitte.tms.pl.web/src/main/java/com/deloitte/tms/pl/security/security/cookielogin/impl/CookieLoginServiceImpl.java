package com.deloitte.tms.pl.security.security.cookielogin.impl;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.core.commons.exception.BusinessException;
import com.deloitte.tms.pl.security.model.SecurityUser;
import com.deloitte.tms.pl.security.security.IRetrivePreAuthenticatedUser;

@Component("ling2.cookieLoginService")
public class CookieLoginServiceImpl extends AbstractCookieLoginService implements IRetrivePreAuthenticatedUser{
	
	@Override
	public Authentication autoLogin(HttpServletRequest request,
			HttpServletResponse response) {
		UserDetails user=getLoginUser(request,response);
		if(user!=null){
			return createSuccessfulAuthentication(request, user);
		}else{
			throw new BusinessException("没有从cookie中找到用户信息");
		}		
	}
	public SecurityUser getLoginUser(HttpServletRequest request, HttpServletResponse response){
		//从cookie中获取加密的用户信息
		String cookieToken=extractRememberMeCookie(request);
		if(cookieToken!=null){
			String[] cookieTokens = decodeCookie(cookieToken);
			//获取用户名
			//生成UsernamePasswordAuthenticationToken 
			UserDetails user=processAutoLoginCookie(cookieTokens,request,response);
			return (SecurityUser) user;
		}else{
			return null;
		}
	}
	 /**
     * Sets a "cancel cookie" (with maxAge = 0) on the response to disable persistent logins.
     */
    public void cancelCookie(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("Cancelling cookie");
        Cookie cookie = new Cookie(cookieName, null);
        cookie.setMaxAge(0);
        
        cookie.setPath(getCookiePath(request));
        if(enablecookieDomain){
        	cookie.setDomain(getCookieDomain());
        }
        
        response.addCookie(cookie);
        
        Cookie nickcookie = new Cookie(nickName, null);
        nickcookie.setMaxAge(0);
        
        nickcookie.setPath(getCookiePath(request));
        if(enablecookieDomain){
        	nickcookie.setDomain(getCookieDomain());
        }
        
        response.addCookie(nickcookie);
    }
	@Override
	public void addCookie(HttpServletRequest request,
			HttpServletResponse response,
			Authentication successfulAuthentication) {		
		setUserInfoInCookie(request, response, successfulAuthentication);
	}

	@Override
	public SecurityUser retrive(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {
		return getLoginUser(request, response);
	}
}
