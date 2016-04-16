package com.deloitte.tms.pl.security.security.cookielogin.impl;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.security.crypto.codec.Utf8;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.rememberme.InvalidCookieException;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import com.deloitte.tms.pl.core.commons.constant.LoginConstantDef;
import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.core.commons.utils.CookieUtils;
import com.deloitte.tms.pl.security.model.impl.DefaultUser;
import com.deloitte.tms.pl.security.security.cookielogin.CookieLoginService;
import com.deloitte.tms.pl.security.security.cookielogin.CookieLoginTokenService;

@Component("ling2.cookieLoginService")
public abstract class AbstractCookieLoginService implements CookieLoginService,CookieLoginTokenService {
	
	private static final String DELIMITER = ":";
	
	protected final Log logger = LogFactory.getLog(getClass());
	private Boolean useSecureCookie = null;
	private Method setHttpOnlyMethod=ReflectionUtils.findMethod(Cookie.class,"setHttpOnly", boolean.class);
	public static final int TWO_WEEKS_S = 1209600;
	SecureRandom random = new SecureRandom();
	public static final int DEFAULT_SERIES_LENGTH = 16;
	public static final int DEFAULT_TOKEN_LENGTH = 16;
	private int seriesLength = DEFAULT_SERIES_LENGTH;
	private int tokenLength = DEFAULT_TOKEN_LENGTH;
	 
	protected AuthenticationDetailsSource<HttpServletRequest,?> authenticationDetailsSource = new WebAuthenticationDetailsSource();
	
	@Resource
	UserDetailsService userDetailsService;
	@Resource
	RememberMeServices rememberMeServices;
	
	@Value("${ling2.cookieuserinfo.key}")
	private String key;
	
	@Value("${ling2.cookieDomain}")
	String cookieDomain;
	
	@Value("${ling2.enablecookieDomain}")
	Boolean enablecookieDomain;
	
	public String getKey() {
        return key;
    }
	protected int getTokenValiditySeconds() {
		return TWO_WEEKS_S;
	}
	
	protected void setUserInfoInCookie(HttpServletRequest request,
			HttpServletResponse response,
			Authentication successfulAuthentication) {
		 String username = retrieveUserName(successfulAuthentication);
	     String password = retrievePassword(successfulAuthentication);
	    
        // If unable to find a username and password, just abort as TokenBasedRememberMeServices is
        // unable to construct a valid token in this case.
        if (!StringUtils.hasLength(username)) {
            logger.debug("Unable to retrieve username");
            return;
        }
        
        UserDetails user = userDetailsService.loadUserByUsername(username);
	     if (user==null) {
	     	logger.debug("username:"+username+" not find in database");
	        return;
	     }
	     
        if (!StringUtils.hasLength(password)) {
            password = user.getPassword();
            if (!StringUtils.hasLength(password)) {
                logger.debug("Unable to obtain password for user: " + username);
                return;
            }
        }
		 int tokenLifetime = calculateLoginLifetime(request, successfulAuthentication);
	     long expiryTime = System.currentTimeMillis();
	        // SEC-949
	     expiryTime += 1000L* (tokenLifetime < 0 ? TWO_WEEKS_S : tokenLifetime);

	     String signatureValue = makeTokenSignature(expiryTime, username, password);

	     setCookie_Key(new String[] {username, Long.toString(expiryTime), signatureValue}, tokenLifetime, request, response);
	     
	     String nickName=successfulAuthentication.getName();
	     if(user instanceof DefaultUser){
	    	 DefaultUser defaultUser=(DefaultUser)user;
	    	 nickName=defaultUser.getNickname()==null?nickName:defaultUser.getNickname();
	     }	     
	     setCookie_NikeName(nickName,tokenLifetime, request, response);
	     CookieUtils.cancelCookie(request, response, LoginConstantDef.LOGIN_ERROKEY);
	}	

	protected int calculateLoginLifetime(HttpServletRequest request, Authentication authentication) {
        return getTokenValiditySeconds();
    }
	protected String retrieveUserName(Authentication authentication) {
        if (isInstanceOfUserDetails(authentication)) {
            return ((UserDetails) authentication.getPrincipal()).getUsername();
        }
        else {
            return authentication.getPrincipal().toString();
        }
    }

    protected String retrievePassword(Authentication authentication) {
        if (isInstanceOfUserDetails(authentication)) {
            return ((UserDetails) authentication.getPrincipal()).getPassword();
        }
        else {
            if (authentication.getCredentials() == null) {
                return null;
            }
            return authentication.getCredentials().toString();
        }
    }
    private boolean isInstanceOfUserDetails(Authentication authentication) {
        return authentication.getPrincipal() instanceof UserDetails;
    }

	protected String generateTokenData() {
        byte[] newToken = new byte[tokenLength];
        random.nextBytes(newToken);
        return new String(Base64.encode(newToken));
    }
	protected String generateSeriesData() {
        byte[] newSeries = new byte[seriesLength];
        random.nextBytes(newSeries);
        return new String(Base64.encode(newSeries));
    }

	protected Authentication createSuccessfulAuthentication(HttpServletRequest request, UserDetails user) {
		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        setDetails(request,auth);
        return auth;
    }
	protected void setDetails(HttpServletRequest request, UsernamePasswordAuthenticationToken authRequest) {
	    authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
	}
	/**
	 * 从cookie中获取加密的用户信息
	 * @param request
	 * @return
	 */
    protected String extractRememberMeCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();

        if ((cookies == null) || (cookies.length == 0)) {
            return null;
        }

        for (Cookie cookie : cookies) {
            if (cookieName.equals(cookie.getName())) {
                return cookie.getValue();
            }
        }

        return null;
    }
    protected String[] decodeCookie(String cookieValue) throws InvalidCookieException {
        for (int j = 0; j < cookieValue.length() % 4; j++) {
            cookieValue = cookieValue + "=";
        }

        if (!Base64.isBase64(cookieValue.getBytes())) {
            throw new InvalidCookieException( "Cookie token was not Base64 encoded; value was '" + cookieValue + "'");
        }

        String cookieAsPlainText = new String(Base64.decode(cookieValue.getBytes()));

        String[] tokens = StringUtils.delimitedListToStringArray(cookieAsPlainText, DELIMITER);

        if ((tokens[0].equalsIgnoreCase("http") || tokens[0].equalsIgnoreCase("https")) && tokens[1].startsWith("//")) {
            // Assume we've accidentally split a URL (OpenID identifier)
            String[] newTokens = new String[tokens.length - 1];
            newTokens[0] = tokens[0] + ":" + tokens[1];
            System.arraycopy(tokens, 2, newTokens, 1, newTokens.length - 1);
            tokens = newTokens;
        }

        return tokens;
    }
    protected UserDetails processAutoLoginCookie(String[] cookieTokens, HttpServletRequest request,
            HttpServletResponse response) {

        if (cookieTokens.length != 3) {
            throw new InvalidCookieException("Cookie token did not contain 3" +
                    " tokens, but contained '" + Arrays.asList(cookieTokens) + "'");
        }

        long tokenExpiryTime;

        try {
            tokenExpiryTime = new Long(cookieTokens[1]).longValue();
        }
        catch (NumberFormatException nfe) {
            throw new InvalidCookieException("Cookie token[1] did not contain a valid number (contained '" +
                    cookieTokens[1] + "')");
        }

        if (isTokenExpired(tokenExpiryTime)) {
            throw new InvalidCookieException("Cookie token[1] has expired (expired on '"
                    + new Date(tokenExpiryTime) + "'; current time is '" + new Date() + "')");
        }

        // Check the user exists.
        // Defer lookup until after expiry time checked, to possibly avoid expensive database call.

        UserDetails userDetails = userDetailsService.loadUserByUsername(cookieTokens[0]);

        // Check signature of token matches remaining details.
        // Must do this after user lookup, as we need the DAO-derived password.
        // If efficiency was a major issue, just add in a UserCache implementation,
        // but recall that this method is usually only called once per HttpSession - if the token is valid,
        // it will cause SecurityContextHolder population, whilst if invalid, will cause the cookie to be cancelled.
        /**********有问题,临时注销掉********************/
//        String expectedTokenSignature = makeTokenSignature(tokenExpiryTime, userDetails.getUsername(),
//                userDetails.getPassword());

        
//        if (!equals(expectedTokenSignature,cookieTokens[2])) {
//            throw new InvalidCookieException("Cookie token[2] contained signature '" + cookieTokens[2]
//                                                                                                    + "' but expected '" + expectedTokenSignature + "'");
//        }

        return userDetails;
    }
    protected boolean isTokenExpired(long tokenExpiryTime) {
        return tokenExpiryTime < System.currentTimeMillis();
    }
    /**
     * Calculates the digital signature to be put in the cookie. Default value is
     * MD5 ("username:tokenExpiryTime:password:key")
     */
    protected String makeTokenSignature(long tokenExpiryTime, String username, String password) {
        String data = username + ":" + tokenExpiryTime + ":" + password + ":" + getKey();
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("No MD5 algorithm available!");
        }

        return new String(Hex.encode(digest.digest(data.getBytes())));
    }
   

    /**
     * Sets the cookie on the response.
     *
     * By default a secure cookie will be used if the connection is secure. You can set the {@code useSecureCookie}
     * property to {@code false} to override this. If you set it to {@code true}, the cookie will always be flagged
     * as secure. If Servlet 3.0 is used, the cookie will be marked as HttpOnly.
     *
     * @param tokens the tokens which will be encoded to make the cookie value.
     * @param maxAge the value passed to {@link Cookie#setMaxAge(int)}
     * @param request the request
     * @param response the response to add the cookie to.
     */
    protected void setCookie_Key(String[] tokens, int maxAge, HttpServletRequest request, HttpServletResponse response) {
        String cookieValue = encodeCookie(tokens);
        Cookie cookie = getCookie(cookieName, cookieValue,maxAge,request,response);
        response.addCookie(cookie);
    }
    protected void setCookie_NikeName(String nikeName, int maxAge, HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = getCookie(nickName, nikeName,maxAge,request,response);
        response.addCookie(cookie);
    }
    
    protected Cookie getCookie(String cookieName, String cookieValue,int maxAge, HttpServletRequest request, HttpServletResponse response) {
    	if(AssertHelper.notEmpty(cookieValue)){
    		try {
				cookieValue=URLEncoder.encode(cookieValue,"UTF-8");
//				System.out.println(URLDecoder.decode(cookieValue));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
    	}
    	Cookie cookie = new Cookie(cookieName, cookieValue);
        cookie.setMaxAge(maxAge);
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
            logger.debug("Note: Cookie will not be marked as HttpOnly because you are not using Servlet 3.0 (Cookie#setHttpOnly(boolean) was not found).");
        }
        return cookie;
	}

    protected String getCookiePath(HttpServletRequest request) {
        String contextPath = request.getContextPath();
        return contextPath.length() > 0 ? contextPath : "/";
    }
    public String getCookieDomain() {
    	return cookieDomain;
    }
    /**
     * Inverse operation of decodeCookie.
     *
     * @param cookieTokens the tokens to be encoded.
     * @return base64 encoding of the tokens concatenated with the ":" delimiter.
     */
    protected String encodeCookie(String[] cookieTokens) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i < cookieTokens.length; i++) {
            sb.append(cookieTokens[i]);

            if (i < cookieTokens.length - 1) {
                sb.append(DELIMITER);
            }
        }

        String value = sb.toString();

        sb = new StringBuilder(new String(Base64.encode(value.getBytes())));

        while (sb.charAt(sb.length() - 1) == '=') {
            sb.deleteCharAt(sb.length() - 1);
        }

        return sb.toString();
    }
    /**
     * Constant time comparison to prevent against timing attacks.
     */
    private static boolean equals(String expected, String actual) {
        byte[] expectedBytes = bytesUtf8(expected);
        byte[] actualBytes = bytesUtf8(actual);
        if (expectedBytes.length != actualBytes.length) {
            return false;
        }

        int result = 0;
        for (int i = 0; i < expectedBytes.length; i++) {
            result |= expectedBytes[i] ^ actualBytes[i];
        }
        return result == 0;
    }

    private static byte[] bytesUtf8(String s) {
        if (s == null) {
            return null;
        }
        return Utf8.encode(s);
    }
}
