package com.deloitte.tms.pl.security.model.impl;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.deloitte.tms.pl.core.commons.constant.TablePreDef;
import com.deloitte.tms.pl.core.model.impl.BaseEntity;
import com.deloitte.tms.pl.security.model.SecurityCompany;
import com.deloitte.tms.pl.security.model.SecurityUrl;

/**
 * 页面菜单表
 * 
 * @author bo.wang
 * @create 2016年3月15日 下午4:03:06 
 * @version 1.0.0
 */
@Entity
@Table(name=DefaultUrl.TABLE)
public class DefaultUrl extends BaseEntity implements SecurityCompany,SecurityUrl{
	
	public static final String TABLE=TablePreDef.BASEPRE+"URL";
	public static final String SEQ=TABLE;
	
	@Id
	@Column(name="URL_ID",length=36)
	private String id;
	
	@Column(name="NAME",length=60,nullable=false)
	private String name;
	
	@Column(name="DESCRIPTION",length=120)
	private String desc;
	
	@Column(name="URL",length=120)
	private String url;
	
	@Column(name="IS_FOR_NAVIGATION",nullable=false)
	private boolean forNavigation=true;
	
	@Column(name="SYSTEM_ID",length=60)
	private String systemId;
	
	@Column(name="ICON",length=120)
	private String icon;
	
	@Column(name="SORT_ORDER")
	private Integer order;
	
	@Column(name="target",length=120)
	private String target;
	
	@Column(name="PARENT_ID",length=60)
	private String parentId;
	@ManyToOne
	@JoinColumn(name="PARENT_ID",insertable=false,updatable=false)
	@Cascade(CascadeType.REFRESH)
	DefaultUrl parent;
	
	@OneToMany(mappedBy = "parent")
	@Cascade(CascadeType.REFRESH)
	@OrderBy("order")
	private List<DefaultUrl> children;
	
	@Transient
	private boolean use;
	
	@Transient
	private boolean hasChild;
	
	@Transient
	private List<DefaultRole> roles;	
	
	public DefaultUrl(){
		
	}
	
	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public boolean isUse() {
		return use;
	}

	public void setUse(boolean use) {
		this.use = use;
	}

	public List<DefaultUrl> getChildren() {
		return children;
	}

	public void setChildren(List<DefaultUrl> children) {
		this.children = children;
	}

	public boolean isForNavigation() {
		return forNavigation;
	}

	public void setForNavigation(boolean forNavigation) {
		this.forNavigation = forNavigation;
	}

	public List<DefaultRole> getRoles() {
		return roles;
	}

	public void setRoles(List<DefaultRole> roles) {
		this.roles = roles;
	}

	public String getSystemId() {
		return systemId;
	}

	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}
	public String toString() {
		return name;
	}

	public DefaultUrl getParent() {
		return parent;
	}

	public void setParent(DefaultUrl parent) {
		this.parent = parent;
	}

	public boolean isHasChild() {
		return hasChild;
	}

	public void setHasChild(boolean hasChild) {
		this.hasChild = hasChild;
	}
	
}
