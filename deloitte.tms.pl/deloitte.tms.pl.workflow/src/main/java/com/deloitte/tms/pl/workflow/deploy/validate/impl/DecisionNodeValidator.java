package com.deloitte.tms.pl.workflow.deploy.validate.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Element;

import com.deloitte.tms.pl.workflow.process.node.DecisionType;

/**
 * @author Jacky
 * @date 2013年9月15日
 */
public class DecisionNodeValidator extends NodeValidator {

	@Override
	public void validate(Element element, List<String> errors,List<String> nodeNames) {
		super.validate(element, errors,nodeNames);
		String type=element.getAttribute("decision-type");
		if(StringUtils.isEmpty(type)){
			errors.add("路由决策节点必须要指定决策条件判断方式");
		}else{
			DecisionType decisionType=DecisionType.valueOf(type);
			if(decisionType.equals(DecisionType.Expression) && StringUtils.isEmpty(element.getAttribute("expression"))){
				errors.add("路由决策节点条件判断方式表达式时，必须要指定一个具体表达式");				
			}
			if(decisionType.equals(DecisionType.Handler) && StringUtils.isEmpty(element.getAttribute("handler-bean"))){
				errors.add("路由决策节点条件判断方式实现类Bean时，必须要指定一个具体实现类Bean");				
			}
		}
	}
	
	public boolean support(Element element) {
		return element.getNodeName().equals("decision");
	}

	public String getNodeName() {
		return "路由决策";
	}
}
