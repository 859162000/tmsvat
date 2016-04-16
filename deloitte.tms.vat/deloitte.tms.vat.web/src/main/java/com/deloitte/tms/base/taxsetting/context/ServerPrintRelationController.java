package com.deloitte.tms.base.taxsetting.context;

import java.io.IOException;
import java.util.Date;
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

import com.deloitte.tms.base.taxsetting.model.TmsMdContract;
import com.deloitte.tms.base.taxsetting.model.TmsMdContractInParam;
import com.deloitte.tms.base.taxsetting.service.TmsMdContractService;
import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.commons.utils.PageUtils;
import com.deloitte.tms.pl.security.model.impl.DefaultUser;
import com.deloitte.tms.vat.controller.BaseController;
import com.deloitte.tms.vat.core.common.JsonDateValueProcessor;


@Controller
@RequestMapping("serverPrint")
public class ServerPrintRelationController extends BaseController {
	
	@Resource
	private TmsMdContractService tmsMdContractService;

	@RequestMapping(value = "/tmscontract", method = RequestMethod.GET)
	public String invoiceSendHInit() throws Exception {
		return "base/taxsetting/serverPrintRelation";
	}

	@RequestMapping("serverPrint/save.do")
	public void save(
			@ModelAttribute("tmsMdContractInParam") TmsMdContractInParam tmsMdContractInParam,
			HttpServletResponse response) throws IOException {
		
		String projdata = tmsMdContractInParam.getProjData();
		//tmsMdContractService.save(tmsMdContractInParam);
		JSONObject result = new JSONObject();
		result.put("success", true);
		result.put("msg", getMessage("save.sucess"));
		retJson(response, result);
	}
	
	@RequestMapping("serverPrint/search.do")
	public void search(@RequestParam Map<String, Object> parameter,	HttpServletResponse response) throws IOException {
		
		DaoPage daoPage = tmsMdContractService.findTmsMdContractByParams(parameter,
				PageUtils.getPageNumber(parameter),
				PageUtils.getPageSize(parameter));
		List<TmsMdContract> list = (List<TmsMdContract>) daoPage.getResult();
		JsonConfig jsonConfig = new JsonConfig();
		JSONArray jsonArray = JSONArray.fromObject(list, jsonConfig);
		JSONObject result = new JSONObject();
		result.put("total", daoPage.getTotal());
		result.put("rows", jsonArray);
		retJson(response, result);	
	}

}
