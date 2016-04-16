package com.deloitte.tms.pl.workflow.deploy.validate.impl;

import org.w3c.dom.Element;

/**
 * @author Jacky
 * @date 2013年9月15日
 */
public class ForkNodeValidator extends NodeValidator {

	public boolean support(Element element) {
		return element.getNodeName().equals("fork");
	}

	public String getNodeName() {
		return "分支";
	}

}
