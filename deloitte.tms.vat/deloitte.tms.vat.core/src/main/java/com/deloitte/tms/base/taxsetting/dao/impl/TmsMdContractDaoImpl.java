package com.deloitte.tms.base.taxsetting.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.deloitte.tms.base.taxsetting.dao.TmsMdContractDao;
import com.deloitte.tms.base.taxsetting.model.TmsMdContract;
import com.deloitte.tms.base.taxsetting.model.TmsMdFlexValueSets;
import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.dao.impl.BaseDao;

@Component(TmsMdContractDao.BEAN_ID)
public class TmsMdContractDaoImpl extends BaseDao<TmsMdContract> implements TmsMdContractDao{

	public DaoPage findTmsMdContractByParams(Map params, Integer pageIndex,Integer pageSize)
	{
		StringBuffer query=new StringBuffer();
		Map values=new HashMap();
		buildTmsDBContractQuery(query, values, params);
		return pageBy(query, values, pageIndex, pageSize);
	}
	
	public List<TmsMdContract> findTmsMdContractByParams(Map params)
	{		
		StringBuffer query=new StringBuffer();
		Map values=new HashMap();
		buildTmsDBContractQuery(query, values, params);
		return findBy(query, values);		
	}
	
	private void buildTmsDBContractQuery(StringBuffer query,Map values,Map params) {
		query.append(" from TmsMdContract where 1=1");
		Object value=params.get("status");
		if(value!=null){
			query.append(" and status=:status");
			values.put("status", value);
		}
		
		Object contractName = params.get("contractName");//合同名称
		if (contractName != null && !"".equals(contractName)){
			query.append(" and contract_name=:contractName");
			values.put("contractName", contractName);
		}
		Object contractNumber = params.get("contractNumber");//合同号码
		if (contractNumber != null && !"".equals(contractNumber)){
			query.append(" and contract_number=:contractNumber");
			values.put("contractNumber", contractNumber);
		}
		Object startDate = params.get("startDate");//开始日期
		if(startDate !=null && !"".equals(startDate)){		
			query.append(" and startDate like :startDate");	
			values.put("startDate","%"+startDate+"%");		
		}
		Object endDate = params.get("endDate");//结束日期
		if (endDate != null && !"".equals(endDate)){
			query.append(" and endDate like :endDate");	
			values.put("endDate","%"+endDate+"%");	
		}
	}

	
	@Override
	public TmsMdContract getContractById(String id) {
		StringBuffer query=new StringBuffer();
		Map values=new HashMap<String, Object>();
		query.append("from TmsMdContract c WHERE c.id=:id");
		values.put("id", id);
		List<TmsMdContract> contracts=findBy(query, values);
		if(contracts.size()==0){
			return null;		
		}else{
			TmsMdContract contract=(TmsMdContract)contracts.get(0);
			return contract;
		}
	}

	@Override
	public void removeTmsMdContractById(String contractId) {
		//this.removeById(TmsMdContract.class, contractId);
		StringBuffer query=new StringBuffer();
		Map values=new HashMap<String, Object>();
		query.append("delete from TmsMdContract where id =:contractId ");
		values.put("contractId", contractId);
		executeHqlProduce(query.toString(), values);
	}
}
