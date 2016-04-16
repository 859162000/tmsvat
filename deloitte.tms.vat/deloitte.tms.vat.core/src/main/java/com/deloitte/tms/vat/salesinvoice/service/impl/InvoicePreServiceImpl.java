package com.deloitte.tms.vat.salesinvoice.service.impl;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Component;

import com.deloitte.tms.base.cache.model.OrgNode;
import com.deloitte.tms.base.cache.utils.OrgCacheUtils;
import com.deloitte.tms.base.masterdata.model.Customer;
import com.deloitte.tms.base.masterdata.model.TmsMdTaxTrxType;
import com.deloitte.tms.pl.cache.utils.DictionaryCacheUtils;
import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.core.commons.utils.DateUtils;
import com.deloitte.tms.pl.core.commons.utils.reflect.ReflectUtils;
import com.deloitte.tms.pl.core.dao.IDao;
import com.deloitte.tms.pl.core.service.impl.BaseService;
import com.deloitte.tms.pl.workflow.utils.WorkFlowUtils;
import com.deloitte.tms.vat.base.enums.CrvaInvoicePreStatusEnums;
import com.deloitte.tms.vat.base.enums.CrvatTaxPoolStatuEnums;
import com.deloitte.tms.vat.salesinvoice.dao.InvoicePreHDao;
import com.deloitte.tms.vat.salesinvoice.model.InvoiceReqH;
import com.deloitte.tms.vat.salesinvoice.model.InvoiceTrxPool;
import com.deloitte.tms.vat.salesinvoice.model.TmsCrvatInvoicePreH;
import com.deloitte.tms.vat.salesinvoice.model.TmsCrvatInvoicePreL;
import com.deloitte.tms.vat.salesinvoice.model.TmsCrvatInvoicePreLInParam;
import com.deloitte.tms.vat.salesinvoice.model.VoTmsCrvatInvoicePreH;
import com.deloitte.tms.vat.salesinvoice.service.InvoicePreService;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
@Component(InvoicePreService.BEAN_ID)
public class InvoicePreServiceImpl extends BaseService implements InvoicePreService {

	@Resource 
	InvoicePreHDao invoicePreHDao;
	@Override
	public IDao getDao() {
		// TODO Auto-generated method stub
		return invoicePreHDao;
	}
	@Override
	public DaoPage findTmsCrvatInvoicePreHsByParam(
			Map<String, Object> map) {
	
		DaoPage daoPage =  invoicePreHDao.findTmsCrvatInvoicePreHsByParam(map);
		daoPage.setResult(convertInvoiceTmsCrvatInvoicePreHInParam((List<TmsCrvatInvoicePreH>)daoPage.getResult()));
		return daoPage;
	}
	@Override
	public TmsCrvatInvoicePreH findTmsCrvatInvoicePreHById(String id) {
		return invoicePreHDao.findTmsCrvatInvoicePreHById(id);
	}
	@Override
	public DaoPage findTmsCrvatInvoicePreLByPreHId(String id,String pageIndex,String pageNumber) {
		int index = Integer.valueOf(pageIndex);
		int number = Integer.valueOf(pageNumber);	
		DaoPage daoPage = invoicePreHDao.findTmsCrvatInvoicePreLById(id, index, number);	
		daoPage.setResult(convertInvoiceTmsCrvatInvoicePreLInParam((List<TmsCrvatInvoicePreL>)daoPage.getResult()));
		return daoPage;			
	}
	
	private List<VoTmsCrvatInvoicePreH> convertInvoiceTmsCrvatInvoicePreHInParam(List<TmsCrvatInvoicePreH> models){
		List<VoTmsCrvatInvoicePreH> result=new ArrayList<VoTmsCrvatInvoicePreH>();
		for(TmsCrvatInvoicePreH initiation:models){
			VoTmsCrvatInvoicePreH inparam=convertInvoiceTmsCrvatInvoicePreHInParam(initiation);
			result.add(inparam);
		}
		return result;
	}
	public VoTmsCrvatInvoicePreH convertInvoiceTmsCrvatInvoicePreHInParam(TmsCrvatInvoicePreH model){
		VoTmsCrvatInvoicePreH inparam=new VoTmsCrvatInvoicePreH();
		ReflectUtils.copyProperties(model, inparam);
		BigDecimal totalAmount = BigDecimal.ZERO;
		BigDecimal accTotalAmount = BigDecimal.ZERO;		
		List<TmsCrvatInvoicePreL> list = getTmsCrvatInvoicePreLsByPreHId(model.getId());
		for(TmsCrvatInvoicePreL tmsCrvatInvoicePreL:list){
			if(tmsCrvatInvoicePreL.getInvoiceAmount()!=null){
				totalAmount = totalAmount.add(tmsCrvatInvoicePreL.getInvoiceAmount());
			}
			if(tmsCrvatInvoicePreL.getAcctdAmountCr()!=null){
				accTotalAmount = accTotalAmount.add(tmsCrvatInvoicePreL.getAcctdAmountCr());
			}
		}
		String totalAmountStr = String.valueOf(totalAmount);
		String accTotalAmountStr = String.valueOf(accTotalAmount);
		inparam.setAcctdAmountCr(accTotalAmountStr);
		inparam.setInvoiceAmount(totalAmountStr);
		String acceptOrgId = model.getOrgId();
		OrgNode acceptOrgNode = OrgCacheUtils.getNodeByOrgId(acceptOrgId);
		if(acceptOrgNode!=null){
			inparam.setAcceptOrg(acceptOrgNode.getName());
		}
		Date submitDate = model.getSubmitDate();
		if(submitDate!=null){
			String submitDateStr = DateUtils.format("yyyy-MM-dd", submitDate);		
			inparam.setStrSubmitDate(submitDateStr);
		}
		Date preDate = model.getInvoicePreDate();
		if(preDate!=null){
			String predateStr = DateUtils.format("yyyy-MM-dd", preDate);
			inparam.setInvoicePreDate(predateStr);
		}		
		Customer customer = model.getCustomer();
		if(customer!=null){
			inparam.setCustomerName(customer.getCustomerName());
			inparam.setCustomerCode(customer.getCustomerNumber());
			inparam.setCustomerNumber(customer.getCustomerNumber());
			inparam.setCustomerIdentity(customer.getCustRegistrationNumber());
			inparam.setBankAccount(customer.getCustDepositBankAccountNum());
			inparam.setBankName(customer.getCustDepositBankName());
			inparam.setContactName(customer.getContactName());
			inparam.setAddress(customer.getCustRegistrationAddress());
			inparam.setContactPhone(customer.getContactPhone());
		}
		InvoiceReqH invoiceReqH = model.getInvoiceReqH();
		if(invoiceReqH!=null){
			inparam.setInvoiceReqNum(invoiceReqH.getCrvatInvoiceReqNumber());
			inparam.setCrvatInvoiceReqNumber(invoiceReqH.getCrvatInvoiceReqNumber());
			String reqOrgid = invoiceReqH.getOrgId();
			OrgNode orgNode = OrgCacheUtils.getNodeByOrgId(reqOrgid);
			if(orgNode!=null){
				inparam.setRequestOrg(orgNode.getName());
			}			
			Date reqestDate = invoiceReqH.getInvoiceReqDate();
			String reqestDateStr = DateUtils.format("yyyy-MM-dd", reqestDate);
			inparam.setRequestDate(reqestDateStr);
			inparam.setRequestPerson(invoiceReqH.getCreatedBy());			
			
		}
		String codeValue = model.getCustRegistrationCode();
		String valueName = DictionaryCacheUtils.getCodeName("VAT_CUSTOMER_DISC_OPTION", codeValue);
		inparam.setCustRegistrationCodeStr(valueName);
		
		//inparam.setApprovalBy(model.getApprovalBy());
		//inparam.setApproveDate(model.getApproveDate());
		
		
		return inparam;
	}
	
	private List<TmsCrvatInvoicePreLInParam> convertInvoiceTmsCrvatInvoicePreLInParam(List<TmsCrvatInvoicePreL> models){
		List<TmsCrvatInvoicePreLInParam> result=new ArrayList<TmsCrvatInvoicePreLInParam>();
		for(TmsCrvatInvoicePreL initiation:models){
			TmsCrvatInvoicePreLInParam inparam=convertInvoiceTmsCrvatInvoicePreLInParam(initiation);
			result.add(inparam);
		}
		return result;
	}
	public TmsCrvatInvoicePreLInParam convertInvoiceTmsCrvatInvoicePreLInParam(TmsCrvatInvoicePreL model){
		TmsCrvatInvoicePreLInParam inparam=new TmsCrvatInvoicePreLInParam();
		ReflectUtils.copyProperties(model, inparam);
		inparam.setAcctdAmountCr(model.getAcctdAmountCr());
		inparam.setInvoiceAmount(model.getInvoiceAmount());
		inparam.setVatAmount(model.getVatAmount());
		InvoiceTrxPool invoiceTrxPool = model.getInvoiceTrxPool();
		if(invoiceTrxPool!=null){
			inparam.setTrxSquence(invoiceTrxPool.getTrxNumber());
			String trxDateString = DateUtils.format("yyyy-MM-dd",invoiceTrxPool.getTrxDate());
			inparam.setTrxDate(trxDateString);
			inparam.setTrxBatchNum(invoiceTrxPool.getTrxBatchNum());
		
		}
		TmsMdTaxTrxType tmsMdTaxTrxType = model.getTmsMdTaxTrxType();
		if(tmsMdTaxTrxType!=null){
			inparam.setTaxTrxTypeName(tmsMdTaxTrxType.getTaxTrxTypeName());
			inparam.setTaxTrxTypeCode(tmsMdTaxTrxType.getTaxTrxTypeCode());
		}
		String orgId = model.getOrgId();
		OrgNode orgNode = OrgCacheUtils.getNodeByOrgId(orgId);
		if(orgNode!=null){
			inparam.setOrgCode(orgNode.getCode());
			inparam.setOrgName(orgNode.getName());
		}
		return inparam;
	}
	@Override
	public void setAcceptStatus(TmsCrvatInvoicePreH tmsCrvatInvoicePreH) {
		// TODO Auto-generated method stub
		TmsCrvatInvoicePreH tmsCrvatInvoicePreHForUpdate = (TmsCrvatInvoicePreH) get(TmsCrvatInvoicePreH.class, tmsCrvatInvoicePreH.getId());
		Date date = getDatabaseServerDate();
		tmsCrvatInvoicePreHForUpdate.setApproveDate(date);
		tmsCrvatInvoicePreHForUpdate.setApprovalStatus(tmsCrvatInvoicePreH.getApprovalStatus());
		tmsCrvatInvoicePreHForUpdate.setOrgId(tmsCrvatInvoicePreH.getOrgId());
		tmsCrvatInvoicePreHForUpdate.setApprovalBy(tmsCrvatInvoicePreH.getApprovalBy());
		invoicePreHDao.updateTrxPoolStatusByPreHid(tmsCrvatInvoicePreHForUpdate.getId(), CrvatTaxPoolStatuEnums.PREP_FORM_APPROVED.getValue());
		//Hibernate.initialize(tmsCrvatInvoicePreHForUpdate.getTmsCrvatInvoicePreLs());
		//tmsCrvatInvoicePreHForUpdate.setTmsCrvatInvoicePreLs(invoicePreHDao.getCrvatInvoicePreLsByPreHId(tmsCrvatInvoicePreH.getId()));
		/*for(TmsCrvatInvoicePreL tmsCrvatInvoicePreL:tmsCrvatInvoicePreHForUpdate.getTmsCrvatInvoicePreLs()){	
			InvoiceTrxPool invoiceTrxPool = tmsCrvatInvoicePreL.getInvoiceTrxPool();
			invoiceTrxPool.setStatus(CrvatTaxPoolStatuEnums.PREP_FORM_APPROVED.getValue());
			update(invoiceTrxPool);
		}*/
		String taskId = tmsCrvatInvoicePreHForUpdate.getWfTaskId();
		if(!AssertHelper.empty(taskId)){
			long id = Long.valueOf(taskId.trim());
			WorkFlowUtils.completeTask(id);
		}else {
		   update(tmsCrvatInvoicePreHForUpdate);
		}
		
	}
	@Override
	public void setRevertStatus(TmsCrvatInvoicePreH tmsCrvatInvoicePreH) {
		// TODO Auto-generated method stub
		TmsCrvatInvoicePreH tmsCrvatInvoicePreHForUpdate = (TmsCrvatInvoicePreH) get(TmsCrvatInvoicePreH.class, tmsCrvatInvoicePreH.getId());
		tmsCrvatInvoicePreHForUpdate.setApprovalStatus(CrvaInvoicePreStatusEnums.REVOKED.getValue());
		tmsCrvatInvoicePreHForUpdate.setApproveDate(null);
		tmsCrvatInvoicePreHForUpdate.setApprovalBy(null);				
		//Hibernate.initialize(tmsCrvatInvoicePreHForUpdate.getTmsCrvatInvoicePreLs());
		tmsCrvatInvoicePreHForUpdate.setTmsCrvatInvoicePreLs(invoicePreHDao.getCrvatInvoicePreLsByPreHId(tmsCrvatInvoicePreH.getId()));
		for(TmsCrvatInvoicePreL tmsCrvatInvoicePreL:tmsCrvatInvoicePreHForUpdate.getTmsCrvatInvoicePreLs()){	
			InvoiceTrxPool invoiceTrxPool = tmsCrvatInvoicePreL.getInvoiceTrxPool();
			invoiceTrxPool.setStatus(CrvatTaxPoolStatuEnums.PREP_FORM_REVOKED.getValue());
			update(invoiceTrxPool);
		}
		update(tmsCrvatInvoicePreHForUpdate);
	}
	
	private List<TmsCrvatInvoicePreL> getTmsCrvatInvoicePreLsByPreHId(String preHid){
		return invoicePreHDao.getCrvatInvoicePreLsByPreHId(preHid);
	}

	

}
