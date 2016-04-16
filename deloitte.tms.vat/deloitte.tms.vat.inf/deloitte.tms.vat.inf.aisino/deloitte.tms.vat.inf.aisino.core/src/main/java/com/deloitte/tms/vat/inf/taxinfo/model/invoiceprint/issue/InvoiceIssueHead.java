package com.deloitte.tms.vat.inf.taxinfo.model.invoiceprint.issue;

import com.deloitte.tms.vat.inf.taxinfo.annotation.AisinoSHSerializeProperty;
import com.deloitte.tms.vat.inf.taxinfo.annotation.AisinoSerializeProperty;

/**
 * 发票开具头
 * @author bo.wang
 * @create 2016年4月12日 上午10:47:03
 */
public class InvoiceIssueHead {
	
	/**************************公共部分和报文差异*******************************************/
	@AisinoSerializeProperty(serializeName="FPZL",length=2,des="发票种类",require=true)
	@AisinoSHSerializeProperty(serializeName="invkind",length=3,des="0-专用发票，2 –普通发票",valueFormat=true)
	String invoiceType;//FPZLEnums
	@AisinoSerializeProperty(serializeName="GFMC",length=100,des="购方名称",require=true)
	@AisinoSHSerializeProperty(serializeName="custname",length=100,des="购方名称",require=true)
	String purchaserName;
	@AisinoSerializeProperty(serializeName="GFSH",length=20,des="购方税号",require=true)
	@AisinoSHSerializeProperty(serializeName="custtaxnr",length=20,des="客户税号（普票时可以为空）",require=true)
	String purchaserTaxNo;
	@AisinoSerializeProperty(serializeName="GFDZDH",length=100,des="购方地址电话")
	@AisinoSHSerializeProperty(serializeName="custaddrtel",length=100,des="购方地址电话,普票时可以为空")
	String purchaserTel;
	@AisinoSerializeProperty(serializeName="GFYHZH",length=100,des="购方银行账户")
	@AisinoSHSerializeProperty(serializeName="custbankacct",length=100,des="购方银行账户,普票时可以为空")
	String purchaserBankNo;
	@AisinoSerializeProperty(serializeName="BZ",length=230,des="备注")
	@AisinoSHSerializeProperty(serializeName="memo",length=250,des="备注,打印在发票备注位置")
	String bark;
	@AisinoSerializeProperty(serializeName="SKR",length=8,des="收款人",require=true)
	@AisinoSHSerializeProperty(serializeName="payee",length=8,des="收款人",require=true)
	String reciver;
	@AisinoSerializeProperty(serializeName="FHR",length=8,des="复核人",require=true)
	@AisinoSHSerializeProperty(serializeName="checker",length=8,des="复核人",require=true)
	String checker;
	@AisinoSerializeProperty(serializeName="KPR",length=8,des="开票人",require=true)
	@AisinoSHSerializeProperty(serializeName="issuer",length=8,des="开票人",require=true)
	String drawer;
	@AisinoSerializeProperty(serializeName="XFYHZH",length=100,des="销方银行账户")
	@AisinoSHSerializeProperty(serializeName="sellerbankacct",length=100,des="销方银行账户")
	String salesBankNo;
	@AisinoSerializeProperty(serializeName="XFDZDH",length=100,des="销方地址电话")
	@AisinoSHSerializeProperty(serializeName="selleraddresstel",length=100,des="销方地址电话")
	String salesBankTel;	
	/**************************公共部分和报文差异*******************************************/
	
	
	
	/*****************************************北京航信特有************************/
	@AisinoSerializeProperty(serializeName="QDBZ",length=2,des="清单标志",require=true)
	String isDetail;//  0：不开具清单 1：开具清单 QDBZEnums
	@AisinoSerializeProperty(serializeName="XSDJBH",length=100,des="销售单据编号")
	String salesDocNo;
	@AisinoSerializeProperty(serializeName="KPBZ",length=2,des="开票标志")
	String invoiceMethod;// 0：开票 1：校验 2：空白作废下一张发票 KPBZEnums
	/*****************************************北京航信特有************************/
	
	
	/******************************上海单机专用**********************************/
	@AisinoSHSerializeProperty(serializeName="machinetaxnr",length=20,des="指定的开票机税号")
	String machineTaxNo;
	@AisinoSHSerializeProperty(serializeName="machinenr",length=3,des="指定的开票机号")
	String machineNo;
	@AisinoSHSerializeProperty(serializeName="doctype",length=3,des="1 - 正常单据、2 - 红冲单据、3 - 折扣单据",valueFormat=true)
	String doctype;
	@AisinoSHSerializeProperty(serializeName="docnr",length=50,des="业务系统发票号   单据号")
	String docnr;
	@AisinoSHSerializeProperty(serializeName="docdate",length=100,des="业务系统发票日期 YYYY-MM-DD :S:F:M")
	String docdate;
	@AisinoSHSerializeProperty(serializeName="custnr",length=100,des="客户编号(可以为空,发票上不体现)")
	String custnr;
	@AisinoSHSerializeProperty(serializeName="memo2",length=4000,des="备注2,可以为空，发票上不体现")
	String memo2;
	@AisinoSHSerializeProperty(serializeName="refinvcode",length=10,des="对应正数发票代码（仅用于普通发票）（仅红冲发票时需要）")
	String refinvcode;
	@AisinoSHSerializeProperty(serializeName="refInvnr",length=8,des="普通发票红票对应正数发票号码（仅红冲发票时需要）")
	String refInvnr;
	@AisinoSHSerializeProperty(serializeName="rednoticenr",length=16,des="专用发票红票通知单号（仅红冲发票时需要）")
	String rednoticenr;
	/******************************上海单机专用**********************************/

	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}
	public void setPurchaserName(String purchaserName) {
		this.purchaserName = purchaserName;
	}
	public void setPurchaserTaxNo(String purchaserTaxNo) {
		this.purchaserTaxNo = purchaserTaxNo;
	}
	public void setPurchaserTel(String purchaserTel) {
		this.purchaserTel = purchaserTel;
	}
	public void setPurchaserBankNo(String purchaserBankNo) {
		this.purchaserBankNo = purchaserBankNo;
	}
	public void setBark(String bark) {
		this.bark = bark;
	}
	public void setReciver(String reciver) {
		this.reciver = reciver;
	}
	public void setChecker(String checker) {
		this.checker = checker;
	}
	public void setDrawer(String drawer) {
		this.drawer = drawer;
	}
	public void setSalesBankNo(String salesBankNo) {
		this.salesBankNo = salesBankNo;
	}
	public void setSalesBankTel(String salesBankTel) {
		this.salesBankTel = salesBankTel;
	}
	public void setIsDetail(String isDetail) {
		this.isDetail = isDetail;
	}
	public void setSalesDocNo(String salesDocNo) {
		this.salesDocNo = salesDocNo;
	}
	public void setInvoiceMethod(String invoiceMethod) {
		this.invoiceMethod = invoiceMethod;
	}
	public String getInvoiceType() {
		return invoiceType;
	}
	public String getPurchaserName() {
		return purchaserName;
	}
	public String getPurchaserTaxNo() {
		return purchaserTaxNo;
	}
	public String getPurchaserTel() {
		return purchaserTel;
	}
	public String getPurchaserBankNo() {
		return purchaserBankNo;
	}
	public String getBark() {
		return bark;
	}
	public String getReciver() {
		return reciver;
	}
	public String getChecker() {
		return checker;
	}
	public String getDrawer() {
		return drawer;
	}
	public String getSalesBankNo() {
		return salesBankNo;
	}
	public String getSalesBankTel() {
		return salesBankTel;
	}
	public String getIsDetail() {
		return isDetail;
	}
	public String getSalesDocNo() {
		return salesDocNo;
	}
	public String getInvoiceMethod() {
		return invoiceMethod;
	}
	public String getMachineTaxNo() {
		return machineTaxNo;
	}
	public void setMachineTaxNo(String machineTaxNo) {
		this.machineTaxNo = machineTaxNo;
	}
	public String getMachineNo() {
		return machineNo;
	}
	public void setMachineNo(String machineNo) {
		this.machineNo = machineNo;
	}
	public String getDoctype() {
		return doctype;
	}
	public void setDoctype(String doctype) {
		this.doctype = doctype;
	}
	public String getDocnr() {
		return docnr;
	}
	public void setDocnr(String docnr) {
		this.docnr = docnr;
	}
	public String getDocdate() {
		return docdate;
	}
	public void setDocdate(String docdate) {
		this.docdate = docdate;
	}
	public String getCustnr() {
		return custnr;
	}
	public void setCustnr(String custnr) {
		this.custnr = custnr;
	}
	public String getMemo2() {
		return memo2;
	}
	public void setMemo2(String memo2) {
		this.memo2 = memo2;
	}
	public String getRefinvcode() {
		return refinvcode;
	}
	public void setRefinvcode(String refinvcode) {
		this.refinvcode = refinvcode;
	}
	public String getRefInvnr() {
		return refInvnr;
	}
	public void setRefInvnr(String refInvnr) {
		this.refInvnr = refInvnr;
	}
	public String getRednoticenr() {
		return rednoticenr;
	}
	public void setRednoticenr(String rednoticenr) {
		this.rednoticenr = rednoticenr;
	}	
	
}
