package com.deloitte.tms.pl.security.security.cookielogin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;

public interface CookieLoginTokenService {
	/**
     * 登录失败的处理,去除cookie中关于cookie登录的内容
     * @param request
     * @param response
     */
    void cancelCookie(HttpServletRequest request, HttpServletResponse response);
    
    /**
     * 任何登录成功后都要调用这段代码将用户信息写入cookie
     * @param request
     * @param response
     * @param successfulAuthentication
     */
    void addCookie(HttpServletRequest request, HttpServletResponse response,
        Authentication successfulAuthentication);
}
