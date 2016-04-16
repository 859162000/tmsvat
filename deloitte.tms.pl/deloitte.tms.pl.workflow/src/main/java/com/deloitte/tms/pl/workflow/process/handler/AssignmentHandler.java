package com.deloitte.tms.pl.workflow.process.handler;

import java.util.Collection;

import com.deloitte.tms.pl.workflow.env.Context;
import com.deloitte.tms.pl.workflow.model.ProcessInstance;
import com.deloitte.tms.pl.workflow.process.node.TaskNode;

/**
 * @author Jacky.gao
 * @since 2013年8月10日
 */
public interface AssignmentHandler {
	Collection<String> handle(TaskNode taskNode,ProcessInstance processInstance,Context context);
}
