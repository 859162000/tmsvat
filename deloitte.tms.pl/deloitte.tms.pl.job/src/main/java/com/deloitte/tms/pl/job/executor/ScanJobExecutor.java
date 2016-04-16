package com.deloitte.tms.pl.job.executor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.deloitte.tms.pl.core.commons.utils.SpringUtil;
import com.deloitte.tms.pl.job.service.IJobService;

/**
 * @author Jacky.gao
 * @since 2013-3-12
 */
/**
 * @author Jacky.gao
 * @since 2013-3-12
 */
public class ScanJobExecutor implements Job {
	protected final Log logger = LogFactory.getLog(getClass());
	public static final String JOB_ID="background_scan_job_definition";
	public void execute(JobExecutionContext context) throws JobExecutionException {
		try{
			IJobService jobService=SpringUtil.getBean(IJobService.BEAN_ID);
			jobService.executeScanJobExecutor(context);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

}
