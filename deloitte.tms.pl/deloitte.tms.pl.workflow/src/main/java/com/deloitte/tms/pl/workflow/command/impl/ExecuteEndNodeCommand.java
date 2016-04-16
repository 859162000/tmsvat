package com.deloitte.tms.pl.workflow.command.impl;

import org.hibernate.Session;

import com.deloitte.tms.pl.workflow.command.Command;
import com.deloitte.tms.pl.workflow.env.Context;
import com.deloitte.tms.pl.workflow.model.ProcessInstance;
import com.deloitte.tms.pl.workflow.model.ProcessInstanceState;
import com.deloitte.tms.pl.workflow.process.node.EndNode;

/**
 * @author Jacky.gao
 * @since 2013年7月26日
 */
public class ExecuteEndNodeCommand implements Command<Object> {
	private EndNode endNode;
	private ProcessInstance processInstance;
	public ExecuteEndNodeCommand(EndNode endNode,ProcessInstance processInstance){
		this.endNode=endNode;
		this.processInstance=processInstance;
	}
	public Object execute(Context context) {
		Session session=context.getSession();
		processInstance.setState(ProcessInstanceState.End);
		session.delete(processInstance);
		if(endNode.isTerminate()){
			removeProcessInstances(processInstance,session);
		}
		return null;
	}

	private void removeProcessInstances(ProcessInstance pi,Session session){
		long pid=pi.getParentId();
		if(pid>0){
			ProcessInstance parent=(ProcessInstance)session.get(ProcessInstance.class, pid);
			parent.setState(ProcessInstanceState.End);
			session.delete(parent);
			removeProcessInstances(parent,session);
		}
	}
}
