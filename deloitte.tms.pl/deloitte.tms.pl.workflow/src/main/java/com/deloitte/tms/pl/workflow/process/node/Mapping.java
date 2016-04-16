package com.deloitte.tms.pl.workflow.process.node;

/**
 * @author Jacky
 * @date 2013年9月24日
 */
public class Mapping implements java.io.Serializable{
	private static final long serialVersionUID = -1347697162337032126L;
	private String key;
	private String label;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
}
