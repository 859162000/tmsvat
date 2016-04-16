package com.deloitte.tms.pl.security.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.core.dao.impl.BaseDao;
import com.deloitte.tms.pl.security.dao.Oauth2UserDao;
import com.deloitte.tms.pl.security.model.impl.QQOauth2User;
import com.deloitte.tms.pl.security.model.impl.TaoBaoOauth2User;
@Component(Oauth2UserDao.BEAN_ID)
public class DefaultOauth2UserDao extends BaseDao implements Oauth2UserDao{
	@Override
	public QQOauth2User getQQOauth2User(String appkey) {
		StringBuffer query=new StringBuffer();
		Map values=new HashMap();
		query.append(" from QQOauth2User where 1=1 ");
		query.append(" and appkey=:appkey");
		values.put("appkey", appkey);
		return (QQOauth2User) getFirstRecord(findBy(query, values));
	}

	@Override
	public TaoBaoOauth2User getTaoBaoOauth2User(String appkey) {
		StringBuffer query=new StringBuffer();
		Map values=new HashMap();
		query.append(" from TaoBaoOauth2User where 1=1 ");
		query.append(" and appkey=:appkey");
		values.put("appkey", appkey);
		return (TaoBaoOauth2User) getFirstRecord(findBy(query, values));
	}

}
