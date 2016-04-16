package com.deloitte.tms.pl.workflow.command.impl;

import com.deloitte.tms.pl.workflow.command.Command;
import com.deloitte.tms.pl.workflow.env.Context;
import com.deloitte.tms.pl.workflow.model.task.reminder.TaskReminder;

/**
 * @author Jacky.gao
 * @since 2013年8月21日
 */
public class DeleteTaskReminderCommand implements Command<Object> {
	private long taskReminderId;
	public DeleteTaskReminderCommand(long taskReminderId){
		this.taskReminderId=taskReminderId;
	}
	public Object execute(Context context) {
		context.getSession().createQuery("delete "+TaskReminder.class.getName()+" where id=:id").setLong("id", taskReminderId).executeUpdate();
		return null;
	}
}
