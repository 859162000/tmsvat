package com.deloitte.tms.pl.core.commons.utils.reflect.criteria;

import java.util.ArrayList;
import java.util.List;

import com.deloitte.tms.pl.core.commons.utils.reflect.criteria.expression.PropertyExpression;
import com.deloitte.tms.pl.core.commons.utils.reflect.filter.PropertyFilter;



public class PropertyCriteriaImpl implements PropertyCriteria {

	private List<PropertyFilter> filters = new ArrayList<PropertyFilter>();

	public List<PropertyFilter> getFilters() {
		return filters;
	}
	
	public PropertyCriteria add(PropertyExpression expression) {
		filters.add(expression.toFilter());
		return this;
	}
}
