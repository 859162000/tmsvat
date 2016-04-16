package com.deloitte.tms.base.cache.model;

import com.deloitte.tms.pl.version.party.model.organization.node.TreeNode;

/**
 * 打印服务器--打印终端
 * @author bo.wang
 * @create 2016年3月24日 下午12:09:19 
 * @version 1.0.0
 */
public class PrinterTerminalNode extends TreeNode{
	
	String equipmentId;
	/**
	 * 纳税实体id
	 */
	String legalEntityId;

	LegalEntityNode legalEntityNode;
	
	public String getLegalEntityId() {
		return legalEntityId;
	}

	public void setLegalEntityId(String legalEntityId) {
		this.legalEntityId = legalEntityId;
	}

	public PrinterTerminalNode(String id, String orgName) {
		super(id, orgName);
	}

	public LegalEntityNode getLegalEntityNode() {
		return legalEntityNode;
	}

	public void setLegalEntityNode(LegalEntityNode legalEntityNode) {
		this.legalEntityNode = legalEntityNode;
	}

	public String getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(String equipmentId) {
		this.equipmentId = equipmentId;
	}
	
}
