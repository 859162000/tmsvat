package com.deloitte.tms.base.cache.model;

/**
 * 纳税人识别号节点
 * @author bo.wang
 * @create 2016年3月24日 下午9:03:58 
 * @version 1.0.0
 */
public class LicenseNoNode extends LeafNode {
	
	LegalEntityNode legalEntityNode;

	public LicenseNoNode(String id, String orgName) {
		super(id, orgName);
	}

	public LegalEntityNode getLegalEntityNode() {
		return legalEntityNode;
	}

	public void setLegalEntityNode(LegalEntityNode legalEntityNode) {
		this.legalEntityNode = legalEntityNode;
	}

}
