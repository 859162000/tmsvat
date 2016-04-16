package com.deloitte.tms.pl.workflow.command.impl;

import java.util.Map;

import com.deloitte.tms.pl.workflow.command.Command;
import com.deloitte.tms.pl.workflow.env.Context;
import com.deloitte.tms.pl.workflow.model.task.Task;
import com.deloitte.tms.pl.workflow.model.task.TaskState;
import com.deloitte.tms.pl.workflow.service.TaskOpinion;
import com.deloitte.tms.pl.workflow.service.TaskService;


/**
 * @author Jacky.gao
 * @since 2013年10月12日
 */
public class RollbackTaskCommand implements Command<Object> {
	private Map<String, Object> variables;
	private Task task;
	private TaskOpinion opinion;
	private String targetNodeName;
	public RollbackTaskCommand(Task task,String targetNodeName,Map<String, Object> variables,TaskOpinion opinion){
		this.variables=variables;
		this.opinion=opinion;
		this.task=task;
		this.targetNodeName=targetNodeName;
	}
	public Object execute(Context context) {
		TaskService taskService=context.getTaskService();
		task.setState(TaskState.Rollback);
		taskService.forward(task, targetNodeName, variables,opinion,TaskState.Rollback);
		return null;
	}
}
