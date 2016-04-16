package com.deloitte.tms.pl.security.service.impl;

import java.io.IOException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.AuthenticationException;

import com.deloitte.tms.pl.core.commons.exception.BusinessException;
import com.deloitte.tms.pl.core.commons.utils.PropertiesUtils;
import com.deloitte.tms.pl.core.context.ContextHolder;
import com.deloitte.tms.pl.security.model.SecurityUser;
import com.deloitte.tms.pl.security.model.impl.DefaultUser;
import com.deloitte.tms.pl.security.service.IDeptService;
import com.deloitte.tms.pl.security.service.IFrameworkService;
import com.deloitte.tms.pl.security.service.IGroupService;
import com.deloitte.tms.pl.security.service.IPositionService;
import com.google.code.kaptcha.Constants;

public class DefaultFrameworkService implements IFrameworkService {
	public static final String BEAN_ID="ling2.frameworkService";
	private IDeptService deptService;
	private IPositionService positionService;
	private IGroupService groupService;
	private PasswordEncoder passwordEncoder;
	
	boolean isEncode=true;
	
	public void authenticate(SecurityUser user,UsernamePasswordAuthenticationToken authentication,Boolean isvalidatePassword)
			throws AuthenticationException {
		
		this.preChecks(authentication);
		DefaultUser defaultUser=(DefaultUser)user;
		String presentedPassword;
		
		if(isEncode){
			String pas = authentication.getCredentials().toString();
			Base64 decoder = new Base64();			
			try {
				presentedPassword = new String(decoder.decode(pas));
			} catch (Exception e) {
				throw new BusinessException("用户输入密码解析失败");
			}			
		}else {
			presentedPassword = authentication.getCredentials().toString();
		}
		
        if (isvalidatePassword&&!passwordEncoder.isPasswordValid(user.getPassword(), presentedPassword,defaultUser.getSalt())) {
            throw new BadCredentialsException("密码不正确");
        }else{
//        	defaultUser.setDepts(deptService.loadUserDepts(user.getUsername()));
//        	defaultUser.setPositions(positionService.loadUserPositions(user.getUsername()));
//        	defaultUser.setGroups(groupService.loadUserGroups(user.getUsername()));
        }
	}
	private void preChecks(UsernamePasswordAuthenticationToken authentication)throws AuthenticationException{
		boolean useCaptcha=PropertiesUtils.getBoolean("ling2.useCaptchaForLogin");
		if(useCaptcha){
			String key=ContextHolder.getRequest().getParameter("captcha_");
			if(StringUtils.isNotEmpty(key)){
				String sessionkey=(String)ContextHolder.getHttpSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
				if(sessionkey==null){
					throw new BadCredentialsException("验证码过期");
				}else if(!sessionkey.equals(key)){
					throw new BadCredentialsException("验证码不正确");					
				}
			}else{
				throw new BadCredentialsException("验证码不能为空");					
			}
		}
		if (authentication.getPrincipal() == null) {
			throw new BadCredentialsException("Username can not be null");
		}
		if (authentication.getCredentials() == null) {
			throw new BadCredentialsException("password can not be null");
		}
	}
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
	public void setDeptService(IDeptService deptService) {
		this.deptService = deptService;
	}
	public void setPositionService(IPositionService positionService) {
		this.positionService = positionService;
	}
	public void setGroupService(IGroupService groupService) {
		this.groupService = groupService;
	}
}
