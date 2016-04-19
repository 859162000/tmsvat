package com.deloitte.tms.pl.jobtask;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.job.task.JobTest;
import com.deloitte.tms.vat.base.enums.CrvaInvoicePreStatusEnums;
import com.deloitte.tms.vat.salesinvoice.model.TmsCrvatInvoicePreH;
import com.deloitte.tms.vat.salesinvoice.service.SpecialCrvatInvoicePreToPrintPoolService;
@Component("specialInvoicePreToPrintPool")
public class SpecialInvoicePreToPrintPool implements Job,JobTest{

	@Resource
	SpecialCrvatInvoicePreToPrintPoolService specialCrvatInvoicePreToPrintPoolService;
	

	@Override
	public void execute() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("approvalStatus", CrvaInvoicePreStatusEnums.APPROVED.getValue());
		List<TmsCrvatInvoicePreH> list =  specialCrvatInvoicePreToPrintPoolService.findInvoicePreHByParamsForSpecial(params);
		for(TmsCrvatInvoicePreH tmsCrvatInvoicePreH:list){	
			tmsCrvatInvoicePreH.setApprovalStatus(CrvaInvoicePreStatusEnums.PREP_FORM_GENERATING.getValue());
			specialCrvatInvoicePreToPrintPoolService.update(tmsCrvatInvoicePreH);
			try {				
				specialCrvatInvoicePreToPrintPoolService.exeConvertCrvatInvoicePreToPrintPoolForSpecial(tmsCrvatInvoicePreH);		
				tmsCrvatInvoicePreH.setApprovalStatus(CrvaInvoicePreStatusEnums.INVOICEGENERATED.getValue());
				specialCrvatInvoicePreToPrintPoolService.update(tmsCrvatInvoicePreH);
			} catch (Exception e) {
				tmsCrvatInvoicePreH.setApprovalStatus(CrvaInvoicePreStatusEnums.PREP_FORM_ERRO.getValue());
				specialCrvatInvoicePreToPrintPoolService.update(tmsCrvatInvoicePreH);
			}
			
			
		}
		
	}

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		execute();
	}

}
