package com.deloitte.tms.vat.salesinvoice.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.deloitte.tms.pl.core.commons.annotation.ModelProperty;
import com.deloitte.tms.pl.core.commons.constant.TablePreDef;
import com.deloitte.tms.pl.core.hibernate.identifier.Ling2IdGenerator;
import com.deloitte.tms.pl.core.model.impl.BaseEntity;

@Entity
@Table(name = InvoiceAbolish.TABLE)
@ModelProperty(comment = "销项发票作废")
public class InvoiceAbolish extends BaseEntity {

	public static final String TABLE = TablePreDef.TMS_CRVAT_INVOICE+"ABOLISH";
	public static final String SEQ = TABLE;

	@Id
	@GenericGenerator(name=SEQ+"_GENERATOR",strategy=Ling2IdGenerator.STRATEGY_NAME,parameters={@Parameter(name="pkColumnValue",value=SEQ)})
	@GeneratedValue(strategy = GenerationType.TABLE, generator = SEQ+"_GENERATOR")
	@Column(name = "INVOICE_ABOLISH_ID",  length = 36)
	String id;
	
	@Temporal(TemporalType.DATE)
	@Column(name="ABOLISH_DATE")
	private Date abolishDate;

	@Column(name="ABOLISH_REASON")
	private String abolishReason;

	@Column(name="ABOLISH_TYPE")
	private String abolishType;

	@Column(name="APPROVAL_DESC")
	private String approvalDesc;

	@Column(name="APPROVAL_STATUS")
	private String approvalStatus;

	@Column(name="INVENTORY_INVOICE_ID")
	private String inventoryInvoiceId;

	@Column(name="INVOICE_CODE")
	private String invoiceCode;

	@Column(name="INVOICE_NUMBER")
	private String invoiceNumber;
	
	@Column(name="APPROVAL_DATE")
	private Date approvalDate;
	
	@Column(name="REQUEST_BY")
	private String requestBy;

	public Date getApprovalDate() {
		return approvalDate;
	}

	public void setApprovalDate(Date approvalDate) {
		this.approvalDate = approvalDate;
	}

	public String getRequestBy() {
		return requestBy;
	}

	public void setRequestBy(String requestBy) {
		this.requestBy = requestBy;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public Date getAbolishDate() {
		return this.abolishDate;
	}

	public void setAbolishDate(Date abolishDate) {
		this.abolishDate = abolishDate;
	}

	public String getAbolishReason() {
		return this.abolishReason;
	}

	public void setAbolishReason(String abolishReason) {
		this.abolishReason = abolishReason;
	}

	public String getAbolishType() {
		return this.abolishType;
	}

	public void setAbolishType(String abolishType) {
		this.abolishType = abolishType;
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

	public String getInventoryInvoiceId() {
		return this.inventoryInvoiceId;
	}

	public void setInventoryInvoiceId(String inventoryInvoiceId) {
		this.inventoryInvoiceId = inventoryInvoiceId;
	}

	public String getInvoiceCode() {
		return this.invoiceCode;
	}

	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}

	public String getInvoiceNumber() {
		return this.invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
}