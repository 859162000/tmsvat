package com.deloitte.tms.pl.core.model.impl;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.deloitte.tms.pl.core.commons.annotation.ModelProperty;
import com.deloitte.tms.pl.core.model.IModelBase;

/**
 * 公共字段基础类
 * 
 * @author bo.wang
 * @create 2016年3月15日 下午2:02:51 
 * @version 2.0.0
 * 
 *  name         date         desc.
 *  stone.shi   2016-03-18   biz_org_id --> biz_org_code  
 *                           remove company_id
 */
@MappedSuperclass
public abstract class BaseEntity extends ExtBaseEntity implements IModelBase,java.io.Serializable{

	@Column(name = "CREATED_BY",  length = 50)
	@ModelProperty(comment="创建人")
	String createdBy;
	
	@Column(name = "CREATION_DATE")
	@ModelProperty(comment="创建日期/时间")
	Date createDate;
	
	@Column(name = "LAST_UPDATED_BY",  length = 50)
	@ModelProperty(comment="修改人")
	String modifiedBy;
	
	@Column(name = "LAST_UPDATE_DATE")
	@ModelProperty(comment="修改日期")
	Date modifiedDate;
	
	@Column(name = "status",  length = 10)
	@ModelProperty(comment="状态")
	String status;	
	
	@Column(name = "BIZ_ORG_CODE",  length = 36)
	@ModelProperty(comment="业务所属机构")
	String bizOrgCode;
	
	@Column(name = "OPERATION_ORG_CODE",  length = 36)
	@ModelProperty(comment="数据产生机构,")
	String operationOrgCode;
	
	@Column(name = "DATA_OWNER_CODE",  length = 36)
	@ModelProperty(comment="数据分区键")
	String dataowner;
	
	@Column(name="COMPANY_ID",length=36)
	@ModelProperty(comment="数据归属")
	String companyId;

	@Column(name="ARCHIVE_BASE_DATE")
	@ModelProperty(comment="归档基准日期")
	Date archiveBaseDate;
	
	@Column(name="RECORD_VERSION")
	@ModelProperty(comment="记录版本号，每次修改记录时加1")
	Integer versionId;
	
	@Column(name = "DELETED_FLAG",  length = 10)
	@ModelProperty(comment="删除标记",des="1==true 有效;0==false 无效")
	String flag;
	
	@Column(name="DELETED_BY",length=36)
	@ModelProperty(comment="删除人")
	String deleteBy;
	
	@Column(name="DELETION_DATE",length=36)
	@ModelProperty(comment="删除时间")
	Date deleteDate;	

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDataowner() {
		return dataowner;
	}

	public void setDataowner(String dataowner) {
		this.dataowner = dataowner;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getBizOrgCode() {
		return bizOrgCode;
	}

	public void setBizOrgCode(String bizOrgCode) {
		this.bizOrgCode = bizOrgCode;
	}

	public Date getArchiveBaseDate() {
		return archiveBaseDate;
	}

	public void setArchiveBaseDate(Date archiveBaseDate) {
		this.archiveBaseDate = archiveBaseDate;
	}

	public Integer getVersionId() {
		return versionId;
	}

	public void setVersionId(Integer versionId) {
		this.versionId = versionId;
	}

	public String getDeleteBy() {
		return deleteBy;
	}

	public void setDeleteBy(String deleteBy) {
		this.deleteBy = deleteBy;
	}

	public Date getDeleteDate() {
		return deleteDate;
	}

	public void setDeleteDate(Date deleteDate) {
		this.deleteDate = deleteDate;
	}

	public String getOperationOrgCode() {
		return operationOrgCode;
	}

	public void setOperationOrgCode(String operationOrgCode) {
		this.operationOrgCode = operationOrgCode;
	}


	
}