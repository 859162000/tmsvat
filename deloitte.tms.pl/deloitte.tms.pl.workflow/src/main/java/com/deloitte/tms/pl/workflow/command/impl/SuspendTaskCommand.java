package com.deloitte.tms.pl.workflow.command.impl;

import com.deloitte.tms.pl.workflow.command.Command;
import com.deloitte.tms.pl.workflow.env.Context;
import com.deloitte.tms.pl.workflow.model.task.Task;
import com.deloitte.tms.pl.workflow.model.task.TaskState;

/**
 * @author Jacky.gao
 * @since 2013年8月14日
 */
public class SuspendTaskCommand implements Command<Task> {
	private Task task;
	public SuspendTaskCommand(Task task){
		this.task=task;
	}
	public Task execute(Context context) {
		TaskState state=task.getState();
		if(!state.equals(TaskState.Ready) && !state.equals(TaskState.InProgress) && !state.equals(TaskState.Reserved)){
			throw new IllegalStateException("Only state is ready,reserved or inProgress task can be suspend!");
		}
		task.setPrevState(state);
		task.setState(TaskState.Suspended);
		context.getSession().update(task);
		return null;
	}

}
