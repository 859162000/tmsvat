/**
 * 
 */
package com.deloitte.tms.vat.salesinvoice.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.dao.impl.BaseDao;
import com.deloitte.tms.vat.salesinvoice.dao.TmsCrvatInvoiceReqPDao;
import com.deloitte.tms.vat.salesinvoice.model.TmsCrvatInvoiceReqP;

/**
 * @author jasonjyang
 *
 */
@Component(TmsCrvatInvoiceReqPDao.BEAN_ID)
public class TmsCrvatInvoiceReqPDaoImpl extends BaseDao<TmsCrvatInvoiceReqP> implements TmsCrvatInvoiceReqPDao {

	@Override
	public DaoPage findTmsCrvatInvoiceReqPByParams(Map params,
			Integer pageIndex, Integer pageSize) {
		StringBuffer query=new StringBuffer();
		Map values=new HashMap();
		buildTmsCrvatInvoiceReqPuery(query, values, params);
		return pageBy(query, values, pageIndex, pageSize);
	}
	
	private void buildTmsCrvatInvoiceReqPuery(StringBuffer query,Map values,Map params) {
		query.append(" from TmsCrvatInvoiceReqP where 1=1 ");
	}

}
