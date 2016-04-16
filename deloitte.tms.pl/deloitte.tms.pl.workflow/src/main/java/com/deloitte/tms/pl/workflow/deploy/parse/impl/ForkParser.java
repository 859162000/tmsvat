package com.deloitte.tms.pl.workflow.deploy.parse.impl;

import org.dom4j.Element;
import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.workflow.deploy.parse.AbstractParser;
import com.deloitte.tms.pl.workflow.diagram.NodeDiagram;
import com.deloitte.tms.pl.workflow.diagram.ShapeType;
import com.deloitte.tms.pl.workflow.process.node.ForkNode;

/**
 * @author Jacky.gao
 * @since 2013年8月15日
 */
@Component
public class ForkParser extends AbstractParser {

	public Object parse(Element element, long processId, boolean parseChildren) {
		ForkNode node=new ForkNode();
		node.setProcessId(processId);
		parseNodeCommonInfo(element, node);
		node.setSequenceFlows(parseFlowElement(element,processId,parseChildren));
		NodeDiagram diagram=parseDiagram(element);
		diagram.setIcon("/uflo/icons/fork32.png");
		diagram.setShapeType(ShapeType.Circle);
		diagram.setBorderWidth(1);
		node.setDiagram(diagram);
		return node;
	}

	public boolean support(Element element) {
		return element.getName().equals("fork");
	}

}
