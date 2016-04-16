package com.deloitte.tms.vat.inf.taxinfo.service;

import com.deloitte.tms.vat.inf.taxinfo.model.invoiceprint.issue.InvoiceIssueRequest;
import com.deloitte.tms.vat.inf.taxinfo.model.invoiceprint.issue.InvoiceIssueResponse;
import com.deloitte.tms.vat.inf.taxinfo.model.invoiceprint.print.InvoicePrintRequest;
import com.deloitte.tms.vat.inf.taxinfo.model.invoiceprint.print.PrintInvoiceResponse;
import com.deloitte.tms.vat.inf.taxinfo.model.invoiceserver.distributeInvoice.DistributeInvoiceRequest;
import com.deloitte.tms.vat.inf.taxinfo.model.invoiceserver.distributeInvoice.DistributeInvoiceResponse;

public interface ProcessTaxInterface {
	public static final String BEAN_ID="processTaxInterface";
	public static final String TEST_BEAN_ID="test_processTaxInterface";
	public static final String BEAN1_ID="processTaxInterface";
	public static final String TEST1_BEAN_ID="test_processTaxInterface";
	/**
	 * 处理发票开具返回值
	 * @param response
	 */
	public void processIssueInvoiceResponse(InvoiceIssueRequest request,InvoiceIssueResponse response);
	/**
	 * 处理发票打印返回值
	 * @param response
	 */
	public void processPrintInvoiceResponse(InvoicePrintRequest request,PrintInvoiceResponse response);
	/**
	 * 处理发票分发返回值
	 * @param response
	 */
	public void processDistributeInvoiceResponse(DistributeInvoiceRequest request,DistributeInvoiceResponse response);
}
