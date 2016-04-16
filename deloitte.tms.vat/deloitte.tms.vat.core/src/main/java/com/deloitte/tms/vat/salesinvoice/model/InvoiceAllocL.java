package com.deloitte.tms.vat.salesinvoice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.deloitte.tms.pl.core.commons.annotation.ModelProperty;
import com.deloitte.tms.pl.core.commons.constant.TablePreDef;
import com.deloitte.tms.pl.core.model.impl.BaseEntity;


/**
 * The persistent class for the TMS_CRVAT_INVOICE_ALLOT_L database table.
 * 
 */
@Entity
@Table(name = InvoiceAllocL.TABLE)
@ModelProperty(comment = "销项税发票分发行")
public class InvoiceAllocL extends BaseEntity {
	public static final String TABLE = TablePreDef.TMS_CRVAT_INVOICE+"ALLOT_L";
	public static final String SEQ = TABLE;
	
	@Id
	@Column(name = "INVOICE_ALLOT_L_ID",  length = 36)
	String id;

	@Column(name="INVOICE_ALLOT_H_ID")
	private String invoiceAllotHId;
	@ManyToOne(fetch = FetchType.LAZY)
	@Cascade(CascadeType.REFRESH)
	@JoinColumn(name="INVOICE_ALLOT_H_ID",insertable=false,updatable=false,nullable=true)
	InvoiceAllocH invoiceAllocH;

	private String description;

	@Column(name="END_INVOICE_NUMBER")
	private String endInvoiceNumber;

	@Column(name="EQUIPMENT_ID")
	private String equipmentId;

	@Column(name="INVOICE_ALLOT_QTY")
	private Integer invoiceAllotQty;

	@Column(name="INVOICE_CODE")
	private String invoiceCode;

	@Column(name="LEGAL_ENTITY_ID")
	private String legalEntityId;

	@Column(name="SIGNATURE_STATUS")
	private String signatureStatus;

	@Column(name="START_INVOICE_NUMBER")
	private String startInvoiceNumber;
	
	@Column(name="INVOICE_CATEGORY")
	private String invoiceCategory;

	public String getInvoiceCategory() {
		return invoiceCategory;
	}

	public void setInvoiceCategory(String invoiceCategory) {
		this.invoiceCategory = invoiceCategory;
	}

	public InvoiceAllocL() {
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEndInvoiceNumber() {
		return this.endInvoiceNumber;
	}

	public void setEndInvoiceNumber(String endInvoiceNumber) {
		this.endInvoiceNumber = endInvoiceNumber;
	}

	public String getEquipmentId() {
		return this.equipmentId;
	}

	public void setEquipmentId(String equipmentId) {
		this.equipmentId = equipmentId;
	}

	public String getInvoiceAllotHId() {
		return this.invoiceAllotHId;
	}

	public void setInvoiceAllotHId(String invoiceAllotHId) {
		this.invoiceAllotHId = invoiceAllotHId;
	}

	public Integer getInvoiceAllotQty() {
		return invoiceAllotQty;
	}

	public void setInvoiceAllotQty(Integer invoiceAllotQty) {
		this.invoiceAllotQty = invoiceAllotQty;
	}

	public String getInvoiceCode() {
		return this.invoiceCode;
	}

	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}

	public String getLegalEntityId() {
		return this.legalEntityId;
	}

	public void setLegalEntityId(String legalEntityId) {
		this.legalEntityId = legalEntityId;
	}

	public String getSignatureStatus() {
		return this.signatureStatus;
	}

	public void setSignatureStatus(String signatureStatus) {
		this.signatureStatus = signatureStatus;
	}

	public String getStartInvoiceNumber() {
		return this.startInvoiceNumber;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setStartInvoiceNumber(String startInvoiceNumber) {
		this.startInvoiceNumber = startInvoiceNumber;
	}
	
	public InvoiceAllocH getInvoiceAllocH() {
		return invoiceAllocH;
	}

	public void setInvoiceAllocH(InvoiceAllocH invoiceAllocH) {
		this.invoiceAllocH = invoiceAllocH;
	}
}