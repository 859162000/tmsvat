package com.deloitte.tms.pl.job.model;

import org.quartz.impl.JobDetailImpl;

import com.deloitte.tms.pl.job.service.IJobDefinitionService;
import com.deloitte.tms.pl.job.service.IJobService;

/**
 * @author Jacky.gao
 * @since 2013-3-12
 */
public class ScanJobExecutorDetail extends JobDetailImpl {
	private static final long serialVersionUID = 5235476854106861377L;
	private IJobDefinitionService jobDefinitionService;
	private IJobService jobService;
	
	public ScanJobExecutorDetail(IJobDefinitionService jobDefinitionService,IJobService jobService) {
		this.jobDefinitionService = jobDefinitionService;
		this.jobService = jobService;
	}
	public IJobDefinitionService getJobDefinitionService() {
		return jobDefinitionService;
	}
	public void setJobDefinitionService(IJobDefinitionService jobDefinitionService) {
		this.jobDefinitionService = jobDefinitionService;
	}
	public IJobService getJobService() {
		return jobService;
	}
	public void setJobService(IJobService jobService) {
		this.jobService = jobService;
	}
}
