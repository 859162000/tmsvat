package com.deloitte.tms.pl.workflow.process.handler;

import com.deloitte.tms.pl.workflow.model.ProcessInstance;
import com.deloitte.tms.pl.workflow.model.task.Task;

/**
 * @author Jacky.gao
 * @since 2013年8月20日
 */
public interface ReminderHandler {
	void execute(ProcessInstance processInstance,Task task);
}
