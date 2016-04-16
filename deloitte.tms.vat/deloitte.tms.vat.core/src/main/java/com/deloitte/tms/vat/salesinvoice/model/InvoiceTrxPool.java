package com.deloitte.tms.vat.salesinvoice.model;

import javax.persistence.*;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.CascadeType;

import com.deloitte.tms.base.masterdata.model.Customer;
import com.deloitte.tms.base.masterdata.model.TmsMdLegalEntity;
import com.deloitte.tms.base.masterdata.model.TmsMdTaxTrxType;
import com.deloitte.tms.base.taxsetting.model.Items;
import com.deloitte.tms.pl.core.commons.annotation.ModelProperty;
import com.deloitte.tms.pl.core.commons.constant.TablePreDef;
import com.deloitte.tms.pl.core.hibernate.identifier.Ling2IdGenerator;
import com.deloitte.tms.pl.core.hibernate.identifier.Ling2UUIDGenerator;
import com.deloitte.tms.pl.core.model.impl.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TMS_CRVAT_TRX_POOL_ALL database table.
 * 
 */
@Entity
@Table(name = InvoiceTrxPool.TABLE)
@ModelProperty(comment = "销项税开票交易池")
public class InvoiceTrxPool extends BaseEntity {
	
	/**  
	 * serialVersionUID  
	 */  
	
	private static final long serialVersionUID = 1L;
	public static final String TABLE = TablePreDef.TMS_CRVAT_TRX+"POOL_ALL";
	public static final String SEQ = TABLE;

	@Id
	@GenericGenerator(name=SEQ+"_GENERATOR",strategy=Ling2UUIDGenerator.STRATEGY_NAME,parameters={@Parameter(name="pkColumnValue",value=SEQ)})
	@GeneratedValue(strategy = GenerationType.TABLE, generator = SEQ+"_GENERATOR")
	@Column(name = "CRVAT_TRX_POOL_ID",  length = 36)
	String id;
	
	@Column(name="ACCD_COMBINATION_CODE")
	private String accdCombinationCode;

	@Column(name="ACCD_COMBINATION_ID")
	private String accdCombinationId;

	@Column(name="ACCD_COMBINATION_NAME")
	private String accdCombinationName;

	@Column(name="ACCD_SEGMENT")
	private String accdSegment;

	@Temporal(TemporalType.DATE)
	@Column(name="ACCOUNT_DATE")
	private Date accountDate;

	@Column(name="ACCOUNT_RATE")
	private BigDecimal accountRate;
	
	@Column(name="BSN_COMBINATION_CODE")
	private String bsnCombinationCode;

	@Column(name="BSN_COMBINATION_ID")
	private String bsnCombinationId;

	@Column(name="BSN_COMBINATION_NAME")
	private String bsnCombinationName;

	@Column(name="BUSINESS_CATEGORIES")
	private String businessCategories;

	@Column(name="BANK_ACCOUNT_NUM")
	private String bankAccountNum;

	@Column(name="BANK_ACCOUNT_TYPE")
	private String bankAccountType;

	@Column(name="BANK_BRANCH_NAME")
	private String bankBranchName;

	@Column(name="CODE_COMBINATION")
	private String codeCombination;

	@Column(name="CODE_COMBINATION_NAME")
	private String codeCombinationName;

	@Column(name="CONTACT_NAME")
	private String contactName;

	@Column(name="CONTACT_PHONE")
	private String contactPhone;

	@Column(name="CURRENCY_AMOUNT")
	private BigDecimal currencyAmount;
	
	@Column(name="CURRENCY_AMOUNT_CR")
	private BigDecimal currencyAmountCr;

	@Column(name="CURRENCY_AMOUNT_DR")
	private BigDecimal currencyAmountDr;

	@Column(name="CURRENCY_CODE")
	private String currencyCode;
	
	@Column(name="CUST_BANK_ACCOUNT_NUM")
	private String custBankAccountNum;

	@Column(name="CUST_BANK_ACCOUNT_TYPE")
	private String custBankAccountType;

	@Column(name="CUST_BANK_BRANCH_NAME")
	private String custBankBranchName;

	@Column(name="CUST_CONTACT_NAME")
	private String custContactName;

	@Column(name="CUST_CONTACT_PHONE")
	private String custContactPhone;

	@Column(name="CUST_REGISTRATION_ADDRESS")
	private String custRegistrationAddress;

	@Column(name="CUST_REGISTRATION_CODE")
	private String custRegistrationCode;

	@Column(name="CUST_REGISTRATION_NUMBER")
	private String custRegistrationNumber;

	@Column(name="CUSTOMER_TYPE")
	private String customerType;

	@Column(name="DATA_ORG_ID")
	private String dataOrgId;
	
	@Column(name="DEPT_ID")
	private String deptId;

	@Temporal(TemporalType.DATE)
	@Column(name="END_DATE")
	private Date endDate;

	@Column(name="EXCHANGE_RATE")
	private BigDecimal exchangeRate;

	@Temporal(TemporalType.DATE)
	@Column(name="GL_DATE")
	private Date glDate;

	@Column(name="INVENTORY_ITEM_ID")
	private String inventoryItemId;

	@Column(name="INVENTORY_ITEM_NUMBER")
	private String inventoryItemNumber;

	@Column(name="INVOICE_CATEGORY")
	private String invoiceCategory;
	
	@Column(name="INVOICE_CURRENCY_CODE")
	private String invoiceCurrencyCode;

	@Column(name="INVOICING_TYPE")
	private String invoiceType;


	@Column(name="IS_ACCOUNT")
	private String isAccount;
	

	@Column(name="IS_TAX")
	private String isTax;

	@Column(name="LEGAL_ENTITY_CODE")
	private String legalEntityCode;

	@Column(name="LEGAL_ENTITY_ID")
	private String legalEntityId;
	@ManyToOne(fetch = FetchType.LAZY)
	@Cascade(CascadeType.REFRESH)
	@JoinColumn(name="LEGAL_ENTITY_ID",insertable=false,updatable=false,nullable=true)
	TmsMdLegalEntity tmsMdLegalEntity;

	@Column(name="LEGAL_ENTITY_NAME")
	private String legalEntityName;

	@Column(name="LEGAL_ENTITY_TYPE")
	private String legalEntityType;

	@Column(name="ORG_ID")
	private String orgId;

	/*@Column(name="OPERATION_ORG_CODE")
	private String operationOrgCode;*/

	@Column(name = "CUSTOMER_ID",length=36)
	@ModelProperty(comment = "客户ID")
	String customerId;
	@ManyToOne(fetch = FetchType.LAZY)
	@Cascade(CascadeType.REFRESH)
	@JoinColumn(name="CUSTOMER_ID",insertable=false,updatable=false,nullable=true)
	Customer customer;
	
	@Column(name="ORIGINAL_CURRENCY_AMOUNT")
	private BigDecimal originalCurrencyAmount;
	
	@Column(name="ORIGINAL_CURRENCY_AMOUNT_CR")
	private BigDecimal originalCurrencyAmountCr;

	@Column(name="ORIGINAL_CURRENCY_AMOUNT_DR")
	private BigDecimal originalCurrencyAmountDr;

	@Column(name="ORIGINAL_CURRENCY_CODE")
	private String originalCurrencyCode;

	@Column(name="PERIOD_NAME")
	private String periodName;

	@Temporal(TemporalType.DATE)
	@Column(name="RATE_DATE")
	private Date rateDate;

	@Column(name="RATE_TYPE")
	private String rateType;

	@Column(name="REGISTRATION_CODE")
	private String registrationCode;

	@Column(name="REGISTRATION_NUMBER")
	private String registrationNumber;

	@Column(name="SOURCE_CODE")
	private String sourceCode;

	@Column(name="SOURCE_DISTRIBUTION_ID")
	private String sourceDistributionId;

	@Column(name="SOURCE_TRX_DETAIL_TAX_LINE_ID")
	private String sourceTrxDetailTaxLineId;

	@Column(name="SOURCE_TRX_ID")
	private String sourceTrxId;

	@Column(name="SOURCE_TRX_LINE_ID")
	private String sourceTrxLineId;

	@Column(name="SOURCE_TRX_LINE_TYPE")
	private String sourceTrxLineType;

	@Column(name="SPIT_RULE_ID")
	private String spitRuleId;
	
	@Column(name="SPIT_RULE_CODE")
	private String spitRuleCode;

	@Column(name="START_DATE")
	private Date startDate;

	@Column(name="TAX_BASE_CODE")
	private String taxBaseCode;

	@Column(name="TAX_BASE_ID")
	private String taxBaseId;

	@Column(name="TAX_BASE_NAME")
	private String taxBaseName;
	
	@Column(name="TAX_CATEGORY_ID")
	private String taxCategoryId;
	
	@Column(name="TAX_ITEM_ID")
	private String taxItemId;
	@ManyToOne(fetch=FetchType.LAZY)
	@Cascade(CascadeType.REFRESH)
	@JoinColumn(name="TAX_ITEM_ID",insertable=false,updatable=false,nullable=true)
	private Items taxItems;

	@Column(name="TAX_RATE")
	private Double taxRate;

	@Column(name="TAX_SETTING_METHOD")
	private String taxSettingMethod;

	@Column(name="TAX_SETTING_REF")
	private String taxSettingRef;

	@Column(name="TAX_TRX_TYPE_ID")
	private String taxTrxTypeId;	
	@ManyToOne(fetch = FetchType.LAZY)
	@Cascade(CascadeType.REFRESH)
	@JoinColumn(name="TAX_TRX_TYPE_ID",insertable=false,updatable=false,nullable=true)
	TmsMdTaxTrxType tmsMdTaxTrxType;
	
	
	

	@Column(name="TRX_AFFIRM_ID")
	private String trxAffirmId;
	
	@Column(name="TRX_AFFIRM_SETTING_ID")
	private String trxAffirmSettingId;

	@Column(name="TRX_BATCH_NUM")
	private String trxBatchNum;

	@Column(name="TRX_BUSINESS_CATEGORY")
	private String trxBusinessCategory;

	@Column(name="TRX_BUSINESS_CATEGORY_NAME")
	private String trxBusinessCategoryName;
	
	@Column(name="TRX_CAT_BSN_STR_ID")
	private String trxCatBsnStrId;

	@Temporal(TemporalType.DATE)
	@Column(name="TRX_DATE")
	private Date trxDate;

	@Column(name="TRX_EVENT_ID")
	private String trxEventId;

	@Column(name="TRX_NUMBER")
	private String trxNumber;

	@Column(name="UOM_CODE")
	private String uomCode;

	@Column(name="UOM_CODE_DESCRIPTON")
	private String uomCodeDescripton;
	
	@Column(name = "INVENTORY_ITEM_DESCRIPTON",length=36)
	@ModelProperty(comment = "货物名称")
	String inventoryItemDescripton;
	
	@Column(name = "INVENTORY_ITEM_MODELS",length=36)
	@ModelProperty(comment = "规格型号")
	String inventoryItmeModels;
	
	@Column(name = "INVENTORY_ITEM_QTY",length=36)
	@ModelProperty(comment = "货物数量")
	Integer inventoryItemQty;
	
	@Column(name="IS_APPOINT_INVOICE")
	private String isAppointInvoice;

	@Column(name="PRICE_OF_UNIT")
	private Double priceOfUnit;
	
	@Column(name="EXCHANGE_AMOUNT")
	private BigDecimal exchangeAmount;
	
	public InvoiceTrxPool() {
	}
	
	
	public Double getPriceOfUnit() {
		return priceOfUnit;
	}


	public void setPriceOfUnit(Double priceOfUnit) {
		this.priceOfUnit = priceOfUnit;
	}


	public String getIsAppointInvoice() {
		return isAppointInvoice;
	}

	public void setIsAppointInvoice(String isAppointInvoice) {
		this.isAppointInvoice = isAppointInvoice;
	}



	public String getInventoryItemDescripton() {
		return inventoryItemDescripton;
	}

	public void setInventoryItemDescripton(String inventoryItemDescripton) {
		this.inventoryItemDescripton = inventoryItemDescripton;
	}

	public String getInventoryItmeModels() {
		return inventoryItmeModels;
	}

	public void setInventoryItmeModels(String inventoryItmeModels) {
		this.inventoryItmeModels = inventoryItmeModels;
	}


	
	public String getId() {
		return id;

	}
	public Integer getInventoryItemQty() {
		return inventoryItemQty;
	}

	public void setInventoryItemQty(Integer inventoryItemQty) {
		this.inventoryItemQty = inventoryItemQty;
	}
	public BigDecimal getAccountRate() {
		return accountRate;

	}

	public String getBankAccountNum() {
		return bankAccountNum;
	}

	public String getBankAccountType() {
		return bankAccountType;
	}

	public String getBankBranchName() {
		return bankBranchName;
	}

	public String getCodeCombination() {
		return codeCombination;
	}

	public String getCodeCombinationName() {
		return codeCombinationName;
	}

	public String getContactName() {
		return contactName;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public BigDecimal getCurrencyAmountCr() {
		return currencyAmountCr;
	}

	public BigDecimal getCurrencyAmountDr() {
		return currencyAmountDr;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public String getCustBankAccountNum() {
		return custBankAccountNum;
	}

	public String getCustBankAccountType() {
		return custBankAccountType;
	}

	public String getCustBankBranchName() {
		return custBankBranchName;
	}

	public String getCustContactName() {
		return custContactName;
	}

	public String getCustContactPhone() {
		return custContactPhone;
	}

	public String getCustRegistrationAddress() {
		return custRegistrationAddress;
	}

	public String getCustRegistrationCode() {
		return custRegistrationCode;
	}

	public String getCustRegistrationNumber() {
		return custRegistrationNumber;
	}

	public String getCustomerId() {
		return customerId;
	}

	public String getCustomerType() {
		return customerType;
	}

	public String getDeptId() {
		return deptId;
	}

	public Date getEndDate() {
		return endDate;
	}

	public BigDecimal getExchangeRate() {
		return exchangeRate;
	}

	public Date getGlDate() {
		return glDate;
	}

	public String getInventoryItemId() {
		return inventoryItemId;
	}

	public String getInventoryItemNumber() {
		return inventoryItemNumber;
	}

	public String getInvoiceCategory() {
		return invoiceCategory;
	}

	public String getInvoiceType() {
		return invoiceType;
	}

	
	public String getIsAccount() {
		return isAccount;
	}

	public String getLegalEntityCode() {
		return legalEntityCode;
	}

	public String getLegalEntityId() {
		return legalEntityId;
	}

	public String getLegalEntityName() {
		return legalEntityName;
	}

	public String getLegalEntityType() {
		return legalEntityType;
	}

	public String getOrgId() {
		return orgId;
	}

	public BigDecimal getOriginalCurrencyAmountCr() {
		return originalCurrencyAmountCr;
	}

	public BigDecimal getOriginalCurrencyAmountDr() {
		return originalCurrencyAmountDr;
	}

	public String getOriginalCurrencyCode() {
		return originalCurrencyCode;
	}

	public String getPeriodName() {
		return periodName;
	}

	public Date getRateDate() {
		return rateDate;
	}

	public String getRateType() {
		return rateType;
	}

	public String getRegistrationCode() {
		return registrationCode;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public String getSourceCode() {
		return sourceCode;
	}

	public String getSourceDistributionId() {
		return sourceDistributionId;
	}

	public String getSourceTrxDetailTaxLineId() {
		return sourceTrxDetailTaxLineId;
	}

	public String getSourceTrxId() {
		return sourceTrxId;
	}

	public String getSourceTrxLineId() {
		return sourceTrxLineId;
	}

	public String getSourceTrxLineType() {
		return sourceTrxLineType;
	}

	public String getSpitRuleId() {
		return spitRuleId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public String getTaxBaseCode() {
		return taxBaseCode;
	}

	public String getTaxBaseId() {
		return taxBaseId;
	}

	public String getTaxBaseName() {
		return taxBaseName;
	}

	public String getTaxSettingMethod() {
		return taxSettingMethod;
	}

	public String getTaxSettingRef() {
		return taxSettingRef;
	}

	public String getTaxTrxTypeId() {
		return taxTrxTypeId;
	}

	public String getTrxAffirmId() {
		return trxAffirmId;
	}

	public String getTrxBatchNum() {
		return trxBatchNum;
	}

	public String getTrxBusinessCategory() {
		return trxBusinessCategory;
	}

	public String getTrxBusinessCategoryName() {
		return trxBusinessCategoryName;
	}

	public Date getTrxDate() {
		return trxDate;
	}

	public String getTrxEventId() {
		return trxEventId;
	}

	public String getTrxNumber() {
		return trxNumber;
	}

	public String getUomCode() {
		return uomCode;
	}

	public String getUomCodeDescripton() {
		return uomCodeDescripton;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setAccountRate(BigDecimal accountRate) {
		this.accountRate = accountRate;
	}

	public void setBankAccountNum(String bankAccountNum) {
		this.bankAccountNum = bankAccountNum;
	}

	public void setBankAccountType(String bankAccountType) {
		this.bankAccountType = bankAccountType;
	}

	public void setBankBranchName(String bankBranchName) {
		this.bankBranchName = bankBranchName;
	}

	public void setCodeCombination(String codeCombination) {
		this.codeCombination = codeCombination;
	}

	public void setCodeCombinationName(String codeCombinationName) {
		this.codeCombinationName = codeCombinationName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public void setCurrencyAmountCr(BigDecimal currencyAmountCr) {
		this.currencyAmountCr = currencyAmountCr;
	}

	public void setCurrencyAmountDr(BigDecimal currencyAmountDr) {
		this.currencyAmountDr = currencyAmountDr;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public void setCustBankAccountNum(String custBankAccountNum) {
		this.custBankAccountNum = custBankAccountNum;
	}

	public void setCustBankAccountType(String custBankAccountType) {
		this.custBankAccountType = custBankAccountType;
	}

	public void setCustBankBranchName(String custBankBranchName) {
		this.custBankBranchName = custBankBranchName;
	}

	public void setCustContactName(String custContactName) {
		this.custContactName = custContactName;
	}

	public void setCustContactPhone(String custContactPhone) {
		this.custContactPhone = custContactPhone;
	}

	public void setCustRegistrationAddress(String custRegistrationAddress) {
		this.custRegistrationAddress = custRegistrationAddress;
	}

	public void setCustRegistrationCode(String custRegistrationCode) {
		this.custRegistrationCode = custRegistrationCode;
	}

	public void setCustRegistrationNumber(String custRegistrationNumber) {
		this.custRegistrationNumber = custRegistrationNumber;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void setExchangeRate(BigDecimal exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	public void setGlDate(Date glDate) {
		this.glDate = glDate;
	}

	public void setInventoryItemId(String inventoryItemId) {
		this.inventoryItemId = inventoryItemId;
	}

	public void setInventoryItemNumber(String inventoryItemNumber) {
		this.inventoryItemNumber = inventoryItemNumber;
	}

	public void setInvoiceCategory(String invoiceCategory) {
		this.invoiceCategory = invoiceCategory;
	}

	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}

	public void setIsAccount(String isAccount) {
		this.isAccount = isAccount;
	}

	public void setLegalEntityCode(String legalEntityCode) {
		this.legalEntityCode = legalEntityCode;
	}

	public void setLegalEntityId(String legalEntityId) {
		this.legalEntityId = legalEntityId;
	}

	public void setLegalEntityName(String legalEntityName) {
		this.legalEntityName = legalEntityName;
	}

	public void setLegalEntityType(String legalEntityType) {
		this.legalEntityType = legalEntityType;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public void setOriginalCurrencyAmountCr(BigDecimal originalCurrencyAmountCr) {
		this.originalCurrencyAmountCr = originalCurrencyAmountCr;
	}

	public void setOriginalCurrencyAmountDr(BigDecimal originalCurrencyAmountDr) {
		this.originalCurrencyAmountDr = originalCurrencyAmountDr;
	}

	public void setOriginalCurrencyCode(String originalCurrencyCode) {
		this.originalCurrencyCode = originalCurrencyCode;
	}

	public void setPeriodName(String periodName) {
		this.periodName = periodName;
	}

	public void setRateDate(Date rateDate) {
		this.rateDate = rateDate;
	}

	public void setRateType(String rateType) {
		this.rateType = rateType;
	}

	public void setRegistrationCode(String registrationCode) {
		this.registrationCode = registrationCode;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}

	public void setSourceDistributionId(String sourceDistributionId) {
		this.sourceDistributionId = sourceDistributionId;
	}

	public void setSourceTrxDetailTaxLineId(String sourceTrxDetailTaxLineId) {
		this.sourceTrxDetailTaxLineId = sourceTrxDetailTaxLineId;
	}

	public void setSourceTrxId(String sourceTrxId) {
		this.sourceTrxId = sourceTrxId;
	}

	public void setSourceTrxLineId(String sourceTrxLineId) {
		this.sourceTrxLineId = sourceTrxLineId;
	}

	public void setSourceTrxLineType(String sourceTrxLineType) {
		this.sourceTrxLineType = sourceTrxLineType;
	}

	public void setSpitRuleId(String spitRuleId) {
		this.spitRuleId = spitRuleId;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setTaxBaseCode(String taxBaseCode) {
		this.taxBaseCode = taxBaseCode;
	}

	public void setTaxBaseId(String taxBaseId) {
		this.taxBaseId = taxBaseId;
	}

	public void setTaxBaseName(String taxBaseName) {
		this.taxBaseName = taxBaseName;
	}

	public void setTaxSettingMethod(String taxSettingMethod) {
		this.taxSettingMethod = taxSettingMethod;
	}

	public void setTaxSettingRef(String taxSettingRef) {
		this.taxSettingRef = taxSettingRef;
	}

	public void setTaxTrxTypeId(String taxTrxTypeId) {
		this.taxTrxTypeId = taxTrxTypeId;
	}

	public void setTrxAffirmId(String trxAffirmId) {
		this.trxAffirmId = trxAffirmId;
	}

	public void setTrxBatchNum(String trxBatchNum) {
		this.trxBatchNum = trxBatchNum;
	}

	public void setTrxBusinessCategory(String trxBusinessCategory) {
		this.trxBusinessCategory = trxBusinessCategory;
	}

	public void setTrxBusinessCategoryName(String trxBusinessCategoryName) {
		this.trxBusinessCategoryName = trxBusinessCategoryName;
	}

	public void setTrxDate(Date trxDate) {
		this.trxDate = trxDate;
	}

	public void setTrxEventId(String trxEventId) {
		this.trxEventId = trxEventId;
	}

	public void setTrxNumber(String trxNumber) {
		this.trxNumber = trxNumber;
	}

	public void setUomCode(String uomCode) {
		this.uomCode = uomCode;
	}

	public void setUomCodeDescripton(String uomCodeDescripton) {
		this.uomCodeDescripton = uomCodeDescripton;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Double getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(Double taxRate) {
		this.taxRate = taxRate;
	}

	public BigDecimal getCurrencyAmount() {
		return currencyAmount;
	}

	public void setCurrencyAmount(BigDecimal currencyAmount) {
		this.currencyAmount = currencyAmount;
	}

	public BigDecimal getOriginalCurrencyAmount() {
		return originalCurrencyAmount;
	}

	public void setOriginalCurrencyAmount(BigDecimal originalCurrencyAmount) {
		this.originalCurrencyAmount = originalCurrencyAmount;
	}
	
	public TmsMdTaxTrxType getTmsMdTaxTrxType() {
		return tmsMdTaxTrxType;
	}

	public void setTmsMdTaxTrxType(TmsMdTaxTrxType tmsMdTaxTrxType) {
		this.tmsMdTaxTrxType = tmsMdTaxTrxType;
	}


	public BigDecimal getExchangeAmount() {
		return exchangeAmount;
	}


	public void setExchangeAmount(BigDecimal exchangeAmount) {
		this.exchangeAmount = exchangeAmount;
	}


	public String getAccdCombinationId() {
		return accdCombinationId;
	}


	public void setAccdCombinationId(String accdCombinationId) {
		this.accdCombinationId = accdCombinationId;
	}


	public String getAccdCombinationName() {
		return accdCombinationName;
	}


	public void setAccdCombinationName(String accdCombinationName) {
		this.accdCombinationName = accdCombinationName;
	}


	public String getAccdSegment() {
		return accdSegment;
	}


	public void setAccdSegment(String accdSegment) {
		this.accdSegment = accdSegment;
	}


	public Date getAccountDate() {
		return accountDate;
	}


	public void setAccountDate(Date accountDate) {
		this.accountDate = accountDate;
	}


	public String getBsnCombinationCode() {
		return bsnCombinationCode;
	}


	public void setBsnCombinationCode(String bsnCombinationCode) {
		this.bsnCombinationCode = bsnCombinationCode;
	}


	public String getBsnCombinationId() {
		return bsnCombinationId;
	}


	public void setBsnCombinationId(String bsnCombinationId) {
		this.bsnCombinationId = bsnCombinationId;
	}


	public String getBsnCombinationName() {
		return bsnCombinationName;
	}


	public void setBsnCombinationName(String bsnCombinationName) {
		this.bsnCombinationName = bsnCombinationName;
	}


	public String getBusinessCategories() {
		return businessCategories;
	}


	public void setBusinessCategories(String businessCategories) {
		this.businessCategories = businessCategories;
	}


	public String getDataOrgId() {
		return dataOrgId;
	}


	public void setDataOrgId(String dataOrgId) {
		this.dataOrgId = dataOrgId;
	}


	public String getInvoiceCurrencyCode() {
		return invoiceCurrencyCode;
	}


	public void setInvoiceCurrencyCode(String invoiceCurrencyCode) {
		this.invoiceCurrencyCode = invoiceCurrencyCode;
	}


	public String getIsTax() {
		return isTax;
	}


	public void setIsTax(String isTax) {
		this.isTax = isTax;
	}


	public String getSpitRuleCode() {
		return spitRuleCode;
	}


	public void setSpitRuleCode(String spitRuleCode) {
		this.spitRuleCode = spitRuleCode;
	}


	public String getTaxCategoryId() {
		return taxCategoryId;
	}


	public void setTaxCategoryId(String taxCategoryId) {
		this.taxCategoryId = taxCategoryId;
	}


	public String getTaxItemId() {
		return taxItemId;
	}


	public void setTaxItemId(String taxItemId) {
		this.taxItemId = taxItemId;
	}


	public String getTrxAffirmSettingId() {
		return trxAffirmSettingId;
	}


	public void setTrxAffirmSettingId(String trxAffirmSettingId) {
		this.trxAffirmSettingId = trxAffirmSettingId;
	}


	public String getTrxCatBsnStrId() {
		return trxCatBsnStrId;
	}


	public void setTrxCatBsnStrId(String trxCatBsnStrId) {
		this.trxCatBsnStrId = trxCatBsnStrId;
	}


	public String getAccdCombinationCode() {
		return accdCombinationCode;
	}


	public void setAccdCombinationCode(String accdCombinationCode) {
		this.accdCombinationCode = accdCombinationCode;
	}


	public Items getTaxItems() {
		return taxItems;
	}


	public void setTaxItems(Items taxItems) {
		this.taxItems = taxItems;
	}


	public TmsMdLegalEntity getTmsMdLegalEntity() {
		return tmsMdLegalEntity;
	}


	public void setTmsMdLegalEntity(TmsMdLegalEntity tmsMdLegalEntity) {
		this.tmsMdLegalEntity = tmsMdLegalEntity;
	}
	
	
	
	
	
	
	
	
}