package com.deloitte.tms.vat.salesinvoice.dao.impl;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;






import com.deloitte.tms.base.cache.utils.LegalEntityCacheUtils;
import com.deloitte.tms.base.cache.utils.PrintOrgCacheUtils;
import com.deloitte.tms.base.enums.PrintRangeEnums;
import com.deloitte.tms.base.masterdata.model.Customer;
import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.core.commons.utils.DateUtils;
import com.deloitte.tms.pl.core.dao.impl.BaseDao;
import com.deloitte.tms.vat.base.enums.CrvatTaxPoolStatuEnums;
import com.deloitte.tms.vat.salesinvoice.dao.ExtractInvoicePoolDao;
import com.deloitte.tms.vat.salesinvoice.model.InvoiceTrxPool;

public abstract class AbstractExtractInvoicePoolDaoImpl extends BaseDao implements ExtractInvoicePoolDao {

	@Override
	public DaoPage findInvoiceTrxPoolByParams(Map params, Integer pageIndex,
			Integer pageSize) {
		StringBuffer query=new StringBuffer();
		Map values=new HashMap();
		buildInvoiceTrxPoolQuery(query, values, params);
		DaoPage daoPage = pageBy(query, values, pageIndex, pageSize);		
		return daoPage;
		
	}
	
	public  abstract void buildInvoiceTrxPoolQuery(StringBuffer query,Map values,Map params); 
	
	
	
	
	
	

}
