package com.deloitte.tms.pl.security.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.dao.IDao;
import com.deloitte.tms.pl.core.service.impl.BaseService;
import com.deloitte.tms.pl.security.dao.IPositionDao;
import com.deloitte.tms.pl.security.model.SecurityPosition;
import com.deloitte.tms.pl.security.model.impl.DefaultPosition;
import com.deloitte.tms.pl.security.service.IPositionService;

@Component(IPositionService.BEAN_ID)
public class DefaultPositionService extends BaseService implements IPositionService {
	@Resource
	IPositionDao positionDao;
	public SecurityPosition newPositionInstance(String positionId) {
		return new DefaultPosition(positionId);
	}
	public List<SecurityPosition> loadUserPositions(String username) {
		return positionDao.loadUserPositions(username);
	}
	public SecurityPosition loadPositionById(String positionId) {
		return positionDao.loadPositionById(positionId);
	}
	public DaoPage loadPagePositions(Map params, int pageIndex,int pageSize)
	{
		return positionDao.loadPagePositions(params, pageIndex, pageSize);
	}
	public List<SecurityPosition> loadPagePositions(Map params)
	{
		return positionDao.loadPagePositions(params);
	}
	@Override
	public IDao getDao() {
		return positionDao;
	}
	@Override
	public String uniqueCheck(String id) {
		return positionDao.uniqueCheck(id);
	}
}
