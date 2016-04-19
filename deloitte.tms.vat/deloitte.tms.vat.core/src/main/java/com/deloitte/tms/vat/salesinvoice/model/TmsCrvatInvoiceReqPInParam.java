package com.deloitte.tms.vat.salesinvoice.model;

import java.util.Date;

public class TmsCrvatInvoiceReqPInParam {

	private String id;

	private String crvatInvoiceReqHId;

	InvoiceReqH invoiceReqH;

	private String invoicingType;

	private Long acctdAmountCr;

	private Long vatAmount;

	private Long invoiceAmount;

	private String sourceCode;

	private String taxTrxTypeId;

	private String legalEntityId;

	private String legalEntityCode;

	private String legalEntityName;

	private String registrationNumber;

	private String registrationCode;

	private String inventoryItemId;

	private String inventoryItemNumber;

	private String inventoryItemDescripton;

	private String inventoryItemModels;

	private String uomCode;

	private String uomCodeDescripton;
	
	private double taxRate;

	public double getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(double taxRate) {
		this.taxRate = taxRate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCrvatInvoiceReqHId() {
		return crvatInvoiceReqHId;
	}

	public void setCrvatInvoiceReqHId(String crvatInvoiceReqHId) {
		this.crvatInvoiceReqHId = crvatInvoiceReqHId;
	}

	public InvoiceReqH getInvoiceReqH() {
		return invoiceReqH;
	}

	public void setInvoiceReqH(InvoiceReqH invoiceReqH) {
		this.invoiceReqH = invoiceReqH;
	}

	public String getInvoicingType() {
		return invoicingType;
	}

	public void setInvoicingType(String invoicingType) {
		this.invoicingType = invoicingType;
	}

	public Long getAcctdAmountCr() {
		return acctdAmountCr;
	}

	public void setAcctdAmountCr(Long acctdAmountCr) {
		this.acctdAmountCr = acctdAmountCr;
	}

	public Long getVatAmount() {
		return vatAmount;
	}

	public void setVatAmount(Long vatAmount) {
		this.vatAmount = vatAmount;
	}

	public Long getInvoiceAmount() {
		return invoiceAmount;
	}

	public void setInvoiceAmount(Long invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}

	public String getSourceCode() {
		return sourceCode;
	}

	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}

	public String getTaxTrxTypeId() {
		return taxTrxTypeId;
	}

	public void setTaxTrxTypeId(String taxTrxTypeId) {
		this.taxTrxTypeId = taxTrxTypeId;
	}

	public String getLegalEntityId() {
		return legalEntityId;
	}

	public void setLegalEntityId(String legalEntityId) {
		this.legalEntityId = legalEntityId;
	}

	public String getLegalEntityCode() {
		return legalEntityCode;
	}

	public void setLegalEntityCode(String legalEntityCode) {
		this.legalEntityCode = legalEntityCode;
	}

	public String getLegalEntityName() {
		return legalEntityName;
	}

	public void setLegalEntityName(String legalEntityName) {
		this.legalEntityName = legalEntityName;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public String getRegistrationCode() {
		return registrationCode;
	}

	public void setRegistrationCode(String registrationCode) {
		this.registrationCode = registrationCode;
	}

	public String getInventoryItemId() {
		return inventoryItemId;
	}

	public void setInventoryItemId(String inventoryItemId) {
		this.inventoryItemId = inventoryItemId;
	}

	public String getInventoryItemNumber() {
		return inventoryItemNumber;
	}

	public void setInventoryItemNumber(String inventoryItemNumber) {
		this.inventoryItemNumber = inventoryItemNumber;
	}

	public String getInventoryItemDescripton() {
		return inventoryItemDescripton;
	}

	public void setInventoryItemDescripton(String inventoryItemDescripton) {
		this.inventoryItemDescripton = inventoryItemDescripton;
	}

	public String getInventoryItemModels() {
		return inventoryItemModels;
	}

	public void setInventoryItemModels(String inventoryItemModels) {
		this.inventoryItemModels = inventoryItemModels;
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

	public Long getInventoryItemQty() {
		return inventoryItemQty;
	}

	public void setInventoryItemQty(Long inventoryItemQty) {
		this.inventoryItemQty = inventoryItemQty;
	}

	public Long getPriceOfUnit() {
		return priceOfUnit;
	}

	public void setPriceOfUnit(Long priceOfUnit) {
		this.priceOfUnit = priceOfUnit;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	private Long inventoryItemQty;
	private Long priceOfUnit;
	private Date startDate;
	private Date endDate;
	private String orgId;
}
