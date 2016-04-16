package com.deloitte.tms.pl.cache.impl;

import java.util.Properties;

import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;
import com.deloitte.tms.pl.cache.ApplicationCache;
import com.deloitte.tms.pl.core.commons.exception.BusinessException;
import com.deloitte.tms.pl.core.commons.utils.ConverterUtils;
import com.deloitte.tms.pl.core.commons.utils.PropertiesUtils;
import com.deloitte.tms.pl.core.commons.utils.SpringUtil;

public class MemcachedApplicationCacheImpl implements ApplicationCache,ApplicationContextAware{
	// 创建全局的唯一实例
  protected static MemCachedClient memcachedClient;
  protected static MemCachedClient tempMemcachedClient;
	
	public MemcachedApplicationCacheImpl() {
		try {
			Properties properties=PropertiesUtils.getFileProperty("config/memcache.properties");
			// 服务器列表和其权重
			//设置memcache服务端实例地址.多个地址用","隔开
			//3,7表示30% load 在  10.2.224.36:33001, 70% load 在 10.2.224.46:33001 
			String[] servers = ConverterUtils.stringTostrings(properties.getProperty("memcache.server"));
			Integer[] weights = ConverterUtils.stringToIntegers(properties.getProperty("memcache.weights"));

			// 获取socke连接池的实例对象
			SockIOPool pool = SockIOPool.getInstance();

			// 设置服务器信息
			pool.setServers( servers );
			pool.setWeights( weights );

			// 设置初始连接数、最小和最大连接数以及最大处理时间
			pool.setInitConn( ConverterUtils.getInter(properties.getProperty("memcache.initConn")) );
			pool.setMinConn( ConverterUtils.getInter(properties.getProperty("memcache.minConn")) );
			pool.setMaxConn( ConverterUtils.getInter(properties.getProperty("memcache.maxConn")) );
			pool.setMaxIdle( 1000 * 60 * 60 * 6 );

			// 设置主线程的睡眠时间
			pool.setMaintSleep( ConverterUtils.getInter(properties.getProperty("memcache.maintSleep")) );

			// 设置TCP的参数，连接超时等
			pool.setNagle( ConverterUtils.getBoolean(properties.getProperty("memcache.nagle")) );
			pool.setSocketTO( ConverterUtils.getInter(properties.getProperty("memcache.socketTO")) );
			pool.setSocketConnectTO( 0 );

			// 初始化连接池
			pool.initialize();
			
			memcachedClient = new MemCachedClient();
			tempMemcachedClient = new MemCachedClient();
			
//			testCacheServer();
			// 压缩设置，超过指定大小（单位为K）的数据都会被压缩
//			memcachedClient.setCompressEnable( true );
//			memcachedClient.setCompressThreshold( 64 * 1024 );
//			tempMemcachedClient.setCompressEnable( true );
//			tempMemcachedClient.setCompressThreshold( 64 * 1024 );
		} catch (NumberFormatException e) {
			throw new BusinessException("初始化出错,请检查以下关键字:"+e.getMessage());
		} 
	}

	private void testCacheServer() {
		String test_key="test_key";
		String test_value="test_value";
		memcachedClient.add(test_key, test_value);
		if(!test_value.equals(memcachedClient.get(test_key))){
			throw new BusinessException("服务器初始化出错,请检查缓存服务器是否启动或配置是否正确");
		}
	}
	@Override
	public Object getCacheObject(String key) {
		 return memcachedClient.get(key);
	}

	@Override
	public Object getTemporaryCacheObject(String key) {
		 return tempMemcachedClient.get(key);
	}

	@Override
	public void putCacheObject(String key, Object obj) {
		memcachedClient.add(key, obj);
	}

	@Override
	public void putTemporaryCacheObject(String key, Object obj) {
		tempMemcachedClient.add(key, obj);
	}

	@Override
	public void removeCacheObject(String key) {
		memcachedClient.delete(key);
	}

	@Override
	public void removeTemporaryCacheObject(String key) {
		tempMemcachedClient.delete(key);
	}

	@Override
	public void replaceCacheObject(String key, Object obj) {
		memcachedClient.replace(key, obj);
	}

	@Override
	public void replaceTemporaryCacheObject(String key, Object obj) {
		tempMemcachedClient.replace(key, obj);
	}

	@Override
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		memcachedClient=SpringUtil.getBean("memcachedClient");
		tempMemcachedClient=SpringUtil.getBean("tempMemcachedClient");
	}
	@Test
	public void testMemcached(){
		
	}
}
