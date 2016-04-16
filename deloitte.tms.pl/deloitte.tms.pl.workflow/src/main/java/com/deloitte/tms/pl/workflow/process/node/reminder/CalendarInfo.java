package com.deloitte.tms.pl.workflow.process.node.reminder;

/**
 * @author Jacky.gao
 * @since 2013年8月21日
 */
public class CalendarInfo implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
