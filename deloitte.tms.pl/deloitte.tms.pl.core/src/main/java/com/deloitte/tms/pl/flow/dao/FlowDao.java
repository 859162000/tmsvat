package com.deloitte.tms.pl.flow.dao;

import java.util.List;
import java.util.Map;

import com.deloitte.tms.pl.core.commons.exception.BusinessException;
import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.dao.IDao;
import com.deloitte.tms.pl.flow.model.Flow;

/**
 * 
 * @author daoTemplate
 */
public interface FlowDao extends IDao {
	
	public static final String BEAN_ID="flowDao";

	public Flow getFlowByOrgId(String orgId, String flowType) throws BusinessException;

	public DaoPage getFlowByOrgId(String orgId, int index, int size);

	public List getFlowByOrgId(String orgId);

	public List getFlowByOrgId_Test(String orgId, String flowType, String chatype);

	public DaoPage findFlowByDivAndChatype(String orgId, String chaType, int pageIndex, int pageSize);
	
	public DaoPage findFlowByParams(Map params, Integer pageIndex,Integer pageSize);
	
	public List findFlowByParams(Map params);
}