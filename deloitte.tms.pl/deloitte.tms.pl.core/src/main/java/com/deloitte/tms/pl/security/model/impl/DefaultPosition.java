package com.deloitte.tms.pl.security.model.impl;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.deloitte.tms.pl.core.commons.annotation.ModelProperty;
import com.deloitte.tms.pl.core.commons.constant.TablePreDef;
import com.deloitte.tms.pl.core.model.impl.BaseRelation;
import com.deloitte.tms.pl.security.model.SecurityPosition;
import com.deloitte.tms.pl.security.model.SecurityUser;

/**
 * 
 * 岗位
 * @author bo.wang
 * @create 2016年3月15日 下午1:48:44 
 * @version 2.0.0
 */
@Entity
@Table(name=DefaultPosition.TABLE)
public class DefaultPosition extends BaseRelation implements SecurityPosition{

	public static final String TABLE =TablePreDef.BASEPRE+"POSITION";
	public static final String SEQ=TABLE;

	@Id
	@Column(name="POSITION_ID",length=36)
	String id;
	
	@Column(name="POSITION_NAME",length=120)
	String name;
	
	@Column(name="DESCRIPTION",length=120)
	String desc;
	
	@ModelProperty(comment="岗位类型")
	@Column(name="POSITION_TYPE",length=120)
	String type;

	@Transient
	List<SecurityUser> users;	
	
	public DefaultPosition(){

	}
	
	public DefaultPosition(String positionId){
		this.id=positionId;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public List<SecurityUser> getUsers() {
		return users;
	}
	public void setUsers(List<SecurityUser> users) {
		this.users = users;
	}
}
