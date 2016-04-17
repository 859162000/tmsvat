
/**    
 * Copyright (C),Deloitte
 * @FileName: NoCustomerReqController.java  
 * @Package: com.deloitte.tms.vat.salesinvoice.view  
 * @Description: //模块目的、功能描述  
 * @Author sqing  
 * @Date 2016年4月14日 下午8:33:34  
 * @History: //修改记录
 * 〈author〉      〈time〉      〈version〉       〈desc〉
 * 修改人姓名            修改时间            版本号              描述   
*/  

package com.deloitte.tms.vat.salesinvoice.view;

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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.deloitte.tms.pl.core.commons.exception.BusinessException;
import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.commons.utils.PageUtils;
import com.deloitte.tms.pl.core.commons.utils.reflect.ReflectUtils;
import com.deloitte.tms.pl.core.context.utils.ContextUtils;
import com.deloitte.tms.pl.dictionary.service.DictionaryService;
import com.deloitte.tms.vat.base.enums.CrvatTaxPoolStatuEnums;
import com.deloitte.tms.vat.controller.BaseController;
import com.deloitte.tms.vat.core.common.IdGenerator;
import com.deloitte.tms.vat.core.common.JsonDateValueProcessor;
import com.deloitte.tms.vat.salesinvoice.model.InvoiceTrxPool;
import com.deloitte.tms.vat.salesinvoice.model.InvoiceTrxPoolInParam;
import com.deloitte.tms.vat.salesinvoice.model.TempTmsCrvatInvoiceReqL;
import com.deloitte.tms.vat.salesinvoice.service.CiticInvoiceReqHService;
import com.deloitte.tms.vat.salesinvoice.service.InvoiceReqHService;
import com.deloitte.tms.vat.salesinvoice.service.InvoiceReqLService;
import com.deloitte.tms.vat.salesinvoice.service.InvoiceTrxPoolService;


/**  
 *〈一句话功能简述〉
 * 无主交易申请单页面
 * @author sqing
 * @create 2016年4月14日 下午8:33:34 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Controller
public class NoCustomerReqController extends BaseController{
	@Autowired
	InvoiceTrxPoolService invoiceTrxPoolService;
	@Autowired
	CiticInvoiceReqHService citicInvoiceReqHService;
	@RequestMapping(value = "/getNoCustomerIndex")
	public String getIndex() throws Exception{
		return "invoiceprint/nocustomer";
	}
	/**
	 * 
	 * 根据交易流水搜索无主交易
	 * @param map
	 * @see [相关类/方法]（可选）
	 */
	//@ResponseBody
	@RequestMapping(value = "nocustomer/noCustomerTransactionList")
	public void withOutCustomerTransactionList(@RequestParam Map<String, Object> map,HttpServletResponse response) throws Exception {
		//List<InvoiceTrxPool>list=invoiceTrxPoolService.getAlltransaction(map);
		//InvoiceTrxPool pool=(InvoiceTrxPool) invoiceTrxPoolService.get(InvoiceTrxPool.class, map.get("trxNumber").toString()); 
		JSONObject result=new JSONObject();
		try {
			InvoiceTrxPool pool=citicInvoiceReqHService.findInvoiceTrxPoolByParams(map);
			InvoiceTrxPoolInParam inParam = citicInvoiceReqHService.convertInvoiceTrxPoolToInParam(pool);
			List<InvoiceTrxPoolInParam> list = new ArrayList<InvoiceTrxPoolInParam>();
			list.add(inParam);
			JsonConfig jsonConfig = new JsonConfig();  
			jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor("yyyy-MM-dd")); 
			JSONArray object = JSONArray.fromObject(list,jsonConfig);
			result.put("success", true);
			result.put("total", list.size());
			result.put("rows", object.toString());
			result.put("lid", inParam.getCrvatTrxPoolId());
		} catch (Exception e) {
			result.put("success", false);
			result.put("errorMessage", e.getLocalizedMessage());
		}
		retJson(response, result);
	}
	/**
	 * 
	 * 将选中的交易流水保存到临时表
	 * @param map
	 * @param response
	 * @throws Exception
	 * @see 
	 */
	@RequestMapping(value = "nocustomer/saveTempReqL")
	public void saveTempReqL(@RequestParam Map<String, Object> map,HttpServletResponse response) throws Exception {
		TempTmsCrvatInvoiceReqL entity=new TempTmsCrvatInvoiceReqL();
		//List<TempTmsCrvatInvoiceReqL>list = new ArrayList<TempTmsCrvatInvoiceReqL>();
		List<InvoiceTrxPoolInParam>list=new ArrayList<InvoiceTrxPoolInParam>();
		JSONObject result=new JSONObject();
		try {
			InvoiceTrxPool pool=(InvoiceTrxPool) invoiceTrxPoolService.get(InvoiceTrxPool.class, map.get("id").toString());
			InvoiceTrxPoolInParam inParam=new InvoiceTrxPoolInParam();
			ReflectUtils.copyProperties(pool, inParam);
			inParam.setId(pool.getId());
			inParam.setIsExitsCustomer("1");
			inParam.setInvoicereqhid(map.get("reqHID").toString());
			list.add(inParam);
			invoiceTrxPoolService.insertTempTmsCrvatReqL(list);
			pool.setStatus(ContextUtils.getCurrentUserName());
			invoiceTrxPoolService.update(pool);
			result.put("success", true);
		} catch (Exception e) {
			result.put("success", false);
			result.put("errorMessage", e.getLocalizedMessage());
		}
		retJson(response, result);
	}
	/**
	 * 分页加载当前被选中的交易流水
	 */
	@ResponseBody
	@RequestMapping(value = "nocustomer/loadNoCustomer")
	public DaoPage loadNoCustomer(@RequestParam Map<String, Object> map,HttpServletResponse response) throws Exception {
		map.put("crvatInvoiceReqHId", map.get("reqHID"));
		DaoPage page=invoiceTrxPoolService.findInvoiceTempReqLByParams(map, PageUtils.getPageNumber(map),PageUtils.getPageSize(map));
		return page;
	}
	/**
	 * 在页面上删除交易流水 nocustomer/removeTempInvoice
	 */
	@ResponseBody
	@RequestMapping(value = "nocustomer/removeTempInvoice")
	public void removeTempInvoice(@RequestParam Map<String, Object> map,HttpServletResponse response) throws Exception {
		JSONObject result=new JSONObject();
		try {
			String allId=map.get("ids").toString();
			String[]ids=allId.split(",");
			JSONObject object = new JSONObject();
			Map<String, Object>pMap=new HashMap<String, Object>();
			invoiceTrxPoolService.deleteTempCrvatInvoiceRelById(ids);;
			result.put("success", true);
			result.put("msg", "删除成功");
		} catch (Exception e) {
			result.put("success", false);
			result.put("errorMessage", e.getLocalizedMessage());
		}
		retJson(response, result);
	}
	/*
	 * 
	 */
	@RequestMapping(value = "nocustomer/getReqhId")
	public void getReqhId(@RequestParam Map<String, Object> map,HttpServletResponse response) throws Exception {
		JSONObject result=new JSONObject();
		try {
			String reqHId=IdGenerator.getUUID();
			result.put("reqHID", reqHId);
			result.put("success", true);
		} catch (Exception e) {
			result.put("success", false);
		}
		retJson(response, result);
	}
	/**
	 * nocustomer/saveCustomerAndReq
	 */
	@RequestMapping(value = "nocustomer/saveCustomerAndReq")
	public void saveCustomerAndReq(@RequestParam Map<String, Object> map,HttpServletResponse response) throws Exception {
		JSONObject result=new JSONObject();
		try {
			map.put("orgId", ContextUtils.getCurrentOrgId());
			map.put("name", ContextUtils.getCurrentUserName());
			citicInvoiceReqHService.saveCustomerAndReq(map);
			result.put("msg", "保存成功");
			result.put("success", true);
		} catch (Exception e) {
			result.put("msg", "保存失败");
			result.put("success", false);
			result.put("errorMessage", e.getLocalizedMessage());
			e.printStackTrace();
		}
		retJson(response, result);
	}
}
