package com.deloitte.tms.pl.version.party.model.organization.criteria.expression;

import java.util.Iterator;
import java.util.List;

import com.deloitte.tms.pl.version.party.model.organization.filter.Filter;


public class OrExpression implements Expression {

	private final List<Expression> expressionList;
	
	public OrExpression(final List<Expression> expressionList) {
		super();
		this.expressionList = expressionList;
	}

	public Filter convertToFilter() {
		return new Filter() {

			public boolean isPermitted(Object obj) {
				for (Iterator<Expression> iter = expressionList.iterator(); iter.hasNext();) {
					Expression expression = (Expression) iter.next();
					if (expression.convertToFilter().isPermitted(obj)) {
						return true;
					}
				}
				return false;
			}
			
		};
	}
	
	public OrExpression add(Expression expression) {
		expressionList.add(expression);
		return this;
	}

}
