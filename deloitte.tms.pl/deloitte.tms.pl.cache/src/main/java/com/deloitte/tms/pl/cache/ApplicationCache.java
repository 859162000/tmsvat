package com.deloitte.tms.pl.cache;

/**
 * @author bo.wang
 * @since 2013-5-21
 */
public interface ApplicationCache {
	
	public static final String APPNAME="LING2";
	public static final String BEAN_ID = "ling2.applicationCache";
	/**
	 * 根据指定的key，从缓存当中获取一个对象
	 * @param key 缓存当中对象的key值
	 * @return 返回缓存当中与给定key对应的对象值，如果对象不存在，就返回null
	 */
	Object getCacheObject(String key);

	/**
	 * 从临时缓存当中获取一个被临时缓存对象，<br>
	 * 默认情况下，位于临时缓存中对象生命周期为1800秒，也就是半小时
	 * @param key 缓存当中对象的key值
	 * @return 返回缓存当中与给定key对应的对象值，如果对象不存在，就返回null
	 */
	Object getTemporaryCacheObject(String key);

	/**
	 * 将一个对象放入缓存当中，同时如果缓存当中有存在相同key的对象，则进行覆盖
	 * @param key 对象的key
	 * @param obj 具体对象
	 */
	void putCacheObject(String key, Object obj);

	/**
	 * 将一个对象放入临时缓存当中，同时如果缓存当中有存在相同key的对象，则进行覆盖，<br>
	 * 默认情况下，位于临时缓存中对象生命周期为1800秒，也就是半小时
	 * @param key 对象的key
	 * @param obj 具体对象
	 */
	void putTemporaryCacheObject(String key, Object obj);

	/**
	 * 从缓存当中移除一个对象
	 * @param key 要移除的对象的key值
	 */
	void removeCacheObject(String key);

	/**
	 * 从临时缓存当中移除一个对象
	 * @param key 要移除的对象的key值
	 */
	void removeTemporaryCacheObject(String key);

	/**
	 * 从缓存当中移除一个对象
	 * @param key 要移除的对象的key值
	 */
	void replaceCacheObject(String key, Object obj);

	/**
	 * 从临时缓存当中移除一个对象
	 * @param key 要移除的对象的key值
	 */
	void replaceTemporaryCacheObject(String key, Object obj);

	
}