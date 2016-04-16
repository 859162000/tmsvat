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

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.deloitte.tms.base.masterdata.model.TmsMdEquipment;
import com.deloitte.tms.base.masterdata.model.TmsMdEquipmentInParam;
import com.deloitte.tms.base.masterdata.service.EquipmentService;
import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.core.commons.utils.PageUtils;
import com.deloitte.tms.pl.dictionary.model.DictionaryEntity;
import com.deloitte.tms.pl.dictionary.service.DictionaryService;
import com.deloitte.tms.pl.security.utils.LittleUtils;
import com.deloitte.tms.vat.controller.BaseController;
import com.deloitte.tms.vat.core.common.FunctionTreeNode;
import com.deloitte.tms.vat.core.common.JsonDateValueProcessor;
import com.deloitte.tms.vat.core.common.TreeGenerator;


@Controller
public class EquipmentController extends BaseController{

	@Resource
	EquipmentService equipmentService;

	@Resource
	DictionaryService dictionaryService;
	
	Logger logger = Logger.getLogger(this.getClass());
	
	@RequestMapping(value = "equipmentMgt.do")
	public String getIndex(){
	//equipment/loadPage.do	
		try{
			return "base/masterdata/equipment";
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return "base/masterdata/equipment";
		
	}
	
	@RequestMapping("equipmentN/createtree.do")
	public void createTree(HttpServletResponse response) {
		try {
			List<TmsMdEquipment> list = equipmentService.loadAllEquipment();
			logger.info("createtree list size:"+list.size());
			List<FunctionTreeNode> FunctionTreeNodes = convertFunctionTreeNodeList(list);
			logger.info("createtree FunctionTreeNodes size:"+FunctionTreeNodes.size());
			List<FunctionTreeNode> results = TreeGenerator.buildTree(FunctionTreeNodes);
			logger.info("createtree results size:"+results.size());
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

	@RequestMapping(value = "equipmentN/getDictionary", method = RequestMethod.GET)    
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

	@RequestMapping(value = "equipmentN/loadEquipmentPage")
	public void loadEquipmentPage(@RequestParam Map<String,Object> parameter,HttpServletResponse response) throws Exception {
		
		try{
			
			Integer pageNumber;
			Integer pageSize;
			try{
		 pageNumber = PageUtils.getPageNumber(parameter);
		 pageSize = PageUtils.getPageSize(parameter);
			}catch(Exception ex){
				//ex.printStackTrace();
				logger.info("will use default pageNuber and pageSize as exception when get pageNumber or pageSize");
				pageNumber=LittleUtils.pageNumber;
				pageSize=LittleUtils.pageSize;
			}
			
			if(pageNumber<1){
				pageNumber=LittleUtils.pageNumber;
			}
			if(pageSize<1){
				pageSize=LittleUtils.pageSize;
			}
		
		DaoPage daoPage=equipmentService.findEquipmentByParams(parameter,pageNumber,pageSize);
		logger.info("loadEquipmentPage: daoPage:"+daoPage);
		
		if(daoPage==null){
			return;
		}
		
		List<TmsMdEquipmentInParam> list = (List<TmsMdEquipmentInParam>) daoPage.getResult();
		
		if (list==null){
			logger.info("loadEquipmentPage: list is null:"+list);
			return;
		}
		logger.info("loadEquipmentPage: list size:"+list.size());
		
		//Map<String,String> map = DictionaryCacheUtils.getCodesByCvalueMap("BASE_IS_ENABLED");
		Map<String,String> map = new HashMap(); 
		
		
		List<TmsMdEquipmentInParam> finalList = new ArrayList<TmsMdEquipmentInParam>();
		
		if(map==null || map.size()<1){
			
		}else{
			for (Iterator<TmsMdEquipmentInParam> i = list.iterator(); i.hasNext(); ) {
				TmsMdEquipmentInParam entity = i.next();
				entity.setEnabledFlag(map.get(entity.getEnabledFlag()));
				finalList.add(entity);
			}
		}	
		
		daoPage.setResult(finalList);
		
		this.returnFromDaoPage(response, daoPage);
		
		
		}catch(Exception e){
			e.printStackTrace();
		}
		/*
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class,new JsonDateValueProcessor("yyyy-MM-dd"));
		JSONArray jsonArray = JSONArray.fromObject(daoPage.getResult(), jsonConfig);
		JSONObject result = new JSONObject();
		result.put("total", daoPage.getRecordCount());
		result.put("rows", jsonArray.toString());
		retJson(response, result);
		
		*/
	}
	
	@ResponseBody
	@RequestMapping(value = "equipmentN/loadLegalEntity")
	public DaoPage loadLegalEntityPage(@RequestParam Map<String,Object> parameter) throws Exception {
		DaoPage daoPage=equipmentService.findLegalEntityByParams(parameter,PageUtils.getPageNumber(parameter),PageUtils.getPageSize(parameter));
		logger.info("equipmentN/loadLegalEntity daoPage:"+daoPage);
		return daoPage;
	}
	
	@RequestMapping("equipmentN/saveEquipment.do")
	public void saveEquipment(@ModelAttribute("tmsMdEquipment") TmsMdEquipmentInParam tmsMdEquipment,
			HttpServletResponse response){

		//TmsMdEquipment tmsMdEquipment=new TmsMdEquipment(map);
		
		
		try{
		TmsMdEquipment entity = null;
		if (AssertHelper.empty(tmsMdEquipment.getEnabledFlag()) || tmsMdEquipment.getEnabledFlag().equals("是")) {
			tmsMdEquipment.setEnabledFlag("Yes");
		}

		if (AssertHelper.empty(tmsMdEquipment.getId())) {
			entity=equipmentService.convertEquipmentInParamToEntity(tmsMdEquipment);
			//entity=tmsMdEquipment;
			equipmentService.save(entity);
		}else{
			entity=(TmsMdEquipment) equipmentService.get(TmsMdEquipment.class, tmsMdEquipment.getId());
			if(!AssertHelper.empty(tmsMdEquipment.getEquipmentCode())){
				entity.setEquipmentCode(tmsMdEquipment.getEquipmentCode());
			}
			if(!AssertHelper.empty(tmsMdEquipment.getEquipmentName())){
				entity.setEquipmentName(tmsMdEquipment.getEquipmentName());
			}
			if(!AssertHelper.empty(tmsMdEquipment.getEquipmentIp())){
				entity.setEquipmentIp(tmsMdEquipment.getEquipmentIp());
			}
			if(!AssertHelper.empty(tmsMdEquipment.getEquipmentManager())){
				entity.setEquipmentManager(tmsMdEquipment.getEquipmentManager());
			}
			if(!AssertHelper.empty(tmsMdEquipment.getEnabledFlag())){
				entity.setEnabledFlag(tmsMdEquipment.getEnabledFlag());
			}
			if(!AssertHelper.empty(tmsMdEquipment.getParentEquipmentId())){
				entity.setParentEquipmentId(tmsMdEquipment.getParentEquipmentId());
			}
			equipmentService.update(entity);
		}
		
		tmsMdEquipment.setId(entity.getId());
		
		this.returnOk(response, "保存成功");
		//return equipmentForm;
		
		
	}catch(Exception x){
		x.printStackTrace();
		this.returnFail(response, "保存异常");
	}
		return;
		//return equipmentForm;
	}
	
	@RequestMapping(value = "equipmentN/getEquipmentById")
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
	
	@RequestMapping(value = "equipmentN/deleteEquipment")
	public void deleteEquipment(@RequestParam("id") String id,
			HttpServletResponse response) {
		JSONObject result = new JSONObject();
		try{
		
		equipmentService.deleteEquipment(id);
		String successMsg = "删除成功！";
		result.put("success", true);
		result.put("msg", successMsg);
		retJson(response, result);
		}catch(Exception x){
			x.printStackTrace();
			String successMsg = "删除成功！";
			result.put("success", false);
			result.put("msg", successMsg);
		}
	}
}
