package com.deloitte.tms.vat.salesinvoice.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.deloitte.tms.base.masterdata.model.Customer;
import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.service.IService;
import com.deloitte.tms.vat.salesinvoice.model.InvoiceReqH;
import com.deloitte.tms.vat.salesinvoice.model.InvoiceReqHInParam;
import com.deloitte.tms.vat.salesinvoice.model.TmsCrvatInvoiceReqP;
import com.deloitte.tms.vat.salesinvoice.model.TmsCrvatInvoiceReqPInParam;

/**
 * 特殊开票，无合同
 * @author jasonjyang
 *
 */
public interface InvoiceSpecialService extends IService {
	/**
	 * 申请单数据查询
	 * @param map
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	DaoPage findInvoiceReqAll(Map<String, Object>map, Integer pageIndex,
			Integer pageSize);
	/**
	 * 保存特殊开票申请单
	 * @param map
	 * @return
	 * @throws ParseException
	 */
	String setUpHead(Map<String, Object>map) throws ParseException;
	
	InvoiceReqH getInvoiceReqH(String id);
	
	void deleteFromReqAll(String[] ids);

	DaoPage findInvoiceReqHByParams(Map params, Integer pageIndex,
			Integer pageSize);

	Customer getCustomerParam(Map<String, Object> map);

	DaoPage findTmsMdInventoryItemsByParams(Map params, Integer pageIndex,
			Integer pageSize);
	InvoiceReqHInParam convertInvoiceReqHToInParam(InvoiceReqH model);
	
	List<TmsCrvatInvoiceReqPInParam> convertReqP2Param(List<TmsCrvatInvoiceReqP> reqPs);
}
