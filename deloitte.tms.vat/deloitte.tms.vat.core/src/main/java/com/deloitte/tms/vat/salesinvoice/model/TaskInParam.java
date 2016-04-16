package com.deloitte.tms.vat.salesinvoice.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.deloitte.tms.pl.workflow.model.task.DateUnit;
import com.deloitte.tms.pl.workflow.model.task.TaskState;
import com.deloitte.tms.pl.workflow.model.task.TaskType;

public class TaskInParam {
	
	private String id;
	
	private String taskName;
	

	private String assignee;
	

	private String owner;
	

	private Integer progress;
	

	private String state;
	

	private String prevState;
	

	private long processInstanceId;
	

	private long rootProcessInstanceId;
	

	private Date createDate;
	

	private String prevTask;
	

	private String opinion;
	

	private String url;


	private String type;
	

	private int countersignCount;
	

	private Date duedate;
	

	private String dateUnit;
	

	private String businessId;


	public String getTaskName() {
		return taskName;
	}


	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}


	public String getAssignee() {
		return assignee;
	}


	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}


	public String getOwner() {
		return owner;
	}


	public void setOwner(String owner) {
		this.owner = owner;
	}


	public Integer getProgress() {
		return progress;
	}


	public void setProgress(Integer progress) {
		this.progress = progress;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getPrevState() {
		return prevState;
	}


	public void setPrevState(String prevState) {
		this.prevState = prevState;
	}


	public long getProcessInstanceId() {
		return processInstanceId;
	}


	public void setProcessInstanceId(long processInstanceId) {
		this.processInstanceId = processInstanceId;
	}


	public long getRootProcessInstanceId() {
		return rootProcessInstanceId;
	}


	public void setRootProcessInstanceId(long rootProcessInstanceId) {
		this.rootProcessInstanceId = rootProcessInstanceId;
	}


	public Date getCreateDate() {
		return createDate;
	}


	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}


	public String getPrevTask() {
		return prevTask;
	}


	public void setPrevTask(String prevTask) {
		this.prevTask = prevTask;
	}


	public String getOpinion() {
		return opinion;
	}


	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public int getCountersignCount() {
		return countersignCount;
	}


	public void setCountersignCount(int countersignCount) {
		this.countersignCount = countersignCount;
	}


	public Date getDuedate() {
		return duedate;
	}


	public void setDuedate(Date duedate) {
		this.duedate = duedate;
	}


	public String getDateUnit() {
		return dateUnit;
	}


	public void setDateUnit(String dateUnit) {
		this.dateUnit = dateUnit;
	}


	public String getBusinessId() {
		return businessId;
	}


	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}
	
	
	
	

}
