package com.deloitte.tms.pl.security.dao;

import com.deloitte.tms.pl.core.dao.IDao;

public interface ICompanyDao extends IDao{
	public static final String BEAN_ID="ling2.companyDao";
	void registerCompany(String id,String name,String desc);
	public String checkCompanyExist(String id);
}
