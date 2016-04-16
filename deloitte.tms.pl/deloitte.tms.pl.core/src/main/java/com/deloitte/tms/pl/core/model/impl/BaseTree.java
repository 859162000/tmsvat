package com.deloitte.tms.pl.core.model.impl;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.deloitte.tms.pl.core.commons.annotation.ModelProperty;

/**
 * 
 * @author bo.wang
 *
 */
@MappedSuperclass
public abstract class BaseTree extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = -829640066995405902L;
	
	@Column(name = "parentName",  length = 150)
	@ModelProperty(comment="上级名称")
	private String parentName;
	
	@Column(name = "parentCode",  length = 150)
	@ModelProperty(comment="上级代码")
	String parentCode;
	
	@Column(name = "NAME_",  length = 150)
	@ModelProperty(comment="定义名称")
	private String name;
	
	@Column(name = "code",  length = 150)
	@ModelProperty(comment="代码")
	String code;
	
	@Column(name = "type",  length = 150)
	@ModelProperty(comment="类型",des="如果是相同数据存在一张表,用于表示数据代表什么类别的基础数据")
	String type;
	
	@Column(name = "dataType",  length = 50)
	@ModelProperty(comment="数据分类",des="用于基础数据的分类")
	String dataType;	
	
	@Column(name = "dataLevel")
	@ModelProperty(comment="分类",des="用于区别数据的层级,第一层为1")
	Integer dataLevel;
	
	@Column(name = "dataSeq")
	@ModelProperty(comment="数据序列")
	String dataSeq;
	
	@Column(name="DESC_",length=120)
	@ModelProperty(comment="描述")
	String des;
	
	@Column(name = "orgCode")
	@ModelProperty(comment="主数据归属单位")
	String orgCode;
	
	@Column(name = "orgName")
	@ModelProperty(comment="主数据归属单位")
	String orgName;
	
	@Column(name = "PARENT_ID_")
	@ModelProperty(comment="上级")
	String parentId;	
	
	@Column(name = "firstVersionId",  length = 10)
	@ModelProperty(comment="第一个版本ID")
	Long firstVersionId;
	
	@Column(name = "version",  length = 10)
	@ModelProperty(comment="版本号")
	String version;
	
	@Column(name = "copyRelations",length = 200)
	@ModelProperty(comment="复制关联")
	String copyRelations;
	
	@Column(name = "effectDate")
	@ModelProperty(comment="有效期起")
	Date effectDate;
	
	@Column(name = "quitDate")
	@ModelProperty(comment="有效期止")
	Date quitDate;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public Integer getDataLevel() {
		return dataLevel;
	}
	public void setDataLevel(Integer dataLevel) {
		this.dataLevel = dataLevel;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDataSeq() {
		return dataSeq;
	}
	public void setDataSeq(String dataSeq) {
		this.dataSeq = dataSeq;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String toString(){
		return name;
	}
	public Long getFirstVersionId() {
		return firstVersionId;
	}
	public void setFirstVersionId(Long firstVersionId) {
		this.firstVersionId = firstVersionId;
	}
	public String getCopyRelations() {
		return copyRelations;
	}
	public void setCopyRelations(String copyRelations) {
		this.copyRelations = copyRelations;
	}
	public Date getEffectDate() {
		return effectDate;
	}
	public void setEffectDate(Date effectDate) {
		this.effectDate = effectDate;
	}
	public Date getQuitDate() {
		return quitDate;
	}
	public void setQuitDate(Date quitDate) {
		this.quitDate = quitDate;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public String getParentCode() {
		return parentCode;
	}
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
	
}
