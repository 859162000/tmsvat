package com.deloitte.tms.vat.inf.taxinfo.model.invoiceprint.print;

import com.deloitte.tms.vat.inf.taxinfo.aisino.constant.AisinoResponseCodeDef;
import com.deloitte.tms.vat.inf.taxinfo.annotation.AisinoSerializeProperty;

public class InvoicePrintResult {
	
	@AisinoSerializeProperty(serializeName="RETCODE",length=100,des="错误代码",require=true)
	String erroCode;
	@AisinoSerializeProperty(serializeName="RETMSG",length=100,des="错误信息",require=true)
	String erroMsg;
	@AisinoSerializeProperty(serializeName="FPZL",length=2,des="发票种类",require=true)
	String invoiceType;
	@AisinoSerializeProperty(serializeName="FPDM",length=2,des="发票代码",require=true)
	String invoiceCode;
	@AisinoSerializeProperty(serializeName="FPHM",length=2,des="发票号码",require=true)
	String invoiceNo;
	/**
	 * 是否成功
	 * @return
	 */
	public boolean isIssucess() {
		return AisinoResponseCodeDef.PRINT_SITE_SUCESS.equals(erroCode);
	}
	
	/**
	 * 错误代码
	 * @return
	 */
	public String getErroCode() {
		return erroCode;
	}
	/**
	 * 错误信息
	 * @return
	 */
	public String getErroMsg() {
		return erroMsg;
	}
	/**
	 * 发票种类
	 * @return
	 */
	public String getInvoiceType() {
		return invoiceType;
	}
	/**
	 * 发票代码
	 * @return
	 */
	public String getInvoiceCode() {
		return invoiceCode;
	}
	/**
	 * 
	 * @return
	 */
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setErroCode(String erroCode) {
		this.erroCode = erroCode;
	}
	public void setErroMsg(String erroMsg) {
		this.erroMsg = erroMsg;
	}
	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}
	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	
}
