package com.deloitte.tms.pl.security.model.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.deloitte.tms.pl.core.commons.constant.TablePreDef;
import com.deloitte.tms.pl.core.model.impl.BaseEntity;

/**
 * 角色资源
 * 
 * @author bo.wang
 * @create 2016年3月15日 下午3:27:51 
 * @version 1.0.0
 */
@Entity
@Table(name=RoleResource.TABLE)
public class RoleResource extends BaseEntity{
	
	public static final String TABLE=TablePreDef.BASEPRE+"ROLE_RESOURCE";
	public static final String SEQ=TABLE;
	
	@Id
	@Column(name="ROLE_RESOURCE_ID",length=36)
	String id;
	
	@Column(name="ROLE_ID",length=36)
	String roleId;
	
	@Column(name="URL_ID",length=36)
	String urlId;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getUrlId() {
		return urlId;
	}
	public void setUrlId(String urlId) {
		this.urlId = urlId;
	}
}
