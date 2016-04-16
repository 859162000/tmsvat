package com.deloitte.tms.pl.workflow.env;

import org.hibernate.Session;
import org.springframework.context.ApplicationContext;

import com.deloitte.tms.pl.workflow.command.CommandService;
import com.deloitte.tms.pl.workflow.expr.ExpressionContext;
import com.deloitte.tms.pl.workflow.service.IdentityService;
import com.deloitte.tms.pl.workflow.service.ProcessService;
import com.deloitte.tms.pl.workflow.service.TaskService;

/**
 * @author Jacky.gao
 * @since 2013年8月12日
 */
public interface Context {
	Session getSession();
	ApplicationContext getApplicationContext();
	CommandService getCommandService();
	ProcessService getProcessService();
	ExpressionContext getExpressionContext();
	IdentityService getIdentityService();
	TaskService getTaskService();
}
