package com.deloitte.tms.base.taxsetting.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.deloitte.tms.pl.core.hibernate.identifier.Ling2IdGenerator;
import com.deloitte.tms.pl.core.model.impl.BaseEntity;


/**
 * The persistent class for the TMS_MD_PROJECTS database table.
 * 
 */
@Entity
@Table(name="TMS_MD_PROJECTS")
@NamedQuery(name="TmsMdProject.findAll", query="SELECT t FROM TmsMdProject t")
public class TmsMdProject extends BaseEntity {
	private static final long serialVersionUID = 1L;
	public static final String TABLE = "TMS_MD_PROJECT";
	public static final String SEQ = TABLE;

	@Id
	@GenericGenerator(name=SEQ+"_GENERATOR",strategy=Ling2IdGenerator.STRATEGY_NAME,parameters={@Parameter(name="pkColumnValue",value=SEQ)})
	@GeneratedValue(strategy = GenerationType.TABLE, generator = SEQ+"_GENERATOR")
	@Column(name = "PROJECT_ID",  length = 36)
	private String Id;

	@Column(name="CONTRACT_ID")
	private String contractId;

	private String description;

	@Column(name="ENABLED_FLAG")
	private String enabledFlag;

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	@Temporal(TemporalType.DATE)
	@Column(name="END_DATE")
	private Date endDate;

	@Column(name="PROJECT_AMOUNT")
	private BigDecimal projectAmount;

	@Column(name="PROJECT_CATEGORY")
	private String projectCategory;

	@Column(name="PROJECT_NUMBER")
	private String projectNumber;
	
	@Column(name="PROJECT_NAME")
	private String projectName;

	@Column(name="REF_CUSTOMER_ID")
	private String refCustomerId;

	@Temporal(TemporalType.DATE)
	@Column(name="START_DATE")
	private Date startDate;
	
	/**
	 * 实际累计收款金额
	 */
	@Column(name="ACTUAL_TOTAL_RECEIPTS_AMT")
	private BigDecimal actualTotalProjectAmt;
	
	/**
	 * 已用累计收款金额
	 */
	@Column(name="VIRTUAL_TOTAL_RECEIPTS_AMT")
	private BigDecimal virtualTotalReceiptsAmt;
	/**
	 * 实际累计开票金额
	 */
	@Column(name="ACTUAL_TOTAL_INVOICE_AMT")
	private BigDecimal actualTotalInvoiceAmt;
	
	/**
	 * 已用累计开票金额
	 */
	@Column(name="VIRTUAL_TOTAL_INVOICE_AMT")
	private BigDecimal virtualTotalInvoiceAmt;

	//private TmsMdContract tmsMdContract;

	public TmsMdProject() {
	}

	public String getId() {
		return this.Id;
	}

	public void setId(String Id) {
		this.Id = Id;
	}

	public String getContractId() {
		return this.contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEnabledFlag() {
		return this.enabledFlag;
	}

	public void setEnabledFlag(String enabledFlag) {
		this.enabledFlag = enabledFlag;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public BigDecimal getProjectAmount() {
		return this.projectAmount;
	}

	public void setProjectAmount(BigDecimal projectAmount) {
		this.projectAmount = projectAmount;
	}

	public String getProjectCategory() {
		return this.projectCategory;
	}

	public void setProjectCategory(String projectCategory) {
		this.projectCategory = projectCategory;
	}

	public String getProjectNumber() {
		return this.projectNumber;
	}

	public void setProjectNumber(String projectNumber) {
		this.projectNumber = projectNumber;
	}

	public String getRefCustomerId() {
		return this.refCustomerId;
	}

	public void setRefCustomerId(String refCustomerId) {
		this.refCustomerId = refCustomerId;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public BigDecimal getActualTotalProjectAmt() {
		return actualTotalProjectAmt;
	}

	public void setActualTotalProjectAmt(BigDecimal actualTotalProjectAmt) {
		this.actualTotalProjectAmt = actualTotalProjectAmt;
	}

	public BigDecimal getVirtualTotalReceiptsAmt() {
		return virtualTotalReceiptsAmt;
	}

	public void setVirtualTotalReceiptsAmt(BigDecimal virtualTotalReceiptsAmt) {
		this.virtualTotalReceiptsAmt = virtualTotalReceiptsAmt;
	}

	public BigDecimal getActualTotalInvoiceAmt() {
		return actualTotalInvoiceAmt;
	}

	public void setActualTotalInvoiceAmt(BigDecimal actualTotalInvoiceAmt) {
		this.actualTotalInvoiceAmt = actualTotalInvoiceAmt;
	}

	public BigDecimal getVirtualTotalInvoiceAmt() {
		return virtualTotalInvoiceAmt;
	}

	public void setVirtualTotalInvoiceAmt(BigDecimal virtualTotalInvoiceAmt) {
		this.virtualTotalInvoiceAmt = virtualTotalInvoiceAmt;
	}

	
	
	

}