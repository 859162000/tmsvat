package com.deloitte.tms.pl.core.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.deloitte.tms.pl.core.dao.IDao;
import com.deloitte.tms.pl.core.service.OnlineService;

/**
 * 
 * @author 孙宇
 * 
 */
@Service(OnlineService.BEAN_ID)
public class OnlineServiceImpl extends BaseService implements OnlineService {
	@Resource
	IDao baseDao;
	@Override
	public IDao getDao() {
		return baseDao;
	}
}
