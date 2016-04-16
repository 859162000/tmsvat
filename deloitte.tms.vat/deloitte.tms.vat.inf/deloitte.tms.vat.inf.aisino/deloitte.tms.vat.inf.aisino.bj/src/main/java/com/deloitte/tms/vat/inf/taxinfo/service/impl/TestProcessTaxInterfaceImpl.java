package com.deloitte.tms.vat.inf.taxinfo.service.impl;

import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.vat.inf.taxinfo.model.invoiceprint.issue.InvoiceIssueRequest;
import com.deloitte.tms.vat.inf.taxinfo.model.invoiceprint.issue.InvoiceIssueResponse;
import com.deloitte.tms.vat.inf.taxinfo.model.invoiceprint.issue.InvoiceIssueResult;
import com.deloitte.tms.vat.inf.taxinfo.model.invoiceprint.print.InvoicePrintRequest;
import com.deloitte.tms.vat.inf.taxinfo.model.invoiceprint.print.InvoicePrintResult;
import com.deloitte.tms.vat.inf.taxinfo.model.invoiceprint.print.PrintInvoiceResponse;
import com.deloitte.tms.vat.inf.taxinfo.model.invoiceserver.distributeInvoice.DistributeInvoiceRequest;
import com.deloitte.tms.vat.inf.taxinfo.model.invoiceserver.distributeInvoice.DistributeInvoiceResponse;
import com.deloitte.tms.vat.inf.taxinfo.service.ProcessTaxInterface;

@Component(value=ProcessTaxInterface.TEST_BEAN_ID)
public class TestProcessTaxInterfaceImpl implements ProcessTaxInterface{

	@Override
	public void processIssueInvoiceResponse(InvoiceIssueRequest request,InvoiceIssueResponse response) {
		AssertHelper.notEmpty_assert(response, "返回数据不能为空");
		for(InvoiceIssueResult result:response.getResults()){
			if(result.isIssucess()){//开具成功
				
			}else{//开具失败,异步处理,不抛出异常
				
			}
		}
	}

	@Override
	public void processPrintInvoiceResponse(InvoicePrintRequest request,PrintInvoiceResponse response) {
		AssertHelper.notEmpty_assert(response, "返回数据不能为空");
		for(InvoicePrintResult result:response.getResults()){
			if(result.isIssucess()){//打印成功
				
			}else{//打印失败,异步处理,不抛出异常
				
			}
		}
	}

	@Override
	public void processDistributeInvoiceResponse(DistributeInvoiceRequest request,
			DistributeInvoiceResponse response) {
		AssertHelper.notEmpty_assert(response, "返回数据不能为空");
		AssertHelper.notEmpty_assert(response.getErrorinfo(), "返回数据不能为空");
		if(response.getErrorinfo().isIssucess()){//成功
			
		}else{//失败
			
		}
	}

}
