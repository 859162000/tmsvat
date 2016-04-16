package com.deloitte.tms.vat.salesinvoice.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.dao.IDao;
import com.deloitte.tms.pl.core.service.impl.BaseService;
import com.deloitte.tms.vat.salesinvoice.dao.TmsCrvatInvoiceReqPDao;
import com.deloitte.tms.vat.salesinvoice.model.TmsCrvatInvoiceReqPInParam;
import com.deloitte.tms.vat.salesinvoice.service.TmsCrvatInvoiceReqPService;

/**
 * 销项税特殊开票申请单明细
 * 
 * @author jasonjyang
 * 
 */
@Component(TmsCrvatInvoiceReqPService.BEAN_ID)
public class TmsCrvatInvoiceReqPServiceImpl extends BaseService implements
		TmsCrvatInvoiceReqPService {

	@Resource
	private TmsCrvatInvoiceReqPDao tmsCrvatInvoiceReqPDao;

	@Override
	public IDao getDao() {
		return tmsCrvatInvoiceReqPDao;
	}

	@Override
	public DaoPage findTmsCrvatInvoiceReqPByParams(Map params,
			Integer pageIndex, Integer pageSize) {
		if (params == null) {
			params = new HashMap();
		}
		DaoPage daoPage = tmsCrvatInvoiceReqPDao.findTmsCrvatInvoiceReqPByParams(params, pageIndex, pageSize);
		daoPage.setResult(convertObjectToInParam((List<Object[]>) daoPage
				.getResult()));
		return daoPage;
	}

	private List<TmsCrvatInvoiceReqPInParam> convertObjectToInParam(
			List<Object[]> models) {
		List<TmsCrvatInvoiceReqPInParam> result = new ArrayList<TmsCrvatInvoiceReqPInParam>();
		for (Object[] model : models) {
			TmsCrvatInvoiceReqPInParam inParam = null;// =
														// convertObjectToInParam(model);
			result.add(inParam);
		}
		return result;
	}

}
