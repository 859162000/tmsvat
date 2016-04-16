package com.deloitte.tms.pl.workflow.process.node;

/**
 * @author Jacky.gao
 * @since 2013年11月28日
 */
public class UserData {
	private String key;
	private String value;
	
	public UserData(String key, String value) {
		super();
		this.key = key;
		this.value = value;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
