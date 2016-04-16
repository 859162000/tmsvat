package com.deloitte.tms.pl.workflow.process.node.reminder;

public class Reminder implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private String handlerBean;

	public String getHandlerBean() {
		return handlerBean;
	}

	public void setHandlerBean(String handlerBean) {
		this.handlerBean = handlerBean;
	}
}
