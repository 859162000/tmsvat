package com.deloitte.tms.pl.workflow.command.impl;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.deloitte.tms.pl.workflow.command.Command;
import com.deloitte.tms.pl.workflow.env.Context;
import com.deloitte.tms.pl.workflow.model.ProcessDefinition;
import com.deloitte.tms.pl.workflow.model.task.Task;
import com.deloitte.tms.pl.workflow.model.task.TaskState;
import com.deloitte.tms.pl.workflow.process.flow.SequenceFlowImpl;
import com.deloitte.tms.pl.workflow.process.node.Node;
import com.deloitte.tms.pl.workflow.service.ProcessService;
import com.deloitte.tms.pl.workflow.service.TaskOpinion;
import com.deloitte.tms.pl.workflow.service.TaskService;

/**
 * @author Jacky.gao
 * @since 2013年9月19日
 */
public class ForwardTaskCommand implements Command<Task> {
	private Task task;
	private String jumpTargetNodeName;
	private Map<String,Object> variables;
	private TaskOpinion opinion;
	private TaskState state;
	public ForwardTaskCommand(Task task,String jumpTargetNodeName,Map<String,Object> variables,TaskOpinion opinion,TaskState state){
		this.task=task;
		this.opinion=opinion;
		this.jumpTargetNodeName=jumpTargetNodeName;
		this.variables=variables;
		this.state=state;
	}
	public Task execute(Context context) {
		ProcessService processService=context.getProcessService();
		ProcessDefinition processDefinition=processService.getProcessById(task.getProcessId());
		Node targetNode=processDefinition.getNode(jumpTargetNodeName);
		if(targetNode==null){
			throw new IllegalArgumentException("Target node "+jumpTargetNodeName+" is not exist!");
		}
		Node taskNode=processDefinition.getNode(task.getNodeName());
		String targetFlowName=null;
		List<SequenceFlowImpl> flows=taskNode.getSequenceFlows();
		for(SequenceFlowImpl flow:flows){
			if(flow.getToNode().equals(jumpTargetNodeName)){
				targetFlowName=flow.getName();
				break;
			}
		}
		if(targetFlowName==null){
			targetFlowName=TaskService.TEMP_FLOW_NAME_PREFIX+UUID.randomUUID().toString();
			SequenceFlowImpl newFlow=new SequenceFlowImpl();
			newFlow.setToNode(jumpTargetNodeName);
			newFlow.setName(targetFlowName);
			newFlow.setProcessId(task.getProcessId());
			flows.add(newFlow);
		}
		task.setState(state);			
		return context.getCommandService().executeCommand(new CompleteTaskCommand(task, targetFlowName,opinion,variables));
	}
}
