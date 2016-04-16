package com.deloitte.tms.pl.workflow.deploy.validate.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.deloitte.tms.pl.workflow.process.node.AssignmentType;

/**
 * @author Jacky
 * @date 2013年9月15日
 */
public class TaskNodeValidator extends NodeValidator {

	@Override
	public void validate(Element element, List<String> errors,List<String> nodeNames) {
		super.validate(element, errors,nodeNames);
		String taskType=element.getAttribute("task-type");
		if(StringUtils.isEmpty(taskType)){
			errors.add("任务节点必须要定义任务类型");
		}
		String assignmentType=element.getAttribute("assignment-type");
		if(StringUtils.isEmpty(assignmentType)){
			errors.add("任务节点必须要定义任务的分配方式");			
		}
		AssignmentType at=AssignmentType.valueOf(assignmentType);
		if(at.equals(AssignmentType.Expression) && StringUtils.isEmpty(element.getAttribute("expression"))){
			errors.add("任务节点的任务分配方式为表达式时，必须要定义一个具体的表达式，如${startDate}");						
		}
		if(at.equals(AssignmentType.Handler) && StringUtils.isEmpty(element.getAttribute("assignment-handler-bean"))){
			errors.add("任务节点的任务分配方式为指定Bean时，必须要定义一个具体的实现了AssignmentHandler的类的Bean");						
		}
		if(at.equals(AssignmentType.Swimlane) && StringUtils.isEmpty(element.getAttribute("swimlane"))){
			errors.add("任务节点的任务分配方式为泳道时，必须要定义一个具体的泳道名称");						
		}
		if(at.equals(AssignmentType.Assignee)){
			boolean hasAssignee=false;
			NodeList nodeList=element.getChildNodes();
			for(int i=0;i<nodeList.getLength();i++){
				Node node=nodeList.item(i);
				if(!(node instanceof Element)){
					continue;
				}
				Element e=(Element)node;
				if(e.getNodeName().equals("assignee")){
					hasAssignee=true;
				}
			}
			if(!hasAssignee){
				errors.add("任务节点的任务分配方式为指定参与者时，至少要定义一个具体的参与者信息");										
			}
		}
		NodeList nodeList=element.getChildNodes();
		for(int i=0;i<nodeList.getLength();i++){
			boolean hasDue=false;
			Node node=nodeList.item(i);
			if(!(node instanceof Element)){
				continue;
			}
			Element e=(Element)node;
			if(e.getNodeName().equals("due")){
				hasDue=true;
			}
			if(hasDue){
				validateDue(e,errors);
			}
		}
		
	}
	
	private void validateDue(Element element,List<String> errors){
		NodeList nodeList=element.getChildNodes();
		for(int i=0;i<nodeList.getLength();i++){
			Node node=nodeList.item(i);
			if(!(node instanceof Element)){
				continue;
			}
			Element e=(Element)node;
			String reminder=null;
			String nodeName=e.getNodeName();
			if(nodeName.equals("once-reminder")){
				reminder="只提醒一次";
			}
			if(nodeName.equals("period-reminder")){
				reminder="周期性提醒";
			}
			if(reminder!=null){
				String handlerBean=e.getAttribute("handler-bean");
				if(StringUtils.isEmpty(handlerBean)){
					errors.add("过期提醒类型为"+reminder+"时，提醒Bean属性不能为空.");
					break;
				}
			}
		}
	}
	
	public boolean support(Element element) {
		return element.getNodeName().equals("task");
	}

	public String getNodeName() {
		return "人工任务";
	}
}
