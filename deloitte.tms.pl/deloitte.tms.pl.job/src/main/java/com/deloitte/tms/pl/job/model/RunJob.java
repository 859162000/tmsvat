package com.deloitte.tms.pl.job.model;

import org.quartz.impl.JobDetailImpl;


/**
 * @author Jacky.gao
 * @since 2013-3-29
 */
public class RunJob {
	private JobDefinition jobDefinition;
	private JobDetailImpl jobDetail;
	public JobDefinition getJobDefinition() {
		return jobDefinition;
	}
	public void setJobDefinition(JobDefinition jobDefinition) {
		this.jobDefinition = jobDefinition;
	}
	public JobDetailImpl getJobDetail() {
		return jobDetail;
	}
	public void setJobDetail(JobDetailImpl jobDetail) {
		this.jobDetail = jobDetail;
	}	
}
