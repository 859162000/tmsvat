package com.deloitte.tms.base.masterdata.model;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.deloitte.tms.pl.core.commons.annotation.ModelProperty;
import com.deloitte.tms.pl.core.commons.constant.TablePreDef;
import com.deloitte.tms.pl.core.hibernate.identifier.Ling2UUIDGenerator;
import com.deloitte.tms.pl.core.model.impl.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TMS_MD_LEGAL_INVOICE database table.
 * 
 */
@Entity
@Table(name=TmsMdLegalInvoice.TABLE)
@ModelProperty(comment = "纳税主体与发票种类限额关系表")
public class TmsMdLegalInvoice extends BaseEntity{
	private static final long serialVersionUID = 1L;
	public static final String TABLE = TablePreDef.TMS_MD_LEGAL+"INVOICE";
	public static final String SEQ = TABLE;

	@Id
	@Column(name="LEGAL_INVOICE_ID")
	@GenericGenerator(name=SEQ+"_GENERATOR",strategy=Ling2UUIDGenerator.STRATEGY_NAME,parameters={@Parameter(name="pkColumnValue",value=SEQ)})
	@GeneratedValue(strategy = GenerationType.TABLE, generator = SEQ+"_GENERATOR")
	private String id;
	
	/*@Column(name="ATTRIBUTE_CATEGORY")
	private String attributeCategory;*/
	
	
	@Column(name="ENABLED_FLAG")
	private String enabledFlag;

	@Temporal(TemporalType.DATE)
	@Column(name="END_DATE")
	private Date endDate;

	@Column(name="INVOICE_CATEGORY")
	private String invoiceCategory;

	@Column(name="INVOICE_LIMIT_AMOUNT")
	private String invoiceLimitAmount;

	@Column(name="INVOICE_LIMIT_AMOUNT_VALUE")
	private Long invoiceLimitAmountValue;

	

	@Column(name="LEGAL_ENTITY_ID")
	private String legalEntityId;
	@ManyToOne
	@Cascade(CascadeType.REFRESH)
	@JoinColumn(name="LEGAL_ENTITY_ID",insertable=false,updatable=false,nullable=true)
	TmsMdLegalEntity tmsMdLegalEntity;
	
	@Temporal(TemporalType.DATE)
	@Column(name="START_DATE")
	private Date startDate;

	public TmsMdLegalInvoice() {
	}

	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}

	/*public String getAttributeCategory() {
		return this.attributeCategory;
	}

	public void setAttributeCategory(String attributeCategory) {
		this.attributeCategory = attributeCategory;
	}*/

	public String getEnabledFlag() {
		return this.enabledFlag;
	}

	public void setEnabledFlag(String enabledFlag) {
		this.enabledFlag = enabledFlag;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getInvoiceCategory() {
		return this.invoiceCategory;
	}

	public void setInvoiceCategory(String invoiceCategory) {
		this.invoiceCategory = invoiceCategory;
	}

	public String getInvoiceLimitAmount() {
		return this.invoiceLimitAmount;
	}

	public void setInvoiceLimitAmount(String invoiceLimitAmount) {
		this.invoiceLimitAmount = invoiceLimitAmount;
	}

	public Long getInvoiceLimitAmountValue() {
		return this.invoiceLimitAmountValue;
	}

	public void setInvoiceLimitAmountValue(Long invoiceLimitAmountValue) {
		this.invoiceLimitAmountValue = invoiceLimitAmountValue;
	}

	public String getLegalEntityId() {
		return this.legalEntityId;
	}

	public void setLegalEntityId(String legalEntityId) {
		this.legalEntityId = legalEntityId;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public TmsMdLegalEntity getTmsMdLegalEntity() {
		return tmsMdLegalEntity;
	}

	public void setTmsMdLegalEntity(TmsMdLegalEntity tmsMdLegalEntity) {
		this.tmsMdLegalEntity = tmsMdLegalEntity;
	}
	
}