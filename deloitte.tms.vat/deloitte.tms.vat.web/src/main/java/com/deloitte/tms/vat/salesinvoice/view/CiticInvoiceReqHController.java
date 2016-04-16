
/**    
 * Copyright (C),Deloitte
 * @FileName: CiticInvoiceReqHController.java  
 * @Package: com.deloitte.tms.vat.salesinvoice.view  
 * @Description: //模块目的、功能描述  
 * @Author sqing  
 * @Date 2016年4月10日 上午10:10:55  
 * @History: //修改记录
 * 〈author〉      〈time〉      〈version〉       〈desc〉
 * 修改人姓名            修改时间            版本号              描述   
*/  

package com.deloitte.tms.vat.salesinvoice.view;

import java.io.IOException;
import java.text.SimpleDateFormat;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.deloitte.tms.base.masterdata.model.Customer;
import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.core.commons.utils.DateUtils;
import com.deloitte.tms.pl.core.commons.utils.PageUtils;
import com.deloitte.tms.pl.core.context.utils.ContextUtils;
import com.deloitte.tms.pl.dictionary.service.DictionaryService;
import com.deloitte.tms.vat.base.enums.AppFormStatuEnums;
import com.deloitte.tms.vat.core.common.JsonDateValueProcessor;
import com.deloitte.tms.vat.salesinvoice.model.InvoiceReqH;
import com.deloitte.tms.vat.salesinvoice.model.InvoiceReqHInParam;
import com.deloitte.tms.vat.salesinvoice.model.InvoiceReqL;
import com.deloitte.tms.vat.salesinvoice.model.InvoiceTrxPool;
import com.deloitte.tms.vat.salesinvoice.model.InvoiceTrxPoolInParam;
import com.deloitte.tms.vat.salesinvoice.service.CiticInvoiceReqHService;
import com.deloitte.tms.vat.salesinvoice.service.InvoiceReqHService;
import com.deloitte.tms.vat.salesinvoice.service.InvoiceReqLService;
import com.deloitte.tms.vat.salesinvoice.service.InvoiceTrxPoolService;
import com.deloitte.tms.vat.controller.BaseController;
/**  
 *〈一句话功能简述〉
 * 功能详细描述
 * @author sqing
 * @create 2016年4月10日 上午10:10:55 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Controller
public class CiticInvoiceReqHController extends BaseController{
	@Autowired
	DictionaryService dictionaryService;
	@Resource
	InvoiceReqHService invoiceReqHService;
	@Autowired
	InvoiceReqLService invoiceReqLService;
	@Autowired
	InvoiceTrxPoolService invoiceTrxPoolService;
	@Autowired
	CiticInvoiceReqHService citicInvoiceReqHService;
	@RequestMapping(value = "/getCiticInvoiceReqIndex")
	public String getIndex() throws Exception{
		return "invoiceprint/citicinvoicereq";
		/*return "invoiceprint/nocustomer";*/
	}
	@ResponseBody
	@RequestMapping(value = "citicInvoiceReq/removeInvoiceReqHs", method = RequestMethod.POST)
	//@RoleAnnotation(roles=RoleDef.ECOMMERCE_ADMIN)
	public void removeInvoiceReqHs(@RequestParam String ids,HttpServletResponse response) throws IOException {
		AssertHelper.notEmpty_assert(ids,"需要删除的用户不能为空");
		String[] invoiceReqHIds=ids.split(",");
		JSONObject object=new JSONObject();
		try {
			invoiceReqHService.deleteFromReqAll(invoiceReqHIds);
			object.put("msg", "删除成功");
			object.put("success", "true");
		} catch (Exception e) {
			object.put("msg", "删除失败");
			object.put("success", "false");
		}
		retJson(response, object);
	}
	@ResponseBody
	@RequestMapping(value = "citicInvoiceReq/getNewReadyParam", method = RequestMethod.GET)
	public void getReadyParam(Map<String, Object> map,HttpServletResponse response) throws Exception {
		InvoiceReqHInParam inParam = new InvoiceReqHInParam();
		JsonConfig jsonConfig = new JsonConfig();  
		jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor("yyyy-MM-dd")); 
		Date d=new Date();
		String date=DateFormat(d);
		inParam.setCrvatInvoiceReqNumber(/*Long.toString(d.getTime())*/" ");
		inParam.setInvoiceReqDate(d);
		inParam.setApplicant(ContextUtils.getCurrentUserName());
		inParam.setStatus(getMessage(AppFormStatuEnums.DRAFT.getValue()));
		//inParam.setStatus(AppFormStatuEnums.DRAFT.getValue());
		inParam.setCreatedBy(ContextUtils.getCurrentUserName());
		//inParam.setOperationOrgCode("0001");
		JSONObject object=new JSONObject();
		JSONObject object1=JSONObject.fromObject(inParam, jsonConfig);
		object.put("invoiceReadyData", object1);
		retJson(response, object);
	}
	@RequestMapping(value = "citicInvoiceReq/getCustomerParam")
	public void getCustomerParam(@RequestParam Map<String, Object> map,HttpServletResponse response) throws IOException{
		Customer customer=invoiceReqHService.getCustomerParam(map);
		JSONObject object=new JSONObject();
		object.put("customer", customer);
		if(AssertHelper.isOrNotEmpty_assert(customer.getId())){
			object.put("success", true);
		}else{
			object.put("success", false);
		}
		retJson(response, object);
	}
	//@ResponseBody
	@RequestMapping(value = "citicInvoiceReq/transactionlist")
	public void getTransactionlist(@RequestParam Map<String, Object> map,HttpServletResponse response) throws Exception {
		map.put("orgId", ContextUtils.getCurrentOrgId());
		JSONObject result=new JSONObject();
		try {
			DaoPage page=invoiceReqHService.findInvoiceTrxPoolByParams(map, PageUtils.getPageNumber(map),PageUtils.getPageSize(map));
			List<InvoiceTrxPoolInParam>list=invoiceTrxPoolService.getAlltransaction(map);
			JsonConfig jsonConfig = new JsonConfig();  
			jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor("yyyy-MM-dd")); 
			JSONArray jsonArray1 = JSONArray.fromObject(page.getResult(),jsonConfig);
			result.put("total", page.getRecordCount());
			result.put("rows", jsonArray1.toString());
			String ids="";
			if(list.size()>0){
				for (int i = 0; i < list.size(); i++) {
					ids+=list.get(i).getId()+";";
				}
			}
			/*if(list.size()>0){
				result.put("customerId",list.get(0).getCustomerId());
			}*/
			result.put("allIds", ids);
			result.put("pages", page.getPageCount());
			result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("success", false);
			result.put("errorMessage", e.getLocalizedMessage());
		}
		retJson(response,result);
	}
	@RequestMapping(value = "citicInvoiceReq/getAlltransaction")
	public void getAlltransaction(@RequestParam Map<String, Object> map,HttpServletResponse response) throws Exception {
		//List<InvoiceTrxPool>list=invoiceTrxPoolService.getAlltransaction(map);
		List<InvoiceTrxPoolInParam>list=invoiceTrxPoolService.getAlltransaction(map);
		JsonConfig jsonConfig = new JsonConfig();  
		jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor("yyyy-MM-dd")); 
		JSONArray jsonArray1 = JSONArray.fromObject(list,jsonConfig);
		JSONObject result=new JSONObject();
		result.put("total", list.size());
		result.put("rows", jsonArray1.toString());
		retJson(response, result);
	}
	@RequestMapping(value = "citicInvoiceReq/saveCustomerAndReq")
	public void saveCustomerAndReq(@RequestParam Map<String, Object> map,HttpServletResponse response) throws Exception {
		JSONObject object=new JSONObject();
		try {
			citicInvoiceReqHService.saveCustomerAndReq(map);
			object.put("msg", "保存成功");
			object.put("success", "true");
		} catch (Exception e) {
			object.put("msg", "保存失败");
			object.put("success", "false");
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "citicInvoiceReq/withOutCustomerTransactionList")
	public void withOutCustomerTransactionList(@RequestParam Map<String, Object> map,HttpServletResponse response) throws Exception {
		//List<InvoiceTrxPool>list=invoiceTrxPoolService.getAlltransaction(map);
		//InvoiceTrxPool pool=(InvoiceTrxPool) invoiceTrxPoolService.get(InvoiceTrxPool.class, map.get("trxNumber").toString()); 
		InvoiceTrxPool pool=citicInvoiceReqHService.findInvoiceTrxPoolByParams(map);
		InvoiceTrxPoolInParam inParam = citicInvoiceReqHService.convertInvoiceTrxPoolToInParam(pool);
		List<InvoiceTrxPoolInParam> list = new ArrayList<InvoiceTrxPoolInParam>();
		list.add(inParam);
		JsonConfig jsonConfig = new JsonConfig();  
		jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor("yyyy-MM-dd")); 
		JSONArray object = JSONArray.fromObject(list,jsonConfig);
		JSONObject result=new JSONObject();
		result.put("total", list.size());
		result.put("rows", object.toString());
		result.put("lid", inParam.getCrvatTrxPoolId());
		retJson(response, result);
	}
	@RequestMapping(value = "citicInvoiceReq/saveInvoiceReqAll", method = RequestMethod.POST)
	//@RoleAnnotation(roles=RoleDef.ECOMMERCE_ADMIN)
	public void saveInvoiceReqAll(InvoiceReqHInParam inParam,HttpServletResponse response) throws Exception {
		JSONObject object = new JSONObject();
		try {
			inParam.setOrgId(ContextUtils.getCurrentOrgId());
			invoiceReqHService.saveInvoiceReqAll(inParam,new HashMap());
			object.put("msg", "保存成功");
			object.put("success", "true");
		} catch (Exception e) {
			object.put("msg", "保存失败");
			object.put("success", "false");
			e.printStackTrace();
		}
		retJson(response, object);
	}
	//申请单查询页面
	@RequestMapping(value = "citicInvoiceReq/getInvoiceReqAll", method = RequestMethod.POST)
	public void getInvoiceReqAll(@RequestParam Map<String, Object>map,HttpServletResponse response) throws Exception{
		DaoPage page=invoiceReqHService.findInvoiceReqAll(map, PageUtils.getPageNumber(map),PageUtils.getPageSize(map));
		JSONObject result=new JSONObject();
		JsonConfig jsonConfig = new JsonConfig();  
		jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor("yyyy-MM-dd")); 
		JSONArray jsonArray1 = JSONArray.fromObject(page.getResult(),jsonConfig);
		result.put("total", page.getRecordCount());
		result.put("rows", jsonArray1.toString());
		retJson(response,result);
	}
	//编辑申请单
	@RequestMapping(value = "citicInvoiceReq/getEditInfo", method = RequestMethod.POST)
	public void getEditInfo(@RequestParam Map<String, Object>map,HttpServletResponse response) throws IOException{
		JSONObject result=new JSONObject();
		DaoPage page=invoiceReqLService.findInvoiceReqLByParams(map, PageUtils.getPageNumber(map),PageUtils.getPageSize(map));
		//InvoiceReqH invoiceReqH= (InvoiceReqH) invoiceReqHService.findById(InvoiceReqH.class, map.get("crvatInvoiceReqHId").toString());
		JsonConfig jsonConfig = new JsonConfig();  
		jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor("yyyy-MM-dd")); 
		JSONArray jsonArray1 = JSONArray.fromObject(page.getResult(),jsonConfig);
		result.put("total", page.getRecordCount());
		result.put("rows", jsonArray1.toString());
		/*InvoiceReqHInParam inParam=invoiceReqHService.getEditInfo(invoiceReqH.getId());
		JSONObject object1= JSONObject.fromObject(inParam,jsonConfig);
		result.put("reqH", object1.toString());*/
		retJson(response,result);
	}
	@RequestMapping(value = "citicInvoiceReq/updateCommitStatus", method = RequestMethod.POST)
	public void updateCommitStatus(InvoiceReqHInParam inParam,HttpServletResponse response) throws IOException{
		JSONObject object = new JSONObject();
		String id = inParam.getId();
		String ids=inParam.getIds();
		try {
			Map<String, Object>map=new HashMap<String, Object>();
			map.put("id", id);
			map.put("rowsids",ids);
			map.put("customerId", inParam.getCustomerId());
			map.put("custRegistrationCode", inParam.getCustRegistrationCode());
			Date date = invoiceReqHService.getDatabaseServerDate();
			String dateString = DateUtils.format("yyyy-MM-dd", date);
			map.put("invoiceReqDate", dateString);
			map.put("reqInvoiceRange", inParam.getReqInvoiceRange());
			map.put("custRegistrationNumber", inParam.getCustRegistrationNumber());
			map.put("status",AppFormStatuEnums.SUBMITTED.getValue());
			map.put("orgId", ContextUtils.getCurrentOrgId());
			map.put("name", ContextUtils.getCurrentUserName());
			String hid=invoiceReqHService.setUpHead(map);
			invoiceReqHService.updateCommit(hid,new HashMap());
			object.put("msg", "提交成功");
			object.put("success", "true");
		} catch (Exception e) {
			object.put("msg", "提交失败");
			object.put("success", "false");
			e.printStackTrace();
		}
		retJson(response, object);
	}
	@RequestMapping(value="citicInvoiceReq/submitFromPage",method=RequestMethod.POST)
	public void submitFromPage(@RequestParam Map<String,Object>map,HttpServletResponse response) throws IOException{
		String allId=map.get("ids").toString();
		String[]ids=allId.split(",");
		JSONObject object = new JSONObject();
		Map<String, Object>pMap=new HashMap<String, Object>();
		List<InvoiceReqL>list=new ArrayList<InvoiceReqL>();
		try {
			for (int i = 0; i < ids.length; i++) {
				InvoiceReqH entity=(InvoiceReqH) invoiceReqHService.get(InvoiceReqH.class, ids[i]);
				/*pMap.put("id", ids[i]);
				String rowsids=invoiceReqHService.getRowsids(ids[i]);
				pMap.put("rowsids", rowsids);
				this.setUpHead(pMap);*/
				invoiceReqHService.updateCommit(ids[i],new HashMap());
			}
			object.put("msg", "提交成功");
			object.put("success", "true");
		} catch (Exception e) {
			object.put("msg", "提交失败");
			object.put("success", "false");
			e.printStackTrace();
		}
		retJson(response, object);
	}
	@RequestMapping(value="citicInvoiceReq/saveInvoiceReqHead",method=RequestMethod.POST)
	public void saveInvoiceReqHead(@RequestParam Map<String, Object>map,HttpServletResponse response) throws IOException{
		
		JSONObject result=new JSONObject();
		try {
			map.put("status",AppFormStatuEnums.DRAFT.getValue());
			map.put("orgId", ContextUtils.getCurrentOrgId());
			map.put("name", ContextUtils.getCurrentUserName());
			citicInvoiceReqHService.setUpHead(map);
			result.put("msg", "保存成功");
			result.put("success", "true");
		} catch (Exception e) {
			result.put("msg", "保存失败");
			result.put("success", "false");
			e.printStackTrace();
		}
		retJson(response,result);
	}
	private String DateFormat(Date d){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
		String str=sdf.format(d);  
		return str;
	}
}
