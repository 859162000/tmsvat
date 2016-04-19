package com.deloitte.tms.base.masterdata.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.deloitte.tms.base.masterdata.dao.EquipmentDao;
import com.deloitte.tms.base.masterdata.model.TmsMdEquipment;
import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.core.dao.impl.BaseDao;
import com.deloitte.tms.pl.security.utils.LittleUtils;

@Component(EquipmentDao.BEAN_ID)
public class EquipmentDaoImpl extends BaseDao<TmsMdEquipment> implements EquipmentDao{
	
	Logger logger = Logger.getLogger(this.getClass());
	
	public DaoPage findEquipmentByParams(Map params, Integer pageIndex,Integer pageSize)
	{
		StringBuffer query=new StringBuffer();
		Map values=new HashMap();
		buildEquipmentQuery(query, values, params);
		return pageBy(query, values, pageIndex, pageSize);
		
	}
	
	private void buildEquipmentQuery(StringBuffer query,Map values,Map params) {
		
		try{
		query.append(" from ");
		query.append(TmsMdEquipment.class.getName());
		query.append(" where flag =").append(LittleUtils.one);
		
		Object value=params.get("id");
		if(!AssertHelper.empty(value) )
		{
			if(value instanceof String){
				
				String newV = (String)value;	
				if(LittleUtils.strEmpty(newV)){
					
				}else{
					query.append(" and id=:ID");
					values.put("ID", newV.trim());
				}
			}
			
		}
		
		
		
		value=params.get("getparent");
		if(!AssertHelper.empty(value))
		{
			if(LittleUtils.isDB2()){
				
				query.append(" and parentEquipmentId ='' ");
			}else{
				query.append(" and parentEquipmentId is null");
			}
			
			
		}
		
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		logger.info("buildEquipmentQuery will run: "+query);

	}
	
	public List<TmsMdEquipment> loadEquipmentData(Map params)
	{
		List<TmsMdEquipment> list=null;
		try{
		StringBuffer query=new StringBuffer();
		Map values=new HashMap();
		query.append("  from ");
		query.append(TmsMdEquipment.class.getName());
		query.append("  where  flag =" ).append(LittleUtils.one);
		
	/*	Object value=params.get("enabledFlag");
		if(value!=null)
		{
			query.append(" and enabledFlag=:enabledFlag");
			values.put("enabledFlag", value);
		}*/
		list = findBy(query, params);
		
		if(list == null){
			list = new ArrayList<TmsMdEquipment>();
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return list;
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

	@Override
	public List<TmsMdEquipment> loadSonEquipments(String parentEquipmentId) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("parentEquipmentId", parentEquipmentId);
		return find(" from TmsMdEquipment where 1=1 and  flag = 1 and parentEquipmentId=:parentEquipmentId", params);		
	}
}
