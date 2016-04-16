package com.deloitte.tms.pl.security.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.dao.IDao;
import com.deloitte.tms.pl.core.service.impl.BaseService;
import com.deloitte.tms.pl.security.dao.IGroupDao;
import com.deloitte.tms.pl.security.model.SecurityGroup;
import com.deloitte.tms.pl.security.model.impl.GroupMember;
import com.deloitte.tms.pl.security.service.IGroupService;

/**
 * @since 2013-1-30
 * @author Jacky.gao
 */
@Component(IGroupService.BEAN_ID)
public class DefaultGroupService extends BaseService implements IGroupService {
	@Resource
	IGroupDao groupDao;
	public List<SecurityGroup> loadUserGroups(String username) {
		return groupDao.loadUserGroups(username);
	}
	@Override
	public IDao getDao() {
		return groupDao;
	}
	@Override
	public DaoPage loadGroups(Map params, Integer pageIndex, Integer pageSize) {
		return groupDao.loadGroups(params, pageIndex, pageSize);
	}
	@Override
	public List<GroupMember> loadGroupMembers(Map params) {
		return groupDao.loadGroupMembers(params);
	}
	public DaoPage loadGroupMembers(Map params, Integer pageIndex, Integer pageSize) {
		return groupDao.loadGroupMembers(params,pageIndex,pageSize);
	}
}
