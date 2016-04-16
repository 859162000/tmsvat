
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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Where;

import com.deloitte.tms.base.masterdata.model.Customer;
import com.deloitte.tms.base.masterdata.model.TmsMdLegalEntity;
import com.deloitte.tms.pl.core.commons.annotation.ModelProperty;
import com.deloitte.tms.pl.core.commons.constant.TablePreDef;
import com.deloitte.tms.pl.core.hibernate.identifier.Ling2UUIDGenerator;
import com.deloitte.tms.pl.core.model.impl.BaseEntity;


/**  
 *〈一句话功能简述〉
 * 功能详细描述
 * @author liazhou
 * @create 2016年3月17日 下午12:12:48 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Entity
@Table(name = InvoicePrintPoolH.TABLE)
@ModelProperty(comment = "销项发票打印池主表")
public class InvoicePrintPoolH extends BaseEntity{
	public static final String TABLE = TablePreDef.TMS_CRVAT_INV+"PRT_POOL_H";//TMS_CRVAT_INV_PRT_POOL_H
	public static final String SEQ = TABLE;
	
	@Id
	/*@GenericGenerator(name=SEQ+"_GENERATOR",strategy=Ling2UUIDGenerator.STRATEGY_NAME,parameters={@Parameter(name="pkColumnValue",value=SEQ)})
	@GeneratedValue(strategy = GenerationType.TABLE, generator = SEQ+"_GENERATOR")*/
	@Column(name = "INVOICE_PRT_POOL_H_ID",  length = 36)
	@ModelProperty(comment="发票打印池-头")
	String id ;
	
	@Column(name = "INVOICE_CATEGORY",length=36)
	@ModelProperty(comment = "发票类型")
	String invoiceCategory;
	
	
	
	
	@Column(name = "CRVAT_INVOICE_PRE_H_ID",length=36)
	@ModelProperty(comment = "开票准备单头ID")
	String crvatInvoicePreHId;
	
	@Cascade(CascadeType.REFRESH)
	@JoinColumn(name="CRVAT_INVOICE_PRE_H_ID",insertable=false,updatable=false,nullable=true)
	@ManyToOne
	TmsCrvatInvoicePreH crvatInvoicePreH;
	
	@Column(name = "INVENTORY_INVOICE_ID",length=36)
	@ModelProperty(comment = "发票库存ID")
	String inventoryInvoiceId;
	
	@Column(name = "INVOICE_CODE",length=100)
	@ModelProperty(comment = "发票代码")
	String invoiceCode;
	
	@Column(name = "INVOICE_NUMBER",length=100)
	@ModelProperty(comment = "发票号码")
	String invoiceNumber;
	
	
	@Column(name = "INVOICING_TYPE",length=100)
	@ModelProperty(comment = "开票方式")
	String invoicingType;
	
	@Column(name = "INVOICE_LIMIT_AMOUNT",length=100)
	@ModelProperty(comment = "发票票面限额")
	BigDecimal invoiceLimitAmount;
	
	@Column(name = "INVOICE_REQ_TYPE",length=36)
	@ModelProperty(comment = "申请开票类型(柜台/特殊/自动)")
	String invoiceReqType;
	
	@Column(name = "CUSTOMER_ID",length=36)
	@ModelProperty(comment = "购方名称ID")
	String customerId;
	
	@Column(name = "CUSTOMER_NUMBER")
	@ModelProperty(comment = "购方编码")
	String customerNumber;
	
	@Column(name = "CUST_DEPOSIT_BANK_NUMBER")
	@ModelProperty(comment = "购方开户银行代码")
	String custDepositBankNumber;
	
	@Column(name = "CUST_DEPOSIT_BANK_NAME")
	@ModelProperty(comment = "购方开户银行代码")
	String custDepositBankName;
	
	@Column(name = "CUST_DEPOSIT_BANK_ACCOUNT_NUM")
	@ModelProperty(comment = "购方开户账号")
	String custDepositBankAccountNum;
	
	@Column(name = "CUST_REGISTRATION_ADDRESS")
	@ModelProperty(comment = "购方纳税人地址")
	String custRegistrationAddress;
	
	
	@Column(name = "CUST_CONTACT_PHONE")
	@ModelProperty(comment = "购方纳税人联系电话")
	String custContactPhone;
	
	
	@Column(name = "CUST_CONTACT_NAME")
	@ModelProperty(comment = "购方纳税人联系联系人")
	String custContactName;
	
	@Column(name = "BANK_BRANCH_NAME")
	@ModelProperty(comment = "销方开户银行")
	String bankBranchName;
	
	@Column(name = "BANK_ACCOUNT_NUM")
	@ModelProperty(comment = "销方开户银行账号")
	String bankAccountNum;
	
	
	@Column(name = "REGISTRATION_CONTACT_PHONE")
	@ModelProperty(comment = "销方地址联系电话")
	String registrationContactPhone;
	
	
	@Column(name = "REGISTRATION_CONTACT_ADDRESS")
	@ModelProperty(comment = "销方地址")
	String registrationContactAddress;
	
	
	@Column(name = "CUST_REGISTRATION_NUMBER",length=100)
	@ModelProperty(comment = "购方纳税识别号")
	String custRegistrationNumber;
	
	@Column(name = "CUST_REGISTRATION_CODE",length=100)
	@ModelProperty(comment = "购方纳税识别号类型_枚举值")
	String custRegistrationCode;
	
	@Column(name = "CUSTOMER_NAME",length=500)
	@ModelProperty(comment = "购方纳税名称 ")
	String customerName;
	
	@Column(name = "IS_EXITS_CUSTOMER",length=10)
	@ModelProperty(comment = "是否无主交易 ")
	String isExistCustomer;
	
	@Column(name = "LEGAL_ENTITY_ID",length=36)
	@ModelProperty(comment = "销方纳税人ID ")
	String legalEntityId;
	
	@Column(name = "LEGAL_ENTITY_CODE",length=100)
	@ModelProperty(comment = "销方纳税人编码")
	String legalEntityCode;
	
	@Column(name = "LEGAL_ENTITY_NAME",length=500)
	@ModelProperty(comment = "销方纳税名称")
	String legalEntityName;
	
	@Column(name = "REGISTRATION_NUMBER",length=100)
	@ModelProperty(comment = "销方纳税人识别号")
	String registrationNumber;
	
	@Column(name = "REGISTRATION_CODE",length=100)
	@ModelProperty(comment = "销方纳税人识别号类型")
	String registrationCode;
	
	@Column(name = "EQUIPMENT_ID",length=100)
	@ModelProperty(comment = "终端设备ID")
	String equipmentId;
	
	@Column(name = "EQUIPMENT_CODE",length=100)
	@ModelProperty(comment = "终端设备编号")
	String equipmentCode;
	
	@Column(name = "EQUIPMENT_NAME",length=100)
	@ModelProperty(comment = "终端设备名称")
	String equipmentName;
	
	@Column(name = "INVOICE_PRINT_STATUS",length=100)
	@ModelProperty(comment = "开据状态")
	String invoicePrintStatus;
	
	@Column(name = "UPLOAD_STATUS",length=100)
	@ModelProperty(comment = "上传状态")
	String uploadStatus;
	

	
	@Column(name = "INVOICE_PRINT_BY",length=100)
	@ModelProperty(comment = "发票开据人")
	String invoicePrintBy;
	
	@Column(name = "INVOICE_PRINT_DATE",length=100)
	@ModelProperty(comment = "发票开据日期")
	Date invoicePrintDate;
	
	@Column(name = "SOURCE_CODE",length=100)
	@ModelProperty(comment = "来源系统")
	String sourceCode;
	
	@Column(name = "SOURCE_TRX_ID",length=100)
	@ModelProperty(comment = "来源数据头ID")
	String sourceTrxId;
	
	@Column(name = "SOURCE_TRX_LINE_ID",length=100)
	@ModelProperty(comment = "来源数据行ID")
	String sourceTrxLineId;
	
	@Column(name = "SOURCE_TRX_LINE_TYPE",length=100)
	@ModelProperty(comment = "来源数据行类型")
	String sourceTrxLineType;
	
	@Column(name = "SOURCE_TRX_DETAIL_TAX_LINE_ID",length=100)
	@ModelProperty(comment = "来源数据明细税ID")
	String sourceTrxDetailTaxLineId;
	
	@Column(name = "SOURCE_DISTRIBUTION_ID",length=100)
	@ModelProperty(comment = "来源数据明细ID")
	String sourceDistributionId;
	
	
	@Column(name = "START_DATE")
	@ModelProperty(comment = "有效日期")
	Date startDate;
	
	@Column(name = "END_DATE")
	@ModelProperty(comment = "失效日期")
	Date endDate;
	
	
	@OneToMany(mappedBy = "invoicePrintPoolH")
	@Cascade(CascadeType.REFRESH)
	@Where(clause="DELETED_FLAG=1")
	Collection<InvoicePrintPoolL> invoicePrintPoolLs = new ArrayList<InvoicePrintPoolL>();
	
	@ManyToOne
	@Cascade(CascadeType.REFRESH)
	@JoinColumn(name="CUSTOMER_ID",insertable=false,updatable=false,nullable=true)
	Customer customer;
	
	@Transient
	BigDecimal totalAmount;
	
	
	public Collection<InvoicePrintPoolL> getInvoicePrintPoolLs() {
		return invoicePrintPoolLs;
	}

	public void setInvoicePrintPoolLs(
			Collection<InvoicePrintPoolL> invoicePrintPoolLs) {
		this.invoicePrintPoolLs = invoicePrintPoolLs;
	}


	public String getInventoryInvoiceId() {
		return inventoryInvoiceId;
	}

	public void setInventoryInvoiceId(String inventoryInvoiceId) {
		this.inventoryInvoiceId = inventoryInvoiceId;
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

	public String getInvoiceCategory() {
		return invoiceCategory;
	}

	public void setInvoiceCategory(String invoiceCategory) {
		this.invoiceCategory = invoiceCategory;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}


	public String getInvoicingType() {
		return invoicingType;
	}

	public void setInvoicingType(String invoicingType) {
		this.invoicingType = invoicingType;
	}

	public BigDecimal getInvoiceLimitAmount() {
		return invoiceLimitAmount;
	}

	public void setInvoiceLimitAmount(BigDecimal invoiceLimitAmount) {
		this.invoiceLimitAmount = invoiceLimitAmount;
	}

	public String getInvoiceReqType() {
		return invoiceReqType;
	}

	public void setInvoiceReqType(String invoiceReqType) {
		this.invoiceReqType = invoiceReqType;
	}


	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustRegistrationNumber() {
		return custRegistrationNumber;
	}

	public void setCustRegistrationNumber(String custRegistrationNumber) {
		this.custRegistrationNumber = custRegistrationNumber;
	}

	public String getCustRegistrationCode() {
		return custRegistrationCode;
	}

	public void setCustRegistrationCode(String custRegistrationCode) {
		this.custRegistrationCode = custRegistrationCode;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getIsExistCustomer() {
		return isExistCustomer;
	}

	public void setIsExistCustomer(String isExistCustomer) {
		this.isExistCustomer = isExistCustomer;
	}

	public String getLegalEntityId() {
		return legalEntityId;
	}

	public void setLegalEntityId(String legalEntityId) {
		this.legalEntityId = legalEntityId;
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

	public String getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(String equipmentId) {
		this.equipmentId = equipmentId;
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


	public String getInvoicePrintStatus() {
		return invoicePrintStatus;
	}

	public void setInvoicePrintStatus(String invoicePrintStatus) {
		this.invoicePrintStatus = invoicePrintStatus;
	}

	public String getUploadStatus() {
		return uploadStatus;
	}

	public void setUploadStatus(String uploadStatus) {
		this.uploadStatus = uploadStatus;
	}


	public String getCrvatInvoicePreHId() {
		return crvatInvoicePreHId;
	}

	public void setCrvatInvoicePreHId(String crvatInvoicePreHId) {
		this.crvatInvoicePreHId = crvatInvoicePreHId;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public String getRegistrationCode() {
		return registrationCode;
	}

	public void setRegistrationCode(String registrationCode) {
		this.registrationCode = registrationCode;
	}



	public String getInvoicePrintBy() {
		return invoicePrintBy;
	}

	public void setInvoicePrintBy(String invoicePrintBy) {
		this.invoicePrintBy = invoicePrintBy;
	}

	public Date getInvoicePrintDate() {
		return invoicePrintDate;
	}

	public void setInvoicePrintDate(Date invoicePrintDate) {
		this.invoicePrintDate = invoicePrintDate;
	}

	public String getSourceCode() {
		return sourceCode;
	}

	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}

	public String getSourceTrxId() {
		return sourceTrxId;
	}

	public void setSourceTrxId(String sourceTrxId) {
		this.sourceTrxId = sourceTrxId;
	}

	public String getSourceTrxLineId() {
		return sourceTrxLineId;
	}

	public void setSourceTrxLineId(String sourceTrxLineId) {
		this.sourceTrxLineId = sourceTrxLineId;
	}

	public String getSourceTrxLineType() {
		return sourceTrxLineType;
	}

	public void setSourceTrxLineType(String sourceTrxLineType) {
		this.sourceTrxLineType = sourceTrxLineType;
	}

	public String getSourceTrxDetailTaxLineId() {
		return sourceTrxDetailTaxLineId;
	}

	public void setSourceTrxDetailTaxLineId(String sourceTrxDetailTaxLineId) {
		this.sourceTrxDetailTaxLineId = sourceTrxDetailTaxLineId;
	}

	public String getSourceDistributionId() {
		return sourceDistributionId;
	}

	public void setSourceDistributionId(String sourceDistributionId) {
		this.sourceDistributionId = sourceDistributionId;
	}


	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public TmsCrvatInvoicePreH getCrvatInvoicePreH() {
		return crvatInvoicePreH;
	}

	public void setCrvatInvoicePreH(TmsCrvatInvoicePreH crvatInvoicePreH) {
		this.crvatInvoicePreH = crvatInvoicePreH;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}

	public String getCustDepositBankNumber() {
		return custDepositBankNumber;
	}

	public void setCustDepositBankNumber(String custDepositBankNumber) {
		this.custDepositBankNumber = custDepositBankNumber;
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

	public String getCustContactName() {
		return custContactName;
	}

	public void setCustContactName(String custContactName) {
		this.custContactName = custContactName;
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
	
	
	
	
}
