package com.deloitte.tms.pl.workflow.deploy.parse.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Element;
import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.workflow.deploy.parse.AbstractParser;
import com.deloitte.tms.pl.workflow.diagram.NodeDiagram;
import com.deloitte.tms.pl.workflow.diagram.ShapeType;
import com.deloitte.tms.pl.workflow.process.node.SubprocessNode;
import com.deloitte.tms.pl.workflow.process.node.SubprocessType;
import com.deloitte.tms.pl.workflow.process.node.SubprocessVariable;

/**
 * @author Jacky.gao
 * @since 2013年8月13日
 */
@Component
public class SubprocessParser extends AbstractParser {

	public Object parse(Element element, long processId, boolean parseChildren) {
		SubprocessNode node=new SubprocessNode();
		node.setProcessId(processId);
		parseNodeCommonInfo(element, node);
		node.setSequenceFlows(parseFlowElement(element,processId,parseChildren));
		String type=element.attributeValue("subprocess-type");
		if(StringUtils.isNotEmpty(type)){
			node.setSubprocessType(SubprocessType.valueOf(type));			
		}
		node.setSubprocessId(unescape(element.attributeValue("subprocess-id")));
		node.setSubprocessKey(unescape(element.attributeValue("subprocess-key")));
		node.setSubprocessName(unescape(element.attributeValue("subprocess-name")));
		List<SubprocessVariable> inVars=new ArrayList<SubprocessVariable>();
		List<SubprocessVariable> outVars=new ArrayList<SubprocessVariable>();
		for(Object obj:element.elements()){
			if(!(obj instanceof Element)){
				continue;
			}
			Element ele=(Element)obj;
			if(ele.getName().equals("in-subprocess-variable")){
				SubprocessVariable var=new SubprocessVariable(unescape(ele.attributeValue("in-parameter-key")),unescape(ele.attributeValue("out-parameter-key")));
				inVars.add(var);
			}
			if(ele.getName().equals("out-subprocess-variable")){
				SubprocessVariable var=new SubprocessVariable(unescape(ele.attributeValue("in-parameter-key")),unescape(ele.attributeValue("out-parameter-key")));
				outVars.add(var);
			}
		}
		node.setInVariables(inVars);
		node.setOutVariables(outVars);
		NodeDiagram diagram=parseDiagram(element);
		diagram.setIcon("/uflo/icons/subprocess32.png");
		diagram.setShapeType(ShapeType.Rectangle);
		diagram.setBorderWidth(1);
		diagram.setBorderColor("3, 104, 154");
		diagram.setBackgroundColor("250, 250, 250");
		node.setDiagram(diagram);
		return node;
	}

	public boolean support(Element element) {
		return element.getName().equals("subprocess");
	}

}
