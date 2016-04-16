package com.deloitte.tms.vat.salesinvoice.service;


import java.util.Map;
import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.service.IService;
import com.deloitte.tms.vat.salesinvoice.model.TmsCrvatInvoicePreH;


public interface InvoicePreService extends IService{
	public static final String BEAN_ID="invoicePreService";
	public DaoPage findTmsCrvatInvoicePreHsByParam(Map<String,Object> map);
	public TmsCrvatInvoicePreH findTmsCrvatInvoicePreHById(String id);
	public DaoPage  findTmsCrvatInvoicePreLByPreHId(String id,String pageIndex,String pageNumber);
    public void setAcceptStatus(TmsCrvatInvoicePreH tmsCrvatInvoicePreH);
    public void setRevertStatus(TmsCrvatInvoicePreH tmsCrvatInvoicePreH);
}
