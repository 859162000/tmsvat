package com.deloitte.tms.vat.salesinvoice.dao;

import java.util.List;
import java.util.Map;

import com.deloitte.tms.base.masterdata.model.Customer;
import com.deloitte.tms.base.taxsetting.model.TmsMdProject;
import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.dao.IDao;
import com.deloitte.tms.vat.salesinvoice.model.InvoiceReqH;
import com.deloitte.tms.vat.salesinvoice.model.TmsCrvatInvoiceReqP;

/**
 * 特殊开票，无合同
 * @author jasonjyang
 *
 */
public interface InvoiceSpecialDao extends IDao<InvoiceReqH>{

	DaoPage findInvoiceReqHByParams(Map params, Integer pageIndex,
			Integer pageSize);
	DaoPage findInvoiceReqPByParams(Map params, Integer pageIndex,
			Integer pageSize);
	
	DaoPage findTmsMdInventoryItemsByParams(Map params, Integer pageIndex,
			Integer pageSize);

	List<Customer> findCustomer(String string,String string1);
}
