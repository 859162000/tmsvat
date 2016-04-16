package com.deloitte.tms.pl.dictionary.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.deloitte.tms.pl.dictionary.model.DictionaryEntity;
import com.deloitte.tms.pl.dictionary.service.DictionaryService;

@Component()
@RequestMapping("dictionary")
public class DictionaryController
{
  @Resource
  DictionaryService dictionaryService;

  @ResponseBody
  @RequestMapping(value = "/getdDictionaryEntitiesByCode", method = RequestMethod.GET)
  public Collection<DictionaryEntity> getdDictionaryEntitiesByCode(@RequestParam String code)
  {
	Collection<DictionaryEntity> results=dictionaryService.loadDictionaryEntities(code);
//	for(DictionaryEntity entity:results){
//		String name=entity.getName();
//		if(AssertHelper.notEmpty(name)){
//			if(name.indexOf("${res[")>=0){
//				name=name.replace("${res[\"", "");
//				name=name.replace("\"]}", "");
//				name=I18nUtils.get(name);
//				entity.setName(name);
//			}
//		}
//	}
    return results;
  }
  @ResponseBody
  @RequestMapping(value = "/getdDictionaryEntitiesByCodeWithNull", method = RequestMethod.GET)
  public Collection<DictionaryEntity> getdDictionaryEntitiesByCodeWithNull(@RequestParam String code)
  {
	  Collection<DictionaryEntity> temps=dictionaryService.loadDictionaryEntities(code);
	  List<DictionaryEntity> result=new ArrayList<DictionaryEntity>();
	  
	  DictionaryEntity nullEntity=new DictionaryEntity();
	  nullEntity.setCode(null);
	  nullEntity.setName("无");
	  result.add(nullEntity);
	  for(DictionaryEntity entity:temps)
	  {
		  result.add(entity);
	  }
	  return result;
  }
  public void reloadDictionary(){
	  dictionaryService.reloadDictionary();
  }
}