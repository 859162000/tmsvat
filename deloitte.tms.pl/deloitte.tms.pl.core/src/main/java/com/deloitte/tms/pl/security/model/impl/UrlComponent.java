package com.deloitte.tms.pl.security.model.impl;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.deloitte.tms.pl.core.commons.constant.TablePreDef;
import com.deloitte.tms.pl.core.model.impl.BaseEntity;
import com.deloitte.tms.pl.security.enums.AuthorityType;

/**
 * 页面组件
 * 
 * @author bo.wang
 * @create 2016年3月15日 下午3:28:30 
 * @version 1.0.0
 */
@Entity
@Table(name=UrlComponent.TABLE)
public class UrlComponent extends BaseEntity{
	
	public static final String TABLE=TablePreDef.BASEPRE+"URL_COMPONENT";
	public static final String SEQ=TABLE;

	@Id
	@Column(name="URL_COMPONENT_ID",length=36)
	private String id;
	
	@Column(name="URL_ID",length=36)
	private String urlId;
	
	@Column(name="ROLE_ID",length=36)
	private String roleId;
	
	@Column(name="AUTHORITY_TYPE",length=10,nullable=false)
	@Enumerated(EnumType.STRING)
	private AuthorityType authorityType;	
	
	@ManyToOne(cascade=CascadeType.ALL,targetEntity=ComponentDefinition.class,fetch=FetchType.EAGER)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name="COMPONENT_ID")
	private ComponentDefinition component;
	
	@Transient
	private DefaultUrl url;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUrlId() {
		return urlId;
	}

	public void setUrlId(String urlId) {
		this.urlId = urlId;
	}
	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public AuthorityType getAuthorityType() {
		return authorityType;
	}

	public void setAuthorityType(AuthorityType authorityType) {
		this.authorityType = authorityType;
	}

	public ComponentDefinition getComponent() {
		return component;
	}

	public void setComponent(ComponentDefinition component) {
		this.component = component;
	}

	public DefaultUrl getUrl() {
		return url;
	}

	public void setUrl(DefaultUrl url) {
		this.url = url;
	}
}
