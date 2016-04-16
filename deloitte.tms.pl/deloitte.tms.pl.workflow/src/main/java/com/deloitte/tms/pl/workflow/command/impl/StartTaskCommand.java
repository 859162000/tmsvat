package com.deloitte.tms.pl.workflow.command.impl;

import com.deloitte.tms.pl.workflow.command.Command;
import com.deloitte.tms.pl.workflow.env.Context;
import com.deloitte.tms.pl.workflow.model.task.Task;
import com.deloitte.tms.pl.workflow.model.task.TaskState;

/**
 * @author Jacky.gao
 * @since 2013年8月14日
 */
public class StartTaskCommand implements Command<Task> {
	private Task task;
	public StartTaskCommand(Task task){
		this.task=task;
	}
	public Task execute(Context context) {
		if(!task.getState().equals(TaskState.Created) && !task.getState().equals(TaskState.Reserved)){
			throw new IllegalStateException("Only state is Created or Reserved task can be start!");
		}
		task.setState(TaskState.InProgress);
		context.getSession().update(task);
		return task;
	}

}
