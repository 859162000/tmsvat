package com.deloitte.tms.base.enums;

import java.io.Serializable;

/**
 * 
 *发票快递单状态
 * @author admin
 * @create Mar 18, 2016 2:37:07 PM 
 * @version 1.0.0
 */
public enum InvoiceExpressStatuEnums implements Serializable{
	
	TOBEDELIVERED("10","tobedelivered"), //待发送
	ONTHEWAY("20","crvatTaxPoolStatus.appform_used"), //在途
	RECEIVED("30","crvatTaxPoolStatus.appform_revoked"), //已签收
	RETURNED("40","crvatTaxPoolStatus.appform_submitted");//已退件
	
	private String value;
	private String text;

	private InvoiceExpressStatuEnums(String value,String text) {
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

