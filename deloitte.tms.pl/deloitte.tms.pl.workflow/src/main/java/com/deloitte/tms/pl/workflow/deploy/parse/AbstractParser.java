package com.deloitte.tms.pl.workflow.deploy.parse;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.dom4j.Element;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.apache.commons.lang.StringUtils;

import com.deloitte.tms.pl.workflow.deploy.StringTools;
import com.deloitte.tms.pl.workflow.diagram.NodeDiagram;
import com.deloitte.tms.pl.workflow.process.assign.Assignee;
import com.deloitte.tms.pl.workflow.process.flow.SequenceFlowImpl;
import com.deloitte.tms.pl.workflow.process.node.FormElement;
import com.deloitte.tms.pl.workflow.process.node.Mapping;
import com.deloitte.tms.pl.workflow.process.node.Node;
import com.deloitte.tms.pl.workflow.process.node.UserData;
import com.deloitte.tms.pl.workflow.process.security.Authority;


/**
 * @author Jacky.gao
 * @since 2013年7月30日
 */
public abstract class AbstractParser implements Parser,ApplicationContextAware {
	protected Collection<Parser> parsers;
	
	protected String unescape(String str){
		if(StringUtils.isEmpty(str))return str;
		return StringTools.unescape(str);
	}
	
	protected NodeDiagram parseDiagram(Element element){
		NodeDiagram diagram=new NodeDiagram();
		String g=element.attributeValue("g");
		if(StringUtils.isEmpty(g))return diagram;
		String[] info=g.split(",");
		String name=element.attributeValue("name");
		String label=element.attributeValue("label");
		if(info.length!=4){
			throw new IllegalArgumentException("Node "+element.attributeValue("name")+" diagram info is invalide!");
		}
		diagram.setX(Integer.valueOf(info[0]));
		diagram.setY(Integer.valueOf(info[1]));
		diagram.setWidth(Integer.valueOf(info[2]));
		diagram.setHeight(Integer.valueOf(info[3]));
		diagram.setLabel(label);			
		diagram.setName(name);
		return diagram;
	}
	
	protected void parseNodeCommonInfo(Element element, Node node) {
		node.setName(unescape(element.attributeValue("name")));
		String label=element.attributeValue("label");
		if(StringUtils.isNotEmpty(label)){
			node.setLabel(unescape(label));
		}
		Element desc=element.element("description");
		if(desc!=null){
			node.setDescription(unescape(desc.getTextTrim()));
		}
		node.setEventHandlerBean(unescape(element.attributeValue("event-handler-bean")));
	}
	
	protected List<Assignee> parserAssignees(Element element){
		List<Assignee> assignees=new ArrayList<Assignee>();
		for(Object obj:element.elements()){
			if(!(obj instanceof Element)){
				continue;
			}
			Element ele=(Element)obj;
			if(!ele.getName().equals("assignee")){
				continue;
			}
			String id=unescape(ele.attributeValue("id"));
			String name=unescape(ele.attributeValue("name"));
			String providerId=unescape(ele.attributeValue("provider-id"));
			Assignee assignee=new Assignee();
			assignee.setId(id);
			assignee.setName(name);
			assignee.setProviderId(providerId);
			assignees.add(assignee);
		}
		return assignees;
	}
	
	protected List<UserData> parseUserData(Element element){
		List<UserData> data=new ArrayList<UserData>();
		for(Object object:element.elements()){
			if(!(object instanceof Element))continue;
			Element ele=(Element)object;
			if(!ele.getName().equals("user-data"))continue;
			data.add(new UserData(ele.attributeValue("key"),ele.attributeValue("value")));
		}
		return data;
	}
	
	protected List<FormElement> parseFormElements(Element element){
		List<FormElement> formElements=new ArrayList<FormElement>();
		for(Object object:element.elements()){
			if(!(object instanceof Element))continue;
			Element ele=(Element)object;
			if(!ele.getName().equals("form-element"))continue;
			FormElement formElement=new FormElement();
			formElement.setName(ele.attributeValue("name"));
			formElement.setCaption(ele.attributeValue("caption"));
			formElement.setDataType(ele.attributeValue("data-type"));
			formElement.setDefaultValue(ele.attributeValue("default-value"));
			formElement.setEditorType(ele.attributeValue("editor-type"));
			formElement.setRequired(Boolean.valueOf(ele.attributeValue("required")));
			formElement.setAuthority(Authority.valueOf(ele.attributeValue("authority")));
			List<Mapping> mappings=null;
			for(Object obj:ele.elements()){
				if(!(obj instanceof Element)){
					continue;
				}
				Element mappingElement=(Element)obj;
				if(!mappingElement.getName().equals("mapping")){
					continue;
				}
				if(mappings==null){
					mappings=new ArrayList<Mapping>();
				}
				Mapping mapping=new Mapping();
				mapping.setKey(mappingElement.attributeValue("key"));
				mapping.setLabel(mappingElement.attributeValue("label"));
				mappings.add(mapping);
			}
			formElement.setMappings(mappings);
			formElements.add(formElement);
		}
		return formElements;
	}
	
	protected List<SequenceFlowImpl> parseFlowElement(Element element,long processId,boolean parseChildren){
		List<SequenceFlowImpl> flows=new ArrayList<SequenceFlowImpl>();
		for(Object obj:element.elements()){
			if(!(obj instanceof Element)){
				continue;
			}
			Element ele=(Element)obj;
			for(Parser parser:parsers){
				if(!parser.support(ele)){
					continue;
				}
				Object processElement=parser.parse(ele,processId,parseChildren);
				if(processElement instanceof SequenceFlowImpl){
					SequenceFlowImpl flow=(SequenceFlowImpl)processElement;
					flows.add(flow);
				}
				break;
			}
		}
		return flows;
	}
	
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		parsers=applicationContext.getBeansOfType(Parser.class).values();
	}
}
