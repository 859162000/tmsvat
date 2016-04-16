package com.deloitte.tms.pl.version.party.model.organization.criteria;

import java.util.List;

import com.deloitte.tms.pl.version.party.model.organization.criteria.expression.Expression;
import com.deloitte.tms.pl.version.party.model.organization.filter.Filter;


public interface CriteriaFilter {

	public List<Filter> getFilterChain();
	
	public CriteriaFilter add(Expression expression);
	
}
