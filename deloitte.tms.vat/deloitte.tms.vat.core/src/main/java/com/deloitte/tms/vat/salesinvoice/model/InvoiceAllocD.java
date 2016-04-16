package com.deloitte.tms.vat.salesinvoice.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.deloitte.tms.base.masterdata.model.TmsMdEquipment;
import com.deloitte.tms.pl.core.commons.annotation.ModelProperty;
import com.deloitte.tms.pl.core.commons.constant.TablePreDef;
import com.deloitte.tms.pl.core.hibernate.identifier.Ling2IdGenerator;
import com.deloitte.tms.pl.core.model.impl.BaseEntity;


/**
 * The persistent class for the TMS_CRVAT_INVOICE_ALLOT_D database table.
 * 
 */
@Entity
@Table(name = InvoiceAllocD.TABLE)
@ModelProperty(comment = "销项税发票分发明细表")
public class InvoiceAllocD extends BaseEntity {
	public static final String TABLE = TablePreDef.TMS_CRVAT_INVOICE+"ALLOT_D";
	public static final String SEQ = TABLE;

	@Id
	@GenericGenerator(name=SEQ+"_GENERATOR",strategy=Ling2IdGenerator.STRATEGY_NAME,parameters={@Parameter(name="pkColumnValue",value=SEQ)})
	@GeneratedValue(strategy = GenerationType.TABLE, generator = SEQ+"_GENERATOR")
	@Column(name = "INVOICE_ALLOT_D_ID",  length = 36)
	String id;

	@Column(name="CRVAT_INVOICE_TRX_L_ID")
	private String crvatInvoiceTrxLId;

	@Column(name="EQUIPMENT_ID")
	private String equipmentId;
	@ManyToOne
	@Cascade(CascadeType.REFRESH)
	@JoinColumn(name="EQUIPMENT_ID",insertable=false,updatable=false,nullable=true)
	TmsMdEquipment tmsMdEquipment; 
	
	@Column(name="INVOICE_ALLOT_L_ID")
	private String invoiceAllotLId;

	@Column(name="INVOICE_CODE")
	private String invoiceCode;

	@Column(name="INVOICE_NUMBER")
	private String invoiceNumber;

	@Column(name="INVOICE_QTY")
	private Integer invoiceQty;

	@Column(name="LEGAL_ENTITY_ID")
	private String legalEntityId;

	public InvoiceAllocD() {
	}

	public String getCrvatInvoiceTrxLId() {
		return this.crvatInvoiceTrxLId;
	}

	public void setCrvatInvoiceTrxLId(String crvatInvoiceTrxLId) {
		this.crvatInvoiceTrxLId = crvatInvoiceTrxLId;
	}

	public String getEquipmentId() {
		return this.equipmentId;
	}

	public void setEquipmentId(String equipmentId) {
		this.equipmentId = equipmentId;
	}

	public String getInvoiceAllotLId() {
		return this.invoiceAllotLId;
	}

	public void setInvoiceAllotLId(String invoiceAllotLId) {
		this.invoiceAllotLId = invoiceAllotLId;
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

	public Integer getInvoiceQty() {
		return invoiceQty;
	}

	public void setInvoiceQty(Integer invoiceQty) {
		this.invoiceQty = invoiceQty;
	}

	public String getLegalEntityId() {
		return this.legalEntityId;
	}

	public void setLegalEntityId(String legalEntityId) {
		this.legalEntityId = legalEntityId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}