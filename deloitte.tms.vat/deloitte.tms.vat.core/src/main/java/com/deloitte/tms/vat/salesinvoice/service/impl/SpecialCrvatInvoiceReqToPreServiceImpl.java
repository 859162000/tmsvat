package com.deloitte.tms.vat.salesinvoice.service.impl;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.deloitte.tms.base.cache.model.LegalEntityNode;
import com.deloitte.tms.base.cache.model.LicenseNoNode;
import com.deloitte.tms.base.cache.model.PrintSiteNode;
import com.deloitte.tms.base.cache.utils.LegalEntityCacheUtils;
import com.deloitte.tms.base.cache.utils.PrintOrgCacheUtils;
import com.deloitte.tms.pl.core.dao.IDao;
import com.deloitte.tms.pl.core.service.impl.BaseService;
import com.deloitte.tms.pl.flow.utils.FlowHelper;
import com.deloitte.tms.pl.workflow.utils.WorkFlowUtils;
import com.deloitte.tms.vat.base.enums.AppFormStatuEnums;
import com.deloitte.tms.vat.base.enums.CrvaInvoicePreStatusEnums;
import com.deloitte.tms.vat.base.enums.CrvatTaxPoolStatuEnums;
import com.deloitte.tms.vat.core.common.IdGenerator;
import com.deloitte.tms.vat.salesinvoice.dao.InvoicePreHDao;
import com.deloitte.tms.vat.salesinvoice.dao.InvoiceReqHDao;
import com.deloitte.tms.vat.salesinvoice.model.InvoiceReqH;
import com.deloitte.tms.vat.salesinvoice.model.InvoiceReqL;
import com.deloitte.tms.vat.salesinvoice.model.InvoiceTrxPool;
import com.deloitte.tms.vat.salesinvoice.model.TmsCrvatInvoicePreH;
import com.deloitte.tms.vat.salesinvoice.model.TmsCrvatInvoicePreL;
import com.deloitte.tms.vat.salesinvoice.model.TmsCrvatInvoicePreP;
import com.deloitte.tms.vat.salesinvoice.model.TmsCrvatInvoiceReqP;
import com.deloitte.tms.vat.salesinvoice.service.SpecialCrvatInvoiceReqToPreService;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

@Component(SpecialCrvatInvoiceReqToPreService.BEAN_ID)
public class SpecialCrvatInvoiceReqToPreServiceImpl extends BaseService implements
	SpecialCrvatInvoiceReqToPreService {
	
	@Resource 
	InvoiceReqHDao invoiceReqHDao;
	
	@Resource 
	InvoicePreHDao invoicePreHDao;

	@Override
	public IDao getDao() {		
		return invoiceReqHDao;
	}

	

	@Override
	public void specialConvertCrvaInvoiceReqToPre(List<InvoiceReqH> list) {		
		for(InvoiceReqH invoiceReqH:list){			
			List<TmsCrvatInvoiceReqP> reqPlist = (List<TmsCrvatInvoiceReqP>) invoiceReqH.getInvoiceReqPs();
			Map<String, List<TmsCrvatInvoiceReqP>> map = new HashMap<String,List<TmsCrvatInvoiceReqP>>();		
			for(TmsCrvatInvoiceReqP invoiceReqP:reqPlist){
				PrintSiteNode pringOrgNode = PrintOrgCacheUtils.getPrintNodeByLegalId(invoiceReqP.getLegalEntityId());
			    if(pringOrgNode!=null){
			    	String orgId = pringOrgNode.getId();
				    if(map.get(orgId)!=null){
				    	map.get(orgId).add(invoiceReqP);
				    }else {
				    	List<TmsCrvatInvoiceReqP> invoiceReqPs = new ArrayList<TmsCrvatInvoiceReqP>();
				    	invoiceReqPs.add(invoiceReqP);
				    	map.put(orgId, invoiceReqPs);
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
				for(TmsCrvatInvoiceReqP invoiceReqP:map.get(key) ){
					TmsCrvatInvoicePreP tmsCrvatInvoicePreP = new TmsCrvatInvoicePreP();
					tmsCrvatInvoicePreP.setAcctdAmountCr(invoiceReqP.getAcctdAmountCr());
					tmsCrvatInvoicePreP.setCrvatInvoiceReqPId(invoiceReqP.getId());
					tmsCrvatInvoicePreP.setInventoryItemDescripton(invoiceReqP.getInventoryItemDescripton());
					tmsCrvatInvoicePreP.setInventoryItemId(invoiceReqP.getInventoryItemId());
					tmsCrvatInvoicePreP.setInventoryItemModels(invoiceReqP.getInventoryItemModels());
					tmsCrvatInvoicePreP.setInventoryItemNumber(invoiceReqP.getInventoryItemNumber());
					tmsCrvatInvoicePreP.setInventoryItemQty(invoiceReqP.getInventoryItemQty());
					tmsCrvatInvoicePreP.setInvoiceAmount(invoiceReqP.getInvoiceAmount());
					//tmsCrvatInvoicePreP.setInvoiceType(invoiceReqP.getInvoiceType());
					tmsCrvatInvoicePreP.setLegalEntityCode(invoiceReqP.getLegalEntityCode());
					tmsCrvatInvoicePreP.setLegalEntityId(invoiceReqP.getLegalEntityId());
					tmsCrvatInvoicePreP.setLegalEntityName(invoiceReqP.getLegalEntityName());
					tmsCrvatInvoicePreP.setRegistrationCode(invoiceReqP.getRegistrationCode());
					tmsCrvatInvoicePreP.setRegistrationNumber(invoiceReqP.getRegistrationNumber());
					tmsCrvatInvoicePreP.setTaxTrxTypeId(invoiceReqP.getTaxTrxTypeId());
					tmsCrvatInvoicePreP.setStartDate(invoiceReqP.getStartDate());
					tmsCrvatInvoicePreP.setEndDate(invoiceReqP.getEndDate());
					tmsCrvatInvoicePreP.setTaxTrxTypeId(invoiceReqP.getTaxTrxTypeId());
					tmsCrvatInvoicePreP.setCrvatInvoicePreHId(tmsCrvatInvoicePreH.getId());
					invoicePreHDao.save(tmsCrvatInvoicePreP);
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
	public int exeConvertCrvatInvoiceReqToPreForSpecial(InvoiceReqH invoiceReqH,Map params) {
		List<TmsCrvatInvoiceReqP> reqPlist = (List<TmsCrvatInvoiceReqP>) invoiceReqH.getInvoiceReqPs();
		Map<String, List<TmsCrvatInvoiceReqP>> map = new HashMap<String,List<TmsCrvatInvoiceReqP>>();		
		for(TmsCrvatInvoiceReqP invoiceReqP:reqPlist){		
			PrintSiteNode pringOrgNode = PrintOrgCacheUtils.getPrintNodeByLegalId(invoiceReqP.getLegalEntityId());
			//PrintSiteNode pringOrgNode = PrintOrgCacheUtils.getPrintNodeByOrgId(invoiceReqL.getOrgId());
		    if(pringOrgNode!=null){
		    	String orgId = pringOrgNode.getId();
			    if(map.get(orgId)!=null){
			    	map.get(orgId).add(invoiceReqP);
			    }else {
			    	List<TmsCrvatInvoiceReqP> invoiceReqPs = new ArrayList<TmsCrvatInvoiceReqP>();
			    	invoiceReqPs.add(invoiceReqP);
			    	map.put(orgId, invoiceReqPs);
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
			for(TmsCrvatInvoiceReqP invoiceReqP:map.get(key) ){
				TmsCrvatInvoicePreP tmsCrvatInvoicePreP = new TmsCrvatInvoicePreP();
				tmsCrvatInvoicePreP.setId(IdGenerator.getUUID());
				tmsCrvatInvoicePreP.setAcctdAmountCr(invoiceReqP.getAcctdAmountCr());
				tmsCrvatInvoicePreP.setCrvatInvoiceReqPId(invoiceReqP.getId());
				tmsCrvatInvoicePreP.setInventoryItemDescripton(invoiceReqP.getInventoryItemDescripton());
				tmsCrvatInvoicePreP.setInventoryItemId(invoiceReqP.getInventoryItemId());
				tmsCrvatInvoicePreP.setInventoryItemModels(invoiceReqP.getInventoryItemModels());
				tmsCrvatInvoicePreP.setInventoryItemNumber(invoiceReqP.getInventoryItemNumber());
				tmsCrvatInvoicePreP.setInventoryItemQty(invoiceReqP.getInventoryItemQty());
				tmsCrvatInvoicePreP.setInvoiceAmount(invoiceReqP.getInvoiceAmount());
				//tmsCrvatInvoicePreP.setInvoiceType(invoiceReqP.getInvoiceType());
				tmsCrvatInvoicePreP.setVatAmount(invoiceReqP.getVatAmount());				
				tmsCrvatInvoicePreP.setLegalEntityCode(invoiceReqP.getLegalEntityCode());
				tmsCrvatInvoicePreP.setLegalEntityId(invoiceReqP.getLegalEntityId());
				tmsCrvatInvoicePreP.setLegalEntityName(invoiceReqP.getLegalEntityName());
				tmsCrvatInvoicePreP.setOrigLegalEntityCode(invoiceReqP.getLegalEntityCode());			
				LegalEntityNode entityNode=LegalEntityCacheUtils.getLegalNodeByLegalId(invoiceReqP.getLegalEntityId());			
				if(entityNode!=null){
					tmsCrvatInvoicePreP.setOrigLegalEntityId(entityNode.getLicenseNoId());
					tmsCrvatInvoicePreP.setOrigLegalEntityName(entityNode.getLicenseName());
					LicenseNoNode licenseNode = entityNode.getLicenseNoNode();
					if(licenseNode!=null){
						tmsCrvatInvoicePreP.setRegistrationNumber(licenseNode.getCode());
					}
					
				}			
				tmsCrvatInvoicePreP.setOrigLegalEntityName(invoiceReqP.getLegalEntityName());
				tmsCrvatInvoicePreP.setOrigRegistrationCode(invoiceReqP.getRegistrationCode());
				tmsCrvatInvoicePreP.setOrigRegistrationNumber(invoiceReqP.getRegistrationNumber());		
				tmsCrvatInvoicePreP.setRegistrationCode(invoiceReqP.getRegistrationCode());
				//tmsCrvatInvoicePreL.setRegistrationNumber(invoiceReqL.getRegistrationNumber());
				tmsCrvatInvoicePreP.setTaxTrxTypeId(invoiceReqP.getTaxTrxTypeId());
				tmsCrvatInvoicePreP.setStartDate(invoiceReqP.getStartDate());
				tmsCrvatInvoicePreP.setEndDate(invoiceReqP.getEndDate());
				tmsCrvatInvoicePreP.setCrvatInvoicePreHId(tmsCrvatInvoicePreH.getId());
				tmsCrvatInvoicePreP.setOrgId(invoiceReqP.getOrgId());
				//tmsCrvatInvoicePreP.setInvoiceCateGory(invoiceReqH.getInvoiceCategory());
				//tmsCrvatInvoicePreL.setCrvatTrxPoolId(invoiceReqP.getCrvatTrxPoolId());
				/*InvoiceTrxPool invoiceTrxPool = (InvoiceTrxPool) invoicePreHDao.get(InvoiceTrxPool.class, invoiceReqP.getCrvatTrxPoolId());
				invoiceTrxPool.setStatus(CrvatTaxPoolStatuEnums.PREP_FORM_USED.getValue());
				invoicePreHDao.update(invoiceTrxPool);*/
				invoicePreHDao.save(tmsCrvatInvoicePreP);
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



	@Override
	public List<InvoiceReqH> findInvoiceReqHByParamsForSpecial(Map params) {
		return invoiceReqHDao.findInvoiceReqHByParamsForSpecial(params);
	}

}
