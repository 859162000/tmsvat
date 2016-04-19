package com.deloitte.tms.vat.salesinvoice.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.deloitte.tms.base.masterdata.model.BaseOrg;
import com.deloitte.tms.base.masterdata.model.Customer;
import com.deloitte.tms.base.masterdata.model.TmsMdEquipment;
import com.deloitte.tms.base.masterdata.model.TmsMdLegalEntity;
import com.deloitte.tms.base.masterdata.model.TmsMdTaxTrxType;
import com.deloitte.tms.base.taxsetting.model.TmsMdContract;
import com.deloitte.tms.base.taxsetting.model.TmsMdInventoryItems;
import com.deloitte.tms.base.taxsetting.model.TmsMdProject;
import com.deloitte.tms.base.taxsetting.model.TmsMdTrxAffirmSetting;
import com.deloitte.tms.pl.cache.utils.DictionaryCacheUtils;
import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.core.commons.utils.PageUtils;
import com.deloitte.tms.pl.core.commons.utils.SpringUtil;
import com.deloitte.tms.pl.core.commons.utils.reflect.ReflectUtils;
import com.deloitte.tms.pl.core.context.utils.ContextUtils;
import com.deloitte.tms.pl.core.dao.impl.BaseDaoSimple;
import com.deloitte.tms.pl.dictionary.model.DictionaryEntity;
import com.deloitte.tms.pl.flow.utils.FlowHelper;
import com.deloitte.tms.pl.security.model.SecurityPosition;
import com.deloitte.tms.pl.security.model.SecurityUser;
import com.deloitte.tms.vat.controller.BaseController;
import com.deloitte.tms.vat.core.common.IdGenerator;
import com.deloitte.tms.vat.core.common.JsonDateValueProcessor;
import com.deloitte.tms.vat.salesinvoice.model.InvoiceReqHInParam;
import com.deloitte.tms.vat.salesinvoice.model.TmsCrvatInvReqBatchesH;
import com.deloitte.tms.vat.salesinvoice.model.TmsCrvatInvReqBatchesL;
import com.deloitte.tms.vat.salesinvoice.model.TmsCrvatInvReqBatchesLInParam;
import com.deloitte.tms.vat.salesinvoice.service.InvoiceSpecialContractBathService;
import com.deloitte.tms.vat.salesinvoice.service.impl.InvoiceSpecialContractBathServiceImpl;
@Controller
@RequestMapping(value="invoiceSpecialContractBathController")
public class InvoiceSpecialContractBathController extends BaseController {
	@Resource
	InvoiceSpecialContractBathService invoiceSpecialContractBathServiceImpl;
	/**
	 * 跳转批量上传页面
	 * @return
	 */
	@RequestMapping(value="/toInvoiceSpecialContractBath")
    public String getIndex(){
		return "invoiceprint/invoiceSpecialContractBath";
    	
    }
	/**
	 * 上传附件
	 * @param sourceFile
	 * @throws IOException 
	 */
	@RequestMapping(value="/contractNumberSave")
	public void contractNumberSave(@RequestParam MultipartFile sourceFile,HttpServletResponse response) throws IOException{
		String fileName = sourceFile.getOriginalFilename();//得到文件名称
		InputStream  inputstream  = null;
		try {
			inputstream = sourceFile.getInputStream();//读取文件流
			} catch (IOException e) {
			e.printStackTrace();
		}
		List<String[]>  list = new ExcelReader(inputstream).getAllData();//读取excel数据
		System.out.println(list.get(0).length);
		if(list.size()==1&&list.get(0).length==1){//读取excel数据错误
			JSONObject result=new JSONObject();  
			JSONArray jsonArray = JSONArray.fromObject(list);
			result.put("success", "erro");
			result.put("rows", jsonArray.toString());
		    retJson(response,result);
		}else{//读取excel数据完毕
			//对解析数据进行处理
			DaoPage daoPage = invoiceSpecialContractBathServiceImpl.findTmsCrvatInvReqBatchesHInParam(list);
			if(daoPage.getPageIndex()!=-1){
			List<TmsCrvatInvReqBatchesLInParam> li = (List<TmsCrvatInvReqBatchesLInParam>) daoPage.getResult();
			JSONObject result=new JSONObject();
			JsonConfig jsonConfig = new JsonConfig();  
			jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor("yyyy-MM-dd")); 
			JSONArray jsonArray = JSONArray.fromObject(daoPage.getResult(),jsonConfig);
			result.put("total", daoPage.getRecordCount());
			result.put("success", "success");
			result.put("invoiceAmount", li.get(li.size()-1).getInvoiceAmounts());
			result.put("rows", jsonArray.toString());
			retJson(response,result);
			}else{
				JSONObject result=new JSONObject();  
				JSONArray jsonArray = JSONArray.fromObject(daoPage.getResult());
				result.put("success", "erro");
				result.put("rows", jsonArray.toString());
			    retJson(response,result);
			}
		}
		
		
		
		
	}
	
	/**
	 * 开票申请单号生成
	 * @throws IOException 
	 */
	@RequestMapping(value="/getCreanumber")
	public void creanumber(HttpServletResponse response) throws IOException{
		
		TmsCrvatInvReqBatchesLInParam tmsCrvatInvReqBatchesLInParam = getTmsCrvatInvReqBatchesLInParam(response);
		String invoicereq = FlowHelper.getNextFlowNo("INVOICEREQ");//开票申请单号生成
		tmsCrvatInvReqBatchesLInParam.setCrvatInvoiceReqNumber(invoicereq);
		JSONObject object=new JSONObject();
		object.put("result", tmsCrvatInvReqBatchesLInParam);
		retJson(response, object);
		
	}
	/**
	 * 保存申请单
	 * @throws ParseException 
	 */
	@RequestMapping(value="/saveInvoiceReqHead")
	public void saveInvoiceReqHead(@RequestParam Map<String, Object>map,HttpServletResponse response) throws ParseException{
		String appuseruuid = (String) map.get("appuseruuid");//特殊批量开票申请ID
		String tip = (String) map.get("tips");//特殊批量开票申请ID
		
		
		TmsCrvatInvReqBatchesH tmsCrvatInvReqBatchesH = new TmsCrvatInvReqBatchesH();//特殊批量开票申请行
		tmsCrvatInvReqBatchesH.setCrvatInvoiceReqNumber((String) map.get("crvatInvoiceReqNumber"));//申请单编号
		tmsCrvatInvReqBatchesH.setLegalEntityId((String) map.get("legalEntityId"));//销方纳税人实体ID
		tmsCrvatInvReqBatchesH.setInvoiceReqDate(new SimpleDateFormat("yyyy-MM-dd").parse((String) map.get("invoiceReqDate")));//申请日期
		tmsCrvatInvReqBatchesH.setStatus((String) map.get("status"));//申请状态
		tmsCrvatInvReqBatchesH.setIsReceipts((String) map.get("isReceipts"));//是否已收款
		tmsCrvatInvReqBatchesH.setOperationOrgCode(ContextUtils.getCurrentOrgId());//组织id
		tmsCrvatInvReqBatchesH.setEquipmentId((String) map.get("equipmentId"));//终端设备ID 
		tmsCrvatInvReqBatchesH.setIsTax("0");//是否含税
		tmsCrvatInvReqBatchesH.setAttribute1((String) map.get("invoiceAmount"));//总金额
		
		int tips = 1;
      if("submit".equals(tip)){
    	  tmsCrvatInvReqBatchesH.setStatus("30");//申请状态
		}
		if(AssertHelper.empty(appuseruuid)){
			tmsCrvatInvReqBatchesH.setId(IdGenerator.getUUID());
			invoiceSpecialContractBathServiceImpl.save(tmsCrvatInvReqBatchesH);
			tips = 0;
		}else{
			tmsCrvatInvReqBatchesH.setId(appuseruuid);
			invoiceSpecialContractBathServiceImpl.update(tmsCrvatInvReqBatchesH);
			tips = 1;
		}
		
		JSONArray jsonArray = JSONArray.fromObject(map.get("dgrequestdetaildata"));
		for(int i=0;i<jsonArray.size(); i++){
			JSONObject jsonJ = jsonArray.getJSONObject(i);
			
			TmsCrvatInvReqBatchesLInParam tmsCrvatInvReqBatchesLInParam = (TmsCrvatInvReqBatchesLInParam) jsonJ.toBean(jsonJ, TmsCrvatInvReqBatchesLInParam.class);
			
			
			TmsCrvatInvReqBatchesL tmsCrvatInvReqBatchesL = new TmsCrvatInvReqBatchesL();
			try{
			ReflectUtils.copyProperties(tmsCrvatInvReqBatchesLInParam, tmsCrvatInvReqBatchesL);
			if("是".equals(tmsCrvatInvReqBatchesL.getIsTax())){
				tmsCrvatInvReqBatchesL.setIsTax("1");
			}else{
				tmsCrvatInvReqBatchesL.setIsTax("0");
			}
			tmsCrvatInvReqBatchesL.setOrgId(ContextUtils.getCurrentOrgId());//设置组织id
			tmsCrvatInvReqBatchesL.setBizOrgCode(ContextUtils.getCurrentOrgId());//运营组织编码
			tmsCrvatInvReqBatchesL.setCreatedBy(ContextUtils.getCurrentUserName());//创建人
			tmsCrvatInvReqBatchesL.setModifiedBy(ContextUtils.getCurrentUserName());//最后修改人flag
			tmsCrvatInvReqBatchesL.setFlag("1");//删除
			}catch(Exception e){
				System.out.println(e);
			}
			tmsCrvatInvReqBatchesL.setCrvatInvReqBatchesHId(tmsCrvatInvReqBatchesH.getId());//设置特殊批量开票申请ID
			
			if(tips==0&&AssertHelper.empty(tmsCrvatInvReqBatchesL.getId())){
				tmsCrvatInvReqBatchesL.setId(IdGenerator.getUUID());
			invoiceSpecialContractBathServiceImpl.save(tmsCrvatInvReqBatchesL);
			}else if(tips==1){
			invoiceSpecialContractBathServiceImpl.update(tmsCrvatInvReqBatchesL);
			}
			
			}
		
	
		
		
		
	}
	
	/**
	 * 特殊申请单批量数据查询
	 * @param map
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/getInvoiceReqAll", method = RequestMethod.POST)
	public void getInvoiceReqAll(@RequestParam Map<String, Object>map,HttpServletResponse response) throws Exception{
		
		
		DaoPage page=invoiceSpecialContractBathServiceImpl.findInvoiceReqAll(map, PageUtils.getPageNumber(map),PageUtils.getPageSize(map));
		JSONObject result=new JSONObject();
		JsonConfig jsonConfig = new JsonConfig();  
		jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor("yyyy-MM-dd")); 
		JSONArray jsonArray1 = JSONArray.fromObject(page.getResult(),jsonConfig);
		result.put("total", page.getRecordCount());
		result.put("rows", jsonArray1.toString());
		retJson(response,result);
	}
	/**
	 * 纳税主体相关信息查询
	 * @param response
	 */
	public TmsCrvatInvReqBatchesLInParam getTmsCrvatInvReqBatchesLInParam(HttpServletResponse response){
		String orgId = ContextUtils.getCurrentOrgId();//得到组织id
		BaseOrg baseOrg = invoiceSpecialContractBathServiceImpl.getOrg(orgId);
		TmsMdLegalEntity  tmsMdLegalEntity = invoiceSpecialContractBathServiceImpl.getRegistrationNumber(orgId);//查询纳税主体信息
		String legalEntityId = tmsMdLegalEntity.getId();//纳税主体id
		TmsMdEquipment tmsMdEquipment = invoiceSpecialContractBathServiceImpl.getTmsMdEquipment(legalEntityId);//查询终端信息
		TmsCrvatInvReqBatchesLInParam tmsCrvatInvReqBatchesLInParam = new TmsCrvatInvReqBatchesLInParam();
		
		ReflectUtils.copyProperties(tmsMdEquipment, tmsCrvatInvReqBatchesLInParam);
		ReflectUtils.copyProperties(tmsMdLegalEntity, tmsCrvatInvReqBatchesLInParam);
		ReflectUtils.copyProperties(baseOrg, tmsCrvatInvReqBatchesLInParam);
		tmsCrvatInvReqBatchesLInParam.setLegalEntityId(tmsMdLegalEntity.getId());
		return tmsCrvatInvReqBatchesLInParam;
	}
	
	/**
	 * 编辑纳税主体查询
	 * @throws IOException 
	 */
	@RequestMapping(value="/getTmsMdLegalEntity")
	public void getTmsMdLegalEntity(HttpServletResponse response) throws IOException{
		TmsCrvatInvReqBatchesLInParam tmsCrvatInvReqBatchesLInParam = getTmsCrvatInvReqBatchesLInParam(response);
		JSONObject object=new JSONObject();
		object.put("result", tmsCrvatInvReqBatchesLInParam);
		retJson(response, object);
		
	}
	/**
	 * 编辑申请单明细查询
	 * @throws IOException 
	 */
	@RequestMapping(value="/listTmsCrvatInvReqBatchesL")
	public void listTmsCrvatInvReqBatchesL(@RequestParam Map<String, Object>map,HttpServletResponse response) throws IOException{
		DaoPage daoPage = invoiceSpecialContractBathServiceImpl.findTmsCrvatInvReqBatchesLInParam(map,Integer.parseInt((String) map.get("page")),
				Integer.parseInt((String) map.get("rows")));
		List<TmsCrvatInvReqBatchesLInParam> li = (List<TmsCrvatInvReqBatchesLInParam>) daoPage.getResult();
		JSONObject result=new JSONObject();
		JsonConfig jsonConfig = new JsonConfig();  
		jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor("yyyy-MM-dd")); 
		JSONArray jsonArray1 = JSONArray.fromObject(daoPage.getResult(),jsonConfig);
		result.put("total", daoPage.getRecordCount());
		result.put("success", "success");
		if(li.size()>0){
		result.put("invoiceAmount", li.get(li.size()-1).getInvoiceAmounts());
		}
		result.put("rows", jsonArray1.toString());
		retJson(response,result);
		
	}
	
	/**
	 * 下载模板
	 * @throws IOException 
	 */
	@RequestMapping(value="/download")
	public String getExcel(HttpServletRequest request,HttpServletResponse response) throws IOException{		
		String webPath =  request.getSession().getServletContext().getRealPath("/")+"/uploadFolder/批量开票测试模板.xlsx";  
	        File file=new File(webPath);  
	        //String fileName=new String("批量开票测试模板.xlsx".getBytes("UTF-8"),"iso-8859-1");
	        OutputStream os = response.getOutputStream();  
	        try {  
	        	response.reset();  
	        	response.setHeader("Content-Disposition", "attachment; filename="+java.net.URLEncoder.encode("批量开票测试模板.xlsx", "UTF-8"));  
	        	response.setContentType("application/octet-stream; charset=utf-8");  
	            os.write(FileUtils.readFileToByteArray(file));  
	            os.flush();  
	        } finally {  
	            if (os != null) {  
	                os.close();  
	            }  
	        }  
	        
	        
	        
	        return null;
	}
	/**
	 * 删除申请单
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="/removeInvoiceReqHs")
	public String removeInvoiceReqHs(@RequestParam String ids,HttpServletResponse response) throws IOException{
		int count = invoiceSpecialContractBathServiceImpl.removeInvoiceReqHs(ids);
		JSONObject result=new JSONObject();
		result.put("success", count);
		retJson(response,result);
		return null;
	}
	
	/**
	 * 提交申请单
	 * @param ps
	 * @throws IOException 
	 */
	@RequestMapping(value="/submitFromPage")
	public void submitFromPage(@RequestParam String ids,HttpServletResponse response) throws IOException{
		int count = invoiceSpecialContractBathServiceImpl.submitFromPage(ids);
		JSONObject result=new JSONObject();
		result.put("success", true);
		retJson(response,result);
	}
	
}
