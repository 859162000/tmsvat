package com.deloitte.tms.pl.workflow.command.impl;

import java.util.List;

import com.deloitte.tms.pl.workflow.command.Command;
import com.deloitte.tms.pl.workflow.env.Context;

/**
 * @author Jacky.gao
 * @since 2013年10月9日
 */
public class BatchStartTasksCommand implements Command<Object> {
	private List<Long> taskIds;
	public BatchStartTasksCommand(List<Long> taskIds){
		this.taskIds=taskIds;
	}
	public Object execute(Context context) {
		for(long taskId:taskIds){
			context.getTaskService().start(taskId);
		}
		return null;
	}
}
