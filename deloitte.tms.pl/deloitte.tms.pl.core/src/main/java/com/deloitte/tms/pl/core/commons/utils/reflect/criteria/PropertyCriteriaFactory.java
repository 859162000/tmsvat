package com.deloitte.tms.pl.core.commons.utils.reflect.criteria;

public class PropertyCriteriaFactory {

	public static PropertyCriteria create() {
		return new PropertyCriteriaImpl();
	}
	
}
