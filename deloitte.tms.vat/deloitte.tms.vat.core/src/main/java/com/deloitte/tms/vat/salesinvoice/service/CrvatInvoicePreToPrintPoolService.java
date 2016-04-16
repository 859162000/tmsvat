package com.deloitte.tms.vat.salesinvoice.service;

import java.util.List;
import java.util.Map;

import com.deloitte.tms.pl.core.service.IService;
import com.deloitte.tms.vat.salesinvoice.model.TmsCrvatInvoicePreH;

public interface CrvatInvoicePreToPrintPoolService extends IService {
	public static final String BEAN_ID="crvatInvoicePreToPrintPoolService";
	public int exeConvertCrvatInvoicePreToPrintPool(TmsCrvatInvoicePreH tmsCrvatInvoicePreH);
	public List<TmsCrvatInvoicePreH> findInvoicePreHByParams(Map params);

}
