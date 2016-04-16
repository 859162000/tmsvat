package com.deloitte.tms.pl.workflow.command.impl;

import org.hibernate.Session;

import com.deloitte.tms.pl.workflow.command.Command;
import com.deloitte.tms.pl.workflow.env.Context;
import com.deloitte.tms.pl.workflow.model.task.Task;
import com.deloitte.tms.pl.workflow.model.task.TaskType;
import com.deloitte.tms.pl.workflow.query.TaskQuery;

/**
 * @author Jacky.gao
 * @since 2013年10月15日
 */
public class DeleteCountersignCommand implements Command<Object> {
	private Task task;
	public DeleteCountersignCommand(Task task){
		this.task=task;
	}
	public Object execute(Context context) {
		if(!TaskType.Countersign.equals(task.getType())){
			throw new IllegalArgumentException("Task "+task.getId()+" is not a countersign task.");
		}
		Session session=context.getSession();
		TaskQuery query=context.getTaskService().createTaskQuery();
		query.processInstanceId(task.getProcessInstanceId());
		query.nodeName(task.getNodeName());
		int count=0;
		for(Task t:query.list()){
			count=t.getCountersignCount();
			t.setCountersignCount(count-1);
			session.update(t);
		}
		session.delete(task);
		return null;
	}
}
