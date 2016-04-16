package com.deloitte.tms.pl.workflow.deploy.validate.impl;

import java.util.List;

import org.w3c.dom.Element;

/**
 * @author Jacky
 * @date 2013年9月15日
 */
public class StartNodeValidator extends NodeValidator {

	public void validate(Element element, List<String> errors,List<String> nodeNames) {
		super.validate(element, errors,nodeNames);
	}

	public boolean support(Element element) {
		return element.getNodeName().equals("start");
	}

	
	public String getNodeName() {
		return "开始";
	}
}
