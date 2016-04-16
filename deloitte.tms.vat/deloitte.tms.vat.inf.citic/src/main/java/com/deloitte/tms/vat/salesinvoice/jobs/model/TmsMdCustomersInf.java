package com.deloitte.tms.vat.salesinvoice.jobs.model;


import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.deloitte.tms.pl.core.commons.annotation.ModelProperty;
import com.deloitte.tms.pl.core.hibernate.identifier.Ling2IdGenerator;
import com.deloitte.tms.pl.core.model.impl.BaseEntity;

import java.sql.Timestamp;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TMS_MD_CUSTOMERS_INF database table.
 * 
 */
@Entity
@Table(name=TmsMdCustomersInf.TABLE)
@ModelProperty(comment = "客户数据xml返回")
@NamedQuery(name="TmsMdCustomersInf.findAll", query="SELECT t FROM TmsMdCustomersInf t")
public class TmsMdCustomersInf extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String TABLE = "TMS_MD_CUSTOMERS_INF";
	public static final String SEQ = TABLE;
	
	@Id
	@GenericGenerator(name=SEQ+"_GENERATOR",strategy=Ling2IdGenerator.STRATEGY_NAME,parameters={@Parameter(name="pkColumnValue",value=SEQ)})
	@GeneratedValue(strategy = GenerationType.TABLE, generator = SEQ+"_GENERATOR")
	@Column(name = "INTERFACE_TRX_ID",  length = 36)
	String id;

	@Column(name="BANK_ACCOUNT_NUM")
	private String bankAccountNum;

	@Column(name="BANK_ACCOUNT_TYPE")
	private String bankAccountType;

	@Column(name="BANK_BRANCH_NAME")
	private String bankBranchName;

	@Column(name="BILLING_PERIOD")
	private String billingPeriod;

	@Column(name="BIZ_ORG_ID")
	private String bizOrgId;

	@Column(name="CONTACT_NAME")
	private String contactName;

	@Column(name="CONTACT_PHONE")
	private String contactPhone;

	@Column(name="CUST_REGISTRATION_ADDRESS")
	private String custRegistrationAddress;

	@Column(name="CUST_REGISTRATION_CODE")
	private String custRegistrationCode;

	
	@Column(name="CUST_REGISTRATION_DATE")
	private Date custRegistrationDate;

	@Column(name="CUST_REGISTRATION_NUMBER")
	private String custRegistrationNumber;

	@Column(name="CUSTOMER_ACCOUNT")
	private String customerAccount;

	@Column(name="CUSTOMER_ID")
	private String customerId;

	@Column(name="CUSTOMER_NAME")
	private String customerName;

	@Column(name="CUSTOMER_NUMBER")
	private String customerNumber;

	@Column(name="CUSTOMER_TYPE")
	private String customerType;

	@Column(name="DATA_ORG_ID")
	private String dataOrgId;

	@Column(name="DATA_OWENER_CODE")
	private String dataOwenerCode;

	@Column(name="ENABLED_FLAG")
	private String enabledFlag;

	
	@Column(name="END_DATE")
	private Date endDate;

	@Column(name="INTERFACE_TRX_DATE")
	private Date interfaceTrxDate;

	@Column(name="INTERFACE_TRX_FLAG")
	private String interfaceTrxFlag;

	@Column(name="INTERFACE_TRX_MSG")
	private String interfaceTrxMsg;

	@Column(name="INVOICE_MODE")
	private String invoiceMode;

	@Column(name="INVOICE_TYPE")
	private String invoiceType;

	@Column(name="IS_APPOINT_INVOICE")
	private String isAppointInvoice;

	@Column(name="IS_INVOICE_PROVIDING")
	private String isInvoiceProviding;

	@Column(name="LEGAL_ENTITY_TYPE")
	private String legalEntityType;

	@Column(name="ORG_CODE")
	private String orgCode;

	@Column(name="ORG_ID")
	private String orgId;

	@Column(name="ORG_NAME")
	private String orgName;

	@Column(name="PRINT_PERIOD_NAME")
	private String printPeriodName;

	@Column(name="RECIPIENT_ADDR")
	private String recipientAddr;

	@Column(name="RECIPIENT_COMP")
	private String recipientComp;

	@Column(name="RECIPIENT_NAME")
	private String recipientName;

	@Column(name="RECIPIENT_PHONE")
	private String recipientPhone;

	@Column(name="REQUEST_ID")
	private String requestId;

	@Column(name="SOURCE_CUSTOMER_CODE")
	private String sourceCustomerCode;

	@Column(name="SOURCE_CUSTOMER_ID")
	private String sourceCustomerId;

	@Column(name="SOURCE_CUSTOMER_NAME")
	private String sourceCustomerName;

	@Column(name="SOURCE_CUSTOMER_TYPE")
	private String sourceCustomerType;

	
	@Column(name="START_DATE")
	private Date startDate;

	@Column(name="ZIP_CODE")
	private String zipCode;

	public TmsMdCustomersInf() {
	}

	public String getBankAccountNum() {
		return this.bankAccountNum;
	}

	public void setBankAccountNum(String bankAccountNum) {
		this.bankAccountNum = bankAccountNum;
	}

	public String getBankAccountType() {
		return this.bankAccountType;
	}

	public void setBankAccountType(String bankAccountType) {
		this.bankAccountType = bankAccountType;
	}

	public String getBankBranchName() {
		return this.bankBranchName;
	}

	public void setBankBranchName(String bankBranchName) {
		this.bankBranchName = bankBranchName;
	}

	public String getBillingPeriod() {
		return this.billingPeriod;
	}

	public void setBillingPeriod(String billingPeriod) {
		this.billingPeriod = billingPeriod;
	}

	public String getBizOrgId() {
		return this.bizOrgId;
	}

	public void setBizOrgId(String bizOrgId) {
		this.bizOrgId = bizOrgId;
	}

	public String getContactName() {
		return this.contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactPhone() {
		return this.contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getCustRegistrationAddress() {
		return this.custRegistrationAddress;
	}

	public void setCustRegistrationAddress(String custRegistrationAddress) {
		this.custRegistrationAddress = custRegistrationAddress;
	}

	public String getCustRegistrationCode() {
		return this.custRegistrationCode;
	}

	public void setCustRegistrationCode(String custRegistrationCode) {
		this.custRegistrationCode = custRegistrationCode;
	}

	public Date getCustRegistrationDate() {
		return this.custRegistrationDate;
	}

	public void setCustRegistrationDate(Date custRegistrationDate) {
		this.custRegistrationDate = custRegistrationDate;
	}

	public String getCustRegistrationNumber() {
		return this.custRegistrationNumber;
	}

	public void setCustRegistrationNumber(String custRegistrationNumber) {
		this.custRegistrationNumber = custRegistrationNumber;
	}

	public String getCustomerAccount() {
		return this.customerAccount;
	}

	public void setCustomerAccount(String customerAccount) {
		this.customerAccount = customerAccount;
	}

	public String getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerNumber() {
		return this.customerNumber;
	}

	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}

	public String getCustomerType() {
		return this.customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public String getDataOrgId() {
		return this.dataOrgId;
	}

	public void setDataOrgId(String dataOrgId) {
		this.dataOrgId = dataOrgId;
	}

	public String getDataOwenerCode() {
		return this.dataOwenerCode;
	}

	public void setDataOwenerCode(String dataOwenerCode) {
		this.dataOwenerCode = dataOwenerCode;
	}

	public String getEnabledFlag() {
		return this.enabledFlag;
	}

	public void setEnabledFlag(String enabledFlag) {
		this.enabledFlag = enabledFlag;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getInterfaceTrxDate() {
		return interfaceTrxDate;
	}

	public void setInterfaceTrxDate(Date interfaceTrxDate) {
		this.interfaceTrxDate = interfaceTrxDate;
	}

	public String getInterfaceTrxFlag() {
		return this.interfaceTrxFlag;
	}

	public void setInterfaceTrxFlag(String interfaceTrxFlag) {
		this.interfaceTrxFlag = interfaceTrxFlag;
	}


	
	public void setId(String id) {
		this.id = id;
	}

	public String getInterfaceTrxMsg() {
		return this.interfaceTrxMsg;
	}

	public void setInterfaceTrxMsg(String interfaceTrxMsg) {
		this.interfaceTrxMsg = interfaceTrxMsg;
	}

	public String getInvoiceMode() {
		return this.invoiceMode;
	}

	public void setInvoiceMode(String invoiceMode) {
		this.invoiceMode = invoiceMode;
	}

	public String getInvoiceType() {
		return this.invoiceType;
	}

	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}

	public String getIsAppointInvoice() {
		return this.isAppointInvoice;
	}

	public void setIsAppointInvoice(String isAppointInvoice) {
		this.isAppointInvoice = isAppointInvoice;
	}

	public String getIsInvoiceProviding() {
		return this.isInvoiceProviding;
	}

	public void setIsInvoiceProviding(String isInvoiceProviding) {
		this.isInvoiceProviding = isInvoiceProviding;
	}

	public String getLegalEntityType() {
		return this.legalEntityType;
	}

	public void setLegalEntityType(String legalEntityType) {
		this.legalEntityType = legalEntityType;
	}

	public String getOrgCode() {
		return this.orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgId() {
		return this.orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return this.orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getPrintPeriodName() {
		return this.printPeriodName;
	}

	public void setPrintPeriodName(String printPeriodName) {
		this.printPeriodName = printPeriodName;
	}

	public String getRecipientAddr() {
		return this.recipientAddr;
	}

	public void setRecipientAddr(String recipientAddr) {
		this.recipientAddr = recipientAddr;
	}

	public String getRecipientComp() {
		return this.recipientComp;
	}

	public void setRecipientComp(String recipientComp) {
		this.recipientComp = recipientComp;
	}

	public String getRecipientName() {
		return this.recipientName;
	}

	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}

	public String getRecipientPhone() {
		return this.recipientPhone;
	}

	public void setRecipientPhone(String recipientPhone) {
		this.recipientPhone = recipientPhone;
	}

	public String getRequestId() {
		return this.requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getSourceCustomerCode() {
		return this.sourceCustomerCode;
	}

	public void setSourceCustomerCode(String sourceCustomerCode) {
		this.sourceCustomerCode = sourceCustomerCode;
	}

	public String getSourceCustomerId() {
		return this.sourceCustomerId;
	}

	public void setSourceCustomerId(String sourceCustomerId) {
		this.sourceCustomerId = sourceCustomerId;
	}

	public String getSourceCustomerName() {
		return this.sourceCustomerName;
	}

	public void setSourceCustomerName(String sourceCustomerName) {
		this.sourceCustomerName = sourceCustomerName;
	}

	public String getSourceCustomerType() {
		return this.sourceCustomerType;
	}

	public void setSourceCustomerType(String sourceCustomerType) {
		this.sourceCustomerType = sourceCustomerType;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getZipCode() {
		return this.zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	
	/**   
	 * @return  
	 * @see com.deloitte.tms.pl.core.model.IModelBase#getId()  
	 */
	
	@Override
	public Serializable getId() {
		// TODO Auto-generated method stub
		return null;
	}

}