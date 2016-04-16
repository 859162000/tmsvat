package com.deloitte.tms.pl.flow.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.deloitte.tms.pl.core.commons.exception.BusinessException;
import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.service.IService;
import com.deloitte.tms.pl.flow.model.Flow;

public interface FlowService extends IService{
	public static final String BEAN_ID="flowService";

	/**
	 * 批量保存(含修改、新增、删除)
	 * 
	 * @param records
	 */
	public void saveFlows(Collection<Flow> flows);

	/**
	 * 根据机构部门简称和流水号类型获取号码流水
	 * 
	 * @param records
	 */
	public Flow getFlowByPreName(String preName, String flowType);

	/**
	 * 
	 * @param orgId
	 * @param flowType
	 * @return
	 */
	public Flow getFlowByOrgId(String orgId, String flowType);

	/**
	 * 
	 * 根据 流水号类型 流水号类型、渠道类型、组织机构编码联合确定的流水号记录的唯一性
	 * 
	 * @param entity
	 * @param propertyNames
	 * @return
	 */
	public boolean validateObj(Flow obj) throws BusinessException;


	public DaoPage getFlowByOrgId(String orgId, int index, int size);

	public List getFlowByOrgId(String orgId);

	/**
	 * 用与FlowModuleTest gulin | 2009-6-14
	 * */
	public Flow getFlowByOrgId_Test(String orgcode, String flowType,
			String chatype);

	public DaoPage findFlowByDivAndChatype(String orgId, String chaType,
			int pageIndex, int pageSize);

	public void saveReplaceFlows(Collection<Flow> records);

	public String generateNextFlowCode(Flow flow);

	public DaoPage findFlowByParams(Map params, Integer pageIndex,Integer pageSize);
	
	public List findFlowByParams(Map params);
	
	public void saveFlowDataListsMap(Map dataListsMap);
}
