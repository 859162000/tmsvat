package com.deloitte.tms.vat.base.enums;

import java.io.Serializable;

	/**
	 * 是否预约开票
	 *
	 * @author admin
	 * @create Mar 18, 2016 2:37:07 PM 
	 * @version 1.0.0
	 */
public enum VatIsAppointIssuInvoiceEnums implements Serializable{
	
	VAT_IS_APPOINT_ISSU_INVOICEY("Y","是"), //
	VAT_IS_APPOINT_ISSU_INVOICEN("N","否"); //
	
	private String value;
	private String text;

	private VatIsAppointIssuInvoiceEnums(String value,String text) {
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

