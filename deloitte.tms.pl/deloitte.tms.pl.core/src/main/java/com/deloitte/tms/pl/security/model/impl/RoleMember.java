package com.deloitte.tms.pl.security.model.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.deloitte.tms.pl.core.commons.annotation.ModelProperty;
import com.deloitte.tms.pl.core.commons.constant.TablePreDef;
import com.deloitte.tms.pl.core.model.impl.BaseEntity;
import com.deloitte.tms.pl.security.model.SecurityDept;
import com.deloitte.tms.pl.security.model.SecurityPosition;
import com.deloitte.tms.pl.security.model.SecurityUser;

/**
 * 角色成员
 * 
 * @author bo.wang
 * @create 2016年3月15日 下午3:28:13 
 * @version 1.0.0
 */
@Entity
@Table(name=TablePreDef.BASEPRE+"ROLE_MEMBER")
public class RoleMember extends BaseEntity {
	
	public static final String USER="user";
	public static final String DEPT="dept";
	public static final String POSITION="position";
	public static final String GROUP="group";

	@Id
	@Column(name="ROLE_MENBER_ID",length=36)
	String id;
	
	@Column(name="ORG_ID",length=36)
	String deptId;
	
	@Column(name="POSITION_ID",length=36)
	String positionId;
	
	@Column(name="ROLE_ID",length=36)
	String roleId;
	
	@Column(name="IS_GRANTED")
	@ModelProperty(comment="是否启用授权",des="貌似暂时没有实现")
	boolean granted;

	@Column(name="USERNAME",length=36)
	String username;
	@ManyToOne(targetEntity=DefaultUser.class)
	@Cascade(CascadeType.REFRESH)
	@JoinColumn(name="USERNAME",insertable=false,updatable=false)
	SecurityUser user;
	
	@Column(name="GROUP_ID",length=36)
	String groupId;
	@ManyToOne(targetEntity=DefaultGroup.class)
	@Cascade(CascadeType.REFRESH)
	@JoinColumn(name="GROUP_ID",insertable=false,updatable=false)
	DefaultGroup group;

	@Transient
	SecurityDept dept;
	
	@Transient
	SecurityPosition position;	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public DefaultGroup getGroup() {
		return group;
	}
	public void setGroup(DefaultGroup group) {
		this.group = group;
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
	public boolean isGranted() {
		return granted;
	}
	public void setGranted(boolean granted) {
		this.granted = granted;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	
}
