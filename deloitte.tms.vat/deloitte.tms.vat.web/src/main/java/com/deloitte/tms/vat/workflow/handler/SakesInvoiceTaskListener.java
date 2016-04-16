package com.deloitte.tms.vat.workflow.handler;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;



import com.deloitte.tms.pl.core.context.utils.ContextUtils;
import com.deloitte.tms.pl.workflow.env.Context;
import com.deloitte.tms.pl.workflow.model.ProcessInstance;
import com.deloitte.tms.pl.workflow.model.task.Task;
import com.deloitte.tms.pl.workflow.process.listener.TaskListener;
import com.deloitte.tms.pl.workflow.process.node.TaskNode;
import com.deloitte.tms.vat.base.enums.CrvaInvoicePreStatusEnums;
import com.deloitte.tms.vat.salesinvoice.model.TmsCrvatInvoicePreH;
import com.deloitte.tms.vat.salesinvoice.service.InvoicePreService;

@Component("sakesInvoiceTaskListener")
public class SakesInvoiceTaskListener implements TaskListener {

	@Resource
	InvoicePreService invoicePreService;
	@Override
	public boolean beforeTaskCreate(Context context,
			ProcessInstance processInstance, TaskNode node) {
		return false;
	}

	@Override
	public void onTaskCreate(Context context, Task task) {
		
	}

	@Override
	public void onTaskComplete(Context context, Task task) {		
		TmsCrvatInvoicePreH tmsCrvatInvoicePreH = (TmsCrvatInvoicePreH) invoicePreService.get(TmsCrvatInvoicePreH.class, task.getBusinessId());			
		tmsCrvatInvoicePreH.setApprovalStatus(CrvaInvoicePreStatusEnums.APPROVED.getValue());   
		tmsCrvatInvoicePreH.setOrgId(ContextUtils.getCurrentOrgId());
		tmsCrvatInvoicePreH.setApprovalBy(ContextUtils.getCurrentUserName());
		invoicePreService.setAcceptStatus(tmsCrvatInvoicePreH);
	}

}
