package com.deloitte.tms.vat.inf.taxinfo.model.invoiceserver.returnInvoice;

import com.deloitte.tms.vat.inf.taxinfo.annotation.AisinoSerializeProperty;
import com.deloitte.tms.vat.inf.taxinfo.model.RequestBase;

public class ReturnInvoiceRequest extends RequestBase{
	
	@AisinoSerializeProperty(serializeName="NSRSBH",length=20,des="纳税人识别号")
	String taxPayerNo;
	@AisinoSerializeProperty(serializeName="KPFWQH",length=10,des="开票服务器号",require=true)
	String serverCode;//0表示主服务器
	@AisinoSerializeProperty(serializeName="KPDH",length=10,des="开票点号",require=true)
	String keyNo;
	@AisinoSerializeProperty(serializeName="FPZL",length=2,des="发票种类",require=true)
	String inoviceType;//0-增值税专用发票； 2-增值税普通发票； 11-货物运输业增值税专用发票； 12-机动车销售统一发票
	@AisinoSerializeProperty(serializeName="FPDM",length=20,des="发票代码",require=true)
	String invoiceCode;//发票代码	字符	20	
	@AisinoSerializeProperty(serializeName="StartNO",length=20	,des="起始发票号码",require=true)
	String startNo;//起始发票号码	字符	20	
	@AisinoSerializeProperty(serializeName="InvoiceCount",length=10,des="发票份数",require=true)
	Integer invoiceCount;	//发票份数	数值	

	/**
	 * 纳税人识别号 金税盘上信息
	 * @param nsrsbh
	 */
	public void setTaxPayerNo(String nsrsbh) {
		this.taxPayerNo = nsrsbh;
	}
	/**
	 * 0表示主服务器 从依次编号
	 * @param kpfwqh
	 */
	public void setServerCode(String kpfwqh) {
		this.serverCode = kpfwqh;
	}
	/**
	 * 开票点号,就是钥匙的编号
	 * serverNo
	 * @param kpdh
	 */
	public void setKeyNo(String kpdh) {
		this.keyNo = kpdh;
	}
	/**
	 * 发票类型
	 * @param fpzl
	 */
	public void setInoviceType(String fpzl) {
		this.inoviceType = fpzl;
	}
	
	/**
	 * 发票代码
	 * @param fpdm
	 */
	public void setInvoiceCode(String fpdm) {
		this.invoiceCode = fpdm;
	}
	
	/**
	 * 起始号码
	 * @param startNo
	 */
	public void setStartNo(String startNo) {
		this.startNo = startNo;
	}
	/**
	 * 发票数量
	 * @param invoiceCount
	 */
	public void setInvoiceCount(Integer invoiceCount) {
		this.invoiceCount = invoiceCount;
	}	
	
}
