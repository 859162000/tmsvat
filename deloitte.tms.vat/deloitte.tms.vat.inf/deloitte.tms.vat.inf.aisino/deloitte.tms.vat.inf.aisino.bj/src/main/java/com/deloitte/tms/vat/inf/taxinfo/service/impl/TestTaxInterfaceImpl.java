package com.deloitte.tms.vat.inf.taxinfo.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.flow.utils.FlowHelper;
import com.deloitte.tms.vat.inf.taxinfo.aisino.constant.AisinoResponseCodeDef;
import com.deloitte.tms.vat.inf.taxinfo.model.invoiceprint.issue.InvoiceIssue;
import com.deloitte.tms.vat.inf.taxinfo.model.invoiceprint.issue.InvoiceIssueDetail;
import com.deloitte.tms.vat.inf.taxinfo.model.invoiceprint.issue.InvoiceIssueRequest;
import com.deloitte.tms.vat.inf.taxinfo.model.invoiceprint.issue.InvoiceIssueResponse;
import com.deloitte.tms.vat.inf.taxinfo.model.invoiceprint.issue.InvoiceIssueResult;
import com.deloitte.tms.vat.inf.taxinfo.model.invoiceprint.print.InvoicePrint;
import com.deloitte.tms.vat.inf.taxinfo.model.invoiceprint.print.InvoicePrintRequest;
import com.deloitte.tms.vat.inf.taxinfo.model.invoiceprint.print.InvoicePrintResult;
import com.deloitte.tms.vat.inf.taxinfo.model.invoiceprint.print.PrintInvoiceResponse;
import com.deloitte.tms.vat.inf.taxinfo.model.invoiceserver.distributeInvoice.DistributeInvoiceRequest;
import com.deloitte.tms.vat.inf.taxinfo.model.invoiceserver.distributeInvoice.DistributeInvoiceResponse;
import com.deloitte.tms.vat.inf.taxinfo.service.ProcessTaxInterface;
import com.deloitte.tms.vat.inf.taxinfo.service.TaxInterface;

@Component(TaxInterface.TEST_BEAN_ID)
public class TestTaxInterfaceImpl implements TaxInterface{
	
	@Resource(name=ProcessTaxInterface.TEST_BEAN_ID)
	ProcessTaxInterface processTaxInterface;
	
	
	public static String testInvoiceCode="testInvoiceCode";
	public static String testInvoiceNo="testInvoiceNo";
	
	@Override
	public InvoiceIssueResponse processIssueInvoice(InvoiceIssueRequest request) {
		AssertHelper.notEmpty_assert(request, "请求数据不能为空");
		request.check();
		InvoiceIssueResponse response=new InvoiceIssueResponse();
		for(InvoiceIssue issue:request.getRecord()){
			InvoiceIssueResult result=new InvoiceIssueResult();
			result.setErroCode(AisinoResponseCodeDef.PRINT_SITE_SUCESS);
			Double totalAmout=0.0;
			for(InvoiceIssueDetail detail:issue.getDetails()){
				totalAmout=totalAmout+detail.getTaxAmount();
			}
			result.setInvoiceAmount(totalAmout);
			result.setInvoiceCode(FlowHelper.getNextFlowNo(testInvoiceCode));
			result.setInvoiceNo(FlowHelper.getNextFlowNo(testInvoiceNo));
			response.addResult(result);			
		}		
		processTaxInterface.processIssueInvoiceResponse(request,response);
		return response;
	}

	@Override
	public PrintInvoiceResponse processPrintInvoice(InvoicePrintRequest request) {
		AssertHelper.notEmpty_assert(request, "打印内容不能为空");
		request.check();
		PrintInvoiceResponse response=new PrintInvoiceResponse();
		for(InvoicePrint print:request.getRecords()){
			InvoicePrintResult result=new InvoicePrintResult();
			result.setErroCode(AisinoResponseCodeDef.PRINT_SITE_SUCESS);
			result.setInvoiceCode(print.getInvoiceCode());
			result.setInvoiceNo(print.getInvoiceNo());
			result.setInvoiceType(print.getInvoiceType());
			response.addResult(result);
		}
		processTaxInterface.processPrintInvoiceResponse(request,response);
		
		return response;
	}


	@Override
	public DistributeInvoiceResponse processDistributeInvoice(
			DistributeInvoiceRequest request) {
		AssertHelper.notEmpty_assert(request, "分发内容不能为空");
		request.check();
		DistributeInvoiceResponse response=new DistributeInvoiceResponse();
		response.setSucess();
		processTaxInterface.processDistributeInvoiceResponse(request,response);
		return response;
	}

}
