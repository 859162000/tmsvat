/**    
 * Copyright (C),Deloitte
 * @FileName: TmsCrvatTrxInfJobService.java  
 * @Package: com.deloitte.tms.vat.salesinvoice.jobs.service.impl  
 * @Description: //模块目的、功能描述  
 * @Author stonshi  
 * @Date 2016年3月19日 下午8:34:25  
 * @History: //修改记录
 * 〈author〉      〈time〉      〈version〉       〈desc〉
 * 修改人姓名            修改时间            版本号              描述   
 */

package com.deloitte.tms.vat.salesinvoice.jobs.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.deloitte.tms.base.masterdata.dao.BaseOrgDao;
import com.deloitte.tms.base.masterdata.dao.CustBankAccountDao;
import com.deloitte.tms.base.masterdata.dao.CustomerDao;
import com.deloitte.tms.base.masterdata.dao.CustomerSiteDao;
import com.deloitte.tms.base.masterdata.dao.TmsMdLegalEntityDao;
import com.deloitte.tms.base.masterdata.dao.TmsMdOrgLegalEntityDao;
import com.deloitte.tms.base.masterdata.model.BaseOrg;
import com.deloitte.tms.base.masterdata.model.CustBankAccount;
import com.deloitte.tms.base.masterdata.model.Customer;
import com.deloitte.tms.base.masterdata.model.CustomerSite;
import com.deloitte.tms.base.masterdata.model.TmsMdLegalEntity;
import com.deloitte.tms.base.masterdata.model.TmsMdOrgLegalEntity;
import com.deloitte.tms.base.masterdata.service.CustomerService;
import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.core.commons.utils.StringUtils;
import com.deloitte.tms.vat.core.common.IdGenerator;
import com.deloitte.tms.vat.salesinvoice.common.StringPool;
import com.deloitte.tms.vat.salesinvoice.dao.InvoiceTrxPoolDao;
import com.deloitte.tms.vat.salesinvoice.jobs.dao.TmsCrvatTrxInfDao;
import com.deloitte.tms.vat.salesinvoice.jobs.model.TmsCrvatTrxInf;
import com.deloitte.tms.vat.salesinvoice.jobs.service.TmsCrvatTrxInfJobTask;
import com.deloitte.tms.vat.salesinvoice.model.InvoiceTrxPool;
import com.deloitte.tms.vat.salesinvoice.service.InvoiceTrxPoolService;

/**
 * 〈一句话功能简述〉 功能详细描述
 * 
 * @author stonshi
 * @create 2016年3月19日 下午8:34:25
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Component(TmsCrvatTrxInfJobTask.BEAN_ID)
public class TmsCrvatTrxInfJobTaskImpl implements TmsCrvatTrxInfJobTask {
	
	Logger log = Logger.getLogger(this.getClass());

	@Resource
	private InvoiceTrxPoolService invoiceTrxPoolService;

	@Resource
	private CustomerService customerService;

	@Resource
	private TmsCrvatTrxInfDao tmsCrvatTrxInfDao;

	@Resource
	private InvoiceTrxPoolDao invoiceTrxPoolDao;

	@Resource
	private CustomerDao customerDao;

	@Resource
	private CustBankAccountDao custBankAccountDao;

	@Resource
	private CustomerSiteDao customerSiteDao;

	@Resource
	private TmsMdLegalEntityDao tmsMdLegalEntityDao;

	@Resource
	private TmsMdOrgLegalEntityDao tmsMdOrgLegalEntityDao;

	@Resource
	private BaseOrgDao baseOrgDao;

	List<TmsMdLegalEntity> list = new ArrayList<TmsMdLegalEntity>();
	
	@Override
	public int executeTransactionInfDatas(List<TmsCrvatTrxInf> tmsCrvatTrxInfs
			,List<TmsMdLegalEntity> allLegalEntities
			,List<TmsMdOrgLegalEntity> allOrgLegalEntities
			,List<BaseOrg> allOrgs
			,List<Customer> allCustomers
			, List<CustomerSite> allListSite
			, List<CustBankAccount> allCustBankAccount) {
		int sucessnum=0;
		for(TmsCrvatTrxInf tmsCrvatTrxInf:tmsCrvatTrxInfs){
			if(executeTransactionInfData(tmsCrvatTrxInf, 
					allLegalEntities, allOrgLegalEntities
					,allOrgs,allCustomers
					, allListSite, allCustBankAccount)){
				sucessnum++;
			}
		}
		return sucessnum;
	}
	
	public boolean executeTransactionInfData(TmsCrvatTrxInf tmsCrvatTrxInf,List<TmsMdLegalEntity> allLegalEntities,
			List<TmsMdOrgLegalEntity> allOrgLegalEntities,
			List<BaseOrg> allOrgs
			,List<Customer> allCustomers
			, List<CustomerSite> allListSite
			, List<CustBankAccount> allCustBankAccount) {
		boolean result=false;
		try {//全部catch,防止对其他数据的影响
			//生成交易数据
			InvoiceTrxPool invoiceTrxPool = createInvoiceTrxPool(tmsCrvatTrxInf);
			
			//处理机构,纳税实体和相关关系 
			/**********bo.wang 不再处理**************************/
//			processOrgAndLegalEntity(allLegalEntities, allOrgLegalEntities,allOrgs, tmsCrvatTrxInf);
			/**********bo.wang 不再处理**************************/
			//如果客户号不为空,处理客户数据
			//中信不给客户信息,特殊处理用户元数据
			if(AssertHelper.notEmpty(tmsCrvatTrxInf.getCustomerNumber())){
				Customer customer = processCustomer(tmsCrvatTrxInf,allCustomers, allListSite, allCustBankAccount);
				invoiceTrxPool.setCustomerId(customer.getId());	
			}
			//处理legalentityId,orgId
			String legalEntityCode=invoiceTrxPool.getLegalEntityCode();
			if(AssertHelper.notEmpty(legalEntityCode)){
				BaseOrg org=getBaseOrg(allOrgs, legalEntityCode);
				if(AssertHelper.notEmpty(org)){
					invoiceTrxPool.setOrgId(org.getId());
				}	
				TmsMdLegalEntity legalEntity=getTmsMdLegalEntity(allLegalEntities, legalEntityCode);
				if(AssertHelper.notEmpty(legalEntity)){
					invoiceTrxPool.setLegalEntityId(legalEntity.getId());
					invoiceTrxPool.setLegalEntityCode(legalEntity.getLegalEntityCode());
					invoiceTrxPool.setLegalEntityName(legalEntity.getLegalEntityName());
				}
			}			
			tmsCrvatTrxInf.setInterfaceTrxFlag(StringPool.FINISH);
			tmsMdLegalEntityDao.save(invoiceTrxPool);		
			tmsCrvatTrxInfDao.update(tmsCrvatTrxInf);			
			result=true;
		} catch (Exception e) {
			tmsCrvatTrxInf.setInterfaceTrxFlag(StringPool.ERRO);
			tmsCrvatTrxInfDao.update(tmsCrvatTrxInf);
			log.error("处理数据"+tmsCrvatTrxInf.toString()+"出错,原因:"+e.getMessage()+" 堆栈信息如下:");
			e.printStackTrace();
			result=false;
		}
		return result;
	}

	/**
	 * 购方地址信息
	 * 
	 * @param tmsCrvatTrxInf
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */

	private CustomerSite createCustomerSite(TmsCrvatTrxInf tmsCrvatTrxInf) {

		String recipientName = tmsCrvatTrxInf.getRecipientName();
		String recipientAddr = tmsCrvatTrxInf.getRecipientAddr();
		String recipientComp = tmsCrvatTrxInf.getRecipientComp();
		String recipientPhone = tmsCrvatTrxInf.getRecipientPhone();

		CustomerSite tmsMdCustSite = new CustomerSite();

		tmsMdCustSite.setRecipientAddr(recipientAddr);
		tmsMdCustSite.setRecipientComp(recipientComp);
		tmsMdCustSite.setRecipientName(recipientName);
		tmsMdCustSite.setRecipientPhone(recipientPhone);
		tmsMdCustSite.setIsDefault("Y");
		tmsMdCustSite.setEnabledFlag("N");

		return tmsMdCustSite;
	}

	/**
	 * 购方资金账户信息
	 * 
	 * @param tmsCrvatTrxInf
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */

	private CustBankAccount createCustBankAccount(TmsCrvatTrxInf tmsCrvatTrxInf) {
		String custBankAccountNum = tmsCrvatTrxInf.getCustomerAccount();
		CustBankAccount custBankAccount = new CustBankAccount();
		// custBankAccount.setCustomerId(customerId);
		custBankAccount.setCustBankAccountNum(custBankAccountNum);
		custBankAccount.setEnabledFlag("N");
		return custBankAccount;
	}

	/**
	 * 客户表
	 * 已经在进入此方法的时候判断了不能为空
	 * @param tmsCrvatTrxInf
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	private Customer processCustomer(TmsCrvatTrxInf tmsCrvatTrxInf
			,List<Customer> allCustomers
			, List<CustomerSite> allListSite
			, List<CustBankAccount> allCustBankAccount) {
		AssertHelper.notEmpty_assert(tmsCrvatTrxInf.getCustomerNumber(), "进入此方法客户id不能为空");
		String customerNumber = tmsCrvatTrxInf.getCustomerNumber();
		String sourceCustomerId = tmsCrvatTrxInf.getCustomerNumber();
		String customerName = tmsCrvatTrxInf.getCustomerName();
		String sourceCustomerName = tmsCrvatTrxInf.getCustomerName();
		String customerType = tmsCrvatTrxInf.getCustomerType();
		String sourceCustomerType = tmsCrvatTrxInf.getCustomerType();
		String custLegalEntityType = tmsCrvatTrxInf.getLegalEntityType();
		String custRegistrationNumber = tmsCrvatTrxInf.getRegistrationNumber();
		String gsnRegistrationNumber = tmsCrvatTrxInf.getRegistrationNumber();
		String custDepositBankName = tmsCrvatTrxInf.getCustBankBranchName();
		String custDepositBankNumber = tmsCrvatTrxInf.getCustBankAccountNum();
		String custRegistrationAddress = tmsCrvatTrxInf.getCustRegistrationAddress();
		String contactPhone = tmsCrvatTrxInf.getCustContactPhone();
		String printPeriodName = tmsCrvatTrxInf.getPrintPeriodName();
		String appointInvoiceOrgCode = tmsCrvatTrxInf.getAppointInvoiceOrgCode();
		
		/**** bo.wang 用缓存吧*****************/
//		Map<String,String> map = new HashMap<String,String>();
//		map.put("customerNumber", customerNumber);
//		List<Customer> listCustomer = customerDao.findCustomerByParams(map);
//	
//		Customer customer;
//		if(listCustomer.size()>0) {
//			customer = listCustomer.get(0);
//		} else {
//			customer = new Customer();
//		}
		/**** bo.wang 用缓存吧*****************/
		Customer customer=getCustomer(customerNumber,allCustomers);		
		if(customer==null){
			customer=new Customer();
		}
		
		customer.setCustomerNumber(customerNumber);
		customer.setSourceCustomerId(sourceCustomerId);
		customer.setCustomerName(customerName);
		customer.setSourceCustomerName(sourceCustomerName);
		customer.setCustomerType(customerType);
		customer.setSourceCustomerType(sourceCustomerType);
		customer.setCustLegalEntityType(custLegalEntityType);
		customer.setCustRegistrationNumber(custRegistrationNumber);
		customer.setGsnRegistrationNumber(gsnRegistrationNumber);
		customer.setCustDepositBankName(custDepositBankName);
		customer.setCustDepositBankNumber(custDepositBankNumber);
		customer.setCustRegistrationAddress(custRegistrationAddress);
		customer.setContactPhone(contactPhone);
		customer.setPrintPeriodName(printPeriodName);
		customer.setAppointInvoiceOrgCode(appointInvoiceOrgCode);
		customer.setIsAppointInvoice("N");
		customer.setIsInvoiceProviding("N");
		customer.setEnabledFlag("N");
		
		if(customer.getId()==null){
			customerDao.save(customer);
			if(AssertHelper.notEmpty(tmsCrvatTrxInf.getCustBankAccountNum())){
				CustBankAccount custBankAccount = createCustBankAccount(tmsCrvatTrxInf);
				custBankAccount.setCustomerId(customer.getId());
				custBankAccountDao.save(custBankAccount);
			}
			if(AssertHelper.notEmpty(tmsCrvatTrxInf.getRecipientName())&&
					AssertHelper.notEmpty(tmsCrvatTrxInf.getRecipientAddr())&&
					AssertHelper.notEmpty(tmsCrvatTrxInf.getRecipientComp())&&
					AssertHelper.notEmpty(tmsCrvatTrxInf.getRecipientPhone())){
				CustomerSite tmsMdCustSite = createCustomerSite(tmsCrvatTrxInf);					
				tmsMdCustSite.setCustomerId(customer.getId());	
				customerSiteDao.save(tmsMdCustSite);
			}			
		}else{
			customerDao.update(customer);
			String customerId=customer.getId();
			//根据客户号和客户地址检查是否一致
			if(AssertHelper.notEmpty(tmsCrvatTrxInf.getRecipientAddr())){
//				Map<String, String> params=new HashMap<String, String>();
//				params.put("customerId", customerId);
//				params.put("recipientAddr", tmsCrvatTrxInf.getRecipientAddr());
//				List<CustomerSite> listSite=customerSiteDao.findCustomerSiteByParams(params);
//				if(listSite.size()<1){
				CustomerSite customerSite=getCustomerSite(customerId,tmsCrvatTrxInf.getRecipientAddr(),allListSite);
				if(customerSite==null){
					customerSite = createCustomerSite(tmsCrvatTrxInf);
					customerSiteDao.save(customerSite);
					allListSite.add(customerSite);
				}
			}
			if(AssertHelper.notEmpty(tmsCrvatTrxInf.getCustBankAccountNum())){
				//根据客户号和资金账号检查是否一致
//				Map<String, String> param_2=new HashMap<String, String>();
//				param_2.put("customerId", customerId);
//				param_2.put("custBankAccountNum", tmsCrvatTrxInf.getCustBankAccountNum());
//				List<CustBankAccount> listBank=custBankAccountDao.findCustBankAccountByParams(param_2);
//				if(listBank.size()<1){
				CustBankAccount custBankAccount=getCustBankAccount(customerId,tmsCrvatTrxInf.getCustBankAccountNum(),allCustBankAccount);
				if(custBankAccount==null)
					custBankAccount = createCustBankAccount(tmsCrvatTrxInf);
					custBankAccountDao.save(custBankAccount);
					allCustBankAccount.add(custBankAccount);
				}
			}			
		return customer;
	}
	private CustBankAccount getCustBankAccount(String customerId,
			String custBankAccountNum, List<CustBankAccount> allCustBankAccount) {
		for(CustBankAccount custBankAccount:allCustBankAccount){
			if(customerId.equals(custBankAccount.getId())&&custBankAccountNum.equals(custBankAccount.getCustBankAccountNum())){
				return custBankAccount;
			}
		}
		return null;
	}
	private CustomerSite getCustomerSite(String customerId,
			String recipientAddr, List<CustomerSite> allListSite) {
		for(CustomerSite customerSite:allListSite){
			if(customerId.equals(customerSite.getId())&&recipientAddr.equals(customerSite.getRecipientAddr())){
				return customerSite;
			}
		}
		return null;
	}

	private Customer getCustomer(String customerNumber,
			List<Customer> allCustomers) {
		for(Customer customer:allCustomers){
			if(customer.getCustomerNumber().equals(customerNumber)){
				return customer;
			}
		}
		return null;
	}

	/**
	 * 销项税开票交易池
	 * 永远都是插入
	 * @param tmsCrvatTrxInf
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	private InvoiceTrxPool createInvoiceTrxPool(TmsCrvatTrxInf tmsCrvatTrxInf) {

		String invoicingType = tmsCrvatTrxInf.getInvoicingType();
		String legalEntityCode = tmsCrvatTrxInf.getOrgCode();
		String legalEntityName = tmsCrvatTrxInf.getOrgName();
		String sourceCode = tmsCrvatTrxInf.getSourceCode();
		String trxBatchNum = tmsCrvatTrxInf.getTrxBatchNum();
		String trxNumber = tmsCrvatTrxInf.getTrxNumber();
		Date trxDate = tmsCrvatTrxInf.getTrxDate();
		String originalCurrencyCode = tmsCrvatTrxInf.getOriginalCurrencyCode();
		Double originalCurrencyAmount = tmsCrvatTrxInf.getOriginalCurrencyAmount();
		String currencyCode = tmsCrvatTrxInf.getCurrencyCode();
		Double currencyAmount = tmsCrvatTrxInf.getCurrencyAmount();
		Double exchangeRate = tmsCrvatTrxInf.getExchangeRate();
		Double taxRate = tmsCrvatTrxInf.getTaxRate();
		String invoiceCategory = tmsCrvatTrxInf.getInvoiceCategory();
		String deptId = tmsCrvatTrxInf.getDeptId();
		String inventoryItemNumber = tmsCrvatTrxInf.getInventoryItemNumber();
		String inventoryItemDescripton = tmsCrvatTrxInf.getInventoryItemDescripton();
		String inventoryItemModels = tmsCrvatTrxInf.getInventoryItemModels();
		String uomCode = tmsCrvatTrxInf.getUomCode();
		Integer inventoryItemQty = tmsCrvatTrxInf.getInventoryItemQty();
		Double priceOfUnit = tmsCrvatTrxInf.getPriceOfUnit();
		Date rateDate = tmsCrvatTrxInf.getRateDate();
		String isAppointInvoice = tmsCrvatTrxInf.getIsAppointInvoice();
		String isAccount = tmsCrvatTrxInf.getIsAccount();
		String bizOrgCode = tmsCrvatTrxInf.getBizOrgCode();

		InvoiceTrxPool invoiceTrxPool = new InvoiceTrxPool();

		invoiceTrxPool.setLegalEntityCode(legalEntityCode);
		invoiceTrxPool.setLegalEntityName(legalEntityName);
		invoiceTrxPool.setSourceCode(StringUtils.trim(sourceCode));
		invoiceTrxPool.setTrxBatchNum(StringUtils.trim(trxBatchNum));
		invoiceTrxPool.setTrxNumber(StringUtils.trim(trxNumber));
		invoiceTrxPool.setTrxDate(trxDate);
		invoiceTrxPool.setOriginalCurrencyCode(StringUtils.trim(originalCurrencyCode));
		if(originalCurrencyAmount!=null){
			invoiceTrxPool.setOriginalCurrencyAmount(BigDecimal.valueOf(originalCurrencyAmount));
		}		
		invoiceTrxPool.setCurrencyAmount(BigDecimal.valueOf(currencyAmount));
		invoiceTrxPool.setCurrencyCode(StringUtils.trim(currencyCode));
		invoiceTrxPool.setExchangeRate(BigDecimal.valueOf(exchangeRate));
		invoiceTrxPool.setTaxRate(Double.valueOf(taxRate));
		invoiceTrxPool.setRateDate(rateDate);
		invoiceTrxPool.setInvoiceCategory(StringUtils.trim(invoiceCategory));
		invoiceTrxPool.setDeptId(deptId);
		invoiceTrxPool.setInventoryItemNumber(inventoryItemNumber);
		invoiceTrxPool.setInventoryItemDescripton(inventoryItemDescripton);
		invoiceTrxPool.setInventoryItmeModels(inventoryItemModels);
		invoiceTrxPool.setUomCode(uomCode);
		invoiceTrxPool.setInventoryItemQty(inventoryItemQty == null ? 1 : inventoryItemQty);
		invoiceTrxPool.setPriceOfUnit(priceOfUnit == null ? originalCurrencyAmount : priceOfUnit);
		invoiceTrxPool.setInvoiceType(invoicingType);
		invoiceTrxPool.setIsAccount(StringUtils.trim(isAccount));
		invoiceTrxPool.setIsAppointInvoice(StringUtils.trim(isAppointInvoice));
		invoiceTrxPool.setBizOrgCode(StringUtils.trim(bizOrgCode));
		invoiceTrxPool.setIsAccount("N");
		invoiceTrxPool.setArchiveBaseDate(new Date());
		return invoiceTrxPool;
	}

	private void processOrgAndLegalEntity(
			List<TmsMdLegalEntity> allLegalEntities,
			List<TmsMdOrgLegalEntity> allOrgLegalEntities,
			List<BaseOrg> allOrgs, TmsCrvatTrxInf tmsCrvatTrxInf) {
		//bo.wang 断点发现这里legalEntityCode有空的情况,导致机构全表查询
		String orgCode=tmsCrvatTrxInf.getOrgCode();
		AssertHelper.notEmpty_assert(orgCode, "orgCode不能为空");
		//机构代码是有的,纳税实体代码没有,所以必须从机构开始
		BaseOrg baseOrg=getBaseOrg(allOrgs, orgCode);
		if(baseOrg==null){
			baseOrg=new BaseOrg();
			baseOrg.setOrgCode(orgCode);
			baseOrg.setOrgName(tmsCrvatTrxInf.getOrgName());
			tmsMdLegalEntityDao.save(baseOrg);
			allOrgs.add(baseOrg);
		}
		String legalEntityType = tmsCrvatTrxInf.getLegalEntityType();
		if(StringUtils.isEmpty(legalEntityType)) {
			legalEntityType = "3";
		}	
		//根据纳税实体代码查找是否有纳税实体已经存在,中信中:纳税实体代码==机构代码
		TmsMdLegalEntity tmsMdLegalEntity = getTmsMdLegalEntity(allLegalEntities, orgCode);
		if(tmsMdLegalEntity==null){
			tmsMdLegalEntity = new TmsMdLegalEntity();
			tmsMdLegalEntity.setLegalEntityCode(orgCode);
			tmsMdLegalEntity.setLegalEntityName(tmsCrvatTrxInf.getLegalEntityName());
			tmsMdLegalEntity.setLegalEntityType(legalEntityType);
			tmsMdLegalEntityDao.save(tmsMdLegalEntity);
			allLegalEntities.add(tmsMdLegalEntity);
		}
		//纳税实体必须有机构,机构不一定有纳税实体,所以关系必须从纳税实体找
		TmsMdOrgLegalEntity tmsMdOrgLegalEntity=getTmsMdOrgLegalEntity(allOrgLegalEntities, tmsMdLegalEntity.getId());
			
		if(tmsMdOrgLegalEntity==null){
			tmsMdOrgLegalEntity = new TmsMdOrgLegalEntity();
			tmsMdOrgLegalEntity.setOrgId(baseOrg.getId());
			tmsMdOrgLegalEntity.setLegalEntityId(tmsMdLegalEntity.getId());
			tmsMdOrgLegalEntity.setId(IdGenerator.getUUID());
			tmsMdOrgLegalEntity.setLegalEntityType(legalEntityType);
			tmsMdOrgLegalEntity.setEnabledFlag("Y");
			tmsMdLegalEntityDao.save(tmsMdOrgLegalEntity);
			allOrgLegalEntities.add(tmsMdOrgLegalEntity);
		}else{
			tmsMdOrgLegalEntity.setOrgId(baseOrg.getId());
			tmsMdOrgLegalEntity.setLegalEntityId(tmsMdLegalEntity.getId());
			tmsMdLegalEntityDao.update(tmsMdOrgLegalEntity);
		}
	}
	TmsMdLegalEntity getTmsMdLegalEntity(List<TmsMdLegalEntity> allLegalEntities,String legalEntityCode){
		for(TmsMdLegalEntity tmsMdLegalEntity:allLegalEntities){
			if(legalEntityCode.equals(tmsMdLegalEntity.getLegalEntityCode())){
				return tmsMdLegalEntity;
			}
		}
		return null;
	}
	TmsMdOrgLegalEntity getTmsMdOrgLegalEntity(List<TmsMdOrgLegalEntity> allOrgLegalEntities,String legalId){
		for(TmsMdOrgLegalEntity tmsMdOrgLegalEntity:allOrgLegalEntities){
			if(legalId.equals(tmsMdOrgLegalEntity.getLegalEntityId())){
				return tmsMdOrgLegalEntity;
			}
		}
		return null;
	}
	BaseOrg getBaseOrg(List<BaseOrg> allOrgs,String orgCode){
		for(BaseOrg baseOrg:allOrgs){
			if(orgCode.equals(baseOrg.getOrgCode())){
				return baseOrg;
			}
		}
		return null;
	}
}
