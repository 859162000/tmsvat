package com.deloitte.tms.pl.core.commons.constant;

public enum EnableFlagEnums {
	
	ENABLE_EFFECT("1","EnableFlagEnums.ENABLE_EFFECT"), //普票
	ENABLE_DISABLED("2","EnableFlagEnums.ENABLE_DISABLED");
	
	private String value;
	private String text;

	private EnableFlagEnums(String value,String text) {
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
