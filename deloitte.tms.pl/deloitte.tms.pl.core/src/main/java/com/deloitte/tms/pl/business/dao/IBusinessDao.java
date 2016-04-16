package com.deloitte.tms.pl.business.dao;

import java.util.List;

import com.deloitte.tms.pl.business.model.Business;
import com.deloitte.tms.pl.core.dao.IDao;

public interface IBusinessDao extends IDao {

	/**
	 * 通过类型查找
	 * @param type
	 * @return
	 */
	public List<Business> findEnabledBusinesssByType(String type);
	/**
	 * 通过名称查找
	 * @param lable
	 * @return
	 */
	public List<Business> findEnabledBusinesssByLable(String lable);
	/**
	 * 通过编码查找
	 * @param code
	 * @return
	 */
	public Business loadBusinessByCode(String code);
	/**
	 * 查找下级业务
	 * @param parentId
	 * @return
	 */
	public List<Business> findSubBusinesss(Long parentId);
}
