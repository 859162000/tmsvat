package com.deloitte.tms.pl.core.model.impl;

import java.io.Serializable;
import java.util.Date;

import com.deloitte.tms.pl.core.model.IModelHistory;

/**
 * LingModelhistory entity. @author MyEclipse Persistence Tools
 */

public class LingModelhistory extends BaseEntity implements IModelHistory {

	// Fields

	private Date operatorDate;
	private String operatorMessage;
	private String operatorName;
	private String operatorState;
	private String operatorTable;
	private Long operatorKey;
	protected Long id;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = (Long) id;
	}
	public Serializable getOperatorKey() {
		return operatorKey;
	}

	public void setOperatorKey(Serializable operatorKey) {
		this.operatorKey = (Long) operatorKey;
	}

	/** default constructor */
	public LingModelhistory() {
	}

	/** full constructor */
	public LingModelhistory(Date operatorDate, String operatorMessage,
			String operatorName, String operatorState, String operatorTable) {
		this.operatorDate = operatorDate;
		this.operatorMessage = operatorMessage;
		this.operatorName = operatorName;
		this.operatorState = operatorState;
		this.operatorTable = operatorTable;
	}

	// Property accessors


	public Date getOperatorDate() {
		return this.operatorDate;
	}

	public void setOperatorDate(Date operatorDate) {
		this.operatorDate = operatorDate;
	}

	public String getOperatorMessage() {
		return this.operatorMessage;
	}

	public void setOperatorMessage(String operatorMessage) {
		this.operatorMessage = operatorMessage;
	}

	public String getOperatorName() {
		return this.operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public String getOperatorState() {
		return this.operatorState;
	}

	public void setOperatorState(String operatorState) {
		this.operatorState = operatorState;
	}

	public String getOperatorTable() {
		return this.operatorTable;
	}

	public void setOperatorTable(String operatorTable) {
		this.operatorTable = operatorTable;
	}
	
}