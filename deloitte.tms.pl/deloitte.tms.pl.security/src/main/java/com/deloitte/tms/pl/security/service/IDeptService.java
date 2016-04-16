package com.deloitte.tms.pl.security.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.deloitte.tms.pl.core.service.IService;
import com.deloitte.tms.pl.security.model.SecurityDept;
import com.deloitte.tms.pl.security.model.SecurityPosition;
import com.deloitte.tms.pl.security.model.SecurityUser;
import com.deloitte.tms.pl.security.model.impl.DefaultDeptInUser;

/**
 * @since 2013-1-24
 * @author Jacky.gao
 */
public interface IDeptService extends IService{
	public static final String BEAN_ID="ling2.deptService";
	SecurityDept newDeptInstance(String deptId);
	List<SecurityDept> loadUserDepts(String username);
	SecurityDept loadUserDefaultDept(String username);
	SecurityDept loadDeptById(String deptId);
	List<SecurityDept> loadDeptsByParentId(String parentId,String companyId, Map params);
	public String uniqueCheck(String id);
	public int countChildren(String parentId);
	List<SecurityPosition> loadPositionsByDeptId(String deptId);
	List<SecurityUser> loadUsersByDeptId(String deptId);
	public List<SecurityDept> findAllDepts();
	public List<SecurityDept> findAllDeptsByFlag();
	public JSONArray getOrgTree2Sel4User(HttpServletResponse response, String username);
	public void updateUserOrgMainByUser(String username, String orgIds2);
	public void updateOrgWithUsers(DefaultDeptInUser defaultDeptInUser) throws Exception;
	public ArrayList<String> loadUsernamesByOrgId(String orgId);
}
