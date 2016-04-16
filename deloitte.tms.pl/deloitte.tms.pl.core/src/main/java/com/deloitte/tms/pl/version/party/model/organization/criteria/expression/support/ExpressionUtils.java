package com.deloitte.tms.pl.version.party.model.organization.criteria.expression.support;

import java.lang.reflect.Method;

@SuppressWarnings("unchecked")
public class ExpressionUtils {
	
	/**
	 * ��ȡisEmpty����
	 * 
	 * @param clazz
	 * @return
	 */
	public static Method getEmptyMethod(Class clazz) {
		Method[] methods = clazz.getMethods();
		for (int i = 0; i < methods.length; i++) {
			Method method = methods[i];
			if (method.getName().equals("isEmpty")) {
				String returnTypeName = method.getReturnType().getName();
				Class[] returnParameterTypes = method.getParameterTypes();
				if (returnTypeName.equals("boolean") && returnParameterTypes.length == 0) {
					return method;
				}
			}
		}
		return null;
	}
	
	/**
	 * �Ƿ����isEmpty����
	 * 
	 * @param clazz
	 * @return
	 */
	public static boolean isContainEmptyMethod(Class clazz) {
		Method[] methods = clazz.getMethods();
		for (int i = 0; i < methods.length; i++) {
			Method method = methods[i];
			if (method.getName().equals("isEmpty")) {
				String returnTypeName = method.getReturnType().getName();
				Class[] returnParameterTypes = method.getParameterTypes();
				if (returnTypeName.equals("boolean") && returnParameterTypes.length == 0) {
					return true;
				}
			}
		}
		return false;
	}

}
