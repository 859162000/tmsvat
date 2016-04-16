package com.deloitte.tms.base.masterdata.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.deloitte.tms.base.masterdata.dao.LegalEntityDao;
import com.deloitte.tms.base.masterdata.model.LegalTaxCategory;
import com.deloitte.tms.base.masterdata.model.TmsMdLegalEnablePrint;
import com.deloitte.tms.base.masterdata.model.TmsMdLegalEntity;
import com.deloitte.tms.base.masterdata.model.TmsMdLegalEquipment;
import com.deloitte.tms.base.masterdata.model.TmsMdLegalInvoice;
import com.deloitte.tms.base.masterdata.model.TmsMdOrgLegalEntity;
import com.deloitte.tms.base.masterdata.model.TmsMdUsageLocalLegal;
import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.core.dao.impl.BaseDao;
import com.deloitte.tms.pl.security.utils.LittleUtils;

/**
 * @since 2013-1-24
 * @author Jacky.gao
 */
@Component(LegalEntityDao.BEAN_ID)
public class LegalEntityDaoImpl extends BaseDao implements LegalEntityDao {
	
	/**   
	 * @param id  
	 * @see com.deloitte.tms.pl.security.dao.IDeptDao#delByIds(java.lang.String)  
	 */
	
	@Override
	public void delByIds(ArrayList<String> ids, Class cla) {
		
		StringBuffer query=new StringBuffer();
		Map values=new HashMap<String, Object>();
		query.append("delete from "+ cla.getName()+"  rr where rr.id in (:Id) ");
		values.put("Id", ids);
		
		System.out.println("delByIds query: "+query);
		executeHqlProduce(query.toString(), values);
		
	}
	
	
	@Override
	public void delByIdsUseFlag(ArrayList<String> ids, Class cla) {
		
		StringBuffer query=new StringBuffer();
		Map values=new HashMap<String, Object>();
		
		query.append(" update ").append(cla.getName()).append( " set flag = '0' where  id in (:ids) ");
		
		
		
		//flag='1' from "+ cla.getName()+"  rr where rr.id in (:Id) ");
		values.put("ids", ids);
		
		System.out.println("delByIds query: "+query);
		executeHqlProduce(query.toString(), values);
		
	}
	
	
	
	
	/**
	 * no query filter
	 * @param cla
	 * @param params
	 * @param pageIndex
	 * @param pageSize
	 * @return  
	 * @see com.deloitte.tms.base.masterdata.dao.LegalEntityDao#loadPageTaxOrg(java.lang.Class, java.util.Map, int, int)
	 */

	@Override
	public DaoPage loadPageTaxOrg(Class cla, int pageIndex, int pageSize){
		
		try{

		StringBuffer newQuery=new StringBuffer();
		
		HashMap<String, String> newMap=new HashMap<String, String>();
		
		
		
		newQuery.append(" from ").append(cla.getName() ).append(" where flag!='0' ");
		
		System.out.println("loadPageTaxOrg: "+ newQuery );

		

		
		System.out.println("loadPageTaxOrg dao hpl:"+newQuery+" ; "+" newMap.size():"+newMap.size());
		
		return pageBy(newQuery, newMap, pageIndex, pageSize);
		
		}catch(Exception x){
			
			x.printStackTrace();
		}
		return null;
	
	}
	
	
	
	/**
	 * 
	 * @param params
	 * @param pageIndex
	 * @param pageSize
	 * @return  
	 * @see com.deloitte.tms.pl.security.dao.IDeptDao#loadPageDepts(java.util.Map, int, int)
	 */
	@Override
	public DaoPage loadPageTaxDepts(Class cla,Map<String, String> params, int pageIndex, int pageSize) {

		StringBuffer newQuery=new StringBuffer();
		
		HashMap<String, Object> newMap=new HashMap<String, Object>();
		
		//pageNumber,pageSize
		
		try{
			if(params!=null){
				params.remove("pageNumber");
				params.remove("pageSize");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		newQuery.append(" from ").append(cla.getName() ).append(" where  flag!='0' ");
		
		System.out.println("loadPageTaxDepts: "+ newQuery );
		
		if(params!=null && params.size()>0){
			
			for (Iterator iter = params.keySet().iterator(); iter.hasNext();) {
				
				String key = (String) iter.next();
				String value = params.get(key);
				
				if( AssertHelper.empty(value)||AssertHelper.empty(key)  ){
					
				}else{
					newQuery.append(" and ").append(key).append("=:").append(key).append(" ");
					newMap.put(key, value);
				}
			}
		}
		

		
		System.out.println("loadPageTaxDepts dao hpl:"+newQuery+" ; "+" newMap.size():"+newMap.size());
		
		return pageBy(newQuery, newMap, pageIndex, pageSize);
		

	
	}
	
	/**
	 * 
	 *〈一句话功能简述〉 
	 * 功能详细描述
	 * @param params
	 * @param query
	 * @param values
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	private void buildQueryTaxDept(Map params, StringBuffer query, Map values) {
		if(params==null){
			params=new HashMap();
		}
		Object object= params.get("roles");
		String[] roles;
		if(object instanceof String){
			if(AssertHelper.notEmpty(object.toString())){
				roles=new String[]{object.toString()};
			}else{
				roles=new String[]{};
			}
		}else if(object instanceof String[]){
			roles=(String[])object;
		}else{
			roles=new String[]{};
		}		
		String role=(String)params.get("role");
		if((AssertHelper.notEmpty(roles)&&roles.length>0)||AssertHelper.notEmpty(role)){			
			query.append("SELECT u FROM DefaultUser u,DefaultRole r,RoleMember rm WHERE 1=1  and u.username= rm.username and rm.roleId=r.id ");
		}else{
			query.append("SELECT u FROM DefaultUser u WHERE 1=1 ");
		}
		if(AssertHelper.notEmpty(roles)&&roles.length>0){
			query.append(" and r.name in (:roles) ");
			values.put("roles", roles);
		}
		if(AssertHelper.notEmpty(role)){
			query.append(" and r.desc like :role ");
			values.put("role", "%"+role+"%");
		}
		Object value=params.get("username");
		if(AssertHelper.notEmpty(value)){
			query.append(" and u.username like :username ");
			values.put("username", "%"+value+"%");
		}
		value=params.get("userCode");
		if(AssertHelper.notEmpty(value)){
			query.append(" and u.userCode like :userCode ");
			values.put("userCode", "%"+value+"%");
		}
		value=params.get("cname");
		if(AssertHelper.notEmpty(value)){
			query.append(" and u.cname like :cname ");
			values.put("cname", "%"+value+"%");
		}
		value=params.get("selectids");
		if(AssertHelper.notEmpty(value)){
			String[] selectids=value.toString().split(",");
			query.append(" and u.username not in :selectids ");
			values.put("selectids", selectids);
		}
		value=params.get("selectedids");
		if(AssertHelper.notEmpty(value)){
			String[] selectedids=value.toString().split(",");
			query.append(" and u.username in :selectedids ");
			values.put("selectedids", selectedids);
		}
		value=params.get("keyword");
		if(AssertHelper.notEmpty(value)){
			query.append(" and (u.nickname like :keyword or u.mobile like :keyword or u.realName like :keyword)");
			values.put("keyword", "%"+value+"%");
		}
		query.append(" order by u.enabled  desc");
	}



	@Override
	public List<TmsMdLegalEntity> getAllLegalEntities() {
		StringBuffer query=new StringBuffer();
		Map values=new HashMap<String, Object>();
		query.append(" from TmsMdLegalEntity  t  where flag=").append(LittleUtils.one);
		return findBy(query,values);
	}


	@Override
	public List<TmsMdOrgLegalEntity> getAllOrgLegalEntities() {
		StringBuffer query=new StringBuffer();
		Map values=new HashMap<String, Object>();
		query.append(" from TmsMdOrgLegalEntity  t  ");
		return findBy(query,values);
	}



	@Override
	public List<TmsMdLegalInvoice> getAllLegalInvoice() {
		StringBuffer query=new StringBuffer();
		Map values=new HashMap<String, Object>();
		query.append(" from TmsMdLegalInvoice  t  ");
		return findBy(query,values);
	}



	@Override
	public List<LegalTaxCategory> getAllLegalTaxCategories(String taxCode) {
		StringBuffer query=new StringBuffer();
		Map values=new HashMap<String, Object>();
		query.append(" from LegalTaxCategory  t  ");
		return findBy(query,values);
	}
	
	@Override
	public List<TmsMdUsageLocalLegal> getAllTmsMdUsageLocalLegals() {
		StringBuffer query=new StringBuffer();
		Map values=new HashMap<String, Object>();
		query.append(" from TmsMdUsageLocalLegal  t  ");
		return findBy(query,values);
	}
	@Override
	public List<TmsMdLegalEquipment> getAllTmsMdLegalEquipments() {
		StringBuffer query=new StringBuffer();
		Map values=new HashMap<String, Object>();
		query.append(" from TmsMdLegalEquipment  t  ");
		return findBy(query,values);
	}
	@Override
	public List<TmsMdLegalEnablePrint> getAllTmsMdLegalEnablePrints() {
		StringBuffer query=new StringBuffer();
		Map values=new HashMap<String, Object>();
		query.append(" from TmsMdLegalEnablePrint  t  ");
		return findBy(query,values);
	}


	@Override
	public List<TmsMdLegalEntity> searchLegal(HashMap<String, Object> para) {
		
		StringBuffer query=new StringBuffer();
		
		query.append(" from TmsMdLegalEntity  t  where flag=").append(LittleUtils.one);
		
		if( para!=null && para.size() > 0){
			
			
			
			Set<String> keys = para.keySet();
			
			for(String k : keys){
				query.append(" and ").append(k).append("  like :").append(k).append(" ");
				
				String tempV = (String)para.get(k)+"%";
				System.out.println("tempV like is :"+tempV);
				para.put(k, tempV);
				
			}
			
		}
		
		System.out.println("---searchLegal query:"+query);
		return findBy(query,para);
	}
}
