package com.deloitte.tms.pl.cache;

import com.deloitte.tms.pl.cache.ApplicationCache;
import com.deloitte.tms.pl.core.commons.exception.BusinessException;
import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.core.commons.utils.PropertiesUtils;

public class CacheUtils {
	
	private static ApplicationCache applicationCache;
	
	private static ApplicationCache getApplicationCache(){
		if(applicationCache==null){
			String cacheimpl=(String) PropertiesUtils.getFileProperty("config/cache.properties","cache.impl");
			if(AssertHelper.notEmpty(cacheimpl)){
				try {
					applicationCache=(ApplicationCache) Class.forName(cacheimpl).newInstance();
				} catch (InstantiationException e) {
					throw new BusinessException("类:"+cacheimpl+"初始化失败");
				} catch (IllegalAccessException e) {
					throw new BusinessException("类:"+cacheimpl+"构造失败");
				} catch (ClassNotFoundException e) {
					throw new BusinessException("类:"+cacheimpl+"没有找到");
				}
			}else {
				throw new BusinessException("缓存的实现类没有配置");
			}
		}
		return applicationCache;
	}
	/**
	 * 根据指定的key，从缓存当中获取一个对象
	 * @param key 缓存当中对象的key值
	 * @return 返回缓存当中与给定key对应的对象值，如果对象不存在，就返回null
	 */
	public static Object getCacheObject(String key){
		return getApplicationCache().getCacheObject(key);
	}

	/**
	 * 从临时缓存当中获取一个被临时缓存对象，<br>
	 * 默认情况下，位于临时缓存中对象生命周期为1800秒，也就是半小时
	 * @param key 缓存当中对象的key值
	 * @return 返回缓存当中与给定key对应的对象值，如果对象不存在，就返回null
	 */
	public static Object getTemporaryCacheObject(String key){
		return getApplicationCache().getTemporaryCacheObject(key);
	}

	/**
	 * 将一个对象放入缓存当中，同时如果缓存当中有存在相同key的对象，则进行覆盖
	 * @param key 对象的key
	 * @param obj 具体对象
	 */
	public static void putCacheObject(String key, Object obj){
		getApplicationCache().putCacheObject(key,obj);
	}

	/**
	 * 将一个对象放入临时缓存当中，同时如果缓存当中有存在相同key的对象，则进行覆盖，<br>
	 * 默认情况下，位于临时缓存中对象生命周期为1800秒，也就是半小时
	 * @param key 对象的key
	 * @param obj 具体对象
	 */
	public static void putTemporaryCacheObject(String key, Object obj){
		getApplicationCache().putTemporaryCacheObject(key, obj);
	}

	/**
	 * 从缓存当中移除一个对象
	 * @param key 要移除的对象的key值
	 */
	public static void removeCacheObject(String key){
		getApplicationCache().removeCacheObject(key);
	}

	/**
	 * 从临时缓存当中移除一个对象
	 * @param key 要移除的对象的key值
	 */
	public static void removeTemporaryCacheObject(String key){
		getApplicationCache().removeTemporaryCacheObject(key);
	}

	/**
	 * 从缓存当中移除一个对象
	 * @param key 要移除的对象的key值
	 */
	public static void replaceCacheObject(String key, Object obj){
		getApplicationCache().replaceCacheObject(key,obj);
	}

	/**
	 * 从临时缓存当中移除一个对象
	 * @param key 要移除的对象的key值
	 */
	public static void replaceTemporaryCacheObject(String key, Object obj){
		getApplicationCache().replaceTemporaryCacheObject(key,obj);
	}
}
