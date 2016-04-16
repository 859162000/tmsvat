package com.deloitte.tms.vat.salesinvoice.model;

import java.io.Serializable;

import javax.persistence.*;

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

import java.sql.Timestamp;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TMS_CRVAT_INVOICE_PRE_H database table.
 * 
 */
@Entity
@Table(name="TMS_CRVAT_INVOICE_PRE_H")
@NamedQuery(name="TmsCrvatInvoicePreH.findAll", query="SELECT t FROM TmsCrvatInvoicePreH t")
@ModelProperty(comment="准备单头")
public class TmsCrvatInvoicePreH extends BaseEntity {
	

	public static final String TABLE = TablePreDef.TMS_CRVAT_INVOICE+"PRE_H";
	public static final String SEQ = TABLE;
	
	private static final long serialVersionUID = 1L;

	@Id
	/*@GenericGenerator(name=SEQ+"_GENERATOR",strategy=Ling2UUIDGenerator.STRATEGY_NAME,parameters={@Parameter(name="pkColumnValue",value=SEQ)})
	@GeneratedValue(strategy = GenerationType.TABLE, generator = SEQ+"_GENERATOR")*/
	@Column(name="CRVAT_INVOICE_PRE_H_ID")
	private String id;

	@Column(name="APPROVAL_DESC")
	private String approvalDesc;

	@Column(name="APPROVAL_STATUS")
	private String approvalStatus;
	
	@Column(name="APPROVAL_BY")
	private String approvalBy;
	
	
	@Column(name = "APPROVAL_DATE")	
	private Date approveDate;

	@Column(name = "INVOICE_REQ_TYPE")
	private String invoiceReqType;
	

	@Column(name="CRVAT_INVOICE_PRE_NUMBER")
	private String crvatInvoicePreNumber;

	@Column(name="CRVAT_INVOICE_REQ_H_ID")
	private String crvatInvoiceReqHId;	
	@ManyToOne(fetch = FetchType.LAZY)
	@Cascade(CascadeType.REFRESH)
	@JoinColumn(name="CRVAT_INVOICE_REQ_H_ID",insertable=false,updatable=false,nullable=true)
	InvoiceReqH invoiceReqH;

	@Column(name="CUST_REGISTRATION_CODE")
	private String custRegistrationCode;

	@Column(name="CUST_REGISTRATION_NUMBER")
	private String custRegistrationNumber;

	
	@Column(name="CUSTOMER_ID")
	private String customerId;	
	@ManyToOne(fetch = FetchType.LAZY)
	@Cascade(CascadeType.REFRESH)
	@JoinColumn(name="CUSTOMER_ID",insertable=false,updatable=false,nullable=true)
	Customer customer;

	
	
	
	
	

	@Column(name="CUSTOMER_NAME")
	private String customerName;

	

	@Temporal(TemporalType.DATE)
	@Column(name="END_DATE")
	private Date endDate;

	@Temporal(TemporalType.DATE)
	@Column(name="INVOICE_PRE_DATE")
	private Date invoicePreDate;

	@Column(name="INVOICING_TYPE")
	private String invoicingType;

	@Column(name="IS_EXITS_CUSTOMER")
	private String isExitsCustomer;
	

	@Column(name="LEGAL_ENTITY_CODE")
	private String legalEntityCode;

	@Column(name="LEGAL_ENTITY_ID")
	private String legalEntityId;
	@ManyToOne(fetch = FetchType.LAZY)
	@Cascade(CascadeType.REFRESH)
	@JoinColumn(name="LEGAL_ENTITY_ID",insertable=false,updatable=false,nullable=true)
	TmsMdLegalEntity tmsMdLegalEntity;

	@Column(name="LEGAL_ENTITY_NAME")
	private String legalEntityName;


	@Column(name="ORG_ID")
	private String orgId;

	@Column(name="PRT_LEGAL_ENTITY_CODE")
	private String prtLegalEntityCode;

	@Column(name="PRT_LEGAL_ENTITY_ID")
	private String prtLegalEntityId;

	@Column(name="QTY_OF_INVOICE")
	private Integer qtyOfInvoice;
	

	@Column(name="REGISTRATION_CODE")
	private String registrationCode;

	@Column(name="REGISTRATION_NUMBER")
	private String registrationNumber;
	


	@Temporal(TemporalType.DATE)
	@Column(name="START_DATE")
	private Date startDate;



	@Temporal(TemporalType.DATE)
	@Column(name="SUBMIT_DATE")
	private Date submitDate;

	@Column(name="WF_TASK_ID")
	private String wfTaskId;

	//bi-directional many-to-one association to TmsCrvatInvoicePreL
	/*@OneToMany(mappedBy = "tmsCrvatInvoicePreH", fetch = FetchType.LAZY)
	@Cascade(CascadeType.REFRESH)
	@Where(clause="DELETED_FLAG=1")*/
	@Transient
	private List<TmsCrvatInvoicePreL> tmsCrvatInvoicePreLs;
	
	@Transient
	BigDecimal totalAmount;
	@Transient
	BigDecimal accTotalAmount;

	public TmsCrvatInvoicePreH() {
	}

	

	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getApprovalDesc() {
		return this.approvalDesc;
	}

	public void setApprovalDesc(String approvalDesc) {
		this.approvalDesc = approvalDesc;
	}

	public String getApprovalStatus() {
		return this.approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	

	public String getCrvatInvoicePreNumber() {
		return this.crvatInvoicePreNumber;
	}

	public void setCrvatInvoicePreNumber(String crvatInvoicePreNumber) {
		this.crvatInvoicePreNumber = crvatInvoicePreNumber;
	}

	public String getCrvatInvoiceReqHId() {
		return this.crvatInvoiceReqHId;
	}

	public void setCrvatInvoiceReqHId(String crvatInvoiceReqHId) {
		this.crvatInvoiceReqHId = crvatInvoiceReqHId;
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

	public String getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getInvoicePreDate() {
		return this.invoicePreDate;
	}

	public void setInvoicePreDate(Date invoicePreDate) {
		this.invoicePreDate = invoicePreDate;
	}


	public String getInvoicingType() {
		return invoicingType;
	}



	public void setInvoicingType(String invoicingType) {
		this.invoicingType = invoicingType;
	}



	public String getIsExitsCustomer() {
		return this.isExitsCustomer;
	}

	public void setIsExitsCustomer(String isExitsCustomer) {
		this.isExitsCustomer = isExitsCustomer;
	}

	

	public String getLegalEntityCode() {
		return this.legalEntityCode;
	}

	public void setLegalEntityCode(String legalEntityCode) {
		this.legalEntityCode = legalEntityCode;
	}

	public String getLegalEntityId() {
		return this.legalEntityId;
	}

	public void setLegalEntityId(String legalEntityId) {
		this.legalEntityId = legalEntityId;
	}

	public String getLegalEntityName() {
		return this.legalEntityName;
	}

	public void setLegalEntityName(String legalEntityName) {
		this.legalEntityName = legalEntityName;
	}



	public String getPrtLegalEntityCode() {
		return this.prtLegalEntityCode;
	}

	public void setPrtLegalEntityCode(String prtLegalEntityCode) {
		this.prtLegalEntityCode = prtLegalEntityCode;
	}

	public String getPrtLegalEntityId() {
		return this.prtLegalEntityId;
	}

	public void setPrtLegalEntityId(String prtLegalEntityId) {
		this.prtLegalEntityId = prtLegalEntityId;
	}



	public String getRegistrationCode() {
		return this.registrationCode;
	}

	public void setRegistrationCode(String registrationCode) {
		this.registrationCode = registrationCode;
	}

	public String getRegistrationNumber() {
		return this.registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}



	public Date getSubmitDate() {
		return this.submitDate;
	}

	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
	}

	public String getWfTaskId() {
		return this.wfTaskId;
	}

	public void setWfTaskId(String wfTaskId) {
		this.wfTaskId = wfTaskId;
	}

	public List<TmsCrvatInvoicePreL> getTmsCrvatInvoicePreLs() {
		return this.tmsCrvatInvoicePreLs;
	}

	public void setTmsCrvatInvoicePreLs(List<TmsCrvatInvoicePreL> tmsCrvatInvoicePreLs) {
		this.tmsCrvatInvoicePreLs = tmsCrvatInvoicePreLs;
	}
	
	

	public InvoiceReqH getInvoiceReqH() {
		return invoiceReqH;
	}



	public void setInvoiceReqH(InvoiceReqH invoiceReqH) {
		this.invoiceReqH = invoiceReqH;
	}



	public Customer getCustomer() {
		return customer;
	}



	public void setCustomer(Customer customer) {
		this.customer = customer;
	}



	public TmsMdLegalEntity getTmsMdLegalEntity() {
		return tmsMdLegalEntity;
	}



	public void setTmsMdLegalEntity(TmsMdLegalEntity tmsMdLegalEntity) {
		this.tmsMdLegalEntity = tmsMdLegalEntity;
	}
	
	



	public Integer getQtyOfInvoice() {
		return qtyOfInvoice;
	}



	public void setQtyOfInvoice(Integer qtyOfInvoice) {
		this.qtyOfInvoice = qtyOfInvoice;
	}
	
	
	
	



	public String getInvoiceReqType() {
		return invoiceReqType;
	}



	public void setInvoiceReqType(String invoiceReqType) {
		this.invoiceReqType = invoiceReqType;
	}



	public Date getApproveDate() {
		return approveDate;
	}



	public void setApproveDate(Date approveDate) {
		this.approveDate = approveDate;
	}
	
	



	public BigDecimal getTotalAmount() {
		return totalAmount;
	}



	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}



	public BigDecimal getAccTotalAmount() {
		return accTotalAmount;
	}



	public void setAccTotalAmount(BigDecimal accTotalAmount) {
		this.accTotalAmount = accTotalAmount;
	}
	
	



	public String getOrgId() {
		return orgId;
	}



	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	
	



	public String getApprovalBy() {
		return approvalBy;
	}



	public void setApprovalBy(String approvalBy) {
		this.approvalBy = approvalBy;
	}



	public TmsCrvatInvoicePreL addTmsCrvatInvoicePreL(TmsCrvatInvoicePreL tmsCrvatInvoicePreL) {
		getTmsCrvatInvoicePreLs().add(tmsCrvatInvoicePreL);
		tmsCrvatInvoicePreL.setTmsCrvatInvoicePreH(this);

		return tmsCrvatInvoicePreL;
	}

	public TmsCrvatInvoicePreL removeTmsCrvatInvoicePreL(TmsCrvatInvoicePreL tmsCrvatInvoicePreL) {
		getTmsCrvatInvoicePreLs().remove(tmsCrvatInvoicePreL);
		tmsCrvatInvoicePreL.setTmsCrvatInvoicePreH(null);

		return tmsCrvatInvoicePreL;
	}

}