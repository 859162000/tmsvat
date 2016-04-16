package com.deloitte.tms.pl.workflow.command.impl;

import com.deloitte.tms.pl.workflow.command.Command;
import com.deloitte.tms.pl.workflow.env.Context;
import com.deloitte.tms.pl.workflow.model.task.Task;

/**
 * @author Jacky.gao
 * @since 2013年7月31日
 */
public class GetTaskCommand implements Command<Task> {
	private long taskId;
	public GetTaskCommand(long taskId){
		this.taskId=taskId;
	}
	public Task execute(Context context) {
		Task task=(Task)context.getSession().get(Task.class,taskId);
		return task;
	}
}
