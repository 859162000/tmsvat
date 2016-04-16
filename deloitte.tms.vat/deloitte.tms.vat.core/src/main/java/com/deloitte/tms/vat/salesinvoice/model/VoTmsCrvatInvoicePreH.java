package com.deloitte.tms.vat.salesinvoice.model;

import java.util.Date;

public class VoTmsCrvatInvoicePreH {
	
private String id;
	
	private String crvatInvoiceReqNumber;

	private String approvalDesc;

	private String approvalStatus;
	
	private String astatus;

	private String crvatInvoicePreNumber;

	private String crvatInvoiceReqHId;
	/**
	 * 购方证件类型
	 */
	private String custRegistrationCode;
	
	private String custRegistrationCodeStr;

	private String custRegistrationNumber;

	private String customerId;

	private String customerName;
	
	private String invoiceReqNum;
	
	private String requestOrg;
	
	private String acceptOrg;
	
	private String requestPerson;
	
	private String customerCode;
	
	private String customerIdentity;
	
	private String requestDate;
	
	private String invoiceAmount;
	
	private String acctdAmountCr;
	
	private String bankName;
	
	private String bankAccount;
	
	private String contactName;
	
	private String address;
	
	/**
	 * 受理人
	 */
	private String approvalBy;
	/**
	 * 受理日期
	 */
	private Date   approveDate; 

	private Date endDate;

	private String invoicePreDate;

	private String invoiceType;

	private String isExitsCustomer;
	
	private String operationOrgCode;

	

	private String legalEntityCode;

	private String legalEntityId;

	private String legalEntityName;

	private String prtLegalEntityCode;

	private String prtLegalEntityId;

	
	private String registrationCode;

	private String registrationNumber;

	private Date startDate;

	private Date submitDate;
	
	private String strSubmitDate;
	
	private String strPreDate;

	private String wfTaskId;

	private String customerNumber;
	
	private String contactPhone; 
	
	
	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}

	public String getApprovalBy() {
		return approvalBy;
	}

	public void setApprovalBy(String approvalBy) {
		this.approvalBy = approvalBy;
	}



	public Date getApproveDate() {
		return approveDate;
	}

	public void setApproveDate(Date approveDate) {
		this.approveDate = approveDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getApprovalDesc() {
		return approvalDesc;
	}

	public void setApprovalDesc(String approvalDesc) {
		this.approvalDesc = approvalDesc;
	}

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public String getCrvatInvoicePreNumber() {
		return crvatInvoicePreNumber;
	}

	public void setCrvatInvoicePreNumber(String crvatInvoicePreNumber) {
		this.crvatInvoicePreNumber = crvatInvoicePreNumber;
	}

	public String getCrvatInvoiceReqHId() {
		return crvatInvoiceReqHId;
	}

	public void setCrvatInvoiceReqHId(String crvatInvoiceReqHId) {
		this.crvatInvoiceReqHId = crvatInvoiceReqHId;
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

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	

	public String getInvoicePreDate() {
		return invoicePreDate;
	}

	public void setInvoicePreDate(String invoicePreDate) {
		this.invoicePreDate = invoicePreDate;
	}

	public String getStrSubmitDate() {
		return strSubmitDate;
	}

	public void setStrSubmitDate(String strSubmitDate) {
		this.strSubmitDate = strSubmitDate;
	}

	public String getInvoiceType() {
		return invoiceType;
	}

	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}

	public String getIsExitsCustomer() {
		return isExitsCustomer;
	}

	public void setIsExitsCustomer(String isExitsCustomer) {
		this.isExitsCustomer = isExitsCustomer;
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

	public String getPrtLegalEntityCode() {
		return prtLegalEntityCode;
	}

	public void setPrtLegalEntityCode(String prtLegalEntityCode) {
		this.prtLegalEntityCode = prtLegalEntityCode;
	}

	public String getPrtLegalEntityId() {
		return prtLegalEntityId;
	}

	public void setPrtLegalEntityId(String prtLegalEntityId) {
		this.prtLegalEntityId = prtLegalEntityId;
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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
	}

	public String getWfTaskId() {
		return wfTaskId;
	}

	public void setWfTaskId(String wfTaskId) {
		this.wfTaskId = wfTaskId;
	}

	public String getInvoiceReqNum() {
		return invoiceReqNum;
	}

	public void setInvoiceReqNum(String invoiceReqNum) {
		this.invoiceReqNum = invoiceReqNum;
	}

	public String getRequestOrg() {
		return requestOrg;
	}

	public void setRequestOrg(String requestOrg) {
		this.requestOrg = requestOrg;
	}
	
	

	public String getAcceptOrg() {
		return acceptOrg;
	}

	public void setAcceptOrg(String acceptOrg) {
		this.acceptOrg = acceptOrg;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getCustomerIdentity() {
		return customerIdentity;
	}

	public void setCustomerIdentity(String customerIdentity) {
		this.customerIdentity = customerIdentity;
	}

	public String getCrvatInvoiceReqNumber() {
		return crvatInvoiceReqNumber;
	}

	public void setCrvatInvoiceReqNumber(String crvatInvoiceReqNumber) {
		this.crvatInvoiceReqNumber = crvatInvoiceReqNumber;
	}

	public String getOperationOrgCode() {
		return operationOrgCode;
	}

	public void setOperationOrgCode(String operationOrgCode) {
		this.operationOrgCode = operationOrgCode;
	}

	public String getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(String requestDate) {
		this.requestDate = requestDate;
	}

	public String getInvoiceAmount() {
		return invoiceAmount;
	}

	public void setInvoiceAmount(String invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}

	public String getAcctdAmountCr() {
		return acctdAmountCr;
	}

	public void setAcctdAmountCr(String acctdAmountCr) {
		this.acctdAmountCr = acctdAmountCr;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAstatus() {
		return astatus;
	}

	public void setAstatus(String astatus) {
		this.astatus = astatus;
	}

	public String getRequestPerson() {
		return requestPerson;
	}

	public void setRequestPerson(String requestPerson) {
		this.requestPerson = requestPerson;
	}

	public String getCustRegistrationCodeStr() {
		return custRegistrationCodeStr;
	}

	public void setCustRegistrationCodeStr(String custRegistrationCodeStr) {
		this.custRegistrationCodeStr = custRegistrationCodeStr;
	}

	public String getStrPreDate() {
		return strPreDate;
	}

	public void setStrPreDate(String strPreDate) {
		this.strPreDate = strPreDate;
	}
	
	
	
	
	
	
	

}
