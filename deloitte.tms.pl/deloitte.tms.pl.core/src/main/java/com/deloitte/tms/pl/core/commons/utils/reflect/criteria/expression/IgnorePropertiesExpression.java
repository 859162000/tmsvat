package com.deloitte.tms.pl.core.commons.utils.reflect.criteria.expression;

import java.beans.PropertyDescriptor;
import java.util.List;

import com.deloitte.tms.pl.core.commons.utils.reflect.filter.PropertyFilter;



public class IgnorePropertiesExpression implements PropertyExpression {

	private List<String> properties;

	public IgnorePropertiesExpression(List<String> properties) {
		super();
		this.properties = properties;
	}

	public PropertyFilter toFilter() {
		return new PropertyFilter() {

			public boolean isPermit(PropertyDescriptor property, Object object) {
				if (properties.contains(property.getName())) {
					return false;
				}
				return true;
			}
			
		};
	}
	
	
	
}
