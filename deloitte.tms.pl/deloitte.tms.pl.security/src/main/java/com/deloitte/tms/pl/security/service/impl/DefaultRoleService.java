package com.deloitte.tms.pl.security.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.commons.utils.BatchUtils;
import com.deloitte.tms.pl.core.dao.IDao;
import com.deloitte.tms.pl.core.service.impl.BaseService;
import com.deloitte.tms.pl.security.dao.IRoleDao;
import com.deloitte.tms.pl.security.model.impl.DefaultRole;
import com.deloitte.tms.pl.security.model.impl.DefaultUser;
import com.deloitte.tms.pl.security.model.impl.RoleMember;
import com.deloitte.tms.pl.security.model.impl.RoleResource;
import com.deloitte.tms.pl.security.model.impl.UrlComponent;
import com.deloitte.tms.pl.security.model.impl.VroleUser;
import com.deloitte.tms.pl.security.service.IRoleService;

/**
 * @since 2013-1-24
 * @author Jacky.gao
 */
@Component(IRoleService.BEAN_ID)
public class DefaultRoleService extends BaseService implements IRoleService {
	@Resource
	IRoleDao roleDao;
	public List<DefaultRole> loadAllRoles() {
		return roleDao.loadAllRoles();
	}
	
	public List<RoleMember> loadRoleMemberByRoleId(String roleId) {
		return roleDao.loadRoleMemberByRoleId(roleId);
	}
	public List<DefaultUser> loadRoleUsersByRoleId(String roleId){
		return roleDao.loadRoleUsersByRoleId(roleId);
	}
	@Override
	public IDao getDao() {
		return roleDao;
	}
	
	@Override
	public List<RoleMember> loadRoleMemberByRoleNameAndType(String roleId, String type) {
		// TODO Auto-generated method stub
		return roleDao.loadRoleMemberByRoleNameAndType(roleId, type);
	}

	@Override
	public DaoPage loadRoleMemberByRoleIdAndType(String roleId, String type,
			Integer pageIndex, Integer pageSize) {
		// TODO Auto-generated method stub
		return roleDao.loadRoleMemberByRoleIdAndType(roleId, type, pageIndex, pageSize);
	}

	@Override
	public Integer loadRoleMemberSizeByRoleIdAndType(String roleId, String type, String id) {
		return roleDao.loadRoleMemberSizeByRoleIdAndType(roleId, type,id);
	}

	@Override
	public DaoPage loadAllRoles(Map params, Integer pageIndex, Integer pageSize) {
		return roleDao.loadAllRoles(params, pageIndex, pageSize);
	}

	@Override
	public void saveRoles(Map dataListsMap) {
		Collection<DefaultRole> deleteList = BatchUtils.to(DefaultRole.class).getDeleteEntities(dataListsMap);
		Collection<DefaultRole> insertList =  BatchUtils.to(DefaultRole.class).getInsertEntities(dataListsMap);
		Collection<DefaultRole> updateList =  BatchUtils.to(DefaultRole.class).getModifiedEntities(dataListsMap);
		if ((updateList != null) && (updateList.size() > 0)) {
			for(DefaultRole role:updateList)
			{
				DefaultRole daorole=(DefaultRole) roleDao.get(DefaultRole.class, role.getId());
				daorole.setDesc(role.getDesc());
				daorole.setSortOrder(role.getSortOrder());
				roleDao.update(daorole);
			}
		}
		if ((insertList != null) && (insertList.size() > 0)) {
			for(DefaultRole role:insertList)
			{
				role.setId(UUID.randomUUID().toString());
				role.setCreateDate(new Date());
				role.setType(DefaultRole.NORMAL_TYPE);
				roleDao.save(role);
			}
		}
		if ((deleteList != null) && (deleteList.size() > 0)) {
			for(DefaultRole role:deleteList)
			{
				Map params=new HashMap();
				params.put("roleId", role.getId());
				List<RoleResource> roleResources=roleDao.loadRoleResourceByRoleId(role.getId());
				List<RoleMember> roleMembers=roleDao.loadRoleMemberByRoleId(role.getId());
				List<UrlComponent> roleUrlComponents=roleDao.loadUrlComponentByRoleId(role.getId());
				roleDao.removeAll(roleResources);
				roleDao.removeAll(roleMembers);
				roleDao.removeAll(roleUrlComponents);
				roleDao.remove(role);
			}
		}
		
	}

	@Override
	public List<DefaultRole> loadUserRoles(String userName) {
		return roleDao.loadRolesByUserName(userName);
	}

	@Override
	public void deleteDefaultRoleById(String id) {
		// TODO Auto-generated method stub
		deleteRoleMemberByRoleId(id);
		deleteRoleResourceByRoleId(id);
		roleDao.removeByIdDirectly(DefaultRole.class, id);
		
	}

	@Override
	public List<DefaultRole> getDefaultRoleByRoleName(String RoleName) {
		// TODO Auto-generated method stub
		return roleDao.getDefaultRoleByRoleName(RoleName);
	}

	@Override
	public List<VroleUser> getVroleUsersByUserName(String username) {
		// TODO Auto-generated method stub
		return roleDao.getVroleUsersByUserName(username);
	}

	@Override
	public void saveUserRole(String username, String roleuuids) {
		// TODO Auto-generated method stub
		deleteRoleMemberByUserName(username);			
		String[] idStrings = roleuuids.split(";");
		List<String> list = Arrays.asList(idStrings);
		for(String id:list){
			RoleMember roleMember = new RoleMember();
			roleMember.setId(UUID.randomUUID().toString());
			roleMember.setUsername(username);
			roleMember.setRoleId(id);
			roleMember.setGranted(true);
			roleDao.save(roleMember);
		}
		
	}

	@Override
	public void deleteRoleMemberByUserName(String username) {
		// TODO Auto-generated method stub
		StringBuffer query=new StringBuffer();
		Map values=new HashMap<String, Object>();
		query.append("delete FROM "+RoleMember.class.getName()+" m");
		query.append(" where m.username=:username");
		values.put("username", username);
		roleDao.executeHqlProduce(query.toString(), values);
	}

	@Override
	public void deleteRoleMemberByBatchUserName(List<String> usernames) {
		// TODO Auto-generated method stub
		for(String username:usernames){
			deleteRoleMemberByUserName(username);
		}
	}

	@Override
	public void saveRoleFrmfunction(String roleid, String fnode, String child) {
		// TODO Auto-generated method stub
		deleteRoleResourceByRoleId(roleid);
		String[] fnodes = fnode.split(";");
		String[] childs = child.split(";");
		for(String pnode:fnodes){
			RoleResource roleResource = new RoleResource();
			roleResource.setId(UUID.randomUUID().toString());
			roleResource.setRoleId(roleid);
			roleResource.setUrlId(pnode);
			roleDao.save(roleResource);
		}
		for(String cnode:childs){
			RoleResource roleResource = new RoleResource();
			roleResource.setId(UUID.randomUUID().toString());
			roleResource.setRoleId(roleid);
			roleResource.setUrlId(cnode);
			roleDao.save(roleResource);
		}
	}

	@Override
	public List<String> getFunctionIdsByRoleId(String roleid) {
		Map values=new HashMap<String, Object>();
		StringBuffer query=new StringBuffer();
		query.append(" FROM "+RoleResource.class.getName()+" r ");
		query.append("where  r.roleId=:roleId");
		values.put("roleId", roleid);
		List<RoleResource> list = roleDao.findBy(query, values);
		List<String> roleIds = new ArrayList<String>();
		for(RoleResource roleResource:list){
			String urlid = roleResource.getUrlId();
			roleIds.add(urlid);			
		}
		return roleIds;
	}
	
	private void deleteRoleResourceByRoleId(String roleId){
		StringBuffer query=new StringBuffer();
		Map values=new HashMap<String, Object>();
		query.append("delete FROM "+RoleResource.class.getName()+" m");
		query.append(" where m.roleId=:roleId");
		values.put("roleId", roleId);
		roleDao.executeHqlProduce(query.toString(), values);
	}
	private void deleteRoleMemberByRoleId(String roleId){
		StringBuffer query=new StringBuffer();
		Map values=new HashMap<String, Object>();
		query.append("delete FROM "+RoleMember.class.getName()+" m");
		query.append(" where m.roleId=:roleId");
		values.put("roleId", roleId);
		roleDao.executeHqlProduce(query.toString(), values);
	}


	
}
