package com.deloitte.tms.pl.security.service;

import java.util.Map;

import com.deloitte.tms.pl.core.commons.support.DaoPage;

public interface UserSingleSelectProvider {
	public static String DEFAULT_BEAN_ID="defaultUserSingleSelectProvider";
	/**
	 * 获取所有可供选择的用户数据
	 * @param pageIndex
	 * @param pageSize
	 * @param params
	 * @return
	 */
	public DaoPage getUserForSingleSelect(Integer pageIndex,Integer pageSize,Map params);
}
