package com.deloitte.tms.vat.inf.taxinfo.model.invoiceprint.issue;

import com.deloitte.tms.vat.inf.taxinfo.annotation.AisinoSHSerializeProperty;
import com.deloitte.tms.vat.inf.taxinfo.annotation.AisinoSerializeProperty;

/**
 * 发票开具行
 * @author bo.wang
 * @create 2016年4月12日 上午10:47:12
 */
public class InvoiceIssueDetail {
	
	/**************************公共部分和报文差异*******************************************/
	@AisinoSerializeProperty(length=92,des="商品名称",require=true,serializeName="SPMC")
	@AisinoSHSerializeProperty(length=92,des="商品名称",require=true,serializeName="prodname")
	String commodityName;
	@AisinoSerializeProperty(length=2,des="含税标志",require=true,serializeName="HSBZ")
	@AisinoSHSerializeProperty(length=2,des="含税标志0-不含税，1–含税",require=true,serializeName="includetax")
	String isTax;//0：不含税 1：含税 HSBZEnums
	@AisinoSerializeProperty(des="税率",require=true,serializeName="SLV")
	@AisinoSHSerializeProperty(des="税率",require=true,serializeName="taxrate")
	Double taxRate;
	@AisinoSerializeProperty(des="金额",require=true,serializeName="JE")
	@AisinoSHSerializeProperty(des="金额(判断是否含税看includetax)",require=true,serializeName="amount")//(判断是否含税看includetax)
	Double invoiceAmount;
	@AisinoSerializeProperty(des="单价",serializeName="DJ")
	@AisinoSHSerializeProperty(des="单价,(判断是否含税看includetax)",serializeName="pirce")//(判断是否含税看includetax)
	Double unitPrice;
	@AisinoSerializeProperty(length=230,des="计量单位",serializeName="JLDW")
	@AisinoSHSerializeProperty(length=40,des="计量单位",serializeName="unit")
	String measurementUnit;
	@AisinoSerializeProperty(length=8,des="规格型号",serializeName="GGXH")
	@AisinoSHSerializeProperty(length=40,des="规格型号",serializeName="spec")
	String specification;
	@AisinoSerializeProperty(des="税额",require=true,serializeName="SE")
	@AisinoSHSerializeProperty(des="税额（如果为空，系统自行计算）",require=true,serializeName="tax")
	Double taxAmount;
	@AisinoSerializeProperty(des="数量",serializeName="SL")
	@AisinoSHSerializeProperty(des="数量",serializeName="quantity")
	Long quantity;
	/**************************公共部分和报文差异*******************************************/
	
	
	/******************************上海单机专用**********************************/
	@AisinoSHSerializeProperty(length=50,des="商品编号（可以为空，发票上不会体现）",serializeName="commodityNo")
	String commodityNo;
	@AisinoSHSerializeProperty(length=50,des="折扣金额(判断是否含税看includetax), 红冲单据无折扣金额",serializeName="discountValue")
	Double discountValue;	
	@AisinoSHSerializeProperty(length=50,des="折扣税额，红冲单据无折扣税额(如果为空，系统自行计算）",serializeName="discountTax")
	Double discountTax;	
	/******************************上海单机专用**********************************/
	
	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}
	public void setIsTax(String isTax) {
		this.isTax = isTax;
	}
	public void setTaxRate(Double taxRate) {
		this.taxRate = taxRate;
	}
	public void setInvoiceAmount(Double invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}
	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public void setMeasurementUnit(String measurementUnit) {
		this.measurementUnit = measurementUnit;
	}
	public void setSpecification(String specification) {
		this.specification = specification;
	}
	public void setTaxAmount(Double taxAmount) {
		this.taxAmount = taxAmount;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	public String getCommodityName() {
		return commodityName;
	}
	public String getIsTax() {
		return isTax;
	}
	public Double getTaxRate() {
		return taxRate;
	}
	public Double getInvoiceAmount() {
		return invoiceAmount;
	}
	public Double getUnitPrice() {
		return unitPrice;
	}
	public String getMeasurementUnit() {
		return measurementUnit;
	}
	public String getSpecification() {
		return specification;
	}
	public Double getTaxAmount() {
		return taxAmount;
	}
	public Long getQuantity() {
		return quantity;
	}
	public String getCommodityNo() {
		return commodityNo;
	}
	public void setCommodityNo(String commodityNo) {
		this.commodityNo = commodityNo;
	}
	public Double getDiscountValue() {
		return discountValue;
	}
	public void setDiscountValue(Double discountValue) {
		this.discountValue = discountValue;
	}
	public Double getDiscountTax() {
		return discountTax;
	}
	public void setDiscountTax(Double discountTax) {
		this.discountTax = discountTax;
	}
	
	
}
