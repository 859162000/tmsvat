package com.deloitte.tms.pl.core.commons.annotation;

import com.deloitte.tms.pl.core.commons.utils.AnnotationUtils;

public class AnnotationTest {
	@ModelProperty(comment="test1")
	String test1;
	@ModelProperty(comment="test2")
	String test2;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(AnnotationUtils.getModelPropertyOfComment(AnnotationTest.class, "test1"));
	}
}
