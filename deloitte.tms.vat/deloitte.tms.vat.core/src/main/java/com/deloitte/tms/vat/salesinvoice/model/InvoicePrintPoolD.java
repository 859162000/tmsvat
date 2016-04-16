package com.deloitte.tms.vat.salesinvoice.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.deloitte.tms.pl.core.commons.annotation.ModelProperty;
import com.deloitte.tms.pl.core.commons.constant.TablePreDef;
import com.deloitte.tms.pl.core.hibernate.identifier.Ling2UUIDGenerator;
import com.deloitte.tms.pl.core.model.impl.BaseEntity;


/**
 * The persistent class for the TMS_CRVAT_INV_PRT_POOL_D database table.
 * 
 */
@Entity
@Table(name = InvoicePrintPoolD.TABLE)
@ModelProperty(comment = "销项发票打印池交易数据表")
public class InvoicePrintPoolD extends BaseEntity  implements Serializable  {
	private static final long serialVersionUID = 1L;
	public static final String TABLE = TablePreDef.TMS_CRVAT_INV+"PRT_POOL_D";
	public static final String SEQ = TABLE;

	@Id
	@GenericGenerator(name=SEQ+"_GENERATOR",strategy=Ling2UUIDGenerator.STRATEGY_NAME,parameters={@Parameter(name="pkColumnValue",value=SEQ)})
	@GeneratedValue(strategy = GenerationType.TABLE, generator = SEQ+"_GENERATOR")
	@Column(name="INVOICE_PRT_POOL_D_ID")
	@ModelProperty(comment="发票打印池明细ID")
	private String id;

	@Column(name="ACCTD_AMOUNT_CR")
	@ModelProperty(comment = "净额 ")
	private BigDecimal acctdAmountCr;

	@ModelProperty(comment = "打印池行表ID")
	@Column(name="INVOICE_PRT_POOL_L_ID")
	private String invoicePrtPoolLId;
	
	@Column(name="CRVAT_INVOICE_PRE_L_ID")
	@ModelProperty(comment = "准备单行表ID")
	private String crvatInvoicePreLId;
	
	@Column(name="CRVAT_TRX_POOL_ID")
	@ModelProperty(comment = "交易明细ID ")
	private String crvatTrxPoolId;

	@Column(name="END_DATE")
	@ModelProperty(comment = "失效时间 ")
	private Date endDate;

	@Column(name="INVENTORY_ITEM_DESCRIPTON")
	@ModelProperty(comment = "商品及服务名称")
	private String inventoryItemDescripton;
	
	
	@Column(name="INVENTORY_ITEM_ID")
	@ModelProperty(comment = "货物ID ")
	private String inventoryItemId;

	@Column(name="INVENTORY_ITEM_MODELS")
	@ModelProperty(comment = "规格型号")
	private String inventoryItemModels;

	@Column(name="INVENTORY_ITEM_NUMBER")
	@ModelProperty(comment = "商品及服务编码")
	private String inventoryItemNumber;

	@Column(name="INVENTORY_ITEM_QTY")
	@ModelProperty(comment = "货物数量")
	private Integer inventoryItemQty;
	

	@Column(name="INVOICE_AMOUNT")
	@ModelProperty(comment = "金额")
	private BigDecimal invoiceAmount;

	@Column(name="PRINT_VERSION")
	@ModelProperty(comment = "打印版本号")
	private BigDecimal printVersion;


	@Column(name="SOURCE_CODE")
	@ModelProperty(comment = "来源系统")
	private String sourceCode;

	@Column(name="SOURCE_DISTRIBUTION_ID")
	@ModelProperty(comment = "来源数据明细ID")
	private String sourceDistributionId;

	@Column(name="SOURCE_TRX_DETAIL_TAX_LINE_ID")
	@ModelProperty(comment = "来源数据明细税ID")
	private String sourceTrxDetailTaxLineId;

	@Column(name="SOURCE_TRX_ID")
	@ModelProperty(comment = "来源数据头ID")
	private String sourceTrxId;

	@Column(name="SOURCE_TRX_LINE_ID")
	@ModelProperty(comment = "来源数据行ID")
	private String sourceTrxLineId;

	@Column(name="SOURCE_TRX_LINE_TYPE")
	@ModelProperty(comment = "来源数据行类型")
	private String sourceTrxLineType;

	@Column(name="START_DATE")
	@ModelProperty(comment = "生效时间")
	private Date startDate;

	@Column(name="TAX_RATE")
	@ModelProperty(comment = "税率")
	private BigDecimal taxRate;

	@Column(name="UOM_CODE")
	@ModelProperty(comment = "单位代码")
	private String uomCode;

	@Column(name="UOM_CODE_DESCRIPTON")
	@ModelProperty(comment = "单位名称")
	private String uomCodeDescripton;

	@Column(name="VAT_AMOUNT")
	@ModelProperty(comment = "税额")
	private BigDecimal vatAmount;
	
	@Transient
	private String invoiceTrxPoolId;

	public InvoicePrintPoolD() {
	}


	public BigDecimal getAcctdAmountCr() {
		return this.acctdAmountCr;
	}

	public void setAcctdAmountCr(BigDecimal acctdAmountCr) {
		this.acctdAmountCr = acctdAmountCr;
	}



	public Integer getInventoryItemQty() {
		return inventoryItemQty;
	}


	public void setInventoryItemQty(Integer inventoryItemQty) {
		this.inventoryItemQty = inventoryItemQty;
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

	public String getInvoicePrtPoolLId() {
		return this.invoicePrtPoolLId;
	}

	public void setInvoicePrtPoolLId(String invoicePrtPoolLId) {
		this.invoicePrtPoolLId = invoicePrtPoolLId;
	}


	public BigDecimal getPrintVersion() {
		return this.printVersion;
	}

	public void setPrintVersion(BigDecimal printVersion) {
		this.printVersion = printVersion;
	}


	public String getSourceCode() {
		return this.sourceCode;
	}

	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}

	public String getSourceDistributionId() {
		return this.sourceDistributionId;
	}

	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getCrvatInvoicePreLId() {
		return crvatInvoicePreLId;
	}


	public void setCrvatInvoicePreLId(String crvatInvoicePreLId) {
		this.crvatInvoicePreLId = crvatInvoicePreLId;
	}


	public String getCrvatTrxPoolId() {
		return crvatTrxPoolId;
	}


	public void setCrvatTrxPoolId(String crvatTrxPoolId) {
		this.crvatTrxPoolId = crvatTrxPoolId;
	}


	public Date getEndDate() {
		return endDate;
	}


	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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


	public void setSourceDistributionId(String sourceDistributionId) {
		this.sourceDistributionId = sourceDistributionId;
	}

	public String getSourceTrxDetailTaxLineId() {
		return this.sourceTrxDetailTaxLineId;
	}

	public void setSourceTrxDetailTaxLineId(String sourceTrxDetailTaxLineId) {
		this.sourceTrxDetailTaxLineId = sourceTrxDetailTaxLineId;
	}

	public String getSourceTrxId() {
		return this.sourceTrxId;
	}

	public void setSourceTrxId(String sourceTrxId) {
		this.sourceTrxId = sourceTrxId;
	}

	public String getSourceTrxLineId() {
		return this.sourceTrxLineId;
	}

	public void setSourceTrxLineId(String sourceTrxLineId) {
		this.sourceTrxLineId = sourceTrxLineId;
	}

	public String getSourceTrxLineType() {
		return this.sourceTrxLineType;
	}

	public void setSourceTrxLineType(String sourceTrxLineType) {
		this.sourceTrxLineType = sourceTrxLineType;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}


	public BigDecimal getTaxRate() {
		return this.taxRate;
	}

	public void setTaxRate(BigDecimal taxRate) {
		this.taxRate = taxRate;
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


	public String getInvoiceTrxPoolId() {
		return invoiceTrxPoolId;
	}


	public void setInvoiceTrxPoolId(String invoiceTrxPoolId) {
		this.invoiceTrxPoolId = invoiceTrxPoolId;
	}
	
	

}