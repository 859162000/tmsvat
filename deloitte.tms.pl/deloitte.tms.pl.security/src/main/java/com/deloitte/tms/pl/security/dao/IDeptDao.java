package com.deloitte.tms.pl.security.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.deloitte.tms.pl.core.dao.IDao;
import com.deloitte.tms.pl.orgpath.model.OrgPath;
import com.deloitte.tms.pl.security.model.SecurityDept;
import com.deloitte.tms.pl.security.model.SecurityPosition;
import com.deloitte.tms.pl.security.model.SecurityUser;
import com.deloitte.tms.pl.security.model.impl.DefaultDept;
import com.deloitte.tms.pl.security.model.impl.UserDept;

/**
 * @since 2013-1-24
 * @author Jacky.gao
 */
public interface IDeptDao  extends IDao{
	public static final String BEAN_ID="ling2.deptDao";
	List<SecurityDept> loadUserDepts(String username);
	SecurityDept loadDeptById(String deptId);
	List<SecurityDept> loadDeptsByParentId(String parentId,String companyId, Map params);
	List<SecurityDept> findAllDepts();
	public String uniqueCheck(String id);
	public int countChildren(String parentId);
	List<SecurityPosition> loadPositionsByDeptId(String deptId);
	List<SecurityUser> loadUsersByDeptId(String deptId);
	List<UserDept> loadUserDeptsByDeptId(String deptId);
	public List<SecurityDept> findAllDeptsByFlag();
	public DefaultDept findUserDeptByUsername(String userName);
	public OrgPath findOrgPathByOrgId(String orgId);
	public void updateUserOrgMainByUser(String username, String orgIds);
	public void updateUserOrgMainByOrg(String usernames, String deptId);
	public void getOrgByRelationKeyId(String relationKeyId,
			ArrayList<String> needFields);
}
