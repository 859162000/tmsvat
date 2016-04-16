
/**    
 * Copyright (C),Deloitte
 * @FileName: InvoicePrintPoolH.java  
 * @Package: com.deloitte.tms.vat.salesinvoice.model  
 * @Description: //模块目的、功能描述  
 * @Author liazhou  
 * @Date 2016年3月17日 下午12:12:48  
 * @History: //修改记录
 * 〈author〉      〈time〉      〈version〉       〈desc〉
 * 修改人姓名            修改时间            版本号              描述   
*/  

package com.deloitte.tms.vat.salesinvoice.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.deloitte.tms.pl.core.commons.annotation.ModelProperty;
import com.deloitte.tms.pl.core.commons.constant.TablePreDef;
import com.deloitte.tms.pl.core.hibernate.identifier.Ling2UUIDGenerator;
import com.deloitte.tms.pl.core.model.impl.BaseEntity;


/**  
 *〈一句话功能简述〉
 * 功能详细描述
 * @author liazhou
 * @create 2016年3月17日 下午12:12:48 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Entity
@Table(name = InvoicePrintPoolL.TABLE)
@ModelProperty(comment = "销项发票打印池明细表")
public class InvoicePrintPoolL extends BaseEntity{
	
	public static final String TABLE = TablePreDef.TMS_CRVAT_INV+"PRT_POOL_L";//TMS_CRVAT_INV_PRT_POOL_L
	public static final String SEQ = TABLE;
	
	@Id
	/*@GenericGenerator(name=SEQ+"_GENERATOR",strategy=Ling2UUIDGenerator.STRATEGY_NAME,parameters={@Parameter(name="pkColumnValue",value=SEQ)})
	@GeneratedValue(strategy = GenerationType.TABLE, generator = SEQ+"_GENERATOR")*/
	@Column(name = "INVOICE_PRT_POOL_L_ID",  length = 36)
	@ModelProperty(comment="发票打印池行ID")
	String id;
	
	@Column(name = "INVOICE_PRT_POOL_H_ID",length=36,nullable=false)
	@ModelProperty(comment = "发票打印池-头 ")
	String invoicePrtPoolHId;
	
	@ManyToOne
	@Cascade(CascadeType.REFRESH)
	@JoinColumn(name="INVOICE_PRT_POOL_H_ID",insertable=false,updatable=false,nullable=true)
	private InvoicePrintPoolH invoicePrintPoolH;
	
	@Column(name = "INVENTORY_ITEM_ID",length=100)
	@ModelProperty(comment = "货物ID")
	String inventoryItemId;
	
	@Column(name = "INVENTORY_ITEM_NUMBER",length=100)
	@ModelProperty(comment = "货物代码")
	String inventoryItemNumber;
	
	@Column(name = "INVENTORY_ITEM_DESCRIPTON",length=2000)
	@ModelProperty(comment = "货物名称")
	String inventoryItemDescripton;
	
	@Column(name = "INVENTORY_ITEM_MODELS",length=500)
	@ModelProperty(comment = "规格型号")
	String inventoryItemModels;
	
	@Column(name = "UOM_CODE",length=100)
	@ModelProperty(comment = "单位代码")
	String uomCode;
	
	@Column(name = "UOM_CODE_DESCRIPTON",length=100)
	@ModelProperty(comment = "单位名称")
	String uomCodeDescripton;
	
	@Column(name = "INVENTORY_ITEM_QTY")
	@ModelProperty(comment = "货物数量")
	Integer inventoryItemQty;
	
	@Column(name = "INVOICE_AMOUNT")
	@ModelProperty(comment = "金额")
	BigDecimal invoiceAmount;
	
	@Column(name = "TAX_RATE")
	@ModelProperty(comment = "税率")
	BigDecimal taxRate ;
	
	@Column(name = "ACCTD_AMOUNT_CR")
	@ModelProperty(comment = "净额")
	BigDecimal acctdAmountCR;
	
	@Column(name = "VAT_AMOUNT")
	@ModelProperty(comment = "税额")
	BigDecimal vatAmount;
	
	@Column(name = "SOURCE_CODE",length=100)
	@ModelProperty(comment = "来源系统")
	String sourceCode;
	
	@Column(name = "SOURCE_TRX_ID",length=100)
	@ModelProperty(comment = "来源数据头ID")
	String sourceTrxId;
	
	@Column(name = "SOURCE_TRX_LINE_ID",length=100)
	@ModelProperty(comment = "来源数据行ID")
	String sourceTrxLineId;
	
	@Column(name = "SOURCE_TRX_LINE_TYPE",length=100)
	@ModelProperty(comment = "来源数据行类型")
	String sourceTrxLineType;
	
	@Column(name = "SOURCE_TRX_DETAIL_TAX_LINE_ID",length=100)
	@ModelProperty(comment = "来源数据明细税ID")
	String sourceTrxDetailTaxLineId;
	
	@Column(name = "SOURCE_DISTRIBUTION_ID",length=100)
	@ModelProperty(comment = "来源数据明细ID")
	String sourceDistributionId;
	
	@Column(name = "START_DATE")
	@ModelProperty(comment = "有效日期")
	Date startDate;
	
	@Column(name = "END_DATE")
	@ModelProperty(comment = "失效日期")
	Date EndDate;
	
	@Column(name = "PRICE_OF_UNIT")
	@ModelProperty(comment = "单价")
	BigDecimal priceOfUnit;
	
	@Column(name = "IS_TAX")
	@ModelProperty(comment = "是否含税")
	String isTax;
	
	//CRVAT_INV_REQ_BATCHES_L_ID
	@Column(name = "CRVAT_INV_REQ_BATCHES_L_ID")
	@ModelProperty(comment = "批量开票")
	String crvatInvReqBatchesLId;
	
	public String getCrvatInvReqBatchesLId() {
		return crvatInvReqBatchesLId;
	}


	public void setCrvatInvReqBatchesLId(String crvatInvReqBatchesLId) {
		this.crvatInvReqBatchesLId = crvatInvReqBatchesLId;
	}


	@Transient
	int currentTypeNum;
	@Transient
	private List<InvoicePrintPoolD> poolDs = new ArrayList<InvoicePrintPoolD>();
	

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


	public String getInvoicePrtPoolHId() {
		return invoicePrtPoolHId;
	}

	public void setInvoicePrtPoolHId(String invoicePrtPoolHId) {
		this.invoicePrtPoolHId = invoicePrtPoolHId;
	}

	public String getInventoryItemId() {
		return inventoryItemId;
	}

	public void setInventoryItemId(String inventoryItemId) {
		this.inventoryItemId = inventoryItemId;
	}

	public String getInventoryItemNumber() {
		return inventoryItemNumber;
	}

	public void setInventoryItemNumber(String inventoryItemNumber) {
		this.inventoryItemNumber = inventoryItemNumber;
	}

	public String getInventoryItemDescripton() {
		return inventoryItemDescripton;
	}

	public void setInventoryItemDescripton(String inventoryItemDescripton) {
		this.inventoryItemDescripton = inventoryItemDescripton;
	}

	public String getInventoryItemModels() {
		return inventoryItemModels;
	}

	public void setInventoryItemModels(String inventoryItemModels) {
		this.inventoryItemModels = inventoryItemModels;
	}

	public String getUomCode() {
		return uomCode;
	}

	public void setUomCode(String uomCode) {
		this.uomCode = uomCode;
	}

	public String getUomCodeDescripton() {
		return uomCodeDescripton;
	}

	public void setUomCodeDescripton(String uomCodeDescripton) {
		this.uomCodeDescripton = uomCodeDescripton;
	}


	public BigDecimal getInvoiceAmount() {
		return invoiceAmount;
	}

	public void setInvoiceAmount(BigDecimal invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}

	public BigDecimal getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(BigDecimal taxRate) {
		this.taxRate = taxRate;
	}

	public BigDecimal getAcctdAmountCR() {
		return acctdAmountCR;
	}

	public void setAcctdAmountCR(BigDecimal acctdAmountCR) {
		this.acctdAmountCR = acctdAmountCR;
	}

	public BigDecimal getVatAmount() {
		return vatAmount;
	}

	public void setVatAmount(BigDecimal vatAmount) {
		this.vatAmount = vatAmount;
	}

	public String getSourceCode() {
		return sourceCode;
	}

	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}

	public String getSourceTrxId() {
		return sourceTrxId;
	}

	public void setSourceTrxId(String sourceTrxId) {
		this.sourceTrxId = sourceTrxId;
	}

	public String getSourceTrxLineId() {
		return sourceTrxLineId;
	}

	public void setSourceTrxLineId(String sourceTrxLineId) {
		this.sourceTrxLineId = sourceTrxLineId;
	}

	public String getSourceTrxLineType() {
		return sourceTrxLineType;
	}

	public void setSourceTrxLineType(String sourceTrxLineType) {
		this.sourceTrxLineType = sourceTrxLineType;
	}

	public String getSourceTrxDetailTaxLineId() {
		return sourceTrxDetailTaxLineId;
	}

	public void setSourceTrxDetailTaxLineId(String sourceTrxDetailTaxLineId) {
		this.sourceTrxDetailTaxLineId = sourceTrxDetailTaxLineId;
	}

	public String getSourceDistributionId() {
		return sourceDistributionId;
	}

	public void setSourceDistributionId(String sourceDistributionId) {
		this.sourceDistributionId = sourceDistributionId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return EndDate;
	}

	public void setEndDate(Date endDate) {
		EndDate = endDate;
	}


	public InvoicePrintPoolH getInvoicePrintPoolH() {
		return invoicePrintPoolH;
	}

	public void setInvoicePrintPoolH(InvoicePrintPoolH invoicePrintPoolH) {
		this.invoicePrintPoolH = invoicePrintPoolH;
	}

	public int getCurrentTypeNum() {
		return currentTypeNum;
	}

	public void setCurrentTypeNum(int currentTypeNum) {
		this.currentTypeNum = currentTypeNum;
	}


	public BigDecimal getPriceOfUnit() {
		return priceOfUnit;
	}


	public void setPriceOfUnit(BigDecimal priceOfUnit) {
		this.priceOfUnit = priceOfUnit;
	}


	public List<InvoicePrintPoolD> getPoolDs() {
		return poolDs;
	}


	public void setPoolDs(List<InvoicePrintPoolD> poolDs) {
		this.poolDs = poolDs;
	}


	public String getIsTax() {
		return isTax;
	}


	public void setIsTax(String isTax) {
		this.isTax = isTax;
	}
	
	
	
}
