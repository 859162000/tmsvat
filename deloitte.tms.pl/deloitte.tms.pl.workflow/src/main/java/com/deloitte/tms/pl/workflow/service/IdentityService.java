package com.deloitte.tms.pl.workflow.service;

import java.util.Collection;

import com.deloitte.tms.pl.workflow.process.assign.Entity;
import com.deloitte.tms.pl.workflow.process.assign.PageQuery;

/**
 * @author Jacky.gao
 * @since 2013年8月12日
 */
public interface IdentityService {
	public static final String USER_TYPE="user";
	public static final String DEPT_TYPE="udept";
	public static final String POSITION_TYPE="position";
	public static final String GROUP_TYPE="group";
	public static final String DEPT_POSITION_TYPE="dept-position";
	
	public static final String BEAN_ID="uflo.identityService";
	void userPageQuery(PageQuery<Entity> query);
	void deptPageQuery(PageQuery<Entity> query,String parentId);
	void positionPageQuery(PageQuery<Entity> query,String parentId);
	void groupPageQuery(PageQuery<Entity> query,String parentId);
	
	Collection<String> getUsersByGroup(String group);
	Collection<String> getUsersByPosition(String position);
	Collection<String> getUsersByDept(String dept);
	Collection<String> getUsersByDeptAndPosition(String dept,String position);
}
