package com.deloitte.tms.pl.security.service;

import com.deloitte.tms.pl.core.service.IService;
import com.deloitte.tms.pl.security.model.impl.DefaultUser;
import com.deloitte.tms.pl.security.model.impl.QQOauth2User;
import com.deloitte.tms.pl.security.model.impl.TaoBaoOauth2User;

public interface Oauth2Service extends IService{
	public static final String BEAN_ID="oauth2Service";
	public QQOauth2User getQQOauth2User(String appkey);
	public TaoBaoOauth2User getTaoBaoOauth2User(String appkey);
	DefaultUser getUserByUsername(String username);
	public void saveNewuser(DefaultUser user,String accessToken,String appkey);
}
