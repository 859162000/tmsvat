package com.deloitte.tms.pl.jobtask;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.job.task.JobTest;
import com.deloitte.tms.vat.base.enums.AppFormStatuEnums;
import com.deloitte.tms.vat.base.enums.CrvaInvoicePreStatusEnums;
import com.deloitte.tms.vat.salesinvoice.model.InvoiceReqH;
import com.deloitte.tms.vat.salesinvoice.model.TmsCrvatInvoicePreH;
import com.deloitte.tms.vat.salesinvoice.service.CrvatInvoicePreToPrintPoolService;
import com.deloitte.tms.vat.salesinvoice.service.CrvatInvoiceReToPreService;
@Component("invoicePreToPrintPool")
public class InvoicePreToPrintPool implements Job,JobTest{

	@Resource
	CrvatInvoicePreToPrintPoolService crvatInvoicePreToPrintPoolService;
	

	@Override
	public void execute() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("approvalStatus", CrvaInvoicePreStatusEnums.APPROVED.getValue());
		List<TmsCrvatInvoicePreH> list =  crvatInvoicePreToPrintPoolService.findInvoicePreHByParams(params);
		for(TmsCrvatInvoicePreH tmsCrvatInvoicePreH:list){
			tmsCrvatInvoicePreH.setApprovalStatus(CrvaInvoicePreStatusEnums.PREP_FORM_GENERATING.getValue());
			crvatInvoicePreToPrintPoolService.update(tmsCrvatInvoicePreH);
		}
		for(TmsCrvatInvoicePreH tmsCrvatInvoicePreH:list){				
			try {				
				crvatInvoicePreToPrintPoolService.exeConvertCrvatInvoicePreToPrintPool(tmsCrvatInvoicePreH);		
				tmsCrvatInvoicePreH.setApprovalStatus(CrvaInvoicePreStatusEnums.INVOICEGENERATED.getValue());
				crvatInvoicePreToPrintPoolService.update(tmsCrvatInvoicePreH);
			} catch (Exception e) {
				tmsCrvatInvoicePreH.setApprovalStatus(CrvaInvoicePreStatusEnums.PREP_FORM_ERRO.getValue());
				crvatInvoicePreToPrintPoolService.update(tmsCrvatInvoicePreH);
			}
			
			
		}
		
	}

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		execute();
	}

}
