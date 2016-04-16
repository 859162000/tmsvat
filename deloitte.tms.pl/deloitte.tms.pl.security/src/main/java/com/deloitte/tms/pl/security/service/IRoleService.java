package com.deloitte.tms.pl.security.service;

import java.util.List;
import java.util.Map;

import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.service.IService;
import com.deloitte.tms.pl.security.model.impl.DefaultRole;
import com.deloitte.tms.pl.security.model.impl.DefaultRoleInParam;
import com.deloitte.tms.pl.security.model.impl.DefaultUser;
import com.deloitte.tms.pl.security.model.impl.RoleMember;
import com.deloitte.tms.pl.security.model.impl.VroleUser;

public interface IRoleService extends IService{
	public static final String BEAN_ID="ling2.roleService";
	List<DefaultRole> loadAllRoles();
	List<DefaultRole> loadUserRoles(String username);
	List<RoleMember> loadRoleMemberByRoleId(String roleId);
	List<DefaultUser> loadRoleUsersByRoleId(String roleId);
	/**
	 * 
	 * @param roleId 角色name
	 * @param type RoleMember.USER,DEPT
	 * @return
	 */
	public List<RoleMember> loadRoleMemberByRoleNameAndType(String roleName,String type);
	public DaoPage loadRoleMemberByRoleIdAndType(String roleId,String type,Integer pageIndex,Integer pageSize);
	public Integer loadRoleMemberSizeByRoleIdAndType(String roleId,String type, String id);
	public DaoPage loadAllRoles(Map params,Integer pageIndex,Integer pageSize);
	public void saveRoles(Map roles);
	public void deleteDefaultRoleById(String id);
	public List<DefaultRole> getDefaultRoleByRoleName(String RoleName);
	public List<VroleUser> getVroleUsersByUserName(String username);
	
	public void saveUserRole(String username,String roleuuids);
	public void saveRoleFrmfunction(String roleid, String fnode,String child);
	public void deleteRoleMemberByUserName(String username);
	public void deleteRoleMemberByBatchUserName(List<String> username);
	public List<String> getFunctionIdsByRoleId(String roleid);
	
}
