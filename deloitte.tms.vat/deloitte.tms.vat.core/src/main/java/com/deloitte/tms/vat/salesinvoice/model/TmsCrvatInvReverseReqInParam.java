package com.deloitte.tms.vat.salesinvoice.model;

import java.math.BigDecimal;
import java.sql.Date;

public class TmsCrvatInvReverseReqInParam extends TmsCrvatInvReverseReq {
	
	private String printHId;//打印头Id

	private String oriInventoryInvoiceId;//蓝字库存Id
	
	private String reverseReqHId;//红字头Id
	
	private String category;//发票类型
	
	private BigDecimal version;//发票版本

	private String oriInvoiceCode;//蓝字发票代码

	private String oriInvoiceNumber;//蓝字发票号码
	
	private String printTerminalNumber;//打印终端编号
	
	private String customName;//购方名称
	
	private String customRegisterNumber;//购方证件号码
	
	private String customRegisterType;//购方证件类型
	
	private Date invoicePrintDate;//蓝字发票开具日期

	private BigDecimal vatNetValue;//蓝字发票净额
	
	private BigDecimal vatTaxValue;//蓝字发票税额
	
	private BigDecimal vatAmountValue;//蓝字发票价税合计
	
	public String getOriInvoiceNumber() {
		return oriInvoiceNumber;
	}

	public void setOriInvoiceNumber(String oriInvoiceNumber) {
		this.oriInvoiceNumber = oriInvoiceNumber;
	}
	
	public String getOriInventoryInvoiceId() {
		return oriInventoryInvoiceId;
	}

	public void setOriInventoryInvoiceId(String oriInventoryInvoiceId) {
		this.oriInventoryInvoiceId = oriInventoryInvoiceId;
	}
	
	public Date getInvoicePrintDate() {
		return invoicePrintDate;
	}

	public void setInvoicePrintDate(Date invoicePrintDate) {
		this.invoicePrintDate = invoicePrintDate;
	}
	
	public String getPrintHId() {
		return printHId;
	}

	public void setPrintHId(String printHId) {
		this.printHId = printHId;
	}

	public String getReverseReqHId() {
		return reverseReqHId;
	}

	public void setReverseReqHId(String reverseReqHId) {
		this.reverseReqHId = reverseReqHId;
	}



	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public BigDecimal getVersion() {
		return version;
	}

	public void setVersion(BigDecimal version) {
		this.version = version;
	}

	public String getOriInvoiceCode() {
		return oriInvoiceCode;
	}

	public void setOriInvoiceCode(String oriInvoiceCode) {
		this.oriInvoiceCode = oriInvoiceCode;
	}

	public String getPrintTerminalNumber() {
		return printTerminalNumber;
	}

	public void setPrintTerminalNumber(String printTerminalNumber) {
		this.printTerminalNumber = printTerminalNumber;
	}

	public String getCustomName() {
		return customName;
	}

	public void setCustomName(String customName) {
		this.customName = customName;
	}

	public String getCustomRegisterNumber() {
		return customRegisterNumber;
	}

	public void setCustomRegisterNumber(String customRegisterNumber) {
		this.customRegisterNumber = customRegisterNumber;
	}

	public String getCustomRegisterType() {
		return customRegisterType;
	}

	public void setCustomRegisterType(String customRegisterType) {
		this.customRegisterType = customRegisterType;
	}

	public BigDecimal getVatNetValue() {
		return vatNetValue;
	}

	public void setVatNetValue(BigDecimal vatNetValue) {
		this.vatNetValue = vatNetValue;
	}

	public BigDecimal getVatTaxValue() {
		return vatTaxValue;
	}

	public void setVatTaxValue(BigDecimal vatTaxValue) {
		this.vatTaxValue = vatTaxValue;
	}

	public BigDecimal getVatAmountValue() {
		return vatAmountValue;
	}

	public void setVatAmountValue(BigDecimal vatAmountValue) {
		this.vatAmountValue = vatAmountValue;
	}


	
	

	
	
}
