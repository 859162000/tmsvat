package com.deloitte.tms.pl.workflow.command.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.deloitte.tms.pl.workflow.command.Command;
import com.deloitte.tms.pl.workflow.command.impl.jump.JumpNode;
import com.deloitte.tms.pl.workflow.command.impl.jump.JumpNodeBuilder;
import com.deloitte.tms.pl.workflow.env.Context;
import com.deloitte.tms.pl.workflow.model.ProcessDefinition;
import com.deloitte.tms.pl.workflow.model.task.Task;

/**
 * @author Jacky.gao
 * @since 2013年9月26日
 */
public class GetJumpAvaliableTaskNodesCommand implements Command<List<JumpNode>> {
	private Task task;
	public GetJumpAvaliableTaskNodesCommand(Task task){
		this.task=task;
	}
	public List<JumpNode> execute(Context context) {
		ProcessDefinition process=context.getProcessService().getProcessById(task.getProcessId());
		JumpNodeBuilder builder=new JumpNodeBuilder(process);
		Map<String,JumpNode> map=builder.buildSimulationTasks();
		JumpNode node=map.get(task.getNodeName());
		List<JumpNode> list=new ArrayList<JumpNode>();
		int level=node.getLevel();
		for(JumpNode jumpNode:map.values()){
			if(jumpNode.getLevel()==level && parentEquals(node.getParent(),jumpNode.getParent()) && !jumpNode.getName().equals(node.getName())){
				list.add(jumpNode);
			}
		}
		return list;
	}
	
	private boolean parentEquals(List<String> a,List<String> b){
		if(a.size()!=b.size())return false;
		for(int i=0;i<a.size();i++){
			String name=a.get(i);
			if(!name.equals(b.get(i))){
				return false;
			}
		}
		return true;
	}
}
