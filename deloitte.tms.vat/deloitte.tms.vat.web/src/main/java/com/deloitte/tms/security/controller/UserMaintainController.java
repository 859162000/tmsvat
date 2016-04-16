package com.deloitte.tms.security.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.commons.lang.math.RandomUtils;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.core.commons.utils.PageUtils;
import com.deloitte.tms.pl.security.model.impl.DefaultUser;
import com.deloitte.tms.pl.security.model.impl.VroleUser;
import com.deloitte.tms.pl.security.service.IDeptService;
import com.deloitte.tms.pl.security.service.IRoleService;
import com.deloitte.tms.pl.security.service.IUserService;
import com.deloitte.tms.vat.controller.BaseController;
import com.deloitte.tms.vat.core.common.IdGenerator;
import com.deloitte.tms.vat.core.common.JsonDateValueProcessor;


@Controller
public class UserMaintainController extends BaseController {

	@Resource
	private IUserService userService;
	@Resource
	IRoleService roleService;
	
	@Resource
	IDeptService deptService;
	
	@Resource(name="ling2.passwordEncoder")
	private PasswordEncoder passwordEncoder;
	
	@RequestMapping(value = "/usermaintain")
	public String indexPage() {
		return "security/usermaintain";
	}

	@RequestMapping("usermaintain/search.do")
	public void search(@RequestParam Map<String, Object> parameter,
			HttpServletResponse response) throws IOException {
		DaoPage daoPage = userService.loadPageUsers(parameter,
				PageUtils.getPageNumber(parameter),
				PageUtils.getPageSize(parameter));
		List<DefaultUser> list = (List<DefaultUser>) daoPage.getResult();
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class,
				new JsonDateValueProcessor("yyyy-MM-dd"));
		JSONArray jsonArray = JSONArray.fromObject(list, jsonConfig);
		JSONObject result = new JSONObject();
		result.put("total", daoPage.getTotal());
		result.put("rows", jsonArray);
		retJson(response, result);	
	}

	@RequestMapping("usermaintain/save.do")
	public void save(@ModelAttribute("defaultUser") DefaultUser user,
			HttpServletResponse response) throws IOException {
		if (AssertHelper.empty(user.getUserId())) {
			user.setUserId(IdGenerator.getUUID());
			if(user.getPassword()==null){
				user.setPassword("111111");
			}
			int salt=RandomUtils.nextInt(1000);
			user.setPassword(passwordEncoder.encodePassword(user.getPassword(), salt));
			user.setSalt(String.valueOf(salt));
			userService.save(user);
		} else {
			DefaultUser defaultUser = userService.getDefaultUserByUserId(user
					.getUserId());
			defaultUser.setUserCode(user.getUserCode());
			defaultUser.setEmail(user.getEmail());
			defaultUser.setCname(user.getCname());
			defaultUser.setEname(user.getEname());
			defaultUser.setMobile(user.getMobile());
			defaultUser.setRemark(user.getRemark());
			defaultUser.setEffectDate(user.getEffectDate());
			defaultUser.setQuitDate(user.getQuitDate());
			String salt=defaultUser.getSalt();
			if(defaultUser.getSalt()==null){
				salt=String.valueOf(RandomUtils.nextInt(1000));
			}
			user.setPassword(passwordEncoder.encodePassword(user.getPassword(), salt));
			user.setSalt(String.valueOf(salt));
//			defaultUser.setPassword("123456");
//			defaultUser.setSalt("123456");
			userService.update(defaultUser);
		}

		JSONObject result = new JSONObject();
		result.put("success", true);
		result.put("msg", getMessage("save.sucess"));
		retJson(response, result);
	}

	@RequestMapping("usermaintain/delete.do")
	public void deleteAppuser(@RequestParam("uuid") String uuid,
			HttpServletResponse response) throws IOException {
		JSONObject result = new JSONObject();
		String successMsg = "删除成功！";
		result.put("success", "true");
		result.put("msg", successMsg);

		retJson(response, result);
	}

	// @ResponseBody
	@RequestMapping("usermaintain/getuserbyid.do")
	public void fingUserById(@RequestParam("userId") String userId,
			HttpServletResponse response) throws IOException {
		/*DefaultUser defaultUser = (DefaultUser) userService.get(
				DefaultUser.class, userId);*/
		DefaultUser defaultUser = userService.getDefaultUserByUserId(userId);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class,
				new JsonDateValueProcessor("yyyy-MM-dd"));
		JSONObject jsonObject = JSONObject.fromObject(defaultUser, jsonConfig);
		retJson(response, jsonObject);

	}

	// @ResponseBody
	@RequestMapping("usermaintain/getuserrole.do")
	public void getUserRole(@RequestParam("username") String username,
			HttpServletResponse response) throws IOException {
		List<VroleUser> results = roleService.getVroleUsersByUserName(username);
		JSONObject result = new JSONObject();
		result.put("total", results.size());
		result.put("rows", results);
		retJson(response, result);

	}

	@RequestMapping("usermaintain/saveuserrole.do")
	public void saveUserRole(@RequestParam("username") String username,
			@RequestParam("roleids") String roleids,
			HttpServletResponse response) throws IOException {
		JSONObject result = new JSONObject();		
		roleService.saveUserRole(username, roleids);	
		String msg  = getMessage("save.sucess");
		result.put("success", true);
		result.put("msg", msg);		
		retJson(response, result);
	}
	
	
	@RequestMapping("usermaintain/getuserorg.do")
	public void getUserOrg(@RequestParam("username") String username,
			HttpServletResponse response){
		
		try{
			
			JSONArray jsonArray = this.deptService.getOrgTree2Sel4User(response, username);
		    
		    System.out.println("------getUserOrg will return: "+jsonArray);
		    
		    if(jsonArray==null){
		    	return;
		    }else{
		    	retJsonArray(response, jsonArray);
		    }
		    
		    
		}catch(Exception e){
			System.out.println("getUserOrg------------------exception as below:");
			System.out.println("getUserOrg------------------exception:"+e.getMessage());
		}
	

	}
	
	
	
	
	@RequestMapping("usermaintain/updateUserOrg.do")
	public void updateUserOrg(String username,String orgIds2,
			HttpServletResponse response){
		
		try{
			System.out.println("controller UserMaintainController get: username:"+username+";orgIds2:"+orgIds2);
			
			this.deptService.updateUserOrgMainByUser(username, orgIds2);
		    
		    this.returnOk(response, "设置组织成功");
		    
		}catch(Exception e){
			System.out.println("updateUserOrg------------------exception as below:");
			System.out.println("updateUserOrg------------------exception:"+e.getMessage());
			this.returnFail(response, "保存异常");
		}
	

	}

}
