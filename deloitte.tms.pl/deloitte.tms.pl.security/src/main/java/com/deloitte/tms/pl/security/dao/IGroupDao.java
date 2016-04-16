package com.deloitte.tms.pl.security.dao;

import java.util.List;
import java.util.Map;

import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.dao.IDao;
import com.deloitte.tms.pl.security.model.SecurityGroup;
import com.deloitte.tms.pl.security.model.impl.GroupMember;

/**
 * @since 2013-1-29
 * @author Jacky.gao
 */
public interface IGroupDao extends IDao {
	public static final String BEAN_ID="ling2.groupDao";
	List<SecurityGroup> loadUserGroups(String username);
	DaoPage loadGroups(Map params,Integer pageIndex,Integer pageSize);
	public DaoPage loadGroupMembers(Map params, Integer pageIndex,Integer pageSize);
	public List<GroupMember> loadGroupMembers(Map params);
}