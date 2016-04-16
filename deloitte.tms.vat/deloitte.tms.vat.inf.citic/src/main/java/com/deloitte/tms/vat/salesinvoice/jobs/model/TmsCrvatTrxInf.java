package com.deloitte.tms.vat.salesinvoice.jobs.model;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.deloitte.tms.pl.core.commons.annotation.ModelProperty;
import com.deloitte.tms.pl.core.hibernate.identifier.Ling2IdGenerator;
import com.deloitte.tms.pl.core.hibernate.identifier.Ling2UUIDGenerator;
import com.deloitte.tms.pl.core.model.impl.BaseEntity;

import java.util.Date;


/**
 * The persistent class for the TMS_CRVAT_TRX_INF database table.
 * 
 */
@Entity
@Table(name = TmsCrvatTrxInf.TABLE)
@ModelProperty(comment = "销项税开票交易接口表")
public class TmsCrvatTrxInf extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String TABLE = "TMS_CRVAT_TRX_INF";
	public static final String SEQ = TABLE;
	
	@Id
	@GenericGenerator(name=SEQ+"_GENERATOR",strategy=Ling2UUIDGenerator.STRATEGY_NAME,parameters={@Parameter(name="pkColumnValue",value=SEQ)})
	@GeneratedValue(strategy = GenerationType.TABLE, generator = SEQ+"_GENERATOR")
	@Column(name = "INTERFACE_TRX_H_ID",  length = 36)
	String id;

	@Column(name="ACCOUNT_RATE")
	private  Double accountRate;
	
	@Column(name="CUSTOMER_ACCOUNT")
	private String customerAccount;
	
	@Column(name="APPOINT_INVOICE_ORG_CODE")
	private String appointInvoiceOrgCode;

	@Column(name="APPOINT_INVOICE_ORG_ID")
	private String appointInvoiceOrgId;

	@Column(name="APPOINT_INVOICE_ORG_NAME")
	private String appointInvoiceOrgName;

	@Column(name="AUTOREVERSE_FLAG")
	private String autoreverseFlag;

	@Column(name="CODE_COMBINATION")
	private String codeCombination;

	@Column(name="CODE_COMBINATION_NAME")
	private String codeCombinationName;

	@Column(name="CURRENCY_AMOUNT_CR")
	private Double currencyAmountCr;

	@Column(name="CURRENCY_AMOUNT_DR")
	private Double currencyAmountDr;
	
	@Column(name="CURRENCY_AMOUNT")
	private Double currencyAmount;

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

	@Column(name="CUSTOMER_ID")
	private String customerId;

	@Column(name="CUSTOMER_TYPE")
	private String customerType;

	@Column(name="DEPT_ID")
	private String deptId;

	@Temporal(TemporalType.DATE)
	@Column(name="END_DATE")
	private Date endDate;

	@Column(name="EXCHANGE_RATE")
	private Double exchangeRate;

	@Temporal(TemporalType.DATE)
	@Column(name="GL_DATE")
	private Date glDate;

	@Column(name="INTERFACE_TRX_DATE")
	private Date interfaceTrxDate;

	@Column(name="INTERFACE_TRX_FLAG")
	private String interfaceTrxFlag;

	@Column(name="INTERFACE_TRX_MSG")
	private String interfaceTrxMsg;

	@Column(name="INVENTORY_ITEM_DESCRIPTON")
	private String inventoryItemDescripton;

	@Column(name="INVENTORY_ITEM_ID")
	private String inventoryItemId;

	@Column(name="INVENTORY_ITEM_MODELS")
	private String inventoryItemModels;

	@Column(name="INVENTORY_ITEM_NUMBER")
	private String inventoryItemNumber;

	@Column(name="INVENTORY_ITEM_QTY")
	private Integer inventoryItemQty;

	@Column(name="INVOICE_CATEGORY")
	private String invoiceCategory;

	@Column(name="INVOICING_TYPE")
	private String invoicingType;

	@Column(name="IS_ACCOUNT")
	private String isAccount;

	@Column(name="IS_APPOINT_INVOICE")
	private String isAppointInvoice;

	@Column(name="LEGAL_ENTITY_CODE")
	private String legalEntityCode;

	@Column(name="LEGAL_ENTITY_ID")
	private String legalEntityId;

	@Column(name="LEGAL_ENTITY_NAME")
	private String legalEntityName;

	@Column(name="LEGAL_ENTITY_TYPE")
	private String legalEntityType;

	@Column(name="ORG_CODE")
	private String orgCode;

	@Column(name="ORG_ID")
	private String orgId;

	@Column(name="ORG_NAME")
	private String orgName;

	@Column(name="ORIGINAL_CURRENCY_AMOUNT")
	private  Double originalCurrencyAmount;
	
	@Column(name="ORIGINAL_CURRENCY_AMOUNT_CR")
	private  Double originalCurrencyAmountCr;

	@Column(name="ORIGINAL_CURRENCY_AMOUNT_DR")
	private Double originalCurrencyAmountDr;

	@Column(name="ORIGINAL_CURRENCY_CODE")
	private String originalCurrencyCode;

	@Column(name="PERIOD_NAME")
	private String periodName;

	@Column(name="PRICE_OF_UNIT")
	private Double priceOfUnit;

	@Temporal(TemporalType.DATE)
	@Column(name="RATE_DATE")
	private Date rateDate;

	@Column(name="RATE_TYPE")
	private String rateType;

	@Column(name="REGISTRATION_CODE")
	private String registrationCode;

	@Column(name="REGISTRATION_NUMBER")
	private String registrationNumber;

	@Column(name="REQUEST_ID")
	private String requestId;

	@Column(name="REVERSAL_TRX_NUMBER")
	private String reversalTrxNumber;

	@Column(name="REVERSE_TYPE_CODE")
	private String reverseTypeCode;

	@Column(name="REVERSE_TYPE_NAME")
	private String reverseTypeName;

	@Column(name="REVERSAL_DATE")
	private Date reversalDate;
	
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

	@Temporal(TemporalType.DATE)
	@Column(name="START_DATE")
	private Date startDate;

	@Column(name="TAX_BASE_CODE")
	private String taxBaseCode;

	@Column(name="TAX_BASE_ID")
	private String taxBaseId;

	@Column(name="TAX_BASE_NAME")
	private String taxBaseName;

	@Column(name="TAX_RATE")
	private  Double taxRate;

	@Column(name="TAX_SETTING_METHOD")
	private String taxSettingMethod;

	@Column(name="TAX_TRX_TYPE_ID")
	private String taxTrxTypeId;

	@Column(name="TRX_BATCH_NUM")
	private String trxBatchNum;

	@Column(name="TRX_BUSINESS_CATEGORY")
	private String trxBusinessCategory;

	@Column(name="TRX_BUSINESS_CATEGORY_NAME")
	private String trxBusinessCategoryName;

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
	
	@Column(name="RECIPIENT_NAME")
	private String recipientName;
	
	@Column(name="RECIPIENT_COMP")
	private String recipientComp;
	
	@Column(name="RECIPIENT_ADDR")
	private String recipientAddr;
	
	@Column(name="RECIPIENT_PHONE")
	private String recipientPhone;
	
	@Column(name="PRINT_PERIOD_NAME")
	private String printPeriodName;
	
	@Column(name="CUSTOMER_NUMBER")
	private String customerNumber;
	
	@Column(name="CUSTOMER_NAME")
	private String customerName;
	
	@Column(name="CUSTOMER_ACCOUNT_LE_CODE")
	private String customerAccountLeCode;

	public TmsCrvatTrxInf() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Double getAccountRate() {
		return accountRate;
	}

	public void setAccountRate(Double accountRate) {
		this.accountRate = accountRate;
	}

	public String getAppointInvoiceOrgCode() {
		return appointInvoiceOrgCode;
	}

	public void setAppointInvoiceOrgCode(String appointInvoiceOrgCode) {
		this.appointInvoiceOrgCode = appointInvoiceOrgCode;
	}

	public String getAppointInvoiceOrgId() {
		return appointInvoiceOrgId;
	}

	public void setAppointInvoiceOrgId(String appointInvoiceOrgId) {
		this.appointInvoiceOrgId = appointInvoiceOrgId;
	}

	public String getAppointInvoiceOrgName() {
		return appointInvoiceOrgName;
	}

	public void setAppointInvoiceOrgName(String appointInvoiceOrgName) {
		this.appointInvoiceOrgName = appointInvoiceOrgName;
	}

	public String getAutoreverseFlag() {
		return autoreverseFlag;
	}

	public void setAutoreverseFlag(String autoreverseFlag) {
		this.autoreverseFlag = autoreverseFlag;
	}

	public String getCodeCombination() {
		return codeCombination;
	}

	public void setCodeCombination(String codeCombination) {
		this.codeCombination = codeCombination;
	}

	public String getCodeCombinationName() {
		return codeCombinationName;
	}

	public void setCodeCombinationName(String codeCombinationName) {
		this.codeCombinationName = codeCombinationName;
	}

	public Double getCurrencyAmountCr() {
		return currencyAmountCr;
	}

	public void setCurrencyAmountCr(Double currencyAmountCr) {
		this.currencyAmountCr = currencyAmountCr;
	}

	public Double getCurrencyAmountDr() {
		return currencyAmountDr;
	}

	public void setCurrencyAmountDr(Double currencyAmountDr) {
		this.currencyAmountDr = currencyAmountDr;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getCustBankAccountNum() {
		return custBankAccountNum;
	}

	public void setCustBankAccountNum(String custBankAccountNum) {
		this.custBankAccountNum = custBankAccountNum;
	}

	public String getCustBankAccountType() {
		return custBankAccountType;
	}

	public void setCustBankAccountType(String custBankAccountType) {
		this.custBankAccountType = custBankAccountType;
	}

	public String getCustBankBranchName() {
		return custBankBranchName;
	}

	public void setCustBankBranchName(String custBankBranchName) {
		this.custBankBranchName = custBankBranchName;
	}

	public String getCustContactName() {
		return custContactName;
	}

	public void setCustContactName(String custContactName) {
		this.custContactName = custContactName;
	}

	public String getCustContactPhone() {
		return custContactPhone;
	}

	public void setCustContactPhone(String custContactPhone) {
		this.custContactPhone = custContactPhone;
	}

	public String getCustRegistrationAddress() {
		return custRegistrationAddress;
	}

	public void setCustRegistrationAddress(String custRegistrationAddress) {
		this.custRegistrationAddress = custRegistrationAddress;
	}

	public String getCustRegistrationCode() {
		return custRegistrationCode;
	}

	public void setCustRegistrationCode(String custRegistrationCode) {
		this.custRegistrationCode = custRegistrationCode;
	}

	public String getCustRegistrationNumber() {
		return custRegistrationNumber;
	}

	public void setCustRegistrationNumber(String custRegistrationNumber) {
		this.custRegistrationNumber = custRegistrationNumber;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Double getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(Double exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	public Date getGlDate() {
		return glDate;
	}

	public void setGlDate(Date glDate) {
		this.glDate = glDate;
	}

	public Date getInterfaceTrxDate() {
		return interfaceTrxDate;
	}

	public void setInterfaceTrxDate(Date interfaceTrxDate) {
		this.interfaceTrxDate = interfaceTrxDate;
	}

	public String getInterfaceTrxFlag() {
		return interfaceTrxFlag;
	}

	public void setInterfaceTrxFlag(String interfaceTrxFlag) {
		this.interfaceTrxFlag = interfaceTrxFlag;
	}

	public String getInterfaceTrxMsg() {
		return interfaceTrxMsg;
	}

	public void setInterfaceTrxMsg(String interfaceTrxMsg) {
		this.interfaceTrxMsg = interfaceTrxMsg;
	}

	public String getInventoryItemDescripton() {
		return inventoryItemDescripton;
	}

	public void setInventoryItemDescripton(String inventoryItemDescripton) {
		this.inventoryItemDescripton = inventoryItemDescripton;
	}

	public String getInventoryItemId() {
		return inventoryItemId;
	}

	public void setInventoryItemId(String inventoryItemId) {
		this.inventoryItemId = inventoryItemId;
	}

	public String getInventoryItemModels() {
		return inventoryItemModels;
	}

	public void setInventoryItemModels(String inventoryItemModels) {
		this.inventoryItemModels = inventoryItemModels;
	}

	public String getInventoryItemNumber() {
		return inventoryItemNumber;
	}

	public void setInventoryItemNumber(String inventoryItemNumber) {
		this.inventoryItemNumber = inventoryItemNumber;
	}

	public Integer getInventoryItemQty() {
		return inventoryItemQty;
	}

	public void setInventoryItemQty(Integer inventoryItemQty) {
		this.inventoryItemQty = inventoryItemQty;
	}

	public String getInvoiceCategory() {
		return invoiceCategory;
	}

	public void setInvoiceCategory(String invoiceCategory) {
		this.invoiceCategory = invoiceCategory;
	}

	public String getInvoicingType() {
		return invoicingType;
	}

	public void setInvoicingType(String invoicingType) {
		this.invoicingType = invoicingType;
	}

	public String getIsAccount() {
		return isAccount;
	}

	public void setIsAccount(String isAccount) {
		this.isAccount = isAccount;
	}

	public String getIsAppointInvoice() {
		return isAppointInvoice;
	}

	public void setIsAppointInvoice(String isAppointInvoice) {
		this.isAppointInvoice = isAppointInvoice;
	}

	public String getLegalEntityCode() {
		return legalEntityCode;
	}

	public void setLegalEntityCode(String legalEntityCode) {
		this.legalEntityCode = legalEntityCode;
	}

	public String getLegalEntityId() {
		return legalEntityId;
	}

	public void setLegalEntityId(String legalEntityId) {
		this.legalEntityId = legalEntityId;
	}

	public String getLegalEntityName() {
		return legalEntityName;
	}

	public void setLegalEntityName(String legalEntityName) {
		this.legalEntityName = legalEntityName;
	}

	public String getLegalEntityType() {
		return legalEntityType;
	}

	public void setLegalEntityType(String legalEntityType) {
		this.legalEntityType = legalEntityType;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Double getOriginalCurrencyAmountCr() {
		return originalCurrencyAmountCr;
	}

	public void setOriginalCurrencyAmountCr(Double originalCurrencyAmountCr) {
		this.originalCurrencyAmountCr = originalCurrencyAmountCr;
	}

	public Double getOriginalCurrencyAmountDr() {
		return originalCurrencyAmountDr;
	}

	public void setOriginalCurrencyAmountDr(Double originalCurrencyAmountDr) {
		this.originalCurrencyAmountDr = originalCurrencyAmountDr;
	}

	public String getOriginalCurrencyCode() {
		return originalCurrencyCode;
	}

	public void setOriginalCurrencyCode(String originalCurrencyCode) {
		this.originalCurrencyCode = originalCurrencyCode;
	}

	public String getPeriodName() {
		return periodName;
	}

	public void setPeriodName(String periodName) {
		this.periodName = periodName;
	}

	public Double getPriceOfUnit() {
		return priceOfUnit;
	}

	public void setPriceOfUnit(Double priceOfUnit) {
		this.priceOfUnit = priceOfUnit;
	}

	public Date getRateDate() {
		return rateDate;
	}

	public void setRateDate(Date rateDate) {
		this.rateDate = rateDate;
	}

	public String getRateType() {
		return rateType;
	}

	public void setRateType(String rateType) {
		this.rateType = rateType;
	}

	public String getRegistrationCode() {
		return registrationCode;
	}

	public void setRegistrationCode(String registrationCode) {
		this.registrationCode = registrationCode;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getReversalTrxNumber() {
		return reversalTrxNumber;
	}

	public void setReversalTrxNumber(String reversalTrxNumber) {
		this.reversalTrxNumber = reversalTrxNumber;
	}

	public String getReverseTypeCode() {
		return reverseTypeCode;
	}

	public void setReverseTypeCode(String reverseTypeCode) {
		this.reverseTypeCode = reverseTypeCode;
	}

	public String getReverseTypeName() {
		return reverseTypeName;
	}

	public void setReverseTypeName(String reverseTypeName) {
		this.reverseTypeName = reverseTypeName;
	}

	public String getSourceCode() {
		return sourceCode;
	}

	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}

	public String getSourceDistributionId() {
		return sourceDistributionId;
	}

	public void setSourceDistributionId(String sourceDistributionId) {
		this.sourceDistributionId = sourceDistributionId;
	}

	public String getSourceTrxDetailTaxLineId() {
		return sourceTrxDetailTaxLineId;
	}

	public void setSourceTrxDetailTaxLineId(String sourceTrxDetailTaxLineId) {
		this.sourceTrxDetailTaxLineId = sourceTrxDetailTaxLineId;
	}

	public String getSourceTrxId() {
		return sourceTrxId;
	}

	public void setSourceTrxId(String sourceTrxId) {
		this.sourceTrxId = sourceTrxId;
	}

	public String getSourceTrxLineId() {
		return sourceTrxLineId;
	}

	public void setSourceTrxLineId(String sourceTrxLineId) {
		this.sourceTrxLineId = sourceTrxLineId;
	}

	public String getSourceTrxLineType() {
		return sourceTrxLineType;
	}

	public void setSourceTrxLineType(String sourceTrxLineType) {
		this.sourceTrxLineType = sourceTrxLineType;
	}

	public String getSpitRuleId() {
		return spitRuleId;
	}

	public void setSpitRuleId(String spitRuleId) {
		this.spitRuleId = spitRuleId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getTaxBaseCode() {
		return taxBaseCode;
	}

	public void setTaxBaseCode(String taxBaseCode) {
		this.taxBaseCode = taxBaseCode;
	}

	public String getTaxBaseId() {
		return taxBaseId;
	}

	public void setTaxBaseId(String taxBaseId) {
		this.taxBaseId = taxBaseId;
	}

	public String getTaxBaseName() {
		return taxBaseName;
	}

	public void setTaxBaseName(String taxBaseName) {
		this.taxBaseName = taxBaseName;
	}

	public Double getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(Double taxRate) {
		this.taxRate = taxRate;
	}

	public String getTaxSettingMethod() {
		return taxSettingMethod;
	}

	public void setTaxSettingMethod(String taxSettingMethod) {
		this.taxSettingMethod = taxSettingMethod;
	}

	public String getTaxTrxTypeId() {
		return taxTrxTypeId;
	}

	public void setTaxTrxTypeId(String taxTrxTypeId) {
		this.taxTrxTypeId = taxTrxTypeId;
	}

	public String getTrxBatchNum() {
		return trxBatchNum;
	}

	public void setTrxBatchNum(String trxBatchNum) {
		this.trxBatchNum = trxBatchNum;
	}

	public String getTrxBusinessCategory() {
		return trxBusinessCategory;
	}

	public void setTrxBusinessCategory(String trxBusinessCategory) {
		this.trxBusinessCategory = trxBusinessCategory;
	}

	public String getTrxBusinessCategoryName() {
		return trxBusinessCategoryName;
	}

	public void setTrxBusinessCategoryName(String trxBusinessCategoryName) {
		this.trxBusinessCategoryName = trxBusinessCategoryName;
	}

	public Date getTrxDate() {
		return trxDate;
	}

	public void setTrxDate(Date trxDate) {
		this.trxDate = trxDate;
	}

	public String getTrxEventId() {
		return trxEventId;
	}

	public void setTrxEventId(String trxEventId) {
		this.trxEventId = trxEventId;
	}

	public String getTrxNumber() {
		return trxNumber;
	}

	public void setTrxNumber(String trxNumber) {
		this.trxNumber = trxNumber;
	}

	public String getUomCode() {
		return uomCode;
	}

	public void setUomCode(String uomCode) {
		this.uomCode = uomCode;
	}

	public String getUomCodeDescripton() {
		return uomCodeDescripton;
	}

	public void setUomCodeDescripton(String uomCodeDescripton) {
		this.uomCodeDescripton = uomCodeDescripton;
	}

	public Date getReversalDate() {
		return reversalDate;
	}

	public void setReversalDate(Date reversalDate) {
		this.reversalDate = reversalDate;
	}

	public String getCustomerAccount() {
		return customerAccount;
	}

	public void setCustomerAccount(String customerAccount) {
		this.customerAccount = customerAccount;
	}

	public String getPrintPeriodName() {
		return printPeriodName;
	}

	public void setPrintPeriodName(String printPeriodName) {
		this.printPeriodName = printPeriodName;
	}

	public String getRecipientName() {
		return recipientName;
	}

	public String getRecipientComp() {
		return recipientComp;
	}

	public String getRecipientAddr() {
		return recipientAddr;
	}

	public String getRecipientPhone() {
		return recipientPhone;
	}

	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}

	public void setRecipientComp(String recipientComp) {
		this.recipientComp = recipientComp;
	}

	public void setRecipientAddr(String recipientAddr) {
		this.recipientAddr = recipientAddr;
	}

	public void setRecipientPhone(String recipientPhone) {
		this.recipientPhone = recipientPhone;
	}

	public String getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Double getCurrencyAmount() {
		return currencyAmount;
	}

	public void setCurrencyAmount(Double currencyAmount) {
		this.currencyAmount = currencyAmount;
	}

	public Double getOriginalCurrencyAmount() {
		return originalCurrencyAmount;
	}

	public void setOriginalCurrencyAmount(Double originalCurrencyAmount) {
		this.originalCurrencyAmount = originalCurrencyAmount;
	}

	
	public String getCustomerAccountLeCode() {
		return customerAccountLeCode;
	}

	public void setCustomerAccountLeCode(String customerAccountLeCode) {
		this.customerAccountLeCode = customerAccountLeCode;
	}
	
}