package com.deloitte.tms.pl.dictionary.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.deloitte.tms.pl.core.commons.constant.TableColnumDef;
import com.deloitte.tms.pl.core.commons.exception.BusinessException;
import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.core.commons.utils.PageUtils;
import com.deloitte.tms.pl.core.commons.utils.reflect.ReflectUtils;
import com.deloitte.tms.pl.dictionary.service.DictionaryService;
import com.deloitte.tms.pl.system.model.SysCategory;
import com.deloitte.tms.pl.system.model.SysCategoryInParam;
import com.deloitte.tms.pl.system.service.SysCategoryService;

@Controller
@RequestMapping("category")
public class CategoryController
{

  @Resource
  SysCategoryService sysCategoryService;
  @Resource
  DictionaryService dictionaryService;

  @ResponseBody
  @RequestMapping(value = "/loadSysCategoryPage", method = RequestMethod.GET)
  public DaoPage loadSysCategoryPage(@RequestParam Map<String,Object> parameter) throws Exception {
    DaoPage daoPage = this.sysCategoryService.findSysCategoryByParams(parameter,PageUtils.getPageIndex(parameter),PageUtils.getPageSize(parameter));
    return daoPage;
  }

  @ResponseBody
  @RequestMapping(value = "/loadSysCategory", method = RequestMethod.GET)
  public Collection<SysCategoryInParam> loadSysCategory(@RequestParam Map<String, Object> parameter) throws Exception {
	List<SysCategoryInParam> result=new ArrayList();
	List<SysCategory> categories =sysCategoryService.loadSysCategory(parameter);
	for(SysCategory category:categories){
		SysCategoryInParam inParam=new SysCategoryInParam();
		ReflectUtils.copyProperties(category, inParam);
		result.add(inParam);
	}
    return result;
  }
  
  @ResponseBody
  @RequestMapping(value = "/saveSysCategory", method = RequestMethod.POST)
  public SysCategoryInParam saveSysCategory(SysCategoryInParam inParam) throws Exception {
	 checkCategory(inParam);
	 if(inParam.getId()==null){
		 SysCategory category=new SysCategory();
		 ReflectUtils.copyProperties(inParam, category);
		 category.setFlag(TableColnumDef.FLAG_EFFECT);
		 category.setEnabled(true);
		 sysCategoryService.save(category);
		 inParam.setId(category.getId());
	 }else {
		SysCategory category=(SysCategory) sysCategoryService.get(SysCategory.class,inParam.getId());
		ReflectUtils.copyProperties(inParam, category);		
		sysCategoryService.update(category);
	}
	 return inParam;
  }
  @ResponseBody
  @RequestMapping(value = "/removeSysCategory", method = RequestMethod.POST)
  public SysCategoryInParam removeSysCategory(SysCategoryInParam inParam) throws Exception {
	 if(inParam.getId()==null){
		 
	 }else {
		 sysCategoryService.removeSysCategory(inParam.getId());
	}
	 return inParam;
  }
  @ResponseBody
  @RequestMapping(value = "/reloadDictionary", method = RequestMethod.POST)
  public void reloadDictionary(){
	  dictionaryService.reloadDictionary();
  }
  private void checkCategory(SysCategoryInParam inParam){
	  AssertHelper.notEmpty_assert(inParam,"保存的数据不能为空");
	  AssertHelper.notEmpty_assert(inParam.getCode(),"代码不能为空");
	  AssertHelper.notEmpty_assert(inParam.getLabel(),"显示名称不能为空");
	  AssertHelper.notEmpty_assert(inParam.getSortOrder(),"请指定排序");
  }
}