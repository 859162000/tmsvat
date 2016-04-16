
/**    
 * Copyright (C),Deloitte
 * @FileName: InvoiceTrxH.java  
 * @Package: com.deloitte.tms.vat.salesinvoice.model  
 * @Description: //模块目的、功能描述  
 * @Author stonshi  
 * @Date 2016年3月18日 下午8:46:22  
 * @History: //修改记录
 * 〈author〉      〈time〉      〈version〉       〈desc〉
 * 修改人姓名            修改时间            版本号              描述   
*/  

package com.deloitte.tms.vat.salesinvoice.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Where;

import com.deloitte.tms.base.masterdata.model.TmsMdLegalEntity;
import com.deloitte.tms.pl.core.commons.annotation.ModelProperty;
import com.deloitte.tms.pl.core.commons.constant.TablePreDef;
import com.deloitte.tms.pl.core.hibernate.identifier.Ling2IdGenerator;
import com.deloitte.tms.pl.core.model.impl.BaseEntity;



/**  
 *〈一句话功能简述〉
 * 功能详细描述
 * @author stonshi
 * @create 2016年3月18日 下午8:46:22 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Entity
@Table(name = InvoiceTrxH.TABLE)
@ModelProperty(comment = "销项税发票请领入库单头")
public class InvoiceTrxH extends BaseEntity {

	public static final String TABLE = TablePreDef.TMS_CRVAT_INVOICE+"TRX_H";
	public static final String SEQ = TABLE;
	
	@Id
	/*@GenericGenerator(name=SEQ+"_GENERATOR",strategy=Ling2IdGenerator.STRATEGY_NAME,parameters={@Parameter(name="pkColumnValue",value=SEQ)})
	@GeneratedValue(strategy = GenerationType.TABLE, generator = SEQ+"_GENERATOR")*/
	@Column(name = "CRVAT_INVOICE_TRX_H_ID",  length = 36)
	String id;

	@Column(name="APPROVAL_BY")
	private String approvalBy;

	@Column(name="APPROVAL_DESC")
	private String approvalDesc;

	@Column(name="APPROVAL_ORG_ID")
	private String approvalOrgId;

	@Column(name="APPROVAL_STATUS")
	private String approvalStatus;

	@Column(name="CRVAT_INVOICE_TRX_NUMBER")
	private String crvatInvoiceTrxNumber;

	@Column(name="DEPARTMENT_ID")
	private String departmentId;

	@Column(name="DESCRIPTION")
	private String description;

	@Temporal(TemporalType.DATE)
	@Column(name="INVOICE_TRX_DATE")
	private Date invoiceTrxDate;

	@Column(name="LEGAL_ENTITY_ID")
	private String legalEntityId;
	@ManyToOne
	@Cascade(CascadeType.REFRESH)
	@JoinColumn(name="LEGAL_ENTITY_ID",insertable=false,updatable=false,nullable=true)

	TmsMdLegalEntity tmsMdLegalEntity; 
	
	@Column(name="WF_TASK_ID")
	private String wfTaskId;
	
	@Column(name="REQUESTION_BY")
	private String requestionBy;
	
	@Column(name="INVOICE_CATEGORY")
	private String invoiceCategory;
	
	@Column(name="APPROVAL_DATE")
	private Date approvalDate;
	
	@OneToMany(mappedBy = "invoiceTrxH", fetch = FetchType.LAZY)
	@Cascade(CascadeType.REFRESH)
	@Where(clause="DELETED_FLAG=1")
	private List<InvoiceTrxL> InvoiceTrxLs;
	
	
	@Transient
	List<InvoiceTrxL> list = new ArrayList<InvoiceTrxL>();

	public Date getApprovalDate() {
		return approvalDate;
	}

	public void setApprovalDate(Date approvalDate) {
		this.approvalDate = approvalDate;
	}

	public String getInvoiceCategory() {
		return invoiceCategory;
	}

	public void setInvoiceCategory(String invoiceCategory) {
		this.invoiceCategory = invoiceCategory;
	}

	public String getRequestionBy() {
		return requestionBy;
	}

	public void setRequestionBy(String requestionBy) {
		this.requestionBy = requestionBy;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getApprovalBy() {
		return this.approvalBy;
	}

	public void setApprovalBy(String approvalBy) {
		this.approvalBy = approvalBy;
	}

	public String getApprovalDesc() {
		return this.approvalDesc;
	}

	public void setApprovalDesc(String approvalDesc) {
		this.approvalDesc = approvalDesc;
	}

	public String getApprovalOrgId() {
		return this.approvalOrgId;
	}

	public void setApprovalOrgId(String approvalOrgId) {
		this.approvalOrgId = approvalOrgId;
	}

	public String getApprovalStatus() {
		return this.approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public String getCrvatInvoiceTrxNumber() {
		return this.crvatInvoiceTrxNumber;
	}

	public void setCrvatInvoiceTrxNumber(String crvatInvoiceTrxNumber) {
		this.crvatInvoiceTrxNumber = crvatInvoiceTrxNumber;
	}

	public String getDepartmentId() {
		return this.departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getInvoiceTrxDate() {
		return this.invoiceTrxDate;
	}

	public void setInvoiceTrxDate(Date invoiceTrxDate) {
		this.invoiceTrxDate = invoiceTrxDate;
	}

	public String getLegalEntityId() {
		return this.legalEntityId;
	}

	public void setLegalEntityId(String legalEntityId) {
		this.legalEntityId = legalEntityId;
	}

	public String getWfTaskId() {
		return this.wfTaskId;
	}

	public void setWfTaskId(String wfTaskId) {
		this.wfTaskId = wfTaskId;
	}

	public List<InvoiceTrxL> getList() {
		return list;
	}

	public void setList(List<InvoiceTrxL> list) {
		this.list = list;
	}

	public List<InvoiceTrxL> getInvoiceTrxLs() {
		return InvoiceTrxLs;
	}

	public void setInvoiceTrxLs(List<InvoiceTrxL> invoiceTrxLs) {
		InvoiceTrxLs = invoiceTrxLs;
	}
	
	
	
	

}
