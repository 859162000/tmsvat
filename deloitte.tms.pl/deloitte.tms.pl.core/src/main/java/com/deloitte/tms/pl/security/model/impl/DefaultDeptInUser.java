package com.deloitte.tms.pl.security.model.impl;

import java.io.Serializable;
import java.util.List;

public class DefaultDeptInUser extends DefaultDept implements Serializable{

	
	/**  
	 * serialVersionUID  
	 */  
	
	private static final long serialVersionUID = 1L;
	
	private String allUsername;

	public String getAllUsername() {
		return allUsername;
	}

	public void setAllUsername(String allUsername) {
		this.allUsername = allUsername;
	}
	
	
	
}
