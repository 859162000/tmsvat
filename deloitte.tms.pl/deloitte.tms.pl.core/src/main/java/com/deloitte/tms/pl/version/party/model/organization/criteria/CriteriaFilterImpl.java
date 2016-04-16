package com.deloitte.tms.pl.version.party.model.organization.criteria;

import java.util.ArrayList;
import java.util.List;

import com.deloitte.tms.pl.version.party.model.organization.criteria.expression.Expression;
import com.deloitte.tms.pl.version.party.model.organization.filter.Filter;

public class CriteriaFilterImpl implements CriteriaFilter {

	private List<Filter> filterChain;
	
	public List<Filter> getFilterChain() {
		return filterChain;
	}
	
	
	
	public CriteriaFilterImpl() {
		super();
		this.filterChain = new ArrayList<Filter>();
	}



	public CriteriaFilter add(Expression expression) {
		filterChain.add(expression.convertToFilter());
		return this;
	}

}
