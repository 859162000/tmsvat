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
 * @since 2013年9月24日
 */
public class WithdrawTaskCommand implements Command<Object> {
	private Map<String, Object> variables;
	private Task task;
	private TaskOpinion opinion;
	public WithdrawTaskCommand(Task task,Map<String, Object> variables,TaskOpinion opinion){
		this.variables=variables;
		this.opinion=opinion;
		this.task=task;
	}
	public Object execute(Context context) {
		TaskService taskService=context.getTaskService();
		if(!taskService.canWithdraw(task)){
			throw new IllegalArgumentException("Task "+task.getTaskName()+" can not forward to "+task.getPrevTask()+" node.");
		}
		task.setState(TaskState.Canceled);
		taskService.forward(task, task.getPrevTask(), variables,opinion,TaskState.Withdraw);
		return null;
	}
}
