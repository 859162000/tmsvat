package com.deloitte.tms.pl.workflow.command.impl;

import java.util.List;

import org.hibernate.criterion.Restrictions;

import com.deloitte.tms.pl.workflow.command.Command;
import com.deloitte.tms.pl.workflow.env.Context;
import com.deloitte.tms.pl.workflow.model.task.TaskParticipator;

/**
 * @author Jacky.gao
 * @since 2013年9月10日
 */
public class GetTaskParticipatorsCommand implements Command<List<TaskParticipator>> {
	private long taskId;
	public GetTaskParticipatorsCommand(long taskId){
		this.taskId=taskId;
	}
	@SuppressWarnings("unchecked")
	public List<TaskParticipator> execute(Context context) {
		return context.getSession().createCriteria(TaskParticipator.class).add(Restrictions.eq("taskId", taskId)).list();
	}

}
