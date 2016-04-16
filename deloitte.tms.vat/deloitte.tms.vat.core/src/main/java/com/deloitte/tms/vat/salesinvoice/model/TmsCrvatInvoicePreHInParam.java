package com.deloitte.tms.vat.salesinvoice.model;

import java.util.Collection;
import java.util.Date;

public class TmsCrvatInvoicePreHInParam extends TmsCrvatInvoicePreH{
	
	private String crvatInvoiceReqNumber;
	private String invoiceReqType;
	public String getCrvatInvoiceReqNumber() {
		return crvatInvoiceReqNumber;
	}
	public void setCrvatInvoiceReqNumber(String crvatInvoiceReqNumber) {
		this.crvatInvoiceReqNumber = crvatInvoiceReqNumber;
	}
	public String getInvoiceReqType() {
		return invoiceReqType;
	}
	public void setInvoiceReqType(String invoiceReqType) {
		this.invoiceReqType = invoiceReqType;
	}

}