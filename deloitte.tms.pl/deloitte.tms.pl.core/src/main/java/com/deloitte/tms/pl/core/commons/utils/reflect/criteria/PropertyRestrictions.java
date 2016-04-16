package com.deloitte.tms.pl.core.commons.utils.reflect.criteria;

import java.util.ArrayList;
import java.util.List;

import com.deloitte.tms.pl.core.commons.utils.reflect.criteria.expression.IgnorePropertiesExpression;
import com.deloitte.tms.pl.core.commons.utils.reflect.criteria.expression.IgnoreValuesExpression;
import com.deloitte.tms.pl.core.commons.utils.reflect.criteria.expression.PropertyExpression;



public class PropertyRestrictions {

	public static PropertyExpression ignoreProperty(String propertyName) {
		List<String> propertyNames = new ArrayList<String>();
		propertyNames.add(propertyName);
		return new IgnorePropertiesExpression(propertyNames);
	}
	
	public static PropertyExpression ignoreProperties(List<String> propertyNames) {
		return new IgnorePropertiesExpression(propertyNames); 
	}
	
	public static PropertyExpression ignoreValue(Object value) {
		List<Object> propertyValues = new ArrayList<Object>();
		propertyValues.add(value);
		return new IgnoreValuesExpression(propertyValues);
	}
	
	public static PropertyExpression ignoreValues(List<Object> values) {
		return new IgnoreValuesExpression(values);
	}
	
}
