package com.deloitte.tms.base.taxsetting.controller;


import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.core.commons.utils.reflect.ReflectUtils;
import com.deloitte.tms.pl.core.commons.utils.PageUtils;
import com.deloitte.tms.vat.controller.BaseController;
import com.deloitte.tms.vat.core.common.IdGenerator;
import com.deloitte.tms.vat.core.common.JsonDateValueProcessor;
import com.deloitte.tms.vat.trnsctrecog.service.TmsCrvatTrxAffirmService;
import com.deloitte.tms.base.masterdata.model.TmsMdTaxTrxType;
import com.deloitte.tms.base.masterdata.model.TmsMdTaxTrxTypeInParam;
import com.deloitte.tms.base.masterdata.service.TmsMdTaxTrxTypeService;
import com.deloitte.tms.base.taxsetting.model.TmsMdTrxAffirmSetting;
import com.deloitte.tms.base.taxsetting.model.TmsMdTrxAffirmSettingInParam;
import com.deloitte.tms.base.taxsetting.service.TmsMdTrxAffirmSettingService;
/**
 * Home object for domain model class TmsMdTaxTrxType.
 * @see com.deloitte.tms.base.masterdata.model
 * @author Hibernate Tools
 */
@Controller
@RequestMapping("tmsMdTrxAffirmSetting")
public class TmsMdTrxAffirmSettingController extends BaseController{
	@Resource
	TmsMdTaxTrxTypeService tmsMdTaxTrxTypeService;
	
	@Resource
	TmsCrvatTrxAffirmService tmsCrvatTrxAffirmService;
	
	@Resource
	TmsMdTrxAffirmSettingService tmsMdTrxAffirmSettingService;
	
	@RequestMapping(value = "/initTmsMdTrxAffirmSetting", method = RequestMethod.GET)
	public String initTmsMdTrxAffirmSetting()throws Exception{
		return "vat/tmsmdtrxaffirmsetting/tmsMdTrxAffirmSetting";
	}
	
	@ResponseBody
	@RequestMapping(value = "/loadTmsMdTrxAffirmSettingPage", method = RequestMethod.GET)
	//@RoleAnnotation(roles=RoleDef.ECOMMERCE_ADMIN)
	public DaoPage loadTmsMdTrxAffirmSettingPage(@RequestParam Map<String,Object> parameter,HttpServletResponse response) throws Exception {
DaoPage daoPage=tmsMdTrxAffirmSettingService.findTmsMdTrxAffirmSettingByParams(parameter,PageUtils.getPageNumber(parameter),PageUtils.getPageSize(parameter));
		
		JSONObject result = new JSONObject();
		 JsonConfig jsonConfig = new JsonConfig();
		 jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor("yyyy-MM-dd")); 
		 JSONArray jsonArray = JSONArray.fromObject(daoPage.getResult(),jsonConfig);
		result.put("total", daoPage.getRecordCount());
		result.put("rows", jsonArray);// daoPage.getr
		result.put("pages", daoPage.getPageCount());
		result.put("success", true);
		retJson(response, result);
		
		return null;
	}
	/**
	 * 涉税交易类型下拉列表数据取得
	 * @param parameter
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/loadTaxTransactionType_id", method = RequestMethod.GET)
	//@RoleAnnotation(roles=RoleDef.ECOMMERCE_ADMIN)
	public DaoPage loadTaxTransactionType_id(@RequestParam Map<String,Object> parameter,HttpServletResponse response) throws Exception {
	
		DaoPage daoPage=tmsMdTaxTrxTypeService.findTmsMdTaxTrxTypeByParams(parameter,PageUtils.getPageNumber(parameter),PageUtils.getPageSize(parameter));
		JSONObject result = new JSONObject();
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor()); 
		JSONArray jsonArray = JSONArray.fromObject(daoPage.getResult(),jsonConfig);
		
		result.put("total", daoPage.getRecordCount());
		result.put("rows", jsonArray);
		result.put("pages", daoPage.getPageCount());
		result.put("success", true);
		retJson(response, result);
		
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value = "/saveTmsMdTrxAffirmSetting",method = RequestMethod.POST)
	//@RoleAnnotation(roles=RoleDef.ECOMMERCE_ADMIN)
	public void saveTmsMdTrxAffirmSetting(TmsMdTrxAffirmSettingInParam inParam,HttpServletResponse response) throws Exception {

		TmsMdTrxAffirmSetting entity=tmsMdTrxAffirmSettingService.convertTmsMdTrxAffirmSettingInParamToEntity(inParam);
		if(AssertHelper.empty(entity.getId())){
			entity.setId(IdGenerator.getUUID());	
			tmsMdTaxTrxTypeService.save(entity);
		}
		else{		
			tmsMdTaxTrxTypeService.update(entity);
		}
		inParam.setId(entity.getId());
		JSONObject result = new JSONObject();
		result.put("success", true);
		retJson(response, result);
		
	}	
	
	/**
	 * 保存新增信息
	 * @param inParam
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/saveTmsMdTaxTrxType")
	//@RoleAnnotation(roles=RoleDef.ECOMMERCE_ADMIN)
	public void saveTmsMdTaxTrxType(TmsMdTaxTrxTypeInParam inParam,HttpServletResponse response) throws Exception {

	    
		TmsMdTaxTrxType entity=tmsMdTaxTrxTypeService.convertTmsMdTaxTrxTypeInParamToEntity(inParam);
		if(entity.getId()==null){
									
			tmsMdTaxTrxTypeService.save(entity);													
		}
		else{			
			tmsMdTaxTrxTypeService.update(entity);
		}
		inParam.setId(entity.getId());
		JSONObject result = new JSONObject();
		result.put("success", true);
		retJson(response, result);
		
	}	
	@ResponseBody
	@RequestMapping(value = "/removeTmsMdTaxTrxTypes", method = RequestMethod.POST)
	//@RoleAnnotation(roles=RoleDef.ECOMMERCE_ADMIN)
	public void removeTmsMdTaxTrxTypes(@RequestParam String tmsMdTaxTrxTypeKeys) {
		AssertHelper.notEmpty_assert(tmsMdTaxTrxTypeKeys,"需要删除的用户不能为空");
		String[] tmsMdTaxTrxTypeIds=tmsMdTaxTrxTypeKeys.split(",");
		//for(String tmsMdTaxTrxTypeId:tmsMdTaxTrxTypeIds){
			
		//}
	}
	@ResponseBody
	@RequestMapping(value = "/loadTmsMdTaxTrxType", method = RequestMethod.GET)
	//@RoleAnnotation(roles=RoleDef.ECOMMERCE_ADMIN)
	public Collection<TmsMdTaxTrxTypeInParam> loadTmsMdTaxTrxType(Map<String, Object> map) throws Exception {
		List result=tmsMdTaxTrxTypeService.findTmsMdTaxTrxTypeByParams(map);
		return result;
	}
	
	public TmsMdTaxTrxTypeInParam loadAddTmsMdTaxTrxType(Map<String, Object> map) throws Exception {
		TmsMdTaxTrxTypeInParam inParam=new TmsMdTaxTrxTypeInParam();
		return inParam;
	}
	
	@ResponseBody
	@RequestMapping(value = "/loadTmsMdTrxAffirmSetting", method = RequestMethod.POST)
	public TmsMdTrxAffirmSettingInParam loadTmsMdTrxAffirmSetting(@RequestParam Map<String, Object> map,HttpServletResponse response) throws Exception {
		Object id=map.get("id");
		AssertHelper.notEmpty_assert(id,"编辑的主键不能为空");
		TmsMdTrxAffirmSetting entity=(TmsMdTrxAffirmSetting)tmsMdTrxAffirmSettingService.get(TmsMdTrxAffirmSetting.class,id.toString());
		entity.getBaseOrg().setId((String)map.get("orgId"));
		entity.getTmsMdInventoryItems().setId((String)map.get("inventoryItemId"));
		entity.getTaxCategory().setId((String)map.get("taxCategoryId"));
		entity.getItems().setId((String)map.get("taxItemId"));
		entity.getTmsMdTaxTrxType().setId((String)map.get("taxTrxTypeId"));
		TmsMdTrxAffirmSettingInParam inParam=tmsMdTrxAffirmSettingService.convertTmsMdTrxAffirmSettingToInParam(entity);
		JSONObject result = new JSONObject();
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor("yyyy-MM-dd")); 
		JSONArray jsonArray = JSONArray.fromObject(inParam,jsonConfig);
		result.put("rows", jsonArray);
		result.put("success", true);
		retJson(response, result);
		
		
		
		return null;
	}
	
	
	
	public void updateModifyTmsMdTaxTrxType(TmsMdTaxTrxTypeInParam inParam) throws Exception {
		TmsMdTaxTrxType entity=(TmsMdTaxTrxType)tmsMdTaxTrxTypeService.get(TmsMdTaxTrxType.class,inParam.getId());
		ReflectUtils.copyProperties(inParam, entity);
		tmsMdTaxTrxTypeService.update(entity);
	}
	
	
	
	
	
}
