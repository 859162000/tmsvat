package com.deloitte.tms.vat.inf.taxinfo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.TYPE})
@Inherited
public @interface AisinoSHReturnSerializeProperty {
	int length() default 0;	
	String des() default "";
	String format() default "";
	boolean require() default false;
	boolean valueFormat() default false;
	/**
	 * 字段自身别名 非集合对象,无此表示不输出
	 * @return
	 */
	String serializeName() default "";
	/**
	 * 如果是集合,循环集时单项别名 无serializeName表示集合不外套标签
	 * serializeName和itemSerializeName同时为空,表示不输出
	 * @return
	 */
	String itemSerializeName() default "";
	boolean outSize() default false;
}
