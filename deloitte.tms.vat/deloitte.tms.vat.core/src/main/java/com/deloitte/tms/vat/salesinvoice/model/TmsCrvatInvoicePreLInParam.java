package com.deloitte.tms.vat.salesinvoice.model;

import java.math.BigDecimal;
import java.util.List;


public class TmsCrvatInvoicePreLInParam  extends TmsCrvatInvoicePreL {
	
	//交易流水信息
	InvoiceTrxPoolInParam  invoiceTrxPoolInParam ;
	
	private String invoiceCategory;
	
	private BigDecimal taxRate;
	
	private String trxSquence;
	
	private String trxDate;
	
	private String taxTrxTypeName;
	
	private String taxTrxTypeCode;
	
	private String orgCode;
	
	private String orgName;
	
	private String trxBatchNum;
	
	public String getTrxBatchNum() {
		return trxBatchNum;
	}

	public void setTrxBatchNum(String trxBatchNum) {
		this.trxBatchNum = trxBatchNum;
	}

	private List<TmsCrvatInvoicePreLInParam> invoicePreLList;

	public InvoiceTrxPoolInParam getInvoiceTrxPoolInParam() {
		return invoiceTrxPoolInParam;
	}

	public void setInvoiceTrxPoolInParam(InvoiceTrxPoolInParam invoiceTrxPoolInParam) {
		this.invoiceTrxPoolInParam = invoiceTrxPoolInParam;
	}

	public String getInvoiceCategory() {
		return invoiceCategory;
	}

	public void setInvoiceCategory(String invoiceCategory) {
		this.invoiceCategory = invoiceCategory;
	}

	public BigDecimal getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(BigDecimal taxRate) {
		this.taxRate = taxRate;
	}
	
	

	public String getTrxSquence() {
		return trxSquence;
	}

	public void setTrxSquence(String trxSquence) {
		this.trxSquence = trxSquence;
	}
	
	
	
	

	public String getTaxTrxTypeName() {
		return taxTrxTypeName;
	}

	public void setTaxTrxTypeName(String taxTrxTypeName) {
		this.taxTrxTypeName = taxTrxTypeName;
	}

	public String getTaxTrxTypeCode() {
		return taxTrxTypeCode;
	}

	public void setTaxTrxTypeCode(String taxTrxTypeCode) {
		this.taxTrxTypeCode = taxTrxTypeCode;
	}

	public String getTrxDate() {
		return trxDate;
	}

	public void setTrxDate(String trxDate) {
		this.trxDate = trxDate;
	}
	
	

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public List<TmsCrvatInvoicePreLInParam> getInvoicePreLList() {
		return invoicePreLList;
	}

	public void setInvoicePreLList(List<TmsCrvatInvoicePreLInParam> invoicePreLList) {
		this.invoicePreLList = invoicePreLList;
	}
	
	
	
}