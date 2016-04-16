package com.deloitte.tms.vat.inf.taxinfo.service;

import com.deloitte.tms.vat.inf.taxinfo.model.invoiceprint.issue.InvoiceIssueRequest;
import com.deloitte.tms.vat.inf.taxinfo.model.invoiceprint.issue.InvoiceIssueResponse;
import com.deloitte.tms.vat.inf.taxinfo.model.invoiceprint.print.InvoicePrintRequest;
import com.deloitte.tms.vat.inf.taxinfo.model.invoiceprint.print.PrintInvoiceResponse;
import com.deloitte.tms.vat.inf.taxinfo.model.invoiceserver.distributeInvoice.DistributeInvoiceRequest;
import com.deloitte.tms.vat.inf.taxinfo.model.invoiceserver.distributeInvoice.DistributeInvoiceResponse;

public interface TaxInterface {
	public static final String BEAN_ID="taxInterface";
	public static final String TEST_BEAN_ID="test_taxInterface";
	public static final String BEAN1_ID="taxInterface";
	public static final String TEST1_BEAN_ID="test_taxInterface";
	/**
	 * 批量发票开具
	 * @param invoiceIssueHeads
	 * @return 返回批量数据相对应的开具结果
	 */
	public InvoiceIssueResponse processIssueInvoice(InvoiceIssueRequest request);

	/**
	 * 批量打印
	 * @param invoiceIssueHeads
	 * @return 返回批量数据相对应的打印结果
	 */
	public PrintInvoiceResponse processPrintInvoice(InvoicePrintRequest request);

	/**
	 * 发票分发接口
	 * @param request
	 * @return
	 */
	public DistributeInvoiceResponse processDistributeInvoice(DistributeInvoiceRequest request);

}
