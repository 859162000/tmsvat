package com.deloitte.tms.pl.workflow.command.impl;

import org.apache.commons.lang.StringUtils;

import com.deloitte.tms.pl.workflow.command.Command;
import com.deloitte.tms.pl.workflow.env.Context;
import com.deloitte.tms.pl.workflow.model.task.Task;
import com.deloitte.tms.pl.workflow.model.task.TaskState;

/**
 * @author Jacky.gao
 * @since 2013年8月13日
 */
public class ReleaseTaskCommand implements Command<Task> {
	private Task task;
	public ReleaseTaskCommand(Task task){
		this.task=task;
	}
	public Task execute(Context context) {
		if(StringUtils.isEmpty(task.getAssignee())){
			throw new IllegalArgumentException("Current task ["+task.getTaskName()+"] no owner,can not be release!");
		}
		task.setState(TaskState.Ready);			
		task.setAssignee(null);
		context.getSession().update(task);
		return task;
	}
}
