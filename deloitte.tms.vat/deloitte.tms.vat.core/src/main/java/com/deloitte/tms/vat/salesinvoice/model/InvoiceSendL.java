
/**    
 * Copyright (C),Deloitte
 * @FileName: InvoiceSendL.java  
 * @Package: com.deloitte.tms.vat.salesinvoice.model  
 * @Description: //模块目的、功能描述  
 * @Author tomfang  
 * @Date 2016年3月17日 下午3:44:38  
 * @History: //修改记录
 * 〈author〉      〈time〉      〈version〉       〈desc〉
 * 修改人姓名            修改时间            版本号              描述   
*/  

package com.deloitte.tms.vat.salesinvoice.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.deloitte.tms.pl.core.commons.annotation.ModelProperty;
import com.deloitte.tms.pl.core.model.impl.BaseEntity;


/**  
 *发票寄送
 * 功能详细描述
 * @author tomfang
 * @create 2016年3月17日 下午3:44:38 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Entity
@Table(name = InvoiceSendL.TABLE)
@ModelProperty(comment = "寄送发票")
public class InvoiceSendL extends BaseEntity{
	public static final String TABLE = "TMS_CRVAT_INV_DELIVERY_L";
	public static final String SEQ = TABLE;
	@Id
	@Column(name = "INVOICE_DELIVERY_L_ID", length = 255)
	@ModelProperty(comment = "发票寄送主ID")
	String id;

	@Column(name = "INVOICE_PRT_POOL_H_ID", length = 255)
	@ModelProperty(comment = "发票打印池-头)")
	String invoicePrtPoolHId;

	@Column(name = "INVOICE_DELIVERY_H_ID", length = 255)
	@ModelProperty(comment = "发票寄送头ID")
	String invoiceDeliveryHId;

	@Column(name = "DECRIPTION", length = 255)
	@ModelProperty(comment = "描述")
	String description;
	
	@Column(name = "START_DATE", length = 255)
	@ModelProperty(comment = "有效日期")
	Date statDate;
	
	@Column(name = "END_DATE", length = 255)
	@ModelProperty(comment = "失效日期")
	Date endDate;
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInvoicePrtPoolHId() {
		return invoicePrtPoolHId;
	}

	public void setInvoicePrtPoolHId(String invoicePrtPoolHId) {
		this.invoicePrtPoolHId = invoicePrtPoolHId;
	}

	public String getInvoiceDeliveryHId() {
		return invoiceDeliveryHId;
	}

	public void setInvoiceDeliveryHId(String invoiceDeliveryHId) {
		this.invoiceDeliveryHId = invoiceDeliveryHId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStatDate() {
		return statDate;
	}

	public void setStatDate(Date statDate) {
		this.statDate = statDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}



}
