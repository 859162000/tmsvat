package com.deloitte.tms.pl.workflow.console.rest.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Jacky.gao
 * @since 2013年9月21日
 */
@XmlRootElement(name="BatchCompleteTaskInfo")
public class BatchCompleteTaskInfo {
	private List<Long> taskIds=new ArrayList<Long>();
	private String opinion;
	private Map<String,Object> variables;
	public void addTaskId(long taskId){
		taskIds.add(taskId);
	}
	
	public List<Long> getTaskIds() {
		return taskIds;
	}

	public void setTaskIds(List<Long> taskIds) {
		this.taskIds = taskIds;
	}

	public String getOpinion() {
		return opinion;
	}
	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}
	
	public Map<String, Object> getVariables() {
		return variables;
	}
	public void setVariables(Map<String, Object> variables) {
		this.variables = variables;
	}
}
