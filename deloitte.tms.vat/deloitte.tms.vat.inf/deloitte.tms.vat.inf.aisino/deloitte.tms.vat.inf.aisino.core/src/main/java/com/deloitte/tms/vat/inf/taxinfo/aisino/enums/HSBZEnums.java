package com.deloitte.tms.vat.inf.taxinfo.aisino.enums;

public enum HSBZEnums {
	include("1"), //含税
	exclude("0"); //不含税

	private String value;

	private HSBZEnums(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
