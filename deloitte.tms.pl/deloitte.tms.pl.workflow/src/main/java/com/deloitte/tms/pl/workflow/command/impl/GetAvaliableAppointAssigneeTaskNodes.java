package com.deloitte.tms.pl.workflow.command.impl;

import java.util.ArrayList;
import java.util.List;

import com.deloitte.tms.pl.workflow.command.Command;
import com.deloitte.tms.pl.workflow.env.Context;
import com.deloitte.tms.pl.workflow.model.ProcessDefinition;
import com.deloitte.tms.pl.workflow.model.task.Task;
import com.deloitte.tms.pl.workflow.process.flow.SequenceFlowImpl;
import com.deloitte.tms.pl.workflow.process.node.ForeachNode;
import com.deloitte.tms.pl.workflow.process.node.ForkNode;
import com.deloitte.tms.pl.workflow.process.node.JoinNode;
import com.deloitte.tms.pl.workflow.process.node.Node;
import com.deloitte.tms.pl.workflow.process.node.StartNode;
import com.deloitte.tms.pl.workflow.process.node.SubprocessNode;
import com.deloitte.tms.pl.workflow.process.node.TaskNode;

/**
 * @author Jacky.gao
 * @since 2013年10月20日
 */
public class GetAvaliableAppointAssigneeTaskNodes implements Command<List<String>> {
	public Task task;
	public GetAvaliableAppointAssigneeTaskNodes(Task task){
		this.task=task;
	}
	public List<String> execute(Context context) {
		ProcessDefinition pd=context.getProcessService().getProcessById(task.getProcessId());
		Node node=pd.getNode(task.getNodeName());
		List<String> nodes=new ArrayList<String>();
		getAvliableNodes(node,nodes,pd);
		return nodes;
	}
	private void getAvliableNodes(Node node,List<String> nodes,ProcessDefinition pd){
		List<SequenceFlowImpl> flows=node.getSequenceFlows();
		if(flows==null || flows.size()==0)return;
		for(SequenceFlowImpl flow:flows){
			Node toNode=pd.getNode(flow.getToNode());
			if(toNode instanceof StartNode || 
					toNode instanceof JoinNode || 
					toNode instanceof ForeachNode || 
					toNode instanceof SubprocessNode || 
					toNode instanceof ForkNode){
				continue;
			}
			if(toNode instanceof TaskNode){
				TaskNode taskNode=(TaskNode)toNode;
				if(taskNode.isAllowSpecifyAssignee()){
					nodes.add(toNode.getName());					
				}
			}else{
				getAvliableNodes(toNode,nodes,pd);
			}
		}
	}
}
