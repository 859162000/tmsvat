package com.deloitte.tms.pl.flow.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.core.commons.exception.BusinessException;
import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.core.dao.impl.BaseDao;
import com.deloitte.tms.pl.flow.dao.FlowDao;
import com.deloitte.tms.pl.flow.model.Flow;

/**
 * 
 * @author hibernateDaoTemplate
 */
@Component(FlowDao.BEAN_ID)
public class FlowDaoImpl extends BaseDao implements FlowDao {

	protected Class getPersistentClass() {
		return Flow.class;
	}
	/**
	 * 查询机构下的流水号
	 * 查询全局流水号，orgid为空
	 * flowType 流水号类别
	 */
	public synchronized Flow getFlowByOrgId(String orgId, String flowType) {
		Map filterMap = new HashMap();
		if(AssertHelper.notEmpty(orgId)){
			filterMap.put("orgId", orgId);
		}
		AssertHelper.notEmpty_assert(flowType,"流水号类别不能为空");
		filterMap.put("flowType", flowType);
		List<Flow> flows=findByCriteria(filterMap);
		if(flows.size()>1){
			throw new BusinessException("flowType:"+flowType+"不唯一");
		}else if (flows.size()==1) {
			return flows.get(0);
		}else return null;
	}

	public DaoPage getFlowByOrgId(String orgId, int index, int size) {
		StringBuffer queryString = new StringBuffer();
		List param = new ArrayList();
		List values = new ArrayList();
		if (orgId == null || orgId.equals("")) {
			return null;
		} else {
			queryString.append("select flow ");
			queryString.append("from Flow flow where 1=1 ");
			queryString.append(" and flow.orgId =:orgId");
			param.add("orgId");
			values.add(orgId);
			queryString.append(" and flow.flowType in ('0','1')");
			queryString.append(" order by flow.orgId ");

			return (pagedQueryByNamedParam(queryString.toString(),
					(String[]) param.toArray(new String[0]), values.toArray(),
					index, size));
		}
	}

	public List getFlowByOrgId(String orgId) {
		StringBuffer queryString = new StringBuffer();
		List param = new ArrayList();
		List values = new ArrayList();
		if (orgId == null || orgId.equals("")) {
			return null;
		} else {
			queryString.append("select flow ");
			queryString.append("from Flow flow where 1=1 ");
			queryString.append(" and flow.orgId =:orgId");
			param.add("orgId");
			values.add(orgId);
			queryString.append(" order by flow.orgId ");
			return (findByNamedParam(queryString.toString(),
					(String[]) param.toArray(new String[0]), values.toArray()));
		}
	}

	/**
	 * 用于FlowModuleTest kaka.gulin 2009-6-14
	 */
	public synchronized List getFlowByOrgId_Test(String orgId,
			String flowType, String chatype) {
		Map filterMap = new HashMap();
		filterMap.put("orgId", orgId);
		filterMap.put("flowType", flowType);
		filterMap.put("chaType", chatype);
		return this.findByCriteria(filterMap);
	}

	public DaoPage findFlowByDivAndChatype(String orgId, String chaType,
			int pageIndex, int pageSize) {
		StringBuffer sb = new StringBuffer();
		sb.append("from Flow flow where 1=1 ");
		if (null != orgId && !orgId.equals("")) {
			sb.append(" and flow.orgId ='");
			sb.append(orgId);
			sb.append("'");
		}
		if (null != chaType && !chaType.equals("0")) {
			sb.append(" and flow.chaType ='");
			sb.append(chaType);
			sb.append("'");
		}
		sb.append(" order by flow.orgId ");
		return pageBy(sb, pageIndex, pageSize);
	}
	public DaoPage findFlowByParams(Map params, Integer pageIndex,Integer pageSize)
	{
		StringBuffer query=new StringBuffer();
		Map values=new HashMap();
		query.append(" from Flow where 1=1 ");
		Object value=params.get("policyNo");
		if(value!=null&&!"".equals(value))
		{
			query.append(" and policyNo=:policyNo");
			values.put("policyNo", value);
		}
		value=params.get("status");
		if(value!=null)
		{
			query.append(" and status=:status");
			values.put("status", value);
		}
		return pageBy(query, values, pageIndex, pageSize);
	}
	public List findFlowByParams(Map params)
	{
		StringBuffer query=new StringBuffer();
		Map values=new HashMap();
		query.append(" from Flow where 1=1 ");
		Object value=params.get("status");
		if(value!=null)
		{
			query.append(" and status=:status");
			values.put("status", value);
		}
		return findBy(query, values);
	}
}