
/**    
 * Copyright (C),Deloitte
 * @FileName: Customer.java  
 * @Package: com.deloitte.tms.base.masterdata.model  
 * @Description: //模块目的、功能描述  
 * @Author liazhou  
 * @Date 2016年3月16日 上午10:39:08  
 * @History: //修改记录
 * 〈author〉      〈time〉      〈version〉       〈desc〉
 * 修改人姓名            修改时间            版本号              描述   
*/  

package com.deloitte.tms.base.masterdata.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.deloitte.tms.pl.core.commons.annotation.ModelProperty;
import com.deloitte.tms.pl.core.commons.constant.TablePreDef;
import com.deloitte.tms.pl.core.hibernate.identifier.Ling2UUIDGenerator;
import com.deloitte.tms.pl.core.model.impl.BaseEntity;


/**  
 *〈一句话功能简述〉
 * 功能详细描述
 * @author liazhou
 * @create 2016年3月16日 上午10:39:08 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Entity
@Table(name = Customer.TABLE)
@ModelProperty(comment = "客户关系")
public class Customer extends BaseEntity{
	/**
	 * 
	 */
	public static final String TABLE = TablePreDef.TMS_MD_TAG+"CUSTOMER";
	public static final String SEQ = TABLE;
	
	@Id
	@GenericGenerator(name=SEQ+"_GENERATOR",strategy=Ling2UUIDGenerator.STRATEGY_NAME,parameters={@Parameter(name="pkColumnValue",value=SEQ)})
	@GeneratedValue(strategy = GenerationType.TABLE, generator = SEQ+"_GENERATOR")
	@Column(name = "CUSTOMER_ID",  length = 36)
	private String id;

	
	@Column(name="CUST_DEPOSIT_BANK_ACCOUNT_NUM")
	@ModelProperty(comment="购方开户账号")
	private String custDepositBankAccountNum;

	@Column(name="CUST_DEPOSIT_BANK_NAME")
	@ModelProperty(comment="购方开户银行名称")
	private String custDepositBankName;

	@Column(name="CUST_DEPOSIT_BANK_NUMBER")
	@ModelProperty(comment="购方开户银行代码")
	private String custDepositBankNumber;

	
	@Column(name = "CUST_REGISTRATION_CODE")
	@ModelProperty(comment = "购方开票使用证件类型_枚举值(统一社会信用代码/纳税人识别号)")
	private String custRegistrationCode;
	
	@Column(name = "IS_INVOICE_PROVIDING")
	@ModelProperty(comment = "是否开具发票 Y/N")
	private String isInvoiceProviding;
	
	@Column(name = "CUST_REGISTRATION_DATE")
	@ModelProperty(comment = "登记日期")
	private Date custRegistrationDate;
	
	@Column(name = "CUST_REGISTRATION_NUMBER")
	@ModelProperty(comment = "购方纳税人识别号")
	private String custRegistrationNumber;

	@Column(name = "CUSTOMER_NAME")
	@ModelProperty(comment = "购方名称")
	private String customerName;

	@Column(name = "CUSTOMER_NUMBER")
	@ModelProperty(comment = "购方编码")
	private String customerNumber;

	@Column(name = "CUSTOMER_TYPE")
	@ModelProperty(comment = "购方类型_枚举值(公司/个人)")
	private String customerType;

	
	@Column(name = "ENABLED_FLAG")
	@ModelProperty(comment = "是否有效(Y/N)")
	private String enabledFlag;

	@Column(name = "CUST_LEGAL_ENTITY_TYPE ")
	@ModelProperty(comment = "购方纳税人类型_枚举值(增值税一般纳税人/小规模纳税人/非增值税纳税人) ")
	private String custLegalEntityType;

	@Column(name = "CUST_REGISTRATION_ADDRESS")
	@ModelProperty(comment = "购方纳税人地址")
	private String custRegistrationAddress;
	
	@Column(name = "CONTACT_NAME",length=100)
	@ModelProperty(comment = "购方纳税人联系人 ")
	private String contactName;
	
	@Column(name = "CONTACT_PHONE",length=100)
	@ModelProperty(comment = "购方纳税人联系电话 ")
	private String contactPhone;
	

	@Column(name="GSN_REGISTRATION_NUMBER")
	@ModelProperty(comment="购方统一社会信用代码")
	private String gsnRegistrationNumber;

	@Column(name="INVOICING_TYPE")
	@ModelProperty(comment="开票方式_枚举值")
	private String invoicingType;
	
	@Column(name = "START_DATE")
	@ModelProperty(comment = "有效日期")
	private Date startDate;
	
	@Column(name = "END_DATE")
	@ModelProperty(comment = "失效日期")
	private Date endDate;
	
	@Column(name = "IS_APPOINT_INVOICE")
	@ModelProperty(comment = "是否预约开票")
	private String isAppointInvoice;
	
	@Column(name="APPOINT_END_DATE")
	@ModelProperty(comment = "预约结束时间")
	private Date appointEndDate;

	@Column(name="APPOINT_INTERVAL_UOM_CODE")
	@ModelProperty(comment="预约开票间隔单位")
	private String appointIntervalUomCode;

	@Column(name="APPOINT_INVOICE_CATEGORY")
	@ModelProperty(comment="预约开票范围")
	private String appointInvoiceCategory;

	@Column(name="APPOINT_INVOICE_ORG_CODE")
	@ModelProperty(comment="预约开票管理组织代码 ")
	private String appointInvoiceOrgCode;

	@Column(name="APPOINT_RUN_INTERVAL")
	@ModelProperty(comment="预约开票运行间隔")
	private String appointRunInterval;

	@Column(name="APPOINT_START_DATE")
	@ModelProperty(comment="预约开始日期")
	private Date appointStartDate;


	@Column(name = "BILLING_PERIOD",length=100)
	@ModelProperty(comment = "开票周期_枚举值(M:月/S:季度)")
	private String billingPeriod;


	@Column(name="PRINT_PERIOD_NAME")
	@ModelProperty(comment="发票打印频率")
	private String printPeriodName;

	@Column(name="REQ_INVOICE_RANGE")
	@ModelProperty(comment="发票打印受理层级范围_枚举值 ")
	private String reqInvoiceRange;

	@Column(name="SOURCE_CODE")
	@ModelProperty(comment="来源类型(手工/财务系统/资金系统)")
	private String sourceCode;

	@Column(name="SOURCE_CUSTOMER_CODE")
	@ModelProperty(comment="来源购方代码")
	private String sourceCustomerCode;

	@Column(name="SOURCE_CUSTOMER_ID")
	@ModelProperty(comment="来源购方ID")
	private String sourceCustomerId;

	@Column(name="SOURCE_CUSTOMER_NAME")
	@ModelProperty(comment="来源购方名称")
	private String sourceCustomerName;

	@Column(name="SOURCE_CUSTOMER_TYPE")
	@ModelProperty(comment="来源购方类型")
	private String sourceCustomerType;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getAppointEndDate() {
		return appointEndDate;
	}

	public void setAppointEndDate(Date appointEndDate) {
		this.appointEndDate = appointEndDate;
	}

	public String getAppointIntervalUomCode() {
		return appointIntervalUomCode;
	}

	public void setAppointIntervalUomCode(String appointIntervalUomCode) {
		this.appointIntervalUomCode = appointIntervalUomCode;
	}

	public String getAppointInvoiceCategory() {
		return appointInvoiceCategory;
	}

	public void setAppointInvoiceCategory(String appointInvoiceCategory) {
		this.appointInvoiceCategory = appointInvoiceCategory;
	}

	public String getAppointInvoiceOrgCode() {
		return appointInvoiceOrgCode;
	}

	public void setAppointInvoiceOrgCode(String appointInvoiceOrgCode) {
		this.appointInvoiceOrgCode = appointInvoiceOrgCode;
	}

	public String getAppointRunInterval() {
		return appointRunInterval;
	}

	public void setAppointRunInterval(String appointRunInterval) {
		this.appointRunInterval = appointRunInterval;
	}

	public Date getAppointStartDate() {
		return appointStartDate;
	}

	public void setAppointStartDate(Date appointStartDate) {
		this.appointStartDate = appointStartDate;
	}


	public String getBillingPeriod() {
		return billingPeriod;
	}

	public void setBillingPeriod(String billingPeriod) {
		this.billingPeriod = billingPeriod;
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

	public String getCustLegalEntityType() {
		return custLegalEntityType;
	}

	public void setCustLegalEntityType(String custLegalEntityType) {
		this.custLegalEntityType = custLegalEntityType;
	}

	public String getCustRegistrationAddress() {
		return custRegistrationAddress;
	}

	public void setCustRegistrationAddress(String custRegistrationAddress) {
		this.custRegistrationAddress = custRegistrationAddress;
	}

	public String getCustRegistrationCode() {
		return custRegistrationCode;
	}

	public void setCustRegistrationCode(String custRegistrationCode) {
		this.custRegistrationCode = custRegistrationCode;
	}

	public Date getCustRegistrationDate() {
		return custRegistrationDate;
	}

	public void setCustRegistrationDate(Date custRegistrationDate) {
		this.custRegistrationDate = custRegistrationDate;
	}

	public String getCustRegistrationNumber() {
		return custRegistrationNumber;
	}

	public void setCustRegistrationNumber(String custRegistrationNumber) {
		this.custRegistrationNumber = custRegistrationNumber;
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

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public String getEnabledFlag() {
		return enabledFlag;
	}

	public void setEnabledFlag(String enabledFlag) {
		this.enabledFlag = enabledFlag;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getGsnRegistrationNumber() {
		return gsnRegistrationNumber;
	}

	public void setGsnRegistrationNumber(String gsnRegistrationNumber) {
		this.gsnRegistrationNumber = gsnRegistrationNumber;
	}

	public String getInvoicingType() {
		return invoicingType;
	}

	public void setInvoicingType(String invoicingType) {
		this.invoicingType = invoicingType;
	}

	public String getIsAppointInvoice() {
		return isAppointInvoice;
	}

	public void setIsAppointInvoice(String isAppointInvoice) {
		this.isAppointInvoice = isAppointInvoice;
	}

	public String getIsInvoiceProviding() {
		return isInvoiceProviding;
	}

	public void setIsInvoiceProviding(String isInvoiceProviding) {
		this.isInvoiceProviding = isInvoiceProviding;
	}

	public String getPrintPeriodName() {
		return printPeriodName;
	}

	public void setPrintPeriodName(String printPeriodName) {
		this.printPeriodName = printPeriodName;
	}

	public String getReqInvoiceRange() {
		return reqInvoiceRange;
	}

	public void setReqInvoiceRange(String reqInvoiceRange) {
		this.reqInvoiceRange = reqInvoiceRange;
	}

	public String getSourceCode() {
		return sourceCode;
	}

	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}

	public String getSourceCustomerCode() {
		return sourceCustomerCode;
	}

	public void setSourceCustomerCode(String sourceCustomerCode) {
		this.sourceCustomerCode = sourceCustomerCode;
	}

	public String getSourceCustomerId() {
		return sourceCustomerId;
	}

	public void setSourceCustomerId(String sourceCustomerId) {
		this.sourceCustomerId = sourceCustomerId;
	}

	public String getSourceCustomerName() {
		return sourceCustomerName;
	}

	public void setSourceCustomerName(String sourceCustomerName) {
		this.sourceCustomerName = sourceCustomerName;
	}

	public String getSourceCustomerType() {
		return sourceCustomerType;
	}

	public void setSourceCustomerType(String sourceCustomerType) {
		this.sourceCustomerType = sourceCustomerType;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	
}
