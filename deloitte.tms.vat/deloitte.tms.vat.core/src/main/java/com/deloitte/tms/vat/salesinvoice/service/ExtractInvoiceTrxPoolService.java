package com.deloitte.tms.vat.salesinvoice.service;

import java.util.Map;

import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.service.IService;

public interface ExtractInvoiceTrxPoolService extends IService {
	public DaoPage getInvoiceTrxPoolByParams(Map map, Integer pageIndex,Integer pageSize);

}
