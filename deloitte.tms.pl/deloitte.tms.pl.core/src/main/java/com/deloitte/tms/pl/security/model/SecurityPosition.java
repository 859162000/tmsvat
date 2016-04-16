package com.deloitte.tms.pl.security.model;

import java.util.List;

/**
 * @since 2013-1-22
 * @author Jacky.gao
 */
public interface SecurityPosition extends SecurityCompany{
	String getId();
	String getName();
	List<SecurityUser> getUsers();
}
