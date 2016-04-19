package com.deloitte.tms.vat.salesinvoice.jobs.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.deloitte.tms.base.masterdata.model.Customer;
import com.deloitte.tms.pl.core.commons.annotation.ModelProperty;
import com.deloitte.tms.vat.salesinvoice.model.InvoiceReqL;

public class TmsCrvatInvReqHImpl extends InvoiceReqL {

	//------------柜台开票头信息---------------
	//头表id
	private String crvatInvoiceReqHId;

	//审批流状态描述
	private String approvalDesc;

	//申请开票部门
	private String reqDeptNo;

	//审批状态
	private String approvalStatus;

	//开票申请单号
	private String crvatInvoiceReqNumber;

	//购方证件类型_枚举值
	private String custRegistrationCode;

	//购方证件号码
	private String custRegistrationNumber;

	//购方证件ID
	String customerId;
	
	//购方证件名称
	private String customerName;

	//失效日期
	private Date endDate;

	//申请日期
	private Date invoiceReqDate;

	//申请开票类型(柜台/特殊/自动)
	private String invoiceReqType;

	//开票方式_枚举值(明细/汇总)
	private String invoicingType;

	//是否无主交易(已废除)
	private String isExitsCustomer;

	//销方纳税人实体CODE
	private String legalEntityCode;

	//销方纳税人实体Id
	private String legalEntityId;

	//销方纳税人实体名称
	private String legalEntityName;

	//销方纳税人实体识别号类型_枚举值
	private String registrationCode;

	//购方纳税识别号
	private String registrationNumber;

	//有效日期
	private Date startDate;

	//提交日期
	private Date submitDate;

	//工作流ID
	private String wfTaskId;
	
	//匹配状态
	private String mappingStatus;
	
	//是否已全部匹配完成
	private String isAllMapping;
	//项目ID
	private String projectId;
	//发票类型_枚举值(专票/普票/不可开票/混合)
	private String invoiceCategory;
	//生成的准备单数量
	private Integer qtyOfPreInvoice;
   //组织id
	private String orgId;
	//是否已收款
	private String isReceipts;
	//发票打印受理层级范围_枚举值
	private String reqInvoiceRange;
	//寄送类型_枚举值
	private String deliveryType;
	//是否含税
	private String isTax;
	//业务大类_枚举值
	private String businessCategories;
	public String getCrvatInvoiceReqHId() {
		return crvatInvoiceReqHId;
	}
	public void setCrvatInvoiceReqHId(String crvatInvoiceReqHId) {
		this.crvatInvoiceReqHId = crvatInvoiceReqHId;
	}
	public String getApprovalDesc() {
		return approvalDesc;
	}
	public void setApprovalDesc(String approvalDesc) {
		this.approvalDesc = approvalDesc;
	}
	public String getReqDeptNo() {
		return reqDeptNo;
	}
	public void setReqDeptNo(String reqDeptNo) {
		this.reqDeptNo = reqDeptNo;
	}
	public String getApprovalStatus() {
		return approvalStatus;
	}
	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
	public String getCrvatInvoiceReqNumber() {
		return crvatInvoiceReqNumber;
	}
	public void setCrvatInvoiceReqNumber(String crvatInvoiceReqNumber) {
		this.crvatInvoiceReqNumber = crvatInvoiceReqNumber;
	}
	public String getCustRegistrationCode() {
		return custRegistrationCode;
	}
	public void setCustRegistrationCode(String custRegistrationCode) {
		this.custRegistrationCode = custRegistrationCode;
	}
	public String getCustRegistrationNumber() {
		return custRegistrationNumber;
	}
	public void setCustRegistrationNumber(String custRegistrationNumber) {
		this.custRegistrationNumber = custRegistrationNumber;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Date getInvoiceReqDate() {
		return invoiceReqDate;
	}
	public void setInvoiceReqDate(Date invoiceReqDate) {
		this.invoiceReqDate = invoiceReqDate;
	}
	public String getInvoiceReqType() {
		return invoiceReqType;
	}
	public void setInvoiceReqType(String invoiceReqType) {
		this.invoiceReqType = invoiceReqType;
	}
	public String getInvoicingType() {
		return invoicingType;
	}
	public void setInvoicingType(String invoicingType) {
		this.invoicingType = invoicingType;
	}
	public String getIsExitsCustomer() {
		return isExitsCustomer;
	}
	public void setIsExitsCustomer(String isExitsCustomer) {
		this.isExitsCustomer = isExitsCustomer;
	}
	public String getLegalEntityCode() {
		return legalEntityCode;
	}
	public void setLegalEntityCode(String legalEntityCode) {
		this.legalEntityCode = legalEntityCode;
	}
	public String getLegalEntityId() {
		return legalEntityId;
	}
	public void setLegalEntityId(String legalEntityId) {
		this.legalEntityId = legalEntityId;
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
	public String getRegistrationNumber() {
		return registrationNumber;
	}
	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getSubmitDate() {
		return submitDate;
	}
	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
	}
	public String getWfTaskId() {
		return wfTaskId;
	}
	public void setWfTaskId(String wfTaskId) {
		this.wfTaskId = wfTaskId;
	}
	public String getMappingStatus() {
		return mappingStatus;
	}
	public void setMappingStatus(String mappingStatus) {
		this.mappingStatus = mappingStatus;
	}
	public String getIsAllMapping() {
		return isAllMapping;
	}
	public void setIsAllMapping(String isAllMapping) {
		this.isAllMapping = isAllMapping;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getInvoiceCategory() {
		return invoiceCategory;
	}
	public void setInvoiceCategory(String invoiceCategory) {
		this.invoiceCategory = invoiceCategory;
	}
	public Integer getQtyOfPreInvoice() {
		return qtyOfPreInvoice;
	}
	public void setQtyOfPreInvoice(Integer qtyOfPreInvoice) {
		this.qtyOfPreInvoice = qtyOfPreInvoice;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getIsReceipts() {
		return isReceipts;
	}
	public void setIsReceipts(String isReceipts) {
		this.isReceipts = isReceipts;
	}
	public String getReqInvoiceRange() {
		return reqInvoiceRange;
	}
	public void setReqInvoiceRange(String reqInvoiceRange) {
		this.reqInvoiceRange = reqInvoiceRange;
	}
	public String getDeliveryType() {
		return deliveryType;
	}
	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}
	public String getIsTax() {
		return isTax;
	}
	public void setIsTax(String isTax) {
		this.isTax = isTax;
	}
	public String getBusinessCategories() {
		return businessCategories;
	}
	public void setBusinessCategories(String businessCategories) {
		this.businessCategories = businessCategories;
	}
	
	
	
	
	
	

}
