package com.deloitte.tms.vat.salesinvoice.service.impl;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;










import org.springframework.stereotype.Component;

import com.deloitte.tms.base.cache.model.BizOrgNode;
import com.deloitte.tms.base.cache.utils.OrgCacheUtils;
import com.deloitte.tms.base.masterdata.model.Customer;
import com.deloitte.tms.base.masterdata.model.TmsMdLegalEntity;
import com.deloitte.tms.base.masterdata.model.TmsMdTaxTrxType;
import com.deloitte.tms.base.taxsetting.model.TmsMdInventoryItems;
import com.deloitte.tms.pl.cache.utils.DictionaryCacheUtils;
import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.core.commons.utils.SpringUtil;
import com.deloitte.tms.pl.core.commons.utils.reflect.ReflectUtils;
import com.deloitte.tms.pl.core.dao.IDao;
import com.deloitte.tms.pl.core.service.impl.BaseService;
import com.deloitte.tms.vat.salesinvoice.dao.ExtractInvoicePoolDao;
import com.deloitte.tms.vat.salesinvoice.dao.InvoiceReqHDao;
import com.deloitte.tms.vat.salesinvoice.model.InvoiceTrxPool;
import com.deloitte.tms.vat.salesinvoice.model.InvoiceTrxPoolInParam;
import com.deloitte.tms.vat.salesinvoice.service.CiticInvoiceReqHService;
import com.deloitte.tms.vat.salesinvoice.service.ExtractInvoiceTrxPoolService;
@Component("zhongxin")
public class CiticExtractInvoiceTrxPoolServiceImpl extends BaseService implements
		ExtractInvoiceTrxPoolService {
   
	@Resource
	InvoiceReqHDao invoiceReqHDao;
	@Override
	public IDao getDao() {
		// TODO Auto-generated method stub
		return invoiceReqHDao;
	}
	
	@Override
	public DaoPage getInvoiceTrxPoolByParams(Map map, Integer pageIndex,Integer pageSize) {
		// TODO Auto-generated method stub
		if(!AssertHelper.isOrNotEmpty_assert(map))
		{
			map=new HashMap();
		}	
		ExtractInvoicePoolDao extractInvoiceDao = SpringUtil.getBean("citicExtractInvoicePoolDao");
		DaoPage daoPage= extractInvoiceDao.findInvoiceTrxPoolByParams(map, pageIndex, pageSize);
		if(AssertHelper.isOrNotEmpty_assert(daoPage.getResult())){
			daoPage.setResult(convertInvoiceTrxPoolToInParam((List<InvoiceTrxPool>) daoPage.getResult()));
		}
		return daoPage;
	}
	private List<InvoiceTrxPoolInParam> convertInvoiceTrxPoolToInParam(List<InvoiceTrxPool>models){
		List<InvoiceTrxPoolInParam> result=new ArrayList<InvoiceTrxPoolInParam>();
		for(InvoiceTrxPool initiation:models){
			InvoiceTrxPoolInParam inparam=convertInvoiceTrxPoolToInParam(initiation);			
			inparam.setTaxRate(null!=initiation.getTaxRate()?initiation.getTaxRate():0.00);
			inparam.setTrxAffirmId(initiation.getTrxAffirmId());
			inparam.setTrxBatchNum(initiation.getTrxBatchNum());
			inparam.setTrxNumber(initiation.getTrxNumber());
			inparam.setSourceCode(initiation.getSourceCode());
			inparam.setCustRegistrationCode(initiation.getCustRegistrationCode());
			inparam.setCustRegistrationNumber(initiation.getCustRegistrationNumber());
			inparam.setCustBankAccountNum(initiation.getCustBankAccountNum());
			inparam.setCustBankBranchName(initiation.getCustBankBranchName());
			inparam.setTaxRate(initiation.getTaxRate());
			inparam.setTaxBaseName(initiation.getTaxBaseName());
			inparam.setTaxSettingMethod(initiation.getTaxSettingMethod());
			inparam.setInvoiceCategoryName(DictionaryCacheUtils.getCodeName("VAT_INVOICE_RULE", initiation.getInvoiceCategory()));
			inparam.setLegalEntityName(initiation.getLegalEntityName());
			inparam.setRegistrationNumber(initiation.getRegistrationNumber());		
			Customer customer = initiation.getCustomer();
			if(AssertHelper.isOrNotEmpty_assert(customer)){				 
				inparam.setCustomerName(customer.getCustomerName());
				inparam.setCustomerId(customer.getId());
				inparam.setCustomerNumber(customer.getCustomerNumber());
			}			
			inparam.setCrvatTrxPoolId(initiation.getId());
			inparam.setInvoiceAmount(null!=initiation.getCurrencyAmount()?initiation.getCurrencyAmount():BigDecimal.valueOf(0.00).multiply(null!=initiation.getExchangeRate()?initiation.getExchangeRate():BigDecimal.valueOf(0.00)));
			if(AssertHelper.isOrNotEmpty_assert(initiation.getOrgId())){
				BizOrgNode node=OrgCacheUtils.getNodeByOrgId(initiation.getOrgId());
				if(null!=node){
					inparam.setOrgName(node.getName());
					inparam.setOrgCode(node.getCode());	
				}
			}
			// TO DO
			String codeValue = initiation.getStatus();
			if(AssertHelper.isOrNotEmpty_assert(codeValue)){
				String valueName = DictionaryCacheUtils.getCodeName("VAT_CR_INVOICE_TRX_ITEM_STATUS", codeValue); 
				inparam.setStatus(valueName);
			}
			TmsMdTaxTrxType trxType = initiation.getTmsMdTaxTrxType();				
			if(trxType!=null){
				inparam.setTaxTrxTypeCode(trxType.getTaxTrxTypeCode());
				inparam.setTaxTrxTypeName(trxType.getTaxTrxTypeName());
			}
			
			if(AssertHelper.isOrNotEmpty_assert(initiation.getInventoryItemId())){
				TmsMdInventoryItems items = (TmsMdInventoryItems) this.get(TmsMdInventoryItems.class, initiation.getInventoryItemId());
				if(null!=items){
					inparam.setInventoryItemNumber(initiation.getInventoryItemNumber());
					inparam.setInventoryItemDescripton(items.getInventoryItemDescripton());
				}
			}
			if(AssertHelper.isOrNotEmpty_assert(initiation.getLegalEntityId())){
				TmsMdLegalEntity entity=(TmsMdLegalEntity) this.get(TmsMdLegalEntity.class, initiation.getLegalEntityId());
				if(null!=entity){
					inparam.setLegalEntityCode(entity.getLegalEntityCode());
					inparam.setLegalEntityName(entity.getLegalEntityName());
				}
			}
			result.add(inparam);
		}
		return result;
		
	}
	
	private  InvoiceTrxPoolInParam convertInvoiceTrxPoolToInParam(InvoiceTrxPool model){
		InvoiceTrxPoolInParam inparam=new InvoiceTrxPoolInParam();
		ReflectUtils.copyProperties(model, inparam);
		inparam.setTrxid(model.getId());		
		return inparam;
	}

	
}
