package com.deloitte.tms.pl.workflow.process.security;

/**
 * @author Jacky.gao
 * @since 2013年8月18日
 */
public class ComponentAuthority {
	private String component;
	private Authority authority;
	public ComponentAuthority(String component,Authority authority){
		this.component=component;
		this.authority=authority;
	}
	public String getComponent() {
		return component;
	}
	public void setComponent(String component) {
		this.component = component;
	}
	public Authority getAuthority() {
		return authority;
	}
	public void setAuthority(Authority authority) {
		this.authority = authority;
	}
}
