package com.deloitte.tms.pl.workflow.deploy.validate.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.deloitte.tms.pl.workflow.deploy.validate.Validator;

/**
 * @author Jacky
 * @date 2013年9月15日
 */
@Component
public class ProcessValidator implements Validator {
	private List<Validator> validators;
	public ProcessValidator(){
		validators=new ArrayList<Validator>();
		validators.add(new ActionNodeValidator());
		validators.add(new DecisionNodeValidator());
		validators.add(new EndNodeValidator());
		validators.add(new ForeachNodeValidator());
		validators.add(new ForkNodeValidator());
		validators.add(new StartNodeValidator());
		validators.add(new SubprocessNodeValidator());
		validators.add(new SwimlaneValidator());
		validators.add(new TaskNodeValidator());
	}
	public void validate(Element element, List<String> errors,List<String> nodeNames) {
		boolean hasStart=false;
		boolean hasEnd=false;
		String name=element.getAttribute("name");
		if(StringUtils.isEmpty(name)){
			errors.add("流程模版未定义名称");
		}
		NodeList nodeList=element.getChildNodes();
		for(int i=0;i<nodeList.getLength();i++){
			Node node=nodeList.item(i);
			if(!(node instanceof Element)){
				continue;
			}
			Element childElement=(Element)node;
			for(Validator validator:validators){
				if(validator.support(childElement)){
					if(validator instanceof StartNodeValidator){
						hasStart=true;
					}
					if(validator instanceof EndNodeValidator){
						hasEnd=true;
					}
					validator.validate(childElement, errors,nodeNames);
				}
			}
		}
		if(!hasStart){
			errors.add("流程模版中未定义开始节点。");				
		}
		if(!hasEnd){
			errors.add("流程模版中未定义结束节点。");				
		}
	}

	public boolean support(Element element) {
		return element.getNodeName().equals("uflo-process");
	}
	
	public String getNodeName() {
		return "流程模版";
	}
}
