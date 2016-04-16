package com.deloitte.tms.pl.security.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.core.dao.IDao;
import com.deloitte.tms.pl.core.service.impl.BaseService;
import com.deloitte.tms.pl.security.dao.ICompanyDao;
import com.deloitte.tms.pl.security.service.ICompanyService;


/**
 * @author Jacky.gao
 * @since 2013-2-25
 */
@Component(ICompanyService.BEAN_ID)
public class DefaultCompanyService extends BaseService implements ICompanyService {
	@Resource
	ICompanyDao companyDao;
	public void registerCompany(String id, String name, String desc) {
		companyDao.registerCompany(id, name, desc);
	}
	@Override
	public IDao getDao() {
		return companyDao;
	}
	@Override
	public String checkCompanyExist(String id) {
		return companyDao.checkCompanyExist(id);
	}
}
