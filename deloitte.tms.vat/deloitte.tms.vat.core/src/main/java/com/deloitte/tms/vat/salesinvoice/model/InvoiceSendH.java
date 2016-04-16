
/**    
 * Copyright (C),Deloitte
 * @FileName: InvoiceSend.java  
 * @Package: com.deloitte.tms.vat.salesinvoice.model  
 * @Description: //模块目的、功能描述  
 * @Author tomfang  
 * @Date 2016年3月17日 下午3:26:41  
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
import com.deloitte.tms.pl.core.commons.constant.TablePreDef;
import com.deloitte.tms.pl.core.model.impl.BaseEntity;


/**  
 *发票寄送
 * 功能详细描述
 * @author tomfang
 * @create 2016年3月17日 下午3:26:41 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Entity
@Table(name = InvoiceSendH.TABLE)
@ModelProperty(comment = "寄送发票")
public class InvoiceSendH extends BaseEntity{
	public static final String TABLE = "TMS_CRVAT_INV_DELIVERY_H";
	public static final String SEQ = TABLE;
	@Id
	@Column(name = "INVOICE_DELIVERY_H_ID", length = 255)
	@ModelProperty(comment = "发票寄送主ID")
	String id;

	@Column(name = "DELIVERY_NUMBER", length = 255)
	@ModelProperty(comment = "发票寄送单流水号(由系统自动生成)")
	String deliveryNumber;

	@Column(name = "EXPRESS_NO", length = 255)
	@ModelProperty(comment = "快递单号")
	String expressNo;

	@Column(name = "EXPRESS_COMPANY", length = 255)
	@ModelProperty(comment = "快递公司_枚举值")
	String expressCompany;

	@Column(name = "DELIVERY_STATUS", length = 255)
	@ModelProperty(comment = "寄送状态_枚举值")
	String deliveryStatus;
	
	@Column(name = "CUSTOMER_ID", length = 255)
	@ModelProperty(comment = "客户关系ID")
	String customerId;

	@Column(name = "CUSTOMER_NUMBER", length = 255)
	@ModelProperty(comment = "客户代码")
	String customerCode;

	@Column(name = "CUSTOMER_NAME", length = 255)
	@ModelProperty(comment = "客户名称")
	String customerName;

	@Column(name = "BOOKED_DATE", length = 255)
	@ModelProperty(comment = "登记日期")
	Date bookedDate;
	
	@Column(name = "DELIVERY_DATE", length = 255)
	@ModelProperty(comment = "寄送日期")
	Date deliveryDate;
	
	@Column(name = "DELIVERY_BY", length = 255)
	@ModelProperty(comment = "寄件人")
	String deliveryBy;
	
	@Column(name = "LEGAL_ENTITY_ID", length = 255)
	@ModelProperty(comment = "纳税主体ID")
	String legalEntityId;
	
	@Column(name = "PRINT_STATUS", length = 255)
	@ModelProperty(comment = "打印状态_枚举值(已打印/未打印)")
	String printStatus;
	
	@Column(name = "RECIPIENT_NAME", length = 255)
	@ModelProperty(comment = "收件人姓名")
	String recipientName;
	
	@Column(name = "RECIPIENT_ADDR", length = 255)
	@ModelProperty(comment = "收件人地址")
	String recipientAddr;
	
	@Column(name = "RECIPIENT_PHONE", length = 255)
	@ModelProperty(comment = "收件人电话")
	String recipientPhone;
	
	
	@Column(name = "RECIPIENT_COMPANY", length = 255)
	@ModelProperty(comment = "收件人公司")
	String recipientCompany;
	
	@Column(name = "DECRIPTION", length = 255)
	@ModelProperty(comment = "描述")
	String description;
	
	@Column(name = "START_DATE", length = 255)
	@ModelProperty(comment = "有效日期")
	Date statDate;
	
	@Column(name = "END_DATE", length = 255)
	@ModelProperty(comment = "失效日期")
	String endDate;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDeliveryNumber() {
		return deliveryNumber;
	}

	public void setDeliveryNumber(String deliveryNumber) {
		this.deliveryNumber = deliveryNumber;
	}

	public String getExpressNo() {
		return expressNo;
	}

	public void setExpressNo(String expressNo) {
		this.expressNo = expressNo;
	}

	public String getExpressCompany() {
		return expressCompany;
	}

	public void setExpressCompany(String expressCompany) {
		this.expressCompany = expressCompany;
	}

	public String getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Date getBookedDate() {
		return bookedDate;
	}

	public void setBookedDate(Date bookedDate) {
		this.bookedDate = bookedDate;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getDeliveryBy() {
		return deliveryBy;
	}

	public void setDeliveryBy(String deliveryBy) {
		this.deliveryBy = deliveryBy;
	}

	public String getLegalEntityId() {
		return legalEntityId;
	}

	public void setLegalEntityId(String legalEntityId) {
		this.legalEntityId = legalEntityId;
	}

	public String getPrintStatus() {
		return printStatus;
	}

	public void setPrintStatus(String printStatus) {
		this.printStatus = printStatus;
	}

	public String getRecipientName() {
		return recipientName;
	}

	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}

	public String getRecipientAddr() {
		return recipientAddr;
	}

	public void setRecipientAddr(String recipientAddr) {
		this.recipientAddr = recipientAddr;
	}

	public String getRecipientPhone() {
		return recipientPhone;
	}

	public void setRecipientPhone(String recipientPhone) {
		this.recipientPhone = recipientPhone;
	}

	public String getRecipientCompany() {
		return recipientCompany;
	}

	public void setRecipientCompany(String recipientCompany) {
		this.recipientCompany = recipientCompany;
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

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	

	
}
