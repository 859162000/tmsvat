package com.deloitte.tms.pl.business.model;

import com.deloitte.tms.pl.core.model.impl.BaseEntity;


public class BaseBusiness extends BaseEntity{
	private String label;
	
	private String name;
	
	private String icon;
	
	/** SELF | BLANK | PARENT | TOP */
	private String implementation;
	
	private String description;
	
	private boolean enabled;
	
	/** ROOT | BUSINESS_GROUP | BUSINESS_ITEM */
	protected String type;

	/** 冗余字段 页面图标 */
	private String iconUrl;
	
	protected boolean hasChild;
	
	private boolean select;
	
	private String path;
	
	protected Long id;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = (Long) id;
	}
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isSelect() {
		return select;
	}

	public void setSelect(boolean select) {
		this.select = select;
	}

	public boolean isHasChild() {
		return hasChild;
	}

	public void setHasChild(boolean hasChild) {
		this.hasChild = hasChild;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getImplementation() {
		return implementation;
	}

	public void setImplementation(String implementation) {
		this.implementation = implementation;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}
	public String toString() {
		return label;
	}
}
