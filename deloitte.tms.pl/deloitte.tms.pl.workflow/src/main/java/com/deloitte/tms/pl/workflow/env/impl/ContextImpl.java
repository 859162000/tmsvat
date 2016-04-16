package com.deloitte.tms.pl.workflow.env.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.deloitte.tms.pl.workflow.command.CommandService;
import com.deloitte.tms.pl.workflow.env.Context;
import com.deloitte.tms.pl.workflow.expr.ExpressionContext;
import com.deloitte.tms.pl.workflow.service.IdentityService;
import com.deloitte.tms.pl.workflow.service.ProcessService;
import com.deloitte.tms.pl.workflow.service.TaskService;

/**
 * @author Jacky.gao
 * @since 2013年7月30日
 */
public class ContextImpl implements ApplicationContextAware,Context{
	private SessionFactory sessionFactory;
	private ApplicationContext applicationContext;
	private CommandService commandService;
	private ProcessService processService;
	private ExpressionContext expressionContext;
	private IdentityService identityService;
	private TaskService taskService;
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public CommandService getCommandService() {
		return commandService;
	}
	public void setCommandService(CommandService commandService) {
		this.commandService = commandService;
	}
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext=applicationContext;
	}
	public ProcessService getProcessService() {
		return processService;
	}
	public void setProcessService(ProcessService processService) {
		this.processService = processService;
	}
	public ExpressionContext getExpressionContext() {
		return expressionContext;
	}
	public void setExpressionContext(ExpressionContext expressionContext) {
		this.expressionContext = expressionContext;
	}
	public IdentityService getIdentityService() {
		return identityService;
	}
	public void setIdentityService(IdentityService identityService) {
		this.identityService = identityService;
	}
	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}
	public TaskService getTaskService() {
		return taskService;
	}
}
