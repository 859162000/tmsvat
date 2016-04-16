package com.deloitte.tms.base.cache.service;

import com.deloitte.tms.base.cache.model.OrgNode;
import com.deloitte.tms.pl.core.commons.exception.BusinessException;

public interface CacheProvider {
	/**
	 * 构建业务单位
	 * 
	 * @param divisionId
	 * @param date
	 * @return
	 * @throws BusinessException
	 * @author dada
	 */
	public OrgNode build(CacheBusinessContext context) throws BusinessException;
}
