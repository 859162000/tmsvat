package com.deloitte.tms.security.searchform;

import com.deloitte.tms.security.model.User;

public class UserSearchForm extends User{
	
	private String createdatefrom;
	private String createdateto;
	public String getCreatedatefrom() {
		return createdatefrom;
	}
	public void setCreatedatefrom(String createdatefrom) {
		this.createdatefrom = createdatefrom;
	}
	public String getCreatedateto() {
		return createdateto;
	}
	public void setCreatedateto(String createdateto) {
		this.createdateto = createdateto;
	}
	
	

}
