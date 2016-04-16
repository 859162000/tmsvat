package com.deloitte.tms.pl.workflow.command.impl;

import org.apache.commons.lang.StringUtils;

import com.deloitte.tms.pl.workflow.command.Command;
import com.deloitte.tms.pl.workflow.env.Context;
import com.deloitte.tms.pl.workflow.model.task.Task;

/**
 * @author Jacky.gao
 * @since 2013年10月10日
 */
public class ChangeTaskAssigneeCommand implements Command<Object> {
	private long taskId;
	private String username;
	public ChangeTaskAssigneeCommand(long taskId,String username){
		this.taskId=taskId;
		this.username=username;
	}
	public Object execute(Context context) {
		Task task=context.getTaskService().getTask(taskId);
		if(StringUtils.isNotEmpty(task.getOwner())){
			task.setAssignee(username);
			context.getSession().update(task);
		}else{
			throw new IllegalArgumentException("Task "+taskId+" is not the owner,So can not change task assignee.");
		}
		return null;
	}
}
