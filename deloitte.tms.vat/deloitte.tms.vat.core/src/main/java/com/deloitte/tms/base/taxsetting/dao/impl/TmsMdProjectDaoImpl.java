package com.deloitte.tms.base.taxsetting.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.deloitte.tms.base.taxsetting.dao.TmsMdProjectDao;
import com.deloitte.tms.base.taxsetting.model.TmsMdProject;
import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.dao.impl.BaseDao;

@Component(TmsMdProjectDao.BEAN_ID)
public class TmsMdProjectDaoImpl extends BaseDao<TmsMdProject> implements TmsMdProjectDao{
	
	@Override
	public DaoPage findTmsMdProjectByParams(Map params, Integer pageIndex,
			Integer pageSize) {
		StringBuffer query=new StringBuffer();
		Map values=new HashMap();
		buildTmsDBContractQuery(query, values, params);
		return pageBy(query, values, pageIndex, pageSize);
	}

	@Override
	public List<TmsMdProject> findTmsMdProjectByParams(Map params) {
		StringBuffer query=new StringBuffer();
		Map values=new HashMap();
		buildTmsDBContractQuery(query, values, params);
		return findBy(query, values);		
	}
	
	private void buildTmsDBContractQuery(StringBuffer query,Map values,Map params) {
		query.append(" from TmsMdProject where 1=1");
		
		Object contract_id = params.get("contract_id");//合同id
		if (contract_id != null && !"".equals(contract_id)) {
			query.append(" and contract_id=:contract_id");
			values.put("contract_id", contract_id);
		}
		
	}

	@Override
	public void removeTmsMdProjects(String projectId) {
		StringBuffer query=new StringBuffer();
		Map values=new HashMap<String, Object>();
		query.append("update TmsMdProject set DELETED_FLAG = 0 where id =:contractId");
		values.put("projectId", projectId);
		executeHqlProduce(query.toString(), values);
	}
}
