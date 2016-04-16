package com.deloitte.tms.pl.version.party.model.organization.criteria.expression.support;

import org.apache.commons.lang.StringUtils;

public abstract class MatchMode {
	
	public final static MatchMode START = new MatchMode() {
		
		public boolean matchMethod(String str, String value) {
			boolean retval = false;
			
			if (str.length() >= value.length()) {
				String temp = str.substring(0, value.length());
				if (temp.equals(value)) {
					retval = true;
				}
			}
			return retval;
		}
		
	};

	public final static MatchMode END = new MatchMode() {
		
		public boolean matchMethod(String str, String value) {
			boolean retval = false;
			
			if(str.length() >= value.length()) {
				String temp = str.substring(str.length() - value.length(), str.length());
				if (temp.equals(value)) {
					retval = true;
				}
			}
			return retval;
		}
		
	};
	
	public final static MatchMode ANYWHERE = new MatchMode() {

		public boolean matchMethod(String str, String value) {
			return StringUtils.contains(str, value);
		}

	}; 
	
	public abstract boolean matchMethod(String str, String value);
	
}
