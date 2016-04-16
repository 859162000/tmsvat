package com.deloitte.tms.pl.workflow.command.impl;

import org.hibernate.criterion.Restrictions;

import com.deloitte.tms.pl.workflow.command.Command;
import com.deloitte.tms.pl.workflow.env.Context;
import com.deloitte.tms.pl.workflow.model.HistoryTask;

public class GetHistoryTaskCommand implements Command<HistoryTask> {
	private long taskId;
	public GetHistoryTaskCommand(long taskId){
		this.taskId=taskId;
	}
	public HistoryTask execute(Context context) {
		return (HistoryTask)context.getSession().createCriteria(HistoryTask.class).add(Restrictions.eq("taskId", taskId)).uniqueResult();
	}
}
