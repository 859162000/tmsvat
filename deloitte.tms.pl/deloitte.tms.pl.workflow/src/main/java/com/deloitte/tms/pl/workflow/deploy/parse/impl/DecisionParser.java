package com.deloitte.tms.pl.workflow.deploy.parse.impl;

import org.dom4j.Element;
import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.workflow.deploy.parse.AbstractParser;
import com.deloitte.tms.pl.workflow.diagram.NodeDiagram;
import com.deloitte.tms.pl.workflow.diagram.ShapeType;
import com.deloitte.tms.pl.workflow.process.node.DecisionNode;
import com.deloitte.tms.pl.workflow.process.node.DecisionType;

/**
 * @author Jacky.gao
 * @since 2013年8月13日
 */
@Component
public class DecisionParser extends AbstractParser {

	public Object parse(Element element, long processId, boolean parseChildren) {
		DecisionNode node=new DecisionNode();
		node.setProcessId(processId);
		parseNodeCommonInfo(element, node);
		node.setSequenceFlows(parseFlowElement(element,processId,parseChildren));
		node.setDecisionType(DecisionType.valueOf(element.attributeValue("decision-type")));
		node.setExpression(unescape(element.attributeValue("expression")));
		node.setHandlerBean(unescape(element.attributeValue("handler-bean")));
		NodeDiagram diagram=parseDiagram(element);
		diagram.setIcon("/uflo/icons/decision32.png");
		diagram.setShapeType(ShapeType.Circle);
		diagram.setBorderWidth(1);
		node.setDiagram(diagram);
		return node;
	}

	public boolean support(Element element) {
		return element.getName().equals("decision");
	}

}
