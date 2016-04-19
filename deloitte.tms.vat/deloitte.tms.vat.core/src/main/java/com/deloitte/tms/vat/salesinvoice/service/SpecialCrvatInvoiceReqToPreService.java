package com.deloitte.tms.vat.salesinvoice.service;

import java.util.List;
import java.util.Map;

import com.deloitte.tms.pl.core.service.IService;
import com.deloitte.tms.vat.salesinvoice.model.InvoiceReqH;

public interface SpecialCrvatInvoiceReqToPreService extends IService {
	public static final String BEAN_ID="specialCrvatInvoiceReToPreService";
	public void specialConvertCrvaInvoiceReqToPre(List<InvoiceReqH> list);
	public int exeConvertCrvatInvoiceReqToPreForSpecial(InvoiceReqH invoiceReqH,Map params);
	public List<InvoiceReqH> findInvoiceReqHByParams(Map params);
	public List<InvoiceReqH> findInvoiceReqHByParamsForSpecial(Map params);
	/**
	 * 准备单JOB失败时，重置交易池数据状态
	 * @param invoiceReqH
	 */
	public void exeRevertInvoiceReq(InvoiceReqH invoiceReqH);
}
