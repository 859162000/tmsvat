package com.deloitte.tms.base.masterdata.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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

import com.deloitte.tms.base.masterdata.model.TmsMdEquipment;
import com.deloitte.tms.base.masterdata.model.TmsMdEquipmentInParam;
import com.deloitte.tms.base.masterdata.service.EquipmentService;
import com.deloitte.tms.pl.cache.utils.DictionaryCacheUtils;
import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.core.commons.utils.PageUtils;
import com.deloitte.tms.pl.dictionary.model.DictionaryEntity;
import com.deloitte.tms.pl.dictionary.service.DictionaryService;
import com.deloitte.tms.vat.controller.BaseController;
import com.deloitte.tms.vat.core.common.FunctionTreeNode;
import com.deloitte.tms.vat.core.common.JsonDateValueProcessor;
import com.deloitte.tms.vat.core.common.TreeGenerator;


@Controller
@RequestMapping("equipment")
public class EquipmentController extends BaseController{

	@Resource
	EquipmentService equipmentService;

	@Resource
	DictionaryService dictionaryService;
	
	@RequestMapping(value = "/loadPage")
	public String getIndex() throws Exception{
		return "base/masterdata/equipment";
	}
	
	@RequestMapping("createtree")
	public void createTree(HttpServletResponse response) {
		try {
			List<TmsMdEquipment> list = equipmentService.loadAllEquipment();
			List<FunctionTreeNode> FunctionTreeNodes = convertFunctionTreeNodeList(list);
			List<FunctionTreeNode> results = TreeGenerator.buildTree(FunctionTreeNodes);	
			JSONArray jsonArray = JSONArray.fromObject(results);	
			retJsonArray(response, jsonArray);
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	private List<FunctionTreeNode> convertFunctionTreeNodeList(List<TmsMdEquipment> TmsMdEquipments) {
		List<FunctionTreeNode> nodes = null;
		if (TmsMdEquipments != null) {
			nodes = new ArrayList<FunctionTreeNode>();
			for (TmsMdEquipment TmsMdEquipment : TmsMdEquipments) {
				FunctionTreeNode node = convertFunctionTreeNode(TmsMdEquipment);
				if (node != null) {
					nodes.add(node);
				}
			}
		}
		return nodes;
	}
	
	private FunctionTreeNode convertFunctionTreeNode(TmsMdEquipment equipment) {
		FunctionTreeNode node = null;
		if (equipment != null) {
			node = new FunctionTreeNode();
			node.setId(equipment.getId());
			node.setChecked(false);
			node.setText(equipment.getEquipmentName());
		    node.setPid(equipment.getParentEquipmentId());
			Map<String, Object> map = new HashMap<String, Object>();
			node.setAttributes(map);
		}
		return node;
	}

	@RequestMapping(value = "/getDictionary", method = RequestMethod.GET)    
       public void getDictionaryEntitiesByParentCode(HttpServletResponse response) throws IOException{
             Collection<DictionaryEntity> results = dictionaryService.loadDictionaryEntities("BASE_PRINT_TYPE");
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

	@RequestMapping(value = "/loadEquipmentPage", method = RequestMethod.POST)
	public void loadEquipmentPage(@RequestParam Map<String,Object> parameter,HttpServletResponse response) throws Exception {
		
		DaoPage daoPage=equipmentService.findEquipmentByParams(parameter,PageUtils.getPageNumber(parameter),PageUtils.getPageSize(parameter));
		List<TmsMdEquipmentInParam> list = (List<TmsMdEquipmentInParam>) daoPage.getResult();
	
		Map<String,String> map = DictionaryCacheUtils.getCodesByCvalueMap("BASE_IS_ENABLED");
		
		List<TmsMdEquipmentInParam> finalList = new ArrayList<TmsMdEquipmentInParam>();
		
		for (Iterator<TmsMdEquipmentInParam> i = list.iterator(); i.hasNext(); ) {
			TmsMdEquipmentInParam entity = i.next();
			entity.setEnabledFlag(map.get(entity.getEnabledFlag()));
			finalList.add(entity);
		}
		daoPage.setResult(finalList);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class,new JsonDateValueProcessor("yyyy-MM-dd"));
		JSONArray jsonArray = JSONArray.fromObject(daoPage.getResult(), jsonConfig);
		JSONObject result = new JSONObject();
		result.put("total", daoPage.getRecordCount());
		result.put("rows", jsonArray.toString());
		retJson(response, result);
	}
	
	@ResponseBody
	@RequestMapping(value = "/loadLegalEntity", method = RequestMethod.POST)
	public DaoPage loadLegalEntityPage(@RequestParam Map<String,Object> parameter) throws Exception {
		DaoPage daoPage=equipmentService.findLegalEntityByParams(parameter,PageUtils.getPageNumber(parameter),PageUtils.getPageSize(parameter));
		return daoPage;
	}
	
	@ResponseBody
	@RequestMapping(value = "/saveEquipment", method = RequestMethod.POST)
	public TmsMdEquipmentInParam saveEquipment(@ModelAttribute("equipmentForm") TmsMdEquipmentInParam inParam,
			HttpServletResponse response) throws Exception {		

		TmsMdEquipment entity = null;
		if (AssertHelper.empty(inParam.getEnabledFlag()) || inParam.getEnabledFlag().equals("是")) {
			inParam.setEnabledFlag("Yes");
		}

		if (AssertHelper.empty(inParam.getId())) {
			entity=equipmentService.convertEquipmentInParamToEntity(inParam);
			equipmentService.save(entity);
		}else{
			entity=(TmsMdEquipment) equipmentService.get(TmsMdEquipment.class, inParam.getId());
			if(!AssertHelper.empty(inParam.getEquipmentCode())){
				entity.setEquipmentCode(inParam.getEquipmentCode());
			}
			if(!AssertHelper.empty(inParam.getEquipmentName())){
				entity.setEquipmentName(inParam.getEquipmentName());
			}
			if(!AssertHelper.empty(inParam.getEquipmentIp())){
				entity.setEquipmentIp(inParam.getEquipmentIp());
			}
			if(!AssertHelper.empty(inParam.getEquipmentManager())){
				entity.setEquipmentManager(inParam.getEquipmentManager());
			}
			if(!AssertHelper.empty(inParam.getEnabledFlag())){
				entity.setEnabledFlag(inParam.getEnabledFlag());
			}
			if(!AssertHelper.empty(inParam.getParentEquipmentId())){
				entity.setParentEquipmentId(inParam.getParentEquipmentId());
			}
			equipmentService.update(entity);
		}
		
		inParam.setId(entity.getId());
		return inParam;
	}
	
	@RequestMapping(value = "/getEquipmentById")
	public void getEquipmentById(@RequestParam("id") String id,
			HttpServletResponse response) throws IOException {
		TmsMdEquipment entity = (TmsMdEquipment) equipmentService.get(
				TmsMdEquipment.class, id);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class,
				new JsonDateValueProcessor("yyyy-MM-dd"));
		JSONObject jsonObject = JSONObject.fromObject(entity, jsonConfig);
		retJson(response, jsonObject);
	}
	
	@RequestMapping(value = "/deleteEquipment")
	public void deleteEquipment(@RequestParam("id") String id,
			HttpServletResponse response) throws IOException {
		JSONObject result = new JSONObject();
		equipmentService.deleteEquipment(id);
		String successMsg = "删除成功！";
		result.put("success", "true");
		result.put("msg", successMsg);

		retJson(response, result);
	}
}
