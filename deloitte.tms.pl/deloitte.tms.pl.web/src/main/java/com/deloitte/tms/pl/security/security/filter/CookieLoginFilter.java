package com.deloitte.tms.pl.security.security.filter;
//package com.ling2.security.security.filter;
//
//import java.io.IOException;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.filter.GenericFilterBean;
//
//import com.ling2.security.cookielogin.CookieLoginService;
//
//public class CookieLoginFilter extends GenericFilterBean{
//	
//	CookieLoginService cookieLoginService;
//
//	@Override
//	public void doFilter(ServletRequest req, ServletResponse res,
//			FilterChain chain) throws IOException, ServletException {	
//			
//		 	HttpServletRequest request = (HttpServletRequest) req;
//	        HttpServletResponse response = (HttpServletResponse) res;
//	        System.out.println(request.getRequestURL());
//	        if (SecurityContextHolder.getContext().getAuthentication() == null) {
//	        	try {
//					Authentication authentication=cookieLoginService.autoLogin(request, response);
//					 // Store to SecurityContextHolder
//					SecurityContextHolder.getContext().setAuthentication(authentication);
//					cookieLoginService.loginSuccess(request, response, authentication);
//	        	} catch (Exception e) {
//					cookieLoginService.loginFail(request, response);
//				}
//	            chain.doFilter(request, response);
//	        } else {
//	            chain.doFilter(request, response);
//	        }
//	}
//
//	public void setCookieLoginService(CookieLoginService cookieLoginService) {
//		this.cookieLoginService = cookieLoginService;
//	}
//
//}
