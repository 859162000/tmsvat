package com.deloitte.tms.pl.job.listener;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

import com.deloitte.tms.pl.core.commons.utils.SpringUtil;
import com.deloitte.tms.pl.job.service.IJobService;

/**
 * @author Jacky.gao
 * @since 2013-3-10
 */
public class JobExecutionHistoryListener implements JobListener{
	
	private Date start;
	public String getName() {
		return "JobExecutionHistoryListener";
	}
	public void jobToBeExecuted(JobExecutionContext context) {
		this.start=new Date();
	}
	public void jobWasExecuted(JobExecutionContext context,
			JobExecutionException jobException) {
		IJobService jobService=SpringUtil.getBean(IJobService.BEAN_ID);
		String exception=getExceptionStackMessage(jobException);
		jobService.executeJobHistory(context, exception, start);
	}
	private String getExceptionStackMessage(JobExecutionException jobException){
		if(jobException!=null){
			StringWriter sw = new StringWriter();  
			PrintWriter pw = new PrintWriter(sw);
			jobException.printStackTrace(pw);
			return sw.toString();
		}else{
			return null;
		}
	}
	public void jobExecutionVetoed(JobExecutionContext context) {
		
	}
}
