package com.deloitte.tms.pl.security.model.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.deloitte.tms.pl.core.commons.constant.TablePreDef;
import com.deloitte.tms.pl.core.model.impl.BaseRelation;

/**
 * 用户与岗位关系表
 * 
 * @author bo.wang
 * @create 2016年3月15日 下午2:29:31 
 * @version 2.0.0
 */
@Entity
@Table(name=UserPosition.TABLE)
public class UserPosition extends BaseRelation{
	
	public static final String TABLE=TablePreDef.BASEPRE+"USER_POSITION";
	public static final String SEQ=TABLE;
	
	@Id
	@Column(name="USER_POSITION_ID",length=60)
	String id;
	
	@Column(name="USERNAME",length=60,nullable=false)
	String username;
	
	@Column(name="POSITION_ID",length=60,nullable=false)
	String positionId;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPositionId() {
		return positionId;
	}
	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}
}
