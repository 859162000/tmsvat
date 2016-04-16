package com.deloitte.tms.pl.workflow.command.impl;

import com.deloitte.tms.pl.workflow.command.Command;
import com.deloitte.tms.pl.workflow.env.Context;
import com.deloitte.tms.pl.workflow.model.ProcessInstance;
import com.deloitte.tms.pl.workflow.process.node.StartNode;

public class ExecuteStartNodeCommand implements Command<Object> {
	private StartNode startNode;
	private ProcessInstance processInstance;
	public ExecuteStartNodeCommand(StartNode startNode,ProcessInstance processInstance){
		this.startNode=startNode;
		this.processInstance=processInstance;
	}
	public Object execute(Context context) {
		startNode.getSequenceFlows().get(0).execute(context,processInstance);
		return null;
	}
}
