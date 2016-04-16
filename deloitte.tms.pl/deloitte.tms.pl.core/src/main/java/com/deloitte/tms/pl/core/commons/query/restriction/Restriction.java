package com.deloitte.tms.pl.core.commons.query.restriction;

public interface Restriction {

	public boolean isPermitted();
	
	public StringBuffer getSnippet();
	
	public Object getValue();
	
	public Object getCompare();
	
	public void setCompare(Object compare);
	
}
