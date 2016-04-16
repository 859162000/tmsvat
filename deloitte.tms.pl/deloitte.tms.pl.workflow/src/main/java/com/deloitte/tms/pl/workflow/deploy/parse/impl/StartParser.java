package com.deloitte.tms.pl.workflow.deploy.parse.impl;

import org.dom4j.Element;
import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.workflow.deploy.parse.AbstractTaskParser;
import com.deloitte.tms.pl.workflow.diagram.NodeDiagram;
import com.deloitte.tms.pl.workflow.diagram.ShapeType;
import com.deloitte.tms.pl.workflow.process.node.StartNode;

/**
 * @author Jacky.gao
 * @since 2013年7月30日
 */
@Component
public class StartParser extends AbstractTaskParser {

	public Object parse(Element element,long processId,boolean parseChildren) {
		StartNode node=new StartNode();
		node.setProcessId(processId);
		node.setTaskName(unescape(element.attributeValue("task-name")));
		node.setUrl(unescape(element.attributeValue("url")));
		node.setFormTemplate(unescape(element.attributeValue("form-template")));
		parseNodeCommonInfo(element, node);
		node.setSequenceFlows(parseFlowElement(element,processId,parseChildren));
		node.setFormElements(parseFormElements(element));
		node.setComponentAuthorities(parseComponentAuthorities(element));
		NodeDiagram diagram=parseDiagram(element);
		diagram.setIcon("/uflo/icons/start32.png");
		diagram.setShapeType(ShapeType.Circle);
		diagram.setBorderWidth(1);
		node.setDiagram(diagram);
		return node;
	}

	public boolean support(Element element) {
		return element.getName().equals("start");
	}
}
