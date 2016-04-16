package com.deloitte.tms.pl.workflow.deploy.parse.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Element;
import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.workflow.deploy.parse.Parser;
import com.deloitte.tms.pl.workflow.diagram.Point;
import com.deloitte.tms.pl.workflow.diagram.SequenceFlowDiagram;
import com.deloitte.tms.pl.workflow.process.flow.ConditionType;
import com.deloitte.tms.pl.workflow.process.flow.SequenceFlowImpl;

/**
 * @author Jacky.gao
 * @since 2013年8月5日
 */
@Component
public class SequenceFlowParser implements Parser {

	public Object parse(Element element,long processId,boolean parseChildren) {
		SequenceFlowImpl flow=new SequenceFlowImpl();
		flow.setProcessId(processId);
		flow.setName(unescape(element.attributeValue("name")));
		flow.setToNode(unescape((element.attributeValue("to"))));
		String conditionType=element.attributeValue("condition-type");
		if(StringUtils.isNotEmpty(conditionType)){
			flow.setConditionType(ConditionType.valueOf(conditionType));			
			flow.setExpression(element.attributeValue("expression"));
			flow.setHandlerBean(element.attributeValue("handler-bean"));
		}
		flow.setDiagram(parseDiagram(element));
		return flow;
	}

	private SequenceFlowDiagram parseDiagram(Element element){
		SequenceFlowDiagram diagram=new SequenceFlowDiagram();
		diagram.setBorderColor("0,69,123");
		diagram.setFontColor("0,69,123");
		diagram.setBorderWidth(2);
		String name=element.attributeValue("name");
		diagram.setTo(element.attributeValue("to"));
		diagram.setName(name);
		String g=element.attributeValue("g");
		if(StringUtils.isEmpty(g))return diagram;
		String[] pointInfos=null;
		if(org.apache.commons.lang.StringUtils.isNotEmpty(name)){
			String[] info=g.split(":");
			if(info.length==1){
				diagram.setLabelPosition(info[0]);				
				return diagram;
			}
			pointInfos=info[0].split(";");
			diagram.setLabelPosition(info[1]);				
		}else{
			String[] info=g.split(":");
			if(info.length==0){
				pointInfos=g.split(";");
			}else{
				pointInfos=info[0].split(";");
			}
			if(pointInfos.length==0){
				return diagram;
			}
		}
		diagram.setPoints(buildPoint(pointInfos));
		return diagram;
	}
	
	private List<Point> buildPoint(String[] info){
		List<Point> points=new ArrayList<Point>();
		for(String diagram:info){
			String[] d=diagram.split(",");
			if(StringUtils.isEmpty(d[0])){
				continue;
			}
			Point point=new Point();
			point.setX(Integer.valueOf(d[0]));
			point.setY(Integer.valueOf(d[1]));
			points.add(point);
		}
		return points;
	}
	
	public boolean support(Element element) {
		return element.getName().equals("sequence-flow");
	}
	
	protected String unescape(String str){
		if(StringUtils.isEmpty(str))return str;
		str=StringEscapeUtils.escapeXml(str);
		return StringEscapeUtils.unescapeXml(str);
	}
}
