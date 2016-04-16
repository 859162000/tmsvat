package com.deloitte.tms.pl.security.model.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.deloitte.tms.pl.core.commons.constant.TablePreDef;
import com.deloitte.tms.pl.core.model.impl.BaseEntity;

/**
 * 组织和岗位关系
 * 
 * @author bo.wang
 * @create 2016年3月15日 下午1:57:14 
 * @version 2.0.0
 */
@Entity
@Table(name=DeptPosition.TABLE)
public class DeptPosition extends BaseEntity{
	
	public static final String TABLE=TablePreDef.BASEPRE+"ORG_POSITION";
	public static final String SEQ=TABLE;
	
	@Id
	@Column(name="ORG_POSITION_ID",length=60)
	private String id;
	
	@Column(name="ORG_ID",length=60,nullable=false)
	private String deptId;
	
	@Column(name="POSITION_ID",length=60,nullable=false)
	private String positionId;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getPositionId() {
		return positionId;
	}
	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}
}
