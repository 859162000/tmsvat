package com.deloitte.tms.pl.workflow.service.impl.job;

import org.quartz.impl.JobDetailImpl;

import com.deloitte.tms.pl.workflow.model.ProcessInstance;
import com.deloitte.tms.pl.workflow.model.task.Task;
import com.deloitte.tms.pl.workflow.process.handler.ReminderHandler;

/**
 * @author Jacky.gao
 * @since 2013年8月21日
 */
public class ReminderJobDetail extends JobDetailImpl {
	private static final long serialVersionUID = -5400934958181696022L;
	private ProcessInstance processInstance;
	private Task task;
	private ReminderHandler reminderHandlerBean;
	public ProcessInstance getProcessInstance() {
		return processInstance;
	}

	public void setProcessInstance(ProcessInstance processInstance) {
		this.processInstance = processInstance;
	}

	public ReminderHandler getReminderHandlerBean() {
		return reminderHandlerBean;
	}

	public void setReminderHandlerBean(ReminderHandler reminderHandlerBean) {
		this.reminderHandlerBean = reminderHandlerBean;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}
}
