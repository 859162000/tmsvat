package com.deloitte.tms.pl.security.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.core.dao.IDao;
import com.deloitte.tms.pl.core.service.impl.BaseService;
import com.deloitte.tms.pl.security.dao.IDeptDao;
import com.deloitte.tms.pl.security.model.SecurityDept;
import com.deloitte.tms.pl.security.model.SecurityPosition;
import com.deloitte.tms.pl.security.model.SecurityUser;
import com.deloitte.tms.pl.security.model.impl.DefaultDept;
import com.deloitte.tms.pl.security.model.impl.DefaultDeptInUser;
import com.deloitte.tms.pl.security.model.impl.UserDept;
import com.deloitte.tms.pl.security.service.IDeptService;
import com.deloitte.tms.pl.security.utils.FunctionTreeNodeInPL;
import com.deloitte.tms.pl.security.utils.TreeGeneratorInPL;



/**
 * @since 2013-1-24
 * @author Jacky.gao
 */
@Component(IDeptService.BEAN_ID)
public class DefaultDeptService extends BaseService implements IDeptService {
	@Resource
	IDeptDao deptDao;
	public SecurityDept newDeptInstance(String deptId) {
		return new DefaultDept(deptId);
	}
	public List<SecurityDept> loadUserDepts(String username) {
		return deptDao.loadUserDepts(username);
	}
	public SecurityDept loadDeptById(String deptId) {
		return deptDao.loadDeptById(deptId);
	}
	private void buildParentDept(SecurityDept dept){
		DefaultDept defaultDept=(DefaultDept)dept;
		if(StringUtils.isNotEmpty(defaultDept.getParentId())){
			SecurityDept parentDept=this.loadDeptById(defaultDept.getParentId());
			defaultDept.setParent(parentDept);
			this.buildParentDept(parentDept);
		}
	}
	public List<SecurityDept> loadDeptsByParentId(String parentId,String companyId,Map params) {
		return deptDao.loadDeptsByParentId(parentId, companyId, params);
	}
	public String uniqueCheck(String id){
		return deptDao.uniqueCheck(id);
	}
	
	public int countChildren(String parentId) {
		return deptDao.countChildren(parentId);
	}
	@Override
	public IDao getDao() {
		return deptDao;
	}
	@Override
	public List<SecurityPosition> loadPositionsByDeptId(String deptId) {
		return deptDao.loadPositionsByDeptId(deptId);
	}
	@Override
	public List<SecurityUser> loadUsersByDeptId(String deptId) {
		return deptDao.loadUsersByDeptId(deptId);
	}
	@Override
	public List<SecurityDept> findAllDepts() {
		return deptDao.findAllDepts();
	}
	@Override
	public SecurityDept loadUserDefaultDept(String username) {
		List<SecurityDept> depts=loadUserDepts(username);
		if(depts.size()>0){
			return depts.get(0);
		}
		return null;
	}
	
	/**
	 * @author tigchen
	 * @return  
	 * @see com.deloitte.tms.pl.security.service.IDeptService#findAllDepts()
	 */
	@Override
	public List<SecurityDept> findAllDeptsByFlag() {
		return deptDao.findAllDeptsByFlag();
	}
	
	
	//@Override
	public List<SecurityDept> getOrgByUser(String username) {
		List<SecurityDept> depts=loadUserDepts(username);
		if(depts.size()>0){
			
			return depts;
			//return depts.get(0);
		}
		return null;
	}
	
	
	
	//@Override
	public JSONArray getOrgTree2Sel4Legal(HttpServletResponse response, Map para, ArrayList<String> needFields) {
		// 读取层次数据结果集列表
		try {
			
			String relationKeyId="";
			
			List<SecurityDept> list = this.findAllDeptsByFlag();
			
			if(list==null || list.size()<1){
				list= new ArrayList<SecurityDept>();
			}
			
			this.deptDao.getOrgByRelationKeyId(relationKeyId, needFields);
			//this.getOrgByRelationKeyId(relationKeyId);
			List<SecurityDept> subList = getOrgByUser( relationKeyId);
			if(subList==null || subList.size()<1){
				subList= new ArrayList<SecurityDept>();
			}
			
			HashMap<String, Object> att=new HashMap<String, Object>();
			att.put("username", relationKeyId);
			
			System.out.println("all size:"+list.size()+"; subList size:"+subList.size());
			List<FunctionTreeNodeInPL> treeNodes = convertTreeNodeList2Sel(list, subList, att);
			//List<FunctionTreeNodeInPL> results = TreeGeneratorInPL.buildTree(treeNodes);
			System.out.println("treeNodes size:"+treeNodes.size());
			List<FunctionTreeNodeInPL> results = TreeGeneratorInPL.buildTreeUseKey(treeNodes, "id");
			System.out.println("treeNodes in tree size:"+results.size());
			
			JSONArray jsonArray = JSONArray.fromObject(results);	
			
			return jsonArray;
			//retJsonArray(response, jsonArray);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;		
	}
	
	
	
	/**
	 * @author tigchen
	 *〈一句话功能简述〉 
	 * 功能详细描述
	 * @param response
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	
	@Override
	public JSONArray getOrgTree2Sel4User(HttpServletResponse response, String username) {
		// 读取层次数据结果集列表
		try {
			
			List<SecurityDept> list = this.findAllDeptsByFlag();
			
			if(list==null || list.size()<1){
				list= new ArrayList<SecurityDept>();
			}
			
			List<SecurityDept> subList = getOrgByUser( username);
			if(subList==null || subList.size()<1){
				subList= new ArrayList<SecurityDept>();
			}
			
			HashMap<String, Object> att=new HashMap<String, Object>();
			att.put("username", username);
			
			System.out.println("all size:"+list.size()+"; subList size:"+subList.size());
			List<FunctionTreeNodeInPL> treeNodes = convertTreeNodeList2Sel(list, subList, att);
			//List<FunctionTreeNodeInPL> results = TreeGeneratorInPL.buildTree(treeNodes);
			System.out.println("treeNodes size:"+treeNodes.size());
			List<FunctionTreeNodeInPL> results = TreeGeneratorInPL.buildTreeUseKey(treeNodes, "id");
			System.out.println("treeNodes in tree size:"+results.size());
			
			JSONArray jsonArray = JSONArray.fromObject(results);	
			
			return jsonArray;
			//retJsonArray(response, jsonArray);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;		
	}
	
	
	private List<FunctionTreeNodeInPL> convertTreeNodeList2Sel(
			List<SecurityDept> defaultDept, List<SecurityDept> subList, HashMap<String, Object> att) {
		List<FunctionTreeNodeInPL> nodes = null;

		if (defaultDept != null) {
			nodes = new ArrayList<FunctionTreeNodeInPL>();
			for (SecurityDept securityDept : defaultDept) {
				FunctionTreeNodeInPL node = convertTreeNode2Sel(securityDept , subList , att);
				if (node != null) {
					nodes.add(node);
				}
			}
		}

		return nodes;
	}
	
	
	
	private FunctionTreeNodeInPL convertTreeNode2Sel(SecurityDept securityDept,  List<SecurityDept> subList, HashMap<String, Object> att) {
		DefaultDept defaultDept=(DefaultDept)securityDept;
		FunctionTreeNodeInPL node = null;
		if (defaultDept != null) {
			node = new FunctionTreeNodeInPL();
			node.setId(defaultDept.getId());
			
			boolean contain=false;
			for(SecurityDept s : subList){
				if(securityDept.getId().equalsIgnoreCase(s.getId())){
					contain=true;
					break;
				}
			}
			
			if(contain){
				node.setChecked(true);
			}else{
				node.setChecked(false);
			}
			
			node.setText(defaultDept.getOrgName());
			node.setDescription(defaultDept.getDes());
			node.setOrgCode(defaultDept.getOrgCode());	
		    node.setPid(defaultDept.getParentId());
		    
		    node.setVirtual(defaultDept.getVirtual());
		    node.setBizOrgCode(defaultDept.getBizOrgCode());
		    node.setOrgType(defaultDept.getOrgType());
		    
		    /*
		     * virtual
bizOrgCode
orgType
		     */
		    
		    System.out.println("convertTreeNode: "+node);
		    
		    //node.setDescription();
		  /*  if(defaultUrl.isForNavigation()){
		    	node.setNavigationFlag("1");
		    }else {
		    	node.setNavigationFlag("0");
			}*/
		  
			

			Map<String, Object> map = new HashMap<String, Object>();

			map.putAll(att);
			node.setAttributes(map);
		}
		return node;
	}
	
	
	
	
	private List<FunctionTreeNodeInPL> convertTreeNodeList(
			List<SecurityDept> defaultDept) {
		List<FunctionTreeNodeInPL> nodes = null;

		if (defaultDept != null) {
			nodes = new ArrayList<FunctionTreeNodeInPL>();
			for (SecurityDept securityDept : defaultDept) {
				FunctionTreeNodeInPL node = convertTreeNode(securityDept);
				if (node != null) {
					nodes.add(node);
				}
			}
		}

		return nodes;
	}
	
	
	
	private FunctionTreeNodeInPL convertTreeNode(SecurityDept securityDept) {
		DefaultDept defaultDept=(DefaultDept)securityDept;
		FunctionTreeNodeInPL node = null;
		if (defaultDept != null) {
			node = new FunctionTreeNodeInPL();
			node.setId(defaultDept.getId());
			node.setChecked(false);
			node.setText(defaultDept.getOrgName());
			node.setDescription(defaultDept.getDes());
			node.setOrgCode(defaultDept.getOrgCode());	
		    node.setPid(defaultDept.getParentId());
		    
		    node.setVirtual(defaultDept.getVirtual());
		    node.setBizOrgCode(defaultDept.getBizOrgCode());
		    node.setOrgType(defaultDept.getOrgType());
		    
		    /*
		     * virtual
bizOrgCode
orgType
		     */
		    
		    System.out.println("convertTreeNode: "+node);
		    
		    //node.setDescription();
		  /*  if(defaultUrl.isForNavigation()){
		    	node.setNavigationFlag("1");
		    }else {
		    	node.setNavigationFlag("0");
			}*/
		  
			

			Map<String, Object> map = new HashMap<String, Object>();

			node.setAttributes(map);
		}
		return node;
	}
	
	@Override
	public void updateUserOrgMainByUser(String username, String orgIds) {
		// TODO Auto-generated method stub
		this.deptDao.updateUserOrgMainByUser(username, orgIds);
	}
	
	
	@Override
	public void updateOrgWithUsers(DefaultDeptInUser defaultDeptInUser) throws Exception{
		

		System.out.println("------updateOrgWithUsers----defaultDept.getParentId():"+defaultDeptInUser.getParentId()+"; defaultDept.getId():"+defaultDeptInUser.getId());		
	
		this.update(new DefaultDept(defaultDeptInUser));
		
		String orgId=defaultDeptInUser.getId();
		
		String allUserName = defaultDeptInUser.getAllUsername();
		System.out.println("--updateOrgWithUsers allUserName:"+allUserName);
		this.deptDao.updateUserOrgMainByOrg(allUserName, orgId);
		
	/*	if(AssertHelper.empty(allUserName)){
			
			//todo update all UserDept which orgId=orgId
			
			
		}else{
			
			String[] usernames = allUserName.split(",");
			
			for(String username : usernames){
				
				UserDept userDept = new UserDept(username, orgId);//String deptId; username
				
				System.out.println("--updateOrgWithUsers will save userDept:"+userDept);
				this.save(userDept);
			}
			
		}
		*/

		
	}
	
	
	@Override
	//public List<Map<String, Object>> loadUsernamesByOrgId(String orgId) {	
	public ArrayList<String> loadUsernamesByOrgId(String orgId) {
		Collection<SecurityUser> userList = this.loadUsersByDeptId(orgId);
		
		//ArrayList<Map<String, Object>> usernames=new ArrayList<Map<String, Object>>();
		ArrayList<String> usernames=new ArrayList<String>();
		
		for(SecurityUser user  : userList){
		
			//HashMap map = new HashMap();
			//map.put("username",  user.getUsername());
			
			usernames.add(   user.getUsername()       );
		}
		
		return usernames;
	}

}