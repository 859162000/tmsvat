package com.deloitte.tms.pl.version.party.model.organization;



public class DivisionNode extends OrganizationNode {

	/** 公司等级 **/
	private String divLevel;
	
	private String parentCode;

	public DivisionNode(String orgCode, String orgName) {
		super(orgCode, orgName);
	}

	public String getDivLevel() {
		return divLevel;
	}

	public void setDivLevel(String divLevel) {
		this.divLevel = divLevel;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
	
	
}
