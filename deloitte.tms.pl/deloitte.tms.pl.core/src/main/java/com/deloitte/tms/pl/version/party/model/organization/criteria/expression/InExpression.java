package com.deloitte.tms.pl.version.party.model.organization.criteria.expression;

import java.util.Collection;
import java.util.Iterator;

import ognl.Ognl;
import ognl.OgnlException;

import com.deloitte.tms.pl.version.party.model.organization.filter.Filter;

public class InExpression implements Expression {

	private final String propertyName;
	
	private final Collection<Object> values;
	
	public InExpression(final String propertyName, final Collection<Object> values) {
		super();
		this.propertyName = propertyName;
		this.values = values;
	}

	public Filter convertToFilter() {
		return new Filter() {

			public boolean isPermitted(Object obj) {
				Object parse;
				try {
					parse = Ognl.parseExpression(propertyName);
					Object queryObject = Ognl.getValue(parse, obj);
					for (Iterator<Object> iter = values.iterator(); iter.hasNext();) {
						Object value = (Object) iter.next();
						if (queryObject.equals(value)) {
							return true;
						}
					}
				} catch (OgnlException e) {
					throw new RuntimeException("[Ognl failed]", e);
				}
				return false;
			}
			
		};
	}

}
