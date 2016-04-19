
/**    
 * Copyright (C),Deloitte
 * @FileName: InvoiceReqH.java  
 * @Package: com.deloitte.tms.base.invoiceout.print.model  
 * @Description: //模块目的、功能描述  
 * @Author sqing  
 * @Date 2016年3月16日 下午8:22:34  
 * @History: //修改记录
 * 〈author〉      〈time〉      〈version〉       〈desc〉
 * 修改人姓名            修改时间            版本号              描述   
*/  

package com.deloitte.tms.vat.salesinvoice.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.deloitte.tms.base.masterdata.model.Customer;
import com.deloitte.tms.pl.core.commons.annotation.ModelProperty;
import com.deloitte.tms.pl.core.commons.constant.TablePreDef;
import com.deloitte.tms.pl.core.model.impl.BaseEntity;


/**  
 *销项税开票申请单-头表
 * 功能详细描述
 * @author sqing
 * @create 2016年3月16日 下午8:22:34 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Entity
@Table(name = InvoiceReqH.TABLE)
@ModelProperty(comment = "申请单头信息")
public class InvoiceReqH extends BaseEntity{
	public static final String TABLE = TablePreDef.TMS_CRVAT_INVOICE_REQ+"H";
	public static final String SEQ = TABLE;
	private static final long serialVersionUID = 1L;

	@Id
	/*@GenericGenerator(name=SEQ+"_GENERATOR",strategy=Ling2UUIDGenerator.STRATEGY_NAME,parameters={@Parameter(name="pkColumnValue",value=SEQ)})
	@GeneratedValue(strategy = GenerationType.TABLE, generator = SEQ+"_GENERATOR")*/
	@Column(name="CRVAT_INVOICE_REQ_H_ID")
	private String id;

	@Column(name="APPROVAL_DESC")
	private String approvalDesc;

	@Column(name="REQ_DEPT_NO")
	private String reqDeptNo;

	@Column(name="APPROVAL_STATUS")
	private String approvalStatus;

	@Column(name="CRVAT_INVOICE_REQ_NUMBER")
	private String crvatInvoiceReqNumber;

	@Column(name="CUST_REGISTRATION_CODE")
	@ModelProperty(comment="购方证件类型_枚举值")
	private String custRegistrationCode;

	@Column(name="CUST_REGISTRATION_NUMBER")
	@ModelProperty(comment="购方证件号码")
	private String custRegistrationNumber;

	@Column(name="CUSTOMER_ID")
	@ModelProperty(comment="购方证件ID")
	String customerId;
	@ManyToOne(fetch=FetchType.EAGER)
	@Cascade(CascadeType.REFRESH)
	@JoinColumn(name="CUSTOMER_ID",insertable=false,updatable=false,nullable=true)
	Customer customer;

	@Column(name="CUSTOMER_NAME")
	private String customerName;

	@Temporal(TemporalType.DATE)
	@Column(name="END_DATE")
	private Date endDate;

	@Temporal(TemporalType.DATE)
	@Column(name="INVOICE_REQ_DATE")
	private Date invoiceReqDate;

	@Column(name="INVOICE_REQ_TYPE")
	private String invoiceReqType;

	@Column(name="INVOICING_TYPE")
	private String invoicingType;

	@Column(name="IS_EXITS_CUSTOMER")
	private String isExitsCustomer;

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

	@Temporal(TemporalType.DATE)
	@Column(name="SUBMIT_DATE")
	private Date submitDate;

	@Column(name="WF_TASK_ID")
	private String wfTaskId;
	
	@Column(name="MAPPING_STATUS")
	private String mappingStatus;
	
	@Column(name="IS_ALL_MAPPING")
	private String isAllMapping;
	
	@Column(name="SOURCE_CODE")
	@ModelProperty(comment="来源(手工/自动匹配)")
	private String sourceCode;
	
	@Column(name="PROJECT_ID")
	private String projectId;
	
	/*@Column(name="IS_REQ_ALL_TRX")
	private String isReqAllTrx;*/
	
	
	@Column(name="INVOICE_CATEGORY")
	private String invoiceCategory;
	
	@Column(name="QTY_OF_PRE_INVOICE")
	@ModelProperty(comment = "生成的准备单数量")
	private Integer qtyOfPreInvoice;

	@Column(name="ORG_ID")
	private String orgId;
	
	@Column(name="IS_RECEIPTS")
	@ModelProperty(comment = "是否已收款")
	private String isReceipts;
	
	@Column(name="REQ_INVOICE_RANGE")
	private String reqInvoiceRange;
	
	@Column(name="DELIVERY_TYPE")
	@ModelProperty(comment = "寄送类型_枚举值")
	private String deliveryType;
	
	@Column(name="IS_TAX")
	@ModelProperty(comment = "是否含税")
	private String isTax;
	
	@Column(name="BUSINESS_CATEGORIES")
	@ModelProperty(comment = "业务大类_枚举值")
	private String businessCategories;
	
	
	
	/*@OneToMany(mappedBy = "invoiceReqH",fetch=FetchType.LAZY)
	@Cascade(CascadeType.REFRESH)
	@Where(clause="DELETED_FLAG=1")*/
	@Transient
	Collection<InvoiceReqL> invoiceReqLs = new ArrayList<InvoiceReqL>();
	@Transient
	Collection<TmsCrvatInvoiceReqP> invoiceReqPs = new ArrayList<TmsCrvatInvoiceReqP>();
	
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



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getApprovalDesc() {
		return this.approvalDesc;
	}

	public void setApprovalDesc(String approvalDesc) {
		this.approvalDesc = approvalDesc;
	}

	public String getApprovalStatus() {
		return this.approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	

	

	public String getCrvatInvoiceReqNumber() {
		return this.crvatInvoiceReqNumber;
	}

	public void setCrvatInvoiceReqNumber(String crvatInvoiceReqNumber) {
		this.crvatInvoiceReqNumber = crvatInvoiceReqNumber;
	}

	public String getCustRegistrationCode() {
		return this.custRegistrationCode;
	}

	public void setCustRegistrationCode(String custRegistrationCode) {
		this.custRegistrationCode = custRegistrationCode;
	}

	public String getCustRegistrationNumber() {
		return this.custRegistrationNumber;
	}

	public void setCustRegistrationNumber(String custRegistrationNumber) {
		this.custRegistrationNumber = custRegistrationNumber;
	}

	public String getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getInvoiceReqDate() {
		return this.invoiceReqDate;
	}

	public void setInvoiceReqDate(Date invoiceReqDate) {
		this.invoiceReqDate = invoiceReqDate;
	}

	public String getInvoiceReqType() {
		return this.invoiceReqType;
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
		return this.isExitsCustomer;
	}

	public void setIsExitsCustomer(String isExitsCustomer) {
		this.isExitsCustomer = isExitsCustomer;
	}

	

	public String getLegalEntityCode() {
		return this.legalEntityCode;
	}

	public void setLegalEntityCode(String legalEntityCode) {
		this.legalEntityCode = legalEntityCode;
	}

	public String getLegalEntityId() {
		return this.legalEntityId;
	}

	public void setLegalEntityId(String legalEntityId) {
		this.legalEntityId = legalEntityId;
	}

	public String getLegalEntityName() {
		return this.legalEntityName;
	}

	public void setLegalEntityName(String legalEntityName) {
		this.legalEntityName = legalEntityName;
	}

	public String getRegistrationCode() {
		return this.registrationCode;
	}

	public void setRegistrationCode(String registrationCode) {
		this.registrationCode = registrationCode;
	}

	public String getRegistrationNumber() {
		return this.registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	
	public Date getSubmitDate() {
		return this.submitDate;
	}

	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
	}

	public String getWfTaskId() {
		return this.wfTaskId;
	}

	public void setWfTaskId(String wfTaskId) {
		this.wfTaskId = wfTaskId;
	}

	public Collection<InvoiceReqL> getInvoiceReqLs() {
		return invoiceReqLs;
	}

	public void setInvoiceReqLs(Collection<InvoiceReqL> invoiceReqLs) {
		this.invoiceReqLs = invoiceReqLs;
	}


	public String getReqDeptNo() {
		return reqDeptNo;
	}


	public void setReqDeptNo(String reqDeptNo) {
		this.reqDeptNo = reqDeptNo;
	}


	public String getSourceCode() {
		return sourceCode;
	}


	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
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


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
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



	public String getReqInvoiceRange() {
		return reqInvoiceRange;
	}



	public void setReqInvoiceRange(String reqInvoiceRange) {
		this.reqInvoiceRange = reqInvoiceRange;
	}



	public Collection<TmsCrvatInvoiceReqP> getInvoiceReqPs() {
		return invoiceReqPs;
	}



	public void setInvoiceReqPs(Collection<TmsCrvatInvoiceReqP> invoiceReqPs) {
		this.invoiceReqPs = invoiceReqPs;
	}



	public String getIsReceipts() {
		return isReceipts;
	}



	public void setIsReceipts(String isReceipts) {
		this.isReceipts = isReceipts;
	}



	public String getIsTax() {
		return isTax;
	}



	public void setIsTax(String isTax) {
		this.isTax = isTax;
	}
}
