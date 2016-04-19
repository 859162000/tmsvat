
/**    
 * Copyright (C),Deloitte
 * @FileName: taxOrgMgtController.java  
 * @Package: com.deloitte.tms.vat.org.controller  
 * @Description: //模块目的、功能描述  
 * @Author tigchen  
 * @Date 2016年3月17日 上午11:44:32  
 * @History: //修改记录
 * 〈author〉      〈time〉      〈version〉       〈desc〉
 * 修改人姓名            修改时间            版本号              描述   
*/  

package com.deloitte.tms.vat.org.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.deloitte.tms.base.masterdata.model.TmsMdLegalEntity;
import com.deloitte.tms.base.masterdata.service.LegalEntityService;
import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.core.commons.utils.PageUtils;
import com.deloitte.tms.pl.security.service.IDeptService;
import com.deloitte.tms.vat.controller.BaseController;
import com.deloitte.tms.vat.core.common.JsonDateValueProcessor;
/**  
 *〈一句话功能简述〉
 * 功能详细描述
 * @author tigchen
 * @create 2016年3月17日 上午11:44:32 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */

@Controller
public class TaxOrgMgtController extends BaseController {
	
/*	@Resource
	IDeptService deptService;*/
	
	
	@Resource
	LegalEntityService legalEntityService;
	/**
	 * 
	 *〈一句话功能简述〉 
	 * 功能详细描述
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	
	@RequestMapping(value="taxOrgMgt")
	public String taxOrgIndex(){
		System.out.println("taxOrgIndex()...");
		return "org/taxOrgMgt";
	}
	
	@RequestMapping(value="org/addTaxOrg.do")	
	public void addTaxOrg(@ModelAttribute("tmsMdLegalEntity") TmsMdLegalEntity tmsMdLegalEntity, HttpServletResponse response) {
		System.out.println("addTaxOrg--------");
		JSONObject result = new JSONObject();
		try {
			
			System.out.println("addTaxOrg----:"+tmsMdLegalEntity);	
			
			if(tmsMdLegalEntity!=null &&   AssertHelper.empty( tmsMdLegalEntity.getId() ) ){
				
				/**
				 * no have primary id so will add
				 */
				legalEntityService.saveTaxOrgWithNeedInfo(tmsMdLegalEntity);
			}else if(tmsMdLegalEntity!=null){
				
				legalEntityService.updateTaxOrgWithNeedInfo(tmsMdLegalEntity);
			}else{
				
				this.returnFail(result, response);
			}	
			
			
			returnOk(result, response);

		} catch (Exception e) {
			
			returnFail(result, response);
		}
	}
	
	public void returnOk(JSONObject result, HttpServletResponse response) throws Exception{
		
		result.put("success", true);
		result.put("msg", this.getMessage("save.sucess"));
		
		retJson(response, result);
	}
	
	
	public void returnFail(JSONObject result, HttpServletResponse response){
		
		if(result == null){
			return;
		}
		
		try {
			if (result.getBoolean("success")) {
				
				//to-do: need retJson as a return?
			}else{
				result.put("fail", true);
				result.put("errorMsg", "Save new Tax org fail");
				this.retJson(response, result);
			}
		}catch(Exception e2){
			System.out.println("rertunFail failed...");
		}
	
	}
	
	/**
	 * 
	 *〈一句话功能简述〉 
	 * 功能详细描述
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	@RequestMapping(value="taxOrgMgt/updateTaxOrg.do")
	public void updateTaxOrg(@ModelAttribute(value="tmsMdLegalEntity") TmsMdLegalEntity tmsMdLegalEntity, HttpServletResponse response){
		
	System.out.println("taxOrgMgtController > updateTaxOrg ...TmsMdLegalEntity:"+tmsMdLegalEntity);
	JSONObject result = new JSONObject();	
		try{
			
			//this.deptService.update(tmsMdLegalEntity);
			
			this.legalEntityService.update(tmsMdLegalEntity);
			this.returnOk(result, response);
		}catch(Exception e){
			this.returnFail(result, response);
		}
	}
	
	/**
	 * 
	 *〈一句话功能简述〉 
	 * 功能详细描述
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	@RequestMapping(value="taxOrgMgt/initUpdateTaxOrg.do")
	public void initBeforeUpdateTaxOrg(@RequestParam(value="id") String id, HttpServletResponse response){
		
		System.out.println("initBeforeUpdateTaxOrg--------");
	JSONObject result = new JSONObject();	
		try{			
			
			HashMap map = new HashMap();
			map.put("id", id);
			
			StringBuffer sb = new StringBuffer(" from ");
			//sb.append( TmsMdLegalEntity.class.getName() ).append(" where legalEntityId=:legalEntityId ");
			sb.append( TmsMdLegalEntity.class.getName() ).append(" where id=:id ");
			
			//List<TmsMdLegalEntity> list = this.deptService.findBy(sb, map);
			List<TmsMdLegalEntity> list = this.legalEntityService.findBy(sb, map);
			
			if(list==null || list.size()<1 || list.size()>1){
				//to-do throw exception
				
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("errorMsg", "can't identity unique tax Org");
				retJson(response, jsonObject);
			}
			TmsMdLegalEntity tmsMdLegalEntity = list.get(0);
			
			System.out.println("taxOrgMgtController > initBeforeUpdateTaxOrg result TmsMdLegalEntity: "+tmsMdLegalEntity);
			
			
			JSONObject jsonObject = JSONObject.fromObject(tmsMdLegalEntity);
			retJson(response, jsonObject);
			
			//this.returnOk(result, response);
		}catch(Exception e){
			this.returnFail(result, response);
		}
	}
	
	/**
	 * 
	 *〈一句话功能简述〉 
	 * 功能详细描述
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	@RequestMapping(value="taxOrgMgt/delTaxOrg.do")
	public void delTaxOrg(@RequestParam(value="id") String legalEntityId, HttpServletResponse response){
		
		JSONObject result = new JSONObject();
		try{
			ArrayList<String> id = new ArrayList<String>();
			id.add(legalEntityId);
			this.legalEntityService.delById(id, TmsMdLegalEntity.class);
			
			result.put("success", true);
			result.put("msg", "删除纳税实体成功");
			
			retJson(response, result);
			
		}catch(Exception e){
			this.returnFail(result, response);
		}
	}
	
	/**
	 * 
	 *〈一句话功能简述〉 
	 * 功能详细描述
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	@RequestMapping(value = "taxOrgMgt/searchcomobogridTaxOrg.do")
	public void searchComboGridTaxOrg(HttpServletResponse response,
			@RequestParam Map<String, Object> parameter) {
		try {
			System.out.println("searchTaxOrg---");			
			int pageSize=PageUtils.getPageSize(parameter);
			int pageNumber=PageUtils.getPageNumber(parameter);							
			try {

				if (parameter != null) {
					parameter.remove("pageNumber");
					parameter.remove("pageSize");
				}

			}catch(Exception x){
				x.printStackTrace();
			}
			//to-do could not locate named parameter [custRegistrationNumber]
			DaoPage daoPage = legalEntityService.loadPageTaxDepts(TmsMdLegalEntity.class,
					parameter, pageNumber,pageSize);

			List<TmsMdLegalEntity> list = (List<TmsMdLegalEntity>) daoPage.getResult();
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class,
					new JsonDateValueProcessor("yyyy-MM-dd"));
			JSONArray jsonArray = JSONArray.fromObject(list, jsonConfig);
			JSONObject result = new JSONObject();
			result.put("total", daoPage.getTotal());
			result.put("rows", jsonArray);
			retJson(response, result);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
	
	
	/**
	 * 
	 *〈一句话功能简述〉 
	 * 功能详细描述
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	
	@RequestMapping(value = "taxOrgMgt/initSearchTaxOrg.do")
	public void initSearchTaxOrg(HttpServletResponse response,
			@RequestParam Map<String, Object> parameter) {

		try {
			System.out.println("initSearchTaxOrg---");
			
			int pageSize=PageUtils.getPageSize(parameter);
			int pageNumber=PageUtils.getPageNumber(parameter);
			
		
			System.out.println("registrationNumber:"+parameter.get("registrationNumber"));
			System.out.println("legalEntityName:"+parameter.get("legalEntityName"));
			
			try {

				if (parameter != null) {
					parameter.remove("pageNumber");
					parameter.remove("pageSize");
				}

			}catch(Exception x){
				x.printStackTrace();
			}
			//to-do could not locate named parameter [custRegistrationNumber]
			DaoPage daoPage = legalEntityService.loadPageTaxDepts(TmsMdLegalEntity.class,parameter,
					 pageNumber,pageSize);

			if(daoPage!=null){
				
			
			
			List<TmsMdLegalEntity> list = (List<TmsMdLegalEntity>) daoPage.getResult();
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class,
					new JsonDateValueProcessor("yyyy-MM-dd"));
			JSONArray jsonArray = JSONArray.fromObject(list, jsonConfig);
			JSONObject result = new JSONObject();
			result.put("total", daoPage.getTotal());
			result.put("rows", jsonArray);
			retJson(response, result);
			
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@RequestMapping(value = "taxOrgMgt/searchTaxOrg.do")
	public void searchTaxOrg(HttpServletResponse response,
			@RequestParam Map<String, Object> parameter) {

		try {
			System.out.println("searchTaxOrg---");
			
			int pageSize=PageUtils.getPageSize(parameter);
			int pageNumber=PageUtils.getPageNumber(parameter);
			
		
			System.out.println("registrationNumber:"+parameter.get("registrationNumber"));
			System.out.println("legalEntityName:"+parameter.get("legalEntityName"));
			
			try {

				if (parameter != null) {
					parameter.remove("pageNumber");
					parameter.remove("pageSize");
				}

			}catch(Exception x){
				x.printStackTrace();
			}
			//to-do could not locate named parameter [custRegistrationNumber]
			DaoPage daoPage = legalEntityService.loadPageTaxDepts(TmsMdLegalEntity.class,
					parameter, pageNumber,pageSize);

			List<TmsMdLegalEntity> list = (List<TmsMdLegalEntity>) daoPage.getResult();
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class,
					new JsonDateValueProcessor("yyyy-MM-dd"));
			JSONArray jsonArray = JSONArray.fromObject(list, jsonConfig);
			JSONObject result = new JSONObject();
			result.put("total", daoPage.getTotal());
			result.put("rows", jsonArray);
			retJson(response, result);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
