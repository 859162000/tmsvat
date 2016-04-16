package com.deloitte.tms.base.cache.model;


/**
 * 业务组织
 * @author bo.wang
 * @create 2016年3月24日 上午11:57:10 
 * @version 1.0.0
 */
public class BizOrgNode extends OrgNode {
	
	Boolean virtual;
	
	public Boolean getVirtual() {
		return virtual;
	}

	public void setVirtual(Boolean virtual) {
		this.virtual = virtual;
	}

	public BizOrgNode(String id, String orgName) {
		super(id, orgName);
	}
}
