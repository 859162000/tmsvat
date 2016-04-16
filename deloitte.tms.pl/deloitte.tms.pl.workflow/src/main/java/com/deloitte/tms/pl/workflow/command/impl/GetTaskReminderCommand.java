package com.deloitte.tms.pl.workflow.command.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.deloitte.tms.pl.workflow.command.Command;
import com.deloitte.tms.pl.workflow.env.Context;
import com.deloitte.tms.pl.workflow.model.task.reminder.TaskReminder;

/**
 * @author Jacky.gao
 * @since 2013年8月21日
 */
public class GetTaskReminderCommand implements Command<List<TaskReminder>> {
	private long taskId;
	public GetTaskReminderCommand(long taskId){
		this.taskId=taskId;
	}
	@SuppressWarnings("unchecked")
	public List<TaskReminder> execute(Context context) {
		Criteria criteria=context.getSession().createCriteria(TaskReminder.class);
		if(taskId>0){
			criteria.add(Restrictions.eq("taskId",taskId));
		}
		return criteria.list();
	}
}
