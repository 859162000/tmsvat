/**
 * 
 */
package com.deloitte.tms.vat.salesinvoice.dao;

import java.util.List;
import java.util.Map;

import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.dao.IDao;
import com.deloitte.tms.vat.salesinvoice.model.TmsCrvatInvoiceReqP;

/**
 * 销项税特殊开票申请单明细
 * @author jasonjyang
 * 
 */
public interface TmsCrvatInvoiceReqPDao extends IDao<TmsCrvatInvoiceReqP>{
	static final String BEAN_ID="tmsCrvatInvoiceReqPDao";
	DaoPage findTmsCrvatInvoiceReqPByParams(Map params, Integer pageIndex,Integer pageSize);
	List<TmsCrvatInvoiceReqP> findInvoiceReLByReqHId(String hId);
}
