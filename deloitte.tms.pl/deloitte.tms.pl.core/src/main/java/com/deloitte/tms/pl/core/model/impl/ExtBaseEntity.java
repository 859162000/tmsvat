package com.deloitte.tms.pl.core.model.impl;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.deloitte.tms.pl.core.commons.annotation.ModelProperty;

@MappedSuperclass
public abstract class ExtBaseEntity implements java.io.Serializable{

	@Column(name = "ATTRIBUTE1",  length = 240)
	@ModelProperty(comment="ATTRIBUTE1")
	String attribute1;
	
	@Column(name = "ATTRIBUTE2",  length = 240)
	@ModelProperty(comment="ATTRIBUTE2")
	String attribute2;
	
	@Column(name = "ATTRIBUTE3",  length = 240)
	@ModelProperty(comment="ATTRIBUTE3")
	String attribute3;
	
	@Column(name = "ATTRIBUTE4",  length = 240)
	@ModelProperty(comment="ATTRIBUTE4")
	String attribute4;
	
	@Column(name = "ATTRIBUTE5",  length = 240)
	@ModelProperty(comment="ATTRIBUTE5")
	String attribute5;
	
	@Column(name="ATTRIBUTE_CATEGORY",length=36)
	@ModelProperty(comment="扩展字段结构类型")
	String attributeCategory;
	

	public String getAttributeCategory() {
		return attributeCategory;
	}

	public void setAttributeCategory(String attributeCategory) {
		this.attributeCategory = attributeCategory;
	}

	public String getAttribute1() {
		return attribute1;
	}

	public void setAttribute1(String attribute1) {
		this.attribute1 = attribute1;
	}

	public String getAttribute2() {
		return attribute2;
	}

	public void setAttribute2(String attribute2) {
		this.attribute2 = attribute2;
	}

	public String getAttribute3() {
		return attribute3;
	}

	public void setAttribute3(String attribute3) {
		this.attribute3 = attribute3;
	}

	public String getAttribute4() {
		return attribute4;
	}

	public void setAttribute4(String attribute4) {
		this.attribute4 = attribute4;
	}

	public String getAttribute5() {
		return attribute5;
	}

	public void setAttribute5(String attribute5) {
		this.attribute5 = attribute5;
	}
	
	
}