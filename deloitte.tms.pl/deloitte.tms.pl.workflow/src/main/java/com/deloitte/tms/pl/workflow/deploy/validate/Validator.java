package com.deloitte.tms.pl.workflow.deploy.validate;

import java.util.List;

import org.w3c.dom.Element;

/**
 * @author Jacky
 * @date 2013年9月15日
 */
public interface Validator {
	void validate(Element element,List<String> errors,List<String> nodeNames);
	boolean support(Element element);
	String getNodeName();
}
