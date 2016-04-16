package com.deloitte.tms.base.taxsetting.job;

import java.util.List;

import javax.annotation.Resource;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.job.task.JobTest;
import com.deloitte.tms.vat.salesinvoice.model.TmsCrvatInvReqBatInf;
import com.deloitte.tms.vat.salesinvoice.service.InvoiceSpecialContractBathService;
@Component(value="tmsCrvatInvReqBatchesJob")
public class TmsCrvatInvReqBatchesJob implements Job, JobTest {
	@Resource
	InvoiceSpecialContractBathService invoiceSpecialContractBathServiceImpl;


	@Override
	public void execute() {
		//取得长江证券数据
		List<TmsCrvatInvReqBatInf> list = invoiceSpecialContractBathServiceImpl.analyzeTmsCrvatInvReqBatchesParam();

	}

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		
	}

}
