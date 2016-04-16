package com.deloitte.tms.base.taxsetting.context;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.deloitte.tms.base.masterdata.model.BaseOrg;
import com.deloitte.tms.base.masterdata.model.TmsMdLegalEntity;
import com.deloitte.tms.base.masterdata.model.TmsMdOrgLegalEntity;
import com.deloitte.tms.base.masterdata.model.TmsMdOrgLegalEntityInParam;
import com.deloitte.tms.base.masterdata.service.TmsMdLegalEntityService;
import com.deloitte.tms.base.masterdata.service.TmsMdOrgLegalEntityService;
import com.deloitte.tms.base.masterdata.service.TmsMdUsageLocalLegalService;
import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.core.commons.utils.PageUtils;
import com.deloitte.tms.pl.security.model.SecurityDept;
import com.deloitte.tms.pl.security.model.impl.DefaultDept;
import com.deloitte.tms.pl.security.service.IDeptService;
import com.deloitte.tms.pl.security.utils.LittleUtils;
import com.deloitte.tms.vat.controller.BaseController;
import com.deloitte.tms.vat.core.common.IdGenerator;
import com.deloitte.tms.vat.core.common.JsonDateValueProcessor;
/**
 * 纳税主体与组织关系
 * @see com.deloitte.tms.base.masterdata.model
 * @author JasonPu
 */
@Controller
@RequestMapping("orgTaxpayer")
public class OrgTaxpayerRelationController extends BaseController {
	@Resource
	TmsMdOrgLegalEntityService tmsMdorgLegalEntityService;
	
	@Resource
	TmsMdLegalEntityService tmsMdLegalEntityService;
	
	@Resource
	IDeptService deptService;
	
	@Resource
	TmsMdUsageLocalLegalService commonService;
	
	//orgtaxpayer/search.do
	@RequestMapping(value = "/invoiceSendHInit", method = RequestMethod.GET)
	public String invoiceSendHInit() throws Exception {
		return "base/taxsetting/orgTaxpayerRelation";
	}
	
	/**
	 * rewrite this function
	 */
	@RequestMapping(value = "orgTaxpayerRelation/searchLegalEnility")
	public void searchTaxOrg(HttpServletResponse response,
			@RequestParam Map<String, Object> parameter) {

		try {
			/*
			 * 
orgCode
orgName
legalEntityId
legalEntityName
			 */
			int pageSize=PageUtils.getPageSize(parameter);
			int pageNumber=PageUtils.getPageNumber(parameter);
			
			if(parameter!=null && parameter.size()>0){
				
				parameter.remove("pageSize");
				parameter.remove("pageNumber");
			}
			
			DaoPage daoPage = tmsMdorgLegalEntityService.findOrgLegalByParams(parameter, pageNumber, pageSize);

			List<TmsMdOrgLegalEntity> list = (List<TmsMdOrgLegalEntity>) daoPage.getResult();
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
	 * 主表信息查询
	 * @param response
	 * @param parameter
	 */
/*	@RequestMapping(value = "orgTaxpayerRelation/searchLegalEnility")
	public void searchTaxOrg(HttpServletResponse response,
			@RequestParam Map<String, Object> parameter) {

		try {
			int pageSize=PageUtils.getPageSize(parameter);
			int pageNumber=PageUtils.getPageNumber(parameter);
			//System.out.println("legalEntityId:"+parameter.get("legalEntityId"));
			
			//to-do could not locate named parameter [custRegistrationNumber]
			DaoPage daoPage = tmsMdorgLegalEntityService.findTmsMdOrgLegalEntityByParams(parameter, pageNumber, pageSize);

			List<TmsMdOrgLegalEntity> list = (List<TmsMdOrgLegalEntity>) daoPage.getResult();
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

	}*/

	
	@RequestMapping(value = "orgTaxpayerRelation/searchEnilityInDlg")
	public void searchEnilityInDlg(HttpServletResponse response,
			@RequestParam Map<String, Object> parameter) {
		try {
			int pageSize=PageUtils.getPageSize(parameter);
			int pageNumber=PageUtils.getPageNumber(parameter);
			//System.out.println("legalEntityId:"+parameter.get("legalEntityId"));
			
			//to-do could not locate named parameter [custRegistrationNumber]
			DaoPage daoPage = tmsMdLegalEntityService.findTmsMdLegalEntityByParams(parameter, pageNumber, pageSize);
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
	 * 纳税人识别码
	 * @param parameter
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "orgTaxpayerRelation/legalEntitysearch")
	public void getAllLegalEntity(@RequestParam Map<String, Object> parameter,HttpServletResponse response)throws Exception{
		try {
			System.out.println("orgTaxpayerRelation/legalEntitysearch---begin");
			parameter.put("pageSize", 10);
			parameter.put("pageNumber", 1);
			int pageSize=PageUtils.getPageSize(parameter);
			int pageNumber=PageUtils.getPageNumber(parameter);
			//to-do could not locate named parameter [custRegistrationNumber]
			DaoPage daoPage = tmsMdLegalEntityService.findTmsMdLegalEntityByParams(parameter, pageNumber, pageSize);
//
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
	 * 主面板组织查询
	 * @param parameter
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "orgTaxpayerRelation/orgsearch")
	public void orgsearch(@RequestParam Map<String, Object> parameter,HttpServletResponse response)throws Exception{
		try {
			parameter.put("pageSize", 10);
			parameter.put("pageNumber", 1);
			int pageSize=PageUtils.getPageSize(parameter);
			int pageNumber=PageUtils.getPageNumber(parameter);
			DaoPage daoPage = tmsMdorgLegalEntityService.orgsearch(parameter, pageNumber, pageSize);
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
	 * 弹出框组织关系查询
	 * @param parameter
	 * @param response
	 * @throws Exception
	 */

	@RequestMapping(value = "orgTaxpayerRelation/dialogOrgsearchN")
	public void dialogOrgsearchN(@RequestParam Map<String, Object> parameter,HttpServletResponse response)throws Exception{/*
		try {
			System.out.println("orgTaxpayerRelation/orgsearch---begin");
			parameter.put("pageSize", 10);
			parameter.put("pageNumber", 1);
			int pageSize=PageUtils.getPageSize(parameter);
			int pageNumber=PageUtils.getPageNumber(parameter);
			
			DaoPage  page = tmsMdorgLegalEntityService.findAllBaseOrg(parameter, pageNumber, pageSize);//查询组织表数据
			List<Object[]> li = (List) page.getResult();
			
			List<BaseOrg> list = new ArrayList<BaseOrg>();
			for(int i = 0;i<li.size();i++){
				Object[] dataString  =   li.get(i);
				System.out.print(dataString);
				BaseOrg base = new BaseOrg();
				base.setOrgCode((String) dataString[0]);
				base.setOrgName((String) dataString[20]);
				base.setId((String) dataString[0]);
				base.setOrgTye((String) dataString[21]);
				list.add(base);
			}
			
			
			
			
//			JsonConfig jsonConfig = new JsonConfig();
//			jsonConfig.registerJsonValueProcessor(Date.class,
//					new JsonDateValueProcessor("yyyy-MM-dd"));
//			JSONArray jsonArray = JSONArray.fromObject(page.getResult(), jsonConfig);
	 JSONObject result = new JSONObject();
			result.put("total", page.getTotal());
			result.put("rows", list);
			retJson(response, result);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	*/}
	
	
	@RequestMapping(value = "orgTaxpayerRelation/dialoglegalEntitysearch")
	public void dialoglegalEntitysearch(@RequestParam Map<String, Object> parameter,HttpServletResponse response)throws Exception{
		
		HashMap<String, Object> para = new HashMap<String, Object>();
		
		ArrayList<String> fieldList = new ArrayList<String>();
		fieldList.add("id");
		fieldList.add("registrationNumber");
		fieldList.add("legalEntityName");
		
		DaoPage daoPage = this.commonService.listByFilter2DaoPage(TmsMdLegalEntity.class.getName(), TmsMdOrgLegalEntity.class.getName(), fieldList, parameter);
			
		this.returnFromDaoPage(response, daoPage);		
		
	}
	
	
	@RequestMapping("orgTaxpayerRelation/getOrgTree.do")
	public void getOrgTree(@RequestParam Map<String, Object> para,
			HttpServletResponse response){
		
		try{
			
			String mainClass=DefaultDept.class.getName();
			String relationClass=TmsMdOrgLegalEntity.class.getName();
			HashMap<String, Object> paraUse = new HashMap<String, Object>();
			paraUse.put("relationKeyId", para.get("relationKeyId"));
			
			JSONArray jsonArray = this.commonService.getOrgTreeFree4Relation( response,  paraUse,  null,  mainClass,  relationClass, "orgId");
				
			
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
	
	
/**
 * 弹出框纳税人识别码
 * @param parameter
 * @param response
 * @throws Exception
 */
	/*@RequestMapping(value = "orgTaxpayerRelation/dialoglegalEntitysearch")
	public void dialoglegalEntitysearch(@RequestParam Map<String, Object> parameter,HttpServletResponse response)throws Exception{
		try {
			parameter.put("pageSize", 10);
			parameter.put("pageNumber", 1);
			int pageSize=PageUtils.getPageSize(parameter);
			int pageNumber=PageUtils.getPageNumber(parameter);
			
			DaoPage  page = tmsMdorgLegalEntityService.dialoglegalEntitysearch(parameter, pageNumber, pageSize);//查询纳税人识别码
			List<Object[]> li = (List) page.getResult();
			
			List<TmsMdLegalEntity> list = new ArrayList<TmsMdLegalEntity>();
			for(int i = 0;i<li.size();i++){
				Object[] dataString  =   li.get(i);
				TmsMdLegalEntity tmsmdlegalentity = new TmsMdLegalEntity();
				tmsmdlegalentity.setId((String) dataString[0]);
				tmsmdlegalentity.setLegalEntityName((String) dataString[2]);
				tmsmdlegalentity.setRegistrationNumber((String) dataString[10]);
				list.add(tmsmdlegalentity);
			}
	 JSONObject result = new JSONObject();
			result.put("total", page.getTotal());
			result.put("rows", list);
			retJson(response, result);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}*/
	
	
	
	
	
	
	
	
	
	
	/**

	 * 删除方法
	 * @param parameter
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("orgTaxpayerRelation/delete")
	public void deleteRelation(String id,
			HttpServletResponse response) throws Exception {
		
		try {
			if(id==null || ""==id.trim()){
				this.returnFail(response, "请选中一条要删除的记录");
				return;
			}
		
			this.commonService.removeByKeys(id, TmsMdOrgLegalEntity.class.getName());
			
			this.returnOk(response, "删除成功");
			
		} catch (Exception e) {
			e.printStackTrace();
			this.returnFail(response, "删除失败");
			
		}
	}
	
	/**
	 * 得到组织名称
	 * @param map
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("orgTaxpayerRelation/getOrgName")
	public void getOrgName(@RequestParam Map<String, Object> map,HttpServletResponse response) throws IOException{
		List<SecurityDept>list=deptService.findAllDepts();
		List<Map<String, String>>reList=new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			SecurityDept dept= list.get(i);
			Map< String, String > orgNameMap=new HashMap<String, String>();
			orgNameMap.put("value", dept.getId());
			orgNameMap.put("text", dept.getOrgName());
			reList.add(orgNameMap);
		}
		JSONArray jsonArray = JSONArray.fromObject(reList);
		retJsonArray(response, jsonArray); 
	}
	/**
	 * 保存
	 * @param inParam
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("orgTaxpayerRelation/save")
	public void saveOrgTaxpayRelation(@ModelAttribute("TmsMdOrgLegalEntityInParam") TmsMdOrgLegalEntityInParam inParam, HttpServletResponse response) throws IOException{
		TmsMdOrgLegalEntity entity=tmsMdorgLegalEntityService.convertTmsMdOrgLegalEntityInParamToEntity(inParam);
		
		try {
			String legalEntityId = entity.getLegalEntityId();
			if(legalEntityId==null || ""==legalEntityId.trim()){
				this.returnFail(response, "请必须选择纳税主体");
				return;
			}
			
			String orgId = entity.getOrgId();
			if(orgId==null || ""==orgId.trim()){
				this.returnFail(response, "请必须选择组织主体");
				return;
			}
			
			if(AssertHelper.empty(entity.getId())){
				
				entity.setId(IdGenerator.getUUID());
				entity.setFlag(LittleUtils.one);
				tmsMdLegalEntityService.save(entity);
			}else{
				
				if(entity.getOperationOrgCode()==null||"".equals(entity.getOperationOrgCode().trim())){
					entity.setOperationOrgCode("1");
				}
				
				tmsMdLegalEntityService.update(entity);
			}
			
			this.returnOk(response, "保存关系成功");
			//inParam.setId(entity.getId().toString());
		} catch (Exception e) {
			
			this.returnFail(response, "保存关系失败");
		}
	}
}
