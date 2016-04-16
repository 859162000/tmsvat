package com.deloitte.tms.pl.core.commons.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.BooleanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.deloitte.tms.pl.core.commons.exception.BusinessException;

/**
 * 自定义PropertyPlaceholderConfigurer返回properties内容
 * 
 * @author bo.wang
 * 
 */
public class PropertiesUtils extends PropertyPlaceholderConfigurer {
	private static Map<String, Object> ctxPropertiesMap=new HashMap<String, Object>();

	public static Object get(String key) {
		return ctxPropertiesMap.get(key);
	}
	
	public static Map<String, Object> getStore() {
		return ctxPropertiesMap;
	}

	@Override
	protected void processProperties(
			ConfigurableListableBeanFactory beanFactoryToProcess,
			Properties props) throws BeansException {
		super.processProperties(beanFactoryToProcess, props);
		ctxPropertiesMap = new HashMap<String, Object>();
		for (Object key : props.keySet()) {
			String keyStr = key.toString();
			String value = props.getProperty(keyStr);
			ctxPropertiesMap.put(keyStr, value);
		}
//		ConfigureStore store = Configure.getStore();
//		for (String key : store.keySet()) {
//			ctxPropertiesMap.put(key, store.getString(key));
//		}
	}

	public static Object getContextProperty(String name) {
		return ctxPropertiesMap.get(name);
	}
	public static Object getFileProperty(String path,String name) {
		Properties p=getFileProperty(path);
		return p.get(name);
	}
	public static Properties getFileProperty(String path) {
		Properties p = new Properties();
		try {
			p = PropertiesLoaderUtils
					.loadAllProperties(path);
			return p;
		} catch (Exception e) {
			throw new BusinessException("读取文件:"+path+"失败");
		}
	}
	public static Boolean getBoolean(String key){
		Object value=get(key);
		if(AssertHelper.notEmpty(key)){
			try {
				return BooleanUtils.toBoolean((value == null) ? null : value
						.toString());
			} catch (Exception e) {
				throw new BusinessException(value+" 不能被转换为boolean类型");
			}
		}
		return false;		
	}
	public static Boolean getBoolean(String key,boolean defaultvalue){
		try {
			return getBoolean(key);
		} catch (Exception e) {
			return defaultvalue;
		}
	}
	public static Long getLong(String key){
		Object value=get(key);
		if(AssertHelper.notEmpty(key)){
			try {
				return (value == null) ? null : Long.parseLong(value
						.toString());
			} catch (Exception e) {
				throw new BusinessException(value+" 不能被转换为boolean类型");
			}
		}
		return null;		
	}
	public static Long getLong(String key,Long defaultvalue){
		try {
			return getLong(key);
		} catch (Exception e) {
			return defaultvalue;
		}
	}
}
