package com.deloitte.tms.pl.core.commons.support;

public class DaoOrder {
	private String property;
	private String propertyPath;
	private boolean desc;

	public DaoOrder() {
	}

	public DaoOrder(String property, boolean desc) {
		this.property = property;
		this.desc = desc;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public void setDesc(boolean desc) {
		this.desc = desc;
	}

	public String getPropertyPath() {
		return propertyPath;
	}

	public void setPropertyPath(String propertyPath) {
		this.propertyPath = propertyPath;
	}

	public boolean isDesc() {
		return desc;
	}
}