package com.deloitte.tms.pl.security.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.core.dao.impl.BaseDao;
import com.deloitte.tms.pl.security.dao.IUserDao;
import com.deloitte.tms.pl.security.model.SecurityUser;
import com.deloitte.tms.pl.security.model.impl.DefaultUser;

/**
 * @since 2013-1-18
 * @author Jacky.gao
 */
@Component(IUserDao.BEAN_ID)
public class DefaultUserDao extends BaseDao implements IUserDao {
	public SecurityUser loadUserByUsername(String username){
		StringBuffer query=new StringBuffer();
		Map values=new HashMap<String, Object>();
		query.append("from DefaultUser U WHERE U.username=:username");
		values.put("username", username);
		List<SecurityUser> users=findBy(query, values);
		if(users.size()==0){
			return null;		
		}else{
			DefaultUser user=(DefaultUser)users.get(0);
			return user;
		}
	}
	public SecurityUser loadUserByMobile(String mobile){
		StringBuffer query=new StringBuffer();
		Map values=new HashMap<String, Object>();
		query.append("from DefaultUser U WHERE U.mobile=:mobile");
		values.put("mobile", mobile);
		List<SecurityUser> users=findBy(query, values);
		if(users.size()==0){
			return null;		
		}else{
			DefaultUser user=(DefaultUser)users.get(0);
			return user;
		}
	}
	public SecurityUser loadUserByEmail(String email){
		StringBuffer query=new StringBuffer();
		Map values=new HashMap<String, Object>();
		query.append("from DefaultUser U WHERE U.email=:email");
		values.put("email", email);
		List<SecurityUser> users=findBy(query, values);
		if(users.size()==0){
			return null;		
		}else{
			DefaultUser user=(DefaultUser)users.get(0);
			return user;
		}
	}
	@Override
	public DefaultUser getDefaultUserByUserId(String userId) {
		StringBuffer query=new StringBuffer();
		Map values=new HashMap<String, Object>();
		query.append("from DefaultUser U WHERE U.userId=:userId");
		values.put("userId", userId);
		List<DefaultUser> users=findBy(query, values);
		if(users.size()==0){
			return null;		
		}else{
			DefaultUser user=(DefaultUser)users.get(0);
			return user;
		}
	}
	public DaoPage loadPageUsers(Map params,int pageIndex,int pageSize) {
		StringBuffer query=new StringBuffer();
		Map values=new HashMap<String, Object>();
		buildQueryUser(params, query, values);
		return pageBy(query, values, pageIndex, pageSize);
	}
	public Collection<SecurityUser>  loadUsers(Map params){
		StringBuffer query=new StringBuffer();
		Map values=new HashMap<String, Object>();
		buildQueryUser(params, query, values);
		return findBy(query, values);
	}
	private void buildQueryUser(Map params, StringBuffer query, Map values) {
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
	
	public Collection<SecurityUser> loadUsersByDeptId(String deptId) {
		StringBuffer query=new StringBuffer();
		Map values=new HashMap<String, Object>();
		query.append("SELECT x FROM UserDept UD LEFT JOIN DefaultUser x ON UD.username=x.username WHERE UD.deptId=:deptId");
		values.put("deptId", deptId);
		return findBy(query, values);
	}
	
	public void changePassword(String username, String newPassword) {
		DefaultUser user=(DefaultUser) loadUserByUsername(username);
		user.setPassword(newPassword);
		update(user);
	}
	public void registerAdministrator(String username, String cname,
			String ename, String password, String email,String mobile, String companyId) {
		
	}
	@Override
	public String userIsExists(String username) {
		String hql = "select count(*) from " + DefaultUser.class.getName()
				+ " u where u.username = :username";
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("username", username);
		int count = this.queryForInt(hql, parameterMap);

		String returnStr = null;
		if (count > 0) {
			returnStr = "此用户已存在！";
		}
		return returnStr;
	}
	public List<DefaultUser>  loadAllUsers(){
		List<DefaultUser> allusers=new ArrayList<DefaultUser>();
		int pageIndex = 1;
		int pageSize=2000;
		int totalsucess=0;
		int size = 0;
		
		StringBuffer query=new StringBuffer();
		Map values=new HashMap<String, Object>();
		query.append("from DefaultUser U ");
		
		do {
			//分批查询数据			
			List<DefaultUser> users= findByPage(query, values, pageIndex, pageSize);
			allusers.addAll(users);		
			
			size = users.size();
			pageIndex++;
		} while (size == pageSize);
		return allusers;
	}
}
