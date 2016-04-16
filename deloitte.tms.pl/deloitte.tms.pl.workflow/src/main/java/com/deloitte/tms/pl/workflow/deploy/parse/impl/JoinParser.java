package com.deloitte.tms.pl.workflow.deploy.parse.impl;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Element;
import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.workflow.deploy.parse.AbstractParser;
import com.deloitte.tms.pl.workflow.diagram.NodeDiagram;
import com.deloitte.tms.pl.workflow.diagram.ShapeType;
import com.deloitte.tms.pl.workflow.process.node.JoinNode;

/**
 * @author Jacky.gao
 * @since 2013年8月13日
 */
@Component
public class JoinParser extends AbstractParser {

	public Object parse(Element element, long processId, boolean parseChildren) {
		JoinNode node=new JoinNode();
		node.setProcessId(processId);
		parseNodeCommonInfo(element, node);
		node.setSequenceFlows(parseFlowElement(element,processId,parseChildren));
		String multiplicity=element.attributeValue("multiplicity");
		if(StringUtils.isNotEmpty(multiplicity)){
			int mul=Integer.parseInt(multiplicity);
			if(mul>0){
				node.setMultiplicity(mul);
			}
		}
		String percentMultiplicity=element.attributeValue("percent-multiplicity");
		if(StringUtils.isNotEmpty(percentMultiplicity)){
			int mul=Integer.parseInt(percentMultiplicity);
			if(mul>0){
				node.setPercentMultiplicity(mul);
			}
		}
		NodeDiagram diagram=parseDiagram(element);
		diagram.setIcon("/uflo/icons/join32.png");
		diagram.setShapeType(ShapeType.Circle);
		diagram.setBorderWidth(1);
		node.setDiagram(diagram);
		return node;
	}

	public boolean support(Element element) {
		return element.getName().equals("join");
	}

}
