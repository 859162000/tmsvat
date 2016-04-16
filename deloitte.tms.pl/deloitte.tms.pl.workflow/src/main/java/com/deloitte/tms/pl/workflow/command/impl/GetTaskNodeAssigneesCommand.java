package com.deloitte.tms.pl.workflow.command.impl;

import java.util.List;

import com.deloitte.tms.pl.workflow.command.Command;
import com.deloitte.tms.pl.workflow.env.Context;
import com.deloitte.tms.pl.workflow.model.ProcessDefinition;
import com.deloitte.tms.pl.workflow.model.ProcessInstance;
import com.deloitte.tms.pl.workflow.model.task.Task;
import com.deloitte.tms.pl.workflow.process.node.TaskNode;
import com.deloitte.tms.pl.workflow.service.ProcessService;

/**
 * @author Jacky.gao
 * @since 2013年10月20日
 */
public class GetTaskNodeAssigneesCommand implements Command<List<String>> {
	private long taskId;
	private String taskNodeName;
	public GetTaskNodeAssigneesCommand(long taskId,String taskNodeName){
		this.taskId=taskId;
		this.taskNodeName=taskNodeName;
	}
	public List<String> execute(Context context) {
		ProcessService processService=context.getProcessService();
		Task task=context.getTaskService().getTask(taskId);
		ProcessInstance processInstance=processService.getProcessInstanceById(task.getProcessInstanceId());
		ProcessDefinition pd=processService.getProcessById(processInstance.getProcessId());
		TaskNode node=(TaskNode)pd.getNode(taskNodeName);
		return node.getAssigneeUsers(context, processInstance);
	}
}
