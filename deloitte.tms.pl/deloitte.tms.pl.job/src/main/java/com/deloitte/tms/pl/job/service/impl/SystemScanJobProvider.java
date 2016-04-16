package com.deloitte.tms.pl.job.service.impl;

import java.text.ParseException;

import javax.annotation.Resource;

import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Trigger;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.job.executor.ScanJobExecutor;
import com.deloitte.tms.pl.job.model.ScanJobExecutorDetail;
import com.deloitte.tms.pl.job.service.IJobDefinitionService;
import com.deloitte.tms.pl.job.service.IJobService;
import com.deloitte.tms.pl.job.service.ISystemJobProvider;

/**
 * @author Jacky.gao
 * @since 2013-3-29
 */
@Component
public class SystemScanJobProvider  implements ISystemJobProvider {
	@Value("${ling2.scanJobCronExpression}")
	private String scanJobCronExpression;
	@Resource
	private IJobService jobService;
	@Resource
	private IJobDefinitionService jobDefinitionService;
	public JobDetail getJobDetail() {
		ScanJobExecutorDetail jobDetail=new ScanJobExecutorDetail(jobDefinitionService,jobService);
		jobDetail.setKey(new JobKey(ScanJobExecutor.JOB_ID,GROUP_ID));
		jobDetail.setJobClass(ScanJobExecutor.class);
		return jobDetail;
	}
	public Trigger getTrigger() {
		CronTriggerImpl trigger=new CronTriggerImpl();
		trigger.setName("trigger"+ScanJobExecutor.JOB_ID);
		try {
			trigger.setCronExpression(scanJobCronExpression);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		return trigger;
	}
	public String getScanJobCronExpression() {
		return scanJobCronExpression;
	}
	public void setScanJobCronExpression(String scanJobCronExpression) {
		this.scanJobCronExpression = scanJobCronExpression;
	}
	public IJobService getJobService() {
		return jobService;
	}
	public void setJobService(IJobService jobService) {
		this.jobService = jobService;
	}
	public IJobDefinitionService getJobDefinitionService() {
		return jobDefinitionService;
	}
	public void setJobDefinitionService(IJobDefinitionService jobDefinitionService) {
		this.jobDefinitionService = jobDefinitionService;
	}
}
