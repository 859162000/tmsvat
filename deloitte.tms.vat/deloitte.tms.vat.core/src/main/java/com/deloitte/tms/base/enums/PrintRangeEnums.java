package com.deloitte.tms.base.enums;

public enum PrintRangeEnums {
	current("2","PrintRange.VAT_general"), //本组织
	all("1","PrintRange.VAT_special"), //全局
	city("3","PrintRange.VAT_mixed");//同城
	
	private String value;
	private String text;

	private PrintRangeEnums(String value,String text) {
		this.value = value;
		this.text = text;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
