//package com.ling2.cache;
//
//import static org.junit.Assert.assertEquals;
//
//import org.junit.Test;
//
//public class CacheUtilsTest {
//	
//	@Test
//	public void testMemcached(){
//		String key="testcache";
//		String value="testcachevalue";
//		String newvalue="newtestcachevalue";
//		
//		CacheUtils.putCacheObject(key, value);
//		assertEquals(value, CacheUtils.getCacheObject(key));
//		CacheUtils.replaceCacheObject(key,newvalue);
//		assertEquals(newvalue, CacheUtils.getCacheObject(key));
//		CacheUtils.removeCacheObject(key);
//		assertEquals(null, CacheUtils.getCacheObject(key));
//		
//		CacheUtils.putTemporaryCacheObject(key, value);
//		assertEquals(value, CacheUtils.getTemporaryCacheObject(key));
//		CacheUtils.replaceTemporaryCacheObject(key,newvalue);
//		assertEquals(newvalue, CacheUtils.getTemporaryCacheObject(key));
//		CacheUtils.removeTemporaryCacheObject(key);
//		assertEquals(null, CacheUtils.getTemporaryCacheObject(key));
//	}
//}
