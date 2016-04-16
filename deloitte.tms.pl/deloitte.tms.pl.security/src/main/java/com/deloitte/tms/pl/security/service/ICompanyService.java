package com.deloitte.tms.pl.security.service;

/**
 * @author Jacky.gao
 * @since 2013-2-25
 */
public interface ICompanyService {
	public static final String BEAN_ID="ling2.companyService";
	void registerCompany(String id,String name,String desc);
	public String checkCompanyExist(String id);
}
