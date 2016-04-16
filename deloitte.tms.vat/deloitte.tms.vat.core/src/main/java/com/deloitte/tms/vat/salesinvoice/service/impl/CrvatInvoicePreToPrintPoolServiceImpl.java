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
import com.deloitte.tms.base.cache.model.PrintSiteNode;
import com.deloitte.tms.base.cache.model.PrinterTerminalNode;
import com.deloitte.tms.base.cache.utils.LegalEntityCacheUtils;
import com.deloitte.tms.base.cache.utils.PrintOrgCacheUtils;
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
import com.deloitte.tms.vat.base.enums.VatCRInvoiceTypeEnums;
import com.deloitte.tms.vat.core.common.IdGenerator;
import com.deloitte.tms.vat.salesinvoice.dao.InvoicePreHDao;
import com.deloitte.tms.vat.salesinvoice.model.InvoicePrintPoolD;
import com.deloitte.tms.vat.salesinvoice.model.InvoicePrintPoolH;
import com.deloitte.tms.vat.salesinvoice.model.InvoicePrintPoolL;
import com.deloitte.tms.vat.salesinvoice.model.InvoiceTrxPool;
import com.deloitte.tms.vat.salesinvoice.model.TmsCrvatInvoicePreH;
import com.deloitte.tms.vat.salesinvoice.model.TmsCrvatInvoicePreL;
import com.deloitte.tms.vat.salesinvoice.service.CrvatInvoicePreToPrintPoolService;
import com.deloitte.tms.vat.salesinvoice.service.CrvatInvoiceReToPreService;
@Component(CrvatInvoicePreToPrintPoolService.BEAN_ID)
public class CrvatInvoicePreToPrintPoolServiceImpl extends BaseService
		implements CrvatInvoicePreToPrintPoolService {

	@Resource 
	InvoicePreHDao invoicePreHDao;

	@Override
	public IDao getDao() {		
		return invoicePreHDao;
	}

	
	@Override
	public List<TmsCrvatInvoicePreH> findInvoicePreHByParams(Map params) {
		// TODO Auto-generated method stub		
		params.put("approveStatus", CrvaInvoicePreStatusEnums.APPROVED.getValue());	
		params.put("pageNumber", 1);
		params.put("pageSize", Integer.MAX_VALUE);
		DaoPage daoPage =  invoicePreHDao.findTmsCrvatInvoicePreHsByParam(params);
		List<TmsCrvatInvoicePreH> list = (List<TmsCrvatInvoicePreH>) daoPage.getResult();
		for(TmsCrvatInvoicePreH tmsCrvatInvoicePreH:list){
			Hibernate.initialize(tmsCrvatInvoicePreH.getCustomer());
			Hibernate.initialize(tmsCrvatInvoicePreH.getTmsCrvatInvoicePreLs());
		}
		return list;
	}


	@Override
	public int exeConvertCrvatInvoicePreToPrintPool(
			TmsCrvatInvoicePreH tmsCrvatInvoicePreH) {
		// TODO Auto-generated method stub		
		Map<String, List<TmsCrvatInvoicePreL>> map = getTmsCrvaInvoicePresByInvoiceCateGory(tmsCrvatInvoicePreH);
		List<InvoicePrintPoolH> poolHs = new ArrayList<InvoicePrintPoolH>();
		for(String invoiceCateGory:map.keySet()){
			List<TmsCrvatInvoicePreL> tmsCrvatInvoicePreLs = map.get(invoiceCateGory);
			Map<String, List<TmsCrvatInvoicePreL>> legEntityMap = getTmsCrvaInvoicePresByLegalEntityId(tmsCrvatInvoicePreLs);
			for(String legalEntityId:legEntityMap.keySet()){
				//根据销方发票限额
				long limit = LegalEntityCacheUtils.getSelfInvoiceLimitAmountValueByLegalId(legalEntityId, invoiceCateGory);
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
				invoicePrintPoolH.setInvoiceCategory(invoiceCateGory);
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
//				PrinterTerminalNode printerTerminalNode = PrintOrgCacheUtils.getDefaultPrinterTerminalNodes(legalEntityId);
//				if(printerTerminalNode!=null){
//					invoicePrintPoolH.setEquipmentId(printerTerminalNode.getEquipmentId());
//					//invoicePrintPoolH.setEquipmentCode(equipmentCode);
//					//invoicePrintPoolH.setEquipmentName(equipmentName);
//				}
				
				List<TmsCrvatInvoicePreL> legalList = legEntityMap.get(legalEntityId);											
				Map<String, List<TmsCrvatInvoicePreL>> inventoryitemMap = getTmsCrvaInvoicePresByInventoryId(legalList);
				List<InvoicePrintPoolL> poolList = new ArrayList<InvoicePrintPoolL>();
				BigDecimal totalBigDecimal = BigDecimal.ZERO;
				for(String inventoryitem:inventoryitemMap.keySet()){
					InvoicePrintPoolL invoicePrintPoolL = new InvoicePrintPoolL();
					invoicePrintPoolL.setId(IdGenerator.getUUID());
					invoicePrintPoolL.setInvoicePrtPoolHId(invoicePrintPoolH.getId());
					int qty = 0;
					BigDecimal amountBigDecimal = BigDecimal.ZERO;
					BigDecimal vatAmoun = BigDecimal.ZERO;
					BigDecimal accAmount = BigDecimal.ZERO;
					BigDecimal priceOfUnit = BigDecimal.ZERO;
					String inventoryId = "";
					String inventorydesc = "";
					String inventorymode = "";
					String inventoryNum = "";
					List<InvoicePrintPoolD> poolds = new ArrayList<InvoicePrintPoolD>();
					for(TmsCrvatInvoicePreL tmsCrvatInvoicePreL:inventoryitemMap.get(inventoryitem)){
						InvoicePrintPoolD invoicePrintPoolD = new InvoicePrintPoolD();
						invoicePrintPoolD.setId(IdGenerator.getUUID());
						invoicePrintPoolD.setInvoicePrtPoolLId(invoicePrintPoolL.getId());
						invoicePrintPoolD.setCrvatInvoicePreLId(tmsCrvatInvoicePreL.getId());
						invoicePrintPoolD.setAcctdAmountCr(tmsCrvatInvoicePreL.getAcctdAmountCr());
						invoicePrintPoolD.setCrvatTrxPoolId(tmsCrvatInvoicePreL.getCrvatTrxPoolId());
						invoicePrintPoolD.setEndDate(tmsCrvatInvoicePreL.getEndDate());
						invoicePrintPoolD.setInventoryItemDescripton(tmsCrvatInvoicePreL.getInventoryItemDescripton());
						invoicePrintPoolD.setInventoryItemId(tmsCrvatInvoicePreL.getInventoryItemId());
						invoicePrintPoolD.setInventoryItemModels(tmsCrvatInvoicePreL.getInventoryItemModels());
						invoicePrintPoolD.setInventoryItemNumber(tmsCrvatInvoicePreL.getInventoryItemNumber());
						invoicePrintPoolD.setInventoryItemQty(tmsCrvatInvoicePreL.getInventoryItemQty());
						invoicePrintPoolD.setInvoiceAmount(tmsCrvatInvoicePreL.getInvoiceAmount());
						invoicePrintPoolD.setStartDate(tmsCrvatInvoicePreL.getStartDate());
						invoicePrintPoolD.setUomCode(tmsCrvatInvoicePreL.getUomCode());
						invoicePrintPoolD.setUomCodeDescripton(tmsCrvatInvoicePreL.getUomCodeDescripton());
						invoicePrintPoolD.setVatAmount(tmsCrvatInvoicePreL.getVatAmount());
						invoicePrintPoolD.setInvoiceTrxPoolId(tmsCrvatInvoicePreL.getCrvatTrxPoolId());
						if(tmsCrvatInvoicePreL.getInventoryItemQty()!=null){
							qty = qty + tmsCrvatInvoicePreL.getInventoryItemQty();
						}else {
							qty = qty + 1;
						}
						
						if(tmsCrvatInvoicePreL.getInvoiceAmount()!=null){
							amountBigDecimal = amountBigDecimal.add(tmsCrvatInvoicePreL.getInvoiceAmount());
						}
						if(tmsCrvatInvoicePreL.getVatAmount()!=null){
							vatAmoun = vatAmoun.add(tmsCrvatInvoicePreL.getVatAmount());
						}
						if(tmsCrvatInvoicePreL.getAcctdAmountCr()!=null){
							accAmount = accAmount.add(tmsCrvatInvoicePreL.getAcctdAmountCr());
						}					
						priceOfUnit = tmsCrvatInvoicePreL.getPriceOfUnit();
						inventoryId = tmsCrvatInvoicePreL.getInventoryItemId();
						inventorydesc = tmsCrvatInvoicePreL.getInventoryItemDescripton();
						inventorymode = tmsCrvatInvoicePreL.getInventoryItemModels();
						inventoryNum = tmsCrvatInvoicePreL.getInventoryItemNumber();
						poolds.add(invoicePrintPoolD);
						
					}
					invoicePrintPoolL.setInventoryItemQty(qty);
					invoicePrintPoolL.setInventoryItemDescripton(inventorydesc);
					invoicePrintPoolL.setInventoryItemNumber(inventoryNum);
					invoicePrintPoolL.setInventoryItemModels(inventorymode);
					invoicePrintPoolL.setInventoryItemId(inventoryId);
					vatAmoun = vatAmoun.setScale(2, RoundingMode.HALF_UP);
					invoicePrintPoolL.setVatAmount(vatAmoun);
					accAmount = accAmount.setScale(2, RoundingMode.HALF_UP);
					invoicePrintPoolL.setAcctdAmountCR(accAmount);
					amountBigDecimal= amountBigDecimal.setScale(2, RoundingMode.HALF_UP);
					invoicePrintPoolL.setInvoiceAmount(amountBigDecimal);
					invoicePrintPoolL.setPriceOfUnit(priceOfUnit);
					totalBigDecimal = totalBigDecimal.add(amountBigDecimal);
					invoicePrintPoolL.setPoolDs(poolds);
					poolList.add(invoicePrintPoolL);
					
				}
				invoicePrintPoolH.setInvoicePrintPoolLs(poolList);
				totalBigDecimal = totalBigDecimal.setScale(2, RoundingMode.HALF_UP);
				invoicePrintPoolH.setTotalAmount(totalBigDecimal);
				poolHs.add(invoicePrintPoolH);
				saveBachInvoicePrintPoolD(poolHs);
				saveBachInvoicePrintPoolH(poolHs);
				/*BigDecimal amount = BigDecimal.ZERO;
				
				for(TmsCrvatInvoicePreL tmsCrvatInvoicePreL:legalList){
					amount = amount.add(tmsCrvatInvoicePreL.getInvoiceAmount());
				}
				//计算同一销售方根据发票限额拆分的发票数量。
				long a = amount.longValue();
				long b = a/limit;
				if(a%limit>0)b=b+1;
				
				//产生发票
				for(int i=0;i<b;i++){
					
				}
				//根据货品和单价分组
*/			}
		}
		return 0;
	}
	
	private Map<String, List<TmsCrvatInvoicePreL>> getTmsCrvaInvoicePresByInvoiceCateGory(TmsCrvatInvoicePreH tmsCrvatInvoicePreH){
		Map<String, List<TmsCrvatInvoicePreL>> map = new HashMap<String, List<TmsCrvatInvoicePreL>>();
		Customer customer = tmsCrvatInvoicePreH.getCustomer();
		for(TmsCrvatInvoicePreL tmsCrvatInvoicePreL:tmsCrvatInvoicePreH.getTmsCrvatInvoicePreLs()){			
			String invoiceCateGory = tmsCrvatInvoicePreL.getInvoiceCateGory();			
			if(VatCRInvoiceTypeEnums.VAT_CR_INVOICE_ZP.getValue().equals(invoiceCateGory)){
				if(customer!=null){
					String type = customer.getCustLegalEntityType();
					if (!BaseLegalEntityTypeEnums.BASE_LEGAL_ENTITY_TYPE1.getValue().equals(type)){
						tmsCrvatInvoicePreL.setInvoiceCateGory(VatCRInvoiceTypeEnums.VAT_CR_INVOICE_PP.getValue());
					}
				}			
			}			
			if(map.get(tmsCrvatInvoicePreL.getInvoiceCateGory())!=null){
				map.get(tmsCrvatInvoicePreL.getInvoiceCateGory()).add(tmsCrvatInvoicePreL);
			}else {
				List<TmsCrvatInvoicePreL> list = new ArrayList<TmsCrvatInvoicePreL>();
				list.add(tmsCrvatInvoicePreL);
				map.put(tmsCrvatInvoicePreL.getInvoiceCateGory(), list);
			}
		}
		return map;
	}
	private Map<String, List<TmsCrvatInvoicePreL>> getTmsCrvaInvoicePresByLegalEntityId(List<TmsCrvatInvoicePreL> list){
		Map<String, List<TmsCrvatInvoicePreL>> map = new HashMap<String, List<TmsCrvatInvoicePreL>>();
		for(TmsCrvatInvoicePreL tmsCrvatInvoicePreL:list){
			if(map.get(tmsCrvatInvoicePreL.getLegalEntityId())!=null){
				map.get(tmsCrvatInvoicePreL.getLegalEntityId()).add(tmsCrvatInvoicePreL);
			}else {
				List<TmsCrvatInvoicePreL> list2 = new ArrayList<TmsCrvatInvoicePreL>();
				list2.add(tmsCrvatInvoicePreL);
				map.put(tmsCrvatInvoicePreL.getLegalEntityId(), list2);
			}
		}
		return map;
	}
	
	private Map<String, List<TmsCrvatInvoicePreL>> getTmsCrvaInvoicePresByInventoryId(List<TmsCrvatInvoicePreL> list){
		Map<String, List<TmsCrvatInvoicePreL>> map = new HashMap<String, List<TmsCrvatInvoicePreL>>();
		for(TmsCrvatInvoicePreL tmsCrvatInvoicePreL:list){
			String key = tmsCrvatInvoicePreL.getInventoryItemId() +"-"+ tmsCrvatInvoicePreL.getPriceOfUnit();
			if(map.get(key)!=null){
				map.get(key).add(tmsCrvatInvoicePreL);
			}else {
				List<TmsCrvatInvoicePreL> list2 = new ArrayList<TmsCrvatInvoicePreL>();
				list2.add(tmsCrvatInvoicePreL);
				map.put(key, list2);
			}
		}
		return map;
	}
	
	private void saveBachInvoicePrintPoolD(List<InvoicePrintPoolH> list){
		for(InvoicePrintPoolH invoicePrintPoolH:list){	
			
			for(InvoicePrintPoolL invoicePrintPoolL:invoicePrintPoolH.getInvoicePrintPoolLs()){
				for(InvoicePrintPoolD invoicePrintPoolD:invoicePrintPoolL.getPoolDs()){
					InvoiceTrxPool invoiceTrxPool = (InvoiceTrxPool) invoicePreHDao.get(InvoiceTrxPool.class, invoicePrintPoolD.getCrvatTrxPoolId());
					invoiceTrxPool.setStatus(CrvatTaxPoolStatuEnums.INVOICE_TOBE_PRINTED.getValue());
					invoicePreHDao.update(invoiceTrxPool);
					invoicePreHDao.save(invoicePrintPoolD);
					//新增发票打印池行表税率设置
					if(AssertHelper.notEmpty(invoiceTrxPool.getTaxRate())){
						invoicePrintPoolL.setTaxRate(new BigDecimal(invoiceTrxPool.getTaxRate()).setScale(6, RoundingMode.HALF_UP));
					}
					
				}	
				
			}
				
			
		}
	}
	private void saveBachInvoicePrintPoolH(List<InvoicePrintPoolH> list){
		for(InvoicePrintPoolH invoicePrintPoolH:list){
			String legalEntityId = invoicePrintPoolH.getLegalEntityId();
			String invoiceCateGory = invoicePrintPoolH.getInvoiceCategory();
			long limit = LegalEntityCacheUtils.getSelfInvoiceLimitAmountValueByLegalId(legalEntityId, invoiceCateGory);
			//long limit = 2000l;
			BigDecimal limitAmout = BigDecimal.valueOf(limit);
			BigDecimal totalAmount = invoicePrintPoolH.getTotalAmount();
			int size = invoicePrintPoolH.getInvoicePrintPoolLs().size();
			if(size<8&&totalAmount.compareTo(limitAmout)<=0){
				invoicePreHDao.save(invoicePrintPoolH);
				for(InvoicePrintPoolL invoicePrintPoolL:invoicePrintPoolH.getInvoicePrintPoolLs()){					
					invoicePreHDao.save(invoicePrintPoolL);
				}
				
			}
			if(size<8&&totalAmount.compareTo(limitAmout)>0){             
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
			if(size>8){
			   List<InvoicePrintPoolH> poolHs = handleSizeLarge8(invoicePrintPoolH);				
			   saveBachInvoicePrintPoolH(poolHs);
			}
		}
			
	}
	
	private List<InvoicePrintPoolH> handleSizeLarge8(InvoicePrintPoolH invoicePrintPoolH){			
		//根据总条数按照每页8条拆分
		int size = invoicePrintPoolH.getInvoicePrintPoolLs().size();
		int c = size/8;
		int b = size%8;
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
	
	
}
