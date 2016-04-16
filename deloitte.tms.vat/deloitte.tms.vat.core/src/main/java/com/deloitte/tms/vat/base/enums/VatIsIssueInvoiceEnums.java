package com.deloitte.tms.vat.base.enums;

import java.io.Serializable;

	/**
	 * 是否开票
	 *
	 * @author admin
	 * @create Mar 18, 2016 2:37:07 PM 
	 * @version 1.0.0
	 */
public enum VatIsIssueInvoiceEnums implements Serializable{
	
	VAT_IS_ISSUE_INVOICEY("Y","是"), //
	VAT_IS_ISSUE_INVOICEN("N","否"); //
	
	private String value;
	private String text;

	private VatIsIssueInvoiceEnums(String value,String text) {
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

