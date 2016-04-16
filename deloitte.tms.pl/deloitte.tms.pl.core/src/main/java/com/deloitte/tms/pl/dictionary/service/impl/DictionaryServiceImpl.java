package com.deloitte.tms.pl.dictionary.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.core.commons.utils.SpringUtil;
import com.deloitte.tms.pl.core.dao.IDao;
import com.deloitte.tms.pl.core.service.impl.BaseService;
import com.deloitte.tms.pl.dictionary.model.DictionaryEntity;
import com.deloitte.tms.pl.dictionary.service.DictionaryGenerator;
import com.deloitte.tms.pl.dictionary.service.DictionaryService;
import com.deloitte.tms.pl.system.model.SysCategory;
import com.deloitte.tms.pl.system.service.SysCategoryService;
@Component("dictionaryService")
public class DictionaryServiceImpl extends BaseService implements DictionaryService {
	static Map<String, List<DictionaryEntity>> CACHEENUMS=new HashMap<String, List<DictionaryEntity>>();
	@Value("${ling2.dictionarycatch}")
	Boolean isCatch; 
	@Resource
	private SysCategoryService sysCategoryService;
	
	public List<DictionaryEntity> loadDictionaryEntities(String categoryCode) {
		List<DictionaryEntity> result;
//		if(isCatch){
//			result=CACHEENUMS.get(categoryCode);
//			if(result==null){
				result = getDictionary(categoryCode);
//				CACHEENUMS.put(categoryCode, result);
//			}
//		}else {
//			result = getDictionary(categoryCode);
//		}		
		return  result;
	}
	protected List<DictionaryEntity> getDictionary(String categoryCode) {
		Collection<DictionaryGenerator> generators=SpringUtil.getBeansOfType(DictionaryGenerator.class);
		for(DictionaryGenerator generator:generators){
			if(categoryCode.equals(generator.getDictType())){
				return generator.getDictionaryEntities();
			}
		}
		List<SysCategory> categories=sysCategoryService.listEntries(categoryCode);
		List<DictionaryEntity> result=categoryToDictionaryEntity(categories);
		return result;
	}
	private List<DictionaryEntity> categoryToDictionaryEntity(List<SysCategory> categories)
	{
		List<DictionaryEntity> resultsDictionaryEntities=new ArrayList<DictionaryEntity>();
		for(SysCategory category:categories)
		{
			DictionaryEntity dictionaryEntity=convertCategoryToDictionary(category);
			resultsDictionaryEntities.add(dictionaryEntity);
		}
		return resultsDictionaryEntities;
	}
	private DictionaryEntity convertCategoryToDictionary(SysCategory category) {
		DictionaryEntity dictionaryEntity=new DictionaryEntity();
		dictionaryEntity.setCode(category.getCode());
		dictionaryEntity.setName(category.getLabel());
		return dictionaryEntity;
	}
	@Override
	public IDao getDao() {
		return null;
	}
	@Override
	public void reloadDictionary() {
		if(isCatch){
			CACHEENUMS.clear();
		}
	}
	@Override
	public DictionaryEntity getDictionaryEntity(String code) {
		SysCategory category=sysCategoryService.getCategoryCode(code);
		if(category==null){
//			throw new BusinessException("code:"+code+"未定义");
			category=new SysCategory();//防止数据变化导致出错
		}
		DictionaryEntity result=convertCategoryToDictionary(category);
		return result;
	}
}
