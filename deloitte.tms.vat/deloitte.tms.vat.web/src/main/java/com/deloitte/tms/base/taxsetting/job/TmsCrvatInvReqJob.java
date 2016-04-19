package com.deloitte.tms.base.taxsetting.job;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.job.task.JobTest;
import com.deloitte.tms.vat.salesinvoice.jobs.model.TmsCrvatInvReqHImpl;
import com.deloitte.tms.vat.salesinvoice.jobs.service.TmsCrvatInvReqBatchesJobService;
@Component(value="tmsCrvatInvReqJob")
public class TmsCrvatInvReqJob implements Job, JobTest{
	@Resource
	TmsCrvatInvReqBatchesJobService tmsCrvatInvReqBatchesJobServiceImpl;
	@Override
	public void execute() {
	List<TmsCrvatInvReqHImpl> map =  tmsCrvatInvReqBatchesJobServiceImpl.analyzeTmsCrvatInvReqHInf();  
	}

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		
	}

}
