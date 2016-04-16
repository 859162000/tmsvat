/**
 * @(#) IModelBase.java
 */

package com.deloitte.tms.pl.core.model;

import java.io.Serializable;
import java.util.Date;

public interface IModelBase
{
	public Serializable getId();
	
	public String getCreatedBy();

	public void setCreatedBy(String createdBy);

	public Date getCreateDate();

	public void setCreateDate(Date createDate);

	public String getModifiedBy();

	public void setModifiedBy(String modifiedBy);

	public Date getModifiedDate();

	public void setModifiedDate(Date modifiedDate);

	public String getFlag();

	public void setFlag(String flag);

	public String getStatus();

	public void setStatus(String status);

	public String getCompanyId();

	public void setCompanyId(String companyId);
	
	public String getDataowner();

	public void setDataowner(String dataowner);
}
