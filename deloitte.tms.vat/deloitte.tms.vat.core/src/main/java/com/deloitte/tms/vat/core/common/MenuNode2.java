package com.deloitte.tms.vat.core.common;

import java.util.ArrayList;
import java.util.List;

public class MenuNode2 {

	private String id;
	private String text;
	private String icon;
	private String url;
	private String parentId;

	private List<MenuNode2> children=new ArrayList<MenuNode2>();

	public MenuNode2() {

	}

	public MenuNode2(String id, String text, String icon, String parentId,
			List<MenuNode2> menus) {
		super();
		this.id = id;
		this.text = text;
		this.icon = icon;
		this.parentId = parentId;
		// this.menus = menus;
	}

	public void addChild(MenuNode2 node) {
		if (this.children == null) {
			children = new ArrayList<MenuNode2>();
			children.add(node);
		} else {
			children.add(node);
		}
	}

	public List<MenuNode2> getChildren() {
		return children;
	}

	public void setChildren(List<MenuNode2> children) {
		this.children = children;
	}

	public String getText() {
		return text;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMenuname() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
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

}
