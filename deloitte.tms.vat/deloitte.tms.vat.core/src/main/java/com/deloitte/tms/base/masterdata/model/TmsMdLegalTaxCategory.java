package com.deloitte.tms.base.masterdata.model;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.deloitte.tms.pl.core.commons.annotation.ModelProperty;
import com.deloitte.tms.pl.core.hibernate.identifier.Ling2IdGenerator;
import com.deloitte.tms.pl.core.model.impl.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TMS_MD_LEGAL_TAX_CATEGORY database table.
 * 
 */
@Entity
@Table(name = TmsMdLegalTaxCategory.TABLE)
@ModelProperty(comment = "纳税人主体汇缴关系关联表")
public class TmsMdLegalTaxCategory extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String TABLE = "TMS_MD_LEGAL_TAX_CATEGORY";
	public static final String SEQ = TABLE;
	
	@Id
	@GenericGenerator(name=SEQ+"_GENERATOR",strategy=Ling2IdGenerator.STRATEGY_NAME,parameters={@Parameter(name="pkColumnValue",value=SEQ)})
	@GeneratedValue(strategy = GenerationType.TABLE, generator = SEQ+"_GENERATOR")
	@Column(name = "LEGAL_TAX_CATEGORY_ID",  length = 60)
	String id;

	@Column(name="ENABLED_FLAG")
	private String enabledFlag;

	@Column(name="END_DATE")
	private Date endDate;

	@Column(name="LEGAL_ENTITY_ID")
	private String legalEntityId;

	@Column(name="PARENT_ID")
	private String parentId;

	@Column(name="START_DATE")
	private Date startDate;

	@Column(name="TAX_CATEGORY_ID")
	private String taxCategoryId;

	@Column(name="VERSION_NO")
	private int versionNo;

	public TmsMdLegalTaxCategory() {
	}

	public String getId() {
		return id;
	}

	public String getEnabledFlag() {
		return enabledFlag;
	}

	public Date getEndDate() {
		return endDate;
	}

	public String getLegalEntityId() {
		return legalEntityId;
	}

	public String getParentId() {
		return parentId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public String getTaxCategoryId() {
		return taxCategoryId;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setEnabledFlag(String enabledFlag) {
		this.enabledFlag = enabledFlag;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void setLegalEntityId(String legalEntityId) {
		this.legalEntityId = legalEntityId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setTaxCategoryId(String taxCategoryId) {
		this.taxCategoryId = taxCategoryId;
	}

	public int getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(int versionNo) {
		this.versionNo = versionNo;
	}

}