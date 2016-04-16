package com.deloitte.tms.pl.security.model;

import java.util.List;
/**
 * @since 2013-1-22
 * @author Jacky.gao
 */
public interface SecurityGroup extends SecurityCompany{
	
	public String getId() ;
	public String getName();
	public String getDesc();
	public String getParentId();
	public List<SecurityUser> getUsers();
	public List<SecurityDept> getDepts();
	public List<SecurityPosition> getPositions();
}
