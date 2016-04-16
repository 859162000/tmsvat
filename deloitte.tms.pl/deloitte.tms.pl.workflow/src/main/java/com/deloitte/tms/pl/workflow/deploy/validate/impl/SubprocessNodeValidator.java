package com.deloitte.tms.pl.workflow.deploy.validate.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Element;

import com.deloitte.tms.pl.workflow.process.node.SubprocessType;

/**
 * @author Jacky
 * @date 2013年9月15日
 */
public class SubprocessNodeValidator extends NodeValidator {

	public void validate(Element element, List<String> errors,List<String> nodeNames) {
		super.validate(element, errors,nodeNames);
		String subprocessType=element.getAttribute("subprocess-type");
		if(StringUtils.isEmpty(subprocessType)){
			errors.add("子流程节点必须指定加载子流程方式");
		}else{
			SubprocessType type=SubprocessType.valueOf(subprocessType);
			if(type.equals(SubprocessType.Id) && StringUtils.isEmpty(element.getAttribute("subprocess-id"))){
				errors.add("子流程节点在指定加载子流程方式为子流程ID时，必须要设置具体的子流程ID");				
			}
			if(type.equals(SubprocessType.Name) && StringUtils.isEmpty(element.getAttribute("subprocess-name"))){
				errors.add("子流程节点在指定加载子流程方式为子流程名称时，必须要设置具体的子流程名称");				
			}
			if(type.equals(SubprocessType.Key) && StringUtils.isEmpty(element.getAttribute("subprocess-key"))){
				errors.add("子流程节点在指定加载子流程方式为子流程Key时，必须要设置具体的子流程Key");				
			}
		}
		
	}
	
	public boolean support(Element element) {
		return element.getNodeName().equals("subprocess");
	}

	public String getNodeName() {
		return "子流程";
	}
}
