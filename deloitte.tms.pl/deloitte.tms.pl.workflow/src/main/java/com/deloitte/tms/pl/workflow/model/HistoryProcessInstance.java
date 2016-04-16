package com.deloitte.tms.pl.workflow.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.deloitte.tms.pl.core.commons.constant.TablePreDef;

@Entity
@Table(name=TablePreDef.WORKFLOW+"HIS_PROCESS_INSTANCE")
public class HistoryProcessInstance{
	@Id
	@Column(name="ID_")
	private long id;
	
	@Column(name="PROCESS_ID_")
	private long processId;
	
	@Column(name="BUSINESS_ID_",length=60)
	private String businessId;
	
	@Column(name="CREATE_DATE_")
	private Date createDate;
	
	@Column(name="PROCESS_INSTANCE_ID_")
	private long processInstanceId;
	
	@Column(name="END_DATE_")
	private Date endDate;
	
	@Column(name="TAG_",length=30)
	private String tag;
	
	@Column(name="PROMOTER_",length=60)
	private String promoter;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getProcessId() {
		return processId;
	}
	public void setProcessId(long processId) {
		this.processId = processId;
	}
	
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
	public String getBusinessId() {
		return businessId;
	}
	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getPromoter() {
		return promoter;
	}
	public void setPromoter(String promoter) {
		this.promoter = promoter;
	}
}
