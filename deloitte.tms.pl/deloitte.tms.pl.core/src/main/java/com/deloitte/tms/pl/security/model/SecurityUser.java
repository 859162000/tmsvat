package com.deloitte.tms.pl.security.model;

import java.util.List;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * @since 2013-1-24
 * @author Jacky.gao
 */
public interface SecurityUser extends UserDetails,SecurityCompany{
	String getCname();
	String getEname();
	boolean isAdministrator();
	String getMobile();
	String getEmail();
	String getCompanyId();
	/**
	 * 员工号
	 */
	String getUserCode();
	List<SecurityDept> getDepts();
	List<SecurityPosition> getPositions();
	List<SecurityRole> getRoles();
	List<SecurityGroup> getGroups();
	Map<String,Object> getMetadata();
	public Map<String, Object> getCachedProperties();
	boolean isInit();
	public void setInit(boolean isInit);
}
