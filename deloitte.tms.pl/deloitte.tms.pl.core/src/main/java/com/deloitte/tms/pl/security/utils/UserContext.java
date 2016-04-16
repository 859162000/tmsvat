package com.deloitte.tms.pl.security.utils;

import org.springframework.security.core.context.SecurityContextHolder;

import com.deloitte.tms.pl.core.commons.exception.BusinessException;
import com.deloitte.tms.pl.security.model.SecurityUser;

public class UserContext {

	  public static SecurityUser getCurrentUser() {
		  try {
			if("roleAnonymous".equals(SecurityContextHolder.getContext().getAuthentication().getPrincipal())) {
				  return null;
			  }
			  return (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			throw new BusinessException("请先登录");
		}  
	  }
	  
}
