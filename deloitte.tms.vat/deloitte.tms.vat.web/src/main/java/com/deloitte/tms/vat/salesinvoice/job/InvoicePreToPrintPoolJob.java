package com.deloitte.tms.vat.salesinvoice.job;

import javax.annotation.Resource;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.job.task.JobTest;
import com.deloitte.tms.vat.salesinvoice.service.TmsCrvatInvoicePreHService;
//@Component("invoicePreToPrintPool")
public class InvoicePreToPrintPoolJob implements Job,JobTest{
	
	@Resource
	TmsCrvatInvoicePreHService tmsCrvatInvoicePreHService;

	@Override
	public void execute() {
		tmsCrvatInvoicePreHService.exeTmsCrvatInvoicePreJobToInvoice();
	}				
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		execute();
	}
}



