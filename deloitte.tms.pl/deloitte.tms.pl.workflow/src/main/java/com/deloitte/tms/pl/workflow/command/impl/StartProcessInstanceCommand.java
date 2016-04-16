package com.deloitte.tms.pl.workflow.command.impl;

import java.util.Date;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.deloitte.tms.pl.workflow.command.Command;
import com.deloitte.tms.pl.workflow.env.Context;
import com.deloitte.tms.pl.workflow.model.ProcessDefinition;
import com.deloitte.tms.pl.workflow.model.ProcessInstance;
import com.deloitte.tms.pl.workflow.model.ProcessInstanceState;
import com.deloitte.tms.pl.workflow.process.handler.ProcessEventHandler;
import com.deloitte.tms.pl.workflow.process.node.StartNode;
import com.deloitte.tms.pl.workflow.service.StartProcessInfo;
import com.deloitte.tms.pl.workflow.utils.IDGenerator;
import com.deloitte.tms.pl.workflow.utils.ProcessListenerUtils;

/**
 * @author Jacky.gao
 * @since 2013年7月31日
 */
public class StartProcessInstanceCommand implements Command<ProcessInstance> {
	private ProcessDefinition process;
	private Map<String,Object> variables;
	private StartProcessInfo startProcessInfo;
	private long parentProcessInstanceId;
	public StartProcessInstanceCommand(ProcessDefinition process,Map<String,Object> variables,StartProcessInfo startProcessInfo,long parentProcessInstanceId){
		this.process=process;
		this.variables=variables;
		this.startProcessInfo=startProcessInfo;
		this.parentProcessInstanceId=parentProcessInstanceId;
	}
	public ProcessInstance execute(Context context) {
		ProcessInstance processInstance=new ProcessInstance();
		long piId=IDGenerator.getInstance().nextId();
		processInstance.addMetadata(StartProcessInfo.KEY, startProcessInfo);
		processInstance.setId(piId);
		processInstance.setRootId(piId);
		processInstance.setState(ProcessInstanceState.Start);
		processInstance.setProcessId(process.getId());
		processInstance.setCreateDate(new Date());
		processInstance.setPromoter(startProcessInfo.getPromoter());
		processInstance.setBusinessId(startProcessInfo.getBusinessId());
		processInstance.setTag(startProcessInfo.getTag());
		if(parentProcessInstanceId>0){
			processInstance.setParentId(parentProcessInstanceId);
		}
		processInstance.setHistoryProcessInstanceId(IDGenerator.getInstance().nextId());
		if(variables!=null && variables.size()>0){
			context.getCommandService().executeCommand(new SaveProcessInstanceVariablesCommand(processInstance, variables));
		}
		context.getExpressionContext().createContext(processInstance, variables);
		StartNode startNode=process.getStartNode();
		processInstance.setCurrentTask(startNode.getName());
		context.getSession().save(processInstance);
		context.getCommandService().executeCommand(new SaveHistoryProcessInstanceCommand(processInstance));
		processInstance.setCurrentNode(startNode.getName());
		ProcessListenerUtils.fireProcessStartListers(processInstance, context);
		String processEventHandlerBean=process.getEventHandlerBean();
		if(StringUtils.isNotEmpty(processEventHandlerBean)){
			ProcessEventHandler bean=(ProcessEventHandler)context.getApplicationContext().getBean(processEventHandlerBean);
			bean.start(processInstance, context);
		}
		startNode.createActivityHistory(context, processInstance);
		boolean isLeave=startNode.enter(context,processInstance);
		startNode.doEnterEventHandler(context, processInstance);
		if(isLeave){
			startNode.leave(context, processInstance, startProcessInfo.getSequenceFlowName());
		}
		return processInstance;
	}
}
