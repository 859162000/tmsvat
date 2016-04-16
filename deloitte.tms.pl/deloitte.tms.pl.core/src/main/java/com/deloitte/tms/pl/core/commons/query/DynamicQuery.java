package com.deloitte.tms.pl.core.commons.query;

import java.util.List;
import java.util.Map;

import com.deloitte.tms.pl.core.commons.query.restriction.Restriction;


public interface DynamicQuery {

	public Map getParameters();
	
	public List getValues();
	
	public StringBuffer getQueryString();
	
	public SimpleDynamicQuery add(Restriction operator);
	
	public void generate();
	
	public Object convert(Object value, Object compare);

}
