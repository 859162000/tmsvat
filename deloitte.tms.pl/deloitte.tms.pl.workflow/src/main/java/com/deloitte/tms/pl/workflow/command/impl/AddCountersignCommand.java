package com.deloitte.tms.pl.workflow.command.impl;

import java.util.Date;

import org.hibernate.Session;

import com.deloitte.tms.pl.workflow.command.Command;
import com.deloitte.tms.pl.workflow.env.Context;
import com.deloitte.tms.pl.workflow.model.task.Task;
import com.deloitte.tms.pl.workflow.model.task.TaskState;
import com.deloitte.tms.pl.workflow.model.task.TaskType;
import com.deloitte.tms.pl.workflow.query.TaskQuery;
import com.deloitte.tms.pl.workflow.utils.IDGenerator;

/**
 * @author Jacky.gao
 * @since 2013年10月15日
 */
public class AddCountersignCommand implements Command<Task> {
	private Task task;
	private String username;
	public AddCountersignCommand(Task task,String username){
		this.task=task;
		this.username=username;
	}
	public Task execute(Context context) {
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
			t.setCountersignCount(count+1);
			session.update(t);
		}
		Task newTask=new Task();
		newTask.setAssignee(username);
		newTask.setBusinessId(task.getBusinessId());
		newTask.setCountersignCount(count+1);
		newTask.setCreateDate(new Date());
		newTask.setDateUnit(task.getDateUnit());
		newTask.setDescription(task.getDescription());
		newTask.setDuedate(task.getDuedate());
		newTask.setId(IDGenerator.getInstance().nextId());
		newTask.setNodeName(task.getNodeName());
		newTask.setOwner(task.getOwner());
		newTask.setPrevTask(task.getPrevTask());
		newTask.setProcessId(task.getProcessId());
		newTask.setProcessInstanceId(task.getProcessInstanceId());
		newTask.setState(TaskState.Created);
		newTask.setRootProcessInstanceId(task.getRootProcessInstanceId());
		newTask.setTaskName(task.getTaskName());
		newTask.setType(task.getType());
		newTask.setUrl(task.getUrl());
		session.save(newTask);
		return newTask;
	}
}
