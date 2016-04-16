package com.deloitte.tms.pl.cache.impl;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.deloitte.tms.pl.cache.ApplicationCache;
import com.deloitte.tms.pl.core.commons.exception.BusinessException;
import com.deloitte.tms.pl.core.commons.utils.SpringUtil;

public class HashMapApplicationCacheImpl implements ApplicationCache,ApplicationContextAware{
	// 创建全局的唯一实例
  protected static Map cache;
  protected static Map tempCache;
	
	public HashMapApplicationCacheImpl() {
		cache=new HashMap();
		tempCache=new HashMap();
	}

	private void testCacheServer() {
		String test_key="test_key";
		String test_value="test_value";
		cache.put(test_key, test_value);
		if(!test_value.equals(cache.get(test_key))){
			throw new BusinessException("HashMap 缓存初始化出错");
		}
	}
	@Override
	public Object getCacheObject(String key) {
		 return cache.get(key);
	}

	@Override
	public Object getTemporaryCacheObject(String key) {
		 return tempCache.get(key);
	}

	@Override
	public void putCacheObject(String key, Object obj) {
		cache.put(key, obj);
	}

	@Override
	public void putTemporaryCacheObject(String key, Object obj) {
		tempCache.put(key, obj);
	}

	@Override
	public void removeCacheObject(String key) {
		cache.remove(key);
	}

	@Override
	public void removeTemporaryCacheObject(String key) {
		tempCache.remove(key);
	}

	@Override
	public void replaceCacheObject(String key, Object obj) {
		cache.put(key, obj);
	}

	@Override
	public void replaceTemporaryCacheObject(String key, Object obj) {
		tempCache.put(key, obj);
	}

	@Override
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		cache=SpringUtil.getBean("memcachedClient");
		tempCache=SpringUtil.getBean("tempMemcachedClient");
	}
	@Test
	public void testMemcached(){
		
	}
}
