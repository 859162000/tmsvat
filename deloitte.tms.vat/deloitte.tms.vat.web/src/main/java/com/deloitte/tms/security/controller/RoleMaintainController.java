package com.deloitte.tms.security.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.security.model.SecurityUser;
import com.deloitte.tms.pl.security.model.impl.DefaultRole;
import com.deloitte.tms.pl.security.model.impl.DefaultUser;
import com.deloitte.tms.pl.security.model.impl.RoleMember;
import com.deloitte.tms.pl.security.service.IRoleService;
import com.deloitte.tms.security.model.User;
import com.deloitte.tms.vat.controller.BaseController;
import com.deloitte.tms.vat.core.common.IdGenerator;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

@Controller
public class RoleMaintainController extends BaseController{

	@Resource
	IRoleService roleService;
	@RequestMapping(value = "/rolemaintain")
	public String indexPage() {
		return "security/rolemaintain";
	}

	
	@RequestMapping("rolemaintain/rolelist.do")
	public void rolelist(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String roleName = request.getParameter("rolename");
		JSONObject result=new JSONObject();
		if(AssertHelper.empty(roleName)){
			List<DefaultRole> list = roleService.loadAllRoles();
			result.put("total", list.size());
			result.put("rows",list);	
		}else{
			List<DefaultRole> list = roleService.getDefaultRoleByRoleName(roleName);
			result.put("total", list.size());
			result.put("rows",list);	
		}			
		 retJson(response,result);	 
	
	}

	@RequestMapping("rolemaintain/roleSave.do")
	public void saveRoleunite(@ModelAttribute("defaultRole") DefaultRole defaultRole,
			HttpServletResponse response) throws IOException {
		 if(AssertHelper.empty(defaultRole.getId())){
			 defaultRole.setId(IdGenerator.getUUID());	    	
			 roleService.save(defaultRole);
	    }else{
	    	roleService.update(defaultRole);	    	
	    }   
	   JSONObject result=new JSONObject();
	   result.put("success", true);
	   result.put("msg", getMessage("save.sucess"));	        
       retJson(response, result);
	}

	@RequestMapping("rolemaintain/roleDelete.do")
	public void deleteRoleunite(@RequestParam("id") String id,
			HttpServletResponse response) throws IOException {
		JSONObject result = new JSONObject();
		roleService.deleteDefaultRoleById(id);
		result.put("success", true);
		result.put("msg", getMessage("delete.success"));	        
		retJson(response, result);
	}

	

	@RequestMapping("rolemaintain/checkcode.do")
	public void codeCheck(@RequestParam("code") String code,
			HttpServletResponse response) throws IOException {
		JSONObject result = new JSONObject();
		Boolean repeat = checkUniqueCode(code);
		
		retJson(response, result);
	}

	@ResponseBody
	@RequestMapping("rolemaintain/getroleuser.do")
	public void getRoleUser(@RequestParam("roleid") String roleid,@RequestParam("pageNumber") String pageNumber,@RequestParam("pageSize") String pageSize,HttpServletResponse response) throws IOException {
		int pageNumberInt = Integer.valueOf(pageNumber);
		int pageSizeInt = Integer.valueOf(pageSize);
		DaoPage daoPage =roleService.loadRoleMemberByRoleIdAndType(roleid, "user", pageNumberInt, pageSizeInt);
		List<RoleMember> list = (List<RoleMember>) daoPage.getResult();
		List<DefaultUser> userList = new ArrayList<DefaultUser>();
		for(RoleMember rolemember:list){
			SecurityUser securityUser = rolemember.getUser();
			DefaultUser defaultUser = new DefaultUser();
			defaultUser.setUsername(securityUser.getUsername());
			defaultUser.setUserCode(securityUser.getUserCode());
			userList.add(defaultUser);
		}
		JSONObject result=new JSONObject();
		result.put("total", daoPage.getRecordCount());
		result.put("rows",userList);	
		retJson(response, result);
	}
	@RequestMapping("rolemaintain/roleuserDelete.do")
	public void deleteuserRole(@RequestParam("usernames") String usernames,HttpServletResponse response) throws IOException{
		JSONObject result=new JSONObject();
		String name[] = usernames.split(";");	
		List<String> list = Arrays.asList(name);
		roleService.deleteRoleMemberByBatchUserName(list);		
		String successMsg = getMessage("derelate.success");
		result.put("success", true);
		result.put("msg", successMsg);
		retJson(response, result);
	}

	@RequestMapping("rolemaintain/saverolefunction.do")
	public void saveRoleFrmfunction(@RequestParam("roleid") String roleid,
			@RequestParam("fnode") String fnode,
			@RequestParam("child") String child, HttpServletResponse response)
			throws IOException {
			JSONObject result=new JSONObject();
			String msg = "";		
			roleService.saveRoleFrmfunction(roleid, fnode, child);		
			result.put("success", true);
			result.put("msg", getMessage("save.sucess"));	        
			retJson(response, result);
	}

	@RequestMapping("rolemaintain/getrolefrmfunction.do")
	public void getRoleFrmfunction(@RequestParam("roleid") String roleid,
			HttpServletResponse response) throws IOException {
		JSONObject result = new JSONObject();				
		List<String> uuids = roleService.getFunctionIdsByRoleId(roleid);
		if(uuids!=null){
			result.put("frmuuis", uuids);
			result.put("success", true);
		}		
		retJson(response, result);
	}

	private Boolean checkUniqueCode(String code) {
		return true;
	}

}
