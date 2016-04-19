package com.deloitte.tms.vat.salesinvoice.dao;

import java.util.Map;

import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.dao.IDao;

public interface ExtractInvoicePoolDao extends IDao{
	public static final String BEAN_ID="extractInvoicePoolDao";
	public DaoPage findInvoiceTrxPoolByParams(Map params, Integer pageIndex,Integer pageSize) ;

}
