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

@Entity
@Table(name = CustBankAccount.TABLE)
@ModelProperty(comment = "购方资金账户信息")
public class CustBankAccount extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String TABLE = TablePreDef.TMS_MD_TAG+"CUST_BANK_ACCOUNTS";
	public static final String SEQ = TABLE;
	
	@Id
	@GenericGenerator(name=SEQ+"_GENERATOR",strategy=Ling2UUIDGenerator.STRATEGY_NAME,parameters={@Parameter(name="pkColumnValue",value=SEQ)})
	@GeneratedValue(strategy = GenerationType.TABLE, generator = SEQ+"_GENERATOR")
	@Column(name = "CUST_BANK_ACCOUNT_ID")
	private String id;
	
	@Column(name="CUST_BANK_ACCOUNT_NUM")
	@ModelProperty(comment="购方银行或资金账户号码 ")
	private String custBankAccountNum;

	@Column(name="CUST_BANK_NAME")
	@ModelProperty(comment="购方银行或资金账户名称 ")
	private String custBankName;

	@Column(name="CUST_BANK_NUMBER")
	@ModelProperty(comment="购方银行或资金账户代码 ")
	private String custBankNumber;
	
	@Column(name="CUST_BANK_ORG_CODE",length=100)
	@ModelProperty(comment="购方银行或资金账户所属机构号")
	private String custBankOrgCode;	

	@Column(name="CUSTOMER_ID")
	@ModelProperty(comment="购方ID ")
	private String customerId;

	@Column(name="ENABLED_FLAG")
	@ModelProperty(comment="是否有效(Y/N)")
	private String enabledFlag;

	@Column(name="END_DATE")
	@ModelProperty(comment="失效日期")
	private Date endDate;

	@Column(name="SOURCE_CODE")
	@ModelProperty(comment="来源类型(手工/财务系统/资金系统)")
	private String sourceCode;

	@Column(name="SOURCE_CUST_BANK_ACCOUNT_ID")
	@ModelProperty(comment="来源购方账户ID")
	private String sourceCustBankAccountId;

	@Column(name="SOURCE_CUSTOMER_CODE")
	@ModelProperty(comment="来源购方代码 ")
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

	@Column(name="START_DATE")
	@ModelProperty(comment="有效日期 ")
	private Date startDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getCustBankAccountNum() {
		return custBankAccountNum;
	}

	public void setCustBankAccountNum(String custBankAccountNum) {
		this.custBankAccountNum = custBankAccountNum;
	}

	public String getCustBankName() {
		return custBankName;
	}

	public void setCustBankName(String custBankName) {
		this.custBankName = custBankName;
	}

	public String getCustBankNumber() {
		return custBankNumber;
	}

	public void setCustBankNumber(String custBankNumber) {
		this.custBankNumber = custBankNumber;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
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

	public String getSourceCode() {
		return sourceCode;
	}

	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}

	public String getSourceCustBankAccountId() {
		return sourceCustBankAccountId;
	}

	public void setSourceCustBankAccountId(String sourceCustBankAccountId) {
		this.sourceCustBankAccountId = sourceCustBankAccountId;
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

	public String getCustBankOrgCode() {
		return custBankOrgCode;
	}

	public void setCustBankOrgCode(String custBankOrgCode) {
		this.custBankOrgCode = custBankOrgCode;
	}


}
