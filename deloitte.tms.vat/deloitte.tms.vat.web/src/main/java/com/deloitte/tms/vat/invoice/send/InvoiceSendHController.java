/**    
 * Copyright (C),Deloitte
 * @FileName: InvoiceSendHController1.java  
 * @Package: com.deloitte.tms.vat.invoice.send  
 * @Description: //模块目的、功能描述  
 * @Author tomfang  
 * @Date 2016年3月20日 上午10:59:05  
 * @History: //修改记录
 * 〈author〉      〈time〉      〈version〉       〈desc〉
 * 修改人姓名            修改时间            版本号              描述   
 */

package com.deloitte.tms.vat.invoice.send;

import java.io.IOException;
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

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.core.commons.utils.PageUtils;
import com.deloitte.tms.pl.core.commons.utils.reflect.ReflectUtils;
import com.deloitte.tms.pl.core.dao.IDao;
import com.deloitte.tms.pl.core.dao.impl.BaseDaoSimple;
import com.deloitte.tms.vat.controller.BaseController;
import com.deloitte.tms.vat.core.common.IdGenerator;
import com.deloitte.tms.vat.core.common.JsonDateValueProcessor;
import com.deloitte.tms.vat.salesinvoice.model.InvoiceSendH;
import com.deloitte.tms.vat.salesinvoice.model.InvoiceSendHInParam;
import com.deloitte.tms.vat.salesinvoice.model.InvoiceSendL;
import com.deloitte.tms.vat.salesinvoice.service.InvoicePrintPoolHService;
import com.deloitte.tms.vat.salesinvoice.service.InvoiceSendHService;
import com.deloitte.tms.vat.salesinvoice.service.InvoiceSendLService;

/**
 * 〈一句话功能简述〉 功能详细描述
 * 
 * @author tomfang
 * @create 2016年3月20日 上午10:59:05
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Controller
@RequestMapping("invoiceSend")
public class InvoiceSendHController extends BaseController {
	@Resource
	InvoiceSendHService invoiceSendHService;
	@Resource
	InvoicePrintPoolHService invoicePrintPoolHService;
	@Resource
	InvoiceSendLService invoiceSendLService;
	@Resource(name = BaseDaoSimple.BEAN_ID)
	IDao dao;
//invoiceSend/invoiceSendHInit.do
	@RequestMapping(value = "/invoiceSendHInit", method = RequestMethod.GET)
	public String invoiceSendHInit() throws Exception {
		return "vat/invoiceSend/invoiceSend";
	}

	@ResponseBody
	@RequestMapping(value = "/loadInvoiceSendHPage")
	public void loadInvoiceSendHPage(HttpServletResponse response,
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
		// DaoPage
		// daoPage=invoiceSendHService.findInvoiceSendHByParams(parameter,PageUtils.getPageIndex(parameter),PageUtils.getPageSize(parameter));
		// return daoPage;
	}

	// loadInvoiceSendHBySelected
	@ResponseBody
	@RequestMapping(value = "/loadInvoiceSendHBySelected", method = RequestMethod.POST)
	public void loadInvoiceSendHBySelected(
			@ModelAttribute("invoiceSendHInParam") InvoiceSendHInParam invoiceSendHInParam,
			HttpServletResponse response) throws Exception {
		InvoiceSendHInParam frontEntity = 	(InvoiceSendHInParam) invoiceSendHService
				.convertInvoiceSendHInParamToEntity(invoiceSendHInParam);
		Map<String, String> map =new HashMap<String, String>();
		//List<InvoiceSendHInParam> invoiceSendHInParams = new ArrayList<InvoiceSendHInParam>();
		map.put("expressNo", frontEntity.getExpressNo());
		map.put("customerName", frontEntity.getCustomerName());
	//	map.put("deliveryDate", frontEntity.getDeleteDate());
		map.put("deliveryStatus", frontEntity.getDeliveryStatus());
		map.put("deliveryBy", frontEntity.getDeliveryBy());
		map.put("expressCompany", frontEntity.getExpressCompany());
		DaoPage daoPage=invoiceSendHService.findInvoiceSendHByParams(map,Integer.parseInt(frontEntity.getPageNumber()), Integer.parseInt(frontEntity.getPageSize()));
		 JsonConfig jsonConfig = new JsonConfig();  
		 jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor()); 
		 JSONArray jsonArray = JSONArray.fromObject(daoPage.getResult(),jsonConfig);
		JSONObject result = new JSONObject();
		result.put("total",daoPage.getPageCount());
		result.put("rows", jsonArray);// daoPage.getr
		result.put("pages", daoPage.getPageCount());
		result.put("success", true);
		retJson(response, result);
		// DaoPage
		// daoPage=invoiceSendHService.findInvoiceSendHByParams(parameter,PageUtils.getPageIndex(parameter),PageUtils.getPageSize(parameter));
		// return daoPage;
	}

	@ResponseBody
	@RequestMapping(value = "/saveInvoiceSendH", method = RequestMethod.POST)
	public void saveInvoiceSendH(
			@ModelAttribute("invoiceSendHInParam") InvoiceSendHInParam invoiceSendHInParam,
			HttpServletResponse response) throws Exception {
		InvoiceSendH entity = invoiceSendHService
				.convertInvoiceSendHInParamToEntity(invoiceSendHInParam);
		entity.setDeliveryNumber("111000111000");// 流水单号系统自动产生
		entity.setLegalEntityId("DeloitteShangHai");// 关联当前登陆用户
		Date ss = new Date();
		entity.setArchiveBaseDate(ss);// 归档日期
		// 保存寄件头信息
		if ((entity.getId() == null) || "".equals(entity.getId())) {
			entity.setId(IdGenerator.getUUID());
			invoiceSendHService.save(entity);
			// 保存寄件头列信息
			//以下信息放到列controller里面实现
		/*	String clientKeys = invoiceSendHInParam.getInvoiceIDs();
			if ((clientKeys != null)&&(!"".equals(clientKeys))) {
				InvoiceSendL tempInvoiceSendL = new InvoiceSendL();
				for (String clientId : clientKeys.split(",")) {
					tempInvoiceSendL.setInvoiceDeliveryHId(entity.getId());
					tempInvoiceSendL.setInvoicePrtPoolHId(clientId);
					tempInvoiceSendL.setId(clientId);//跟打印池数据一一关联，方便删除
					invoiceSendLService.save(tempInvoiceSendL);
				}
			}*/
		} else {
			invoiceSendHService.update(entity);
			//以下信息放到列controller里面实现
			/*String clientKeys = invoiceSendHInParam.getInvoiceIDs();
			if ((clientKeys != null)&&(!"".equals(clientKeys))) {
				InvoiceSendL tempInvoiceSendL = new InvoiceSendL();
				List<InvoiceSendL> invoiceSendL = new ArrayList<InvoiceSendL>();// 修改之前的列信息
				Map parametersMap = new HashMap();
				StringBuffer entityId = new StringBuffer();
				invoiceSendL = invoiceSendLService
						.findInvoiceSendLByHID(entityId.append(entity.getId()));// 找到之前多对一关联
				invoiceSendLService.removeAll(invoiceSendL);// 删除之前多对一关联
				for (String clientId : clientKeys.split(",")) {// clientKeys.split("").hashCode();
					tempInvoiceSendL.setInvoiceDeliveryHId(entity.getId());
					tempInvoiceSendL.setInvoicePrtPoolHId(clientId);
					tempInvoiceSendL.setId(clientId);//跟打印池数据一一关联，方便删除
					invoiceSendLService.save(tempInvoiceSendL);
				}
			}*/
			// 更新列信息
		}

		JSONObject result = new JSONObject();
		invoiceSendHInParam.setId(entity.getId());
		result.put("success", true);
		result.put("voiceSendId", entity.getId());
		result.put("msg", "保存成功！");
		retJson(response, result);
	}

	@ResponseBody
	@RequestMapping(value = "/removeInvoiceSendHs", method = RequestMethod.POST)
	// @RoleAnnotation(roles=RoleDef.ECOMMERCE_ADMIN)
	public void removeInvoiceSendHs(@RequestParam("id") String clientKeys,
			HttpServletResponse response) throws IOException {
		AssertHelper.notEmpty_assert(clientKeys, "需要删除的用户不能为空");
		for (String clientId : clientKeys.split(",")) {
			InvoiceSendH entity = (InvoiceSendH) invoiceSendHService.get(
					InvoiceSendH.class, clientId);
			dao.remove(entity);
		}
		JSONObject object = new JSONObject();
		object.put("result", "true");
		object.put("success", "true");
		object.put("msg", "删除成功");
		retJson(response, object);
		// for(String invoiceSendHId:invoiceSendHIds){

		// }
	}

	// @ResponseBody
	// @RequestMapping(value = "/searchInvoiceSendH", method =
	// RequestMethod.POST)
	// // @RoleAnnotation(roles=RoleDef.ECOMMERCE_ADMIN)
	// public Collection<InvoiceSendHInParam> loadInvoiceSendH(
	// Map<String, Object> map) throws Exception {
	// List result = invoiceSendHService.findInvoiceSendHByParams(map);
	// return result;
	// }

	public InvoiceSendHInParam loadAddInvoiceSendH(Map<String, Object> map)
			throws Exception {
		InvoiceSendHInParam inParam = new InvoiceSendHInParam();
		return inParam;
	}

	@ResponseBody
	@RequestMapping(value = "/getModifyInvoiceSendH", method = RequestMethod.POST)
	public void loadModifyInvoiceSendH(@RequestParam Map<String, Object> map,
			HttpServletResponse response) throws Exception {
		Object id = map.get("id");
		AssertHelper.notEmpty_assert(id, "编辑的主键不能为空");
		StringBuffer sqlBuffer = new StringBuffer();
		// DaoPage daoPage1=(DaoPage)
		// invoiceSendLService.findBy(sqlBuffer.append(""), map,
		// PageUtils.getPageNumber(map), PageUtils.getPageSize(map));

		// InvoiceSendHInParam
		// inParam=invoiceSendHService.convertInvoiceSendHToInParam(entity);

		JSONObject result = new JSONObject();
		if (!AssertHelper.isOrNotEmpty_assert(id)) {
			result.put("success", "false");
			result.put("errorMsg", "编辑的主键不能为空");
		} else {
			InvoiceSendH entity = (InvoiceSendH) invoiceSendHService.get(
					InvoiceSendH.class, id.toString());
			result.put("success", "true");
			InvoiceSendHInParam aa = new InvoiceSendHInParam();

			ReflectUtils.copyProperties(entity, aa);
			 JsonConfig jsonConfig = new JsonConfig();  
			 jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor()); 
			 JSONObject jsonObject = JSONObject.fromObject(aa,jsonConfig);
			aa.setEntityID(aa.getId());
			result.put("entityID", aa.getId());
			result.put("editInvoiceSendHForm", jsonObject);// editInvoiceSendHForm//
			
			retJson(response, result);
		}
		// return inParam;
	}


	public void updateModifyInvoiceSendH(InvoiceSendHInParam inParam)
			throws Exception {
		InvoiceSendH entity = (InvoiceSendH) invoiceSendHService.get(
				InvoiceSendH.class, inParam.getId());
		ReflectUtils.copyProperties(inParam, entity);
		invoiceSendHService.update(entity);
	}
}
