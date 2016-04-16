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

import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.dictionary.model.impl.BaseCstegory;
import com.deloitte.tms.pl.dictionary.service.DicitionsryManagService;
import com.deloitte.tms.vat.controller.BaseController;
import com.deloitte.tms.vat.core.common.FunctionTreeNode;
import com.deloitte.tms.vat.core.common.IdGenerator;
import com.deloitte.tms.vat.core.common.TreeGenerator;
@Controller
@RequestMapping(value="dictionaryManag")
public class DictionaryManagController extends BaseController{
	@Resource(name="dictionarymanagserviceimpl")
	DicitionsryManagService  dictionarymanagserviceimpl;
	  /**
	   * 跳转大小类页面
	   * @return
	   */
	  @RequestMapping(value="/dictionaryManager")
	public String toDictionaryManager(){
		return "dictionary/dictionaryManag";
	}
	  
	  /**
	   * 读取树结构数据
	   * @param response
	   */
		@RequestMapping("functionMaintain/createtree.do")
		public void createTree(HttpServletResponse response) {
			// 读取层次数据结果集列表
			try {
				List<BaseCstegory> list = dictionarymanagserviceimpl.loadAllUrls();//加载数据
				List<FunctionTreeNode> treeNodes = convertTreeNodeList(list);
				List<FunctionTreeNode> results = TreeGenerator.buildTree(treeNodes);	
				JSONArray jsonArray = JSONArray.fromObject(results);	
				retJsonArray(response, jsonArray);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		}
	/**
	 * 解析节点
	 * @param defaultUrls
	 * @return
	 */
		private List<FunctionTreeNode> convertTreeNodeList(
				List<BaseCstegory> defaultUrls) {
			List<FunctionTreeNode> nodes = null;
			if (defaultUrls != null) {
				nodes = new ArrayList<FunctionTreeNode>();
				for (BaseCstegory defaultUrl : defaultUrls) {
					FunctionTreeNode node = convertTreeNode(defaultUrl);
					if (node != null) {
						nodes.add(node);
					}
				}
			}

			return nodes;
		}
		/**
		 * 
		 * @param defaultUrl
		 * @return
		 */
		private FunctionTreeNode convertTreeNode(BaseCstegory defaultUrl) {
			FunctionTreeNode node = null;
			if (defaultUrl != null) {
				node = new FunctionTreeNode();
				node.setId(defaultUrl.getId());//节点id
				node.setText(defaultUrl.getLabel());//名称
				node.setIconCls(defaultUrl.getCode());//代码
				node.setAction(String.valueOf(defaultUrl.getSortOrder()));//排序
				node.setStrTarget(defaultUrl.getDescription());//描述
				node.setDescription(String.valueOf(defaultUrl.getLevel()));//层级
			    node.setPid(defaultUrl.getParentId());//父节点id
			    if(defaultUrl.getParentId()==null){
			    	node.setNavigationFlag("1");
			    }else {
			    	node.setNavigationFlag("0");
				}
			    
				Map<String, Object> map = new HashMap<String, Object>();

				node.setAttributes(map);
			}
			return node;
		}

		/**
		 * 增加修改
		 * @param request
		 * @param response
		 * @throws IOException
		 */
		@RequestMapping("functionMaintain/save.do")
		public void saveFrmFunction(
				@ModelAttribute("defaultUrl") BaseCstegory url,String forNavigation,String parentstras,
				HttpServletResponse response)  {

			JSONObject result = new JSONObject();
			
			if("0".equals(forNavigation)){//不是根节点
			try {
				if(AssertHelper.empty(url.getId())||"add".equals(parentstras)){
					url.setParentId(url.getId());
					url.setId(IdGenerator.getUUID());
					
					dictionarymanagserviceimpl.save(url);
				}else{
					url.setId(url.getId());
					dictionarymanagserviceimpl.update(url);
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
			}else if("1".equals(forNavigation)){
				try {
					if(AssertHelper.empty(url.getId())||"add".equals(parentstras)){
						url.setId(IdGenerator.getUUID());
						dictionarymanagserviceimpl.save(url);
					}else{
						dictionarymanagserviceimpl.update(url);
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
			
			
			
		}
		
		@RequestMapping("functionMaintain/delete.do")
		public void deleteFrmFunction(@RequestParam("uuid") String uuid,
				HttpServletResponse response) throws IOException {
		
			JSONObject result=new JSONObject();	
			dictionarymanagserviceimpl.deleteDefaultUrlById(uuid);		
			String successMsg = getMessage("delete.success");
			result.put("success", "true");
			result.put("msg", successMsg);		
			retJson(response, result);
			
		}

		
		

}
