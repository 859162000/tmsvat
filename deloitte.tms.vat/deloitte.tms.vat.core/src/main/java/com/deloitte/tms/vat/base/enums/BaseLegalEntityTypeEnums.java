package com.deloitte.tms.vat.base.enums;

import java.io.Serializable;

	/**
	 * 
	 *纳税人（主体）类型
	 * @author admin
	 * @create Mar 18, 2016 2:37:07 PM 
	 * @version 1.0.0
	 */
public enum BaseLegalEntityTypeEnums implements Serializable{
	
	BASE_LEGAL_ENTITY_TYPE1("1","一般增值税纳税人"), //
	BASE_LEGAL_ENTITY_TYPE2("2","小规模增值税纳税人"), //
	BASE_LEGAL_ENTITY_TYPE3("3","非增值税纳税人"); //
	
	private String value;
	private String text;

	private BaseLegalEntityTypeEnums(String value,String text) {
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

