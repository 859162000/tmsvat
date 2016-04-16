package com.deloitte.tms.pl.workflow.deploy.validate.impl;

import org.w3c.dom.Element;

/**
 * @author Jacky
 * @date 2013年9月15日
 */
public class EndNodeValidator extends NodeValidator {

	public boolean support(Element element) {
		return element.getNodeName().equals("end");
	}

	public String getNodeName() {
		return "结束";
	}
}
