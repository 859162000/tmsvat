package com.deloitte.tms.pl.cache.provider;

import java.util.Set;

import javax.annotation.Resource;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.core.commons.utils.CollectionUtils;
import com.deloitte.tms.pl.dictionary.model.DictionaryEntity;
import com.deloitte.tms.pl.dictionary.service.DictionaryService;

/**
 * 数据字典缓存(EHCache实现)
 * @author zhanglin.jiang
 *
 */
@Component("dictionaryCacheProvider")
public class DictionaryCacheProvider {
	@Resource
	private DictionaryService dictionaryService;

	/**
	 * 根据大类代码查询小类集合
	 * @Cacheable 缓存组件，表示申明一个名为“codesCache”的缓存。调用此方法，
	 * 会先判断要查询的数据是否已缓存，如果缓存有则取从缓存取数，若不存在或数据失效则继续调用 findCodesByCvalue 方法查询数据库
	 * @param name
	 * @return
	 */
	@Cacheable("codesCache")
	public Set<DictionaryEntity> getCodes(String categoryCode) {
		return CollectionUtils.convertListToSet(dictionaryService.loadDictionaryEntities(categoryCode));
	}
	
	/**
	 * 移除所有“codesCache”缓存的数据
	 * @CacheEvict 根据参数移除系统缓存
	 */
	@CacheEvict(value = "codesCache", allEntries = true)
	public void removeCache() {
		
	}
}
