package com.deloitte.tms.pl.security.model.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.deloitte.tms.pl.core.commons.annotation.ModelProperty;
import com.deloitte.tms.pl.core.commons.constant.TablePreDef;
import com.deloitte.tms.pl.core.model.impl.BaseEntity;

/**
 * @since 2016-3-14
 * @author bo.wang
 */
@Entity
@Table(name=DefaultCompany.TABLE)
public class DefaultCompany extends BaseEntity{
	
	public static final String TABLE=TablePreDef.BASEPRE+"COMPANY";
	public static final String SEQ=TABLE;
	
	@Id
	@Column(name="COMPANEY_ID",length=36)
	private String id;
	
	@ModelProperty(comment="公司名称")
	@Column(name="COMPANY_NAME",length=60)
	private String name;
	
	@ModelProperty(comment="公司描述")
	@Column(name="DESCRIPTION",length=120)
	private String desc;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
}
