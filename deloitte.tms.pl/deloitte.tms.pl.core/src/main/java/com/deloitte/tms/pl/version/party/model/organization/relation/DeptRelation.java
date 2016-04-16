package com.deloitte.tms.pl.version.party.model.organization.relation;

import com.deloitte.tms.pl.version.party.model.organization.DepartmentNode;

public class DeptRelation {

	//上級
	private DepartmentNode srcDepartment;
	//下級
	private DepartmentNode tgtDepartment;

	private String relCode;
	
	public String getRelCode() {
		return relCode;
	}

	public void setRelCode(String relCode) {
		this.relCode = relCode;
	}
	/**
	 * 上级
	 * @return
	 */
	public DepartmentNode getSrcDepartment() {
		return srcDepartment;
	}

	/**
	 * 上级
	 * @param srcDepartment
	 */
	public void setSrcDepartment(DepartmentNode srcDepartment) {
		this.srcDepartment = srcDepartment;
	}
	/**
	 * 下级
	 * @return
	 */
	public DepartmentNode getTgtDepartment() {
		return tgtDepartment;
	}
	/**
	 * 下级
	 * @param tgtDepartment
	 */
	public void setTgtDepartment(DepartmentNode tgtDepartment) {
		this.tgtDepartment = tgtDepartment;
	}
	
}
