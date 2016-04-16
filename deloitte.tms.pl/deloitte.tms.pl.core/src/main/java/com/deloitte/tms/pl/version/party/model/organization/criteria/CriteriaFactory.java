package com.deloitte.tms.pl.version.party.model.organization.criteria;

public class CriteriaFactory {
	
	public static CriteriaFilter create() {
		return new CriteriaFilterImpl();
	}
}
