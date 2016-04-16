package com.deloitte.tms.base.masterdata.model;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Where;

import com.deloitte.tms.pl.core.commons.annotation.ModelProperty;
import com.deloitte.tms.pl.core.commons.constant.TablePreDef;
import com.deloitte.tms.pl.core.hibernate.identifier.Ling2UUIDGenerator;
import com.deloitte.tms.pl.core.model.impl.BaseEntity;
import com.deloitte.tms.pl.security.utils.LittleUtils;
import com.deloitte.tms.vat.salesinvoice.model.TmsCrvatInvReqBatchesH;
import com.deloitte.tms.vat.salesinvoice.model.TmsCrvatInvReqBatchesL;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TMS_MD_ORG_LEGAL_ENTITY database table.
 * 
 */
@Entity
@Table(name=TmsMdOrgLegalEntity.TABLE)
@ModelProperty(comment = "组织与纳税主体关联表")
//@NamedQuery(name="TmsMdOrgLegalEntity.findAll", query="SELECT t FROM TmsMdOrgLegalEntity t")
public class TmsMdOrgLegalEntity extends BaseEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	public static final String TABLE = TablePreDef.TMS_MD_TAG+"ORG_LEGAL_ENTITY";
	public static final String SEQ = TABLE;

	@Id
	@Column(name="LEGAL_ORG_RELATION_ID")
	/*@GenericGenerator(name=SEQ+"_GENERATOR",strategy=Ling2UUIDGenerator.STRATEGY_NAME,parameters={@Parameter(name="pkColumnValue",value=SEQ)})
	@GeneratedValue(strategy = GenerationType.TABLE, generator = SEQ+"_GENERATOR")*/
	private String id;
	
	@Column(name="ENABLED_FLAG")
	private String enabledFlag;

	@Column(name="END_DATE")
	private Date endDate;

	@Column(name="LEGAL_ENTITY_ID")
	private String legalEntityId;

	@Column(name="LEGAL_ENTITY_TYPE")
	@ModelProperty(comment = "级别")
	private String legalEntityType;//和tms_md_legal_entity里的legalEntityType完全不一样，没关系

	@Column(name="ORG_ID")
	private String orgId;

	@Column(name="START_DATE")
	private Date startDate;


	
	public String getEnabledFlag() {
		return enabledFlag;
	}


	public Date getEndDate() {
		return endDate;
	}


	public String getLegalEntityId() {
		return legalEntityId;
	}


	public String getLegalEntityType() {
		return legalEntityType;
	}


	public String getOrgId() {
		return orgId;
	}


	public Date getStartDate() {
		return startDate;
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


	public void setLegalEntityType(String legalEntityType) {
		this.legalEntityType = legalEntityType;
	}


	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}


	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}


	public String getId() {
		return id;
	}



	
}