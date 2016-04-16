package com.deloitte.tms.pl.workflow.command.impl;

import java.util.List;
import java.util.Map;

import com.deloitte.tms.pl.workflow.command.Command;
import com.deloitte.tms.pl.workflow.env.Context;
import com.deloitte.tms.pl.workflow.service.TaskOpinion;
import com.deloitte.tms.pl.workflow.service.TaskService;

/**
 * @author Jacky.gao
 * @since 2013年10月9日
 */
public class BatchStartAndCompleteTasksCommand implements Command<Object> {
	private List<Long> taskIds;
	private Map<String,Object> variables;
	private TaskOpinion opinion;
	public BatchStartAndCompleteTasksCommand(List<Long> taskIds,Map<String,Object> variables,TaskOpinion opinion){
		this.taskIds=taskIds;
		this.variables=variables;
		this.opinion=opinion;
	}
	public Object execute(Context context) {
		TaskService taskService=context.getTaskService();
		for(long taskId:taskIds){
			taskService.start(taskId);
			taskService.complete(taskId, variables,opinion);
		}
		return null;
	}
}
