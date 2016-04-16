package com.deloitte.tms.pl.core.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.deloitte.tms.pl.core.dao.IDao;
import com.deloitte.tms.pl.core.dao.impl.BaseDao;
@Service(SimpleService.BEAN_ID)
public class SimpleService extends BaseService{
	public static final String BEAN_ID="baseService";
	
	@Resource
	BaseDao baseDao;
	@Override
	public IDao getDao() {
		return baseDao;
	}

}
