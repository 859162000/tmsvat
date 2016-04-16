package com.deloitte.tms.pl.core.model;

import java.io.Serializable;

public interface IUerDetailMore {
	public String getOrgCode();
	public void setOrgCode(String orgCode);
	public String getDeptCode();
	public void setDeptCode(String deptCode);
	public String getOrgName();
	public void setOrgName(String orgName);
	public String getDepName();
	public void setDepName(String depName);
	public Serializable getUserId();
	public void setUserId(Serializable userId);
	public String getUserName();
	public void setUserName(String userName);
	
}
