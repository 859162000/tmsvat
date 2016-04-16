package com.deloitte.tms.pl.business.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.business.dao.IBusinessDao;
import com.deloitte.tms.pl.business.model.Business;
import com.deloitte.tms.pl.core.dao.impl.BaseDao;
@Component("businessDao")
public class BusinessDaoImpl extends BaseDao implements IBusinessDao {

	@SuppressWarnings("unchecked")
	public List<Business> findEnabledBusinesssByType(String type) {
		StringBuffer query = new StringBuffer();
		query.append("select business from Business business where business.enabled = :enabled");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("enabled", Boolean.TRUE);
		
		if (type != null) {
			query.append(" and business.type = :type");
			params.put("type", type);
		}
		return findBy(query, params);
	}

	@Override
	public List<Business> findEnabledBusinesssByLable(String label) {
		StringBuffer query = new StringBuffer();
		query.append("select business from Business business where business.enabled = :enabled");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("enabled", Boolean.TRUE);
		
		if (label != null) {
			query.append(" and business.label = :label");
			params.put("label", label);
		}
		return findBy(query, params);
	}

	@Override
	public Business loadBusinessByCode(String code) {
		StringBuffer query = new StringBuffer();
		query.append("select business from Business business where business.enabled = :enabled");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("enabled", Boolean.TRUE);
		
		if (code != null) {
			query.append(" and business.code = :code");
			params.put("code", code);
		}
		return (Business) getFirstRecord(findBy(query, params));
	}

	@Override
	public List<Business> findSubBusinesss(Long parentId) {
		StringBuffer query = new StringBuffer();
		query.append("select business from Business business where business.enabled = :enabled");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("enabled", Boolean.TRUE);
		
		if (parentId != null) {
			query.append(" and business.parent.id = :parentId");
			params.put("parentId", parentId);
		}
		return findBy(query, params);
	}
}
