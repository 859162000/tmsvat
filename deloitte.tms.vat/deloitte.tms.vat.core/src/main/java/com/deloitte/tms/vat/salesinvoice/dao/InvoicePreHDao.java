package com.deloitte.tms.vat.salesinvoice.dao;

import java.util.List;
import java.util.Map;

import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.dao.IDao;
import com.deloitte.tms.vat.salesinvoice.model.InvoiceReqH;
import com.deloitte.tms.vat.salesinvoice.model.TmsCrvatInvoicePreH;
import com.deloitte.tms.vat.salesinvoice.model.TmsCrvatInvoicePreL;

public interface InvoicePreHDao extends IDao<TmsCrvatInvoicePreH>{
	public static final String BEAN_ID="invoicePreHDao";
	public DaoPage  findTmsCrvatInvoicePreHsByParam(Map<String, Object> map); 
	public TmsCrvatInvoicePreH findTmsCrvatInvoicePreHById(String id);
	public DaoPage findTmsCrvatInvoicePreLById(String preHid,int pageIndex,int pageNumber);
	public List<TmsCrvatInvoicePreL> getCrvatInvoicePreLsByPreHId(String preHid);
	public void updateTrxPoolStatusByPreHid(String prehid,String status);
}
