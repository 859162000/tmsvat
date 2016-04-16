package com.deloitte.tms.pl.core.commons.query;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.deloitte.tms.pl.core.commons.query.restriction.Restriction;


/**
 * 动态查询类
 * 
 * @author dada
 */
public class SimpleDynamicQuery implements DynamicQuery {

	private List restrictions;

	private List values;

	protected StringBuffer queryString;

	public SimpleDynamicQuery(StringBuffer queryString) {
		super();
		this.queryString = queryString;
		this.restrictions = new ArrayList();
		this.values = new ArrayList();
	}

	public SimpleDynamicQuery add(Restriction restriction) {
		restrictions.add(restriction);
		return this;
	}

	public void generate() {
		for (Iterator iter = restrictions.iterator(); iter.hasNext();) {
			Restriction restriction = (Restriction) iter.next();
			StringBuffer sinppet = restriction.getSnippet();
			Object compare = restriction.getCompare();
			Object value = restriction.getValue();
			Object retval = convert(value, compare);
			if (!value.equals(retval) && value == compare) {
				restriction.setCompare(retval);
			}
			if (restriction.isPermitted()) {
				queryString.append(sinppet);
				values.add(retval);
			}
		}
	}

	protected List getRestrictions() {
		return restrictions;
	}

	public StringBuffer getQueryString() {
		return queryString;
	}

	public List getValues() {
		return values;
	}

	public Object convert(Object value, Object compare) {
		return value;
	}

	public Map getParameters() {
		return null;
	}

}
