package com.deloitte.tms.comobox.controller;

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

import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.dictionary.model.DictionaryEntity;
import com.deloitte.tms.pl.dictionary.service.DictionaryService;
import com.deloitte.tms.pl.security.model.impl.DefaultUrl;
import com.deloitte.tms.pl.security.model.impl.DefaultUser;
import com.deloitte.tms.vat.controller.BaseController;
import com.deloitte.tms.vat.core.common.JsonDateValueProcessor;

@Controller
public class ComoboxController extends BaseController{
	
	 @Resource
	 DictionaryService dictionaryService;
	 //@RequestParam("parentCode") String parentCode,
	 @RequestMapping(value = "/getdictionary", method = RequestMethod.GET)	
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
