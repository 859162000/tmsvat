package com.deloitte.tms.pl.workflow.deploy.parse.impl;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Element;
import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.workflow.deploy.parse.AbstractParser;
import com.deloitte.tms.pl.workflow.diagram.NodeDiagram;
import com.deloitte.tms.pl.workflow.diagram.ShapeType;
import com.deloitte.tms.pl.workflow.process.node.EndNode;

/**
 * @author Jacky.gao
 * @since 2013年8月13日
 */
@Component
public class EndParser extends AbstractParser {

	public Object parse(Element element,long processId,boolean parseChildren) {
		EndNode node=new EndNode();
		node.setProcessId(processId);
		parseNodeCommonInfo(element, node);
		String terminate=element.attributeValue("terminate");
		if(StringUtils.isNotEmpty(terminate)){
			node.setTerminate(Boolean.valueOf(terminate));
		}
		NodeDiagram diagram=parseDiagram(element);
		if(node.isTerminate()){
			diagram.setIcon("/uflo/icons/end-terminate32.png");
		}else{
			diagram.setIcon("/uflo/icons/end32.png");
		}
		diagram.setShapeType(ShapeType.Circle);
		diagram.setBorderWidth(1);
		node.setDiagram(diagram);
		return node;
	}

	public boolean support(Element element) {
		return element.getName().equals("end");
	}
}
