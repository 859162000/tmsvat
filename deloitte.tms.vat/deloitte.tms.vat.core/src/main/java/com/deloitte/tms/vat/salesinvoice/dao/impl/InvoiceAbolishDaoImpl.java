
/**    
 * Copyright (C),Deloitte
 * @FileName: InvoiceAbolishDaoImpl.java  
 * @Package: com.deloitte.tms.base.invoice.dao.impl  
 * @Description: //模块目的、功能描述  
 * @Author stonshi  
 * @Date 2016年3月15日 下午2:19:26  
 * @History: //修改记录
 * 〈author〉      〈time〉      〈version〉       〈desc〉
 * 修改人姓名            修改时间            版本号              描述   
*/  

package com.deloitte.tms.vat.salesinvoice.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.core.dao.impl.BaseDao;
import com.deloitte.tms.vat.salesinvoice.dao.InvoiceAbolishDao;
import com.deloitte.tms.vat.salesinvoice.model.InvoiceAbolish;

@Component(InvoiceAbolishDao.BEAN_ID)
public class InvoiceAbolishDaoImpl extends BaseDao<InvoiceAbolish> implements InvoiceAbolishDao {

	/**   
	 * @param params
	 * @param pageIndex
	 * @param pageSize
	 * @return  
	 * @see com.deloitte.tms.vat.salesinvoice.dao.InvoiceAbolishDao#findInvoiceAbolishByParams(java.util.Map, java.lang.Integer, java.lang.Integer)  
	 */
	
	@Override
	public DaoPage findInvoiceDByParams(Map params, Integer pageIndex,Integer pageSize) {
		StringBuffer query=new StringBuffer();
		Map values=new HashMap();
		buildInvoiceQuery(query, values, params);
		DaoPage daoPage= pageBySql(query, values, pageIndex, pageSize);
		return daoPage;
//		return pageBySql(query,values, pageIndex, pageSize);
	}

	private void buildInvoiceQuery(StringBuffer query,Map values,Map params) {
//		query.append("select d.INVENTORY_INVOICE_ID,d.INVOICE_CATEGORY,d.INVOICE_CODE,d.INVOICE_NUMBER,")
//		.append("a.APPROVAL_DATE,a.REQUEST_BY,a.ABOLISH_TYPE,a.APPROVAL_STATUS,d.EQUIPMENT_ID, a.INVOICE_ABOLISH_ID ")
//		.append("from TMS_CRVAT_INVOICE_ONHAND d left join TMS_CRVAT_INVOICE_ABOLISH a ")
//		.append("on d.INVENTORY_INVOICE_ID=a.INVENTORY_INVOICE_ID where d.IS_LOCK != 1 ")
//		.append("order by a.APPROVAL_DATE desc,a.REQUEST_BY");
		
		query.append("select d.INVENTORY_INVOICE_ID,d.INVOICE_CATEGORY,d.INVOICE_CODE,d.INVOICE_NUMBER, ")
		.append("a.APPROVAL_DATE,a.REQUEST_BY,a.ABOLISH_TYPE,a.APPROVAL_STATUS,d.STATUS, a.INVOICE_ABOLISH_ID, ")
		.append("b.invoice_category AS invoice_category1,b.invoice_code AS invoice_code1,b.invoice_number invoice_number1, ")
		.append("b.CUST_REGISTRATION_CODE,b.CUST_REGISTRATION_NUMBER, ")
		.append("b.INVOICE_LIMIT_AMOUNT,b.INVOICE_PRINT_DATE, ")
		.append("b.je,b.se,b.hj,a.ABOLISH_REASON ")
		.append("from TMS_CRVAT_INVOICE_ONHAND d left join TMS_CRVAT_INVOICE_ABOLISH a ")
		.append("on d.INVENTORY_INVOICE_ID=a.INVENTORY_INVOICE_ID and a.DELETED_FLAG = 1 ")
		.append("left join (select ph.invoice_category,ph.invoice_code,ph.invoice_number, ")
		.append("    ph.CUST_REGISTRATION_CODE,ph.CUST_REGISTRATION_NUMBER,ph.INVOICE_LIMIT_AMOUNT,ph.INVOICE_PRINT_DATE, ")
		.append("    sum(pl.invoice_amount) as je,sum(pl.vat_amount) as se,sum(pl.acctd_amount_cr) as hj ")
		.append("    from tms_crvat_inv_prt_pool_h  ph,tms_crvat_inv_prt_pool_l  pl ")
		.append("    where ph.invoice_prt_pool_h_id = pl.invoice_prt_pool_l_id and ph.DELETED_FLAG = 1 and pl.DELETED_FLAG = 1 ")
		.append("    group by ph.invoice_category,ph.invoice_code,ph.invoice_number,ph.CUST_REGISTRATION_CODE, ")
		.append("    ph.CUST_REGISTRATION_NUMBER,ph.INVOICE_LIMIT_AMOUNT,ph.INVOICE_PRINT_DATE )b ")
		.append("on d.invoice_code = b.invoice_code and d.invoice_number = b.invoice_number ")
		.append("where d.IS_LOCK != 1 and  d.DELETED_FLAG = 1 ");
		
		Object value=params.get("abolishType");
		if(AssertHelper.notEmpty(value))
		{
			query.append("and a.abolish_type=:abolishType ");
			values.put("abolishType", value);
		}
		value=params.get("invoiceCategory");
		if(AssertHelper.notEmpty(value))
		{
			query.append(" and d.Invoice_Category =:invoiceCategory ");
			values.put("invoiceCategory", value);
		}
		value=params.get("invoiceCode");
		if(AssertHelper.notEmpty(value))
		{
			query.append(" and d.invoice_code =:invoiceCode ");
			values.put("invoiceCode", value);
		}
		value=params.get("invoiceNumber");
		if(AssertHelper.notEmpty(value))
		{
			query.append(" and d.invoice_number =:invoiceNumber ");
			values.put("invoiceNumber", value);
		}
		value=params.get("custRegistrationCode");
		if(AssertHelper.notEmpty(value))
		{
			query.append(" and b.CUST_REGISTRATION_CODE =:custRegistrationCode ");
			values.put("custRegistrationCode", value);
		}
		value=params.get("custRegistrationNumber");
		if(AssertHelper.notEmpty(value))
		{
			query.append(" and b.CUST_REGISTRATION_NUMBER =:custRegistrationNumber ");
			values.put("custRegistrationNumber", value);
		}
		value=params.get("startDate");
		if(!AssertHelper.empty(value))
		{
			query.append(" and to_char(b.INVOICE_PRINT_DATE,'yyyy-mm-dd')>=:startDate ");
			values.put("startDate", value);
		}
		value=params.get("endDate");
		if(!AssertHelper.empty(value))
		{
			query.append(" and to_char(b.INVOICE_PRINT_DATE,'yyyy-mm-dd')<=:endDate ");
			values.put("endDate", value);
		}		
		query.append("order by a.APPROVAL_DATE desc,a.REQUEST_BY,d.INVOICE_CODE,d.INVOICE_NUMBER ");

	}
}