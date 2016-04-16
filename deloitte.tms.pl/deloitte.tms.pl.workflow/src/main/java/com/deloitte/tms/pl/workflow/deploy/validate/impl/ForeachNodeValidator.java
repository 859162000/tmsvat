package com.deloitte.tms.pl.workflow.deploy.validate.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Element;

import com.deloitte.tms.pl.workflow.process.node.ForeachType;

/**
 * @author Jacky
 * @date 2013年9月15日
 */
public class ForeachNodeValidator extends NodeValidator {

	@Override
	public void validate(Element element, List<String> errors,List<String> nodeNames) {
		super.validate(element, errors,nodeNames);
		String var=element.getAttribute("var");
		if(StringUtils.isEmpty(var)){
			errors.add("动态分支节点的写入分支变量名属性不能为空");
		}
		String type=element.getAttribute("foreach-type");
		if(StringUtils.isEmpty(type)){
			errors.add("动态分支节点的集合类型变量来源属性不能为空");			
		}else{
			ForeachType foreachType=ForeachType.valueOf(type);
			if(foreachType.equals(ForeachType.In) && StringUtils.isEmpty(element.getAttribute("in"))){
				errors.add("动态分支节点的集合类型变量来源为流程变量时，流程变量属性不能为空");							
			}
			if(foreachType.equals(ForeachType.Handler) && StringUtils.isEmpty(element.getAttribute("handler-bean"))){
				errors.add("动态分支节点的集合类型变量来源为实现类Bean时，实现类Bean属性不能为空");							
			}
		}
	}
	
	public boolean support(Element element) {
		return element.getNodeName().equals("foreach");
	}

	public String getNodeName() {
		return "动态分支";
	}
}
