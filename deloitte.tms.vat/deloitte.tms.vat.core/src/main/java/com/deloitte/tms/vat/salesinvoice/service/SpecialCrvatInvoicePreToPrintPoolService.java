package com.deloitte.tms.vat.salesinvoice.service;

import java.util.List;
import java.util.Map;

import com.deloitte.tms.pl.core.service.IService;
import com.deloitte.tms.vat.salesinvoice.model.TmsCrvatInvoicePreH;

public interface SpecialCrvatInvoicePreToPrintPoolService extends IService {
	public static final String BEAN_ID="specialCrvatInvoicePreToPrintPoolService";
	public int exeConvertCrvatInvoicePreToPrintPoolForSpecial(TmsCrvatInvoicePreH tmsCrvatInvoicePreH);
	public List<TmsCrvatInvoicePreH> findInvoicePreHByParamsForSpecial(Map params);

}
