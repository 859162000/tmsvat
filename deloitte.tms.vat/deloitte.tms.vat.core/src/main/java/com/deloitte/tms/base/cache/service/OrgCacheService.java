package com.deloitte.tms.base.cache.service;

import java.util.List;

import com.deloitte.tms.pl.security.model.SecurityPosition;
import com.deloitte.tms.pl.security.model.SecurityRole;
import com.deloitte.tms.pl.security.model.impl.DefaultDeptInParam;

public interface OrgCacheService {
	public static final String BEAN_ID = "ling2.securityApplicationCache";
	public static final String DEPT_CACHE_ID = "ling2.DEPT_CACHE_ID";
	public static final String ROLE_CACHE_ID = "ling2.ROLE_CACHE_ID";
	public static final String POSITION_CACHE_ID = "ling2.POSITION_CACHE_ID";
	/**
	 * 获取机构树缓存
	 * @return
	 */
	public List<DefaultDeptInParam> getDeptCacheTree();
	/**
	 * 获取生效的机构树缓存
	 * @return
	 */
	public List<DefaultDeptInParam> getEffectDeptCacheTree();
	/**
	 * 通过部门id从缓存树中获取业务单位
	 */
	public DefaultDeptInParam getDeptById(String deptId);
	/**
	 * 刷新业务单位的缓存树
	 */
	public void flushDeptCacheTree();
	/**
	 * 获取角色树缓存
	 * @return
	 */
	public List<SecurityRole> getRoleCacheTree();
	/**
	 * 获取岗位树缓存
	 * @return
	 */
	public List<SecurityPosition> getPositionCacheTree();

}
