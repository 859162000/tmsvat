package com.deloitte.tms.pl.workflow.command.impl.jump;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.deloitte.tms.pl.workflow.model.ProcessDefinition;
import com.deloitte.tms.pl.workflow.process.flow.SequenceFlow;
import com.deloitte.tms.pl.workflow.process.flow.SequenceFlowImpl;
import com.deloitte.tms.pl.workflow.process.node.DecisionNode;
import com.deloitte.tms.pl.workflow.process.node.ForeachNode;
import com.deloitte.tms.pl.workflow.process.node.ForkNode;
import com.deloitte.tms.pl.workflow.process.node.JoinNode;
import com.deloitte.tms.pl.workflow.process.node.Node;
import com.deloitte.tms.pl.workflow.process.node.StartNode;
import com.deloitte.tms.pl.workflow.process.node.TaskNode;

/**
 * @author Jacky.gao
 * @since 2013年9月27日
 */
public class JumpNodeBuilder {
	private ProcessDefinition process;
	private List<SequenceFlow> flowStore=new ArrayList<SequenceFlow>();
	private Map<String,JumpNode> maps=new HashMap<String,JumpNode>();
	public JumpNodeBuilder(ProcessDefinition process){
		this.process=process;
	}
	public Map<String,JumpNode> buildSimulationTasks(){
		StartNode node=process.getStartNode();
		JumpNode jumpNode=new JumpNode(node.getName());
		jumpNode.setTask(true);
		jumpNode.setLevel(1);
		maps.put(jumpNode.getName(), jumpNode);
		simulation(node,jumpNode);
		return maps;
	}
	
	private void simulation(Node parentNode,JumpNode jumpNode){
		List<SequenceFlowImpl> flows=parentNode.getSequenceFlows();
		if(flows==null || flows.size()==0)return;
		for(SequenceFlow flow:flows){
			if(flowStore.contains(flow)){
				continue;
			}else{
				flowStore.add(flow);
			}
			String to=flow.getToNode();
			Node toNode=process.getNode(to);
			JumpNode nextJumpNode=new JumpNode(toNode.getName());
			if(jumpNode.getParent().size()>0){
				nextJumpNode.getParent().addAll(jumpNode.getParent());
			}
			if(parentNode instanceof ForkNode || parentNode instanceof ForeachNode){
				nextJumpNode.setLevel(jumpNode.getLevel()+1);
				nextJumpNode.addParent(parentNode.getName()+"-"+flow.getName());
			}else if(parentNode instanceof JoinNode){
				nextJumpNode.decreaseParent();
				nextJumpNode.setLevel(jumpNode.getLevel()-1);				
			}else{
				nextJumpNode.setLevel(jumpNode.getLevel());				
			}
			if(!maps.containsKey(nextJumpNode.getName()) && !(toNode instanceof ForkNode) && !(toNode instanceof JoinNode) && !(toNode instanceof ForeachNode) && !(toNode instanceof DecisionNode)){
				maps.put(nextJumpNode.getName(), nextJumpNode);
			}
			if(toNode instanceof TaskNode){
				nextJumpNode.setTask(true);
			}
			simulation(toNode,nextJumpNode);
		}
	}
}
