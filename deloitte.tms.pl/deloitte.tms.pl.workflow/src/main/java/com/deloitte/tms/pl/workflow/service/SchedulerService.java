package com.deloitte.tms.pl.workflow.service;

import org.quartz.Scheduler;

import com.deloitte.tms.pl.workflow.model.ProcessInstance;
import com.deloitte.tms.pl.workflow.model.task.Task;
import com.deloitte.tms.pl.workflow.model.task.reminder.TaskReminder;

/**
 * @author Jacky.gao
 * @since 2013年8月21日
 */
public interface SchedulerService {
	public static final String BEAN_ID="uflo.schedulerService";
	Scheduler getScheduler();
	void addReminderJob(TaskReminder reminder,ProcessInstance processInstance,Task task);
	void removeReminderJob(Task task);
}
