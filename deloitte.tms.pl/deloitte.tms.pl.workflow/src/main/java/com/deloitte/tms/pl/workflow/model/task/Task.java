package com.deloitte.tms.pl.workflow.model.task;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.deloitte.tms.pl.workflow.model.Activity;
import com.deloitte.tms.pl.core.commons.constant.TablePreDef;

@XmlRootElement(name="Task")
@Entity
@Table(name=TablePreDef.WORKFLOW+"TASK")
public class Task extends Activity{
	@Column(name="TASK_NAME_",length=60)
	private String taskName;
	
	@Column(name="ASSIGNEE_",length=60)
	private String assignee;
	
	@Column(name="OWNER_",length=60)
	private String owner;
	
	@Column(name="PROGRESS_")
	private Integer progress;
	
	@Enumerated(EnumType.STRING)
	@Column(name="STATE_",length=20)
	private TaskState state;
	
	@Enumerated(EnumType.STRING)
	@Column(name="PREV_STATE_",length=20)
	private TaskState prevState;
	
	@Column(name="PROCESS_INSTANCE_ID_")
	private long processInstanceId;
	
	@Column(name="ROOT_PROCESS_INSTANCE_ID_")
	private long rootProcessInstanceId;
	
	@Column(name="CREATE_DATE_")
	private Date createDate;
	
	@Column(name="PREV_TASK_",length=60)
	private String prevTask;
	
	@Column(name="OPINION_",length=200)
	private String opinion;
	
	@Column(name="URL_",length=120)
	private String url;

	@Column(name="TYPE_",length=20)
	@Enumerated(EnumType.STRING)
	private TaskType type;
	
	@Column(name="COUNTERSIGN_COUNT_")
	private int countersignCount;
	
	@Column(name="DUEDATE_")
	private Date duedate;
	
	@Column(name="DATE_UNIT_",length=20)
	@Enumerated(EnumType.STRING)
	private DateUnit dateUnit;
	
	@Column(name="BUSINESS_ID_",length=60)
	private String businessId;
	
	@XmlTransient
	@OneToMany(cascade=CascadeType.DETACH,fetch=FetchType.LAZY)
	@JoinColumn(name="TASK_ID_")
	private Collection<TaskParticipator> taskParticipators;

	public long getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(long processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
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

	public TaskState getState() {
		return state;
	}

	public void setState(TaskState state) {
		this.state = state;
	}

	public TaskState getPrevState() {
		return prevState;
	}

	public void setPrevState(TaskState prevState) {
		this.prevState = prevState;
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

	public int getCountersignCount() {
		return countersignCount;
	}

	public void setCountersignCount(int countersignCount) {
		this.countersignCount = countersignCount;
	}

	public Collection<TaskParticipator> getTaskParticipators() {
		return taskParticipators;
	}

	public void setTaskParticipators(Collection<TaskParticipator> taskParticipators) {
		this.taskParticipators = taskParticipators;
	}

	public TaskType getType() {
		return type;
	}

	public void setType(TaskType type) {
		this.type = type;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public Date getDuedate() {
		return duedate;
	}

	public void setDuedate(Date duedate) {
		this.duedate = duedate;
	}

	public DateUnit getDateUnit() {
		return dateUnit;
	}

	public void setDateUnit(DateUnit dateUnit) {
		this.dateUnit = dateUnit;
	}

	public long getRootProcessInstanceId() {
		return rootProcessInstanceId;
	}

	public void setRootProcessInstanceId(long rootProcessInstanceId) {
		this.rootProcessInstanceId = rootProcessInstanceId;
	}

	public Integer getProgress() {
		return progress;
	}

	public void setProgress(Integer progress) {
		this.progress = progress;
	}
}
