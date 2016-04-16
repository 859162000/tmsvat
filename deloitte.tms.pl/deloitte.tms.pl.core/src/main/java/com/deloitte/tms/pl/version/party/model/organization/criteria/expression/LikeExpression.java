package com.deloitte.tms.pl.version.party.model.organization.criteria.expression;

import ognl.Ognl;
import ognl.OgnlException;

import com.deloitte.tms.pl.version.party.model.organization.criteria.expression.support.MatchMode;
import com.deloitte.tms.pl.version.party.model.organization.filter.Filter;

public class LikeExpression implements Expression {

	private final String propertyName;
	
	private final String value;
	
	private final MatchMode mode;
	
	public LikeExpression(String propertyName, String value, MatchMode mode) {
		super();
		this.propertyName = propertyName;
		this.value = value;
		this.mode = mode;
	}
	
	public Filter convertToFilter() {
		return new Filter() {
			public boolean isPermitted(Object obj) {
				Object parse;
				try {
					parse = Ognl.parseExpression(propertyName);
					Object queryObject = Ognl.getValue(parse, obj);
					return mode.matchMethod(queryObject.toString(), value);
				} catch (OgnlException e) {
					throw new RuntimeException("[Ognl failed]", e);
				}
			}
		};
	}

}
