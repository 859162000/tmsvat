package com.deloitte.tms.pl.security.model.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.deloitte.tms.pl.core.commons.constant.TablePreDef;
import com.deloitte.tms.pl.core.model.impl.BaseEntity;
import com.deloitte.tms.pl.security.model.SecurityDept;
import com.deloitte.tms.pl.security.model.SecurityPosition;
import com.deloitte.tms.pl.security.model.SecurityUser;

/**
 * 
 *分组成员
 * 关联分组和人,组织,岗位
 * @author bo.wang
 * @create 2016年3月15日 下午1:44:32 
 * @version 2.0.0
 */
@Entity
@Table(name=GroupMember.TABLE)
public class GroupMember extends BaseEntity{
	
	public static final String TABLE =TablePreDef.BASEPRE+"BASE_GROUP_MEMBER";
	public static final String SEQ=TABLE;

	@Id
	@Column(name="GROUP_MEMBER_ID",length=36)
	private String id;
	
	@Column(name="GROUP_ID",length=36)
	private String groupId;

	@Column(name="USERNAME",length=36)
	private String username;
	
	@Column(name="DEPT_ID",length=36)
	private String deptId;
	
	@Column(name="POSITION_ID",length=36)
	private String positionId;
	
	@Transient
	private SecurityUser user;
	@Transient
	private SecurityDept dept;
	@Transient
	private SecurityPosition position;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getPositionId() {
		return positionId;
	}

	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}

	public SecurityUser getUser() {
		return user;
	}

	public void setUser(SecurityUser user) {
		this.user = user;
	}

	public SecurityDept getDept() {
		return dept;
	}

	public void setDept(SecurityDept dept) {
		this.dept = dept;
	}

	public SecurityPosition getPosition() {
		return position;
	}

	public void setPosition(SecurityPosition position) {
		this.position = position;
	}
}
