package com.deloitte.tms.pl.security.model.impl;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;

import com.deloitte.tms.pl.core.commons.annotation.ModelProperty;
import com.deloitte.tms.pl.core.commons.constant.TablePreDef;
import com.deloitte.tms.pl.core.model.impl.BaseEntity;
import com.deloitte.tms.pl.security.model.SecurityCompany;
import com.deloitte.tms.pl.security.model.SecurityRole;

/**
 * 
 * @author bo.wang
 * 角色定义
 */
@Entity
@Table(name=DefaultRole.TABLE)
public class DefaultRole extends BaseEntity implements GrantedAuthority,SecurityCompany,SecurityRole{
	
	public static final String NORMAL_TYPE="normal";
	
	public static final String TABLE=TablePreDef.BASEPRE+"ROLE";
	public static final String SEQ=TABLE;
	
	@Id
	@Column(name="ROLE_ID",length=60)
	private String id;
	
	@Column(name="ROLE_CODE",length=60)
	private String name;
	
	@Column(name="ROLE_NAME",length=120)
	private String desc;
	
	@Column(name="ROLE_TYPE",length=10)
	private String type;

	@Column(name="PARENT_ID",length=60)
	private String parentId;

	@Column(name="SROT_ORDER")
	private Integer sortOrder;
	
	@Transient
	private List<DefaultRole> children;
	
	@Transient
	private List<DefaultUrl> urls;
	@Transient
	private List<UrlComponent> urlComponents;
	@Transient
	private List<RoleMember> roleMembers;
	
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public List<DefaultUrl> getUrls() {
		return urls;
	}
	public void setUrls(List<DefaultUrl> urls) {
		this.urls = urls;
	}

	public List<UrlComponent> getUrlComponents() {
		return urlComponents;
	}
	public void setUrlComponents(List<UrlComponent> urlComponents) {
		this.urlComponents = urlComponents;
	}
	public List<DefaultRole> getChildren() {
		return children;
	}
	public void setChildren(List<DefaultRole> children) {
		this.children = children;
	}
	public List<RoleMember> getRoleMembers() {
		return roleMembers;
	}
	public void setRoleMembers(List<RoleMember> roleMembers) {
		this.roleMembers = roleMembers;
	}
	public String getAuthority() {
		return getId();
	}
	public Integer getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}
	
}
