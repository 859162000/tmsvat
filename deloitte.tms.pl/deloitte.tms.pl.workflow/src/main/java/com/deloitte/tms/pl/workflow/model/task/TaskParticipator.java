package com.deloitte.tms.pl.workflow.model.task;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.deloitte.tms.pl.core.commons.constant.TablePreDef;

@Entity
@Table(name=TablePreDef.WORKFLOW+"TASK_PARTICIPATOR")
public class TaskParticipator {
	@Id
	@Column(name="ID_")
	private long id;
	
	@Column(name="TASK_ID_",nullable=false)
	private long taskId;
		
	@Column(name="USER_",length=60)
	private String user;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getTaskId() {
		return taskId;
	}

	public void setTaskId(long taskId) {
		this.taskId = taskId;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
}
