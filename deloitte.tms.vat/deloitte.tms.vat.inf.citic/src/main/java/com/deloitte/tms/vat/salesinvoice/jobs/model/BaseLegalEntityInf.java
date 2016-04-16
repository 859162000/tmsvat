package com.deloitte.tms.vat.salesinvoice.jobs.model;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.deloitte.tms.pl.core.commons.annotation.ModelProperty;
import com.deloitte.tms.pl.core.hibernate.identifier.Ling2IdGenerator;
import com.deloitte.tms.pl.core.hibernate.identifier.Ling2UUIDGenerator;
import com.deloitte.tms.pl.core.model.impl.BaseEntity;

import java.util.Date;


/**
 * The persistent class for the BASE_LEGAL_ENTITY_INF database table.
 * 
 */
@Entity
@Table(name=BaseLegalEntityInf.TABLE)
@ModelProperty(comment = "法人实体接口表")
public class BaseLegalEntityInf extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String TABLE = "BASE_LEGAL_ENTITY_INF";
	public static final String SEQ = TABLE;
	
	@Id
	@GenericGenerator(name=SEQ+"_GENERATOR",strategy=Ling2UUIDGenerator.STRATEGY_NAME,parameters={@Parameter(name="pkColumnValue",value=SEQ)})
	@GeneratedValue(strategy = GenerationType.TABLE, generator = SEQ+"_GENERATOR")
	@Column(name = "INTERFACE_TRX_ID",  length = 36)
	String id;
	
	@Column(name="DESCRIPTION")
	private String description;

	@Column(name="END_DATE")
	private Date endDate;

	@Column(name="INTERFACE_TRX_DATE")
	private Date interfaceTrxDate;

	@Column(name="INTERFACE_TRX_FLAG")
	private String interfaceTrxFlag;

	@Column(name="INTERFACE_TRX_MSG")
	private String interfaceTrxMsg;

	@Column(name="LEGAL_ENTITY_ID")
	private String legalEntityId;

	@Column(name="LEGAL_ENTITY_LEVEL")
	private String legalEntityLevel;

	@Column(name="LEGAL_ENTITY_NAME")
	private String legalEntityName;

	@Column(name="LEGAL_ENTITY_TYPE")
	private String legalEntityType;

	@Column(name="PARENT_ID")
	private String parentId;

	@Column(name="REFERENCE1")
	private String reference1;

	@Column(name="SOURCE_CREATED_BY")
	private String sourceCreatedBy;

	@Column(name="SOURCE_CREATION_DATE")
	private Date sourceCreationDate;

	@Column(name="SOURCE_DATE")
	private Date sourceDate;

	@Column(name="SOURCE_LAST_UPDATE_DATE")
	private Date sourceLastUpdateDate;

	@Column(name="SOURCE_LAST_UPDATED_BY")
	private String sourceLastUpdatedBy;

	@Column(name="START_DATE")
	private Date startDate;

	public BaseLegalEntityInf() {
		
	}
	
	public String getId() {
		return id;
	}
	
	public String getDescription() {
		return description;
	}

	public Date getEndDate() {
		return endDate;
	}

	public String getInterfaceTrxFlag() {
		return interfaceTrxFlag;
	}

	public String getInterfaceTrxMsg() {
		return interfaceTrxMsg;
	}

	public String getLegalEntityId() {
		return legalEntityId;
	}

	public String getLegalEntityLevel() {
		return legalEntityLevel;
	}

	public String getLegalEntityName() {
		return legalEntityName;
	}

	public String getLegalEntityType() {
		return legalEntityType;
	}

	public String getParentId() {
		return parentId;
	}

	public String getReference1() {
		return reference1;
	}

	public String getSourceCreatedBy() {
		return sourceCreatedBy;
	}

	public Date getSourceCreationDate() {
		return sourceCreationDate;
	}

	public Date getSourceDate() {
		return sourceDate;
	}

	public Date getSourceLastUpdateDate() {
		return sourceLastUpdateDate;
	}

	public String getSourceLastUpdatedBy() {
		return sourceLastUpdatedBy;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getInterfaceTrxDate() {
		return interfaceTrxDate;
	}

	public void setInterfaceTrxDate(Date interfaceTrxDate) {
		this.interfaceTrxDate = interfaceTrxDate;
	}

	public void setInterfaceTrxFlag(String interfaceTrxFlag) {
		this.interfaceTrxFlag = interfaceTrxFlag;
	}

	public void setInterfaceTrxMsg(String interfaceTrxMsg) {
		this.interfaceTrxMsg = interfaceTrxMsg;
	}

	public void setLegalEntityId(String legalEntityId) {
		this.legalEntityId = legalEntityId;
	}

	public void setLegalEntityLevel(String legalEntityLevel) {
		this.legalEntityLevel = legalEntityLevel;
	}

	public void setLegalEntityName(String legalEntityName) {
		this.legalEntityName = legalEntityName;
	}

	public void setLegalEntityType(String legalEntityType) {
		this.legalEntityType = legalEntityType;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public void setReference1(String reference1) {
		this.reference1 = reference1;
	}

	public void setSourceCreatedBy(String sourceCreatedBy) {
		this.sourceCreatedBy = sourceCreatedBy;
	}

	public void setSourceCreationDate(Date sourceCreationDate) {
		this.sourceCreationDate = sourceCreationDate;
	}

	public void setSourceDate(Date sourceDate) {
		this.sourceDate = sourceDate;
	}

	public void setSourceLastUpdateDate(Date sourceLastUpdateDate) {
		this.sourceLastUpdateDate = sourceLastUpdateDate;
	}

	public void setSourceLastUpdatedBy(String sourceLastUpdatedBy) {
		this.sourceLastUpdatedBy = sourceLastUpdatedBy;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

}