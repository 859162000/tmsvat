package com.deloitte.tms.pl.autoproject.model;

import com.deloitte.tms.pl.core.commons.annotation.AttachMentDropdown;
import com.deloitte.tms.pl.core.commons.annotation.Dropdown;
import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.core.commons.utils.UnicodeUtils;

public class DefaultPojoField implements PojoField{
	String name;
	String comment;
	String des;
	Boolean isOneToOne;
	Boolean isOneToMany;
	Boolean isSampleField=false;
	String dataType;
	String htmlType;
	Boolean isNotBaseEntiry=true;
	Dropdown dropdown;
	AttachMentDropdown attachMentDropdown;
	Boolean isrequired;
	String dictCode;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Boolean getIsOneToOne() {
		return isOneToOne;
	}
	public void setIsOneToOne(Boolean isOneToOne) {
		this.isOneToOne = isOneToOne;
	}
	public Boolean getIsOneToMany() {
		return isOneToMany;
	}
	public void setIsOneToMany(Boolean isOneToMany) {
		this.isOneToMany = isOneToMany;
	}
	public Boolean getIsSampleField() {
		return isSampleField;
	}
	public void setIsSampleField(Boolean isSampleField) {
		this.isSampleField = isSampleField;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String toString()
	{
		return name;
	}
	public Boolean getIsNotBaseEntiry() {
		return isNotBaseEntiry;
	}
	public void setIsNotBaseEntiry(Boolean isNotBaseEntiry) {
		this.isNotBaseEntiry = isNotBaseEntiry;
	}
	public Dropdown getDropdown() {
		return dropdown;
	}
	public void setDropdown(Dropdown dropdown) {
		this.dropdown = dropdown;
	}
	public AttachMentDropdown getAttachMentDropdown() {
		return attachMentDropdown;
	}
	public void setAttachMentDropdown(AttachMentDropdown attachMentDropdown) {
		this.attachMentDropdown = attachMentDropdown;
	}
	public Boolean getIsrequired() {
		return isrequired;
	}
	public void setIsrequired(Boolean isrequired) {
		this.isrequired = isrequired;
	}
	public String getDictCode() {
		return dictCode;
	}
	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}
	public String getASCIIComment(){
		if(AssertHelper.notEmpty(comment)){
			return UnicodeUtils.chinaToUnicode(comment);
		}
		return null;
	}
	public String getHtmlType() {
		return htmlType;
	}
	public void setHtmlType(String htmlType) {
		this.htmlType = htmlType;
	}
	
}
