package com.deloitte.tms.vat.salesinvoice.controller;

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

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.core.commons.utils.PageUtils;
import com.deloitte.tms.pl.core.context.utils.ContextUtils;
import com.deloitte.tms.pl.dictionary.model.DictionaryEntity;
import com.deloitte.tms.pl.dictionary.service.DictionaryService;
import com.deloitte.tms.pl.security.model.SecurityDept;
import com.deloitte.tms.pl.security.model.impl.DefaultDept;
import com.deloitte.tms.pl.security.model.impl.DefaultUser;
import com.deloitte.tms.pl.security.service.IDeptService;
import com.deloitte.tms.pl.workflow.utils.WorkFlowUtils;
import com.deloitte.tms.vat.base.enums.CrvaInvoicePreStatusEnums;
import com.deloitte.tms.vat.controller.BaseController;
import com.deloitte.tms.vat.core.common.JsonDateValueProcessor;
import com.deloitte.tms.vat.salesinvoice.model.TmsCrvatInvoicePreH;
import com.deloitte.tms.vat.salesinvoice.model.TmsCrvatInvoicePreHInParam;
import com.deloitte.tms.vat.salesinvoice.model.TmsCrvatInvoicePreL;
import com.deloitte.tms.vat.salesinvoice.model.VoTmsCrvatInvoicePreH;
import com.deloitte.tms.vat.salesinvoice.service.InvoicePreService;
import com.deloitte.tms.vat.base.enums.CrvaInvoicePreStatusEnums;

@Controller
public class CrvatInvoicePreController extends BaseController{
	
	@Resource
	InvoicePreService invoicePreService;
	@Resource
	IDeptService deptService;
	 @Resource
	 DictionaryService dictionaryService;
	
	@RequestMapping(value = "/crvatInvoicePre")
	public String indexPage() {
		return "vat/crvatInvoicePre/crvatInvoicePrePage";
	}
	
	@RequestMapping("crvatInvoicePre/search")
	public void search(@RequestParam Map<String, Object> parameter,
			HttpServletResponse response) throws IOException {
		
		DaoPage daoPage = invoicePreService.findTmsCrvatInvoicePreHsByParam(parameter);
		List<VoTmsCrvatInvoicePreH> list = (List<VoTmsCrvatInvoicePreH>) daoPage.getResult();
		for(VoTmsCrvatInvoicePreH preHInParam:list){
			for(CrvaInvoicePreStatusEnums item:CrvaInvoicePreStatusEnums.values()){
				if(item.getValue().equals(preHInParam.getApprovalStatus())){
					preHInParam.setAstatus(getMessage(item.getText()));
					break;
				}
			}
		}
		
		
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class,
				new JsonDateValueProcessor("yyyy-MM-dd"));
		JSONArray jsonArray = JSONArray.fromObject(daoPage.getResult(), jsonConfig);
		JSONObject result = new JSONObject();
		result.put("total", daoPage.getRecordCount());
		result.put("rows", jsonArray.toString());
		retJson(response, result);	
	}
	
	@RequestMapping("crvatInvoicePre/searchPre.do")
	public void searchPreById(@RequestParam("id") String id,@RequestParam("pageNumber") String pageNumber,@RequestParam("pageSize") String pageSize,
			HttpServletResponse response) throws IOException {	
		DaoPage daoPage = invoicePreService.findTmsCrvatInvoicePreLByPreHId(id,pageNumber,pageSize);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class,
				new JsonDateValueProcessor("yyyy-MM-dd"));
		JSONArray jsonArray = JSONArray.fromObject(daoPage.getResult(), jsonConfig);
		JSONObject result = new JSONObject();
		result.put("total", daoPage.getRecordCount());
		result.put("rows", jsonArray.toString());
		retJson(response, result);	
	}
	@RequestMapping("crvatInvoicePre/acceptPre.do")
	public void acceptPreById(@RequestParam("id") String id,
			HttpServletResponse response) throws IOException {	
		TmsCrvatInvoicePreH tmsCrvatInvoicePreH = (TmsCrvatInvoicePreH) invoicePreService.get(TmsCrvatInvoicePreH.class, id);			
		tmsCrvatInvoicePreH.setApprovalStatus(CrvaInvoicePreStatusEnums.APPROVED.getValue());
		tmsCrvatInvoicePreH.setOrgId(ContextUtils.getCurrentOrgId());
		tmsCrvatInvoicePreH.setApprovalBy(ContextUtils.getCurrentUserName());
		String taskId = tmsCrvatInvoicePreH.getWfTaskId();
		if(!AssertHelper.empty(taskId)){
			long id_temp = Long.valueOf(taskId.trim());
			WorkFlowUtils.completeTask(id_temp);
		}else {
			invoicePreService.setAcceptStatus(tmsCrvatInvoicePreH);
		}
		/*invoicePreService.setAcceptStatus(tmsCrvatInvoicePreH);*/
		JSONObject result = new JSONObject();
		result.put("success", true);	
		result.put("msg", getMessage("accept.sucess"));
		retJson(response, result);	
	}
	@RequestMapping("crvatInvoicePre/revertPre.do")
	public void revertPreById(@RequestParam("id") String id,
			HttpServletResponse response) throws IOException {	
		TmsCrvatInvoicePreH tmsCrvatInvoicePreH = (TmsCrvatInvoicePreH) invoicePreService.get(TmsCrvatInvoicePreH.class, id);		
		invoicePreService.setRevertStatus(tmsCrvatInvoicePreH);
		JSONObject result = new JSONObject();
		result.put("success", true);	
		result.put("msg", getMessage("revert.sucess"));
		retJson(response, result);	
	}

	
	@RequestMapping("crvatInvoicePre/getstatus.do")
	public void getStatus(HttpServletResponse response) throws IOException {
		List<Map<String, Object>> results = new ArrayList<Map<String,Object>>();		 
		for(CrvaInvoicePreStatusEnums eunm:CrvaInvoicePreStatusEnums.values()){
		       String value = eunm.getValue();
		       String text =eunm.getText();
		       String retextString  = getMessage(text);
		       Map<String, Object> map = new HashMap<String, Object>();
		       map.put("value", value);
		       map.put("text",retextString);
		       results.add(map);
		 }
		JSONArray jsonArray = JSONArray.fromObject(results);
		retJsonArray(response, jsonArray);	
	}
	
    @RequestMapping("crvatInvoicePre/getOrgId.do")
	public void getOrgId(HttpServletResponse response) throws IOException {
		List<SecurityDept> depts =	deptService.findAllDepts();
		List<Map<String, Object>> results = new ArrayList<Map<String,Object>>();	
		for(SecurityDept dept:depts){		     
		     Map<String, Object> map = new HashMap<String, Object>();
		     map.put("value", dept.getId());
		     map.put("text",dept.getOrgName());
		     results.add(map);
		 }
		JSONArray jsonArray = JSONArray.fromObject(results);
		retJsonArray(response, jsonArray);	
	}
    
     @RequestMapping(value = "/crvatInvoicePre/getdictionary", method = RequestMethod.GET)	
	 public void getDictionaryEntitiesByParentCode(HttpServletResponse response) throws IOException{
			Collection<DictionaryEntity> results = dictionaryService.loadDictionaryEntities("tms.base.taxsetting.invoiceCodeType");
			 List<Map<String, String>> reList = new ArrayList<Map<String,String>>();
			 for(DictionaryEntity dictionaryEntity:results){
					Map<String,String> map = new HashMap<String,String>();					
					map.put("value", dictionaryEntity.getCode());
					map.put("text", dictionaryEntity.getName());
					reList.add(map);
			 }		
			JSONArray jsonArray = JSONArray.fromObject(reList);
			retJsonArray(response, jsonArray);
		
	 }

}
