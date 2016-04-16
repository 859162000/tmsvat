package com.deloitte.tms.pl.core.commons.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.deloitte.tms.pl.core.commons.annotation.enums.EditType;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.TYPE})
@Inherited
public @interface ModelProperty {
	String comment() default "";
	
	String des() default "";

	boolean isquery() default true;

	boolean iscolnum() default true;
	
	boolean isrequired() default false;
	
	boolean isOneToManay() default false;
	
	String relationInInfo() default "";//Market|CommodityMarket|marketId|CommodityId
	
	String relationId() default "";
	
	String dictCode() default "";
	
	EditType editType() default EditType.form;
	
	boolean isIntro() default false;
	//select t from Market t,CommodityMarket cm where t.id=cm.marketId and cm.CommodityId=:id
	//
}
