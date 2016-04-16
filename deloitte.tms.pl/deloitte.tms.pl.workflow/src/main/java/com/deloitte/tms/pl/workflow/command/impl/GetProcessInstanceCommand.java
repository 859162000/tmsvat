package com.deloitte.tms.pl.workflow.command.impl;

import com.deloitte.tms.pl.workflow.command.Command;
import com.deloitte.tms.pl.workflow.env.Context;
import com.deloitte.tms.pl.workflow.model.ProcessInstance;

/**
 * @author Jacky.gao
 * @since 2013年7月31日
 */
public class GetProcessInstanceCommand implements Command<ProcessInstance> {
	private long processInstanceId;
	public GetProcessInstanceCommand(long processInstanceId){
		this.processInstanceId=processInstanceId;
	}
	public ProcessInstance execute(Context context) {
		return (ProcessInstance)context.getSession().get(ProcessInstance.class, processInstanceId);
	}
}
