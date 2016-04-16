package com.deloitte.tms.vat.inf.taxinfo.aisino.enums;

public enum KPBZEnums {
	
	open("0"), //0：开票 
	check("1"), //1：校验 
	obsoleteNext("2");//2：空白作废下一张发票
	
	private String value;

	private KPBZEnums(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
