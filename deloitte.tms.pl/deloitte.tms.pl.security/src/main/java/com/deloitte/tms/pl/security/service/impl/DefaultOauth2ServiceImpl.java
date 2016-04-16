package com.deloitte.tms.pl.security.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.core.dao.IDao;
import com.deloitte.tms.pl.core.service.impl.BaseService;
import com.deloitte.tms.pl.security.dao.Oauth2UserDao;
import com.deloitte.tms.pl.security.model.impl.DefaultUser;
import com.deloitte.tms.pl.security.model.impl.QQOauth2User;
import com.deloitte.tms.pl.security.model.impl.TaoBaoOauth2User;
import com.deloitte.tms.pl.security.service.Oauth2Service;

@Component(Oauth2Service.BEAN_ID)
public class DefaultOauth2ServiceImpl extends BaseService implements Oauth2Service{
	@Resource
	Oauth2UserDao oauth2UserDao;
	@Override
	public IDao getDao() {
		return oauth2UserDao;
	}
	@Override
	public QQOauth2User getQQOauth2User(String appkey) {
		return oauth2UserDao.getQQOauth2User(appkey);
	}

	public DefaultUser getUserByUsername(String username)
	{
		return (DefaultUser) oauth2UserDao.get(DefaultUser.class, username);
	}
	@Override
	public void saveNewuser(DefaultUser user,String accessToken,String appkey) {
		oauth2UserDao.save(user);
		QQOauth2User qQOauth2User=new QQOauth2User();
		qQOauth2User.setAccessToken(accessToken);
		qQOauth2User.setAppkey(appkey);
		qQOauth2User.setUserid(appkey);
		oauth2UserDao.save(qQOauth2User);
	}
	@Override
	public TaoBaoOauth2User getTaoBaoOauth2User(String appkey) {
		return oauth2UserDao.getTaoBaoOauth2User(appkey);
	}
}
