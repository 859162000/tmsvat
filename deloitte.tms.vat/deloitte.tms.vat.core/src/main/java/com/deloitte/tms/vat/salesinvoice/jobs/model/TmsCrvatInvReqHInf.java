package com.deloitte.tms.vat.salesinvoice.jobs.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.deloitte.tms.pl.core.commons.annotation.ModelProperty;
import com.deloitte.tms.pl.core.hibernate.identifier.Ling2UUIDGenerator;
import com.deloitte.tms.pl.core.model.impl.BaseEntity;

/**
 * 柜台开票接口实体
 */
@Entity
@Table(name = TmsCrvatInvReqHInf.TABLE)
public class TmsCrvatInvReqHInf extends BaseEntity {

	public static final String TABLE = "TMS_CRVAT_INV_REQ_H_INF";
	public static final String SEQ = TABLE;

	@Id
	@GenericGenerator(name = SEQ + "_GENERATOR", strategy = Ling2UUIDGenerator.STRATEGY_NAME, parameters = { @Parameter(name = "pkColumnValue", value = SEQ) })
	@GeneratedValue(strategy = GenerationType.TABLE, generator = SEQ
			+ "_GENERATOR")
	@Column(name = "INTERFACE_TRX_HEADER_ID")
	@ModelProperty(comment = "接口处理ID")
	String id;
	@Column(name = "INTERFACE_TRX_DATE")
	@ModelProperty(comment = "接口处理日期")
	Date interfaceTrxDate;
	@Column(name = "INTERFACE_TRX_FLAG")
	@ModelProperty(comment = "接口处理标记")
	String interfaceTrxFlag;
	@Column(name = "INTERFACE_TRX_MSG")
	@ModelProperty(comment = "接口处理信息")
	String interfaceTrxMsg;
	@Column(name = "REQUEST_ID")
	@ModelProperty(comment = "接口处理处理请求ID(自动任务ID)")
	String requestId;
	@Column(name = "CRVAT_INVOICE_REQ_H_ID")
	@ModelProperty(comment = "开票申请单-头表ID")
	String crvatInvoiceReqHId;
	@Column(name = "CRVAT_INVOICE_REQ_NUMBER")
	@ModelProperty(comment = "开票申请单号")
	String crvatInvoiceReqNumber;
	@Column(name = "ORG_ID")
	@ModelProperty(comment = "组织ID")
	String orgId;
	@Column(name = "SOURCE_ORG_ID")
	@ModelProperty(comment = "来源组织ID	")
	String sourceOrgId;
	@Column(name = "SOURCE_ORG_CODE")
	@ModelProperty(comment = "来源组织CODE	")
	String sourceOrgCode;

	@Column(name = "SUBMIT_DATE")
	@ModelProperty(comment = "提交日期		")
	Date submitDate;

	@Column(name = "INVOICE_REQ_DATE")
	@ModelProperty(comment = "申请日期		")
	Date invoiceReqDate;

	@Column(name = "INVOICE_REQ_TYPE")
	@ModelProperty(comment = "申请开票类型(柜台/特殊/自动)	")
	String invoiceReqType;//

	@Column(name = "REQ_DEPT_NO")
	@ModelProperty(comment = "申请开票部门	")
	String reqDeptNo;//

	@Column(name = "SOURCE_REQ_DEPT_NO")
	@ModelProperty(comment = "来源申请部门	")
	String sourceReqDeptNo;//

	@Column(name = "SOURCE_INVOICE_REQ_BY")
	@ModelProperty(comment = "来源申请人	")
	String sourceInvoiceReqBy;//

	@Column(name = "INVOICE_CATEGORY")
	@ModelProperty(comment = "发票类型_枚举值(专票/普票/不可开票/混合)	")
	String invoiceCategory;//

	@Column(name = "SOURCE_CODE")
	@ModelProperty(comment = "来源(手工/自动匹配)	")
	String sourceCode;//

	@Column(name = "CUSTOMER_ID")
	@ModelProperty(comment = "购方名称ID	")
	String customerId;//

	@Column(name = "SOURCE_CUSTOMER_ID")
	@ModelProperty(comment = "来源购方ID	")
	String sourceCustomerId;//

	@Column(name = "SOURCE_CUSTOMER_NUMBER")
	@ModelProperty(comment = "来源购方代码")
	String sourceCustomerNumber;//

	@Column(name = "SOURCE_CUSTOMER_ACC_NUM")
	@ModelProperty(comment = "来源购方资金账号代码	")
	String sourceCustomerAccNum;//

	@Column(name = "LEGAL_ENTITY_ID")
	@ModelProperty(comment = "销方纳税人实体ID	")
	String legalEntityId;//

	@Column(name = "SOURCE_LEGAL_ENTITY_ID")
	@ModelProperty(comment = "来源销方纳税人ID	")
	String sourceLegalEntityId;//

	@Column(name = "SOURCE_LEGAL_ENTITY_CODE")
	@ModelProperty(comment = "来源销方纳税人代码")
	String sourceLegalEntityCode;//

	@Column(name = "INVOICING_TYPE")
	@ModelProperty(comment = "开票方式_枚举值(明细/汇总)	")
	String invoicingType;//

	@Column(name = "REQ_INVOICE_RANGE")
	@ModelProperty(comment = "发票打印受理层级范围_枚举值	")
	String reqInvoiceRange;//

	@Column(name = "MAPPING_STATUS")
	@ModelProperty(comment = "匹配状态")
	String mappingStatus;//

	@Column(name = "IS_ALL_MAPPING")
	@ModelProperty(comment = "是否已全部匹配完成	")
	String isAllMapping;//

	@Column(name = "IS_RECEIPTS")
	@ModelProperty(comment = "是否已收款	")
	String isReceipts;//

	@Column(name = "BUSINESS_CATEGORIES")
	@ModelProperty(comment = "业务大类_枚举值")
	String businessCategories;//

	@Column(name = "DELIVERY_TYPE")
	@ModelProperty(comment = "寄送类型_枚举值(内部/外包)")
	String deliveryType;//

	@Column(name = "WF_TASK_ID")
	@ModelProperty(comment = "工作流ID	")
	String wfTaskId;//

	@Column(name = "SOURCE_APPROVAL_BY")
	@ModelProperty(comment = "最后审批人	")
	String sourceApprovalBy;//

	@Column(name = "APPROVAL_DATE")
	@ModelProperty(comment = "最后审批日期	")
	Date approvalDate;//

	@Column(name = "APPROVAL_DESC")
	@ModelProperty(comment = "审批流状态描述")
	String approvalDesc;//

	@Column(name = "APPROVAL_STATUS")
	@ModelProperty(comment = "审批状态	")
	String approvalStatus;//

	@Column(name = "PROJECT_ID")
	@ModelProperty(comment = "项目ID")
	String projectId;//

	@Column(name = "SOURCE_PROJECT_NUMBER")
	@ModelProperty(comment = "来源项目编码	")
	String sourceProjectNumber;//

	@Column(name = "SOURCE_CONTRACT_NUMBER")
	@ModelProperty(comment = "来源合同编码	")
	String sourceContractNumber;//

	@Column(name = "QTY_OF_PRE_INVOICE")
	@ModelProperty(comment = "生成的准备单数量	")
	Long qtyOfPreInvoice;//

	@Column(name = "START_TRX_DATE")
	@ModelProperty(comment = "交易起始日期	")
	Date startTrxDate;//

	@Column(name = "END_TRX_DATE")
	@ModelProperty(comment = "交易结束日期	")
	Date endTrxDate;//

	@Column(name = "IS_TAX")
	@ModelProperty(comment = "是否含税	")
	String isTax;//

	@Column(name = "START_DATE")
	@ModelProperty(comment = "有效日期	")
	Date startDate;//

	@Column(name = "END_DATE")
	@ModelProperty(comment = "失效日期	")
	Date endDate;//

	
	

	@Override
	public Serializable getId() {
		// TODO Auto-generated method stub
		return id;
	}




	public Date getInterfaceTrxDate() {
		return interfaceTrxDate;
	}




	public void setInterfaceTrxDate(Date interfaceTrxDate) {
		this.interfaceTrxDate = interfaceTrxDate;
	}




	public String getInterfaceTrxFlag() {
		return interfaceTrxFlag;
	}




	public void setInterfaceTrxFlag(String interfaceTrxFlag) {
		this.interfaceTrxFlag = interfaceTrxFlag;
	}




	public String getInterfaceTrxMsg() {
		return interfaceTrxMsg;
	}




	public void setInterfaceTrxMsg(String interfaceTrxMsg) {
		this.interfaceTrxMsg = interfaceTrxMsg;
	}




	public String getRequestId() {
		return requestId;
	}




	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}




	public String getCrvatInvoiceReqHId() {
		return crvatInvoiceReqHId;
	}




	public void setCrvatInvoiceReqHId(String crvatInvoiceReqHId) {
		this.crvatInvoiceReqHId = crvatInvoiceReqHId;
	}




	public String getCrvatInvoiceReqNumber() {
		return crvatInvoiceReqNumber;
	}




	public void setCrvatInvoiceReqNumber(String crvatInvoiceReqNumber) {
		this.crvatInvoiceReqNumber = crvatInvoiceReqNumber;
	}




	public String getOrgId() {
		return orgId;
	}




	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}




	public String getSourceOrgId() {
		return sourceOrgId;
	}




	public void setSourceOrgId(String sourceOrgId) {
		this.sourceOrgId = sourceOrgId;
	}




	public String getSourceOrgCode() {
		return sourceOrgCode;
	}




	public void setSourceOrgCode(String sourceOrgCode) {
		this.sourceOrgCode = sourceOrgCode;
	}




	public Date getSubmitDate() {
		return submitDate;
	}




	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
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




	public String getReqDeptNo() {
		return reqDeptNo;
	}




	public void setReqDeptNo(String reqDeptNo) {
		this.reqDeptNo = reqDeptNo;
	}




	public String getSourceReqDeptNo() {
		return sourceReqDeptNo;
	}




	public void setSourceReqDeptNo(String sourceReqDeptNo) {
		this.sourceReqDeptNo = sourceReqDeptNo;
	}




	public String getSourceInvoiceReqBy() {
		return sourceInvoiceReqBy;
	}




	public void setSourceInvoiceReqBy(String sourceInvoiceReqBy) {
		this.sourceInvoiceReqBy = sourceInvoiceReqBy;
	}




	public String getInvoiceCategory() {
		return invoiceCategory;
	}




	public void setInvoiceCategory(String invoiceCategory) {
		this.invoiceCategory = invoiceCategory;
	}




	public String getSourceCode() {
		return sourceCode;
	}




	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}




	public String getCustomerId() {
		return customerId;
	}




	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}




	public String getSourceCustomerId() {
		return sourceCustomerId;
	}




	public void setSourceCustomerId(String sourceCustomerId) {
		this.sourceCustomerId = sourceCustomerId;
	}




	public String getSourceCustomerNumber() {
		return sourceCustomerNumber;
	}




	public void setSourceCustomerNumber(String sourceCustomerNumber) {
		this.sourceCustomerNumber = sourceCustomerNumber;
	}




	public String getSourceCustomerAccNum() {
		return sourceCustomerAccNum;
	}




	public void setSourceCustomerAccNum(String sourceCustomerAccNum) {
		this.sourceCustomerAccNum = sourceCustomerAccNum;
	}




	public String getLegalEntityId() {
		return legalEntityId;
	}




	public void setLegalEntityId(String legalEntityId) {
		this.legalEntityId = legalEntityId;
	}




	public String getSourceLegalEntityId() {
		return sourceLegalEntityId;
	}




	public void setSourceLegalEntityId(String sourceLegalEntityId) {
		this.sourceLegalEntityId = sourceLegalEntityId;
	}




	public String getSourceLegalEntityCode() {
		return sourceLegalEntityCode;
	}




	public void setSourceLegalEntityCode(String sourceLegalEntityCode) {
		this.sourceLegalEntityCode = sourceLegalEntityCode;
	}




	public String getInvoicingType() {
		return invoicingType;
	}




	public void setInvoicingType(String invoicingType) {
		this.invoicingType = invoicingType;
	}




	public String getReqInvoiceRange() {
		return reqInvoiceRange;
	}




	public void setReqInvoiceRange(String reqInvoiceRange) {
		this.reqInvoiceRange = reqInvoiceRange;
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




	public String getIsReceipts() {
		return isReceipts;
	}




	public void setIsReceipts(String isReceipts) {
		this.isReceipts = isReceipts;
	}




	public String getBusinessCategories() {
		return businessCategories;
	}




	public void setBusinessCategories(String businessCategories) {
		this.businessCategories = businessCategories;
	}




	public String getDeliveryType() {
		return deliveryType;
	}




	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}




	public String getWfTaskId() {
		return wfTaskId;
	}




	public void setWfTaskId(String wfTaskId) {
		this.wfTaskId = wfTaskId;
	}




	public String getSourceApprovalBy() {
		return sourceApprovalBy;
	}




	public void setSourceApprovalBy(String sourceApprovalBy) {
		this.sourceApprovalBy = sourceApprovalBy;
	}




	public Date getApprovalDate() {
		return approvalDate;
	}




	public void setApprovalDate(Date approvalDate) {
		this.approvalDate = approvalDate;
	}




	public String getApprovalDesc() {
		return approvalDesc;
	}




	public void setApprovalDesc(String approvalDesc) {
		this.approvalDesc = approvalDesc;
	}




	public String getApprovalStatus() {
		return approvalStatus;
	}




	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}




	public String getProjectId() {
		return projectId;
	}




	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}




	public String getSourceProjectNumber() {
		return sourceProjectNumber;
	}




	public void setSourceProjectNumber(String sourceProjectNumber) {
		this.sourceProjectNumber = sourceProjectNumber;
	}




	public String getSourceContractNumber() {
		return sourceContractNumber;
	}




	public void setSourceContractNumber(String sourceContractNumber) {
		this.sourceContractNumber = sourceContractNumber;
	}




	public Long getQtyOfPreInvoice() {
		return qtyOfPreInvoice;
	}




	public void setQtyOfPreInvoice(Long qtyOfPreInvoice) {
		this.qtyOfPreInvoice = qtyOfPreInvoice;
	}




	public Date getStartTrxDate() {
		return startTrxDate;
	}




	public void setStartTrxDate(Date startTrxDate) {
		this.startTrxDate = startTrxDate;
	}




	public Date getEndTrxDate() {
		return endTrxDate;
	}




	public void setEndTrxDate(Date endTrxDate) {
		this.endTrxDate = endTrxDate;
	}




	public String getIsTax() {
		return isTax;
	}




	public void setIsTax(String isTax) {
		this.isTax = isTax;
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




	public void setId(String id) {
		this.id = id;
	}

}
