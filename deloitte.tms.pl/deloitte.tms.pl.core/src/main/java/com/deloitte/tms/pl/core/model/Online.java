package com.deloitte.tms.pl.core.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.deloitte.tms.pl.core.commons.annotation.ModelProperty;
import com.deloitte.tms.pl.core.commons.constant.TablePreDef;

@Entity
@Table(name = TablePreDef.BASEPRE + "Online")
@ModelProperty(comment = "在线记录")
public class Online implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2676888458087342844L;
	@Id
	@TableGenerator(name = "Online_GENERATOR", pkColumnValue = "Online", table = "TBL_SEQUENCE", pkColumnName = "TYPE", valueColumnName = "VALUE", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "Online_GENERATOR")
	@Column(name = "ID",  length = 16)
	private Long id;
	@Column(name = "LOGINNAME", length = 100)
	private String loginname;
	@Column(name = "IP", length = 100)
	private String ip;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATEDATETIME", length = 7)
	private Date createdatetime;
	@Column(name = "TYPE", length = 1)
	private String type;// 1.登录0.注销
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = (Long) id;
	}
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Date getCreatedatetime() {
		return createdatetime;
	}
	public void setCreatedatetime(Date createdatetime) {
		this.createdatetime = createdatetime;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
