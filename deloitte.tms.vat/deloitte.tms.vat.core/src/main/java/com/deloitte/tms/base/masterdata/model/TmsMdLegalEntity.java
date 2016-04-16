package com.deloitte.tms.base.masterdata.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.deloitte.tms.pl.core.commons.annotation.ModelProperty;
import com.deloitte.tms.pl.core.commons.constant.TablePreDef;
import com.deloitte.tms.pl.core.hibernate.identifier.Ling2UUIDGenerator;
import com.deloitte.tms.pl.core.model.impl.BaseEntity;


/**
 * The persistent class for the TMS_MD_LEGAL_ENTITY database table.
 * 
 */
@Entity
@Table(name=TmsMdLegalEntity.TABLE)
@ModelProperty(comment = "纳税主体表")
public class TmsMdLegalEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String TABLE = TablePreDef.TMS_MD_TAG+"LEGAL_ENTITY";//BASE_LEGAL_ENTITY, TMS_MD_LEGAL_ENTITY
	public static final String SEQ = TABLE;

	@Id
	@GenericGenerator(name=SEQ+"_GENERATOR",strategy=Ling2UUIDGenerator.STRATEGY_NAME,parameters={@Parameter(name="pkColumnValue",value=SEQ)})
	@GeneratedValue(strategy = GenerationType.TABLE, generator = SEQ+"_GENERATOR")
	@Column(name="LEGAL_ENTITY_ID")
	private String id;

	@Column(name="ACCOUNT_MODE_ID")
	private String accountModeId;

	@Column(name="ADMIN_INDUSTRY_COMMERCE")
	private String adminIndustryCommerce;

	@Column(name="AGENCY_TYPE_ID")
	private String agencyTypeId;

	@Column(name="ANNUAL_PAYMENT_AMOUNT")
	private BigDecimal annualPaymentAmount;

	@Column(name="ASSETS_RELATION_ID")
	private String assetsRelationId;

	@Column(name="BANK_ACCOUNT_NUM")
	private String bankAccountNum;

	@Column(name="BANK_BRANCH_NAME")
	private String bankBranchName;

	@Column(name="BIZ_ORG_ID")
	private String bizOrgId;

	@Column(name="BUSINESS_SCOPE_ID")
	private String businessScopeId;

	@Column(name="BUSINESS_TAX_RELATION")
	private String businessTaxRelation;

	@Column(name="COMPANY_TYPE_ID")
	private String companyTypeId;

	@Column(name="CONTACT_ADDRESS")
	private String contactAddress;

	@Column(name="CUST_REGISTRATION_CODE")
	private String custRegistrationCode;

	@Column(name="CUST_REGISTRATION_NUMBER")
	private String custRegistrationNumber;
	
	@Column(name="DESCRIPTION")
	private String description;

	@Column(name="ENTERPRISE_CHANGE_ID")
	private String enterpriseChangeId;

	@Column(name="GBTRADE_AFFILIATED_INDUSTRY")
	private String gbtradeAffiliatedIndustry;

	@Column(name="GBTRADE_PRIMARY_INDUSTRY")
	private String gbtradePrimaryIndustry;

	@Column(name="GROUP_CUST_REGISTRATION_NUMBER")
	private String groupCustRegistrationNumber;

	@Column(name="INCOME_TAX_RELATION")
	private String incomeTaxRelation;

	@Column(name="INVESTOR_NAME")
	private String investorName;

	@Column(name="INVOICE_LIMIT_AMOUNT")
	private BigDecimal invoiceLimitAmount;

	@Column(name="IS_ENABLE_PRINT")
	private String isEnablePrint;

	@Column(name="IS_INDEPENDENT_TAX")
	private String isIndependentTax;
	
	@Deprecated
	@Column(name="IS_PAYMENT_COLLECT")
	private String isPaymentCollect;

	@Column(name="IS_VAT")
	private String isVat;

	@Column(name="LEGAL_ENTITY_CODE")
	private String legalEntityCode;

	@Column(name="LEGAL_ENTITY_NAME")
	private String legalEntityName;

	@Column(name="LEGAL_ENTITY_TYPE")
	private String legalEntityType;

	@ModelProperty(comment="证照名称")
	@Column(name="LICENSE_NAME")
	private String licenseName;

	@ModelProperty(comment="证照号码")
	@Column(name="LICENSE_NO")
	private String licenseNo;

	@Column(name="LOCAL_ACGENCY")
	private String localAcgency;

	@Column(name="NUMBER_OF_EMPLOYEES")
	private BigDecimal numberOfEmployees;

	@Column(name="ORG_CODE")
	private String orgCode;

	@Deprecated
	@Column(name="PARENT_ID")
	private String parentId;

	@Column(name="PAY_METHOD_ID")
	private String payMethodId;

	@Column(name="REGISTRATION_CAPITAL_INVESTMEN")
	private BigDecimal registrationCapitalInvestmen;

	@Column(name="REGISTRATION_CODE")
	private String registrationCode;

	@Column(name="REGISTRATION_CONTACT_ADDRESS")
	private String registrationContactAddress;

	@Column(name="REGISTRATION_CONTACT_PHONE")
	private String registrationContactPhone;

	@Column(name="REGISTRATION_NUMBER")
	private String registrationNumber;

	@Column(name="REGISTRATION_TYPE_ID")
	private String registrationTypeId;

	@Column(name="TAXPAYER_NAME")
	private String taxpayerName;

	@Column(name="ZIP_CODE")
	private String zipCode;

	 @ManyToOne(fetch = FetchType.LAZY)
	@Cascade(CascadeType.REFRESH)
	@JoinColumn(name="LEGAL_ENTITY_ID",insertable=false,updatable=false,nullable=true)
	private TmsMdOrgLegalEntity tmsMdOrgLegalEntity;
	
	
	
	//bi-directional one-to-one association to TmsMdLegalEquipment

	//private TmsMdLegalEquipment tmsMdLegalEquipment;

	public TmsMdLegalEntity() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccountModeId() {
		return this.accountModeId;
	}

	public void setAccountModeId(String accountModeId) {
		this.accountModeId = accountModeId;
	}

	public String getAdminIndustryCommerce() {
		return this.adminIndustryCommerce;
	}

	public void setAdminIndustryCommerce(String adminIndustryCommerce) {
		this.adminIndustryCommerce = adminIndustryCommerce;
	}

	public String getAgencyTypeId() {
		return this.agencyTypeId;
	}

	public void setAgencyTypeId(String agencyTypeId) {
		this.agencyTypeId = agencyTypeId;
	}

	public BigDecimal getAnnualPaymentAmount() {
		return this.annualPaymentAmount;
	}

	public void setAnnualPaymentAmount(BigDecimal annualPaymentAmount) {
		this.annualPaymentAmount = annualPaymentAmount;
	}

	public String getAssetsRelationId() {
		return this.assetsRelationId;
	}

	public void setAssetsRelationId(String assetsRelationId) {
		this.assetsRelationId = assetsRelationId;
	}

	public String getBankAccountNum() {
		return this.bankAccountNum;
	}

	public void setBankAccountNum(String bankAccountNum) {
		this.bankAccountNum = bankAccountNum;
	}

	public String getBankBranchName() {
		return this.bankBranchName;
	}

	public void setBankBranchName(String bankBranchName) {
		this.bankBranchName = bankBranchName;
	}

	public String getBizOrgId() {
		return this.bizOrgId;
	}

	public void setBizOrgId(String bizOrgId) {
		this.bizOrgId = bizOrgId;
	}

	public String getBusinessScopeId() {
		return this.businessScopeId;
	}

	public void setBusinessScopeId(String businessScopeId) {
		this.businessScopeId = businessScopeId;
	}

	public String getBusinessTaxRelation() {
		return this.businessTaxRelation;
	}

	public void setBusinessTaxRelation(String businessTaxRelation) {
		this.businessTaxRelation = businessTaxRelation;
	}

	public String getCompanyTypeId() {
		return this.companyTypeId;
	}

	public void setCompanyTypeId(String companyTypeId) {
		this.companyTypeId = companyTypeId;
	}

	public String getContactAddress() {
		return this.contactAddress;
	}

	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}

	public String getCustRegistrationCode() {
		return this.custRegistrationCode;
	}

	public void setCustRegistrationCode(String custRegistrationCode) {
		this.custRegistrationCode = custRegistrationCode;
	}

	public String getCustRegistrationNumber() {
		return this.custRegistrationNumber;
	}

	public void setCustRegistrationNumber(String custRegistrationNumber) {
		this.custRegistrationNumber = custRegistrationNumber;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEnterpriseChangeId() {
		return this.enterpriseChangeId;
	}

	public void setEnterpriseChangeId(String enterpriseChangeId) {
		this.enterpriseChangeId = enterpriseChangeId;
	}

	public String getGbtradeAffiliatedIndustry() {
		return this.gbtradeAffiliatedIndustry;
	}

	public void setGbtradeAffiliatedIndustry(String gbtradeAffiliatedIndustry) {
		this.gbtradeAffiliatedIndustry = gbtradeAffiliatedIndustry;
	}

	public String getGbtradePrimaryIndustry() {
		return this.gbtradePrimaryIndustry;
	}

	public void setGbtradePrimaryIndustry(String gbtradePrimaryIndustry) {
		this.gbtradePrimaryIndustry = gbtradePrimaryIndustry;
	}

	public String getGroupCustRegistrationNumber() {
		return this.groupCustRegistrationNumber;
	}

	public void setGroupCustRegistrationNumber(String groupCustRegistrationNumber) {
		this.groupCustRegistrationNumber = groupCustRegistrationNumber;
	}

	public String getIncomeTaxRelation() {
		return this.incomeTaxRelation;
	}

	public void setIncomeTaxRelation(String incomeTaxRelation) {
		this.incomeTaxRelation = incomeTaxRelation;
	}

	public String getInvestorName() {
		return this.investorName;
	}

	public void setInvestorName(String investorName) {
		this.investorName = investorName;
	}

	public BigDecimal getInvoiceLimitAmount() {
		return this.invoiceLimitAmount;
	}

	public void setInvoiceLimitAmount(BigDecimal invoiceLimitAmount) {
		this.invoiceLimitAmount = invoiceLimitAmount;
	}
	public String getIsEnablePrint() {
		return this.isEnablePrint;
	}
	public void setIsEnablePrint(String isEnablePrint) {
		this.isEnablePrint = isEnablePrint;
	}

	public String getIsIndependentTax() {
		return this.isIndependentTax;
	}

	public void setIsIndependentTax(String isIndependentTax) {
		this.isIndependentTax = isIndependentTax;
	}
	@Deprecated
	public String getIsPaymentCollect() {
		return this.isPaymentCollect;
	}
	@Deprecated
	public void setIsPaymentCollect(String isPaymentCollect) {
		this.isPaymentCollect = isPaymentCollect;
	}

	public String getIsVat() {
		return this.isVat;
	}

	public void setIsVat(String isVat) {
		this.isVat = isVat;
	}

	public String getLegalEntityCode() {
		return this.legalEntityCode;
	}

	public void setLegalEntityCode(String legalEntityCode) {
		this.legalEntityCode = legalEntityCode;
	}

	public String getLegalEntityName() {
		return this.legalEntityName;
	}

	public void setLegalEntityName(String legalEntityName) {
		this.legalEntityName = legalEntityName;
	}

	public String getLegalEntityType() {
		return this.legalEntityType;
	}

	public void setLegalEntityType(String legalEntityType) {
		this.legalEntityType = legalEntityType;
	}

	public String getLicenseName() {
		return this.licenseName;
	}

	public void setLicenseName(String licenseName) {
		this.licenseName = licenseName;
	}

	public String getLicenseNo() {
		return this.licenseNo;
	}

	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}

	public String getLocalAcgency() {
		return this.localAcgency;
	}

	public void setLocalAcgency(String localAcgency) {
		this.localAcgency = localAcgency;
	}

	public BigDecimal getNumberOfEmployees() {
		return this.numberOfEmployees;
	}

	public void setNumberOfEmployees(BigDecimal numberOfEmployees) {
		this.numberOfEmployees = numberOfEmployees;
	}

	public String getOrgCode() {
		return this.orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	@Deprecated
	public String getParentId() {
		return this.parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getPayMethodId() {
		return this.payMethodId;
	}

	public void setPayMethodId(String payMethodId) {
		this.payMethodId = payMethodId;
	}

	public BigDecimal getRegistrationCapitalInvestmen() {
		return this.registrationCapitalInvestmen;
	}

	public void setRegistrationCapitalInvestmen(BigDecimal registrationCapitalInvestmen) {
		this.registrationCapitalInvestmen = registrationCapitalInvestmen;
	}

	public String getRegistrationCode() {
		return this.registrationCode;
	}

	public void setRegistrationCode(String registrationCode) {
		this.registrationCode = registrationCode;
	}

	public String getRegistrationContactAddress() {
		return this.registrationContactAddress;
	}

	public void setRegistrationContactAddress(String registrationContactAddress) {
		this.registrationContactAddress = registrationContactAddress;
	}

	public String getRegistrationContactPhone() {
		return this.registrationContactPhone;
	}

	public void setRegistrationContactPhone(String registrationContactPhone) {
		this.registrationContactPhone = registrationContactPhone;
	}

	public String getRegistrationNumber() {
		return this.registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public String getRegistrationTypeId() {
		return this.registrationTypeId;
	}

	public void setRegistrationTypeId(String registrationTypeId) {
		this.registrationTypeId = registrationTypeId;
	}

	public String getTaxpayerName() {
		return this.taxpayerName;
	}

	public void setTaxpayerName(String taxpayerName) {
		this.taxpayerName = taxpayerName;
	}

	public String getZipCode() {
		return this.zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

/*	public TmsMdLegalEquipment getTmsMdLegalEquipment() {
		return this.tmsMdLegalEquipment;
	}

	public void setTmsMdLegalEquipment(TmsMdLegalEquipment tmsMdLegalEquipment) {
		this.tmsMdLegalEquipment = tmsMdLegalEquipment;
	}*/

}