package com.deloitte.tms.vat.base.enums;

import java.io.Serializable;

	/**
	 * 销项发票种类
	 *
	 * @author admin
	 * @create Mar 18, 2016 2:37:07 PM 
	 * @version 1.0.0
	 */
public enum VatCRInvoiceTypeEnums implements Serializable{
	
	VAT_CR_INVOICE_PP("1","增值税普通发票"), //
	VAT_CR_INVOICE_ZP("2","增值税专用发票"); //
	
	private String value;
	private String text;

	private VatCRInvoiceTypeEnums(String value,String text) {
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

