package com.deloitte.tms.pl.core.commons.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import com.deloitte.tms.pl.core.commons.annotation.ModelProperty;

/**
 * 
 * @author bo.wang
 *
 */
public class AnnotationUtils {
	/**
	 * 获取ModelProperty的Comment信息
	 * @param model 类名
	 * @param property 属性,字段
	 * @return
	 */
	public static String getModelPropertyOfComment(Class model,String property)
	{
		try {
			Field field=model.getDeclaredField(property);
			Annotation annotation=field.getAnnotation(ModelProperty.class);
			ModelProperty modelProperty=(ModelProperty)annotation;
			return modelProperty.comment();
		} catch (Exception e) {
			return null;
		} 
	}
	public static String getModelPropertyOfComment(Field field)
	{
		try {
			Annotation annotation=field.getAnnotation(ModelProperty.class);
			ModelProperty modelProperty=(ModelProperty)annotation;
			return modelProperty.comment();
		} catch (Exception e) {
			return null;
		} 
	}
}
