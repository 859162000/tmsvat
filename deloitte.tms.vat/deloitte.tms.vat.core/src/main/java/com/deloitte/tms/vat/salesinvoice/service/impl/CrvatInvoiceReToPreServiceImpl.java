package com.deloitte.tms.vat.salesinvoice.service.impl;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.deloitte.tms.base.cache.utils.LegalEntityCacheUtils;
import com.deloitte.tms.base.cache.utils.PrintOrgCacheUtils;
import com.deloitte.tms.pl.core.commons.exception.BusinessException;
import com.deloitte.tms.pl.core.context.ServiceContextUtils;
import com.deloitte.tms.pl.core.dao.IDao;
import com.deloitte.tms.pl.core.service.impl.BaseService;
import com.deloitte.tms.pl.flow.utils.FlowHelper;
import com.deloitte.tms.pl.workflow.utils.WorkFlowUtils;
import com.deloitte.tms.vat.salesinvoice.dao.InvoicePreHDao;
import com.deloitte.tms.vat.salesinvoice.dao.InvoiceReqHDao;
import com.deloitte.tms.vat.salesinvoice.model.InvoiceReqH;
import com.deloitte.tms.vat.salesinvoice.model.InvoiceReqL;
import com.deloitte.tms.vat.salesinvoice.model.InvoiceTrxPool;
import com.deloitte.tms.vat.salesinvoice.model.TmsCrvatInvoicePreH;
import com.deloitte.tms.vat.salesinvoice.model.TmsCrvatInvoicePreL;
import com.deloitte.tms.vat.salesinvoice.service.CrvatInvoiceReToPreService;
import com.deloitte.tms.vat.base.enums.AppFormStatuEnums;
import com.deloitte.tms.vat.base.enums.CrvaInvoicePreStatusEnums;
import com.deloitte.tms.vat.base.enums.CrvatTaxPoolStatuEnums;
import com.deloitte.tms.vat.core.common.IdGenerator;
import com.deloitte.tms.base.cache.model.LegalEntityNode;
import com.deloitte.tms.base.cache.model.PrintSiteNode;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

@Component(CrvatInvoiceReToPreService.BEAN_ID)
public class CrvatInvoiceReToPreServiceImpl extends BaseService implements
		CrvatInvoiceReToPreService {
	
	@Resource 
	InvoiceReqHDao invoiceReqHDao;
	
	@Resource 
	InvoicePreHDao invoicePreHDao;

	@Override
	public IDao getDao() {		
		return invoiceReqHDao;
	}

	

	@Override
	public void convertCrvaInvoiceReToPre(List<InvoiceReqH> list) {		
		for(InvoiceReqH invoiceReqH:list){			
			List<InvoiceReqL> reqllist = (List<InvoiceReqL>) invoiceReqH.getInvoiceReqLs();
			Map<String, List<InvoiceReqL>> map = new HashMap<String,List<InvoiceReqL>>();		
			for(InvoiceReqL invoiceReqL:reqllist){
				PrintSiteNode pringOrgNode = PrintOrgCacheUtils.getPrintNodeByLegalId(invoiceReqL.getLegalEntityId());
			    if(pringOrgNode!=null){
			    	String orgId = pringOrgNode.getId();
				    if(map.get(orgId)!=null){
				    	map.get(orgId).add(invoiceReqL);
				    }else {
				    	List<InvoiceReqL> invoiceReqls = new ArrayList<InvoiceReqL>();
				    	invoiceReqls.add(invoiceReqL);
				    	map.put(orgId, invoiceReqls);
					}		
			    }
										
			}
			Date date = getDatabaseServerDate();
			for(String key:map.keySet()){
				TmsCrvatInvoicePreH tmsCrvatInvoicePreH = new TmsCrvatInvoicePreH();
				tmsCrvatInvoicePreH.setCrvatInvoicePreNumber(invoiceReqH.getCrvatInvoiceReqNumber());
				tmsCrvatInvoicePreH.setCustomerName(invoiceReqH.getCustomerName());
				tmsCrvatInvoicePreH.setCustomerId(invoiceReqH.getCustomerId());
				tmsCrvatInvoicePreH.setCustRegistrationCode(invoiceReqH.getCustRegistrationCode());
				tmsCrvatInvoicePreH.setCustRegistrationNumber(invoiceReqH.getCustRegistrationNumber());
				tmsCrvatInvoicePreH.setInvoicePreDate(date);
				tmsCrvatInvoicePreH.setInvoicingType(invoiceReqH.getInvoicingType());
				//tmsCrvatInvoicePreH.setIsExitsCustomer(invoiceReqH.getIsExitsCustomer());
				tmsCrvatInvoicePreH.setLegalEntityId(invoiceReqH.getLegalEntityId());
				tmsCrvatInvoicePreH.setLegalEntityCode(invoiceReqH.getLegalEntityCode());
				tmsCrvatInvoicePreH.setLegalEntityName(invoiceReqH.getLegalEntityName());
				tmsCrvatInvoicePreH.setPrtLegalEntityId(key);
				LegalEntityNode legalEntityNode=LegalEntityCacheUtils.getLegalNodeByLegalId(key);
				tmsCrvatInvoicePreH.setPrtLegalEntityCode(legalEntityNode.getCode());
				tmsCrvatInvoicePreH.setPrtLegalEntityCode(legalEntityNode.getCode());
				tmsCrvatInvoicePreH.setRegistrationCode(invoiceReqH.getRegistrationCode());
				tmsCrvatInvoicePreH.setRegistrationNumber(invoiceReqH.getRegistrationNumber());
				tmsCrvatInvoicePreH.setInvoiceReqType(invoiceReqH.getInvoiceReqType());
				tmsCrvatInvoicePreH.setSubmitDate(invoiceReqH.getSubmitDate());
				tmsCrvatInvoicePreH.setStartDate(invoiceReqH.getStartDate());
				tmsCrvatInvoicePreH.setEndDate(invoiceReqH.getEndDate());
				tmsCrvatInvoicePreH.setStatus(CrvaInvoicePreStatusEnums.TOBEAPPROVE.getValue());			
				invoicePreHDao.save(tmsCrvatInvoicePreH);
				for(InvoiceReqL invoiceReqL:map.get(key) ){
					TmsCrvatInvoicePreL tmsCrvatInvoicePreL = new TmsCrvatInvoicePreL();
					tmsCrvatInvoicePreL.setAcctdAmountCr(invoiceReqL.getAcctdAmountCr());
					tmsCrvatInvoicePreL.setCrvatInvoiceReqLId(invoiceReqL.getId());
					tmsCrvatInvoicePreL.setInventoryItemDescripton(invoiceReqL.getInventoryItemDescripton());
					tmsCrvatInvoicePreL.setInventoryItemId(invoiceReqL.getInventoryItemId());
					tmsCrvatInvoicePreL.setInventoryItemModels(invoiceReqL.getInventoryItemModels());
					tmsCrvatInvoicePreL.setInventoryItemNumber(invoiceReqL.getInventoryItemNumber());
					tmsCrvatInvoicePreL.setInventoryItemQty(invoiceReqL.getInventoryItemQty());
					tmsCrvatInvoicePreL.setInvoiceAmount(invoiceReqL.getInvoiceAmount());
					tmsCrvatInvoicePreL.setInvoiceType(invoiceReqL.getInvoiceType());
					tmsCrvatInvoicePreL.setInvoiceAmount(invoiceReqL.getInvoiceAmount());
					tmsCrvatInvoicePreL.setLegalEntityCode(invoiceReqL.getLegalEntityCode());
					tmsCrvatInvoicePreL.setLegalEntityId(invoiceReqL.getLegalEntityId());
					tmsCrvatInvoicePreL.setLegalEntityName(invoiceReqL.getLegalEntityName());
					tmsCrvatInvoicePreL.setRegistrationCode(invoiceReqL.getRegistrationCode());
					tmsCrvatInvoicePreL.setRegistrationNumber(invoiceReqL.getRegistrationNumber());
					tmsCrvatInvoicePreL.setTaxTrxTypeId(invoiceReqL.getTaxTrxTypeId());
					tmsCrvatInvoicePreL.setStartDate(invoiceReqL.getStartDate());
					tmsCrvatInvoicePreL.setEndDate(invoiceReqL.getEndDate());
					tmsCrvatInvoicePreL.setTaxTrxTypeId(invoiceReqL.getTaxTrxTypeId());
					tmsCrvatInvoicePreL.setInvoicePeHid(tmsCrvatInvoicePreH.getId());
					invoicePreHDao.save(tmsCrvatInvoicePreL);
				}
			}			
		}
		

	}



	@Override
	public List<InvoiceReqH> findInvoiceReqHByParams(Map params) {
		// TODO Auto-generated method stub
		return invoiceReqHDao.findInvoiceReqHByParams(params);
	}



	@Override
	public int exeConvertCrvatInvoiceReToPre(InvoiceReqH invoiceReqH,Map params) {
		List<InvoiceReqL> reqllist = (List<InvoiceReqL>) invoiceReqH.getInvoiceReqLs();
		Map<String, List<InvoiceReqL>> map = new HashMap<String,List<InvoiceReqL>>();		
		for(InvoiceReqL invoiceReqL:reqllist){		
			PrintSiteNode pringOrgNode = PrintOrgCacheUtils.getPrintNodeByLegalId(invoiceReqL.getLegalEntityId());
		    if(pringOrgNode!=null){
		    	String orgId = pringOrgNode.getId();
			    if(map.get(orgId)!=null){
			    	map.get(orgId).add(invoiceReqL);
			    }else {
			    	List<InvoiceReqL> invoiceReqls = new ArrayList<InvoiceReqL>();
			    	invoiceReqls.add(invoiceReqL);
			    	map.put(orgId, invoiceReqls);
				}		
		    }
									
		}
		Date date = getDatabaseServerDate();
		
		for(String key:map.keySet()){
			TmsCrvatInvoicePreH tmsCrvatInvoicePreH = new TmsCrvatInvoicePreH();
			String sequece = FlowHelper.getNextFlowNo("INVOICEPREH");
			
			tmsCrvatInvoicePreH.setId(IdGenerator.getUUID());
			tmsCrvatInvoicePreH.setCrvatInvoicePreNumber(sequece);
			tmsCrvatInvoicePreH.setCrvatInvoiceReqHId(invoiceReqH.getId());
			tmsCrvatInvoicePreH.setCustomerName(invoiceReqH.getCustomerName());
			tmsCrvatInvoicePreH.setCustomerId(invoiceReqH.getCustomerId());
			tmsCrvatInvoicePreH.setCustRegistrationCode(invoiceReqH.getCustRegistrationCode());
			tmsCrvatInvoicePreH.setCustRegistrationNumber(invoiceReqH.getCustRegistrationNumber());
			tmsCrvatInvoicePreH.setInvoicePreDate(date);
			tmsCrvatInvoicePreH.setInvoicingType(invoiceReqH.getInvoicingType());
			tmsCrvatInvoicePreH.setIsExitsCustomer(invoiceReqH.getIsExitsCustomer());
			tmsCrvatInvoicePreH.setLegalEntityId(invoiceReqH.getLegalEntityId());
			tmsCrvatInvoicePreH.setLegalEntityCode(invoiceReqH.getLegalEntityCode());
			tmsCrvatInvoicePreH.setLegalEntityName(invoiceReqH.getLegalEntityName());
			tmsCrvatInvoicePreH.setPrtLegalEntityId(key);
			LegalEntityNode legalEntityNode=LegalEntityCacheUtils.getLegalNodeByLegalId(key);
			tmsCrvatInvoicePreH.setPrtLegalEntityCode(legalEntityNode.getCode());
			tmsCrvatInvoicePreH.setPrtLegalEntityCode(legalEntityNode.getCode());
			tmsCrvatInvoicePreH.setRegistrationCode(invoiceReqH.getRegistrationCode());
			tmsCrvatInvoicePreH.setRegistrationNumber(invoiceReqH.getRegistrationNumber());
			tmsCrvatInvoicePreH.setSubmitDate(invoiceReqH.getSubmitDate());
			tmsCrvatInvoicePreH.setStartDate(invoiceReqH.getStartDate());
			tmsCrvatInvoicePreH.setEndDate(invoiceReqH.getEndDate());
			tmsCrvatInvoicePreH.setInvoiceReqType(invoiceReqH.getInvoiceReqType());
			tmsCrvatInvoicePreH.setApprovalStatus(CrvaInvoicePreStatusEnums.TOBEAPPROVE.getValue());			
			long task_id =WorkFlowUtils.startFlowByKey("salesinvoice", null, tmsCrvatInvoicePreH.getId());
			String str_task_id = String.valueOf(task_id);
			tmsCrvatInvoicePreH.setWfTaskId(str_task_id);
			invoicePreHDao.save(tmsCrvatInvoicePreH);
			for(InvoiceReqL invoiceReqL:map.get(key) ){
				TmsCrvatInvoicePreL tmsCrvatInvoicePreL = new TmsCrvatInvoicePreL();
				tmsCrvatInvoicePreL.setId(IdGenerator.getUUID());
				tmsCrvatInvoicePreL.setAcctdAmountCr(invoiceReqL.getAcctdAmountCr());
				tmsCrvatInvoicePreL.setCrvatInvoiceReqLId(invoiceReqL.getId());
				tmsCrvatInvoicePreL.setInventoryItemDescripton(invoiceReqL.getInventoryItemDescripton());
				tmsCrvatInvoicePreL.setInventoryItemId(invoiceReqL.getInventoryItemId());
				tmsCrvatInvoicePreL.setInventoryItemModels(invoiceReqL.getInventoryItemModels());
				tmsCrvatInvoicePreL.setInventoryItemNumber(invoiceReqL.getInventoryItemNumber());
				tmsCrvatInvoicePreL.setInventoryItemQty(invoiceReqL.getInventoryItemQty());
				tmsCrvatInvoicePreL.setInvoiceAmount(invoiceReqL.getInvoiceAmount());
				tmsCrvatInvoicePreL.setInvoiceType(invoiceReqL.getInvoiceType());
				tmsCrvatInvoicePreL.setVatAmount(invoiceReqL.getVatAmount());				
				tmsCrvatInvoicePreL.setLegalEntityCode(invoiceReqL.getLegalEntityCode());
				tmsCrvatInvoicePreL.setLegalEntityId(invoiceReqL.getLegalEntityId());
				tmsCrvatInvoicePreL.setLegalEntityName(invoiceReqL.getLegalEntityName());
				tmsCrvatInvoicePreL.setOrigLegalEntityCode(invoiceReqL.getLegalEntityCode());
				tmsCrvatInvoicePreL.setOrigLegalEntityId(invoiceReqL.getLegalEntityId());
				tmsCrvatInvoicePreL.setOrigLegalEntityName(invoiceReqL.getLegalEntityName());
				tmsCrvatInvoicePreL.setOrigRegistrationCode(invoiceReqL.getRegistrationCode());
				tmsCrvatInvoicePreL.setOrigRegistrationNumber(invoiceReqL.getRegistrationNumber());		
				tmsCrvatInvoicePreL.setRegistrationCode(invoiceReqL.getRegistrationCode());
				tmsCrvatInvoicePreL.setRegistrationNumber(invoiceReqL.getRegistrationNumber());
				tmsCrvatInvoicePreL.setTaxTrxTypeId(invoiceReqL.getTaxTrxTypeId());
				tmsCrvatInvoicePreL.setStartDate(invoiceReqL.getStartDate());
				tmsCrvatInvoicePreL.setEndDate(invoiceReqL.getEndDate());
				tmsCrvatInvoicePreL.setInvoicePeHid(tmsCrvatInvoicePreH.getId());
				tmsCrvatInvoicePreL.setOrgId(invoiceReqL.getOrgId());
				tmsCrvatInvoicePreL.setInvoiceCateGory(invoiceReqL.getInvoiceCategory());
				tmsCrvatInvoicePreL.setCrvatTrxPoolId(invoiceReqL.getCrvatTrxPoolId());
				InvoiceTrxPool invoiceTrxPool = (InvoiceTrxPool) invoicePreHDao.get(InvoiceTrxPool.class, invoiceReqL.getCrvatTrxPoolId());
				invoiceTrxPool.setStatus(CrvatTaxPoolStatuEnums.PREP_FORM_USED.getValue());
				invoicePreHDao.update(invoiceTrxPool);
				invoicePreHDao.save(tmsCrvatInvoicePreL);
			}
			
		}	
		return map.keySet().size();
	}

	/**
	 * 
	 */
	@Override
	public void exeRevertInvoiceReq(InvoiceReqH invoiceReqH) {
		invoiceReqH.setStatus(AppFormStatuEnums.PREP_FORM_ERRO.getValue());
		invoiceReqHDao.update(invoiceReqH);
		invoiceReqHDao.updateTrxPoolStatusByReqHid(invoiceReqH.getId(),CrvatTaxPoolStatuEnums.OPEN.getValue());
	}

}
