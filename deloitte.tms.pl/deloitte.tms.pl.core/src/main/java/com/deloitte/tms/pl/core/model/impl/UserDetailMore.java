package com.deloitte.tms.pl.core.model.impl;

import java.io.Serializable;

import com.deloitte.tms.pl.core.model.IUerDetailMore;

public class UserDetailMore extends BaseEntity implements IUerDetailMore {
	protected Long id;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = (Long) id;
	}
	protected String orgCode;
	protected String deptCode;
	protected String orgName;
	protected String depName;
	protected Long userId;
	protected String userName;
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getDepName() {
		return depName;
	}
	public void setDepName(String depName) {
		this.depName = depName;
	}
	public Serializable getUserId() {
		return userId;
	}
	public void setUserId(Serializable userId) {
		this.userId = (Long) userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
