package com.deloitte.tms.pl.job.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.deloitte.tms.pl.core.commons.constant.TablePreDef;

@Entity
@Table(name = TablePreDef.BASE_TASK_PRE+"JOB_CALENDAR_RELATION")
public class JobCalendarRelation  implements java.io.Serializable{
	private static final long serialVersionUID = -7527015309794277429L;

	@Id
	@Column(name="ID_",length=60)
	private String id;
	
	@Column(name="CALENDAR_ID_",length=60)
	private String calendarId;
	
	@Column(name="JOB_ID_",length=60)
	private String jobId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCalendarId() {
		return calendarId;
	}

	public void setCalendarId(String calendarId) {
		this.calendarId = calendarId;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
}
