package com.deloitte.tms.pl.core.commons.utils.reflect.criteria;

import java.util.List;

import com.deloitte.tms.pl.core.commons.utils.reflect.criteria.expression.PropertyExpression;
import com.deloitte.tms.pl.core.commons.utils.reflect.filter.PropertyFilter;



public interface PropertyCriteria {

	public PropertyCriteria add(PropertyExpression expression);
	
	public List<PropertyFilter> getFilters();
	
}
