package com.deloitte.tms.vat.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.deloitte.tms.pl.core.commons.springmvc.propertyeditor.DateEditor;
import com.deloitte.tms.pl.core.commons.springmvc.propertyeditor.DoubleEditor;
import com.deloitte.tms.pl.core.commons.springmvc.propertyeditor.FloatEditor;
import com.deloitte.tms.pl.core.commons.springmvc.propertyeditor.IntegerEditor;
import com.deloitte.tms.pl.core.commons.springmvc.propertyeditor.LongEditor;
import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.commons.constant.TableColnumDef;
import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.vat.core.common.JsonDateValueProcessor;
import com.deloitte.tms.vat.core.common.MessageHelp;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;


/**
 * @author 
 */

@Controller
public  class BaseController {

	private Logger log = Logger.getLogger(this.getClass());
	public static final String IDSPE="#xfxDeloitte#";
	
	@Autowired  
	private  HttpServletRequest request; 

	
	/**
	 * 
	 * @param response
	 * @param jsonObject
	 * @throws IOException
	 */
	
	public void retJson(HttpServletResponse response, JSONObject jsonObject)
			throws IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/plain;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(jsonObject.toString());
		out.flush();
		out.close();
	}
	
	/**
	 * 
	 * @param response
	 * @param jsonObject
	 * @throws IOException
	 */
	public void retJsonArray(HttpServletResponse response, JSONArray jsonArray)
			throws IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/plain;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(jsonArray.toString());
		out.flush();
		out.close();
	}
	
	public Locale getLocale(){
		HttpSession httpSession = request.getSession();
		Locale locale = (Locale)httpSession.getAttribute("locale");
		if(AssertHelper.empty(locale)){
			locale = new Locale("zh", "CN");
		}
		return locale;		
	}
	
	public String getUserId(){
		HttpSession httpSession = request.getSession();
		String userId = (String)httpSession.getAttribute("userId");
		if(AssertHelper.empty(userId)){
			return "system";
		}
		return userId;
	}
	@InitBinder   
	protected void initBinder(WebDataBinder binder) {    
		binder.registerCustomEditor(Date.class, new DateEditor());   
	    binder.registerCustomEditor(int.class, new IntegerEditor());     
	    binder.registerCustomEditor(long.class, new LongEditor());    
	    binder.registerCustomEditor(double.class, new DoubleEditor());    
	    binder.registerCustomEditor(float.class, new FloatEditor());
	} 
	/**
	 * @author sqing
	 * 是否启用
	 */
	public String getIsUsed(String isUsed){
		if("Y".equals(isUsed)){
			return TableColnumDef.ENABLE_EFFECT;
		}else if("N".equals(isUsed)){
			return TableColnumDef.ENABLE_DISABLED;
		}else{
			return "";
		}
	}
	
	public String getMessage(String key){
		return MessageHelp.getMessage(key, getLocale());
	}

	
	public void returnOk( HttpServletResponse response, String msg ) throws Exception{
		
		JSONObject result = new JSONObject();
		result.put("success", true);
		result.put("msg", msg);
		
		retJson(response, result);
		
	}
	
	
	public void returnOk( HttpServletResponse response) throws Exception{
		
		JSONObject result = new JSONObject();
		this.returnOk(result, response);
	}
	
	public void returnOk(JSONObject result, HttpServletResponse response) throws Exception{
		
		result.put("success", true);
		result.put("msg", this.getMessage("save.sucess"));
		
		retJson(response, result);
	}
	
	
	public void returnFail(HttpServletResponse response){
		
		JSONObject result = new JSONObject();
		this.returnFail(result, response);
	}
	
	
	public void returnFail(HttpServletResponse response, String errorMsg){
		
		try{
		JSONObject result = new JSONObject();
		result.put("success", false);
		result.put("fail", true);
		result.put("errorMsg", errorMsg);
		
		this.retJson(response, result);
		}catch(Exception e){
			e.printStackTrace();
		}
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
	
	
	public void returnList( HttpServletResponse response, int total, List<?> list ) throws Exception{
		
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class,
				new JsonDateValueProcessor("yyyy-MM-dd"));
		JSONArray jsonArray = JSONArray.fromObject(list, jsonConfig);
		JSONObject result = new JSONObject();
		result.put("success", true);
		result.put("total", total);
		result.put("rows", jsonArray);
		retJson(response, result);
	}
	
	public void returnFromDaoPage( HttpServletResponse response, DaoPage daoPage ) throws Exception{
		
		int total = daoPage.getTotal();
		
		Collection<?> list = daoPage.getResult();
		
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class,
				new JsonDateValueProcessor("yyyy-MM-dd"));
		JSONArray jsonArray = JSONArray.fromObject(list, jsonConfig);
		JSONObject result = new JSONObject();
		result.put("success", true);
		result.put("total", total);
		result.put("rows", jsonArray);
		retJson(response, result);
	}
	

}
