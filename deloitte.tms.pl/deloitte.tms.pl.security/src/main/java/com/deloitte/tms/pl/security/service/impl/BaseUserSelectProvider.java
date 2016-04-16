package com.deloitte.tms.pl.security.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.deloitte.tms.pl.core.commons.utils.ViewUtils;
import com.deloitte.tms.pl.core.commons.utils.reflect.ReflectUtils;
import com.deloitte.tms.pl.security.model.SecurityUser;
import com.deloitte.tms.pl.security.model.UserForSelect;


public abstract class BaseUserSelectProvider{
	protected String getRelationType(Map params){
		return ViewUtils.getParamValue("relationType", params);
	}
	protected String getRelationId(Map params){
		return ViewUtils.getParamValue("relationId", params);
	}
	protected String getSelectedids(Map params){
		return ViewUtils.getParamValue("selectedids", params);
	}
	protected List<UserForSelect> convertUserForSelect(Collection<SecurityUser> users) {
		List<UserForSelect> results=new ArrayList<UserForSelect>();
		for(SecurityUser defaultUser:users){
			UserForSelect userForSelect=new UserForSelect();
			ReflectUtils.copyProperties(defaultUser, userForSelect);
			results.add(userForSelect);
		}
		return results;
	}
}
