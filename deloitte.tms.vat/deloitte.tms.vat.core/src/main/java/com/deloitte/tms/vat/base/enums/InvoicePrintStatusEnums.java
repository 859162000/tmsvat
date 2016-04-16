package com.deloitte.tms.vat.base.enums;

import java.io.Serializable;

public enum InvoicePrintStatusEnums implements Serializable{
	
	TOBEINVOICE("10","待开具"), TOBEPRINT("20","待打印"), PRINTED("30","已打印"),
	PRINTFAIL("40","打印失败");
	
	private String value;
	private String text;
	
	private InvoicePrintStatusEnums(String value,String text) {
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

