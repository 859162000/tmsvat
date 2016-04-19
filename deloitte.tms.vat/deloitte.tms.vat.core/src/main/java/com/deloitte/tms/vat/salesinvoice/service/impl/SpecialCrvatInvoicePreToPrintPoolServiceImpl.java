package com.deloitte.tms.vat.salesinvoice.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Component;

import com.deloitte.tms.base.cache.model.LegalEntityNode;
import com.deloitte.tms.base.cache.utils.LegalEntityCacheUtils;
import com.deloitte.tms.base.masterdata.model.Customer;
import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.core.commons.utils.reflect.ReflectUtils;
import com.deloitte.tms.pl.core.dao.IDao;
import com.deloitte.tms.pl.core.service.impl.BaseService;
import com.deloitte.tms.vat.base.enums.BaseLegalEntityTypeEnums;
import com.deloitte.tms.vat.base.enums.CrvaInvoicePreStatusEnums;
import com.deloitte.tms.vat.base.enums.CrvatTaxPoolStatuEnums;
import com.deloitte.tms.vat.base.enums.InvoicePrintStatusEnums;
import com.deloitte.tms.vat.base.enums.InvoiceReqTypeEnums;
import com.deloitte.tms.vat.base.enums.VatCRInvoiceTypeEnums;
import com.deloitte.tms.vat.core.common.IdGenerator;
import com.deloitte.tms.vat.salesinvoice.dao.InvoicePreHDao;
import com.deloitte.tms.vat.salesinvoice.model.InvoicePrintPoolD;
import com.deloitte.tms.vat.salesinvoice.model.InvoicePrintPoolH;
import com.deloitte.tms.vat.salesinvoice.model.InvoicePrintPoolL;
import com.deloitte.tms.vat.salesinvoice.model.InvoiceReqH;
import com.deloitte.tms.vat.salesinvoice.model.InvoiceTrxPool;
import com.deloitte.tms.vat.salesinvoice.model.TmsCrvatInvoicePreH;
import com.deloitte.tms.vat.salesinvoice.model.TmsCrvatInvoicePreP;
import com.deloitte.tms.vat.salesinvoice.model.TmsCrvatInvoicePreP;
import com.deloitte.tms.vat.salesinvoice.service.SpecialCrvatInvoicePreToPrintPoolService;
@Component(SpecialCrvatInvoicePreToPrintPoolService.BEAN_ID)
public class SpecialCrvatInvoicePreToPrintPoolServiceImpl extends BaseService
		implements SpecialCrvatInvoicePreToPrintPoolService {

	@Resource 
	InvoicePreHDao invoicePreHDao;

	@Override
	public IDao getDao() {
		return invoicePreHDao;
	}

	@Override
	public int exeConvertCrvatInvoicePreToPrintPoolForSpecial(
			TmsCrvatInvoicePreH tmsCrvatInvoicePreH){
		InvoiceReqH temp_reqH=(InvoiceReqH)invoicePreHDao.get(InvoiceReqH.class, tmsCrvatInvoicePreH.getCrvatInvoiceReqHId());
		//手工开票的发票类型已确定
		//Map<String, List<TmsCrvatInvoicePreP>> map = getTmsCrvaInvoicePresByInvoiceCateGory(tmsCrvatInvoicePreH);
		List<InvoicePrintPoolH> poolHs = new ArrayList<InvoicePrintPoolH>();
		//List<TmsCrvatInvoicePreP> TmsCrvatInvoicePrePs = map.get(invoiceCateGory);
		List<TmsCrvatInvoicePreP> TmsCrvatInvoicePrePs = tmsCrvatInvoicePreH.getTmsCrvatInvoicePrePs();
		Map<String, List<TmsCrvatInvoicePreP>> legEntityMap = getTmsCrvaInvoicePresByLegalEntityId(TmsCrvatInvoicePrePs);
		for(String legalEntityId:legEntityMap.keySet()){
			//根据销方发票限额
			long limit = LegalEntityCacheUtils.getSelfInvoiceLimitAmountValueByLegalId(legalEntityId, temp_reqH.getInvoiceCategory());
			InvoicePrintPoolH invoicePrintPoolH = new InvoicePrintPoolH();
			invoicePrintPoolH.setId(IdGenerator.getUUID());
			invoicePrintPoolH.setCrvatInvoicePreHId(tmsCrvatInvoicePreH.getId());
			invoicePrintPoolH.setCustomerId(tmsCrvatInvoicePreH.getCustomerId());							
			Customer customer = tmsCrvatInvoicePreH.getCustomer();
			if(customer!=null){
				invoicePrintPoolH.setCustomerNumber(customer.getCustomerNumber());
				invoicePrintPoolH.setCustDepositBankNumber(customer.getCustDepositBankNumber());
				invoicePrintPoolH.setCustDepositBankAccountNum(customer.getCustDepositBankAccountNum());
				invoicePrintPoolH.setCustDepositBankName(customer.getCustDepositBankName());
				invoicePrintPoolH.setCustContactName(customer.getContactName());
				invoicePrintPoolH.setCustContactPhone(customer.getContactPhone());
				invoicePrintPoolH.setCustRegistrationAddress(customer.getCustRegistrationAddress());	
				invoicePrintPoolH.setCustomerName(customer.getCustomerName());
			}
			invoicePrintPoolH.setCustRegistrationCode(invoicePrintPoolH.getCustRegistrationCode());
			invoicePrintPoolH.setCustRegistrationNumber(tmsCrvatInvoicePreH.getCustRegistrationNumber());
			invoicePrintPoolH.setEndDate(tmsCrvatInvoicePreH.getEndDate());
			invoicePrintPoolH.setInvoiceCategory(temp_reqH.getInvoiceCategory());
			invoicePrintPoolH.setInvoiceLimitAmount(new BigDecimal(limit));
			invoicePrintPoolH.setInvoiceReqType(tmsCrvatInvoicePreH.getInvoiceReqType());
			invoicePrintPoolH.setInvoicingType(tmsCrvatInvoicePreH.getInvoicingType());
			invoicePrintPoolH.setIsExistCustomer(tmsCrvatInvoicePreH.getIsExitsCustomer());
			invoicePrintPoolH.setInvoicePrintStatus(InvoicePrintStatusEnums.TOBEINVOICE.getValue());
			invoicePrintPoolH.setLegalEntityId(legalEntityId);
			LegalEntityNode legalEntityNode = LegalEntityCacheUtils.getLegalNodeByLegalId(legalEntityId);
			if(legalEntityNode!=null){
				invoicePrintPoolH.setLegalEntityCode(legalEntityNode.getCode());
				invoicePrintPoolH.setLegalEntityName(legalEntityNode.getName());							
				invoicePrintPoolH.setRegistrationNumber(legalEntityNode.getSelfLicenseNo());				
				invoicePrintPoolH.setRegistrationCode(legalEntityNode.getSelfLicenseName());	
				invoicePrintPoolH.setBankBranchName(legalEntityNode.getBankBranchName());				
				invoicePrintPoolH.setBankAccountNum(legalEntityNode.getBankAccountNum());				
				invoicePrintPoolH.setRegistrationContactPhone(legalEntityNode.getRegistrationContactPhone());				
				invoicePrintPoolH.setRegistrationContactAddress(legalEntityNode.getRegistrationContactAddress());
			}
			List<TmsCrvatInvoicePreP> legalList = legEntityMap.get(legalEntityId);
			int qty = 1;
			List<InvoicePrintPoolL> poolList = new ArrayList<InvoicePrintPoolL>();
			BigDecimal totalBigDecimal= BigDecimal.ZERO;	
			for(TmsCrvatInvoicePreP param:legalList){
				InvoicePrintPoolL invoicePrintPoolL = new InvoicePrintPoolL();
				invoicePrintPoolL.setId(IdGenerator.getUUID());
				invoicePrintPoolL.setInvoicePrtPoolHId(invoicePrintPoolH.getId());
				invoicePrintPoolL.setInventoryItemQty(qty);
				invoicePrintPoolL.setInventoryItemDescripton(param.getInventoryItemDescripton());
				invoicePrintPoolL.setInventoryItemNumber(param.getInventoryItemNumber());
				invoicePrintPoolL.setInventoryItemModels(param.getInventoryItemModels());
				invoicePrintPoolL.setInventoryItemId(param.getInventoryItemId());
				invoicePrintPoolL.setInvoiceAmount(param.getInvoiceAmount());
				invoicePrintPoolL.setPriceOfUnit(new BigDecimal(param.getPriceOfUnit()));
				totalBigDecimal.add(invoicePrintPoolL.getInvoiceAmount());
				poolList.add(invoicePrintPoolL);
			}
			invoicePrintPoolH.setInvoicePrintPoolLs(poolList);
			invoicePrintPoolH.setTotalAmount(totalBigDecimal.setScale(2, RoundingMode.HALF_UP));
			poolHs.add(invoicePrintPoolH);
			saveBachInvoicePrintPoolH(poolHs);
		}
		return 0;
	}
	/**
	 * 根据legalentity分组
	 * @param list
	 * @return
	 */
	private Map<String, List<TmsCrvatInvoicePreP>> getTmsCrvaInvoicePresByLegalEntityId(List<TmsCrvatInvoicePreP> list){
		Map<String, List<TmsCrvatInvoicePreP>> map = new HashMap<String, List<TmsCrvatInvoicePreP>>();
		for(TmsCrvatInvoicePreP TmsCrvatInvoicePreP:list){
			if(map.get(TmsCrvatInvoicePreP.getLegalEntityId())!=null){
				map.get(TmsCrvatInvoicePreP.getLegalEntityId()).add(TmsCrvatInvoicePreP);
			}else {
				List<TmsCrvatInvoicePreP> list2 = new ArrayList<TmsCrvatInvoicePreP>();
				list2.add(TmsCrvatInvoicePreP);
				map.put(TmsCrvatInvoicePreP.getLegalEntityId(), list2);
			}
		}
		return map;
	}
	
	private Map<String, List<TmsCrvatInvoicePreP>> getTmsCrvaInvoicePresByInventoryId(List<TmsCrvatInvoicePreP> list){
		Map<String, List<TmsCrvatInvoicePreP>> map = new HashMap<String, List<TmsCrvatInvoicePreP>>();
		for(TmsCrvatInvoicePreP TmsCrvatInvoicePreP:list){
			String key = TmsCrvatInvoicePreP.getInventoryItemId() +"-"+ TmsCrvatInvoicePreP.getPriceOfUnit();
			if(map.get(key)!=null){
				map.get(key).add(TmsCrvatInvoicePreP);
			}else {
				List<TmsCrvatInvoicePreP> list2 = new ArrayList<TmsCrvatInvoicePreP>();
				list2.add(TmsCrvatInvoicePreP);
				map.put(key, list2);
			}
		}
		return map;
	}
	/**
	 * 保存
	 * @param list
	 */
	private void saveBachInvoicePrintPoolH(List<InvoicePrintPoolH> list){
		for(InvoicePrintPoolH invoicePrintPoolH:list){
			String legalEntityId = invoicePrintPoolH.getLegalEntityId();
			String invoiceCateGory = invoicePrintPoolH.getInvoiceCategory();
			long limit = LegalEntityCacheUtils.getSelfInvoiceLimitAmountValueByLegalId(legalEntityId, invoiceCateGory);
			//long limit = 2000l;
			BigDecimal limitAmout = BigDecimal.valueOf(limit);
			BigDecimal totalAmount = invoicePrintPoolH.getTotalAmount();
			int size = invoicePrintPoolH.getInvoicePrintPoolLs().size();
			int sizelimit = 150;
			if(size<sizelimit&&totalAmount.compareTo(limitAmout)<=0){
				invoicePreHDao.save(invoicePrintPoolH);
				for(InvoicePrintPoolL invoicePrintPoolL:invoicePrintPoolH.getInvoicePrintPoolLs()){					
					invoicePreHDao.save(invoicePrintPoolL);
				}
				
			}
			if(size<sizelimit&&totalAmount.compareTo(limitAmout)>0){             
				List<InvoicePrintPoolL> list2 = (List<InvoicePrintPoolL>) invoicePrintPoolH.getInvoicePrintPoolLs();				
				//计算同一销售方根据发票限额拆分的发票数量。				
				long a = totalAmount.longValue();
				long c = a/limit;
				long b = a%limit;
				Long p = 0L;
				if(b>0){
					p=c+1;				
				}else {
					p=c;
				}
				if(size>1){
					if(size>p){
						Long pagesize = size/p;
						Long retain = size%p;
						int actpagesize=0;
						if(retain>0){
							List<InvoicePrintPoolH> poolHs = new ArrayList<InvoicePrintPoolH>();
							actpagesize = pagesize.intValue()+1;
							//余数不为0，有余数个表头，每个表头有actpagesize条行信息
							for(int i=0;i<retain;i++){
								InvoicePrintPoolH invoicePrintPoolH2 = new InvoicePrintPoolH();
								ReflectUtils.copyProperties(invoicePrintPoolH, invoicePrintPoolH2);
								invoicePrintPoolH2.setId(IdGenerator.getUUID());
								List<InvoicePrintPoolL> poolLs = new ArrayList<InvoicePrintPoolL>();
								BigDecimal amountBigDecimal = BigDecimal.ZERO;
								for(int j=i*actpagesize;j<i*actpagesize+actpagesize;j++){
									InvoicePrintPoolL invoicePrintPoolL = list2.get(j);								
									invoicePrintPoolL.setInvoicePrtPoolHId(invoicePrintPoolH2.getId());
									amountBigDecimal = amountBigDecimal.add(invoicePrintPoolL.getInvoiceAmount());
									poolLs.add(invoicePrintPoolL);
								}
								invoicePrintPoolH2.setTotalAmount(amountBigDecimal);
								invoicePrintPoolH2.setInvoicePrintPoolLs(poolLs);
								poolHs.add(invoicePrintPoolH2);
							}
							//剩余表头数量为p-retain,每个表头对应一条行信息
							int p2 = p.intValue()-retain.intValue();
							int index = retain.intValue()*actpagesize;
							for(int i=0;i<p2;i++){
								InvoicePrintPoolH invoicePrintPoolH2 = new InvoicePrintPoolH();
								ReflectUtils.copyProperties(invoicePrintPoolH, invoicePrintPoolH2);
								invoicePrintPoolH2.setId(IdGenerator.getUUID());
								InvoicePrintPoolL invoicePrintPoolL = list2.get(index+i);
								List<InvoicePrintPoolL> pools = new ArrayList<InvoicePrintPoolL>();
								invoicePrintPoolL.setInvoicePrtPoolHId(invoicePrintPoolH2.getId());
								pools.add(invoicePrintPoolL);
								invoicePrintPoolH2.setTotalAmount(invoicePrintPoolL.getInvoiceAmount());
								invoicePrintPoolH2.setInvoicePrintPoolLs(pools);
								poolHs.add(invoicePrintPoolH2);
							}
							saveBachInvoicePrintPoolH(poolHs);
							//saveBachInvoicePrintPoolD(poolHs);
						}else {
							//余数为0，拆分为p个发票表头，每个表头有actpagesize条行信息
							actpagesize = pagesize.intValue();
							List<InvoicePrintPoolH> poolHs = new ArrayList<InvoicePrintPoolH>();
							for(int i=0;i<p;i++){
								InvoicePrintPoolH invoicePrintPoolH2 = new InvoicePrintPoolH();
								ReflectUtils.copyProperties(invoicePrintPoolH, invoicePrintPoolH2);
								invoicePrintPoolH2.setId(IdGenerator.getUUID());
								List<InvoicePrintPoolL> poolLs = new ArrayList<InvoicePrintPoolL>();
								BigDecimal amountBigDecimal = BigDecimal.ZERO;
								for(int j=i*actpagesize;j<i*actpagesize+actpagesize;j++){
									InvoicePrintPoolL invoicePrintPoolL = list2.get(j);								
									invoicePrintPoolL.setInvoicePrtPoolHId(invoicePrintPoolH2.getId());
									amountBigDecimal = amountBigDecimal.add(invoicePrintPoolL.getInvoiceAmount());
									poolLs.add(invoicePrintPoolL);
								}
								invoicePrintPoolH2.setTotalAmount(amountBigDecimal);
								invoicePrintPoolH2.setInvoicePrintPoolLs(poolLs);
								poolHs.add(invoicePrintPoolH2);
							}
							saveBachInvoicePrintPoolH(poolHs);
							//saveBachInvoicePrintPoolD(poolHs);
						}						 														
						
					}else {
						List<InvoicePrintPoolH> poolHs = new ArrayList<InvoicePrintPoolH>();
						for(int i=0;i<list2.size();i++){
							InvoicePrintPoolH invoicePrintPoolH2 = new InvoicePrintPoolH();
							ReflectUtils.copyProperties(invoicePrintPoolH, invoicePrintPoolH2);
							invoicePrintPoolH2.setId(IdGenerator.getUUID());
							InvoicePrintPoolL invoicePrintPoolL = list2.get(i);
							List<InvoicePrintPoolL> pools = new ArrayList<InvoicePrintPoolL>();
							invoicePrintPoolL.setInvoicePrtPoolHId(invoicePrintPoolH2.getId());
							pools.add(invoicePrintPoolL);
							invoicePrintPoolH2.setTotalAmount(invoicePrintPoolL.getInvoiceAmount());
							invoicePrintPoolH2.setInvoicePrintPoolLs(pools);
							poolHs.add(invoicePrintPoolH2);
						}
						saveBachInvoicePrintPoolH(poolHs);
						//saveBachInvoicePrintPoolD(poolHs);
					}
				}
				if(size==1){
					List<InvoicePrintPoolL> list3 =  (List<InvoicePrintPoolL>) invoicePrintPoolH.getInvoicePrintPoolLs();
					InvoicePrintPoolL invoicePrintPoolL = list3.get(0);
					if(invoicePrintPoolL.getInventoryItemQty()==1){
						
					}else {
						if(limitAmout.compareTo(invoicePrintPoolL.getPriceOfUnit())>=0){
							//限额大于单价并且数量大于1需要拆分
							BigDecimal priceOfUnitBig = invoicePrintPoolL.getPriceOfUnit();
							Long price = priceOfUnitBig.longValue();						
							Long pLong = limit/price;
							int quantity = invoicePrintPoolL.getInventoryItemQty();
							
						}else {
							//限额小于单价
						}
					}
					
				}				
			}
			if(size>sizelimit){
			   List<InvoicePrintPoolH> poolHs = handleSizeLarge8(invoicePrintPoolH);				
			   saveBachInvoicePrintPoolH(poolHs);
			}
		}
			
	}
	
	private List<InvoicePrintPoolH> handleSizeLarge8(InvoicePrintPoolH invoicePrintPoolH){			
		//根据总条数按照每页8条拆分
		int size = invoicePrintPoolH.getInvoicePrintPoolLs().size();
		int sizelimit = 150;
		int c = size/sizelimit;
		int b = size%sizelimit;
		int p = 0;
		if(b>0){
			p=c+1;				
		}else {
			p=c;
		}
		List<InvoicePrintPoolL> list2 = (List<InvoicePrintPoolL>) invoicePrintPoolH.getInvoicePrintPoolLs();
		List<InvoicePrintPoolH> poolHs = new ArrayList<InvoicePrintPoolH>();
		for(int i=0;i<p;i++){
			InvoicePrintPoolH invoicePrintPoolH2 = new InvoicePrintPoolH();
			ReflectUtils.copyProperties(invoicePrintPoolH, invoicePrintPoolH2);
			invoicePrintPoolH2.setId(IdGenerator.getUUID());
			List<InvoicePrintPoolL> poolLs = new ArrayList<InvoicePrintPoolL>();
			BigDecimal totalAmount = BigDecimal.ZERO;
			if(i<c){
				for(int j=i*size;j<i*size+8;j++){
					InvoicePrintPoolL invoicePrintPoolL = list2.get(j);
					totalAmount = totalAmount.add(invoicePrintPoolL.getInvoiceAmount());
					invoicePrintPoolL.setInvoicePrtPoolHId(invoicePrintPoolH2.getId());
					poolLs.add(invoicePrintPoolL);
				}
			}else {
				for(int j=i*size;j<list2.size();j++){
					InvoicePrintPoolL invoicePrintPoolL = list2.get(j);
					totalAmount = totalAmount.add(invoicePrintPoolL.getInvoiceAmount());
					invoicePrintPoolL.setInvoicePrtPoolHId(invoicePrintPoolH2.getId());
					poolLs.add(invoicePrintPoolL);
				}
			}
			invoicePrintPoolH2.setTotalAmount(totalAmount);
			invoicePrintPoolH2.setInvoicePrintPoolLs(poolLs);
			poolHs.add(invoicePrintPoolH2);
		}
		return poolHs;
	}


	@Override
	public List<TmsCrvatInvoicePreH> findInvoicePreHByParamsForSpecial(
			Map params) {
		params.put("approveStatus", CrvaInvoicePreStatusEnums.APPROVED.getValue());
		params.put("invoiceReqType", InvoiceReqTypeEnums.SPECIAL_CONTRACT.getValue());
		params.put("pageNumber", 1);
		params.put("pageSize", Integer.MAX_VALUE);
		DaoPage daoPage =  invoicePreHDao.findTmsCrvatInvoicePreHsByParam(params);
		List<TmsCrvatInvoicePreH> list = (List<TmsCrvatInvoicePreH>) daoPage.getResult();
		for(TmsCrvatInvoicePreH tmsCrvatInvoicePreH:list){
			Hibernate.initialize(tmsCrvatInvoicePreH.getCustomer());
			List<TmsCrvatInvoicePreP> prePlist = invoicePreHDao.getCrvatInvoicePrePsByPreHId(tmsCrvatInvoicePreH.getId());
			tmsCrvatInvoicePreH.setTmsCrvatInvoicePrePs(prePlist);
		}
		return list;
	}
	
	
}
