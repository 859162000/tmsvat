package com.deloitte.tms.pl.workflow.deploy.parse.impl;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Element;
import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.workflow.deploy.parse.AbstractParser;
import com.deloitte.tms.pl.workflow.process.node.AssignmentType;
import com.deloitte.tms.pl.workflow.process.swimlane.Swimlane;

/**
 * @author Jacky.gao
 * @since 2013年8月13日
 */
@Component
public class SwimlaneParser extends AbstractParser {

	public Object parse(Element element, long processId, boolean parseChildren) {
		Swimlane swimlane=new Swimlane();
		String name=element.attributeValue("name");
		swimlane.setName(unescape(name));
		Element descElement=element.element("description");
		if(descElement!=null){
			String desc=unescape(descElement.getTextTrim());
			desc=(desc.length()>120?desc.substring(0,120):desc);
			swimlane.setDescription(desc);
		}
		String type=element.attributeValue("assignment-type");
		if(StringUtils.isNotEmpty(type)){
			swimlane.setAssignmentType(AssignmentType.valueOf(type));			
		}
		swimlane.setAssignmentHandlerBean(unescape(element.attributeValue("assignment-handler-bean")));
		swimlane.setExpression(unescape(element.attributeValue("expression")));
		AssignmentType assignmentType=swimlane.getAssignmentType();
		if(assignmentType!=null && (assignmentType.equals(AssignmentType.Assignee))){
			swimlane.setAssignees(parserAssignees(element));
		}
		return swimlane;
	}

	public boolean support(Element element) {
		return element.getName().equals("swimlane");
	}
}
