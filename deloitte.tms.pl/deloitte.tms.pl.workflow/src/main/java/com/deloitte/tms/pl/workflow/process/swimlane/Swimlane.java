package com.deloitte.tms.pl.workflow.process.swimlane;

import java.util.List;

import com.deloitte.tms.pl.workflow.process.assign.Assignee;
import com.deloitte.tms.pl.workflow.process.node.AssignmentType;


/**
 * @author Jacky.gao
 * @since 2013年8月12日
 */
public class Swimlane {
	private String name;
	private String description;
	private AssignmentType assignmentType;
	private List<Assignee> assignees;
	private String expression;
	private String assignmentHandlerBean;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Assignee> getAssignees() {
		return assignees;
	}
	public void setAssignees(List<Assignee> assignees) {
		this.assignees = assignees;
	}
	public String getAssignmentHandlerBean() {
		return assignmentHandlerBean;
	}
	public void setAssignmentHandlerBean(String assignmentHandlerBean) {
		this.assignmentHandlerBean = assignmentHandlerBean;
	}
	public AssignmentType getAssignmentType() {
		return assignmentType;
	}
	public void setAssignmentType(AssignmentType assignmentType) {
		this.assignmentType = assignmentType;
	}
	public String getExpression() {
		return expression;
	}
	public void setExpression(String expression) {
		this.expression = expression;
	}
}
