package com.deloitte.tms.vat.salesinvoice.model;

import java.math.BigDecimal;

import javax.persistence.Column;

import com.deloitte.tms.pl.core.commons.annotation.ModelProperty;

/**
 * 批量开票前台页面变量
 * @author user
 *
 */
public class TmsCrvatInvReqBatchesLInParam extends TmsCrvatInvReqBatchesL{
	
	//--------TmsCrvatInvReqBatchesH--特殊批量开票头表---------
	//特殊批量开票申请单号
	private String crvatInvoiceReqNumber;
	//是否已收款
	private String isReceipts;
	
	//-----Customer--客户表----
	//购方名称
	private String customerName;
	//购方编码
	private String customerNumber;
	//购方纳税人地址
	private String custRegistrationAddress;
	//购方纳税人联系人
	private String contactName;
	
	public String getCustomerType() {
		return customerType;
	}
	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	private String customerType;
	//购方纳税人联系电话 
	private String contactPhone;
	//购方纳税人识别号
	private String custRegistrationNumber;
	//购方纳税人类型_枚举值(增值税一般纳税人/小规模纳税人/非增值税纳税人)
	private String custLegalEntityType;
	//购方开户账号
	private String custDepositBankAccountNum;
    //购方开户银行名称
	private String custDepositBankName;
    //购方开户银行代码
	private String custDepositBankNumber;
	
	//-----TmsMdContract--合同表----
	//合同编号
	private String contractNumber;
	//合同名称
	private String contractName;
	
	//----TmsMdProject--项目表---
	//项目编码
	private String projectNumber;
	//项目名称
	private String projectName;
	
	//---TmsMdTaxTrxType涉税交易类型表----
    //涉税交易类型编码
	private String taxTrxTypeCode;
    //涉税交易类型_枚举值
	private String taxTrxTypeName;
	
	//---TmsMdInventoryItems--商品及服务编码表----
	//商品及服务编码
	private String inventoryItemNumber;
    //商品及服务名称
	private String inventoryItemDescripton;
	
	
	//---TmsMdTrxAffirmSetting--涉税交易认定设置规则ID----
	//全局/组织类型_枚举值(全局/组织)
	private String globalOrLocalOgrType;
	//开票规则类型_枚举值(专票/普票/不可开票)
	private String invoiceCategories;
	//税率
	private  Double taxRate;
	//数量
	private Long inventoryItemQty;
	//总金额
	private BigDecimal invoiceAmounts;
	
   //-------设备/打印机表--TMSMDEQUIPMENT----
	private String equipmentId;//终端id
	private String equipmentName;//终端名称
	private String equipmentCode;//终端编号
	
	//-------纳税主体表--TmsMdLegalEntity----
	
	private String legalEntityId;//纳税主体id
	private String legalEntityCode;//纳税主体CODE
	private String legalEntityName;//纳税主体名称
	private String bankBranchName;//开户银行
	private String bankAccountNum;//开户银行账号
	private String registrationContactPhone;//联系电话
	private String registrationContactAddress;//地址
	//-------组织--BaseOrg----
	private String orgName;//组织名称
	private String orgCode;//组织代码
	
	
	public String getEquipmentId() {
		return equipmentId;
	}
	public void setEquipmentId(String equipmentId) {
		this.equipmentId = equipmentId;
	}
	public String getCrvatInvoiceReqNumber() {
		return crvatInvoiceReqNumber;
	}
	public void setCrvatInvoiceReqNumber(String crvatInvoiceReqNumber) {
		this.crvatInvoiceReqNumber = crvatInvoiceReqNumber;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerNumber() {
		return customerNumber;
	}
	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}
	public String getCustRegistrationAddress() {
		return custRegistrationAddress;
	}
	public void setCustRegistrationAddress(String custRegistrationAddress) {
		this.custRegistrationAddress = custRegistrationAddress;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	public String getCustRegistrationNumber() {
		return custRegistrationNumber;
	}
	public void setCustRegistrationNumber(String custRegistrationNumber) {
		this.custRegistrationNumber = custRegistrationNumber;
	}
	public String getCustLegalEntityType() {
		return custLegalEntityType;
	}
	public void setCustLegalEntityType(String custLegalEntityType) {
		this.custLegalEntityType = custLegalEntityType;
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
	public String getCustDepositBankNumber() {
		return custDepositBankNumber;
	}
	public void setCustDepositBankNumber(String custDepositBankNumber) {
		this.custDepositBankNumber = custDepositBankNumber;
	}
	public String getContractNumber() {
		return contractNumber;
	}
	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}
	public String getContractName() {
		return contractName;
	}
	public void setContractName(String contractName) {
		this.contractName = contractName;
	}
	public String getProjectNumber() {
		return projectNumber;
	}
	public void setProjectNumber(String projectNumber) {
		this.projectNumber = projectNumber;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
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

	public String getInventoryItemNumber() {
		return inventoryItemNumber;
	}
	public void setInventoryItemNumber(String inventoryItemNumber) {
		this.inventoryItemNumber = inventoryItemNumber;
	}
	public String getInventoryItemDescripton() {
		return inventoryItemDescripton;
	}
	public void setInventoryItemDescripton(String inventoryItemDescripton) {
		this.inventoryItemDescripton = inventoryItemDescripton;
	}
	public String getGlobalOrLocalOgrType() {
		return globalOrLocalOgrType;
	}
	public void setGlobalOrLocalOgrType(String globalOrLocalOgrType) {
		this.globalOrLocalOgrType = globalOrLocalOgrType;
	}

	public String getInvoiceCategories() {
		return invoiceCategories;
	}
	public void setInvoiceCategories(String invoiceCategories) {
		this.invoiceCategories = invoiceCategories;
	}
	public Double getTaxRate() {
		return taxRate;
	}
	public void setTaxRate(Double taxRate) {
		this.taxRate = taxRate;
	}
	public Long getInventoryItemQty() {
		return inventoryItemQty;
	}
	public void setInventoryItemQty(Long inventoryItemQty) {
		this.inventoryItemQty = inventoryItemQty;
	}
	public String getEquipmentName() {
		return equipmentName;
	}
	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}
	public String getLegalEntityCode() {
		return legalEntityCode;
	}
	public void setLegalEntityCode(String legalEntityCode) {
		this.legalEntityCode = legalEntityCode;
	}
	public String getLegalEntityName() {
		return legalEntityName;
	}
	public void setLegalEntityName(String legalEntityName) {
		this.legalEntityName = legalEntityName;
	}
	public String getBankBranchName() {
		return bankBranchName;
	}
	public void setBankBranchName(String bankBranchName) {
		this.bankBranchName = bankBranchName;
	}
	public String getBankAccountNum() {
		return bankAccountNum;
	}
	public void setBankAccountNum(String bankAccountNum) {
		this.bankAccountNum = bankAccountNum;
	}
	public String getRegistrationContactPhone() {
		return registrationContactPhone;
	}
	public void setRegistrationContactPhone(String registrationContactPhone) {
		this.registrationContactPhone = registrationContactPhone;
	}
	public String getRegistrationContactAddress() {
		return registrationContactAddress;
	}
	public void setRegistrationContactAddress(String registrationContactAddress) {
		this.registrationContactAddress = registrationContactAddress;
	}
	public String getEquipmentCode() {
		return equipmentCode;
	}
	public void setEquipmentCode(String equipmentCode) {
		this.equipmentCode = equipmentCode;
	}
	public String getLegalEntityId() {
		return legalEntityId;
	}
	public void setLegalEntityId(String legalEntityId) {
		this.legalEntityId = legalEntityId;
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
	public String getIsReceipts() {
		return isReceipts;
	}
	public void setIsReceipts(String isReceipts) {
		this.isReceipts = isReceipts;
	}
	public BigDecimal getInvoiceAmounts() {
		return invoiceAmounts;
	}
	public void setInvoiceAmounts(BigDecimal invoiceAmounts) {
		this.invoiceAmounts = invoiceAmounts;
	}

	
	
	

	
	
	
	
}
