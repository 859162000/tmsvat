package com.deloitte.tms.pl.workflow.command.impl;

import java.util.Date;

import org.hibernate.Session;

import com.deloitte.tms.pl.workflow.command.Command;
import com.deloitte.tms.pl.workflow.env.Context;
import com.deloitte.tms.pl.workflow.model.HistoryTask;
import com.deloitte.tms.pl.workflow.model.ProcessInstance;
import com.deloitte.tms.pl.workflow.model.task.Task;
import com.deloitte.tms.pl.workflow.utils.IDGenerator;

/**
 * @author Jacky.gao
 * @since 2013年7月31日
 */
public class SaveHistoryTaskCommand implements Command<HistoryTask> {
	private Task task;
	private ProcessInstance processInstance;
	public SaveHistoryTaskCommand(Task task,ProcessInstance processInstance){
		this.task=task;
		this.processInstance=processInstance;
	}
	public HistoryTask execute(Context context) {
		Session session=context.getSession();
		HistoryTask hisTask=new HistoryTask();
		hisTask.setId(IDGenerator.getInstance().nextId());
		hisTask.setDescription(task.getDescription());
		hisTask.setProcessId(task.getProcessId());
		hisTask.setHistoryProcessInstanceId(processInstance.getHistoryProcessInstanceId());
		hisTask.setCreateDate(task.getCreateDate());
		hisTask.setProcessInstanceId(processInstance.getRootId());
		hisTask.setRootProcessInstanceId(processInstance.getRootId());
		hisTask.setTaskName(task.getTaskName());
		hisTask.setAssignee(task.getAssignee());
		hisTask.setNodeName(task.getNodeName());
		hisTask.setState(task.getState());
		hisTask.setEndDate(new Date());
		hisTask.setCreateDate(task.getCreateDate());
		hisTask.setBusinessId(task.getBusinessId());
		hisTask.setDuedate(task.getDuedate());
		hisTask.setOpinion(task.getOpinion());
		hisTask.setOwner(task.getOwner());
		hisTask.setType(task.getType());
		hisTask.setUrl(task.getUrl());
		hisTask.setTaskId(task.getId());
		session.save(hisTask);
		return hisTask;
	}

}
