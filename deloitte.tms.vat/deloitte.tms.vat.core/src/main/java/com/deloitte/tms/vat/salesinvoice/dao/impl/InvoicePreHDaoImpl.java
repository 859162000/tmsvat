package com.deloitte.tms.vat.salesinvoice.dao.impl;



import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
















import org.hibernate.Hibernate;
import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.core.commons.utils.DateUtils;
import com.deloitte.tms.pl.core.commons.utils.PageUtils;
import com.deloitte.tms.pl.core.dao.impl.BaseDao;
import com.deloitte.tms.vat.salesinvoice.dao.InvoicePreHDao;
import com.deloitte.tms.vat.salesinvoice.model.TmsCrvatInvoicePreH;
import com.deloitte.tms.vat.salesinvoice.model.TmsCrvatInvoicePreL;
import com.deloitte.tms.vat.salesinvoice.model.TmsCrvatInvoicePreP;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

@Component(InvoicePreHDao.BEAN_ID)
public class InvoicePreHDaoImpl extends BaseDao<TmsCrvatInvoicePreH> implements InvoicePreHDao {

	@Override
	public DaoPage findTmsCrvatInvoicePreHsByParam(
			Map<String, Object> map) {
		// TODO Auto-generated method stub		
		int pageIndex = PageUtils.getPageNumber(map);
		int pageSize = PageUtils.getPageSize(map);
		StringBuffer query=new StringBuffer();
		query.append("select preh from TmsCrvatInvoicePreH preh,InvoiceReqH reh,Customer cust where 1=1 and preh.crvatInvoiceReqHId=reh.id and preh.customerId = cust.id");
		Map values=new HashMap();
		if(!AssertHelper.empty(map.get("customerId"))){
			query.append(" and  preh.customerId = :customerId");
			values.put("customerId", map.get("customerId"));
		}
		
		if(!AssertHelper.empty(map.get("custRegistrationCode"))){
			query.append(" and  preh.custRegistrationCode = :custRegistrationCode");
			values.put("custRegistrationCode", map.get("custRegistrationCode"));
		}
		
		//新增柜台，预约，特殊开票 
		if(!AssertHelper.empty(map.get("invoiceReqType"))){
			query.append(" and  preh.invoiceReqType = :invoiceReqType");
			values.put("invoiceReqType", map.get("invoiceReqType"));		
		}
		
		if(!AssertHelper.empty(map.get("custRegistrationNumber"))){
			query.append(" and  preh.custRegistrationNumber = :custRegistrationNumber");
			values.put("custRegistrationNumber", map.get("custRegistrationNumber"));		
		}
		if(!AssertHelper.empty(map.get("crvatInvoiceReqNumber"))){
			query.append(" and  reh.crvatInvoiceReqNumber = :crvatInvoiceReqNumber");
			values.put("crvatInvoiceReqNumber", map.get("crvatInvoiceReqNumber"));	
		}
		if(!AssertHelper.empty(map.get("reqid"))){
			query.append(" and  reh.orgId = :reqid");
			values.put("reqid", map.get("reqid"));
		}
		if(!AssertHelper.empty(map.get("acceptid"))){
			query.append(" and  preh.orgId = :acceptid");
			values.put("acceptid", map.get("acceptid"));
		}
        if(!AssertHelper.empty(map.get("categoryName"))){
			
		}
		if(!AssertHelper.empty(map.get("approveStatus"))){
			String approveStatus = (String)map.get("approveStatus");
			query.append(" and  preh.approvalStatus = :approvalStatus");
			values.put("approvalStatus", approveStatus);		
		}
		
		if(!AssertHelper.empty(map.get("approvedatefrom"))){
			String appDateFromStr = (String)map.get("approvedatefrom");
			Date dateFrom = DateUtils.parse(appDateFromStr,"yyyy-MM-dd");
			query.append(" and  preh.approveDate >= :approvedatefrom");
			values.put("approvedatefrom", dateFrom);	
		}
		if(!AssertHelper.empty(map.get("approvedateto"))){
			String appDateToStr = (String)map.get("approvedateto");
			Date dateTo = DateUtils.parse(appDateToStr,"yyyy-MM-dd");
			Date dateToDate = DateUtils.addDay(dateTo, 1);		
			query.append(" and  preh.approveDate < :approvedateto");
			values.put("approvedateto", dateToDate);	
		}
		if(!AssertHelper.empty(map.get("customerCode"))){
			String customerNum = (String)map.get("customerCode");
			query.append(" and  cust.customerNumber = :customerNum");
			values.put("customerNum", customerNum);			
		}
		
		if(!AssertHelper.empty(map.get("applyDatefrom"))){
			String appDateFromStr = (String)map.get("applyDatefrom");
			Date dateFrom = DateUtils.parse(appDateFromStr,"yyyy-MM-dd");
			query.append(" and  reh.invoiceReqDate >= :applyDatefrom");
			values.put("applyDatefrom", dateFrom);			
		}
		if(!AssertHelper.empty(map.get("applyDateto"))){
			String appDateToStr = (String)map.get("applyDateto");
			Date dateTo = DateUtils.parse(appDateToStr,"yyyy-MM-dd");
			Date dateToDate = DateUtils.addDay(dateTo, 1);
			query.append(" and  reh.invoiceReqDate < :applyDateto");
			values.put("applyDateto", dateToDate);	
		}
		query.append(" order by preh.crvatInvoicePreNumber desc");
		return pageBy(query, values, pageIndex, pageSize);
	}

	@Override
	public TmsCrvatInvoicePreH findTmsCrvatInvoicePreHById(String id) {
		// TODO Auto-generated method stub
		TmsCrvatInvoicePreH tmsCrvatInvoicePreH = (TmsCrvatInvoicePreH) get(TmsCrvatInvoicePreH.class, id);
		Hibernate.initialize(tmsCrvatInvoicePreH.getTmsCrvatInvoicePreLs());
		return tmsCrvatInvoicePreH;
	}

	@Override
	public DaoPage findTmsCrvatInvoicePreLById(String preHid, int pageIndex,
			int pageNumber) {
		StringBuffer query=new StringBuffer();
		query.append(" from TmsCrvatInvoicePreL where 1=1 ");
		query.append(" and invoicePeHid = :preHid");
		Map values=new HashMap();
		values.put("preHid", preHid);
		return pageBy(query, values, pageIndex, pageNumber);
		
	}

	@Override
	public List<TmsCrvatInvoicePreL> getCrvatInvoicePreLsByPreHId(String preHid) {
		// TODO Auto-generated method stub
		StringBuffer query=new StringBuffer();
		query.append(" from TmsCrvatInvoicePreL where 1=1 ");
		query.append(" and invoicePeHid = :preHid");
		Map values=new HashMap();
		values.put("preHid", preHid);
		return findBy(query,values);
	}

	@Override
	public void updateTrxPoolStatusByPreHid(String prehid,String status) {
		AssertHelper.notEmpty_assert(prehid, "准备单号不能为空");
		/*//AssertHelper.notEmpty_assert(status, "状态参数不能为空");
*/		// TODO Auto-generated method stub
		StringBuffer query=new StringBuffer();
		Map<String,Object> values=new HashMap<String,Object>();		
		query.append("update TMS_CRVAT_TRX_POOL_ALL set status = :status  where CRVAT_TRX_POOL_ID in ");
		query.append("(select prel.CRVAT_TRX_POOL_ID from TMS_CRVAT_INVOICE_PRE_L prel where 1=1 and DELETED_FLAG = 1 ");
		values.put("status", status);
		if(AssertHelper.notEmpty(prehid))
		{
			query.append(" and prel.CRVAT_INVOICE_PRE_H_ID=:prehid)");
			values.put("prehid", prehid);
		}else {
			query.append(")");
		}
		executeSql(query, values);
	}

	@Override
	public List<TmsCrvatInvoicePreP> getCrvatInvoicePrePsByPreHId(String preHid) {
			StringBuffer query=new StringBuffer();
			query.append(" from TmsCrvatInvoicePreP where 1=1 ");
			query.append(" and crvatInvoicePreHId = :preHid");
			Map values=new HashMap();
			values.put("preHid", preHid);
		return findBy(query,values);
	}
	
	

	

}
