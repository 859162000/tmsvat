package com.deloitte.tms.pl.workflow.command.impl;

import org.apache.commons.lang.StringUtils;

import com.deloitte.tms.pl.workflow.command.Command;
import com.deloitte.tms.pl.workflow.env.Context;
import com.deloitte.tms.pl.workflow.model.task.Task;
import com.deloitte.tms.pl.workflow.model.task.TaskState;
import com.deloitte.tms.pl.workflow.model.task.TaskType;

/**
 * @author Jacky.gao
 * @since 2013年8月13日
 */
public class ClaimTaskCommand implements Command<Task> {
	private Task task;
	private String user;
	public ClaimTaskCommand(Task task,String user){
		this.task=task;
		this.user=user;
	}
	public Task execute(Context context) {
		if(!task.getType().equals(TaskType.Participative)){
			throw new IllegalStateException("Current task ["+task.getTaskName()+"] is not a participative task!");
		}
		if(StringUtils.isNotEmpty(task.getAssignee())){
			throw new IllegalStateException("Current task ["+task.getTaskName()+"] has belonged to ["+task.getAssignee()+"].");
		}
		task.setState(TaskState.Reserved);
		task.setAssignee(user);
		context.getSession().update(task);
		return task;
	}
}
