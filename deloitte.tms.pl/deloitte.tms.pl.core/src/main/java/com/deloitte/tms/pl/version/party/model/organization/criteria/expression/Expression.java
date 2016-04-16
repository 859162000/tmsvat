package com.deloitte.tms.pl.version.party.model.organization.criteria.expression;

import com.deloitte.tms.pl.version.party.model.organization.filter.Filter;


public interface Expression {

	public Filter convertToFilter();
}
