package com.deloitte.tms.pl.workflow.console.rest.model;

import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Jacky.gao
 * @since 2013年9月21日
 */
@XmlRootElement(name="CompleteTaskInfo")
public class CompleteTaskInfo {
	private long taskId;
	private String flowName;
	private String targetNodeName;
	private String opinion;
	private Map<String,Object> variables;
	public long getTaskId() {
		return taskId;
	}
	public void setTaskId(long taskId) {
		this.taskId = taskId;
	}
	public String getFlowName() {
		return flowName;
	}
	public void setFlowName(String flowName) {
		this.flowName = flowName;
	}
	
	public String getOpinion() {
		return opinion;
	}
	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}
	public String getTargetNodeName() {
		return targetNodeName;
	}
	public void setTargetNodeName(String targetNodeName) {
		this.targetNodeName = targetNodeName;
	}
	public Map<String, Object> getVariables() {
		return variables;
	}
	public void setVariables(Map<String, Object> variables) {
		this.variables = variables;
	}
}
