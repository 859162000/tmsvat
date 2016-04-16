package com.deloitte.tms.pl.security.dao.impl;

import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.core.dao.impl.BaseDao;
import com.deloitte.tms.pl.security.dao.ICompanyDao;
import com.deloitte.tms.pl.security.model.impl.DefaultCompany;


/**
 * @author Jacky.gao
 * @since 2013-2-25
 */
@Component(ICompanyDao.BEAN_ID)
public class DefaultCompanyDao extends BaseDao implements ICompanyDao {

	public void registerCompany(String id, String name, String desc) {
		DefaultCompany company=new DefaultCompany();
		company.setId(id);
		company.setName(name);
		company.setDesc(desc);
		save(company);
	}

	@Override
	public String checkCompanyExist(String id) {
		String result=null;
		String sql="SELECT COUNT(*) FROM DefaultCompany WHERE id=?";
		int count=queryForInt(sql,new Object[]{id});
		if(count>0){
			result="公司ID已存在";
		}
		return result;
	}
}
