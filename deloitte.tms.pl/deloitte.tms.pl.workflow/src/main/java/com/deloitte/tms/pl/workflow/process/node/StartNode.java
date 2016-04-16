package com.deloitte.tms.pl.workflow.process.node;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.apache.commons.lang.StringUtils;

import com.deloitte.tms.pl.workflow.command.impl.SaveHistoryTaskCommand;
import com.deloitte.tms.pl.workflow.env.Context;
import com.deloitte.tms.pl.workflow.model.ProcessInstance;
import com.deloitte.tms.pl.workflow.model.task.CancelTaskInterceptor;
import com.deloitte.tms.pl.workflow.model.task.Task;
import com.deloitte.tms.pl.workflow.model.task.TaskState;
import com.deloitte.tms.pl.workflow.model.task.TaskType;
import com.deloitte.tms.pl.workflow.process.security.ComponentAuthority;
import com.deloitte.tms.pl.workflow.service.StartProcessInfo;
import com.deloitte.tms.pl.workflow.utils.IDGenerator;

/**
 * @author Jacky.gao
 * @since 2013年7月31日
 */
public class StartNode extends Node {
	private static final long serialVersionUID = 1L;
	private String taskName;
	private String url;
	private String formTemplate;
	private List<FormElement> formElements;
	private List<ComponentAuthority> componentAuthorities;
	@Override
	public boolean enter(Context context,ProcessInstance processInstance) {
		StartProcessInfo startProcessInfo=(StartProcessInfo)processInstance.getMetadata(StartProcessInfo.KEY);
		Task task=new Task();
		task.setId(IDGenerator.getInstance().nextId());
		task.setNodeName(getName());
		if(StringUtils.isEmpty(taskName)){
			taskName=getName();
		}
		task.setRootProcessInstanceId(processInstance.getRootId());
		task.setTaskName(context.getExpressionContext().evalString(processInstance, taskName));
		task.setDescription(getDescription());
		task.setCreateDate(new Date());
		task.setProcessId(getProcessId());
		task.setProcessInstanceId(processInstance.getId());
		task.setPrevTask(processInstance.getCurrentTask());
		task.setBusinessId(processInstance.getBusinessId());
		String targetUrl=getUrl();
		if(StringUtils.isEmpty(targetUrl)){
			targetUrl=getFormTemplate();
		}
		task.setUrl(targetUrl);
		task.setType(TaskType.Normal);
		if(startProcessInfo!=null){
			task.setOwner(startProcessInfo.getPromoter());
			task.setAssignee(startProcessInfo.getPromoter());
			if(startProcessInfo.isCompleteStartTask()){
				task.setState(TaskState.Completed);
				context.getCommandService().executeCommand(new SaveHistoryTaskCommand(task,processInstance));
				return true;
			}
		}else{
			task.setOwner(processInstance.getPromoter());
			task.setAssignee(processInstance.getPromoter());
		}
		task.setState(TaskState.Created);
		context.getSession().save(task);			
		return false;
	}
	@Override
	public String leave(Context context,ProcessInstance processInstance,String flowName) {
		return leaveNode(context, processInstance, flowName);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void cancel(Context context, ProcessInstance processInstance) {
		Session session=context.getSession();
		Collection<Task> tasks=session.createCriteria(Task.class).add(Restrictions.eq("processInstanceId", processInstance.getId())).list();
		cancelTasks(context, processInstance, tasks);
	}

	private void cancelTasks(Context context, ProcessInstance processInstance, Collection<Task> tasks) {
		Session session=context.getSession();
		Collection<CancelTaskInterceptor> interceptors=context.getApplicationContext().getBeansOfType(CancelTaskInterceptor.class).values();
		for(Task task:tasks){
			for(CancelTaskInterceptor interceptor:interceptors){
				interceptor.intercept(context, task);
			}
			session.delete(task);
			task.setState(TaskState.Canceled);
			context.getCommandService().executeCommand(new SaveHistoryTaskCommand(task,processInstance));
		}
	}
	
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public List<FormElement> getFormElements() {
		return formElements;
	}
	public void setFormElements(List<FormElement> formElements) {
		this.formElements = formElements;
	}
	public String getFormTemplate() {
		return formTemplate;
	}
	public void setFormTemplate(String formTemplate) {
		this.formTemplate = formTemplate;
	}
	public List<ComponentAuthority> getComponentAuthorities() {
		return componentAuthorities;
	}
	public void setComponentAuthorities(
			List<ComponentAuthority> componentAuthorities) {
		this.componentAuthorities = componentAuthorities;
	}
}
