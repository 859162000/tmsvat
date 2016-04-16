package com.deloitte.tms.pl.cache.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.cache.provider.DictionaryCacheProvider;
import com.deloitte.tms.pl.dictionary.model.DictionaryEntity;

/**
 * 查询大小类的工具类（走缓存）
 * @author zhanglin.jiang
 *
 */
@Component("codesCacheUtils")
public class DictionaryCacheUtils {

	private DictionaryCacheProvider dictionaryCacheProvider;
	private static DictionaryCacheProvider s_dictionaryCacheProvider;
	
	@Resource
	public void setDictionaryCacheProvider(DictionaryCacheProvider dictionaryCacheProvider) {
		this.dictionaryCacheProvider = dictionaryCacheProvider;
	}

	@PostConstruct
    protected void init() { 
		s_dictionaryCacheProvider = dictionaryCacheProvider;
    } 

	

	/**
	 * 通过大类代码查询所有小类
	 * @param cValue
	 * @return
	 */
	public static Set<DictionaryEntity> getCodesByCvalue(String cValue) {
		return s_dictionaryCacheProvider.getCodes(cValue);
	}
	
	/**
	 * 通过大类代码查询所有小类
	 * @param cValue
	 * @return
	 */
	public static Map<String,String> getCodesByCvalueMap(String cValue) {
		Set<DictionaryEntity> dictionaryEntities=s_dictionaryCacheProvider.getCodes(cValue);
		Map results=new HashMap();
		for(DictionaryEntity entity:dictionaryEntities){
			results.put(entity.getCode(), entity.getName());
		}
		return results;
	}
	
	/**
	 * 通过大类代码和小类代码查询小类名字
	 * @return
	 */
	public static String getCodeName(String cValue, String codeValue) {
		Set<DictionaryEntity> codes = s_dictionaryCacheProvider.getCodes(cValue);
		for (Iterator<DictionaryEntity> iter = codes.iterator(); iter.hasNext();) {
			DictionaryEntity code = iter.next();
			if (code.getCode().equals(codeValue)) {
				return code.getName();
			}
		}
		return null;
	}
	/**
	 * 通过大类代码和小类代码查询小类名字
	 * @return
	 */
	public static String getCodeValue(String cValue, String codeValue) {
		Set<DictionaryEntity> codes = s_dictionaryCacheProvider.getCodes(cValue);
		for (Iterator<DictionaryEntity> iter = codes.iterator(); iter.hasNext();) {
			DictionaryEntity code = iter.next();
			if (code.getCode().equals(codeValue)) {
				return code.getCode();
			}
		}
		return null;
	}
	public void removeCache() {
		s_dictionaryCacheProvider.removeCache();
	}
}
