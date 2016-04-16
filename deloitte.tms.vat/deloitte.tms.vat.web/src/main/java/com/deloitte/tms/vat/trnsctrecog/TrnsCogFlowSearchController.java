
/**    
 * Copyright (C),Deloitte
 * @FileName: SourceSubjectController.java  
 * @Package: com.deloitte.tms.vat.controller  
 * @Description: //模块目的、功能描述  
 * @Author weijia  
 * @Date 2016年3月15日 下午4:37:49  
 * @History: //修改记录
 * 〈author〉      〈time〉      〈version〉       〈desc〉
 * 修改人姓名            修改时间            版本号              描述   
*/  

package com.deloitte.tms.vat.trnsctrecog;


import java.util.Date;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.commons.utils.PageUtils;
import com.deloitte.tms.vat.controller.BaseController;
import com.deloitte.tms.vat.core.common.JsonDateValueProcessor;
import com.deloitte.tms.vat.salesinvoice.service.InvoiceSendHService;

/**  
 * 来源数据结构定义controller
 * 功能详细描述
 * @author weijia
 * @create 2016年3月15日 下午4:37:49 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Controller
@RequestMapping("trnsctrecog")
public class TrnsCogFlowSearchController  extends BaseController{
	
	@Resource
	InvoiceSendHService invoiceSendHService;
	
	@RequestMapping(value = "/trnsCognFlowSearch", method = RequestMethod.GET)
	public String invoiceSendHInit() throws Exception {
		return "vat/trnsctrecog/trnsCognFlowSearch";
	}
	
	@ResponseBody
	@RequestMapping(value = "/loadTrnsCogFlowSearchPage")
	public void loadTrnsCogFlowSearchPage(HttpServletResponse response,
			@RequestParam Map<String, Object> parameter) throws Exception {
		DaoPage daoPage = invoiceSendHService.findInvoiceSendHByParams(
				parameter, PageUtils.getPageNumber(parameter),
				PageUtils.getPageSize(parameter));// (parameter,PageUtils.getPageNumber(parameter),PageUtils.getPageSize(parameter));
		
		JSONObject result = new JSONObject();
		 JsonConfig jsonConfig = new JsonConfig();  
		 jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor()); 
		 JSONArray jsonArray = JSONArray.fromObject(daoPage.getResult(),jsonConfig);
		result.put("total", daoPage.getRecordCount());
		result.put("rows", jsonArray);// daoPage.getr
		result.put("pages", daoPage.getPageCount());
		result.put("success", true);
		retJson(response, result);
	}
}
