package com.deloitte.tms.pl.security.model.impl;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.deloitte.tms.pl.core.commons.annotation.ModelProperty;
import com.deloitte.tms.pl.core.commons.constant.TablePreDef;
import com.deloitte.tms.pl.core.model.impl.BaseEntity;
@Entity
@Table(name = TablePreDef.BASEPRE + "Oauth2User")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "apptype", discriminatorType = DiscriminatorType.STRING)
@ModelProperty(comment = "oauth2用户关联")
public class Oauth2User extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5146508926082543480L;
	@Id
	@TableGenerator(name = "Oauth2User_GENERATOR", pkColumnValue = "Oauth2User", table = "TBL_SEQUENCE", pkColumnName = "TYPE", valueColumnName = "VALUE", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "Oauth2User_GENERATOR")
	@Column(name = "ID",  length = 16)
	Long id;
	@ModelProperty(comment = "应用的唯一标识")
	@Column(name="appkey",length=60)
	private String appkey;
	@ModelProperty(comment = "用户编号")
	@Column(name="userid",length=60)
	private String userid;
	@ModelProperty(comment = "accessToken")
	@Column(name="accessToken",length=60)
	private String accessToken;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id=(Long) id;
	}
	public String getAppkey() {
		return appkey;
	}
	public void setAppkey(String appkey) {
		this.appkey = appkey;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	
}
