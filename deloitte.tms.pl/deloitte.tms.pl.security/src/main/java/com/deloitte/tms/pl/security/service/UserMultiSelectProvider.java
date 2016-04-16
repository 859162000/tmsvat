package com.deloitte.tms.pl.security.service;

import java.util.List;
import java.util.Map;

import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.security.model.UserForSelect;

public interface UserMultiSelectProvider {
	public static String DEFAULT_BEAN_ID="defaultMultiSelectProvider";
	/**
	 * 获取所有待选的用户分页数据
	 * @param pageIndex
	 * @param pageSize
	 * @param params
	 * @return
	 */
	public DaoPage getUserForMultiSelect(Integer pageIndex,Integer pageSize,Map params);
	/**
	 * 获取已经选择的用户数据
	 * @param params
	 * @return
	 */
	public List<UserForSelect> getUserSelected(Map params);
}
