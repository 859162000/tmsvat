package com.deloitte.tms.vat.salesinvoice.service;

import java.text.ParseException;
import java.util.Map;

import com.deloitte.tms.base.masterdata.model.Customer;
import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.service.IService;

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
	public DaoPage findInvoiceReqAll(Map<String, Object>map, Integer pageIndex,
			Integer pageSize);
	/**
	 * 保存特殊开票申请单
	 * @param map
	 * @return
	 * @throws ParseException
	 */
	String setUpHead(Map<String, Object>map) throws ParseException;

	DaoPage findInvoiceReqHByParams(Map params, Integer pageIndex,
			Integer pageSize);

	Customer getCustomerParam(Map<String, Object> map);

	public DaoPage findTmsMdContractByParams(Map<String, Object> parameter,
			Integer pageNumber, Integer pageSize);

	public DaoPage findTmsMdInventoryItemsByParams(Map params, Integer pageIndex,
			Integer pageSize);

}
