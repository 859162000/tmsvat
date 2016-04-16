
/**    
 * Copyright (C),Deloitte
 * @FileName: InvoiceReqHInParam.java  
 * @Package: com.deloitte.tms.vat.salesinvoice.model  
 * @Description: //模块目的、功能描述  
 * @Author sqing  
 * @Date 2016年3月16日 下午9:37:32  
 * @History: //修改记录
 * 〈author〉      〈time〉      〈version〉       〈desc〉
 * 修改人姓名            修改时间            版本号              描述   
*/  

package com.deloitte.tms.vat.salesinvoice.model;

import java.util.Date;


/**  
 *〈一句话功能简述〉
 * 功能详细描述
 * @author sqing
 * @create 2016年3月16日 下午9:37:32 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */

public class InvoiceReqHInParam extends InvoiceReqH {
	/**
	 * 客户关系
	 */
	private String custDepositBankAccountNum;//购方开户账号
	private String custDepositBankName;//购方开户银行名称
	private String custRegistrationAddress;//购方纳税人地址
	
	/**
	 * 交易流水号
	 */
	private String batchNo;
	private String batchNoName;
	/**
	 * 申请单状态
	 */
	private String reqStatus;
	/**
	 * 发票种类
	 */
	private String invoiceCate;
	/**
	 * 打印终端编号
	 */
	private String printerNo;
	/**
	 * 申请单净额
	 */
	private String reqAmount;
	/**
	 * 税额
	 */
	private Long tax;
	/**
	 * 计税合计
	 */
	private Long valueAndTaxtotal;
	/**
	 * 涉税交易类型编码
	 */
	private String taxCode;
	/**
	 * 涉税交易类型
	 */
	private String taxType;
	/**
	 * 交易日期
	 */
	private Date tradeDate;
	/**
	 * 申请单生成日期
	 */
	private String bornDate;
	/**
	 * 证件类型
	 */
	private String idType;
	private String validType;
		/**
	 * 社会统一征信号码
	 */
	private String creditNumber;
	/**
	 * 申请人
	 */
	private String applicant;
	/**
	 * 行信息
	 */
	/**
	 * 页面展现的状态值
	 */
	private String pageStatus;
	
	private String ids;
	
	private String readyNo;
	
	private String customerCode;
	
	private String bankNum;
	
	private String contactName;//购方纳税联系人
	
	private String address;
	
	private String bankBranchName;
	
	private String customerNumber;
	
	private String isCommit;
	
	private String acctdAmountCr;
	
	private String orgCode;
	
	private String orgName;
	
	private boolean havereql=false;
	
	public String getAcctdAmountCr() {
		return acctdAmountCr;
	}
	public void setAcctdAmountCr(String acctdAmountCr) {
		this.acctdAmountCr = acctdAmountCr;
	}
	public String getIsCommit() {
		return isCommit;
	}
	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}
	public String getCustomerNumber() {
		return customerNumber;
	}
	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}
	public String getBankNum() {
		return bankNum;
	}
	public void setBankNum(String bankNum) {
		this.bankNum = bankNum;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getBankBranchName() {
		return bankBranchName;
	}
	public void setBankBranchName(String bankBranchName) {
		this.bankBranchName = bankBranchName;
	}
	/*private String custRegistrationNumber;
	
	
	public String getCustRegistrationNumber() {
		return custRegistrationNumber;
	}
	public void setCustRegistrationNumber(String custRegistrationNumber) {
		this.custRegistrationNumber = custRegistrationNumber;
	}*/
	public String getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	public String getReadyNo() {
		return readyNo;
	}
	public void setReadyNo(String readyNo) {
		this.readyNo = readyNo;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public String getApplicant() {
		return applicant;
	}
	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}
	public String getCreditNumber() {
		return creditNumber;
	}
	public void setCreditNumber(String creditNumber) {
		this.creditNumber = creditNumber;
	}
	public String getValidType() {
		return validType;
	}
	public void setValidType(String validType) {
		this.validType = validType;
	}
	public String getIdType() {
		return idType;
	}
	public void setIdType(String idType) {
		this.idType = idType;
	}
	public String getBornDate() {
		return bornDate;
	}
	public void setBornDate(String bornDate) {
		this.bornDate = bornDate;
	}
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	public String getReqStatus() {
		return reqStatus;
	}
	public void setReqStatus(String reqStatus) {
		this.reqStatus = reqStatus;
	}
	public String getInvoiceCate() {
		return invoiceCate;
	}
	public void setInvoiceCate(String invoiceCate) {
		this.invoiceCate = invoiceCate;
	}
	public String getPrinterNo() {
		return printerNo;
	}
	public void setPrinterNo(String printerNo) {
		this.printerNo = printerNo;
	}
	public String getReqAmount() {
		return reqAmount;
	}
	public void setReqAmount(String reqAmount) {
		this.reqAmount = reqAmount;
	}
	public Long getTax() {
		return tax;
	}
	public void setTax(Long tax) {
		this.tax = tax;
	}
	public Long getValueAndTaxtotal() {
		return valueAndTaxtotal;
	}
	public void setValueAndTaxtotal(Long valueAndTaxtotal) {
		this.valueAndTaxtotal = valueAndTaxtotal;
	}
	public String getTaxCode() {
		return taxCode;
	}
	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}
	public String getTaxType() {
		return taxType;
	}
	public void setTaxType(String taxType) {
		this.taxType = taxType;
	}
	public Date getTradeDate() {
		return tradeDate;
	}
	public void setTradeDate(Date tradeDate) {
		this.tradeDate = tradeDate;
	}
	public String getBatchNoName() {
		return batchNoName;
	}
	public void setBatchNoName(String batchNoName) {
		this.batchNoName = batchNoName;
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
	public String getPageStatus() {
		return pageStatus;
	}
	public void setPageStatus(String pageStatus) {
		this.pageStatus = pageStatus;
	}
	public String getCustDepositBankAccountNum() {
		return custDepositBankAccountNum;
	}
	public void setCustDepositBankAccountNum(String custDepositBankAccountNum) {
		this.custDepositBankAccountNum = custDepositBankAccountNum;
	}
	public String getCustDepositBankName() {
		return custDepositBankName;
	}
	public void setCustDepositBankName(String custDepositBankName) {
		this.custDepositBankName = custDepositBankName;
	}
	public String getCustRegistrationAddress() {
		return custRegistrationAddress;
	}
	public void setCustRegistrationAddress(String custRegistrationAddress) {
		this.custRegistrationAddress = custRegistrationAddress;
	}
	public boolean isHavereql() {
		return havereql;
	}
	public void setHavereql(boolean havereql) {
		this.havereql = havereql;
	}
	
	
}
