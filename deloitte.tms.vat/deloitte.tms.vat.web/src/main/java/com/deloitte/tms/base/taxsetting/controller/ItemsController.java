package com.deloitte.tms.base.taxsetting.controller;

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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.deloitte.tms.base.taxsetting.model.Items;
import com.deloitte.tms.base.taxsetting.model.ItemsInParam;
import com.deloitte.tms.base.taxsetting.model.TaxCategoryInParam;
import com.deloitte.tms.base.taxsetting.service.ItemsService;
import com.deloitte.tms.base.taxsetting.service.TaxCategoryService;
import com.deloitte.tms.pl.core.commons.constant.TableColnumDef;
import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.core.commons.utils.PageUtils;
import com.deloitte.tms.pl.core.commons.utils.reflect.ReflectUtils;
import com.deloitte.tms.pl.core.context.utils.ContextUtils;
import com.deloitte.tms.vat.controller.BaseController;

/**
 * 
 * @author sqing
 *
 */
@Controller
@RequestMapping("taxitems")
public class ItemsController extends BaseController {
	@Resource
	ItemsService itemsService;
	@Autowired
	TaxCategoryService taxCategoryService;
	@ResponseBody
	@RequestMapping(value = "items/getItems", method = RequestMethod.GET)
	//@RoleAnnotation(roles=RoleDef.ECOMMERCE_ADMIN)
	public void loadItemsPage(@RequestParam Map<String,Object> parameter,HttpServletResponse response/*@ModelAttribute taxItemSearchForm*/) throws Exception {
		DaoPage daoPage=itemsService.findItemsByParams(parameter,PageUtils.getPageNumber(parameter),PageUtils.getPageSize(parameter));
		JSONObject result=new JSONObject();
		result.put("total", daoPage.getRecordCount());
		result.put("rows", daoPage.getResult());
		result.put("pages", daoPage.getPageCount());
		retJson(response,result);
	}
	@RequestMapping(value = "/getItemsIndex")
	public String getIndex() throws Exception{
		return "vat/taxitems/items";
	}
	@ResponseBody
	@RequestMapping(value = "items/saveItems")
	public void saveItems(@ModelAttribute("taxItems") ItemsInParam inParam ,HttpServletResponse response) throws Exception {
		Items entity=itemsService.convertItemsInParamToEntity(inParam);
		JSONObject object = new JSONObject();
		Date date = itemsService.getDatabaseServerDate();
		/*String flag=getIsUsed(entity.getIsUsed());*/
		if(!AssertHelper.isOrNotEmpty_assert(entity.getId())){
			entity.setFlag(TableColnumDef.FLAG_EFFECT);
			entity.setCreateDate(date);
			entity.setCreatedBy(ContextUtils.getCurrentUserCode());
			itemsService.save(entity);
			if(!entity.getId().equals("")){
				object.put("msg", "保存成功");
				object.put("success", "true");
			}else{
				object.put("msg", "保存失败");
				object.put("success", "false");
			}
		}
		else{
			//entity.setIsUsed(flag);
			entity.setModifiedBy(ContextUtils.getCurrentUserCode());
			entity.setModifiedDate(date);
			itemsService.update(entity);
			object.put("msg", "保存成功");
			object.put("success", "true");
		}
		inParam.setId(entity.getId());
		retJson(response, object);
	}	
	@ResponseBody
	@RequestMapping(value = "items/removeItems")
	public void removeItemss(@RequestParam("ids") String ids,HttpServletResponse response )throws IOException {
		AssertHelper.notEmpty_assert(ids,"需要删除的用户不能为空");
		String[] itemsIds=ids.split(",");
		for(String itemsId:itemsIds){
			Items entity = (Items)itemsService.findById(Items.class,itemsId);
			entity.setFlag(TableColnumDef.FLAG_DISABLED);
			/*itemsService.remove(entity);*/
			itemsService.update(entity);
		 }
		JSONObject object=new JSONObject();
		String successMsg="删除成功";
		object.put("result", "true");
		object.put("msg", successMsg);
		retJson(response, object);
	}
	public Collection<ItemsInParam> loadItems(Map<String, Object> map) throws Exception {
		List result=itemsService.findItemsByParams(map);
		return result;
	}
	
	public ItemsInParam loadAddItems(Map<String, Object> map) throws Exception {
		ItemsInParam inParam=new ItemsInParam();
		return inParam;
	}
	@ResponseBody
	@RequestMapping(value = "items/loadModifyItems")
	public void loadModifyItems(@RequestParam Map<String, Object> map,HttpServletResponse response) throws Exception {
		JSONObject jsonObject= new JSONObject();
		Object id=map.get("id");
		if(!AssertHelper.isOrNotEmpty_assert(id)){
			jsonObject.put("success", "false");
			jsonObject.put("errorMsg", "编辑的主键不能为空");
		}else{
			jsonObject.put("success", "true");
		    Items entity=(Items)itemsService.get(Items.class,id.toString());
		    ItemsInParam inParam=itemsService.convertItemsToInParam(entity);
		    jsonObject.put("taxItmesForm", inParam);
		} 
		retJson(response, jsonObject);
		//return inParam;
	}
	@ResponseBody
	@RequestMapping(value="items/getTaxCategoryList")
	public void getTaxCategoryList(@RequestParam Map<String, Object> map,HttpServletResponse response)throws Exception{
		List<TaxCategoryInParam> taxCategoryNameList=taxCategoryService.findTaxCategoryByParams(map);
		List<Map<String, String>>reList=new ArrayList();
		TaxCategoryInParam inParam;
		for (int i = 0; i < taxCategoryNameList.size(); i++) {
			inParam=taxCategoryNameList.get(i);
			Map< String, String > taxCategoryNameMap=new HashMap<String, String>();
			taxCategoryNameMap.put("value", inParam.getId());
			taxCategoryNameMap.put("text", inParam.getCategoryName());
			reList.add(taxCategoryNameMap);
		}
		JSONArray jsonArray = JSONArray.fromObject(reList);
		retJsonArray(response, jsonArray); 
	}
	@ResponseBody
	@RequestMapping(value="items/getTaxRulesName")
	public void getTaxRulesName(@RequestParam Map<String, Object> map,HttpServletResponse response)throws Exception{
		List<ItemsInParam> itemsRulesNameList=itemsService.findItemsByParams(map);
		List<Map<String, String>>reList=new ArrayList();
		ItemsInParam inParam;
		Map<String, String>map2=new HashMap();
		for (int i = 0; i < itemsRulesNameList.size(); i++) {
			inParam=itemsRulesNameList.get(i);
			Map< String, String > itemsRulesNameMap=new HashMap<String, String>();
			//itemsRulesNameMap.put("comboboxId", inParam.getId());
			if(!map2.values().contains(inParam.getItemTaxRules())){
				itemsRulesNameMap.put("comboboxId", inParam.getItemTaxRules());
				itemsRulesNameMap.put("itemTaxRules", inParam.getItemTaxRules());
				map2.put(i+"", inParam.getItemTaxRules());
				reList.add(itemsRulesNameMap);
			}
			
		}
		JSONArray jsonArray = JSONArray.fromObject(reList);
		retJsonArray(response, jsonArray); 
	}
	//@ResponseBody
	@RequestMapping(value="items/getTaxItemsName")
	public void getTaxItemsName(@RequestParam Map<String, Object> map,HttpServletResponse response)throws Exception{
		List<ItemsInParam> itemsList=itemsService.findItemsByParams(map);
		List<Map<String, String>>reList=new ArrayList();
		/*for (ItemsInParam param:itemsList) {
			Map< String, String >Imap = new HashMap<String, String>();
			Imap.put("key", param.getId());
			Imap.put("value", param.getItemName());
			reList.add(Imap);
		}*/
		JSONObject result=new JSONObject();
		 result.put("total", itemsList.size());
		 result.put("rows", itemsList);
		 retJson(response,result);	  
		/*JSONArray jsonArray = JSONArray.fromObject(reList);
		retJsonArray(response, jsonArray); */
	}
	public void updateModifyItems(ItemsInParam inParam) throws Exception {
		Items entity=(Items)itemsService.get(Items.class,inParam.getId());
		ReflectUtils.copyProperties(inParam, entity);
		itemsService.update(entity);
	}
	//用于涉税交易认定规则 根据ID获取名称
	@ResponseBody
	@RequestMapping(value = "LoadgetItemsName", method = RequestMethod.POST)
	//@RoleAnnotation(roles=RoleDef.ECOMMERCE_ADMIN)
	public DaoPage loadItemsPageame(@RequestParam Map<String,Object> parameter) throws Exception {
		DaoPage daoPage=itemsService.findItemsByParams(parameter,PageUtils.getPageNumber(parameter),PageUtils.getPageSize(parameter));

		return daoPage;
	}
	
	
	
}
