package com.deloitte.tms.pl.autoproject.model.tabledes;

import java.util.List;

public class TableDes {
	String name;
	String des;
	String key;
	List<TableField> fields;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public List<TableField> getFields() {
		return fields;
	}
	public void setFields(List<TableField> fields) {
		this.fields = fields;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	
	
}
