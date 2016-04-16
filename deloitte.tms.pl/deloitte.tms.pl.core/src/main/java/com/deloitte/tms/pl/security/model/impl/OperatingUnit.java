
/**    
 * Copyright (C),Deloitte
 * @FileName: OperatingUnit.java  
 * @Package: com.deloitte.tms.pl.security.model.impl  
 * @Description: //纳税主体组识 
 * @Author bo.wang  
 * @Date 2016年3月15日 下午2:38:43  
 * @History: //修改记录
 * 〈author〉      〈time〉      〈version〉       〈desc〉
 * 修改人姓名            修改时间            版本号              描述   
*/  

package com.deloitte.tms.pl.security.model.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.deloitte.tms.pl.core.commons.annotation.ModelProperty;
import com.deloitte.tms.pl.core.commons.constant.TablePreDef;
import com.deloitte.tms.pl.core.model.impl.BaseRelation;


/**  
 * 纳税主体组识
 * @author bo.wang
 * @create 2016年3月15日 下午2:38:43 
 * @version 1.0.0
 */
@Entity
@Table(name=OperatingUnit.TABLE)
public class OperatingUnit extends BaseRelation{
	
	public static final String TABLE = TablePreDef.BASEPRE+"OPERATING_UNIT";
	public static final String SEQ = TABLE;

	@Id
	@Column(name="OPERATING_UNIT_ID",length=60)
	String id;
	
	@Column(name="OPERATING_UNIT_CODE",length=100)
	@ModelProperty(comment="纳税主体CODE")
	String unitCode;

	@Column(name="OPERATING_UNIT_NAME",length=120)
	@ModelProperty(comment="纳税主体名称")
	String unitName;

	@Column(name="OPERATING_UNIT_TYPE",length=120)
	@ModelProperty(comment="纳税主体类型")
	String unitType;

	@Column(name="DESCRIPTION",length=120)
	@ModelProperty(comment="描述")
	String des;

	@Column(name="PARENT_ID",length=36)
	@ModelProperty(comment="父结点ID")
	String parentId;

	@Column(name="IS_ENABLE_PRINT")
	@ModelProperty(comment="是否打印点")
	Boolean isPrint;

	@Column(name="INVOICE_LIMIT_AMOUNT")
	@ModelProperty(comment="发票开取限额")
	Long invoiceLimit;

	@Column(name="IS_PAYMENT_COLLECT",length=10)
	@ModelProperty(comment="是否缴纳汇总")
	Boolean isPaymentCollect;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getUnitType() {
		return unitType;
	}

	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public Boolean getIsPrint() {
		return isPrint;
	}

	public void setIsPrint(Boolean isPrint) {
		this.isPrint = isPrint;
	}

	public Long getInvoiceLimit() {
		return invoiceLimit;
	}

	public void setInvoiceLimit(Long invoiceLimit) {
		this.invoiceLimit = invoiceLimit;
	}

	public Boolean getIsPaymentCollect() {
		return isPaymentCollect;
	}

	public void setIsPaymentCollect(Boolean isPaymentCollect) {
		this.isPaymentCollect = isPaymentCollect;
	}

	
}
