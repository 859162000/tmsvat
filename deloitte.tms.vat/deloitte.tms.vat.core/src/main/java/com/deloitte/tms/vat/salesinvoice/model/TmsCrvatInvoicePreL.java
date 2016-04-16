package com.deloitte.tms.vat.salesinvoice.model;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.deloitte.tms.base.masterdata.model.TmsMdTaxTrxType;
import com.deloitte.tms.pl.core.commons.annotation.ModelProperty;
import com.deloitte.tms.pl.core.commons.constant.TablePreDef;
import com.deloitte.tms.pl.core.hibernate.identifier.Ling2UUIDGenerator;
import com.deloitte.tms.pl.core.model.impl.BaseEntity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the TMS_CRVAT_INVOICE_PRE_L database table.
 * 
 */
@Entity
@Table(name="TMS_CRVAT_INVOICE_PRE_L")
@NamedQuery(name="TmsCrvatInvoicePreL.findAll", query="SELECT t FROM TmsCrvatInvoicePreL t")
public class TmsCrvatInvoicePreL extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String TABLE = TablePreDef.TMS_CRVAT_INVOICE+"PRE_L";
	public static final String SEQ = TABLE;

	@Id
	/*@GenericGenerator(name=SEQ+"_GENERATOR",strategy=Ling2UUIDGenerator.STRATEGY_NAME,parameters={@Parameter(name="pkColumnValue",value=SEQ)})
	@GeneratedValue(strategy = GenerationType.TABLE, generator = SEQ+"_GENERATOR")*/
	@Column(name="CRVAT_INVOICE_PRE_L_ID")
	private String id;

	@Column(name="ACCTD_AMOUNT_CR")
	@ModelProperty(comment="本次开票金额(不含税)")
	private BigDecimal acctdAmountCr;

	

	@Column(name="CRVAT_INVOICE_REQ_L_ID")
	private String crvatInvoiceReqLId;
	@ManyToOne(fetch = FetchType.LAZY)
	@Cascade(CascadeType.REFRESH)
	@JoinColumn(name="CRVAT_INVOICE_REQ_L_ID",insertable=false,updatable=false,nullable=true)
	InvoiceReqL invoiceReqL;
	
	@Column(name="CRVAT_TRX_POOL_ID")
	private String crvatTrxPoolId;
	@ManyToOne(fetch = FetchType.LAZY)
	@Cascade(CascadeType.REFRESH)
	@JoinColumn(name="CRVAT_TRX_POOL_ID",insertable=false,updatable=false,nullable=true)
	InvoiceTrxPool invoiceTrxPool;
	

	@Temporal(TemporalType.DATE)
	@Column(name="END_DATE")
	private Date endDate;

	@Column(name="INVENTORY_ITEM_DESCRIPTON")
	private String inventoryItemDescripton;

	@Column(name="INVENTORY_ITEM_ID")
	private String inventoryItemId;

	@Column(name="INVENTORY_ITEM_MODELS")
	private String inventoryItemModels;

	@Column(name="INVENTORY_ITEM_NUMBER")
	private String inventoryItemNumber;


	@Column(name="INVOICE_AMOUNT")
	@ModelProperty(comment="本次开票总金额")
	private BigDecimal invoiceAmount;

	@Column(name="INVOICING_TYPE")
	private String invoiceType;

	@Column(name="LEGAL_ENTITY_CODE")
	private String legalEntityCode;

	@Column(name="LEGAL_ENTITY_ID")
	private String legalEntityId;

	@Column(name="LEGAL_ENTITY_NAME")
	private String legalEntityName;
	
	
	
	@Column(name="ORIG_LEGAL_ENTITY_CODE")
	private String origLegalEntityCode;

	@Column(name="ORIG_LEGAL_ENTITY_ID")
	private String origLegalEntityId;

	@Column(name="ORIG_LEGAL_ENTITY_NAME")
	private String origLegalEntityName;


	@Column(name="ORG_ID")
	private String orgId;


	@Column(name="REGISTRATION_CODE")
	private String registrationCode;

	@Column(name="ORIG_REGISTRATION_NUMBER")
	private String origRegistrationNumber;
	

	@Column(name="ORIG_REGISTRATION_CODE")
	private String origRegistrationCode;

	@Column(name="REGISTRATION_NUMBER")
	private String registrationNumber;

	@Temporal(TemporalType.DATE)
	@Column(name="START_DATE")
	private Date startDate;

	@Column(name="INVENTORY_ITEM_QTY")
	private Integer inventoryItemQty;
	
	@Column(name="INVOICE_CATEGORY")
	private String invoiceCateGory;
	
	@Column(name="PRICE_OF_UNIT")
	private BigDecimal priceOfUnit;
	


	@Column(name="TAX_TRX_TYPE_ID")
	private String taxTrxTypeId;
	@ManyToOne(fetch = FetchType.LAZY)
	@Cascade(CascadeType.REFRESH)
	@JoinColumn(name="TAX_TRX_TYPE_ID",insertable=false,updatable=false,nullable=true)
	TmsMdTaxTrxType tmsMdTaxTrxType;

	@Column(name="UOM_CODE")
	private String uomCode;

	@Column(name="UOM_CODE_DESCRIPTON")
	private String uomCodeDescripton;

	@Column(name="VAT_AMOUNT")
	@ModelProperty(comment="本次开票税额")
	private BigDecimal vatAmount;

	@Column(name = "CRVAT_INVOICE_PRE_H_ID",length=36)
	@ModelProperty(comment = "开票申请单-头表ID")
	String invoicePeHid;	
	@ManyToOne
	@Cascade(CascadeType.REFRESH)
	@JoinColumn(name="CRVAT_INVOICE_PRE_H_ID",insertable=false,updatable=false,nullable=true)
	TmsCrvatInvoicePreH tmsCrvatInvoicePreH;

	
	
	public String getId() {
		return id;
	}






	public void setId(String id) {
		this.id = id;
	}
	
	



	public Integer getInventoryItemQty() {
		return inventoryItemQty;
	}






	public void setInventoryItemQty(Integer inventoryItemQty) {
		this.inventoryItemQty = inventoryItemQty;
	}






	public String getInvoicePeHid() {
		return invoicePeHid;
	}



	public void setInvoicePeHid(String invoicePeHid) {
		this.invoicePeHid = invoicePeHid;
	}



	public TmsCrvatInvoicePreL() {
	}

	

	public BigDecimal getAcctdAmountCr() {
		return this.acctdAmountCr;
	}

	public void setAcctdAmountCr(BigDecimal acctdAmountCr) {
		this.acctdAmountCr = acctdAmountCr;
	}

	

	public String getCrvatInvoiceReqLId() {
		return this.crvatInvoiceReqLId;
	}

	public void setCrvatInvoiceReqLId(String crvatInvoiceReqLId) {
		this.crvatInvoiceReqLId = crvatInvoiceReqLId;
	}

	
	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getInventoryItemDescripton() {
		return this.inventoryItemDescripton;
	}

	public void setInventoryItemDescripton(String inventoryItemDescripton) {
		this.inventoryItemDescripton = inventoryItemDescripton;
	}

	public String getInventoryItemId() {
		return this.inventoryItemId;
	}

	public void setInventoryItemId(String inventoryItemId) {
		this.inventoryItemId = inventoryItemId;
	}

	public String getInventoryItemModels() {
		return this.inventoryItemModels;
	}

	public void setInventoryItemModels(String inventoryItemModels) {
		this.inventoryItemModels = inventoryItemModels;
	}

	public String getInventoryItemNumber() {
		return this.inventoryItemNumber;
	}

	public void setInventoryItemNumber(String inventoryItemNumber) {
		this.inventoryItemNumber = inventoryItemNumber;
	}

	public BigDecimal getInvoiceAmount() {
		return this.invoiceAmount;
	}

	public void setInvoiceAmount(BigDecimal invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}

	public String getInvoiceType() {
		return this.invoiceType;
	}

	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
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

	

	public String getTaxTrxTypeId() {
		return this.taxTrxTypeId;
	}

	public void setTaxTrxTypeId(String taxTrxTypeId) {
		this.taxTrxTypeId = taxTrxTypeId;
	}

	public String getUomCode() {
		return this.uomCode;
	}

	public void setUomCode(String uomCode) {
		this.uomCode = uomCode;
	}

	public String getUomCodeDescripton() {
		return this.uomCodeDescripton;
	}

	public void setUomCodeDescripton(String uomCodeDescripton) {
		this.uomCodeDescripton = uomCodeDescripton;
	}

	public BigDecimal getVatAmount() {
		return this.vatAmount;
	}

	public void setVatAmount(BigDecimal vatAmount) {
		this.vatAmount = vatAmount;
	}



	public TmsCrvatInvoicePreH getTmsCrvatInvoicePreH() {
		return tmsCrvatInvoicePreH;
	}



	public void setTmsCrvatInvoicePreH(TmsCrvatInvoicePreH tmsCrvatInvoicePreH) {
		this.tmsCrvatInvoicePreH = tmsCrvatInvoicePreH;
	}



	public String getCrvatTrxPoolId() {
		return crvatTrxPoolId;
	}



	public void setCrvatTrxPoolId(String crvatTrxPoolId) {
		this.crvatTrxPoolId = crvatTrxPoolId;
	}




	public InvoiceReqL getInvoiceReqL() {
		return invoiceReqL;
	}



	public void setInvoiceReqL(InvoiceReqL invoiceReqL) {
		this.invoiceReqL = invoiceReqL;
	}



	public InvoiceTrxPool getInvoiceTrxPool() {
		return invoiceTrxPool;
	}



	public void setInvoiceTrxPool(InvoiceTrxPool invoiceTrxPool) {
		this.invoiceTrxPool = invoiceTrxPool;
	}



	public String getOrigLegalEntityCode() {
		return origLegalEntityCode;
	}



	public void setOrigLegalEntityCode(String origLegalEntityCode) {
		this.origLegalEntityCode = origLegalEntityCode;
	}



	public String getOrigLegalEntityId() {
		return origLegalEntityId;
	}



	public void setOrigLegalEntityId(String origLegalEntityId) {
		this.origLegalEntityId = origLegalEntityId;
	}



	public String getOrigLegalEntityName() {
		return origLegalEntityName;
	}



	public void setOrigLegalEntityName(String origLegalEntityName) {
		this.origLegalEntityName = origLegalEntityName;
	}



	public String getOrigRegistrationNumber() {
		return origRegistrationNumber;
	}



	public void setOrigRegistrationNumber(String origRegistrationNumber) {
		this.origRegistrationNumber = origRegistrationNumber;
	}



	public String getOrigRegistrationCode() {
		return origRegistrationCode;
	}



	public void setOrigRegistrationCode(String origRegistrationCode) {
		this.origRegistrationCode = origRegistrationCode;
	}



	public String getInvoiceCateGory() {
		return invoiceCateGory;
	}



	public void setInvoiceCateGory(String invoiceCateGory) {
		this.invoiceCateGory = invoiceCateGory;
	}






	public BigDecimal getPriceOfUnit() {
		return priceOfUnit;
	}






	public void setPriceOfUnit(BigDecimal priceOfUnit) {
		this.priceOfUnit = priceOfUnit;
	}






	public TmsMdTaxTrxType getTmsMdTaxTrxType() {
		return tmsMdTaxTrxType;
	}
    





	public String getOrgId() {
		return orgId;
	}






	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}






	public void setTmsMdTaxTrxType(TmsMdTaxTrxType tmsMdTaxTrxType) {
		this.tmsMdTaxTrxType = tmsMdTaxTrxType;
	}
	
	
	
	
	
	
    
     

	

	








}