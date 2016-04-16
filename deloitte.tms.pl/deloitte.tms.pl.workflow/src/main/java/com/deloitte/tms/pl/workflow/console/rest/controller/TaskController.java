package com.deloitte.tms.pl.workflow.console.rest.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.deloitte.tms.pl.workflow.console.rest.model.BatchCompleteTaskInfo;
import com.deloitte.tms.pl.workflow.console.rest.model.CompleteTaskInfo;
import com.deloitte.tms.pl.workflow.service.TaskOpinion;
import com.deloitte.tms.pl.workflow.service.TaskService;

/**
 * @author Jacky.gao
 * @since 2013年9月20日
 */
@Controller
public class TaskController{
	@Autowired
	@Qualifier(TaskService.BEAN_ID)
	private TaskService taskService;
	
	@RequestMapping(method=RequestMethod.POST,value="/uflo/task/claim/{username}/{taskId}")
	public @ResponseBody void claim(@PathVariable long taskId,@PathVariable String username){
		taskService.claim(taskId, username);
	}
	@RequestMapping(method=RequestMethod.POST,value="/uflo/task/start/{taskId}")
	public @ResponseBody void start(@PathVariable long taskId){
		taskService.start(taskId);
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/uflo/task/suspend/{taskId}")
	public @ResponseBody void suspend(@PathVariable long taskId){
		taskService.suspend(taskId);
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/uflo/task/resume/{taskId}")
	public @ResponseBody void resume(@PathVariable long taskId){
		taskService.resume(taskId);
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/uflo/task/release/{taskId}")
	public @ResponseBody void release(@PathVariable long taskId){
		taskService.release(taskId);
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/uflo/task/complete")
	public @ResponseBody void complete(@RequestBody CompleteTaskInfo info){
		String opinion=info.getOpinion();
		if(StringUtils.isNotEmpty(opinion)){
			taskService.complete(info.getTaskId(), info.getFlowName(), info.getVariables(),new TaskOpinion(info.getOpinion()));			
		}else{
			taskService.complete(info.getTaskId(), info.getFlowName(), info.getVariables());			
		}
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/uflo/task/batch/start")
	public @ResponseBody void batchStart(@RequestBody BatchCompleteTaskInfo info){
		taskService.batchStart(info.getTaskIds());			
	}
	@RequestMapping(method=RequestMethod.POST,value="/uflo/task/batch/complete")
	public @ResponseBody void batchComplete(@RequestBody BatchCompleteTaskInfo info){
		String opinion=info.getOpinion();
		if(StringUtils.isNotEmpty(opinion)){
			taskService.batchComplete(info.getTaskIds(), info.getVariables(),new TaskOpinion(info.getOpinion()));			
		}else{
			taskService.batchComplete(info.getTaskIds(), info.getVariables());			
		}
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/uflo/task/batch/start/complete")
	public @ResponseBody void batchStartAndComplete(@RequestBody BatchCompleteTaskInfo info){
		String opinion=info.getOpinion();
		if(StringUtils.isNotEmpty(opinion)){
			taskService.batchStartAndComplete(info.getTaskIds(), info.getVariables(),new TaskOpinion(info.getOpinion()));			
		}else{
			taskService.batchStartAndComplete(info.getTaskIds(), info.getVariables());			
		}
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/uflo/task/withdraw")
	public @ResponseBody void withdraw(@RequestBody CompleteTaskInfo info){
		String opinion=info.getOpinion();
		if(StringUtils.isNotEmpty(opinion)){
			taskService.withdraw(info.getTaskId(), info.getVariables(),new TaskOpinion(info.getOpinion()));			
		}else{
			taskService.withdraw(info.getTaskId(), info.getVariables());			
		}
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/uflo/task/rollback")
	public @ResponseBody void rollback(@RequestBody CompleteTaskInfo info){
		String opinion=info.getOpinion();
		if(StringUtils.isNotEmpty(opinion)){
			taskService.rollback(info.getTaskId(), info.getTargetNodeName(), info.getVariables(),new TaskOpinion(info.getOpinion()));			
		}else{
			taskService.rollback(info.getTaskId(), info.getTargetNodeName(), info.getVariables());						
		}
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/uflo/task/forward")
	public @ResponseBody void forward(@RequestBody CompleteTaskInfo info){
		String opinion=info.getOpinion();
		if(StringUtils.isNotEmpty(opinion)){
			taskService.forward(info.getTaskId(), info.getTargetNodeName(), info.getVariables(),new TaskOpinion(info.getOpinion()));			
		}else{
			taskService.forward(info.getTaskId(), info.getTargetNodeName(), info.getVariables());						
		}
	}
}
