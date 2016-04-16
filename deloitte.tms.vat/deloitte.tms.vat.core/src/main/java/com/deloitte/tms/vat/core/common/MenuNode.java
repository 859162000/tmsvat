package com.deloitte.tms.vat.core.common;

import java.util.ArrayList;
import java.util.List;

public class MenuNode {

	private String menuid;
	private String menuname;
	private String icon;
	private String url;
	private String pmenuid;
	
	private List<MenuNode> menus;

	public MenuNode() {

	}

	public MenuNode(String menuid, String menuname, String icon,
			String pmenuid, List<MenuNode> menus) {
		super();
		this.menuid = menuid;
		this.menuname = menuname;
		this.icon = icon;
		this.pmenuid = pmenuid;
		//this.menus = menus;
	}

	public void addMenu(MenuNode node){
        if(this.menus==null){
        	menus = new ArrayList<MenuNode>();
        	menus.add(node);
        }else{
        	menus.add(node);
        }
    }

	public String getMenuid() {
		return menuid;
	}

	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}

	public String getMenuname() {
		return menuname;
	}

	public void setMenuname(String menuname) {
		this.menuname = menuname;
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

	public String getPmenuid() {
		return pmenuid;
	}

	public void setPmenuid(String pmenuid) {
		this.pmenuid = pmenuid;
	}

	public List<MenuNode> getMenus() {
		return menus;
	}

	public void setMenus(List<MenuNode> menus) {
		this.menus = menus;
	}

}
