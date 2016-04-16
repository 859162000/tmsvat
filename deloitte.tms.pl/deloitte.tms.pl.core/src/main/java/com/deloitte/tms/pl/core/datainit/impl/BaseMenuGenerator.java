package com.deloitte.tms.pl.core.datainit.impl;

import java.util.UUID;

import com.deloitte.tms.pl.core.datainit.MenuGenerator;
import com.deloitte.tms.pl.security.model.impl.DefaultUrl;

public abstract class BaseMenuGenerator implements MenuGenerator{
	protected DefaultUrl createUrl(String name,String icon,String parentId,int order,String targetUrl,String companyId){
		DefaultUrl url=new DefaultUrl();
		url.setId(UUID.randomUUID().toString());
		url.setName(name);
		url.setIcon(icon);
		url.setUrl(targetUrl);
		url.setParentId(parentId);
		url.setCompanyId(companyId);
		url.setForNavigation(true);
		url.setOrder(order);
		return url;
	}
}
