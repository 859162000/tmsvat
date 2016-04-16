package com.deloitte.tms.pl.workflow.command.impl;

import java.util.List;
import java.util.Map;

import com.deloitte.tms.pl.workflow.command.Command;
import com.deloitte.tms.pl.workflow.env.Context;
import com.deloitte.tms.pl.workflow.service.TaskOpinion;

/**
 * @author Jacky.gao
 * @since 2013年10月9日
 */
public class BatchCompleteTasksCommand implements Command<Object> {
	private List<Long> taskIds;
	private Map<String,Object> variables;
	private TaskOpinion opinion;
	public BatchCompleteTasksCommand(List<Long> taskIds,Map<String,Object> variables,TaskOpinion opinion){
		this.taskIds=taskIds;
		this.variables=variables;
		this.opinion=opinion;
	}
	public Object execute(Context context) {
		for(long taskId:taskIds){
			context.getTaskService().complete(taskId, variables,opinion);
		}
		return null;
	}
}
