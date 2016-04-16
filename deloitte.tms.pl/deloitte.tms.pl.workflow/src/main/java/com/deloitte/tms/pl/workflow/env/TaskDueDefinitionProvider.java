package com.deloitte.tms.pl.workflow.env;

import com.deloitte.tms.pl.workflow.model.ProcessInstance;
import com.deloitte.tms.pl.workflow.model.task.Task;
import com.deloitte.tms.pl.workflow.process.node.reminder.DueDefinition;

/**
 * @author Jacky.gao
 * @since 2013年10月15日
 */
public interface TaskDueDefinitionProvider {
	DueDefinition getDueDefinition(Task task,ProcessInstance processInstance);
}
