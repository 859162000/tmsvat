package com.deloitte.tms.security.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.security.model.impl.DefaultUrl;
import com.deloitte.tms.pl.security.service.IUrlService;
import com.deloitte.tms.security.model.User;
import com.deloitte.tms.vat.controller.BaseController;
import com.deloitte.tms.vat.core.common.IdGenerator;
import com.deloitte.tms.vat.core.common.TreeGenerator;
import com.deloitte.tms.vat.core.common.FunctionTreeNode;

@Controller
public class FunctionMaintainController extends BaseController{
	
	@Resource
	IUrlService urlService;
	@RequestMapping(value = "/functionmaintain")
	public String indexPage() {
		return "security/functionmaintain";
	}

	@RequestMapping("functionMaintain/createtree.do")
	public void createTree(HttpServletResponse response) {
		// 读取层次数据结果集列表
		try {
			List<DefaultUrl> list = urlService.loadAllUrls();
			List<FunctionTreeNode> treeNodes = convertTreeNodeList(list);
			List<FunctionTreeNode> results = TreeGenerator.buildTree(treeNodes);	
			JSONArray jsonArray = JSONArray.fromObject(results);	
			retJsonArray(response, jsonArray);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	@RequestMapping("functionMaintain/parentmenu.do")
	public void getParentmenu(HttpServletResponse response) throws IOException {
		List<DefaultUrl> list = urlService.loadAllUrls();
		List<Map<String, String>> reList = new ArrayList<Map<String,String>>();
		for(DefaultUrl defaultUrl:list){
			Map<String,String> map = new HashMap<String,String>();
			map.put("id", defaultUrl.getId());
			map.put("text", defaultUrl.getName());
			reList.add(map);
		}		
		JSONArray jsonArray = JSONArray.fromObject(reList);
		retJsonArray(response, jsonArray);
	
	}

	@RequestMapping("functionMaintain/save.do")
	public void saveFrmFunction(
			@ModelAttribute("defaultUrl") DefaultUrl url,
			HttpServletResponse response)  {
		JSONObject result = new JSONObject();
		try {
			if(AssertHelper.empty(url.getId())){
				url.setId(IdGenerator.getUUID());
				urlService.save(url);
			}else{
				urlService.update(url);
			}
			result.put("success", true);
			result.put("msg", getMessage("save.sucess"));
			retJson(response, result);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				result.put("msg", getMessage("save.fail"));
				//retJson(response, result);
				e.printStackTrace();
			}
	}

	@RequestMapping("functionMaintain/delete.do")
	public void deleteFrmFunction(@RequestParam("uuid") String uuid,
			HttpServletResponse response) throws IOException {
	
		JSONObject result=new JSONObject();	
		urlService.deleteDefaultUrlById(uuid);		
		String successMsg = getMessage("delete.success");
		result.put("success", "true");
		result.put("msg", successMsg);		
		retJson(response, result);
		
	}

	@RequestMapping("functionMaintain/savefrmaction.do")
	public void saveFrmAction(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		JSONObject result = new JSONObject();
		String msg = "";
		Boolean repeat = false;
		String uuid = request.getParameter("uuid");
		String frmfunctionmenuuuid = request
				.getParameter("frmfunctionmenuuuid");
		String id = request.getParameter("id");
		String label = request.getParameter("lable");
		String desc = request.getParameter("description");
		
		retJson(response, result);
	}

	@ResponseBody
	@RequestMapping("functionMaintain/getfrmaction.do")
	public Map<String, Object> getFrmactions(
			@RequestParam("menuuuid") String menuuuid) {
		
		return null;
	}

	private List<FunctionTreeNode> convertTreeNodeList(
			List<DefaultUrl> defaultUrls) {
		List<FunctionTreeNode> nodes = null;

		if (defaultUrls != null) {
			nodes = new ArrayList<FunctionTreeNode>();
			for (DefaultUrl defaultUrl : defaultUrls) {
				FunctionTreeNode node = convertTreeNode(defaultUrl);
				if (node != null) {
					nodes.add(node);
				}
			}
		}

		return nodes;
	}

	private FunctionTreeNode convertTreeNode(DefaultUrl defaultUrl) {
		FunctionTreeNode node = null;
		if (defaultUrl != null) {
			node = new FunctionTreeNode();
			node.setId(defaultUrl.getId());
			node.setChecked(false);
			node.setText(defaultUrl.getName());
			node.setIconCls(defaultUrl.getIcon());
			node.setAction(defaultUrl.getUrl());
			node.setStrTarget(defaultUrl.getTarget());
			node.setDescription(defaultUrl.getDesc());
			node.setIndex(defaultUrl.getOrder().toString());			
		    node.setPid(defaultUrl.getParentId());
		    if(defaultUrl.isForNavigation()){
		    	node.setNavigationFlag("1");
		    }else {
		    	node.setNavigationFlag("0");
			}
		  
			

			Map<String, Object> map = new HashMap<String, Object>();

			node.setAttributes(map);
		}
		return node;
	}

	private Boolean checkUniqueFrmactionId(String id) {
		
			return false;
	}

}
