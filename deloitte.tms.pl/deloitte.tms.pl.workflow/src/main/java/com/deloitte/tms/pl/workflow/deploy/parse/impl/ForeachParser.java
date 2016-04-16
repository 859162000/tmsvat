package com.deloitte.tms.pl.workflow.deploy.parse.impl;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Element;
import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.workflow.deploy.parse.AbstractParser;
import com.deloitte.tms.pl.workflow.diagram.NodeDiagram;
import com.deloitte.tms.pl.workflow.diagram.ShapeType;
import com.deloitte.tms.pl.workflow.process.node.ForeachNode;
import com.deloitte.tms.pl.workflow.process.node.ForeachType;

/**
 * @author Jacky.gao
 * @since 2013年8月13日
 */
@Component
public class ForeachParser extends AbstractParser {

	public Object parse(Element element, long processId, boolean parseChildren) {
		ForeachNode node=new ForeachNode();
		node.setProcessId(processId);
		parseNodeCommonInfo(element, node);
		node.setSequenceFlows(parseFlowElement(element,processId,parseChildren));
		String type=element.attributeValue("foreach-type");
		if(StringUtils.isNotEmpty(type)){
			node.setForeachType(ForeachType.valueOf(type));			
		}
		node.setVar(unescape(element.attributeValue("var")));
		node.setIn(unescape(element.attributeValue("in")));
		node.setHandlerBean(unescape(element.attributeValue("handler-bean")));
		NodeDiagram diagram=parseDiagram(element);
		diagram.setIcon("/uflo/icons/foreach32.png");
		diagram.setShapeType(ShapeType.Circle);
		diagram.setBorderWidth(1);
		node.setDiagram(diagram);
		return node;
	}

	public boolean support(Element element) {
		return element.getName().equals("foreach");
	}

}
