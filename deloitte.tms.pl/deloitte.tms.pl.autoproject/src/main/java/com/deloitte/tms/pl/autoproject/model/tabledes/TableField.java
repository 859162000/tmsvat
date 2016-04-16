package com.deloitte.tms.pl.autoproject.model.tabledes;

public class TableField {

	String name;
	String colnum;
	String type;
	String length;
	Boolean isnull;
	String des;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getLength() {
		return length;
	}
	public void setLength(String length) {
		this.length = length;
	}
	public Boolean getIsnull() {
		return isnull;
	}
	public void setIsnull(Boolean isnull) {
		this.isnull = isnull;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public String getField(){
		return name.toLowerCase();
	}
	public String getColnum() {
		return colnum;
	}
	public void setColnum(String colnum) {
		this.colnum = colnum;
	}
	
}
