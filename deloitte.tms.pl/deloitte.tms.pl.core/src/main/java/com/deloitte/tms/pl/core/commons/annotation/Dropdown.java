package com.deloitte.tms.pl.core.commons.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.deloitte.tms.pl.core.commons.annotation.enums.DropDownType;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.TYPE})
@Inherited
public @interface Dropdown {
	String relClass();
	String filterProvider();
	String assignmentMap() default "";
	DropDownType dropDownType() default DropDownType.dialog;
	String disProperty();
}
