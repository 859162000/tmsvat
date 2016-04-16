package com.deloitte.tms.vat.inf.taxinfo.model.invoiceprint.issue;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;

import com.deloitte.tms.vat.inf.taxinfo.aisino.constant.AisinoResponseCodeDef;
import com.deloitte.tms.vat.inf.taxinfo.annotation.AisinoSerializeProperty;

//@AisinoSerializeProperty(serializeName="refp")

public class InvoiceIssueResult {
	
	boolean issucess=false;
	
	@AisinoSerializeProperty(serializeName="RETCODE",length=100,des="错误代码",require=true)
	String erroCode;
	@AisinoSerializeProperty(serializeName="RETMSG",length=100,des="错误信息",require=true)
	String erroMsg;
	@AisinoSerializeProperty(serializeName="JE",des="金额")
	Double invoiceAmount;
	@AisinoSerializeProperty(serializeName="SE",des="税额")
	Double taxAmount;
	@AisinoSerializeProperty(serializeName="KPRQ",des="开票日期",format="yyyy-MM-dd")
	Date issueDate;
	@AisinoSerializeProperty(serializeName="FPDM",des="发票代码")
	String invoiceCode;
	@AisinoSerializeProperty(serializeName="FPHM",des="发票号码")
	String invoiceNo;
	@AisinoSerializeProperty(serializeName="SYZFPDM",des="上一张发票代码")
	String nextInvoiceCode;
	@AisinoSerializeProperty(serializeName="SYZFPHM",des="上一张发票号码")
	String nextInvoiceNo;
	@AisinoSerializeProperty(serializeName="SYZFPZL",des="上一张发票种类")
	String nextInvoiceType;
	/**
	 * 是否处理成功
	 * @param issucess
	 */
//	public void setIssucess(boolean issucess) {
//		this.issucess = issucess;
//	}
	/**
	 * issucess=ture时 错误代码
	 * @param erroCode
	 */
	public void setErroCode(String erroCode) {
		this.erroCode = erroCode;
	}
	/**
	 * issucess=ture时 错误信息
	 * @param erroCode
	 */
	public void setErroMsg(String erroMsg) {
		this.erroMsg = erroMsg;
	}
	/**
	 * 金额
	 * @param erroCode
	 */
	public void setInvoiceAmount(Double invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}
	/**
	 * 税额
	 * @param taxAmount
	 */
	public void setTaxAmount(Double taxAmount) {
		this.taxAmount = taxAmount;
	}
	/**
	 * 开具日期
	 * @param issueDate
	 */
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}
	/**
	 * 发票代码
	 * @param invoiceCode
	 */
	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}
	/**
	 * 发票号码
	 * @param invoiceNo
	 */
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	/**
	 * 下一张发票代码
	 * @param nextInvoiceCode
	 */
	public void setNextInvoiceCode(String nextInvoiceCode) {
		this.nextInvoiceCode = nextInvoiceCode;
	}
	/**
	 * 下一张发票号码
	 * @param nextInvoiceNo
	 */
	public void setNextInvoiceNo(String nextInvoiceNo) {
		this.nextInvoiceNo = nextInvoiceNo;
	}
	/**
	 * 下一张发票类别
	 * @param nextInvoiceType
	 */
	public void setNextInvoiceType(String nextInvoiceType) {
		this.nextInvoiceType = nextInvoiceType;
	}
	public boolean isIssucess() {
		return AisinoResponseCodeDef.PRINT_SITE_SUCESS.equals(erroCode);
	}
	@XmlElement(name="RETCODE")
	public String getErroCode() {
		return erroCode;
	}
	public String getErroMsg() {
		return erroMsg;
	}
	public Double getInvoiceAmount() {
		return invoiceAmount;
	}
	public Double getTaxAmount() {
		return taxAmount;
	}
	public Date getIssueDate() {
		return issueDate;
	}
	public String getInvoiceCode() {
		return invoiceCode;
	}
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public String getNextInvoiceCode() {
		return nextInvoiceCode;
	}
	public String getNextInvoiceNo() {
		return nextInvoiceNo;
	}
	public String getNextInvoiceType() {
		return nextInvoiceType;
	}
	
	
}
