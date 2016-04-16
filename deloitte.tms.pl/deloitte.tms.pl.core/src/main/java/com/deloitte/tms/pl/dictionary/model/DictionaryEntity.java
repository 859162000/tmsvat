package com.deloitte.tms.pl.dictionary.model;

public class DictionaryEntity implements java.io.Serializable {
	String name;
	String code;
	String categoryCode;
	
	public String getCodeName() {
		return name;
	}
	public String getCodeValue() {
		return code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	
}
