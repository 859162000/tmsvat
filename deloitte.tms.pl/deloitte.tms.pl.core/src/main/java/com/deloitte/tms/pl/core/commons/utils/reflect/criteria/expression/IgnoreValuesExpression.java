package com.deloitte.tms.pl.core.commons.utils.reflect.criteria.expression;

import java.beans.PropertyDescriptor;
import java.util.List;

import com.deloitte.tms.pl.core.commons.utils.reflect.filter.PropertyFilter;



public class IgnoreValuesExpression implements PropertyExpression {

	private List<Object> values;
	
	public IgnoreValuesExpression(List<Object> values) {
		super();
		this.values = values;
	}


	public PropertyFilter toFilter() {
		return new PropertyFilter() {

			public boolean isPermit(PropertyDescriptor property, Object object) {
				if (values.contains(object)) {
					return false;
				}
				return true;
			}
			
		};
	}

}
