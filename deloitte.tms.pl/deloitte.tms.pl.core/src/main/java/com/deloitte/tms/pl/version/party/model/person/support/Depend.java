package com.deloitte.tms.pl.version.party.model.person.support;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.builder.HashCodeBuilder;

public class Depend {

	public final static String DEFAULT = "DEFAULT";
	
	public final static String CUSTOM = "CUSTOM";
	
	public final static String TRANSIENT = "TRANSIENT";
	
	private String code;

	private Object value;

	private String status;

	private int month = 0;

	private Map<String, Object> properties = new HashMap<String, Object>();
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Depend)) {
			return false;
		}
		Depend depend = (Depend) obj;
		if (code == depend.code && month == depend.month) {
			return true;
		}
		return false;
	}

	public int hashCode() {
		HashCodeBuilder builder = new HashCodeBuilder();
		builder.append(code).append(month);
		return builder.toHashCode();
	}

	public String toString() {
		if (this != null) {
			return this.value.toString();
		} else {
			return "";
		}
	}
	
	public void putPropertyValue(String propertyCode, Object propertyValue) {
		properties.put(propertyCode, propertyValue);
	}
	
	public Object getPropertyValue(String propertyCode) {
		return properties.get(propertyCode);
	}
	
	public void setProperties(Map<String, Object> properties) {
		this.properties = properties;
	}
	
	public Map<String, Object> getProperties() {
		return this.properties;
	}
	
}
