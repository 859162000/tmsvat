package com.deloitte.tms.pl.workflow.command.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.deloitte.tms.pl.workflow.command.Command;
import com.deloitte.tms.pl.workflow.command.impl.jump.JumpNode;
import com.deloitte.tms.pl.workflow.env.Context;
import com.deloitte.tms.pl.workflow.model.ProcessDefinition;
import com.deloitte.tms.pl.workflow.model.task.Task;
import com.deloitte.tms.pl.workflow.process.node.Node;
import com.deloitte.tms.pl.workflow.process.node.StartNode;
import com.deloitte.tms.pl.workflow.process.node.TaskNode;

/**
 * @author Jacky.gao
 * @since 2013年9月24日
 */
public class CanWithdrawDecisionCommand implements Command<Boolean> {
	public Task task;
	public CanWithdrawDecisionCommand(Task task){
		this.task=task;
	}
	public Boolean execute(Context context) {
		ProcessDefinition pd=context.getProcessService().getProcessById(task.getProcessId());
		String prevTaskName=task.getPrevTask();
		if(StringUtils.isEmpty(prevTaskName)){
			return false;
		}
		if(prevTaskName.equals(task.getNodeName())){
			return false;
		}
		List<JumpNode> nodes=context.getTaskService().getAvaliableRollbackTaskNodes(task);
		boolean canJump=false;
		for(JumpNode jumpNode:nodes){
			if(jumpNode.getName().equals(prevTaskName)){
				canJump=true;
				break;
			}
		}
		if(!canJump){
			return false;
		}
		
		Node currentNode=pd.getNode(task.getNodeName());
		Node node=pd.getNode(prevTaskName);
		if(node!=null && (node instanceof TaskNode || node instanceof StartNode)){
			if(node instanceof StartNode && currentNode instanceof StartNode){
				return false;
			}else{
				return true;				
			}
		}else{
			return false;
		}
	}
}
