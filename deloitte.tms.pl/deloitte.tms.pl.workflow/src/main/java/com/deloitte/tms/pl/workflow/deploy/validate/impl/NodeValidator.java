package com.deloitte.tms.pl.workflow.deploy.validate.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Element;

import com.deloitte.tms.pl.workflow.deploy.validate.Validator;

/**
 * @author Jacky
 * @date 2013年9月15日
 */
public abstract class NodeValidator implements Validator {

	public void validate(Element element, List<String> errors,List<String> nodeNames) {
		String name=element.getAttribute("name");
		if(StringUtils.isEmpty(name)){
			errors.add(getNodeName()+"节点未定义名称");
		}else{
			if(nodeNames.contains(name)){
				errors.add("有一个以上名为"+name+"的节点，在一个流程模版当中每个节点名都需要唯一");
			}else{
				nodeNames.add(name);
			}
		}
	}
}
