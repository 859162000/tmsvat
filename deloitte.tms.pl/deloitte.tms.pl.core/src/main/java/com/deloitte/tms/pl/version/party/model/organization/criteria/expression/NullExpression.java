package com.deloitte.tms.pl.version.party.model.organization.criteria.expression;

import ognl.Ognl;
import ognl.OgnlException;

import com.deloitte.tms.pl.version.party.model.organization.filter.Filter;

public class NullExpression implements Expression {

	private final String propertyName;
	
	private final boolean mode;
	
	public NullExpression(final String propertyName, final boolean mode) {
		super();
		this.propertyName = propertyName;
		this.mode = mode;
	}

	public Filter convertToFilter() {
		return new Filter() {

			public boolean isPermitted(Object obj) {
				Object parse;
				try {
					parse = Ognl.parseExpression(propertyName);
					Object queryObject = Ognl.getValue(parse, obj);
					return (queryObject == null) && mode;
				} catch (OgnlException e) {
					throw new RuntimeException("[Ognl failed]", e);
				}
			}
			
		};
	}

}
