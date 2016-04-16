package com.deloitte.tms.pl.core.commons.utils.reflect.criteria.expression;

import com.deloitte.tms.pl.core.commons.utils.reflect.filter.PropertyFilter;


public interface PropertyExpression {

	public PropertyFilter toFilter();
	
}
