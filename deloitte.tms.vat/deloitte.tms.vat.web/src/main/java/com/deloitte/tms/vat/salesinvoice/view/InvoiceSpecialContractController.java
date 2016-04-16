package com.deloitte.tms.vat.salesinvoice.view;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.deloitte.tms.base.masterdata.model.Customer;
import com.deloitte.tms.base.taxsetting.model.TmsMdContract;
import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.commons.utils.PageUtils;
import com.deloitte.tms.pl.dictionary.model.DictionaryEntity;
import com.deloitte.tms.pl.dictionary.service.DictionaryService;
import com.deloitte.tms.pl.flow.utils.FlowHelper;
import com.deloitte.tms.vat.controller.BaseController;
import com.deloitte.tms.vat.core.common.JsonDateValueProcessor;
import com.deloitte.tms.vat.salesinvoice.model.InvoiceReqHInParam;
import com.deloitte.tms.vat.salesinvoice.service.InvoiceSpecialContractService;

@Controller
@RequestMapping(value="isContractController")
public class InvoiceSpecialContractController extends BaseController{
	@Resource
	InvoiceSpecialContractService invoiceSpecialContractServiceImpli;
	@Autowired
	DictionaryService dictionaryService;
	/**跳转手工发票有合同页面
	 * @return
	 */
	@RequestMapping(value="/getIndex")
	public String getIndex(){
		System.out.println("invoiceSpecialContract");
		return "invoiceprint/invoiceSpecialContract";
	}
	
	/**
	 * 特殊申请单数据查询
	 * @param map
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/getInvoiceReqAll", method = RequestMethod.POST)
	public void getInvoiceReqAll(@RequestParam Map<String, Object>map,HttpServletResponse response) throws Exception{
		DaoPage page=invoiceSpecialContractServiceImpli.findInvoiceReqAll(map, PageUtils.getPageNumber(map),PageUtils.getPageSize(map));
		JSONObject result=new JSONObject();
		JsonConfig jsonConfig = new JsonConfig();  
		jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor("yyyy-MM-dd")); 
		JSONArray jsonArray1 = JSONArray.fromObject(page.getResult(),jsonConfig);
		result.put("total", page.getRecordCount());
		result.put("rows", jsonArray1.toString());
		retJson(response,result);
	}
    /**
     * 申请单状态查询
     * @param response
     * @throws IOException
     */
	@RequestMapping(value = "/getdictionary", method = RequestMethod.GET)    
    public void getDictionaryEntitiesByParentCode(HttpServletResponse response) throws IOException{
          Collection<DictionaryEntity> results = dictionaryService.loadDictionaryEntities("VAT_CR_INVOICE_APPFORM_STATUS");
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
	/**
	 * 受理层级查询
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/getLeveldictionary", method = RequestMethod.GET)    
    public void getLeveldictionary(HttpServletResponse response) throws IOException{
          Collection<DictionaryEntity> results = dictionaryService.loadDictionaryEntities("VAT_CR_INVOICE_PRINT_SCOPE");
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
    /**
     * 购方证件类型数据查询
     * @param response
     * @throws IOException
     */
	@RequestMapping(value = "/getTypedictionary", method = RequestMethod.GET)    
    public void getTypedictionary(HttpServletResponse response) throws IOException{
          Collection<DictionaryEntity> results = dictionaryService.loadDictionaryEntities("VAT_CUSTOMER_DISC_OPTION");
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
     /**
             根据填写购方编码，购方证件号码查询
      * @param map
      * @param response
      * @throws IOException
      */
	@RequestMapping(value = "getCustomerParam")
	public void getCustomerParam(@RequestParam Map<String, Object> map,HttpServletResponse response) throws IOException{
		Customer customer=invoiceSpecialContractServiceImpli.getCustomerParam(map);
		JSONObject object=new JSONObject();
		object.put("customer", customer);
		retJson(response, object);
	}
    /**
     * 合同信息查询
     * @param parameter
     * @param response
     * @throws IOException
     */
	@RequestMapping("/searchContract")
	public void searchContract(@RequestParam Map<String, Object> parameter,	HttpServletResponse response) throws IOException {
		
		DaoPage daoPage = invoiceSpecialContractServiceImpli.findTmsMdContractByParams(parameter,
				Integer.parseInt((String) parameter.get("page")),
				Integer.parseInt((String) parameter.get("rows")));
		List<TmsMdContract> list = (List<TmsMdContract>) daoPage.getResult();
		JsonConfig jsonConfig = new JsonConfig();
		JSONArray jsonArray = JSONArray.fromObject(list, jsonConfig);
		JSONObject result = new JSONObject();
		result.put("total", daoPage.getTotal());
		result.put("rows", jsonArray);
		retJson(response, result);	
	}
    /**
     * 商品及服务分类信息查询
     * @param map
     * @param response
     * @throws Exception
     */
	@RequestMapping(value = "/getTmsMdInventoryCategories", method = RequestMethod.POST)
	public void getTmsMdInventoryCategories(@RequestParam Map<String, Object>map,HttpServletResponse response) throws Exception{
		DaoPage page=invoiceSpecialContractServiceImpli.findTmsMdInventoryItemsByParams(map,Integer.parseInt((String) map.get("page")),
				Integer.parseInt((String) map.get("rows")));
		JSONObject result=new JSONObject();
		JsonConfig jsonConfig = new JsonConfig();  
		jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor("yyyy-MM-dd")); 
		JSONArray jsonArray1 = JSONArray.fromObject(page.getResult(),jsonConfig);
		result.put("total", page.getRecordCount());
		result.put("rows", jsonArray1.toString());
		retJson(response,result);
	}
	/**
	 * 开票申请单号生成
	 */
	@RequestMapping(value="/getCreanumber")
	public void creanumber(HttpServletResponse response){
		String invoicereq = FlowHelper.getNextFlowNo("INVOICEREQ");//开票申请单号生成
		JSONObject result=new JSONObject();
		result.put("invoicereq", invoicereq);
		try {
			retJson(response,result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 保存申请单
	 */
	@RequestMapping(value="/saveInvoiceReqHead")
	public void saveInvoiceReqHead(@RequestParam Map<String, Object>map,HttpServletResponse response){
		System.out.println(map.get("dgrequestdetaildata"));

		JSONArray jsonArray = JSONArray.fromObject(map.get("dgrequestdetaildata"));
		JSONArray contractdatagriddataJsonArray = JSONArray.fromObject(map.get("contractdatagriddata"));
		for(int i=0;i<jsonArray.size(); i++){
			JSONObject jsonJ = jsonArray.getJSONObject(i);
			System.out.println(jsonJ.getString("id"));//商品及服务id
			System.out.println(jsonJ.getString("inventoryItemDescripton"));//商品及服务名称
			System.out.println(jsonJ.getString("inventoryItemModels"));//规格型号
			System.out.println(jsonJ.getString("uomCode"));//单位
			System.out.println(jsonJ.getString("taxTrxTypeCode"));//数量
			System.out.println(jsonJ.getString("legalEntityName"));//单价
			System.out.println(jsonJ.getString("legalEntityCode"));//合计金额
			System.out.println(jsonJ.getString("taxRate"));//税率
			System.out.println(jsonJ.getString("trxDate"));//净额
			System.out.println(jsonJ.getString("inventory"));//税额
			}
		for(int i=0;i<contractdatagriddataJsonArray.size(); i++){
			JSONObject jsonJ = contractdatagriddataJsonArray.getJSONObject(i);
			System.out.println(jsonJ.getString("id"));//id
			System.out.println(jsonJ.getString("contractNumber"));//合同编号
			System.out.println(jsonJ.getString("contractAmount"));//合同金额
			System.out.println(jsonJ.getString("projectNumber"));//项目编号
			System.out.println(jsonJ.getString("projectName"));//项目名称
			System.out.println(jsonJ.getString("projectAmount"));//项目金额
			System.out.println(jsonJ.getString("projectAmountcok"));//项目累计金额
			System.out.println(jsonJ.getString("projectAmountrek"));//项目开票金额
			System.out.println(jsonJ.getString("contractAmountcok"));//合同累计金额
			System.out.println(jsonJ.getString("contractAmountrek"));//合同开票金额
		}
		
		InvoiceReqHInParam invoicereqhinparam = new InvoiceReqHInParam();//销项税开票申请单-头表数据
		
		
		
	}
	
	
}
