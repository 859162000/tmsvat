package com.deloitte.tms.pl.core.commons.utils;

import java.util.Map;

public class ViewUtils {
	public static <X> X getParamValue(String key,Map params){
		if(params==null){
			return null;
		}
		return (X) params.get(key);
	}
	public static <X> X getParamValue(String key,Map params,Object defalut){
		if(params==null){
			return (X) defalut;
		}
		if(params.get(key)==null){
			return (X) defalut;
		}
		return (X) params.get(key);
	}
	public static String getString(Map<String, Object> parameters,
			String propertyName) {
		return Converter.to(String.class).convert(parameters, propertyName);
	}

	public static Long getLong(String key,Map params) {
		return Converter.to(Long.class).convert(params, key);
	}
}
