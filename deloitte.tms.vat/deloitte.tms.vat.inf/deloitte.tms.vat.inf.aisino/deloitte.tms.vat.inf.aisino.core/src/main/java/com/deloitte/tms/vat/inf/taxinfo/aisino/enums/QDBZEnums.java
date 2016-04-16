package com.deloitte.tms.vat.inf.taxinfo.aisino.enums;

public enum QDBZEnums {
	open("1"), //开具清单
	notopen("0"); //不开具清单
	
	private String value;

	private QDBZEnums(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
