package com.deloitte.tms.pl.version.party.model.organization.criteria.expression;

import ognl.Ognl;
import ognl.OgnlException;

import com.deloitte.tms.pl.version.party.model.organization.filter.Filter;

public class NeExpression implements Expression {

	private final String propertyName;
	
	private final String value;
	
	public NeExpression(String propertyName, String value) {
		super();
		this.propertyName = propertyName;
		this.value = value;
	}

	public Filter convertToFilter() {
		return new Filter() {
			public boolean isPermitted(Object obj) {
				Object parse;
				try {
					parse = Ognl.parseExpression(propertyName);
					Object queryObject = Ognl.getValue(parse, obj);
					if (!queryObject.equals(value)) {
						return true;
					}
				} catch (OgnlException e) {
					throw new RuntimeException("[Ognl failed]", e);
				}
				return false;
			}
		};
	}
	
}
