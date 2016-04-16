/**
 * 
 */
package com.deloitte.tms.vat.base.enums;

/**
 * 开票方式_枚举值(明细/汇总)
 * @author jasonjyang
 * 
 */
public enum InvoicingTypeEnums {
	SUMMARY("S","Summary--汇总"), //S=汇总
	DETAIL("D","Detail--明细");//D=明细
	private InvoicingTypeEnums(String value,String text) {
		this.value = value;
		this.text = text;
	}
	private String value;
	private String text;
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
