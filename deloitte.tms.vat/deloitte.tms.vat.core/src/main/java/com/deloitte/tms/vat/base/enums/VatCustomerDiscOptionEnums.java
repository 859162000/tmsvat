package com.deloitte.tms.vat.base.enums;

import java.io.Serializable;

	/**
	 * 购方证件类型
	 *
	 * @author admin
	 * @create Mar 18, 2016 2:37:07 PM 
	 * @version 1.0.0
	 */
public enum VatCustomerDiscOptionEnums implements Serializable{
	
	VAT_CUSTOMER_DISC_OPTION1("1","纳税人识别号"), //
	VAT_CUSTOMER_DISC_OPTION2("2","统一社会信用代码"), //
	VAT_CUSTOMER_DISC_OPTION3("3","客户号"); //
	
	private String value;
	private String text;

	private VatCustomerDiscOptionEnums(String value,String text) {
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

