package com.deloitte.tms.vat.salesinvoice.model;

import java.math.BigDecimal;

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
import com.deloitte.tms.pl.core.hibernate.identifier.Ling2IdGenerator;
import com.deloitte.tms.pl.core.model.impl.BaseEntity;


/**
 * The persistent class for the TMS_CRVAT_INVOICE_ONHAND database table.
 * 
 */
@Entity
@Table(name = InvoiceTrxD.TABLE)
@ModelProperty(comment = "销项税发票请领入库单明细")
public class InvoiceTrxD extends BaseEntity {
	public static final String TABLE = TablePreDef.TMS_CRVAT_INVOICE+"ONHAND";
	public static final String SEQ = TABLE;
	
	@Id
	@GenericGenerator(name=SEQ+"_GENERATOR",strategy=Ling2IdGenerator.STRATEGY_NAME,parameters={@Parameter(name="pkColumnValue",value=SEQ)})
	@GeneratedValue(strategy = GenerationType.TABLE, generator = SEQ+"_GENERATOR")
	@Column(name = "INVENTORY_INVOICE_ID",  length = 36)
	String id;

	@Column(name="CRVAT_INVOICE_TRX_ID")
	private String crvatInvoiceTrxId;

	@Column(name="EQUIPMENT_ID")
	private String equipmentId;

	@Column(name="INVOICE_CATEGORY")
	private String invoiceCategory;

	@Column(name="INVOICE_CODE")
	private String invoiceCode;

	@Column(name="INVOICE_LIMIT_AMOUNT")
	private Integer invoiceLimitAmount;

	@Column(name="INVOICE_NUMBER")
	private String invoiceNumber;

	@Column(name="INVOICE_QTY")
	private Integer invoiceQty;

	@Column(name="INVOICE_TRX_TYPE")
	private String invoiceTrxType;

	@Column(name="IS_ABOLISH")
	private String isAbolish;

	@Column(name="IS_HANDING")
	private String isHanding;

	@Column(name="IS_LOST")
	private String isLost;

	@Column(name="IS_RETURN")
	private String isReturn;

	@Column(name="IS_REVERSE")
	private String isReverse;

	@Column(name="IS_USAGE")
	private String isUsage;

	@Column(name="LEGAL_ENTITY_ID")
	private String legalEntityId;

	@Column(name="SUBINVENTORY_TYPE")
	private String subinventoryType;

	@Column(name="TRX_INVENTORY_TYPE")
	private String trxInventoryType;
	
	@Column(name="IS_ALLOT")
	private String isAllot;
	
	@Column(name="IS_LOCK")
	private String isLock;

	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getIsAllot() {
		return isAllot;
	}


	public void setIsAllot(String isAllot) {
		this.isAllot = isAllot;
	}


	public String getIsLock() {
		return isLock;
	}


	public void setIsLock(String isLock) {
		this.isLock = isLock;
	}


	public InvoiceTrxD() {
	}


	public String getCrvatInvoiceTrxId() {
		return this.crvatInvoiceTrxId;
	}

	public void setCrvatInvoiceTrxId(String crvatInvoiceTrxId) {
		this.crvatInvoiceTrxId = crvatInvoiceTrxId;
	}

	public String getEquipmentId() {
		return this.equipmentId;
	}

	public void setEquipmentId(String equipmentId) {
		this.equipmentId = equipmentId;
	}

	public String getInvoiceCategory() {
		return this.invoiceCategory;
	}

	public void setInvoiceCategory(String invoiceCategory) {
		this.invoiceCategory = invoiceCategory;
	}

	public String getInvoiceCode() {
		return this.invoiceCode;
	}

	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}

	public Integer getInvoiceLimitAmount() {
		return invoiceLimitAmount;
	}


	public void setInvoiceLimitAmount(Integer invoiceLimitAmount) {
		this.invoiceLimitAmount = invoiceLimitAmount;
	}


	public Integer getInvoiceQty() {
		return invoiceQty;
	}


	public void setInvoiceQty(Integer invoiceQty) {
		this.invoiceQty = invoiceQty;
	}


	public String getInvoiceNumber() {
		return this.invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public String getInvoiceTrxType() {
		return this.invoiceTrxType;
	}

	public void setInvoiceTrxType(String invoiceTrxType) {
		this.invoiceTrxType = invoiceTrxType;
	}

	public String getIsAbolish() {
		return this.isAbolish;
	}

	public void setIsAbolish(String isAbolish) {
		this.isAbolish = isAbolish;
	}

	public String getIsHanding() {
		return this.isHanding;
	}

	public void setIsHanding(String isHanding) {
		this.isHanding = isHanding;
	}

	public String getIsLost() {
		return this.isLost;
	}

	public void setIsLost(String isLost) {
		this.isLost = isLost;
	}

	public String getIsReturn() {
		return this.isReturn;
	}

	public void setIsReturn(String isReturn) {
		this.isReturn = isReturn;
	}

	public String getIsReverse() {
		return this.isReverse;
	}

	public void setIsReverse(String isReverse) {
		this.isReverse = isReverse;
	}

	public String getIsUsage() {
		return this.isUsage;
	}

	public void setIsUsage(String isUsage) {
		this.isUsage = isUsage;
	}

	public String getLegalEntityId() {
		return this.legalEntityId;
	}

	public void setLegalEntityId(String legalEntityId) {
		this.legalEntityId = legalEntityId;
	}

	public String getSubinventoryType() {
		return this.subinventoryType;
	}

	public void setSubinventoryType(String subinventoryType) {
		this.subinventoryType = subinventoryType;
	}

	public String getTrxInventoryType() {
		return this.trxInventoryType;
	}

	public void setTrxInventoryType(String trxInventoryType) {
		this.trxInventoryType = trxInventoryType;
	}

}