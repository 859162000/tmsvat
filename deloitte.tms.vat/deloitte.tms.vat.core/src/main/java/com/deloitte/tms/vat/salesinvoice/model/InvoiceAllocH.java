package com.deloitte.tms.vat.salesinvoice.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Where;

import com.deloitte.tms.pl.core.commons.annotation.ModelProperty;
import com.deloitte.tms.pl.core.commons.constant.TablePreDef;
import com.deloitte.tms.pl.core.model.impl.BaseEntity;


/**
 * The persistent class for the TMS_CRVAT_INVOICE_ALLOT_H database table.
 * 
 */
@Entity
@Table(name = InvoiceAllocH.TABLE)
@ModelProperty(comment = "销项税发票分发头")
public class InvoiceAllocH extends BaseEntity {
	public static final String TABLE = TablePreDef.TMS_CRVAT_INVOICE+"ALLOT_H";
	public static final String SEQ = TABLE;
	
	@Id
	@Column(name = "INVOICE_ALLOT_H_ID",  length = 36)
	String id;

	@Column(name="ALLOT_H_TYPE")
	private String allotHType;

	@Temporal(TemporalType.DATE)
	@Column(name="APPROVAL_DATE")
	private Date approvalDate;

	@Column(name="APPROVAL_DESC")
	private String approvalDesc;

	@Column(name="APPROVAL_STATUS")
	private String approvalStatus;

	private String description;

	@Column(name="EQUIPMENT_ID")
	private String equipmentId;

	@Temporal(TemporalType.DATE)
	@Column(name="INVOICE_ALLOT_DATE")
	private Date invoiceAllotDate;

	@Column(name="INVOICE_ALLOT_NUMBER")
	private String invoiceAllotNumber;

	@Column(name="INVOICE_ALLOT_REQ_BY")
	private String invoiceAllotReqBy;

	@Column(name="INVOICE_ALLOT_STATUS")
	private String invoiceAllotStatus;

	@Column(name="INVOICE_CATEGORY")
	private String invoiceCategory;

	@Column(name="LEGAL_ENTITY_ID")
	private String legalEntityId;

	@Column(name="RESOURCE_ENTITY_CODE")
	private String resourceEntityCode;

	@Column(name="RESOURCE_ENTITY_ID")
	private String resourceEntityId;

	@Column(name="RESOURCE_ENTITY_NAME")
	private String resourceEntityName;

	@Column(name="WF_TASK_ID")
	private String wfTaskId;

	@OneToMany(mappedBy = "invoiceAllocH", fetch = FetchType.LAZY)
	@Cascade(CascadeType.REFRESH)
	@Where(clause="DELETED_FLAG=1")
	private List<InvoiceAllocL> invoiceAllocLs;

	@Transient
	List<InvoiceAllocL> list = new ArrayList<InvoiceAllocL>();
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<InvoiceAllocL> getInvoiceAllocLs() {
		return invoiceAllocLs;
	}

	public void setInvoiceAllocLs(List<InvoiceAllocL> invoiceAllocLs) {
		this.invoiceAllocLs = invoiceAllocLs;
	}

	public List<InvoiceAllocL> getList() {
		return list;
	}

	public void setList(List<InvoiceAllocL> list) {
		this.list = list;
	}

	public InvoiceAllocH() {
	}

	public String getAllotHType() {
		return this.allotHType;
	}

	public void setAllotHType(String allotHType) {
		this.allotHType = allotHType;
	}

	public Date getApprovalDate() {
		return this.approvalDate;
	}

	public void setApprovalDate(Date approvalDate) {
		this.approvalDate = approvalDate;
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

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEquipmentId() {
		return this.equipmentId;
	}

	public void setEquipmentId(String equipmentId) {
		this.equipmentId = equipmentId;
	}

	public Date getInvoiceAllotDate() {
		return this.invoiceAllotDate;
	}

	public void setInvoiceAllotDate(Date invoiceAllotDate) {
		this.invoiceAllotDate = invoiceAllotDate;
	}

	public String getInvoiceAllotNumber() {
		return this.invoiceAllotNumber;
	}

	public void setInvoiceAllotNumber(String invoiceAllotNumber) {
		this.invoiceAllotNumber = invoiceAllotNumber;
	}

	public String getInvoiceAllotReqBy() {
		return this.invoiceAllotReqBy;
	}

	public void setInvoiceAllotReqBy(String invoiceAllotReqBy) {
		this.invoiceAllotReqBy = invoiceAllotReqBy;
	}

	public String getInvoiceAllotStatus() {
		return this.invoiceAllotStatus;
	}

	public void setInvoiceAllotStatus(String invoiceAllotStatus) {
		this.invoiceAllotStatus = invoiceAllotStatus;
	}

	public String getInvoiceCategory() {
		return this.invoiceCategory;
	}

	public void setInvoiceCategory(String invoiceCategory) {
		this.invoiceCategory = invoiceCategory;
	}

	public String getLegalEntityId() {
		return this.legalEntityId;
	}

	public void setLegalEntityId(String legalEntityId) {
		this.legalEntityId = legalEntityId;
	}

	public String getResourceEntityCode() {
		return this.resourceEntityCode;
	}

	public void setResourceEntityCode(String resourceEntityCode) {
		this.resourceEntityCode = resourceEntityCode;
	}

	public String getResourceEntityId() {
		return this.resourceEntityId;
	}

	public void setResourceEntityId(String resourceEntityId) {
		this.resourceEntityId = resourceEntityId;
	}

	public String getResourceEntityName() {
		return this.resourceEntityName;
	}

	public void setResourceEntityName(String resourceEntityName) {
		this.resourceEntityName = resourceEntityName;
	}

	public String getWfTaskId() {
		return this.wfTaskId;
	}

	public void setWfTaskId(String wfTaskId) {
		this.wfTaskId = wfTaskId;
	}

}