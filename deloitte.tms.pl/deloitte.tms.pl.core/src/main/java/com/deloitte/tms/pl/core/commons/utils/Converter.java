package com.deloitte.tms.pl.core.commons.utils;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


import org.apache.commons.beanutils.ConvertUtils;

/**
 * <pre>
 * DORADO 传参工具类
 * 
 * 适用场景：
 * DORADO在某些情况下传递的参数会失去指定的类型，而被其转换为默认的String类型。
 * 
 * VIEW层参数获取一律使用本方法来避免可能的ClassCastException。
 * 
 * </pre>
 * @author ajoo, dada
 * @see thx ajoo
 * @param <T>
 */
@SuppressWarnings("unchecked")
public final class Converter<T> {
	
	/** 指定类型 **/
	private final Class<T> targetType;
	
	private Converter(Class<T> targetType) {
		this.targetType = targetType;
	}

	/**
	 * 将参数转换后的类型
	 * 
	 * @param <T>
	 * @param targetType
	 * @return
	 * @author dada
	 */
	public static <T> Converter<T> to(Class<T> targetType) {
		return new Converter<T>(targetType);
	}
	
	
	/**
	 * 根据指定的参数集合和属性，获取对应的值，并进行自动转型。
	 * 如果值为Null，则返回Null。
	 * 
	 * <strong>程序范例：</strong>
	 * 
	 * public void execCommandSample(Map dataSets, Object parameter) throws Exception {
	 *     String text = Converter.to(String.class).convert(parameter, "text");
	 *     ...
	 * }
	 * 
	 * </pre>
	 * @param parameters
	 * @param propertyName
	 * @return
	 * @author dada
	 */
	public T convert(Map<String, Object> parameters, String propertyName) {
		if(parameters==null)
		{
			return null;
		}
		Object value = parameters.get(propertyName);
		if (isControled(value)) {
			String parameter = value == null ? null : value.toString();
			if (parameter == null || "".equals(parameter.trim())) {
				return null;
			}
			value = (T) ConvertUtils.convert(parameter, targetType);
		}
		return (T) value;
	}
	
	/**
	 * 将指定的对象自动转型。
	 * 如果值为Null，则返回Null。
	 * 
	 * <strong>程序范例：</strong>
	 *
	 * String text = Converter.to(String.class).convert(object);
	 * ...
	 * 
	 * </pre>
	 * @param parameters
	 * @param propertyName
	 * @return
	 * @author dada
	 */
	public T convert(Object o) {
		String parameter = o == null ? null : o.toString();
		if (parameter == null || "".equals(parameter.trim())) {
			return null;
		}
		return (T) ConvertUtils.convert(parameter, targetType);
	}
	
	private boolean isControled(Object value) {
		if (value instanceof Date) {
			return false;
		}
		return true;
	}
	
	/**
	 * <pre>
	 * 根据指定的DataSet和属性，获取对应的值，并进行自动转型。
	 * 如果值为Null或"null"，则返回Null。 适用于DORADO进行URL形式参数传递的场景
	 * 
	 * <strong>程序范例：</strong>
	 * 
	 * 	Long academyid = Converter.to(Long.class).convert(request, "academyid");
	 *  String code = Converter.to(String.class).convert(request, "code");
	 * 
	 * </pre>
	 * @param request 
	 * @param propertyName
	 * @return
	 * 
	 * @author xingchang.zhang
	 * @since 2008-09-24
	 */
	public T convert(HttpServletRequest request, String propertyName) {
		String parameter = request.getParameter(propertyName);
		if (parameter == null || "".equals(parameter.trim()) || "null".equals(parameter.trim())) {
			return null;
		}
		return (T) ConvertUtils.convert(parameter, targetType);
	}
	
}
