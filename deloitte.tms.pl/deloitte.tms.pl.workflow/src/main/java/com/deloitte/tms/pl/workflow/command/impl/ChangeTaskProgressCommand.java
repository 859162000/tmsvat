package com.deloitte.tms.pl.workflow.command.impl;

import com.deloitte.tms.pl.workflow.command.Command;
import com.deloitte.tms.pl.workflow.env.Context;
import com.deloitte.tms.pl.workflow.model.task.Task;

/**
 * @author Jacky.gao
 * @since 2013年11月23日
 */
public class ChangeTaskProgressCommand implements Command<Task> {
	private Task task;
	private int progress;
	public ChangeTaskProgressCommand(Task task,int progress){
		this.task=task;
		this.progress=progress;
	}
	public Task execute(Context context) {
		task.setProgress(progress);
		context.getSession().update(task);
		return task;
	}
}
