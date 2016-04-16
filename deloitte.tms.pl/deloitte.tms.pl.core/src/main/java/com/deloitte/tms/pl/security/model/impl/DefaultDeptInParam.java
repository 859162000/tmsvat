package com.deloitte.tms.pl.security.model.impl;

import java.util.List;

public class DefaultDeptInParam extends DefaultDept {
	/**
	 * 显示上级部门名称,用于界面显示
	 */
	String parentName;	
	/**
	 *用于控制界面是否勾选
	 */
	boolean checked;
	boolean hot;
	private List<DefaultDeptInParam> relDepts;
	List<DefaultPositionInParam> relPositions;
	List<DefaultUserInParam> relUsers;
	
	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public List<DefaultDeptInParam> getRelDepts() {
		return relDepts;
	}

	public void setRelDepts(List<DefaultDeptInParam> relDepts) {
		this.relDepts = relDepts;
	}

	public List<DefaultPositionInParam> getRelPositions() {
		return relPositions;
	}

	public void setRelPositions(List<DefaultPositionInParam> relPositions) {
		this.relPositions = relPositions;
	}

	public List<DefaultUserInParam> getRelUsers() {
		return relUsers;
	}

	public void setRelUsers(List<DefaultUserInParam> relUsers) {
		this.relUsers = relUsers;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public boolean isHot() {
		return hot;
	}

	public void setHot(boolean hot) {
		this.hot = hot;
	}
	
}
