package com.deloitte.tms.pl.workflow.model.task;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.deloitte.tms.pl.core.commons.constant.TablePreDef;

/**
 * @author Jacky.gao
 * @since 2013年8月19日
 */
@Entity
@Table(name=TablePreDef.WORKFLOW+"TASK_APPOINTOR")
public class TaskAppointor {
	@Id
	@Column(name="ID_")
	private long id;

	@Column(name="PROCESS_INSTANCE_ID_")
	private long processInstanceId;
	
	@Column(name="TASK_NODE_NAME_",length=60)
	private String taskNodeName;

	@Column(name="OWNER_",length=60)
	private String owner;
	
	@Column(name="APPOINTOR_",length=60)
	private String appointor;
	
	@Column(name="APPOINTOR_NODE_",length=60)
	private String appointorNode;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getProcessInstanceId() {
		return processInstanceId;
	}
	public void setProcessInstanceId(long processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	public String getTaskNodeName() {
		return taskNodeName;
	}
	public void setTaskNodeName(String taskNodeName) {
		this.taskNodeName = taskNodeName;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getAppointor() {
		return appointor;
	}
	public void setAppointor(String appointor) {
		this.appointor = appointor;
	}
	public String getAppointorNode() {
		return appointorNode;
	}
	public void setAppointorNode(String appointorNode) {
		this.appointorNode = appointorNode;
	}
}
