package com.deloitte.tms.pl.system.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.deloitte.tms.pl.core.commons.annotation.ModelProperty;


public class SysCategoryInParam {
	
	String id;

	private String label;

	private String code;

	private String description;

	private boolean enabled;

	private Integer sortOrder;

	private Integer level;
	
	String parentId;

	SysCategory parent=null;

	Collection<SysCategoryInParam> childs=new ArrayList();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Integer getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public SysCategory getParent() {
		return parent;
	}

	public void setParent(SysCategory parent) {
		this.parent = parent;
	}

	public Collection<SysCategoryInParam> getChilds() {
		return childs;
	}

	public void setChilds(Collection<SysCategoryInParam> childs) {
		this.childs = childs;
	}
	
	
}
