/**
 * 
 */
package com.deloitte.tms.vat.salesinvoice.service;

import java.util.List;
import java.util.Map;

import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.service.IService;
import com.deloitte.tms.vat.salesinvoice.model.TmsCrvatInvoiceReqP;

/**
 * 销项税特殊开票申请单明细
 * @author jasonjyang
 *
 */
public interface TmsCrvatInvoiceReqPService extends IService{
	static final String BEAN_ID="tmsCrvatInvoiceReqPService";
	DaoPage findTmsCrvatInvoiceReqPByParams(Map params,
			Integer pageIndex, Integer pageSize);
	List<TmsCrvatInvoiceReqP> findInvoiceReLByReqHId(String crvatInvoiceReqHId);
}
