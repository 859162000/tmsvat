
/**    
 * Copyright (C),Deloitte
 * @FileName: InvoicePrintPoolH.java  
 * @Package: com.deloitte.tms.vat.salesinvoice.model  
 * @Description: //模块目的、功能描述  
 * @Author liazhou  
 * @Date 2016年3月17日 下午12:12:48  
 * @History: //修改记录
 * 〈author〉      〈time〉      〈version〉       〈desc〉
 * 修改人姓名            修改时间            版本号              描述   
*/  

package com.deloitte.tms.vat.salesinvoice.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**  
 *〈一句话功能简述〉
 * 功能详细描述
 * @author Tomfang
 * @create 2016年3月17日 下午12:12:48 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class InvoicePrintPoolHInParam {
	
	//发票头信息
	private String id;
	private String crvatInvoicePreHId;
	private String invoicingType;
	/**
	 * 选中标识
	 */
	private String checkFlag;
	/**
	 * 购方单位名称
	 */
	private String customerName;
	/**
	 * 客户号
	 */
	private String customerNumber;
	/**
	 * 购方证件号码
	 */
	private String custRegistrationNumber;
	/**
	 * 购方证件类型
	 */
	private String custRegistrationCode;
	
	/**
	 * 购方证件类型名称
	 */
	private String custRegistrationCodeName;
	
	/**
	 * 购方纳税人地址
	 */
	private String custRegistrationAddress;
	
	
	/**
	 * 联系人
	 */
	private String custContactPhone;
	/**
	 * 开户行
	 */
	private String custDepositBankName;
	/**
	 * 银行账号
	 */
	private String custDepositBankAccountNum;
	/**
	 * 销方纳税名称
	 */
	private String legalEntityName;
	/**
	 * 销方纳税人识别号
	 */
	private String registrationNumber;//
	/**
	 * 发票种类
	 */
	private String invoiceCategory;
	/**
	 * 发票种类名称
	 */
	private String invoiceCategoryName;
	/**
	 * 发票代码
	 */
	private String invoiceCode;//发票代码
	/**
	 * 发票号码
	 */
	private String invoiceNumber;//发票号码
	/**
	 * 来源
	 */
	private String invoiceReqType;
	/**
	 * 来源名称
	 */
	private String invoiceReqTypeName;
	
	private Date invoicePrintDate;//开具日期
	private String invoicePrintBy;//开具人
	private String invoicePrintStatusName;//打印状态名称
	private String invoicePrintStatus;
	private String invoiceSendResultStatus;
	private String invoiceReqNumber;
	private String invoicePreNumber;
	/**
	 *税额
	 */
	private BigDecimal vatAmount;
	/**
	 * 金额
	 */
	private BigDecimal invoiceAmount;
	/**
	 * 净额
	 */
	private BigDecimal acctdAmountCR;
	private String isExistCustomer;
	/**
	 * 申请组织
	 */
	private String orgId;
	/**
	 * 申请组织名称
	 */
	private String orgName;
	/**
	 * 打印终端编号
	 */
	private String equipmentCode;
	/**
	 * 打印终端名称
	 */
	private String equipmentName;
	/**
	 * 打印终端ID
	 */
	private String equipmentId;
	
	private List<InvoicePrintPoolLInParam> printPoolLInParamList;
	/**
	 * 发票限额
	 */
	BigDecimal invoiceLimitAmount;
	
	
	
	public String getCustRegistrationCode() {
		return custRegistrationCode;
	}
	public void setCustRegistrationCode(String custRegistrationCode) {
		this.custRegistrationCode = custRegistrationCode;
	}
	public String getCustRegistrationCodeName() {
		return custRegistrationCodeName;
	}
	public void setCustRegistrationCodeName(String custRegistrationCodeName) {
		this.custRegistrationCodeName = custRegistrationCodeName;
	}
	public String getCheckFlag() {
		return checkFlag;
	}
	public void setCheckFlag(String checkFlag) {
		this.checkFlag = checkFlag;
	}
	public String getInvoiceReqType() {
		return invoiceReqType;
	}
	public void setInvoiceReqType(String invoiceReqType) {
		this.invoiceReqType = invoiceReqType;
	}
	public String getInvoiceReqTypeName() {
		return invoiceReqTypeName;
	}
	public void setInvoiceReqTypeName(String invoiceReqTypeName) {
		this.invoiceReqTypeName = invoiceReqTypeName;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public BigDecimal getInvoiceLimitAmount() {
		return invoiceLimitAmount;
	}
	public void setInvoiceLimitAmount(BigDecimal invoiceLimitAmount) {
		this.invoiceLimitAmount = invoiceLimitAmount;
	}
	public List<InvoicePrintPoolLInParam> getPrintPoolLInParamList() {
		return printPoolLInParamList;
	}
	public void setPrintPoolLInParamList(
			List<InvoicePrintPoolLInParam> printPoolLInParamList) {
		this.printPoolLInParamList = printPoolLInParamList;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCrvatInvoicePreHId() {
		return crvatInvoicePreHId;
	}
	public void setCrvatInvoicePreHId(String crvatInvoicePreHId) {
		this.crvatInvoicePreHId = crvatInvoicePreHId;
	}
	public String getInvoicingType() {
		return invoicingType;
	}
	public void setInvoicingType(String invoicingType) {
		this.invoicingType = invoicingType;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustRegistrationNumber() {
		return custRegistrationNumber;
	}
	public void setCustRegistrationNumber(String custRegistrationNumber) {
		this.custRegistrationNumber = custRegistrationNumber;
	}
	public String getCustRegistrationAddress() {
		return custRegistrationAddress;
	}
	public void setCustRegistrationAddress(String custRegistrationAddress) {
		this.custRegistrationAddress = custRegistrationAddress;
	}
	public String getCustContactPhone() {
		return custContactPhone;
	}
	public void setCustContactPhone(String custContactPhone) {
		this.custContactPhone = custContactPhone;
	}
	public String getCustDepositBankName() {
		return custDepositBankName;
	}
	public void setCustDepositBankName(String custDepositBankName) {
		this.custDepositBankName = custDepositBankName;
	}
	public String getCustDepositBankAccountNum() {
		return custDepositBankAccountNum;
	}
	public void setCustDepositBankAccountNum(String custDepositBankAccountNum) {
		this.custDepositBankAccountNum = custDepositBankAccountNum;
	}
	public String getLegalEntityName() {
		return legalEntityName;
	}
	public void setLegalEntityName(String legalEntityName) {
		this.legalEntityName = legalEntityName;
	}
	public String getRegistrationNumber() {
		return registrationNumber;
	}
	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}
	public String getInvoiceCategory() {
		return invoiceCategory;
	}
	public void setInvoiceCategory(String invoiceCategory) {
		this.invoiceCategory = invoiceCategory;
	}
	public String getInvoiceCategoryName() {
		return invoiceCategoryName;
	}
	public void setInvoiceCategoryName(String invoiceCategoryName) {
		this.invoiceCategoryName = invoiceCategoryName;
	}
	public String getInvoiceCode() {
		return invoiceCode;
	}
	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}
	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	public Date getInvoicePrintDate() {
		return invoicePrintDate;
	}
	public void setInvoicePrintDate(Date invoicePrintDate) {
		this.invoicePrintDate = invoicePrintDate;
	}
	public String getInvoicePrintBy() {
		return invoicePrintBy;
	}
	public void setInvoicePrintBy(String invoicePrintBy) {
		this.invoicePrintBy = invoicePrintBy;
	}
	public String getInvoicePrintStatusName() {
		return invoicePrintStatusName;
	}
	public void setInvoicePrintStatusName(String invoicePrintStatusName) {
		this.invoicePrintStatusName = invoicePrintStatusName;
	}
	public String getInvoicePrintStatus() {
		return invoicePrintStatus;
	}
	public void setInvoicePrintStatus(String invoicePrintStatus) {
		this.invoicePrintStatus = invoicePrintStatus;
	}
	public String getInvoiceSendResultStatus() {
		return invoiceSendResultStatus;
	}
	public void setInvoiceSendResultStatus(String invoiceSendResultStatus) {
		this.invoiceSendResultStatus = invoiceSendResultStatus;
	}
	public String getInvoiceReqNumber() {
		return invoiceReqNumber;
	}
	public void setInvoiceReqNumber(String invoiceReqNumber) {
		this.invoiceReqNumber = invoiceReqNumber;
	}
	public String getInvoicePreNumber() {
		return invoicePreNumber;
	}
	public void setInvoicePreNumber(String invoicePreNumber) {
		this.invoicePreNumber = invoicePreNumber;
	}
	public BigDecimal getVatAmount() {
		return vatAmount;
	}
	public void setVatAmount(BigDecimal vatAmount) {
		this.vatAmount = vatAmount;
	}
	public BigDecimal getInvoiceAmount() {
		return invoiceAmount;
	}
	public void setInvoiceAmount(BigDecimal invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}
	public BigDecimal getAcctdAmountCR() {
		return acctdAmountCR;
	}
	public void setAcctdAmountCR(BigDecimal acctdAmountCR) {
		this.acctdAmountCR = acctdAmountCR;
	}
	public String getIsExistCustomer() {
		return isExistCustomer;
	}
	public void setIsExistCustomer(String isExistCustomer) {
		this.isExistCustomer = isExistCustomer;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getEquipmentCode() {
		return equipmentCode;
	}
	public void setEquipmentCode(String equipmentCode) {
		this.equipmentCode = equipmentCode;
	}
	public String getEquipmentName() {
		return equipmentName;
	}
	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}
	public String getEquipmentId() {
		return equipmentId;
	}
	public void setEquipmentId(String equipmentId) {
		this.equipmentId = equipmentId;
	}
	public String getCustomerNumber() {
		return customerNumber;
	}
	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}
	
	
	
}
