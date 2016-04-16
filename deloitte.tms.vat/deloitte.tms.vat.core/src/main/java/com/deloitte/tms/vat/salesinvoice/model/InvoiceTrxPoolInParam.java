
/**    
 * Copyright (C),Deloitte
 * @FileName: InvoiceTrxPoolInParam.java  
 * @Package: com.deloitte.tms.vat.salesinvoice.model  
 * @Description: //模块目的、功能描述  
 * @Author stonshi  
 * @Date 2016年3月16日 下午10:07:35  
 * @History: //修改记录
 * 〈author〉      〈time〉      〈version〉       〈desc〉
 * 修改人姓名            修改时间            版本号              描述   
*/  

package com.deloitte.tms.vat.salesinvoice.model;

import java.math.BigDecimal;


/**  
 *〈一句话功能简述〉
 * 功能详细描述
 * @author stonshi
 * @create 2016年3月16日 下午10:07:35 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */

public class InvoiceTrxPoolInParam extends InvoiceTrxPool {
	/**
	 * 页面显示总金额
	 */
	private BigDecimal invoiceAmount;

	public BigDecimal getInvoiceAmount() {
		return invoiceAmount;
	}

	public void setInvoiceAmount(BigDecimal invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}
	/**
	 * 
	 */
	private String invoicereqhid;
	
	private String operatorUser;
	
	private String customerName;
	
	private String customerNumber;
	
	private String customerNumbType;
	
	private String customerRegisTrationNum;
	
	private String productNum;
	
	private String productName;
	
	private String taxType;
	
	private String taxTypeName;
	
	private String taxItem;
	
	private String taxMethod;
	
	private String origAmount;
	
	private String origCurrent;
	
	private String currentAmount;
	
	private String current;
	
	private String totalAmount;
	
	private String haveTotalAmount;

	private String remainAmount;
	
	private String isTax;
	

	

	
	
	
	
	
	
	public String getInvoicereqhid() {
		return invoicereqhid;
	}

	public void setInvoicereqhid(String invoicereqhid) {
		this.invoicereqhid = invoicereqhid;
	}

	public String getOperatorUser() {
		return operatorUser;
	}

	public void setOperatorUser(String operatorUser) {
		this.operatorUser = operatorUser;
	}

	public String getIsTax() {
		return isTax;
	}

	public void setIsTax(String isTax) {
		this.isTax = isTax;
	}

	public String getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}

	public String getCustomerNumbType() {
		return customerNumbType;
	}

	public void setCustomerNumbType(String customerNumbType) {
		this.customerNumbType = customerNumbType;
	}

	public String getCustomerRegisTrationNum() {
		return customerRegisTrationNum;
	}

	public void setCustomerRegisTrationNum(String customerRegisTrationNum) {
		this.customerRegisTrationNum = customerRegisTrationNum;
	}

	public String getProductNum() {
		return productNum;
	}

	public void setProductNum(String productNum) {
		this.productNum = productNum;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getTaxType() {
		return taxType;
	}

	public void setTaxType(String taxType) {
		this.taxType = taxType;
	}

	public String getTaxTypeName() {
		return taxTypeName;
	}

	public void setTaxTypeName(String taxTypeName) {
		this.taxTypeName = taxTypeName;
	}

	public String getTaxItem() {
		return taxItem;
	}

	public void setTaxItem(String taxItem) {
		this.taxItem = taxItem;
	}

	public String getTaxMethod() {
		return taxMethod;
	}

	public void setTaxMethod(String taxMethod) {
		this.taxMethod = taxMethod;
	}

	public String getOrigAmount() {
		return origAmount;
	}

	public void setOrigAmount(String origAmount) {
		this.origAmount = origAmount;
	}

	public String getOrigCurrent() {
		return origCurrent;
	}

	public void setOrigCurrent(String origCurrent) {
		this.origCurrent = origCurrent;
	}

	public String getCurrentAmount() {
		return currentAmount;
	}

	public void setCurrentAmount(String currentAmount) {
		this.currentAmount = currentAmount;
	}

	public String getCurrent() {
		return current;
	}

	public void setCurrent(String current) {
		this.current = current;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getHaveTotalAmount() {
		return haveTotalAmount;
	}

	public void setHaveTotalAmount(String haveTotalAmount) {
		this.haveTotalAmount = haveTotalAmount;
	}

	public String getRemainAmount() {
		return remainAmount;
	}

	public void setRemainAmount(String remainAmount) {
		this.remainAmount = remainAmount;
	}
	
	
	

	
	private String taxSettingMethodName;
	
	private String invoiceTypeName;
	
	private String invoiceCategoryName;
	
	private String sourceCodeName;
	
	private String strStatus;
	
	
	
	
	
	public String getSourceCodeName() {
		return sourceCodeName;
	}

	public void setSourceCodeName(String sourceCodeName) {
		this.sourceCodeName = sourceCodeName;
	}

	public String getTaxSettingMethodName() {
		return taxSettingMethodName;
	}

	public void setTaxSettingMethodName(String taxSettingMethodName) {
		this.taxSettingMethodName = taxSettingMethodName;
	}

	public String getInvoiceTypeName() {
		return invoiceTypeName;
	}

	public void setInvoiceTypeName(String invoiceTypeName) {
		this.invoiceTypeName = invoiceTypeName;
	}

	public String getInvoiceCategoryName() {
		return invoiceCategoryName;
	}

	public void setInvoiceCategoryName(String invoiceCategoryName) {
		this.invoiceCategoryName = invoiceCategoryName;
	}

	public String getTaxBaseName() {
		return taxBaseName;
	}

	public void setTaxBaseName(String taxBaseName) {
		this.taxBaseName = taxBaseName;
	}
	
	
	
	public String getStrStatus() {
		return strStatus;
	}

	public void setStrStatus(String strStatus) {
		this.strStatus = strStatus;
	}
	private String orgName;
	
	private String orgCode;
	
	private String trxid;
	
	private String taxBaseName;
	/**
	 * 可开票金额
	 */
	private String userfulAmount;

	public String getUserfulAmount() {
		return userfulAmount;
	}

	public void setUserfulAmount(String userfulAmount) {
		this.userfulAmount = userfulAmount;
	}
	/**
	 * 已开票金额
	 */
	private String usedAmount;
	
	private String taxTrxTypeCode;
	
	private String taxTrxTypeName;
	
	private String isExitsCustomer;
	public String getUsedAmount() {
		return usedAmount;
	}

	public void setUsedAmount(String usedAmount) {
		this.usedAmount = usedAmount;
	}
	
	private String crvatTrxPoolId;
	
	public String getCrvatTrxPoolId() {
		return crvatTrxPoolId;
	}

	public void setCrvatTrxPoolId(String crvatTrxPoolId) {
		this.crvatTrxPoolId = crvatTrxPoolId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	private String customerId;

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getTaxTrxTypeCode() {
		return taxTrxTypeCode;
	}

	public void setTaxTrxTypeCode(String taxTrxTypeCode) {
		this.taxTrxTypeCode = taxTrxTypeCode;
	}

	public String getTaxTrxTypeName() {
		return taxTrxTypeName;
	}

	public void setTaxTrxTypeName(String taxTrxTypeName) {
		this.taxTrxTypeName = taxTrxTypeName;
	}

	public String getTrxid() {
		return trxid;
	}

	public void setTrxid(String trxid) {
		this.trxid = trxid;
	}

	public String getIsExitsCustomer() {
		return isExitsCustomer;
	}

	public void setIsExitsCustomer(String isExitsCustomer) {
		this.isExitsCustomer = isExitsCustomer;
	}

}
