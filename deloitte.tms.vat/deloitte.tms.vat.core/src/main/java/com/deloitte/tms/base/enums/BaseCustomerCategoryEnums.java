package com.deloitte.tms.base.enums;

import java.io.Serializable;

/**
 * 
 *发票快递单状态
 * @author admin
 * @create Mar 18, 2016 2:37:07 PM 
 * @version 1.0.0
 */
public enum BaseCustomerCategoryEnums implements Serializable{
	
	BASE_CUSTOMER_CATEGORY1("1","个人"), 
	BASE_CUSTOMER_CATEGORY2("2","公司"), 
	BASE_CUSTOMER_CATEGORY3("3","同业");
	
	private String value;
	private String text;

	private BaseCustomerCategoryEnums(String value,String text) {
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

