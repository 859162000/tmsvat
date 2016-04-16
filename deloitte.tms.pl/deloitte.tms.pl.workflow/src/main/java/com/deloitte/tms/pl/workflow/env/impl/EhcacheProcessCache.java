package com.deloitte.tms.pl.workflow.env.impl;

import java.io.File;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.config.Configuration;
import net.sf.ehcache.config.TerracottaClientConfiguration;
import net.sf.ehcache.config.TerracottaConfiguration;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.workflow.env.ProcessCache;
import com.deloitte.tms.pl.workflow.utils.EnvironmentUtils;

/**
 * @author Jacky.gao
 * @since 2013年9月17日
 */
@Component
@DependsOn({EnvironmentUtils.BEAN_ID})
public class EhcacheProcessCache implements ProcessCache,InitializingBean {
	private Cache ufloCache;
	private CacheManager cacheManager;
	private String terracottaServer;
	private String storePath;
	private String UFLO_CACHE_NAME="uflo_cache";
	public void store(String key, Object obj) {
		ufloCache.put(new Element(key,obj));
	}

	public Object retrive(String key) {
		Element element=ufloCache.get(key);
		if(element==null){
			return null;
		}
		return element.getObjectValue();
	}

	public void afterPropertiesSet() throws Exception {
		storePath=EnvironmentUtils.getEnvironment().getTempFileStorePath();
		storePath=(StringUtils.isEmpty(storePath))?System.getProperty("java.io.tmpdir"):storePath;
		File f=new File(storePath);
		if(!f.exists()){
			f.mkdirs();
		}
		Configuration config=null;
		if(StringUtils.isNotEmpty(terracottaServer)){
			/**
			 * 如果terracottaServer不为空，那么就采用terracotta server提供的集群式缓存策略，否则就采用单机缓存策略
			 * */
			config=new Configuration();
			TerracottaClientConfiguration terracottaConfig=new TerracottaClientConfiguration();
			terracottaConfig.setUrl(terracottaServer);
			config.addTerracottaConfig(terracottaConfig);
			
			CacheConfiguration cacheConfig=new CacheConfiguration();
			cacheConfig.setEternal(true);
			TerracottaConfiguration terracottaConfiguration=new TerracottaConfiguration();
			terracottaConfiguration.setSynchronousWrites(false);
			cacheConfig.addTerracotta(terracottaConfiguration);
			cacheConfig.setName(UFLO_CACHE_NAME);
			cacheConfig.setMaxEntriesLocalHeap(5000);
			cacheConfig.setMaxElementsOnDisk(0);
			cacheConfig.setMemoryStoreEvictionPolicy("LFU");
			config.addCache(cacheConfig);
		}else{
			config=new Configuration();
			CacheConfiguration cacheConfig=new CacheConfiguration();
			cacheConfig.setEternal(true);
			cacheConfig.setName(UFLO_CACHE_NAME);
			cacheConfig.setMaxEntriesLocalHeap(5000);
			cacheConfig.setMaxElementsOnDisk(0);
			cacheConfig.setMemoryStoreEvictionPolicy("LRU");
			cacheConfig.setDiskSpoolBufferSizeMB(100);
			config.addCache(cacheConfig);
		}
		config.setName("cache_manager_uflo_engine");
		this.cacheManager = new CacheManager(config);				
		ufloCache=cacheManager.getCache(UFLO_CACHE_NAME);
	}
}
