package com.deloitte.tms.base.masterdata.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.deloitte.tms.base.masterdata.model.TmsMdLegalEnablePrint;
import com.deloitte.tms.base.masterdata.model.TmsMdLegalEnablePrintInParam;
import com.deloitte.tms.base.masterdata.model.TmsMdLegalEntity;
import com.deloitte.tms.base.masterdata.model.TmsMdLegalEntityInParam;
import com.deloitte.tms.base.masterdata.model.TmsMdUsageLocalLegal;
import com.deloitte.tms.base.masterdata.model.TmsMdUsageLocalLegalInParam;
import com.deloitte.tms.base.masterdata.service.TmsMdLegalEnablePrintService;
import com.deloitte.tms.base.masterdata.service.TmsMdUsageLocalLegalService;
import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.core.commons.utils.PageUtils;
import com.deloitte.tms.pl.core.commons.utils.reflect.ReflectUtils;
import com.deloitte.tms.vat.controller.BaseController;
import com.deloitte.tms.vat.core.common.FunctionTreeNode;
import com.deloitte.tms.vat.core.common.TreeGenerator;


@Controller
public class UsageLocalMgtController extends BaseController {
	@Resource
	TmsMdUsageLocalLegalService tmsMdUsageLocalLegalService;
	
	
	/**
	 * 
	 * @return
	 * @author tigchen
	 */
	@RequestMapping("usageLocalLegalMgt.do")
	public String goIndexPage(){
		
		return "base/masterdata/usageLocalLegal";
	}
	
	
	
	/**
	 * 
	 *list no used legal and equipment divided
	 * 功能详细描述
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 * @author tigchen
	 */
		@RequestMapping("useLocalLegal/listLegalFree.do")
		public void listLegalFree(HttpServletResponse response){
			
			
			try{
				
				ArrayList<String> needFields = new ArrayList<String>();
				needFields.add("id");
				needFields.add("legalEntityName");
						
			    List<Map<String, Object>> results = this.tmsMdUsageLocalLegalService.listByFilter2Map(TmsMdLegalEntity.class.getName(),
			    		TmsMdUsageLocalLegal.class.getName() , needFields);
			    
			    JSONArray jsonArray = JSONArray.fromObject(results);
			    
			    System.out.println("UsageLocalMgtController > listLegalFree return: "+jsonArray);
			   
			    this.retJsonArray(response, jsonArray);
			    
			    System.out.println("---------------");
			    
			}catch(Exception e){
				
				System.out.println(e.getMessage());
				this.returnFail(response);
			}
		}
		
		
		
		/**
		 * inin tree of legal equipment on page left
		 *〈一句话功能简述〉 
		 * 功能详细描述
		 * @param response
		 * @see [相关类/方法]（可选）
		 * @since [产品/模块版本] （可选）
		 * @author tigchen
		 */
		
		
		@RequestMapping("useLocalLegal/treeUseLocal.do")//treeLegalPrint -> 
		public void treeUseLocalLegal(HttpServletResponse response){
			
			try{
				String mainTable=TmsMdUsageLocalLegal.class.getName();
				String extrTable=TmsMdLegalEntity.class.getName();
				
				//List<TmsMdUsageLocalLegalInParam> legalPrintInPara = this.tmsMdUsageLocalLegalService.findModelInParaByDefault(mainTable, extrTable);

			    List<FunctionTreeNode> treeNodes =  this.tmsMdUsageLocalLegalService.findModelInParaByDefault2Nodes(mainTable, extrTable);
			  
			    List<FunctionTreeNode> results = TreeGenerator.buildTreeUseLegalEntityId(treeNodes);
			    
			    JSONArray jsonArray = JSONArray.fromObject(results);	
			    
			    System.out.println("------treeLegaEquipment return: "+jsonArray);
			    retJsonArray(response, jsonArray);
			    
			}catch(Exception e){
				System.out.println("treeLegaEquipment------------------exception as below:");
				System.out.println("treeLegaEquipment------------------exception:"+e.getMessage());
			}
		}
	
		/**
		 * 删除 legal 部门  和 对应的 打印机 关联关系, 把 当前节点的子节点一起删除, 用个check box判断 要不要删除当前节点子节点 以及 删除后怎么处理
		 *〈一句话功能简述〉 
		 * 功能详细描述
		 * @param response
		 * @param legalPrintIds
		 * @see [相关类/方法]（可选）
		 * @since [产品/模块版本] （可选）
		 * @author tigchen
		 */
		@RequestMapping("useLocalLegal/delUseLocalNode.do")//delLegalPrint->delUseLocalNode
		public void delLegalPrint(HttpServletResponse response,  @RequestParam String useLocalIds){
			
			try{
			
			if(AssertHelper.empty(useLocalIds)){
				//to-do
				this.returnOk(response); 
			}else{
				
		
				HashMap<String, String> map = new HashMap<String, String>();
			
					
					map.put("id", useLocalIds);
				
				
				this.tmsMdUsageLocalLegalService.removeByKeys(map, TmsMdUsageLocalLegal.class.getName());
				 //this.tmsMdLegalEnablePrintService.execDelHqlDefault(map);
				
				returnOk(response);
			}
			
			}catch(Exception e){
				
				this.returnFail(response);
			}
		}
		
		
/*		
		*//**
		 * 
		 * 把纳税主体 加入树， 作为当前选择 纳税主体的 下级 
		 * 功能详细描述
		 * @param response
		 * @param tmsMdLegalEquipment
		 * @see [相关类/方法]（可选）
		 * @since [产品/模块版本] （可选）
		 * @author tigchen
		 *//*
		@RequestMapping("useLocalLegal/addLegalIntoTree.do")//addLegalIntoTree->
		public void addLegalIntoTree(String nowLegalEquId, String willNewLegalid, String willNewLegalName, HttpServletResponse response){
			
			System.out.println("useLocalLegal/addLegalIntoTree.do > addLegalIntoTree---------");
			
			try{
				
				
			if( AssertHelper.empty(willNewLegalid)){
				//to-do
				return;
			}else{

				HashMap<String, String> map = new HashMap<String, String>();
				map.put("legalEntityId", willNewLegalid);
				map.put("parentId", nowLegalEquId);
				this.tmsMdLegalEnablePrintService.execInsertHqlDefault(map);
				

				
				
			TmsMdLegalEnablePrint newEnitty = new TmsMdLegalEnablePrint();
				
				
				if(nowLegalEquId==null || "".equals(nowLegalEquId.trim())){
					nowLegalEquId=null;
				}
				
				newEnitty.setParentId(nowLegalEquId);
				newEnitty.setLegalEntityId(willNewLegalid);
				
				//newEnitty.setIsEnablePrint("0");
				//newEnitty.setEnabledFlag("0");
				newEnitty.setFlag("0");
				
				this.tmsMdUsageLocalLegalService.save(newEnitty);

				this.returnOk( response);
				
				
			}
			
			}catch(Exception e){
				
				e.printStackTrace();
				this.returnFail(response);
			}
		}*/
		
		
		/**
		 * 
		 * upate 功能
		 * 功能详细描述
		 * @param url
		 * @param response
		 * @throws Exception 
		 * @see [相关类/方法]（可选）
		 * @since [产品/模块版本] （可选）
		 * @author tigchen
		 */
		
		@RequestMapping("useLocalLegal/update.do")
		//public void updateUseLocalLegal(@ModelAttribute("tmsMdUsageLocalLegal") TmsMdUsageLocalLegal tmsMdUsageLocalLegal,
		
		public void updateUseLocalLegal(@RequestParam Map<String, Object> map  ,
				HttpServletResponse response)  {
			JSONObject result = new JSONObject();
			try {
				
				TmsMdUsageLocalLegal tmsMdUsageLocalLegal=new TmsMdUsageLocalLegal(map);
				
				
				if(AssertHelper.empty(tmsMdUsageLocalLegal.getId())){
					
					System.out.println("UsageLocalMgtController>updateLegalPrintFunction>tmsMdLegalEnablePrint no id, should throw exception: tmsMdLegalEnablePrint:"+tmsMdUsageLocalLegal);
					result.put("fail", true);
					result.put("errorMsg", "now department uuid is null"+tmsMdUsageLocalLegal);
					retJson(response, result);
					
			
				}else{
					System.out.println("UsageLocalMgtController>updateLegalPrintFunction>defaultDept has uuid, will update: "+tmsMdUsageLocalLegal);
					this.tmsMdUsageLocalLegalService.update(tmsMdUsageLocalLegal);
					
					result.put("success", true);
					result.put("msg", getMessage("save.sucess"));
					retJson(response, result);
				}
				
				} catch (IOException e) {
					// TODO Auto-generated catch block
					result.put("msg", getMessage("save.fail"));
					//retJson(response, result);
					e.printStackTrace();
				}
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
		 * @author tigchen
		 */
		
		@RequestMapping("useLocalLegal/addNew.do")
		public void addNewUseLocalLegalUseMap(@RequestParam Map<String, Object> map,
				HttpServletResponse response)  {
			JSONObject result = new JSONObject();
			try {
				
				if(map==null){
					this.returnFail(response);
					return;
				}
				
				TmsMdUsageLocalLegal tmsMdUsageLocalLegal = new TmsMdUsageLocalLegal(map);
				
				if(AssertHelper.empty(tmsMdUsageLocalLegal)){
					this.returnFail(response);
					return;
				}
				
				
			/*	String tempStoredLegalEntityId = tmsMdUsageLocalLegal.getId();
				
				tmsMdUsageLocalLegal.setParentId(tempStoredLegalEntityId);*/
				
				if(!AssertHelper.empty(tmsMdUsageLocalLegal.getId())){
					
					System.out.println("UsageLocalMgtController>addNewUseLocalLegal>tmsMdUsageLocalLegal have id, exception , should have no id");
					
					tmsMdUsageLocalLegal.setId(null);
					
					
				}
				
				System.out.println("useLocalLegal/addNew.do > will save new tmsMdUsageLocalLegal:"+tmsMdUsageLocalLegal);
				System.out.println("useLocalLegal/addNew.do > will save new tmsMdUsageLocalLegal parentId:"+tmsMdUsageLocalLegal.getParentId());
					
					tmsMdUsageLocalLegalService.save(tmsMdUsageLocalLegal);
					//this.tmsMdUsageLocalLegalService.update(tmsMdUsageLocalLegal);
					
					result.put("success", true);
					result.put("msg", getMessage("save.sucess"));
					
					//retJson(response, result);
					this.returnOk(response);
				
				} catch (Exception e) {
					// TODO Auto-generated catch block
					result.put("errorMsg", getMessage("save.fail"));
					//retJson(response, result);
					e.printStackTrace();
					this.returnFail(response);
				}
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
		 * @author tigchen
		 */
		/*
		@RequestMapping("useLocalLegal/addNew.do")
		public void addNewUseLocalLegal(@ModelAttribute("tmsMdUsageLocalLegal") TmsMdUsageLocalLegal tmsMdUsageLocalLegal,
				HttpServletResponse response)  {
			JSONObject result = new JSONObject();
			try {
				
				String tempStoredLegalEntityId = tmsMdUsageLocalLegal.getId();
				
				tmsMdUsageLocalLegal.setParentId(tempStoredLegalEntityId);
				
				if(!AssertHelper.empty(tmsMdUsageLocalLegal.getId())){
					
					System.out.println("UsageLocalMgtController>addNewUseLocalLegal>tmsMdUsageLocalLegal have id, exception , should have no id");
					
					tmsMdUsageLocalLegal.setId(null);
					
					
				}
				
				System.out.println("useLocalLegal/addNew.do > will save new tmsMdUsageLocalLegal:"+tmsMdUsageLocalLegal);
				System.out.println("useLocalLegal/addNew.do > will save new tmsMdUsageLocalLegal parentId:"+tmsMdUsageLocalLegal.getParentId());
					
					tmsMdUsageLocalLegalService.save(tmsMdUsageLocalLegal);
					//this.tmsMdUsageLocalLegalService.update(tmsMdUsageLocalLegal);
					
					result.put("success", true);
					result.put("msg", getMessage("save.sucess"));
					
					//retJson(response, result);
					this.returnOk(response);
				
				} catch (Exception e) {
					// TODO Auto-generated catch block
					result.put("errorMsg", getMessage("save.fail"));
					//retJson(response, result);
					e.printStackTrace();
					this.returnFail(response);
				}
		}
*/
	

}
