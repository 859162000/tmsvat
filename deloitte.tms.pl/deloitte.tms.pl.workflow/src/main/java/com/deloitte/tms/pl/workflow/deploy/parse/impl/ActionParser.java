package com.deloitte.tms.pl.workflow.deploy.parse.impl;

import org.dom4j.Element;
import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.workflow.deploy.parse.AbstractParser;
import com.deloitte.tms.pl.workflow.diagram.NodeDiagram;
import com.deloitte.tms.pl.workflow.diagram.ShapeType;
import com.deloitte.tms.pl.workflow.process.node.ActionNode;

/**
 * @author Jacky.gao
 * @since 2013年8月13日
 */
@Component
public class ActionParser extends AbstractParser {

	public Object parse(Element element, long processId, boolean parseChildren) {
		ActionNode node=new ActionNode();
		node.setProcessId(processId);
		parseNodeCommonInfo(element, node);
		node.setSequenceFlows(parseFlowElement(element,processId,parseChildren));
		node.setHandlerBean(unescape(element.attributeValue("handler-bean")));
		NodeDiagram diagram=parseDiagram(element);
		diagram.setIcon("/uflo/icons/action32.png");
		diagram.setShapeType(ShapeType.Rectangle);
		diagram.setBorderWidth(1);
		diagram.setBorderColor("3, 104, 154");
		diagram.setBackgroundColor("250, 250, 250");
		node.setDiagram(diagram);
		return node;
	}

	public boolean support(Element element) {
		return element.getName().equals("action");
	}
}
