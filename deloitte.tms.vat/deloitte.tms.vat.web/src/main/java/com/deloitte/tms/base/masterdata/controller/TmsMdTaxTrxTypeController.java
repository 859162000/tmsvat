package com.deloitte.tms.base.masterdata.controller;


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
import com.deloitte.tms.base.masterdata.model.TmsMdTaxTrxType;
import com.deloitte.tms.base.masterdata.model.TmsMdTaxTrxTypeInParam;
import com.deloitte.tms.base.masterdata.service.TmsMdTaxTrxTypeService;
/**
 * Home object for domain model class TmsMdTaxTrxType.
 * @see com.deloitte.tms.base.masterdata.model
 * @author Hibernate Tools
 */
@Controller
@RequestMapping("tmsMdTaxTrxType")
public class TmsMdTaxTrxTypeController extends BaseController{
	@Resource
	TmsMdTaxTrxTypeService tmsMdTaxTrxTypeService;
	/**
	 * 跳转涉税交易类型认定页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value ="/testtaxtransactiondefinition")
	public String transactionexcEptions() throws Exception{
		System.out.println("sasa");
		return "base/masterdata/taxTransactionDefinition";
	} 
	/**
	 * 涉税交易类型页面数据取得
	 * @param parameter
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/loadTmsMdTaxTrxTypePage", method = RequestMethod.GET)
	//@RoleAnnotation(roles=RoleDef.ECOMMERCE_ADMIN)
	public DaoPage loadTmsMdTaxTrxTypePage(@RequestParam Map<String,Object> parameter,HttpServletResponse response) throws Exception {
		String pageNumber= (String) parameter.get("pageNumber");
		if(pageNumber==null||"".equals(pageNumber)){
			parameter.put("pageNumber", 1);
			parameter.put("pageSize", 10);
			
		}
		DaoPage daoPage=tmsMdTaxTrxTypeService.findTmsMdTaxTrxTypeByParams(parameter,PageUtils.getPageNumber(parameter),PageUtils.getPageSize(parameter));
		JSONObject result = new JSONObject();
		 JsonConfig jsonConfig = new JsonConfig();
		 jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor("yyyy-MM-dd")); 
		 JSONArray jsonArray = JSONArray.fromObject(daoPage.getResult(),jsonConfig);
		
		result.put("total", daoPage.getRecordCount());
		result.put("rows", jsonArray);
		result.put("pages", daoPage.getPageCount());
		result.put("success", true);
		retJson(response, result);
		
		return null;
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
		inParam.setBizOrgCode("机构");
		inParam.setCreatedBy("测试");
		inParam.setModifiedDate(new Date());
		inParam.setModifiedBy("zhan");
		inParam.setVersionId(10);
		TmsMdTaxTrxType entity=tmsMdTaxTrxTypeService.convertTmsMdTaxTrxTypeInParamToEntity(inParam);
		if(AssertHelper.empty(entity.getId())){
			entity.setId(IdGenerator.getUUID());
			entity.setFlag("1");
			tmsMdTaxTrxTypeService.save(entity);
		}
		else{
			entity.setFlag("1");
			tmsMdTaxTrxTypeService.update(entity);
		}
		DaoPage daoPage=tmsMdTaxTrxTypeService.findTmsMdTaxTrxTypeByParams(null,1,10);
		JSONObject result = new JSONObject();
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor("yyyy-MM-dd")); 
		JSONArray jsonArray = JSONArray.fromObject(daoPage.getResult(),jsonConfig);
		
		result.put("total", daoPage.getRecordCount());
		result.put("rows", jsonArray);
		result.put("pages", daoPage.getPageCount());
		result.put("success", true);
		retJson(response, result);
		
	}	
	/**
	 * 删除信息
	 */
	@ResponseBody
	@RequestMapping(value = "/removeTmsMdTaxTrxTypes")
	//@RoleAnnotation(roles=RoleDef.ECOMMERCE_ADMIN)
	public void removeTmsMdTaxTrxTypes(@RequestParam Map<String,Object> parameter,HttpServletResponse response) {
		AssertHelper.notEmpty_assert(parameter.get("tmsMdTaxTrxTypeKeys"),"需要删除的用户不能为空");
		String[] tmsMdTaxTrxTypeIds=((String) parameter.get("tmsMdTaxTrxTypeKeys")).split(",");
		for(String tmsMdTaxTrxTypeId:tmsMdTaxTrxTypeIds){
			if(!"".equals(tmsMdTaxTrxTypeId)){
			tmsMdTaxTrxTypeService.removeTmsMdTaxTrxTypes(tmsMdTaxTrxTypeId);
			}
		}
		try {
			parameter.remove("tmsMdTaxTrxTypeKeys");
			DaoPage daoPage=tmsMdTaxTrxTypeService.findTmsMdTaxTrxTypeByParams(parameter,PageUtils.getPageNumber(parameter),PageUtils.getPageSize(parameter));
			JSONObject result = new JSONObject();
			 JsonConfig jsonConfig = new JsonConfig();
			 jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor("yyyy-MM-dd")); 
			 JSONArray jsonArray = JSONArray.fromObject(daoPage.getResult(),jsonConfig);
			
			result.put("total", daoPage.getRecordCount());
			result.put("rows", jsonArray);
			result.put("pages", daoPage.getPageCount());
			result.put("success", true);
			retJson(response, result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public TmsMdTaxTrxTypeInParam loadAddTmsMdTaxTrxType(Map<String, Object> map) throws Exception {
		TmsMdTaxTrxTypeInParam inParam=new TmsMdTaxTrxTypeInParam();
		return inParam;
	}
	
	public TmsMdTaxTrxTypeInParam loadModifyTmsMdTaxTrxType(Map<String, Object> map) throws Exception {
		Object id=map.get("id");
		AssertHelper.notEmpty_assert(id,"编辑的主键不能为空");
		TmsMdTaxTrxType entity=(TmsMdTaxTrxType)tmsMdTaxTrxTypeService.get(TmsMdTaxTrxType.class,Long.parseLong(id.toString()));
		TmsMdTaxTrxTypeInParam inParam=tmsMdTaxTrxTypeService.convertTmsMdTaxTrxTypeToInParam(entity);
		return inParam;
	}
	
	
	
	public void updateModifyTmsMdTaxTrxType(TmsMdTaxTrxTypeInParam inParam) throws Exception {
		TmsMdTaxTrxType entity=(TmsMdTaxTrxType)tmsMdTaxTrxTypeService.get(TmsMdTaxTrxType.class,inParam.getId());
		ReflectUtils.copyProperties(inParam, entity);
		tmsMdTaxTrxTypeService.update(entity);
	}
	
	@RequestMapping(value = "getTaxTrxTypeNameById", method = RequestMethod.GET)
	public void getTaxTrxTypeNameById(HttpServletResponse response,@RequestParam Map<String,Object> parameter) throws Exception {
		List<TmsMdTaxTrxTypeInParam> list =tmsMdTaxTrxTypeService.findTmsMdTaxTrxTypeByParams(parameter);
		JSONObject result = new JSONObject();
		if(list!=null&&list.size()>0){
			result.put("value", list.get(0).getTaxTrxTypeName());
		}
		retJson(response, result);

	}
	
	
	@ResponseBody
	@RequestMapping(value = "/loadTmsMdTaxTrxTypePageName", method = RequestMethod.POST)
	//@RoleAnnotation(roles=RoleDef.ECOMMERCE_ADMIN)
	public DaoPage loadTmsMdTaxTrxTypePageName(@RequestParam Map<String,Object> parameter) throws Exception {
		DaoPage daoPage=tmsMdTaxTrxTypeService.findTmsMdTaxTrxTypeByParams(parameter,PageUtils.getPageNumber(parameter),PageUtils.getPageSize(parameter));
		return daoPage;
	}
	
	
	
}
