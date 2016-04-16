
/**    
 * Copyright (C),Deloitte
 * @FileName: OrgMgtController.java  
 * @Package: com.deloitte.tms.vat.org.controller  
 * @Description: //模块目的、功能描述  
 * @Author tigchen  
 * @Date 2016年3月15日 下午9:09:56  
 * @History: //修改记录
 * 〈author〉      〈time〉      〈version〉       〈desc〉
 * 修改人姓名            修改时间            版本号              描述   
*/  

package com.deloitte.tms.vat.org.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.deloitte.tms.base.cache.service.OrgPathProvider;
import com.deloitte.tms.base.masterdata.service.LegalEntityService;
import com.deloitte.tms.base.masterdata.service.TmsMdUsageLocalLegalService;
import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.security.model.SecurityDept;
import com.deloitte.tms.pl.security.model.impl.DefaultDept;
import com.deloitte.tms.pl.security.model.impl.DefaultDeptInUser;
import com.deloitte.tms.pl.security.model.impl.DefaultUser;
import com.deloitte.tms.pl.security.service.IDeptService;
import com.deloitte.tms.pl.security.service.IUserService;
import com.deloitte.tms.vat.controller.BaseController;
import com.deloitte.tms.vat.core.common.FunctionTreeNode;
import com.deloitte.tms.vat.core.common.TreeGenerator;


/**  
 *〈一句话功能简述〉
 * 功能详细描述
 * @author tigchen
 * @create 2016年3月15日 下午9:09:56 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Controller
public class OrgMgtController extends BaseController{
	
	@Resource
	IDeptService deptService;
	
	@Resource
	LegalEntityService legalEntityService;
	
	@Resource
	IUserService baseUser;
	
	@Resource
	TmsMdUsageLocalLegalService commonService;
	
	@Resource
	OrgPathProvider orgPathProvider;
	
	/**
	 * 
	 *〈一句话功能简述〉 
	 * 功能详细描述
	 * @param response
	 * @param request
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	@RequestMapping(value = "organizationMgt")
	public String indexPage() {
		
		return "org/organizationMgtN";
		
	}
	

	
	/**
	 * 
	 *〈一句话功能简述〉 
	 * 功能详细描述
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	@RequestMapping("orgMgt/createtree")
	public void createTree(HttpServletResponse response) {
		// 读取层次数据结果集列表
		try {
			
			List<SecurityDept> list = deptService.findAllDeptsByFlag();
			List<FunctionTreeNode> treeNodes = convertTreeNodeList(list);
			List<FunctionTreeNode> results = TreeGenerator.buildTree(treeNodes);	
			JSONArray jsonArray = JSONArray.fromObject(results);	
			retJsonArray(response, jsonArray);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	@RequestMapping("orgMgt/parentmenu.do")
	public void getParentmenu(HttpServletResponse response) throws IOException {/*
		List<DefaultUrl> list = deptService.
		List<Map<String, String>> reList = new ArrayList<Map<String,String>>();
		for(DefaultUrl defaultUrl:list){
			Map<String,String> map = new HashMap<String,String>();
			map.put("id", defaultUrl.getId());
			map.put("text", defaultUrl.getName());
			reList.add(map);
		}		
		JSONArray jsonArray = JSONArray.fromObject(reList);
		retJsonArray(response, jsonArray);
	
	*/}
	
	private List<FunctionTreeNode> convertTreeNodeList(
			List<SecurityDept> defaultDept) {
		List<FunctionTreeNode> nodes = null;

		if (defaultDept != null) {
			nodes = new ArrayList<FunctionTreeNode>();
			for (SecurityDept securityDept : defaultDept) {
				FunctionTreeNode node = convertTreeNode(securityDept);
				if (node != null) {
					nodes.add(node);
				}
			}
		}

		return nodes;
	}
	
	private FunctionTreeNode convertTreeNode(SecurityDept securityDept) {
		DefaultDept defaultDept=(DefaultDept)securityDept;
		FunctionTreeNode node = null;
		if (defaultDept != null) {
			node = new FunctionTreeNode();
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
	
	/**
	 * 
	 * upate 功能
	 * 功能详细描述
	 * @param url
	 * @param response
	 * @throws Exception 
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	
	@RequestMapping("orgMgt/update.do")
	public void updateOrgFunction(
			@ModelAttribute("defaultDept") DefaultDeptInUser defaultDept,
			HttpServletResponse response)  {
		JSONObject result = new JSONObject();
		
		
		try {
			
			if(AssertHelper.empty(defaultDept.getId())){
				
				System.out.println("OrgMgtController>saveOrgFunction>defaultDept no uuid, should throw exception: defaultDept:"+defaultDept);
				result.put("fail", true);
				result.put("errorMsg", "now department uuid is null"+defaultDept);
				retJson(response, result);
				
			}else{
				System.out.println("OrgMgtController>updateOrgFunction>defaultDept id:"+defaultDept.getId()+";parentId:"+defaultDept.getParentId());
				System.out.println("OrgMgtController>updateOrgFunction>defaultDept has uuid, will update: "+defaultDept);
				
				deptService.updateOrgWithUsers(defaultDept);
				
				//deptService.update(defaultDept);
				result.put("success", true);
				result.put("msg", getMessage("save.sucess"));
				retJson(response, result);
			}
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				result.put("msg", getMessage("save.fail"));
				//retJson(response, result);
				e.printStackTrace();
			}
	}
	
	
	/**
	 * 
	 * create new 功能
	 * 功能详细描述
	 * @param url
	 * @param response
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	
	@RequestMapping("orgMgt/create.do")
	public void createOrgFunction(
			@ModelAttribute("defaultDept") DefaultDept defaultDept,
			HttpServletResponse response)  {
		JSONObject result = new JSONObject();
		try {
			System.out.println("------saveOrgFunction----defaultDept.getParentId():"+defaultDept.getParentId()+"; defaultDept.getId():"+defaultDept.getId());		
			
			String parentId = defaultDept.getParentId();
			
			if(AssertHelper.empty(parentId)){
				//没有父节点，新建的是top level department
				defaultDept.setParentId(null);
				defaultDept.setId(null);
				//TODO 判断是否已存在同名的org
				//
				//
				deptService.save(defaultDept);
			}else{
				//有父节点， 新建的部门是父节点的子部门
				//defaultDept.setParentId(parentId);
				defaultDept.setId(null);
				deptService.save(defaultDept);
			}
			result.put("success", true);
			result.put("msg", getMessage("save.sucess"));
			retJson(response, result);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				result.put("msg", getMessage("save.fail"));
				//retJson(response, result);
				e.printStackTrace();
			}finally{
				
				orgPathProvider.execRefreshOrgPath();
			}
	}
	
	/**
	 * 
	 *〈一句话功能简述〉 
	 * 功能详细描述
	 * @param uuid
	 * @param response
	 * @throws IOException
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	@RequestMapping("orgMgt/delete.do")
	public void deleteOrgFunction(@RequestParam("uuids") String uuids,
			HttpServletResponse response) {

		try{
		System.out.println("deleteOrgFunction---being");
		JSONObject result=new JSONObject();
		
		this.legalEntityService.delById(getListByStr(uuids), DefaultDept.class);
			
		String successMsg = getMessage("delete.success");
		result.put("success", "true");
		result.put("msg", successMsg);		
		retJson(response, result);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			orgPathProvider.execRefreshOrgPath();
		}
	}
	
	
	/**
	 * 
	 *list all base_user
	 * 功能详细描述
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 * @author tigchen
	 */
		@RequestMapping("orgMgt/getAllUser.do")
		public void getAllUser(HttpServletResponse response){
			
			
			try{
				
				ArrayList<String> needFields = new ArrayList<String>();
				/*needFields.add("id");
				needFields.add("legalEntityName");*/
				needFields.add("username");
			
			    List<Map<String, Object>> results = this.commonService.listByFilter2Map(DefaultUser.class.getName(),
			    		null , needFields);
			    
			    JSONArray jsonArray = JSONArray.fromObject(results);
			    
			    System.out.println("OrgMgtController > listLegalFree return: "+jsonArray);
			   
			    this.retJsonArray(response, jsonArray);
			    
			    System.out.println("---------------");
			    
			}catch(Exception e){
				
				System.out.println(e.getMessage());
				this.returnFail(response);
			}
		}
		
		
		
		@RequestMapping("orgMgt/loadUsernamesByOrgId.do")
		public void loadUsernamesByOrgId(HttpServletResponse response, String orgId){			
			
			try{			
			
			    //List<Map<String, Object>> results = this.baseUser.loadUsernamesByOrgId(orgId);			    		
			   
				ArrayList<String> result = this.deptService.loadUsernamesByOrgId(orgId);
			   
			    JSONArray jsonArray = JSONArray.fromObject(result);
			    
			    System.out.println("OrgMgtController > loadUsernamesByOrgId return: "+jsonArray);
			   
			    this.retJsonArray(response, jsonArray);
			    
			    System.out.println("---------------");
			    
			}catch(Exception e){
				
				System.out.println(e.getMessage());
			}
		}
	
	
	public ArrayList<String> getListByStr(String s){
		System.out.println("getListByStr()-----------");
		ArrayList<String> list = new ArrayList<String>();		
		
		String temp="";
		if(s!=null && s.endsWith("xfxTiger")){
			temp=s.substring(0, s.lastIndexOf("xfxTiger"));
		}else{
			temp=s;
		}
		System.out.println("OrgMgtController>getListByStr:"+temp);
		for(String str : temp.split("xfxTiger")){
			if(str!=null && !"".equals(str.trim())){
				list.add(str);
			}
		}
		
		return list;
	}

}
