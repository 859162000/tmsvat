package com.deloitte.tms.pl.workflow.command.impl;

import com.deloitte.tms.pl.workflow.command.Command;
import com.deloitte.tms.pl.workflow.env.Context;
import com.deloitte.tms.pl.workflow.model.task.Task;
import com.deloitte.tms.pl.workflow.model.task.TaskState;

/**
 * @author Jacky.gao
 * @since 2013年8月14日
 */
public class ResumeTaskCommand implements Command<Task> {
	private Task task;
	public ResumeTaskCommand(Task task){
		this.task=task;
	}
	public Task execute(Context context) {
		if(!task.getState().equals(TaskState.Suspended)){
			throw new IllegalStateException("Only state is suspended task can be start!");
		}
		return null;
	}

}
