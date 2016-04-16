package com.deloitte.tms.vat.model;

public class ReverseReqForm {
		
	private String customName;
	
	private String category;
	
	private String invoiceCode;
	
	private String invoiceNumber;
	
	private String amountValue;
	
	private String invoiceReverseReqReason;
	
	private String invoiceReverseHeaderNo;
	
	private String invoiceReverseReqDate;
	
	private String inventoryInvoiceId;
	
	public String getInventoryInvoiceId() {
		return inventoryInvoiceId;
	}

	public void setInventoryInvoiceId(String inventoryInvoiceId) {
		this.inventoryInvoiceId = inventoryInvoiceId;
	}

	public String getInvoiceReverseReqDate() {
		return invoiceReverseReqDate;
	}

	public void setInvoiceReverseReqDate(String invoiceReverseReqDate) {
		this.invoiceReverseReqDate = invoiceReverseReqDate;
	}

	private String isRepeatInvoice;
	
	public String getCustomName() {
		return customName;
	}

	public void setCustomName(String customName) {
		this.customName = customName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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

	public String getAmountValue() {
		return amountValue;
	}

	public void setAmountValue(String amountValue) {
		this.amountValue = amountValue;
	}

	public String getInvoiceReverseReqReason() {
		return invoiceReverseReqReason;
	}

	public void setInvoiceReverseReqReason(String invoiceReverseReqReason) {
		this.invoiceReverseReqReason = invoiceReverseReqReason;
	}

	public String getInvoiceReverseHeaderNo() {
		return invoiceReverseHeaderNo;
	}

	public void setInvoiceReverseHeaderNo(String invoiceReverseHeaderNo) {
		this.invoiceReverseHeaderNo = invoiceReverseHeaderNo;
	}

	public String getIsRepeatInvoice() {
		return isRepeatInvoice;
	}

	public void setIsRepeatInvoice(String isRepeatInvoice) {
		this.isRepeatInvoice = isRepeatInvoice;
	}

}
