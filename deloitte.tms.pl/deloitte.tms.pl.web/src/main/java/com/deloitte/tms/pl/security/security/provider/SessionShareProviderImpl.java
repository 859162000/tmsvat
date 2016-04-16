package com.deloitte.tms.pl.security.security.provider;

import java.lang.reflect.Method;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import com.deloitte.tms.pl.security.security.SessionShareProvider;

@Component("ling2.sessionShareProvider")
public class SessionShareProviderImpl implements SessionShareProvider{
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	private Boolean useSecureCookie = null;
	
	@Value("${ling2.cookieDomain}")
	String cookieDomain;
	@Value("${ling2.enablecookieDomain}")
	Boolean enablecookieDomain;
	
	private Method setHttpOnlyMethod=ReflectionUtils.findMethod(Cookie.class,"setHttpOnly", boolean.class);
	
	@Override
	public String getSid(HttpRequestResponseHolder holder) {
		String sid=getSidFromCookie(holder.getRequest());
		//System.out.println(holder.getRequest().getRequestURL()+" "+sid);
		if(sid==null){
			sid=UUID.randomUUID().toString();
			addSidToCookie(sid,holder);
		}
		return sid;
	}
	public void clearSid(HttpServletRequest request,HttpServletResponse response){
		logger.debug("Cancelling cookie");
        Cookie cookie = new Cookie(COOKIE_SID, null);
        cookie.setMaxAge(0);
        cookie.setPath(getCookiePath(request));
        if(enablecookieDomain){
        	cookie.setDomain(getCookieDomain());
        }
        response.addCookie(cookie);
	}
	@Override
	public String getSidCacheKey(String sid) {
		return COOKIE_SID+"_"+sid;
	}
	private String getSidFromCookie(HttpServletRequest request){
		Cookie[] cookies = request.getCookies();

        if ((cookies == null) || (cookies.length == 0)) {
            return null;
        }

        for (Cookie cookie : cookies) {
            if (COOKIE_SID.equals(cookie.getName())) {
                return cookie.getValue();
            }
        }

        return null;
	}
	private void addSidToCookie(String sid,HttpRequestResponseHolder holder){
		HttpServletRequest request=holder.getRequest();
		HttpServletResponse response=holder.getResponse();
        Cookie cookie = new Cookie(COOKIE_SID, sid);
        cookie.setMaxAge(SIDLIFE);
        cookie.setPath(getCookiePath(request));
        if(enablecookieDomain){
        	cookie.setDomain(getCookieDomain());
        }
        if (useSecureCookie == null) {
            cookie.setSecure(request.isSecure());
        } else {
            cookie.setSecure(useSecureCookie);
        }

        if(setHttpOnlyMethod != null) {
            ReflectionUtils.invokeMethod(setHttpOnlyMethod, cookie, Boolean.TRUE);
        } else if (logger.isDebugEnabled()) {
            //logger.debug("Note: Cookie will not be marked as HttpOnly because you are not using Servlet 3.0 (Cookie#setHttpOnly(boolean) was not found).");
        }
        
        response.addCookie(cookie);
	}
    protected String getCookiePath(HttpServletRequest request) {
        String contextPath = request.getContextPath();
        return contextPath.length() > 0 ? contextPath : "/";
    }
    
    public void setCookieDomain(String cookieDomain) {
		this.cookieDomain = cookieDomain;
	}
    
    public String getCookieDomain() {
    	return cookieDomain;
    }
}
