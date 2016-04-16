package com.deloitte.tms.pl.workflow.deploy.validate.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Element;

/**
 * @author Jacky
 * @date 2013年9月15日
 */
public class ActionNodeValidator extends NodeValidator {

	public void validate(Element element, List<String> errors,List<String> nodeNames) {
		super.validate(element, errors,nodeNames);
		String handlerBean=element.getAttribute("handler-bean");
		if(StringUtils.isEmpty(handlerBean)){
			errors.add("动作节点必须要定义实现类Bean");
		}
	}

	public boolean support(Element element) {
		return element.getNodeName().equals("action");
	}
	
	public String getNodeName() {
		return "动作";
	}
}
