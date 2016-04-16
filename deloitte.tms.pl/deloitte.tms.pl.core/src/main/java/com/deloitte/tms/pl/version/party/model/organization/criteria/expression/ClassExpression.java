package com.deloitte.tms.pl.version.party.model.organization.criteria.expression;

import com.deloitte.tms.pl.version.party.model.organization.filter.Filter;

@SuppressWarnings("unchecked")
public class ClassExpression implements Expression {
	
	private final Class clazz;
	
	public ClassExpression(final Class clazz) {
		super();
		this.clazz = clazz;
	}

	public Filter convertToFilter() {
		return new Filter() {
			
			public boolean isPermitted(Object obj) {
				if (obj.getClass() == clazz) {
					return true;
				}
				return false;
			}
			
		};
	}
	
	

}
