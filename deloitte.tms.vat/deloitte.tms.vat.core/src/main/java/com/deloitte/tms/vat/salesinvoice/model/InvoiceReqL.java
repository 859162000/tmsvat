package com.deloitte.tms.vat.salesinvoice.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.deloitte.tms.base.masterdata.model.TmsMdTaxTrxType;
import com.deloitte.tms.pl.core.commons.annotation.ModelProperty;
import com.deloitte.tms.pl.core.commons.constant.TablePreDef;
import com.deloitte.tms.pl.core.hibernate.identifier.Ling2UUIDGenerator;
import com.deloitte.tms.pl.core.model.impl.BaseEntity;


/**  
 *销项税开票申请单-行表
 * 功能详细描述
 * @author sqing
 * @create 2016年3月16日 下午10:03:00 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Entity
@Table(name = InvoiceReqL.TABLE)
@ModelProperty(comment = "申请单行信息")
public class InvoiceReqL extends BaseEntity{
	public static final String TABLE = TablePreDef.TMS_CRVAT_INVOICE_REQ+"L";
	public static final String SEQ = TABLE;
	
	@Id
	/*@GenericGenerator(name=SEQ+"_GENERATOR",strategy=Ling2UUIDGenerator.STRATEGY_NAME,parameters={@Parameter(name="pkColumnValue",value=SEQ)})
	@GeneratedValue(strategy = GenerationType.TABLE, generator = SEQ+"_GENERATOR")*/
	@Column(name = "CRVAT_INVOICE_REQ_L_ID",  length = 36)
	protected String id;//开票申请单行ID
	
	@Column(name = "CRVAT_INVOICE_REQ_H_ID",length=36)
	@ModelProperty(comment = "开票申请单-头表ID")
	String crvatInvoiceReqHId;
	@ManyToOne
	@Cascade(CascadeType.REFRESH)
	@JoinColumn(name="CRVAT_INVOICE_REQ_H_ID",insertable=false,updatable=false,nullable=true)
	InvoiceReqH invoiceReqH;

	@Column(name="ACCTD_AMOUNT_CR")
	private BigDecimal acctdAmountCr;
	
	
	@Column(name="CRVAT_TRX_POOL_ID")
	private String crvatTrxPoolId;

	

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

	@Column(name="INVENTORY_ITEM_QTY")
	private Integer inventoryItemQty;

	@Column(name="INVOICE_AMOUNT")
	private BigDecimal invoiceAmount;

	@Column(name="INVOICING_TYPE")
	private String invoiceType;
	

	@Column(name="LEGAL_ENTITY_CODE")
	private String legalEntityCode;

	@Column(name="LEGAL_ENTITY_ID")
	private String legalEntityId;
	

	@Column(name="LEGAL_ENTITY_NAME")
	private String legalEntityName;




	@Column(name="REGISTRATION_CODE")
	private String registrationCode;

	@Column(name="REGISTRATION_NUMBER")
	private String registrationNumber;

	@Temporal(TemporalType.DATE)
	@Column(name="START_DATE")
	private Date startDate;



	@Column(name="TAX_TRX_TYPE_ID")
	private String taxTrxTypeId;
	@ManyToOne
	@Cascade(CascadeType.REFRESH)
	@JoinColumn(name="TAX_TRX_TYPE_ID",insertable=false,updatable=false,nullable=true)
	TmsMdTaxTrxType tmsMdTaxTrxType; 
	
	@Column(name="UOM_CODE")
	private String uomCode;

	@Column(name="UOM_CODE_DESCRIPTON")
	private String uomCodeDescripton;

	@Column(name="VAT_AMOUNT")
	private BigDecimal vatAmount;

	@Column(name="SOURCE_CODE")
	private BigDecimal sourceCode;

	@Column(name="INVOICE_CATEGORY")
	private String invoiceCategory;
	
	@Column(name="ORG_ID")
	private String orgId;
	
	@Column(name="IS_TAX")
	@ModelProperty(comment = "是否含税")
	private String isTax;
	
	

	
	public BigDecimal getSourceCode() {
		return sourceCode;
	}

	public void setSourceCode(BigDecimal sourceCode) {
		this.sourceCode = sourceCode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCrvatInvoiceReqHId() {
		return crvatInvoiceReqHId;
	}

	public void setCrvatInvoiceReqHId(String crvatInvoiceReqHId) {
		this.crvatInvoiceReqHId = crvatInvoiceReqHId;
	}

	

	public String getInvoiceType() {
		return invoiceType;
	}

	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}

	public BigDecimal getAcctdAmountCr() {
		return acctdAmountCr;
	}

	public void setAcctdAmountCr(BigDecimal acctdAmountCr) {
		this.acctdAmountCr = acctdAmountCr;
	}

	public BigDecimal getVatAmount() {
		return vatAmount;
	}

	public void setVatAmount(BigDecimal vatAmount) {
		this.vatAmount = vatAmount;
	}

	public BigDecimal getInvoiceAmount() {
		return invoiceAmount;
	}

	public void setInvoiceAmount(BigDecimal invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}

	public String getLegalEntityId() {
		return legalEntityId;
	}

	public void setLegalEntityId(String legalEntityId) {
		this.legalEntityId = legalEntityId;
	}

	public String getLegalEntityCode() {
		return legalEntityCode;
	}

	public void setLegalEntityCode(String legalEntityCode) {
		this.legalEntityCode = legalEntityCode;
	}

	public String getLegalEntityName() {
		return legalEntityName;
	}

	public void setLegalEntityName(String legalEntityName) {
		this.legalEntityName = legalEntityName;
	}

	public String getRegistrationCode() {
		return registrationCode;
	}

	public void setRegistrationCode(String registrationCode) {
		this.registrationCode = registrationCode;
	}
	public String getCrvatTrxPoolId() {
		return crvatTrxPoolId;
	}

	public void setCrvatTrxPoolId(String crvatTrxPoolId) {
		this.crvatTrxPoolId = crvatTrxPoolId;
	}

	public String getInventoryItemDescripton() {
		return inventoryItemDescripton;
	}

	public void setInventoryItemDescripton(String inventoryItemDescripton) {
		this.inventoryItemDescripton = inventoryItemDescripton;
	}

	public String getInventoryItemId() {
		return inventoryItemId;
	}

	public void setInventoryItemId(String inventoryItemId) {
		this.inventoryItemId = inventoryItemId;
	}

	public String getInventoryItemModels() {
		return inventoryItemModels;
	}

	public void setInventoryItemModels(String inventoryItemModels) {
		this.inventoryItemModels = inventoryItemModels;
	}

	public String getInventoryItemNumber() {
		return inventoryItemNumber;
	}

	public void setInventoryItemNumber(String inventoryItemNumber) {
		this.inventoryItemNumber = inventoryItemNumber;
	}

	public Integer getInventoryItemQty() {
		return inventoryItemQty;
	}

	public void setInventoryItemQty(Integer inventoryItemQty) {
		this.inventoryItemQty = inventoryItemQty;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public String getUomCodeDescripton() {
		return uomCodeDescripton;
	}

	public void setUomCodeDescripton(String uomCodeDescripton) {
		this.uomCodeDescripton = uomCodeDescripton;
	}

	public String getUomCode() {
		return uomCode;
	}

	public void setUomCode(String uomCode) {
		this.uomCode = uomCode;
	}

	public String getTaxTrxTypeId() {
		return taxTrxTypeId;
	}

	public void setTaxTrxTypeId(String taxTrxTypeId) {
		this.taxTrxTypeId = taxTrxTypeId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public InvoiceReqH getInvoiceReqH() {
		return invoiceReqH;
	}

	public void setInvoiceReqH(InvoiceReqH invoiceReqH) {
		this.invoiceReqH = invoiceReqH;
	}

	public String getInvoiceCategory() {
		return invoiceCategory;
	}

	public void setInvoiceCategory(String invoiceCategory) {
		this.invoiceCategory = invoiceCategory;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public TmsMdTaxTrxType getTmsMdTaxTrxType() {
		return tmsMdTaxTrxType;
	}

	public void setTmsMdTaxTrxType(TmsMdTaxTrxType tmsMdTaxTrxType) {
		this.tmsMdTaxTrxType = tmsMdTaxTrxType;
	}

	public String getIsTax() {
		return isTax;
	}

	public void setIsTax(String isTax) {
		this.isTax = isTax;
	}


	
	
	
}