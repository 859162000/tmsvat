package com.deloitte.tms.vat.salesinvoice.service;

import java.util.List;
import java.util.Map;

import com.deloitte.tms.pl.core.service.IService;
import com.deloitte.tms.vat.salesinvoice.model.InvoiceReqH;

public interface CrvatInvoiceReToPreService extends IService {
	public static final String BEAN_ID="crvatInvoiceReToPreService";
	public void convertCrvaInvoiceReToPre(List<InvoiceReqH> list);
	public int exeConvertCrvatInvoiceReToPre(InvoiceReqH invoiceReqH,Map params);
	public List<InvoiceReqH> findInvoiceReqHByParams(Map params);

}
