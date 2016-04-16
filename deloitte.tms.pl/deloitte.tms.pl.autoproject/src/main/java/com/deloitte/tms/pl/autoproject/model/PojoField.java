package com.deloitte.tms.pl.autoproject.model;

import com.deloitte.tms.pl.core.commons.annotation.Dropdown;

public interface PojoField {
	public String getName();
	public String getComment();
	public Boolean getIsOneToOne();
	public Boolean getIsOneToMany();
	public Boolean getIsSampleField();
	public String getDes();
	public String getDataType();
	public Boolean getIsNotBaseEntiry();	
	public Dropdown getDropdown();	
	public void setDropdown(Dropdown dropdown);	
	public Boolean getIsrequired();	
	public String getDictCode();
	public String getHtmlType();
}
