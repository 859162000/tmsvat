package com.deloitte.tms.pl.security.model.impl;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.collections.ListUtils;

import com.deloitte.tms.pl.core.commons.constant.TablePreDef;
import com.deloitte.tms.pl.core.model.impl.BaseEntity;
import com.deloitte.tms.pl.security.model.SecurityCompany;
import com.deloitte.tms.pl.security.model.SecurityDept;
import com.deloitte.tms.pl.security.model.SecurityPosition;
import com.deloitte.tms.pl.security.model.SecurityUser;
/**
 * 
 *分组信息
 * 人,组织,岗位
 * @author bo.wang
 * @create 2016年3月15日 下午1:38:01 
 * @version 2.0.0
 */
@Entity
@Table(name=DefaultGroup.TABLE)
public class DefaultGroup extends BaseEntity implements SecurityCompany{
	
	public static final String TABLE=TablePreDef.BASEPRE+"GROUP";
	public static final String SEQ=TABLE;
	
	@Id
	@Column(name="GROUP_ID",length=36)
	String id;
	
	@Column(name="NAME",length=60)
	String name;
	
	@Column(name="DESCRIPTION",length=120)
	String desc;
	
	@Column(name="PARENT_ID",length=36)
	String parentId;
	
	@Transient
	private List<SecurityUser> users;
	
	@Transient
	private List<SecurityDept> depts;
	
	@Transient
	private List<SecurityPosition> positions;

	public DefaultGroup(String groupId){
		this.id=groupId;
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
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	@SuppressWarnings("unchecked")
	public List<SecurityUser> getUsers() {
		if(users==null){
			return ListUtils.EMPTY_LIST;
		}
		return users;
	}
	public void setUsers(List<SecurityUser> users) {
		this.users = users;
	}
	@SuppressWarnings("unchecked")
	public List<SecurityDept> getDepts() {
		if(depts==null){
			return ListUtils.EMPTY_LIST;
		}
		return depts;
	}
	public void setDepts(List<SecurityDept> depts) {
		this.depts = depts;
	}
	@SuppressWarnings("unchecked")
	public List<SecurityPosition> getPositions() {
		if(positions==null){
			return ListUtils.EMPTY_LIST;
		}
		return positions;
	}
	public void setPositions(List<SecurityPosition> positions) {
		this.positions = positions;
	}
	
}
