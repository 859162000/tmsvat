package com.deloitte.tms.pl.core.commons.utils;  
  
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.ReflectionUtils;
  
public class CookieUtils {  
	
	protected static final Log logger = LogFactory.getLog(CookieUtils.class);
	
	public static final String _last_jumping_url="_last_jumping_url";
	
	public static final int TWO_WEEKS_S = 1209600;
	
    /** 
     * 获得指定cookie中的值 
     *  
     * @param request 
     * @param cookieName 
     *            要查找的cookie的名字 
     * @return 返回指定Cookie中的字符串值 
     */  
    public static String getCookieValue(HttpServletRequest request, String cookieName) {  
    	Cookie cookie=getCookie(request, cookieName);
    	if(cookie!=null){
    		try {
				return URLDecoder.decode(cookie.getValue(),"UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	return null;
    } 
    public static Cookie getCookie(HttpServletRequest request, String cookieName) {  
    	if(AssertHelper.empty(cookieName)){
    		return null;
    	}
        Cookie cookies[] = request.getCookies();  
        if (cookies != null) {  
            for (Cookie cookie : cookies) {  
                // 找到指定的cookie  
                if (cookie != null && cookieName.equals(cookie.getName())) {  
                    return cookie;  
                }  
            }  
        }  
        return null;  
    } 
    public static void addCookie(HttpServletRequest request, HttpServletResponse response,String cookieName, String cookieValue) {
    	if(AssertHelper.notEmpty(cookieValue)){
    		try {
				cookieValue=URLEncoder.encode(cookieValue,"UTF-8");
//				System.out.println(URLDecoder.decode(cookieValue));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
    	}
    	Cookie cookie = new Cookie(cookieName, cookieValue);
        cookie.setMaxAge(TWO_WEEKS_S);
        cookie.setPath(getCookiePath(request));
        
        if(PropertiesUtils.getBoolean("ling2.enablecookieDomain")){
        	cookie.setDomain(getCookieDomain());
        }      

        cookie.setSecure(request.isSecure());

        if(getHttpOnlyMethod() != null) {
            ReflectionUtils.invokeMethod(getHttpOnlyMethod(), cookie, Boolean.TRUE);
        } else if (logger.isDebugEnabled()) {
            logger.debug("Note: Cookie will not be marked as HttpOnly because you are not using Servlet 3.0 (Cookie#setHttpOnly(boolean) was not found).");
        }
        response.addCookie(cookie);
	}
    private static Method getHttpOnlyMethod() {
    	Method setHttpOnlyMethod=ReflectionUtils.findMethod(Cookie.class,"setHttpOnly", boolean.class);
    	return setHttpOnlyMethod;
	}
    
    public static String getCookiePath(HttpServletRequest request) {
        String contextPath = request.getContextPath();
        return contextPath.length() > 0 ? contextPath : "/";
    }
    public static String getCookieDomain() {
    	return (String) PropertiesUtils.get("ling2.cookieDomain");
    }
    public static void cancelCookie(HttpServletRequest request, HttpServletResponse response, String cookieName) {
        Cookie cookie = new Cookie(cookieName,null);
        cookie.setMaxAge(0);
        
        cookie.setPath(getCookiePath(request));
        
        if(PropertiesUtils.getBoolean("ling2.enablecookieDomain")){
        	cookie.setDomain(getCookieDomain());
        } 
        response.addCookie(cookie);
    }
    public static String getLastJumpUrl(HttpServletRequest request){
    	return getCookieValue(request, _last_jumping_url);
    }
    public static void cancelLastJumpUrl(HttpServletRequest request,HttpServletResponse response){
    	cancelCookie(request, response,_last_jumping_url);
    }
}  
