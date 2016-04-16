package com.deloitte.tms.pl.workflow.service.impl.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.deloitte.tms.pl.workflow.process.handler.ReminderHandler;

/**
 * @author Jacky.gao
 * @since 2013年8月21日
 */
public class ReminderJob implements Job {
	public void execute(JobExecutionContext context) throws JobExecutionException {
		ReminderJobDetail jobDetail=(ReminderJobDetail)context.getJobDetail();
		ReminderHandler handler=jobDetail.getReminderHandlerBean();
		handler.execute(jobDetail.getProcessInstance(), jobDetail.getTask());
	}
}
