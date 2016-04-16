package com.deloitte.tms.pl.core.model.impl;

public class Description {

	private String descType;

	private String descName;

	public Description(String descType, String descName) {
		super();
		this.descType = descType;
		this.descName = descName;
	}

	public String getDescName() {
		return descName;
	}

	public void setDescName(String descName) {
		this.descName = descName;
	}

	public String getDescType() {
		return descType;
	}

	public void setDescType(String descType) {
		this.descType = descType;
	}

}
