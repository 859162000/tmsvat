package com.deloitte.tms.pl.security.model;

import java.util.List;

/**
 * @since 2013-1-22
 * @author Jacky.gao
 */
public interface SecurityDept extends SecurityCompany{
	String getId();
	String getOrgName();
	String getParentId();
	SecurityDept getParent();
	List<SecurityUser> getUsers();
}
