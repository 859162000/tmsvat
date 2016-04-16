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
import com.deloitte.tms.vat.base.enums.AppFormStatuEnums;
import com.deloitte.tms.vat.salesinvoice.model.InvoiceReqH;
import com.deloitte.tms.vat.salesinvoice.service.CrvatInvoiceReToPreService;
@Component("invoiceResToPre")
public class InvoiceResToPre implements Job,JobTest{
	
	@Resource
	CrvatInvoiceReToPreService crvatInvoiceReToPreService;


	@Override
	public void execute() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("status", AppFormStatuEnums.SUBMITTED.getValue());
		List<InvoiceReqH> list =  crvatInvoiceReToPreService.findInvoiceReqHByParams(params);
		
		/*for(InvoiceReqH invoiceReqH:list){
			invoiceReqH.setStatus(AppFormStatuEnums.PREP_FORM_GENERATING.getValue());
			crvatInvoiceReToPreService.update(invoiceReqH);
		}*/
		for(InvoiceReqH invoiceReqH:list){	
			invoiceReqH.setStatus(AppFormStatuEnums.PREP_FORM_GENERATING.getValue());
			crvatInvoiceReToPreService.update(invoiceReqH);
			
			try {				
				int i =crvatInvoiceReToPreService.exeConvertCrvatInvoiceReToPre(invoiceReqH, new HashMap());
				invoiceReqH.setQtyOfPreInvoice(i);
				invoiceReqH.setStatus(AppFormStatuEnums.PREP_FORM_DONE.getValue());
				crvatInvoiceReToPreService.update(invoiceReqH);
			} catch (Exception e) {
				e.printStackTrace();
				/*invoiceReqH.setStatus(AppFormStatuEnums.PREP_FORM_ERRO.getValue());
				crvatInvoiceReToPreService.update(invoiceReqH);*/
				
				//申请单出现异常时，回滚当前操作的申请单,并且释放当前占用的交易池数据
				crvatInvoiceReToPreService.exeRevertInvoiceReq(invoiceReqH);
			}	
			
		}
			
		
	}				
		
	


	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		execute();
	}
}



