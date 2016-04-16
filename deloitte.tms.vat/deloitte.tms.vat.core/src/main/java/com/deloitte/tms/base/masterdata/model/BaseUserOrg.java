package com.deloitte.tms.base.masterdata.model;

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
import com.deloitte.tms.pl.core.hibernate.identifier.Ling2IdGenerator;
import com.deloitte.tms.pl.core.hibernate.identifier.Ling2UUIDGenerator;
import com.deloitte.tms.pl.core.model.impl.BaseEntity;

@Entity
@Table(name = BaseUserOrg.TABLE)
@ModelProperty(comment = "组织用户关联表")
public class BaseUserOrg extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String TABLE = "BASE_USER_ORG";
	public static final String SEQ = TABLE;

	@Id
	@GenericGenerator(name=SEQ+"_GENERATOR",strategy=Ling2UUIDGenerator.STRATEGY_NAME,parameters={@Parameter(name="pkColumnValue",value=SEQ)})
	@GeneratedValue(strategy = GenerationType.TABLE, generator = SEQ+"_GENERATOR")
	@Column(name = "USER_ORG_ID",  length = 60)
	String id;

	@Column(name="BIZ_ORG_ID")
	private String bizOrgId;

	@Column(name="DATA_ORG_ID")
	private String dataOrgId;

	@Column(name="DATA_OWENER_CODE")
	private String dataOwenerCode;

	@Column(name="END_DATE")
	private Date endDate;

	@Column(name="EST_DATE")
	private Date estDate;

	@Column(name="ORG_ID")
	private String orgId;

	@Column(name="SORT_ORDER")
	private Double sortOrder;

	@Column(name="START_DATE")
	private Date startDate;

	@Column(name="USERNAME")
	private String username;

	public BaseUserOrg() {
	}

	public String getId() {
		return id;
	}

	public String getBizOrgId() {
		return bizOrgId;
	}

	public String getDataOrgId() {
		return dataOrgId;
	}

	public String getDataOwenerCode() {
		return dataOwenerCode;
	}

	public Date getEndDate() {
		return endDate;
	}

	public Date getEstDate() {
		return estDate;
	}

	public String getOrgId() {
		return orgId;
	}

	public Double getSortOrder() {
		return sortOrder;
	}

	public Date getStartDate() {
		return startDate;
	}

	public String getUsername() {
		return username;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setBizOrgId(String bizOrgId) {
		this.bizOrgId = bizOrgId;
	}

	public void setDataOrgId(String dataOrgId) {
		this.dataOrgId = dataOrgId;
	}

	public void setDataOwenerCode(String dataOwenerCode) {
		this.dataOwenerCode = dataOwenerCode;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void setEstDate(Date estDate) {
		this.estDate = estDate;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public void setSortOrder(Double sortOrder) {
		this.sortOrder = sortOrder;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}
