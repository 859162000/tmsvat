package com.deloitte.tms.pl.security.model;


public class UserForSelect {
	/**
	 * 关联关系类型
	 */
	String relationType;
	/**
	 * 关联关系外键
	 */
	String relationId;
	
	String username;	
	
	String cname;
	
	String ename;
	
	/**
	 * 排序
	 */
	Integer sortOrder;
	
	public String getRelationType() {
		return relationType;
	}
	public void setRelationType(String relationType) {
		this.relationType = relationType;
	}
	public String getRelationId() {
		return relationId;
	}
	public void setRelationId(String relationId) {
		this.relationId = relationId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public Integer getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}
	
}
