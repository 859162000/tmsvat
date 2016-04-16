package com.deloitte.tms.pl.workflow.command.impl;

import java.util.Map;

import org.hibernate.Session;
import org.apache.commons.lang.StringUtils;

import com.deloitte.tms.pl.workflow.command.Command;
import com.deloitte.tms.pl.workflow.env.Context;
import com.deloitte.tms.pl.workflow.model.ProcessDefinition;
import com.deloitte.tms.pl.workflow.model.ProcessInstance;
import com.deloitte.tms.pl.workflow.model.task.Task;
import com.deloitte.tms.pl.workflow.model.task.TaskParticipator;
import com.deloitte.tms.pl.workflow.model.task.TaskState;
import com.deloitte.tms.pl.workflow.model.task.TaskType;
import com.deloitte.tms.pl.workflow.process.listener.TaskListener;
import com.deloitte.tms.pl.workflow.process.node.Node;
import com.deloitte.tms.pl.workflow.process.node.TaskNode;
import com.deloitte.tms.pl.workflow.service.ProcessService;
import com.deloitte.tms.pl.workflow.service.SchedulerService;
import com.deloitte.tms.pl.workflow.service.TaskOpinion;

/**
 * @author Jacky.gao
 * @since 2013年8月7日
 */
public class CompleteTaskCommand implements Command<Task> {
	private Task task;
	private String flowName;
	private TaskOpinion opinion;
	private Map<String,Object> variables;
	public CompleteTaskCommand(Task task,String flowName,TaskOpinion opinion,Map<String,Object> variables){
		this.task=task;
		this.opinion=opinion;
		this.flowName=flowName;
		this.variables=variables;
	}
	public Task execute(Context context) {
		TaskState state=task.getState();
		if(!state.equals(TaskState.InProgress) 
				&& !state.equals(TaskState.Forwarded) 
				&& !state.equals(TaskState.Rollback) 
				&& !state.equals(TaskState.Withdraw)){
			throw new IllegalStateException("Please change task state to inProgress first!");
		}
		if(state.equals(TaskState.InProgress)){
			task.setState(TaskState.Completed);
			task.setProgress(100);
		}
		Session session=context.getSession();
		if(task.getType().equals(TaskType.Participative)){
			session.createQuery("delete "+TaskParticipator.class.getName()+" where taskId=:taskId").setLong("taskId", task.getId()).executeUpdate();			
		}
		ProcessService processService=context.getProcessService();
		ProcessInstance processInstance=processService.getProcessInstanceById(task.getProcessInstanceId());
		if(opinion!=null){
			task.setOpinion(opinion.getOpinion());
		}
		session.delete(task);
		SchedulerService schedulerService=(SchedulerService)context.getApplicationContext().getBean(SchedulerService.BEAN_ID);
		ProcessDefinition process=processService.getProcessById(task.getProcessId());
		Node node=process.getNode(task.getNodeName());
		if(node instanceof TaskNode){
			TaskNode taskNode=(TaskNode)node;
			if(taskNode.getDueDefinition()!=null){
				schedulerService.removeReminderJob(task);			
			}
			String taskListenerBean=taskNode.getTaskListenerBean();
			if(StringUtils.isNotEmpty(taskListenerBean)){
				TaskListener taskListener=(TaskListener)context.getApplicationContext().getBean(taskListenerBean);
				taskListener.onTaskComplete(context, task);
			}
		}
		context.getCommandService().executeCommand(new SaveHistoryTaskCommand(task,processInstance));
		if(variables!=null && variables.size()>0){
			context.getExpressionContext().addContextVariables(processInstance, variables);
			context.getCommandService().executeCommand(new SaveProcessInstanceVariablesCommand(processInstance, variables));			
		}
		node.leave(context,processInstance,flowName);
		return task;
	}
}
