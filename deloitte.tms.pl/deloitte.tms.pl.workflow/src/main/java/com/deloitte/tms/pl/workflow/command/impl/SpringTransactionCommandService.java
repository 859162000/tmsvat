package com.deloitte.tms.pl.workflow.command.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.deloitte.tms.pl.workflow.command.Command;
import com.deloitte.tms.pl.workflow.command.CommandService;
import com.deloitte.tms.pl.workflow.env.impl.ContextImpl;
import com.deloitte.tms.pl.workflow.expr.ExpressionContext;
import com.deloitte.tms.pl.workflow.service.IdentityService;
import com.deloitte.tms.pl.workflow.service.ProcessService;
import com.deloitte.tms.pl.workflow.service.TaskService;
import com.deloitte.tms.pl.workflow.utils.EnvironmentUtils;

/**
 * @author Jacky.gao
 * @since 2013年7月30日
 */
@Component(CommandService.BEAN_ID)
@DependsOn(EnvironmentUtils.BEAN_ID)
public class SpringTransactionCommandService implements CommandService,ApplicationContextAware {
	private ContextImpl context;
	private SessionFactory sessionFactory;
	private PlatformTransactionManager platformTransactionManager;
	private int springPropagationBehaviour = TransactionDefinition.PROPAGATION_REQUIRED;
	private int newSpringPropagationBehaviour = TransactionDefinition.PROPAGATION_REQUIRES_NEW;
	public <T> T executeCommand(final Command<T> command) {
		TransactionTemplate template = new TransactionTemplate(platformTransactionManager);
	    template.setPropagationBehavior(springPropagationBehaviour);
	    return template.execute(new TransactionCallback<T>() {
			public T doInTransaction(TransactionStatus status) {
				return command.execute(context);
			}
	    	
		});
	}
	
	public <T> T executeCommandInNewTransaction(final Command<T> command) {
		TransactionTemplate template = new TransactionTemplate(platformTransactionManager);
		template.setPropagationBehavior(newSpringPropagationBehaviour);
		return template.execute(new TransactionCallback<T>() {
			public T doInTransaction(TransactionStatus status) {
				return command.execute(context);
			}
			
		});
	}
	
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.platformTransactionManager=EnvironmentUtils.getEnvironment().getPlatformTransactionManager();
		this.sessionFactory=EnvironmentUtils.getEnvironment().getSessionFactory();
		context=new ContextImpl();
		context.setCommandService(this);
		context.setApplicationContext(applicationContext);
		context.setSessionFactory(sessionFactory);
		context.setProcessService((ProcessService)applicationContext.getBean(ProcessService.BEAN_ID));
		context.setExpressionContext((ExpressionContext)applicationContext.getBean(ExpressionContext.BEAN_ID));
		context.setIdentityService((IdentityService)applicationContext.getBean(IdentityService.BEAN_ID));
		context.setTaskService((TaskService)applicationContext.getBean(TaskService.BEAN_ID));
	}
}
