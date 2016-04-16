package com.deloitte.tms.base.cache.model;

import java.util.Collection;

/**
 * 打印点
 * @author bo.wang
 * @create 2016年3月24日 下午2:12:05 
 * @version 1.0.0
 */
public class PrintSiteNode extends LeafNode {
	
	LegalEntityNode legalEntityNode;

	public PrintSiteNode(String id, String orgName) {
		super(id, orgName);
	}

	public LegalEntityNode getLegalEntityNode() {
		return legalEntityNode;
	}

	public void setLegalEntityNode(LegalEntityNode legalEntityNode) {
		this.legalEntityNode = legalEntityNode;
	}
	public Collection<PrinterTerminalNode> getPrinterTerminalNodes() {
		return legalEntityNode.getPrinterTerminalNodes();
	}
	public PrinterTerminalNode getDefaultPrinterTerminalNode() {
		return legalEntityNode.getPrinterTerminalNodes().size()==0?null:legalEntityNode.getPrinterTerminalNodes().iterator().next();
	}
}
