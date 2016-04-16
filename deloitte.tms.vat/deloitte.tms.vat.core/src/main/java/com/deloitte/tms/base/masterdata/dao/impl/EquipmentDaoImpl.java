package com.deloitte.tms.base.masterdata.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.deloitte.tms.base.masterdata.dao.EquipmentDao;
import com.deloitte.tms.base.masterdata.model.TmsMdEquipment;
import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.core.dao.impl.BaseDao;

@Component(EquipmentDao.BEAN_ID)
public class EquipmentDaoImpl extends BaseDao<TmsMdEquipment> implements EquipmentDao{
	
	public DaoPage findEquipmentByParams(Map params, Integer pageIndex,Integer pageSize)
	{
		StringBuffer query=new StringBuffer();
		Map values=new HashMap();
		buildEquipmentQuery(query, values, params);
		return pageBy(query, values, pageIndex, pageSize);
	}
	
	private void buildEquipmentQuery(StringBuffer query,Map values,Map params) {
		query.append(" from TmsMdEquipment where 1=1 and  flag = 1 ");
		
		Object value=params.get("id");
		if(!AssertHelper.empty(value))
		{
			query.append(" and id=:ID");
			values.put("ID", value);
		}
		value=params.get("getparent");
		if(!AssertHelper.empty(value))
		{
			query.append(" and parentEquipmentId is null");
		}
	}
	
	public List<TmsMdEquipment> loadEquipmentData(Map params)
	{
		StringBuffer query=new StringBuffer();
		Map values=new HashMap();
		query.append(" from TmsMdEquipment where 1=1 and  flag = 1 ");
		Object value=params.get("enabledFlag");
		if(value!=null)
		{
			query.append(" and enabledFlag=:enabledFlag");
			values.put("enabledFlag", value);
		}
		return findBy(query, values);
	}
	
	public DaoPage findLegalEntityByParams(Map params, Integer pageIndex,Integer pageSize)
	{
		StringBuffer query=new StringBuffer();
		Map values=new HashMap();
		buildLegalEntityQuery(query, values, params);
		return pageBy(query, values, pageIndex, pageSize);
	}
	
	private void buildLegalEntityQuery(StringBuffer query,Map values,Map params) {
		query.append(" from TMSMDLegalEntity where 1=1 and  flag = 1 ");
		
		for (Iterator<String> i = params.keySet().iterator(); i.hasNext(); ) {
			String key = i.next();
			if(key.toLowerCase().contains("pagenumber") || key.toLowerCase().contains("pagesize")){
				continue;
			}
			Object value = params.get(key);
			if(value!=null && !value.equals(""))
			{
				query.append(" and ").append(key).append("=:").append(key);
				if(key.toLowerCase().contains("date")){
					try {
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						Date date = sdf.parse((String)value);
						values.put(key, date);
						continue;
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				values.put(key, value);
			}
		}
	}
	@Override
	public void deleteEquipment(String id) {
		
		StringBuffer query=new StringBuffer();
		Map values=new HashMap<String, Object>();
		query.append("delete from TmsMdEquipment where id =:Id ");
		values.put("Id", id);
		executeHqlProduce(query.toString(), values);
	}
}
