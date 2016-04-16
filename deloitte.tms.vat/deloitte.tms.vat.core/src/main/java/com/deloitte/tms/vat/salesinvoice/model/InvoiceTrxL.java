
/**    
 * Copyright (C),Deloitte
 * @FileName: InvoiceTrxL.java  
 * @Package: com.deloitte.tms.vat.salesinvoice.model  
 * @Description: //模块目的、功能描述  
 * @Author stonshi  
 * @Date 2016年3月18日 下午9:12:07  
 * @History: //修改记录
 * 〈author〉      〈time〉      〈version〉       〈desc〉
 * 修改人姓名            修改时间            版本号              描述   
*/  

package com.deloitte.tms.vat.salesinvoice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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

import com.deloitte.tms.base.masterdata.model.Customer;
import com.deloitte.tms.pl.core.commons.annotation.ModelProperty;
import com.deloitte.tms.pl.core.commons.constant.TablePreDef;
import com.deloitte.tms.pl.core.hibernate.identifier.Ling2IdGenerator;
import com.deloitte.tms.pl.core.model.impl.BaseEntity;


/**  
 *〈一句话功能简述〉
 * 功能详细描述
 * @author stonshi
 * @create 2016年3月18日 下午9:12:07 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Entity
@Table(name = InvoiceTrxL.TABLE)
@ModelProperty(comment = "销项税发票请领入库行")
public class InvoiceTrxL extends BaseEntity {

	public static final String TABLE = TablePreDef.TMS_CRVAT_INVOICE+"TRX_L";
	public static final String SEQ = TABLE;
	
	@Id
	/*@GenericGenerator(name=SEQ+"_GENERATOR",strategy=Ling2IdGenerator.STRATEGY_NAME,parameters={@Parameter(name="pkColumnValue",value=SEQ)})
	@GeneratedValue(strategy = GenerationType.TABLE, generator = SEQ+"_GENERATOR")*/
	@Column(name = "CRVAT_INVOICE_TRX_L_ID",  length = 36)
	String id;
	
	@Column(name = "CRVAT_INVOICE_TRX_H_ID",length=100)
	@ModelProperty(comment = "请领头ID")
	String crvatInvoiceTrxHid;	
	@ManyToOne(fetch = FetchType.LAZY)
	@Cascade(CascadeType.REFRESH)
	@JoinColumn(name="CRVAT_INVOICE_TRX_H_ID",insertable=false,updatable=false,nullable=true)
	InvoiceTrxH invoiceTrxH;
	
	@Column(name = "START_INVOICE_NUMBER",length=100)
	@ModelProperty(comment = "发票起始编号")
	String startInvoiceNumber;
	
	@Column(name = "END_INVOICE_NUMBER",length=100)
	@ModelProperty(comment = "发票终止编号")
	String endInvoiceNumber;
	
	@Column(name = "INVOICE_LIMIT_AMOUNT",length=100)
	@ModelProperty(comment = "发票限额")
	String invoiceLimitAmount;

	@Column(name = "INVOICE_TRX_BY",length=100)
	@ModelProperty(comment = "入库人员")
	String invoiceTrxBy;
	
	@Column(name = "INVOICE_CODE",length=100)
	@ModelProperty(comment = "发票代码")
	String invoiceCode;
	
	@Column(name = "IS_USAGE",length=100)
	@ModelProperty(comment = "是否已使用")
	String isUsage;
	
	@Column(name = "LEGAL_ENTITY_ID",length=100)
	@ModelProperty(comment = "纳税人实体ID")
	String legalEntityId;
	
	@Column(name = "INVOICE_QTY",length=100)
	@ModelProperty(comment = "发票份数")
	String invoiceQty;
	
	@Column(name = "INVOICE_CATEGORY",length=100)
	@ModelProperty(comment = "发票类型")
	String invoiceCategory;
	
	public String getInvoiceCategory() {
		return invoiceCategory;
	}

	public void setInvoiceCategory(String invoiceCategory) {
		this.invoiceCategory = invoiceCategory;
	}

	public String getInvoiceQty() {
		return invoiceQty;
	}

	public void setInvoiceQty(String invoiceQty) {
		this.invoiceQty = invoiceQty;
	}

	public String getLegalEntityId() {
		return legalEntityId;
	}

	public void setLegalEntityId(String legalEntityId) {
		this.legalEntityId = legalEntityId;
	}

	
	public String getInvoiceLimitAmount() {
		return invoiceLimitAmount;
	}

	public void setInvoiceLimitAmount(String invoiceLimitAmount) {
		this.invoiceLimitAmount = invoiceLimitAmount;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCrvatInvoiceTrxHid() {
		return crvatInvoiceTrxHid;
	}

	public void setCrvatInvoiceTrxHid(String crvatInvoiceTrxHid) {
		this.crvatInvoiceTrxHid = crvatInvoiceTrxHid;
	}

	public String getStartInvoiceNumber() {
		return startInvoiceNumber;
	}

	public void setStartInvoiceNumber(String startInvoiceNumber) {
		this.startInvoiceNumber = startInvoiceNumber;
	}

	public String getEndInvoiceNumber() {
		return endInvoiceNumber;
	}

	public void setEndInvoiceNumber(String endInvoiceNumber) {
		this.endInvoiceNumber = endInvoiceNumber;
	}

	

	public String getInvoiceTrxBy() {
		return invoiceTrxBy;
	}

	public void setInvoiceTrxBy(String invoiceTrxBy) {
		this.invoiceTrxBy = invoiceTrxBy;
	}

	public String getInvoiceCode() {
		return invoiceCode;
	}

	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}

	public String getIsUsage() {
		return isUsage;
	}

	public void setIsUsage(String isUsage) {
		this.isUsage = isUsage;
	}
}
