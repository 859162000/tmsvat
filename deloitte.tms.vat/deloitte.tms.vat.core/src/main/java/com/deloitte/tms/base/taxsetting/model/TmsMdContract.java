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
 * The persistent class for the TMS_MD_CONTRACT database table.
 * 
 */
@Entity
@Table(name="TMS_MD_CONTRACT")
@NamedQuery(name="TmsMdContract.findAll", query="SELECT t FROM TmsMdContract t")
public class TmsMdContract extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	public static final String TABLE = "TMS_MD_CONTRACT";
	public static final String SEQ = TABLE;
	
	@Id
	@GenericGenerator(name=SEQ+"_GENERATOR",strategy=Ling2IdGenerator.STRATEGY_NAME,parameters={@Parameter(name="pkColumnValue",value=SEQ)})
	@GeneratedValue(strategy = GenerationType.TABLE, generator = SEQ+"_GENERATOR")
	@Column(name = "CONTRACT_ID",  length = 36)
	private String Id;

	@Column(name="CONTRACT_AMOUNT")
	private BigDecimal contractAmount;

	@Column(name="CONTRACT_CATEGORY")
	private String contractCategory;

	@Column(name="CONTRACT_NUMBER")
	private String contractNumber;
	
	@Column(name="CONTRACT_NAME")
	private String contractName;
	
	@Column(name="ACTUAL_TOTAL_RECEIPTS_AMT")
	private BigDecimal actualTotalProjectAmt;
	
	@Column(name="VIRTUAL_TOTAL_RECEIPTS_AMT")
	private BigDecimal virtualTotalReceiptsAmt;
	
	@Column(name="ACTUAL_TOTAL_INVOICE_AMT")
	private BigDecimal actualTotalInvoiceAmt;
	
	@Column(name="VIRTUAL_TOTAL_INVOICE_AMT")
	private BigDecimal virtualTotalInvoiceAmt;
	

	public String getContractName() {
		return contractName;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	private String description;

	@Column(name="ENABLED_FLAG")
	private String enabledFlag;

	@Temporal(TemporalType.DATE)
	@Column(name="END_DATE")
	private Date endDate;

	@Column(name="REF_CUSTOMER_ID")
	private String refCustomerId;

	@Temporal(TemporalType.DATE)
	@Column(name="START_DATE")
	private Date startDate;

	public TmsMdContract() {
	}

	public String getId() {
		return this.Id;
	}

	public void setId(String Id) {
		this.Id = Id;
	}

	public BigDecimal getContractAmount() {
		return this.contractAmount;
	}

	public void setContractAmount(BigDecimal contractAmount) {
		this.contractAmount = contractAmount;
	}

	public String getContractCategory() {
		return this.contractCategory;
	}

	public void setContractCategory(String contractCategory) {
		this.contractCategory = contractCategory;
	}

	public String getContractNumber() {
		return this.contractNumber;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
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