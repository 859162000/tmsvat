package com.deloitte.tms.vat.inf.taxinfo.model.invoiceprint.print;

import com.deloitte.tms.vat.inf.taxinfo.annotation.AisinoSHSerializeProperty;
import com.deloitte.tms.vat.inf.taxinfo.annotation.AisinoSerializeProperty;

/**
 * 发票打印
 * @author bo.wang
 * @create 2016年4月12日 上午10:54:15
 */
public class InvoicePrint {
	
	String key;
	
	@AisinoSerializeProperty(serializeName="FPZL",length=2,des="发票种类,FPZLEnums,0-专用发票,2-普通发票",require=true)
	@AisinoSHSerializeProperty(serializeName="invkind",length=1,des="0-专用发票,2-普通发票",require=true,valueFormat=true)
	String invoiceType;
	@AisinoSerializeProperty(serializeName="FPDM",length=2,des="发票代码",require=true)
	@AisinoSHSerializeProperty(serializeName="Invcode",length=2,des="发票代码",require=true)
	String invoiceCode;
	@AisinoSerializeProperty(serializeName="FPHM",length=2,des="发票号码",require=true)
	@AisinoSHSerializeProperty(serializeName="Invnr",length=2,des="发票号码",require=true)
	String invoiceNo;
	
	/**
	 * 发票种类
	 * @param invoiceType
	 */
	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
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
	public String getInvoiceType() {
		return invoiceType;
	}
	public String getInvoiceCode() {
		return invoiceCode;
	}
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	
}
