package com.deloitte.tms.pl.workflow.client.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.workflow.client.model.BatchCompleteTaskInfo;
import com.deloitte.tms.pl.workflow.client.model.CompleteTaskInfo;
import com.deloitte.tms.pl.workflow.client.service.TaskClient;
import com.deloitte.tms.pl.workflow.command.impl.jump.JumpNode;
import com.deloitte.tms.pl.workflow.console.rest.RestBase;
import com.deloitte.tms.pl.workflow.model.task.Task;
import com.deloitte.tms.pl.workflow.model.task.TaskAppointor;
import com.deloitte.tms.pl.workflow.model.task.TaskParticipator;
import com.deloitte.tms.pl.workflow.model.task.reminder.TaskReminder;
import com.deloitte.tms.pl.workflow.query.TaskQuery;
import com.deloitte.tms.pl.workflow.service.TaskOpinion;
import com.deloitte.tms.pl.workflow.service.TaskService;

/**
 * @author Jacky.gao
 * @since 2013年9月22日
 */
@Component(TaskClient.BEAN_ID)
public class TaskClientImpl implements TaskClient {
	@Resource
	private TaskService taskService;
	@Resource
	private RestBase rest;
	
	public void setProgress(int progress) {
		taskService.setProgress(progress);
	}
	
	public List<String> getAvaliableAppointAssigneeTaskNodes(long taskId) {
		return taskService.getAvaliableAppointAssigneeTaskNodes(taskId);
	}
	
	public List<String> getTaskNodeAssignees(long taskId, String taskNodeName) {
		return taskService.getTaskNodeAssignees(taskId, taskNodeName);
	}
	
	public void saveTaskAppointor(long taskId, String assignee,String taskNodeName) {
		taskService.saveTaskAppointor(taskId, assignee, taskNodeName);
	}
	
	public void saveTaskAppointor(long taskId, String[] assignees,
			String taskNodeName) {
		taskService.saveTaskAppointor(taskId, assignees, taskNodeName);
	}
	
	public Task addCountersign(long taskId, String username) {
		return taskService.addCountersign(taskId, username);
	}
	
	public void deleteCountersign(long taskId) {
		taskService.deleteCountersign(taskId);
	}
	
	public void rollback(long taskId, String targetNodeName) {
		if(StringUtils.isNotEmpty(rest.getBaseUrl())){
			CompleteTaskInfo info=new CompleteTaskInfo();
			info.setTaskId(taskId);
			info.setTargetNodeName(targetNodeName);
			String uri="/uflo/task/rollback";
			rest.post(uri, info,null);		
		}else{
			taskService.rollback(taskId, targetNodeName);
		}
	}
	
	public void rollback(long taskId, String targetNodeName,Map<String, Object> variables) {
		if(StringUtils.isNotEmpty(rest.getBaseUrl())){
			CompleteTaskInfo info=new CompleteTaskInfo();
			info.setTaskId(taskId);
			info.setTargetNodeName(targetNodeName);
			info.setVariables(variables);
			String uri="/uflo/task/rollback";
			rest.post(uri, info,null);
		}else{
			taskService.rollback(taskId, targetNodeName, variables);
		}
	}
	
	public void rollback(long taskId, String targetNodeName,Map<String, Object> variables, TaskOpinion opinion) {
		if(StringUtils.isNotEmpty(rest.getBaseUrl())){
			CompleteTaskInfo info=new CompleteTaskInfo();
			info.setTaskId(taskId);
			info.setTargetNodeName(targetNodeName);
			if(opinion!=null){
				info.setOpinion(opinion.getOpinion());			
			}
			info.setVariables(variables);
			String uri="/uflo/task/rollback";
			rest.post(uri, info,null);
		}else{
			taskService.rollback(taskId, targetNodeName, variables, opinion);
		}
	}
	
	public void rollback(Task task, String targetNodeName,Map<String, Object> variables, TaskOpinion opinion) {
		if(StringUtils.isNotEmpty(rest.getBaseUrl())){
			CompleteTaskInfo info=new CompleteTaskInfo();
			info.setTaskId(task.getId());
			info.setTargetNodeName(targetNodeName);
			if(opinion!=null){
				info.setOpinion(opinion.getOpinion());			
			}
			info.setVariables(variables);
			String uri="/uflo/task/rollback";
			rest.post(uri, info,null);
		}else{
			taskService.rollback(task, targetNodeName, variables, opinion);
		}
	}
	
	
	public List<JumpNode> getAvaliableForwardTaskNodes(long taskId) {
		return taskService.getAvaliableForwardTaskNodes(taskId);
	}
	
	public List<JumpNode> getAvaliableRollbackTaskNodes(long taskId) {
		return taskService.getAvaliableRollbackTaskNodes(taskId);
	}
	
	public void batchComplete(List<Long> taskIds, Map<String, Object> variables) {
		if(StringUtils.isNotEmpty(rest.getBaseUrl())){
			BatchCompleteTaskInfo info=new BatchCompleteTaskInfo();
			info.setTaskIds(taskIds);
			info.setVariables(variables);
			String uri="/uflo/task/batch/complete";
			rest.post(uri, info,null);
		}else{
			taskService.batchComplete(taskIds, variables);
		}
	}
	
	public void batchStart(List<Long> taskIds) {
		if(StringUtils.isNotEmpty(rest.getBaseUrl())){
			BatchCompleteTaskInfo info=new BatchCompleteTaskInfo();
			info.setTaskIds(taskIds);
			String uri="/uflo/task/batch/start";
			rest.post(uri, info,null);
		}else{
			taskService.batchStart(taskIds);
		}
	}
	
	public void batchStartAndComplete(List<Long> taskIds,Map<String, Object> variables) {
		if(StringUtils.isNotEmpty(rest.getBaseUrl())){
			BatchCompleteTaskInfo info=new BatchCompleteTaskInfo();
			info.setTaskIds(taskIds);
			info.setVariables(variables);
			String uri="/uflo/task/batch/start/complete";
			rest.post(uri, info,null);
		}else{
			taskService.batchStartAndComplete(taskIds,variables);
		}
	}
	
	public void batchComplete(List<Long> taskIds,Map<String, Object> variables, TaskOpinion opinion) {
		if(StringUtils.isNotEmpty(rest.getBaseUrl())){
			BatchCompleteTaskInfo info=new BatchCompleteTaskInfo();
			info.setTaskIds(taskIds);
			info.setVariables(variables);
			if(opinion!=null){
				info.setOpinion(opinion.getOpinion());			
			}
			String uri="/uflo/task/batch/complete";
			rest.post(uri, info,null);
		}else{
			taskService.batchComplete(taskIds, variables,opinion);
		}
	}
	
	public void batchStartAndComplete(List<Long> taskIds,Map<String, Object> variables, TaskOpinion opinion) {
		if(StringUtils.isNotEmpty(rest.getBaseUrl())){
			BatchCompleteTaskInfo info=new BatchCompleteTaskInfo();
			info.setTaskIds(taskIds);
			info.setVariables(variables);
			if(opinion!=null){
				info.setOpinion(opinion.getOpinion());			
			}
			String uri="/uflo/task/batch/start/complete";
			rest.post(uri, info,null);
		}else{
			taskService.batchStartAndComplete(taskIds,variables,opinion);
		}
	}
	
	public void complete(long taskId, String flowName) {
		if(StringUtils.isNotEmpty(rest.getBaseUrl())){
			CompleteTaskInfo info=new CompleteTaskInfo();
			info.setTaskId(taskId);
			info.setFlowName(flowName);
			String uri="/uflo/task/complete";
			rest.post(uri, info,null);
		}else{
			taskService.complete(taskId, flowName);
		}
	}

	public void complete(long taskId, Map<String, Object> variables,TaskOpinion opinion) {
		if(StringUtils.isNotEmpty(rest.getBaseUrl())){
			CompleteTaskInfo info=new CompleteTaskInfo();
			info.setTaskId(taskId);
			info.setVariables(variables);
			if(opinion!=null){
				info.setOpinion(opinion.getOpinion());			
			}
			String uri="/uflo/task/complete";
			rest.post(uri, info,null);
		}else{
			taskService.complete(taskId, variables, opinion);
		}
	}
	
	public void complete(long taskId, String flowName,Map<String, Object> variables, TaskOpinion opinion) {
		if(StringUtils.isNotEmpty(rest.getBaseUrl())){
			CompleteTaskInfo info=new CompleteTaskInfo();
			info.setTaskId(taskId);
			info.setFlowName(flowName);
			info.setVariables(variables);
			if(opinion!=null){
				info.setOpinion(opinion.getOpinion());			
			}
			String uri="/uflo/task/complete";
			rest.post(uri, info,null);
		}else{
			taskService.complete(taskId, flowName, variables, opinion);
		}
	}
	
	public void complete(long taskId, String flowName, TaskOpinion opinion) {
		if(StringUtils.isNotEmpty(rest.getBaseUrl())){
			CompleteTaskInfo info=new CompleteTaskInfo();
			info.setTaskId(taskId);
			info.setFlowName(flowName);
			if(opinion!=null){
				info.setOpinion(opinion.getOpinion());			
			}
			String uri="/uflo/task/complete";
			rest.post(uri, info,null);
		}else{
			taskService.complete(taskId, flowName, opinion);
		}
	}
	
	public void complete(long taskId, TaskOpinion opinion) {
		if(StringUtils.isNotEmpty(rest.getBaseUrl())){
			CompleteTaskInfo info=new CompleteTaskInfo();
			info.setTaskId(taskId);
			if(opinion!=null){
				info.setOpinion(opinion.getOpinion());			
			}
			String uri="/uflo/task/complete";
			rest.post(uri, info,null);
		}else{
			taskService.complete(taskId, opinion);
		}
	}
	
	public void complete(long taskId, String flowName,Map<String, Object> variables) {
		if(StringUtils.isNotEmpty(rest.getBaseUrl())){
			CompleteTaskInfo info=new CompleteTaskInfo();
			info.setTaskId(taskId);
			info.setFlowName(flowName);
			info.setVariables(variables);
			String uri="/uflo/task/complete";
			rest.post(uri, info,null);
		}else{
			taskService.complete(taskId, flowName, variables);
		}
	}

	public void complete(long taskId) {
		if(StringUtils.isNotEmpty(rest.getBaseUrl())){
			CompleteTaskInfo info=new CompleteTaskInfo();
			info.setTaskId(taskId);
			String uri="/uflo/task/complete";
			rest.post(uri, info,null);
		}else{
			taskService.complete(taskId);
		}
	}

	public void complete(long taskId, Map<String, Object> variables) {
		if(StringUtils.isNotEmpty(rest.getBaseUrl())){
			CompleteTaskInfo info=new CompleteTaskInfo();
			info.setTaskId(taskId);
			info.setVariables(variables);
			String uri="/uflo/task/complete";
			rest.post(uri, info,null);
		}else{
			taskService.complete(taskId, variables);
		}
	}

	public void forward(long taskId, String targetNodeName) {
		if(StringUtils.isNotEmpty(rest.getBaseUrl())){
			CompleteTaskInfo info=new CompleteTaskInfo();
			info.setTaskId(taskId);
			info.setTargetNodeName(targetNodeName);
			String uri="/uflo/task/forward";
			rest.post(uri, info,null);
		}else{
			taskService.forward(taskId,targetNodeName);
		}
	}
	
	public void forward(long taskId, String targetNodeName,Map<String, Object> variables, TaskOpinion opinion) {
		if(StringUtils.isNotEmpty(rest.getBaseUrl())){
			CompleteTaskInfo info=new CompleteTaskInfo();
			info.setTaskId(taskId);
			info.setTargetNodeName(targetNodeName);
			if(opinion!=null){
				info.setOpinion(opinion.getOpinion());			
			}
			info.setVariables(variables);
			String uri="/uflo/task/forward";
			rest.post(uri, info,null);
		}else{
			taskService.forward(taskId,targetNodeName,variables,opinion);
		}
	}
	
	public void forward(long taskId, String targetNodeName,TaskOpinion opinion) {
		if(StringUtils.isNotEmpty(rest.getBaseUrl())){
			CompleteTaskInfo info=new CompleteTaskInfo();
			info.setTaskId(taskId);
			info.setTargetNodeName(targetNodeName);
			if(opinion!=null){
				info.setOpinion(opinion.getOpinion());			
			}
			String uri="/uflo/task/forward";
			rest.post(uri, info,null);
		}else{
			taskService.forward(taskId,targetNodeName,opinion);
		}
	}
	
	public void withdraw(long taskId, Map<String, Object> variables) {
		if(StringUtils.isNotEmpty(rest.getBaseUrl())){
			CompleteTaskInfo info=new CompleteTaskInfo();
			info.setTaskId(taskId);
			info.setVariables(variables);
			String uri="/uflo/task/withdraw";
			rest.post(uri, info,null);
		}else{
			taskService.withdraw(taskId, variables);
		}
	}
	
	public void withdraw(long taskId, Map<String, Object> variables,TaskOpinion opinion) {
		if(StringUtils.isNotEmpty(rest.getBaseUrl())){
			CompleteTaskInfo info=new CompleteTaskInfo();
			info.setTaskId(taskId);
			info.setVariables(variables);
			if(opinion!=null){
				info.setOpinion(opinion.getOpinion());			
			}
			String uri="/uflo/task/withdraw";
			rest.post(uri, info,null);
		}else{
			taskService.withdraw(taskId, variables, opinion);
		}
	}
	
	public void withdraw(long taskId) {
		if(StringUtils.isNotEmpty(rest.getBaseUrl())){
			CompleteTaskInfo info=new CompleteTaskInfo();
			info.setTaskId(taskId);
			String uri="/uflo/task/withdraw";
			rest.post(uri, info,null);
		}else{
			taskService.withdraw(taskId);
		}
	}
	
	public void withdraw(long taskId, TaskOpinion opinion) {
		if(StringUtils.isNotEmpty(rest.getBaseUrl())){
			CompleteTaskInfo info=new CompleteTaskInfo();
			info.setTaskId(taskId);
			if(opinion!=null){
				info.setOpinion(opinion.getOpinion());			
			}
			String uri="/uflo/task/withdraw";
			rest.post(uri, info,null);
		}else{
			taskService.withdraw(taskId,opinion);
		}
	}
	
	public boolean canWithdraw(long taskId) {
		return taskService.canWithdraw(taskId);
	}
	
	public void forward(long taskId, String targetNodeName,Map<String, Object> variables) {
		if(StringUtils.isNotEmpty(rest.getBaseUrl())){
			CompleteTaskInfo info=new CompleteTaskInfo();
			info.setTaskId(taskId);
			info.setTargetNodeName(targetNodeName);
			info.setVariables(variables);
			String uri="/uflo/task/forward";
			rest.post(uri, info,null);
		}else{
			taskService.forward(taskId, targetNodeName,variables);
		}
	}


	public Task getTask(long taskId) {
		return taskService.getTask(taskId);
	}

	public void claim(long taskId, String username) {
		if(StringUtils.isNotEmpty(rest.getBaseUrl())){
			String uri="/uflo/task/claim/"+username+"/"+taskId+"";
			rest.post(uri, null,null);			
		}else{
			taskService.claim(taskId, username);
		}
	}

	public void release(long taskId) {
		if(StringUtils.isNotEmpty(rest.getBaseUrl())){
			String uri="/uflo/task/release/"+taskId+"";
			rest.post(uri, null,null);
		}else{
			taskService.release(taskId);
		}
	}

	public void start(long taskId) {
		if(StringUtils.isNotEmpty(rest.getBaseUrl())){
			String uri="/uflo/task/start/"+taskId+"";
			rest.post(uri, null,null);			
		}else{
			taskService.start(taskId);
		}
	}

	public void suspend(long taskId) {
		if(StringUtils.isNotEmpty(rest.getBaseUrl())){
			String uri="/uflo/task/suspend/"+taskId+"";
			rest.post(uri, null,null);
		}else{
			taskService.suspend(taskId);
		}
	}

	public void resume(long taskId) {
		if(StringUtils.isNotEmpty(rest.getBaseUrl())){
			String uri="/uflo/task/resume/"+taskId+"";
			rest.post(uri, null,null);
		}else{
			taskService.resume(taskId);
		}
	}

	public String getUserData(Task task, String key) {
		return taskService.getUserData(task, key);
	}
	
	public String getUserData(long processId, String taskNodeName, String key) {
		return taskService.getUserData(processId,taskNodeName,key);
	}
	
	public List<TaskParticipator> getTaskParticipators(long taskId) {
		return taskService.getTaskParticipators(taskId);
	}

	public List<TaskAppointor> getTaskAppointors(String taskNodeName,long processInstanceId) {
		return taskService.getTaskAppointors(taskNodeName, processInstanceId);
	}


	public TaskQuery createTaskQuery() {
		return taskService.createTaskQuery();
	}

	public List<TaskReminder> getTaskReminders(long taskId) {
		return taskService.getTaskReminders(taskId);
	}

	public void deleteTaskReminder(long taskReminderId) {
		taskService.deleteTaskReminder(taskReminderId);
	}

	public List<TaskReminder> getAllTaskReminders() {
		return taskService.getAllTaskReminders();
	}
	
	public void changeTaskAssignee(long taskId, String username) {
		taskService.changeTaskAssignee(taskId, username);
	}
	
	public RestBase getRest() {
		return rest;
	}

	public void setRest(RestBase rest) {
		this.rest = rest;
	}

	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}
}
