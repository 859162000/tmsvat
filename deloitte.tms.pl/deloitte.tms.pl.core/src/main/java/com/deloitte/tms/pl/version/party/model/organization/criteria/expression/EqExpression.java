package com.deloitte.tms.pl.version.party.model.organization.criteria.expression;

import ognl.Ognl;
import ognl.OgnlException;

import com.deloitte.tms.pl.version.party.model.organization.filter.Filter;

public class EqExpression implements Expression {

	private final String propertyName;
	
	private final Object value;
	
	public EqExpression(String propertyName, Object value) {
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
					if (value.equals(queryObject)) {
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
