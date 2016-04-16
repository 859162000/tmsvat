package com.deloitte.tms.pl.security.dao;

import com.deloitte.tms.pl.core.dao.IDao;
import com.deloitte.tms.pl.security.model.impl.QQOauth2User;
import com.deloitte.tms.pl.security.model.impl.TaoBaoOauth2User;

public interface Oauth2UserDao extends IDao{
	public static final String BEAN_ID="oauth2UserDao";
	public QQOauth2User getQQOauth2User(String appkey);
	public TaoBaoOauth2User getTaoBaoOauth2User(String appkey);
}
