package com.deloitte.tms.pl.security.dao;

import java.util.List;
import java.util.Map;

import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.dao.IDao;
import com.deloitte.tms.pl.security.model.impl.DefaultRole;
import com.deloitte.tms.pl.security.model.impl.DefaultUser;
import com.deloitte.tms.pl.security.model.impl.RoleMember;
import com.deloitte.tms.pl.security.model.impl.RoleResource;
import com.deloitte.tms.pl.security.model.impl.UrlComponent;
import com.deloitte.tms.pl.security.model.impl.VroleUser;

public interface IRoleDao extends IDao {
	public static final String BEAN_ID="ling2.roleDao";
	List<DefaultRole> loadAllRoles();
	List<DefaultRole> loadRolesByUserName(String userName);
	List<DefaultUser> loadRoleUsersByRoleId(String roleId);
	List<RoleMember> loadRoleMemberByRoleId(String roleId);
	public List<RoleMember> loadRoleMemberByRoleNameAndType(String roleId, String type);
	public DaoPage loadRoleMemberByRoleIdAndType(String roleId,String type,Integer pageIndex,Integer pageSize);
	public DaoPage loadAllRoles(Map params,Integer pageIndex,Integer pageSize);
	public Integer loadRoleMemberSizeByRoleIdAndType(String roleId, String type,String id);
	List<RoleResource> loadRoleResourceByRoleId(String roleId);
	List<UrlComponent> loadUrlComponentByRoleId(String roleId);
	public List<DefaultRole> getDefaultRoleByRoleName(String RoleName);
	
	public List<VroleUser> getVroleUsersByUserName(String userName);
	
}
