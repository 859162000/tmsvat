package com.deloitte.tms.base.enums;

public enum InvoiceTypeEnums {
	VAT_general("1","InvoiceType.VAT_general"), //普票
	VAT_special("2","InvoiceType.VAT_special"), //专票
	VAT_mixed("3","InvoiceType.VAT_mixed"),//混合
	VAT_notInvoice("4","InvoiceType.VAT_notInvoice");//不可开票	
	
	private String value;
	private String text;

	private InvoiceTypeEnums(String value,String text) {
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
