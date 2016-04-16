package com.deloitte.tms.base.masterdata.model;

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

@Entity
@Table(name = CustomerSite.TABLE)
@ModelProperty(comment = "购方地址信息")
public class CustomerSite extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String TABLE = TablePreDef.TMS_MD_TAG+"CUST_SITE";
	public static final String SEQ = TABLE;
	
	@Id
	@GenericGenerator(name=SEQ+"_GENERATOR",strategy=Ling2UUIDGenerator.STRATEGY_NAME,parameters={@Parameter(name="pkColumnValue",value=SEQ)})
	@GeneratedValue(strategy = GenerationType.TABLE, generator = SEQ+"_GENERATOR")
	@Column(name = "CUSTOMER_SITE_ID")
	private String id;
	
	@Column(name="ENABLED_FLAG")
	@ModelProperty(comment="是否有效(Y/N)")
	private String enabledFlag;
	
	@Column(name="CUSTOMER_ID")
	@ModelProperty(comment="客户ID")
	private String customerId;
	 

	@Column(name="IS_DEFAULT")
	@ModelProperty(comment="是否默认")
	private String isDefault;

	@Column(name="RECIPIENT_ADDR")
	@ModelProperty(comment="收件人地址")
	private String recipientAddr;

	@Column(name="RECIPIENT_ADDR_ALT")
	@ModelProperty(comment="收件人地址简称")
	private String recipientAddrAlt;

	@Column(name="RECIPIENT_COMP")
	@ModelProperty(comment="收件人公司")
	private String recipientComp;

	@Column(name="RECIPIENT_NAME")
	@ModelProperty(comment="收件人姓名")
	private String recipientName;

	@Column(name="RECIPIENT_PHONE")
	@ModelProperty(comment="收件人电话")
	private String recipientPhone;

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

	@Column(name="SOURCE_CUSTOMER_SITE_ID")
	@ModelProperty(comment="来源购方地址 ")
	private String sourceCustomerSiteId;

	@Column(name="SOURCE_CUSTOMER_TYPE")
	@ModelProperty(comment="来源购方类型 ")
	private String sourceCustomerType;

	@Column(name="ZIP_CODE")
	@ModelProperty(comment="邮编 ")
	private String zipCode;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getEnabledFlag() {
		return enabledFlag;
	}

	public void setEnabledFlag(String enabledFlag) {
		this.enabledFlag = enabledFlag;
	}

	public String getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getRecipientAddr() {
		return recipientAddr;
	}

	public void setRecipientAddr(String recipientAddr) {
		this.recipientAddr = recipientAddr;
	}

	public String getRecipientAddrAlt() {
		return recipientAddrAlt;
	}

	public void setRecipientAddrAlt(String recipientAddrAlt) {
		this.recipientAddrAlt = recipientAddrAlt;
	}

	public String getRecipientComp() {
		return recipientComp;
	}

	public void setRecipientComp(String recipientComp) {
		this.recipientComp = recipientComp;
	}

	public String getRecipientName() {
		return recipientName;
	}

	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}

	public String getRecipientPhone() {
		return recipientPhone;
	}

	public void setRecipientPhone(String recipientPhone) {
		this.recipientPhone = recipientPhone;
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

	public String getSourceCustomerSiteId() {
		return sourceCustomerSiteId;
	}

	public void setSourceCustomerSiteId(String sourceCustomerSiteId) {
		this.sourceCustomerSiteId = sourceCustomerSiteId;
	}

	public String getSourceCustomerType() {
		return sourceCustomerType;
	}

	public void setSourceCustomerType(String sourceCustomerType) {
		this.sourceCustomerType = sourceCustomerType;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
}
