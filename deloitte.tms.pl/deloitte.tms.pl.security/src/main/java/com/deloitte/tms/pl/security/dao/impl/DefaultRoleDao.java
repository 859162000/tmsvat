package com.deloitte.tms.pl.security.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.dao.impl.BaseDao;
import com.deloitte.tms.pl.security.dao.IRoleDao;
import com.deloitte.tms.pl.security.model.impl.DefaultRole;
import com.deloitte.tms.pl.security.model.impl.DefaultUser;
import com.deloitte.tms.pl.security.model.impl.RoleMember;
import com.deloitte.tms.pl.security.model.impl.RoleResource;
import com.deloitte.tms.pl.security.model.impl.UrlComponent;
import com.deloitte.tms.pl.security.model.impl.VroleUser;

/**
 * @since 2013-1-24
 * @author Jacky.gao
 */
@Component(IRoleDao.BEAN_ID)
public class DefaultRoleDao extends BaseDao implements IRoleDao {
	public List<DefaultRole> loadAllRoles() {
		StringBuffer query=new StringBuffer();
		Map values=new HashMap<String, Object>();
		query.append("FROM "+DefaultRole.class.getName()+" R order by sortOrder desc");
//		if(StringUtils.isNotEmpty(this.getFixedCompanyId())){
//			query.append(" where R.companyId=:companyId");
//			return getJdbcTemplate().query(sql,new Object[]{this.getFixedCompanyId()},new RoleRowMapper());
//		}else{
//			return getJdbcTemplate().query(sql, new RoleRowMapper());
//		}
		return findBy(query, values);
	}
	public DaoPage loadAllRoles(Map params,Integer pageIndex,Integer pageSize) {
		StringBuffer query=new StringBuffer();
		Map values=new HashMap<String, Object>();
		query.append("from "+DefaultRole.class.getName()+" r where 1=1");
		query.append(" and r.type=:type and r.companyId=:companyId");
		values.put("type", params.get("type"));
		values.put("companyId", params.get("companyId"));
		query.append(" order by sortOrder  desc");
		return pageBy(query, values, pageIndex, pageSize);
	}

	class RoleRowMapper implements RowMapper<DefaultRole>{
		public DefaultRole mapRow(ResultSet rs, int rowNum) throws SQLException {
			DefaultRole role=new DefaultRole();
			role.setId(rs.getString("ID_"));
			role.setCompanyId(rs.getString("COMPANY_ID_"));
			return role;
		}
	}
	
	public List<RoleMember> loadRoleMemberByRoleId(String roleId) {
		StringBuffer query=new StringBuffer();
		Map values=new HashMap<String, Object>();
		query.append("SELECT M FROM "+RoleMember.class.getName()+" M WHERE M.roleId=:roleId");
		values.put("roleId", roleId);
		return findBy(query, values);
		
	}
	public List<RoleMember> loadRoleMemberByRoleNameAndType(String rolename,String type)
	{
		StringBuffer query=new StringBuffer();
		Map values=new HashMap<String, Object>();
		query.append("SELECT m FROM "+RoleMember.class.getName()+" m,"+DefaultRole.class.getName()+" r WHERE m.roleId=r.id and r.name=:rolename ");
		values.put("rolename", rolename);
		buidlRoleMenberTypeQuery(type, query);
		query.append(" order by m.createDate desc");
		return findBy(query, values);
	}
	private void buidlRoleMenberQuery(String roleId, String type,
			StringBuffer query, Map values) {
		query.append("SELECT m FROM "+RoleMember.class.getName()+" m  WHERE m.roleId=:roleId ");
		values.put("roleId", roleId);
		buidlRoleMenberTypeQuery(type, query);
		query.append(" order by m.createDate desc");
	}
	private void buidlRoleMenberTypeQuery(String type, StringBuffer query) {
		if(type.equals("user")){
			query.append(" and m.username is not null");
		}else if(type.equals("dept")){
			query.append(" and m.deptId is not null");
		}else if(type.equals("position")){
			query.append(" and m.positionId is not null");
		}else if(type.equals("group")){
			query.append(" and m.group is not null");
		}
	}
	public DaoPage loadRoleMemberByRoleIdAndType(String roleId,String type,Integer pageIndex,Integer pageSize)
	{
		StringBuffer query=new StringBuffer();
		Map values=new HashMap<String, Object>();
		buidlRoleMenberQuery(roleId, type, query, values);
		return pageBy(query, values, pageIndex, pageSize);
	}
	public Integer loadRoleMemberSizeByRoleIdAndType(String roleId, String type,String id)
	{
		StringBuffer query=new StringBuffer();
		Map values=new HashMap<String, Object>();
		query.append("SELECT count(rm) FROM "+RoleMember.class.getName()+" rm WHERE rm.roleId=:roleId");
		values.put("roleId", roleId);
		if(type.equals("user")){
			values.put("username",id);
			query.append(" and rm.username=:username ");
		}else if(type.equals("dept")){
			values.put("deptId",id);
			query.append(" and rm.deptId=:deptId");
		}else if(type.equals("position")){
			values.put("positionId",id);
			query.append(" and rm.positionId=:positionId");
		}else if(type.equals("group")){
			values.put("groupId",id);
			query.append(" and rm.group.id=:groupId");
		}
		return queryForInt(query.toString(), values);
	}
	@Override
	public List<RoleResource> loadRoleResourceByRoleId(String roleId) {
		StringBuffer query=new StringBuffer();
		Map values=new HashMap<String, Object>();
		query.append("SELECT M FROM "+RoleResource.class.getName()+" M WHERE M.roleId=:roleId");
		values.put("roleId", roleId);
		return findBy(query, values);
	}
	@Override
	public List<UrlComponent> loadUrlComponentByRoleId(String roleId) {
		StringBuffer query=new StringBuffer();
		Map values=new HashMap<String, Object>();
		query.append("SELECT M FROM "+UrlComponent.class.getName()+" M WHERE M.roleId=:roleId");
		values.put("roleId", roleId);
		return findBy(query, values);
	}
	@Override
	public List<DefaultRole> loadRolesByUserName(String userName) {
		StringBuffer query=new StringBuffer();
		Map values=new HashMap<String, Object>();
		query.append("SELECT distinct(r) FROM "+DefaultRole.class.getName()+" r,"+RoleMember.class.getName()+" m WHERE m.roleId=r.id ");
		query.append(" and m.username=:username");
		values.put("username", userName);
		query.append(" order by r.sortOrder desc");//不能更改排序方式,否则组件权限不能正确覆盖
		List<DefaultRole> results= findBy(query, values);
		return results;
	}
	public List<DefaultUser> loadRoleUsersByRoleId(String roleId){
		StringBuffer query=new StringBuffer();
		Map values=new HashMap<String, Object>();
		query.append("SELECT distinct(u) FROM "+DefaultUser.class.getName()+" u,"+RoleMember.class.getName()+" m WHERE u.username=m.username ");
		query.append(" and m.roleId=:roleId");
		query.append(" and m.granted=:granted");
		values.put("roleId", roleId);
		values.put("granted", true);
		List<DefaultUser> results= findBy(query, values);
		return results;
	}
	@Override
	public List<DefaultRole> getDefaultRoleByRoleName(String roleName) {
		// TODO Auto-generated method stub
		StringBuffer query=new StringBuffer();
		Map values=new HashMap<String, Object>();
		query.append("SELECT r FROM "+DefaultRole.class.getName()+" r WHERE r.name like :rolename ");	
		values.put("rolename", "%"+roleName+"%");
		query.append(" order by r.sortOrder desc");//不能更改排序方式,否则组件权限不能正确覆盖
		List<DefaultRole> results= findBy(query, values);
		return results;
	}
	@Override
	public List<VroleUser> getVroleUsersByUserName(String userName) {
		List<DefaultRole> alllist = loadAllRoles();
		
		StringBuffer query=new StringBuffer();
		Map values=new HashMap<String, Object>();
		query.append("SELECT m FROM "+RoleMember.class.getName()+" m");
		query.append(" where m.username=:username");
		values.put("username", userName);		
		List<RoleMember> results= findBy(query, values);
		List<VroleUser> list = new ArrayList<VroleUser>();
		for(DefaultRole role:alllist){
			VroleUser vroleUser = new VroleUser();
			vroleUser.setId(role.getId());
			vroleUser.setName(role.getName());
			vroleUser.setDesc(role.getDesc());
			vroleUser.setType(role.getType());
			vroleUser.setSortOrder(role.getSortOrder());
			vroleUser.setChecked(false);
			for(RoleMember roleMember:results){
				if(role.getId().equals(roleMember.getRoleId())){
					vroleUser.setChecked(true);
					break;
				}else {
					continue;
				}
			}				
			list.add(vroleUser);
		}
		return list;
	}
	
}
