package com.deloitte.tms.vat.salesinvoice.model;

import java.util.Date;


public class InvoiceTrxDAbolishResult {
	private String id;
	private String invoiceCategory;
	private String invoiceLimitAmount;//发票限额
	private String invoiceCode;
	private String invoiceNumber;
	private String status;
	private String custRegistrationCode;//购方证件类型
	private String custRegistrationNumber;//购方证件号码
	private Date approvalDate;
	private String invoiceAbolishId;
	private String requestBy;
	private String abolishType;//作废类型
	private String approvalStatus;
	private String acctdAmountCR;//发票净额
	private String vatAmount;//发票税额
	private String invoiceAmount;//价税合计
	private String abolishReason;
	public String getAbolishReason() {
		return abolishReason;
	}
	public void setAbolishReason(String abolishReason) {
		this.abolishReason = abolishReason;
	}
	private Date invoicePrintDate;//开具日期

	public String getInvoiceAbolishId() {
		return invoiceAbolishId;
	}
	public void setInvoiceAbolishId(String invoiceAbolishId) {
		this.invoiceAbolishId = invoiceAbolishId;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getApprovalDate() {
		return approvalDate;
	}
	public void setApprovalDate(Date approvalDate) {
		this.approvalDate = approvalDate;
	}
	public void setInvoicePrintDate(Date invoicePrintDate) {
		this.invoicePrintDate = invoicePrintDate;
	}
	public String getInvoiceLimitAmount() {
		return invoiceLimitAmount;
	}
	public void setInvoiceLimitAmount(String invoiceLimitAmount) {
		this.invoiceLimitAmount = invoiceLimitAmount;
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
	public String getAcctdAmountCR() {
		return acctdAmountCR;
	}
	public void setAcctdAmountCR(String acctdAmountCR) {
		this.acctdAmountCR = acctdAmountCR;
	}
	public String getVatAmount() {
		return vatAmount;
	}
	public void setVatAmount(String vatAmount) {
		this.vatAmount = vatAmount;
	}
	public String getInvoiceAmount() {
		return invoiceAmount;
	}
	public void setInvoiceAmount(String invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}
	public Date getInvoicePrintDate() {
		return invoicePrintDate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getInvoiceCategory() {
		return invoiceCategory;
	}
	public void setInvoiceCategory(String invoiceCategory) {
		this.invoiceCategory = invoiceCategory;
	}
	public String getInvoiceCode() {
		return invoiceCode;
	}
	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}
	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	public String getRequestBy() {
		return requestBy;
	}
	public void setRequestBy(String requestBy) {
		this.requestBy = requestBy;
	}
	public String getAbolishType() {
		return abolishType;
	}
	public void setAbolishType(String abolishType) {
		this.abolishType = abolishType;
	}
	public String getApprovalStatus() {
		return approvalStatus;
	}
	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
}
