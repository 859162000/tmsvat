package com.deloitte.tms.samplecenter.core.context;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.deloitte.tms.pl.core.context.BaseContextHolder;
import com.deloitte.tms.pl.core.context.IContextHolder;
import com.deloitte.tms.pl.security.model.SecurityUser;
@Service(IContextHolder.BEAN_ID)
public class SampleCenterContextHolder extends BaseContextHolder{
	@Override
	public void initUserDetail(SecurityUser user,HttpServletRequest request,HttpServletResponse response) {
		super.initUserDetail(user, request, response);
	}
}
