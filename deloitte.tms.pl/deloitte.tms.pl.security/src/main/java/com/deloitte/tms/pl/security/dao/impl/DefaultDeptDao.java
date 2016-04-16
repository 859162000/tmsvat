package com.deloitte.tms.pl.security.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.core.commons.constant.TableColnumDef;
import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.core.dao.impl.BaseDao;
import com.deloitte.tms.pl.orgpath.model.OrgPath;
import com.deloitte.tms.pl.security.dao.IDeptDao;
import com.deloitte.tms.pl.security.model.SecurityDept;
import com.deloitte.tms.pl.security.model.SecurityPosition;
import com.deloitte.tms.pl.security.model.SecurityUser;
import com.deloitte.tms.pl.security.model.impl.DefaultDept;
import com.deloitte.tms.pl.security.model.impl.UserDept;
import com.deloitte.tms.pl.security.utils.LittleUtils;

/**
 * @since 2013-1-24
 * @author Jacky.gao
 */
@Component(IDeptDao.BEAN_ID)
public class DefaultDeptDao extends BaseDao implements IDeptDao {
	
	
	public void getOrgByRelationKeyId(String relationKeyId, ArrayList<String> needFields){
		
		StringBuffer sb = new StringBuffer();
		
		sb.append(" orgId from TmsMdOrgLegalEntity ").append(" where id=:id");
		
		//TmsMdOrgLegalEntity a;//
		
		//this.findBy(sb.toString(), values);
	}
	
	
	public List<SecurityDept> loadUserDepts(String username) {
		StringBuffer query=new StringBuffer();
		query.append("SELECT D FROM UserDept UD ,DefaultDept D where UD.deptId=D.id and UD.username=:username");
		
		query.append("  and UD.flag=").append(LittleUtils.one);
		query.append("  and D.flag=").append(LittleUtils.one);
		
		Map values=new HashMap<String, Object>();
		values.put("username", username);
		return findBy(query, values);
	}
	public SecurityDept loadDeptById(String deptId) {
		StringBuffer query=new StringBuffer();
		query.append("from DefaultDept D WHERE D.id=:id");
		Map values=new HashMap<String, Object>();
		values.put("id", "deptId");
		List<SecurityDept> depts= findBy(query, values);
		if(depts.size()==0){
			return null;			
		}else{
			return depts.get(0);
		}
	}
	private void buildParentDept(SecurityDept dept){
		DefaultDept defaultDept=(DefaultDept)dept;
		if(StringUtils.isNotEmpty(defaultDept.getParentId())){
			SecurityDept parentDept=this.loadDeptById(defaultDept.getParentId());
			defaultDept.setParent(parentDept);
			this.buildParentDept(parentDept);
		}
	}
	public List<SecurityDept> loadDeptsByParentId(String parentId,String companyId, Map params) {
		StringBuffer query=new StringBuffer();
		Map values=new HashMap<String, Object>();
		query.append("FROM DefaultDept D WHERE D.companyId=:companyId");
		parentId=parentId==null?(String)params.get("parentId"):parentId;
		if(parentId==null){
			query.append(" AND D.parentId IS NULL");
		}else{
			query.append(" AND D.parentId=:parentId");			
			values.put("parentId", parentId);
		}
		Object object=params.get("name");
		if(AssertHelper.notEmpty(object)){
			query.append(" AND D.name like :name");			
			values.put("name", "%"+object+"%");
		}
		object=params.get(TableColnumDef.FLAG_DEF);
		if(AssertHelper.notEmpty(object)){
			query.append(" AND D.flag = :flag");			
			values.put(TableColnumDef.FLAG_DEF, object);			
		}
		return findBy(query, values);
	}
	public List<SecurityDept> findAllDepts(){
		StringBuffer query=new StringBuffer();
		Map values=new HashMap<String, Object>();
		query.append("FROM DefaultDept D ");
		return findBy(query, values);
	}
	
	
	@Override
	public List<SecurityDept> findAllDeptsByFlag(){
		StringBuffer query=new StringBuffer();
		Map values=new HashMap<String, Object>();
		query.append("FROM DefaultDept D where flag != '0' ");
		System.out.println("findAllDeptsByFlag > query:"+query);
		return findBy(query, values);
	}
	
	
	public String uniqueCheck(String id){
		String hql = "select count(*) from " + DefaultDept.class.getName() + " d where d.id = :id";
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("id", id);
		if(this.queryForInt(hql, parameterMap)>0){
			return id+"Already Exists";
		}else{
			return null;
		}
	}
	public int countChildren(String parentId) {
		String hql = "select count(*) from " + DefaultDept.class.getName() + " d where d.parentId = :parentId";
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("parentId", parentId);
		return this.queryForInt(hql, parameterMap);
	}
	@Override
	public List<SecurityPosition> loadPositionsByDeptId(String deptId) {
		StringBuffer query=new StringBuffer();
		query.append("SELECT D FROM DeptPosition DP ,DefaultPosition D where DP.deptId=D.id and DP.deptId=:deptId");
		Map values=new HashMap<String, Object>();
		values.put("deptId", deptId);
		return findBy(query, values);
	}
	@Override
	public List<SecurityUser> loadUsersByDeptId(String deptId) {
		StringBuffer query=new StringBuffer();
		query.append("SELECT D FROM UserDept UD ,DefaultUser D where UD.flag='1' and D.flag='1' and UD.username=D.username and UD.deptId=:deptId");
		query.append(" order by UD.sortOrder ");
		Map values=new HashMap<String, Object>();
		values.put("deptId", deptId);
		return findBy(query, values);
	}
	public List<UserDept> loadUserDeptsByDeptId(String deptId) {
		AssertHelper.notEmpty_assert(deptId, "机构id不能为空");
		StringBuffer query=new StringBuffer();
		query.append(" FROM UserDept UD where UD.deptId=:deptId");
		Map values=new HashMap<String, Object>();
		values.put("deptId", deptId);
		return findBy(query, values);
	}
	@Override
	public DefaultDept findUserDeptByUsername(String username) {
		AssertHelper.notEmpty_assert(username, "用户id不能为空");
		StringBuffer query=new StringBuffer();
		query.append(" select d FROM DefaultDept d,UserDept UD where d.id=UD.deptId and UD.username=:username");
		Map values=new HashMap<String, Object>();
		values.put("username", username);
		List<DefaultDept> userDepts= findBy(query, values);
		if(userDepts.size()>0){
			return userDepts.get(0);
		}else{
			return null;
		}
	}
	public OrgPath findOrgPathByOrgId(String orgId) {
		AssertHelper.notEmpty_assert(orgId, "用户id不能为空");
		StringBuffer query=new StringBuffer();
		query.append(" select d FROM OrgPath d where d.orgid=:orgId");
		Map values=new HashMap<String, Object>();
		values.put("orgId", orgId);
		List<OrgPath> userDepts= findBy(query, values);
		if(userDepts.size()>0){
			return userDepts.get(0);
		}else{
			return null;
		}
	}
	
	/*
	 * remove by flag firstly filter by username for baseUserOrg table
	 */
	public void updateUserOrgByFilter(String username){
		
		StringBuffer sb = new StringBuffer();
		
		sb.append(" update ").append(UserDept.class.getName()).append(" set flag=");
		sb.append(LittleUtils.zero);
		sb.append(" where username=:username");
		
		HashMap<String, String> para1=new HashMap<String, String>();
		para1.put("username", username);
		
		int delSize = this.executeHqlProduce(sb.toString(), para1);
		System.out.println("updateUserOrgByFilter deleted "+delSize+ " records for username: "+username);
	}	
	
	@Override
	public void updateUserOrgMainByUser(String username, String orgIds) {
		
		this.updateUserOrgByFilter(username);

		String[] orgId=orgIds.split(",");
		
		if(AssertHelper.empty(username) || AssertHelper.empty(username.trim())){
			
		}else{
			
			for(String id : orgId){
				
				if(AssertHelper.empty(id) || AssertHelper.empty(id.trim())){
					
				}else{
					
					UserDept userDept = new UserDept(username, id.trim());
					save(userDept);
				}
			}
			
		}
		
	}
	
	@Override
	public void updateUserOrgMainByOrg(String usernames, String deptId) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		
		sb.append(" update ").append(UserDept.class.getName()).append(" set flag=");
		sb.append(LittleUtils.zero);
		sb.append(" where deptId=:deptId");
		
		HashMap<String, String> para1=new HashMap<String, String>();
		para1.put("deptId", deptId);
		
		int delSize = this.executeHqlProduce(sb.toString(), para1);
		
		System.out.println("updateUserOrgMainByUser deleted "+delSize+ " records for deptId: "+deptId);
		
		
		
		if(AssertHelper.empty(usernames) || AssertHelper.empty(usernames.trim())){
			
		}else{
			
			String[] usernameAll=usernames.split(",");
			
			for(String username : usernameAll){
				
				if(AssertHelper.empty(username) || AssertHelper.empty(username.trim())){
					
				}else{
					
					UserDept userDept = new UserDept(username.trim(), deptId);
					save(userDept);
				}
			}
			
		}
		
	}
}
