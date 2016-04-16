package com.deloitte.tms.pl.workflow.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.workflow.command.CommandService;
import com.deloitte.tms.pl.workflow.command.impl.GetHistoryActivitiyCommand;
import com.deloitte.tms.pl.workflow.command.impl.GetHistoryProcessInstanceCommand;
import com.deloitte.tms.pl.workflow.command.impl.GetHistoryTaskCommand;
import com.deloitte.tms.pl.workflow.command.impl.GetHistoryVariableCommand;
import com.deloitte.tms.pl.workflow.command.impl.GetHistoryVariablesCommand;
import com.deloitte.tms.pl.workflow.command.impl.GetListHistoryProcessInstancesCommand;
import com.deloitte.tms.pl.workflow.command.impl.GetListHistoryTasksCommand;
import com.deloitte.tms.pl.workflow.model.HistoryActivity;
import com.deloitte.tms.pl.workflow.model.HistoryProcessInstance;
import com.deloitte.tms.pl.workflow.model.HistoryTask;
import com.deloitte.tms.pl.workflow.model.HistoryVariable;
import com.deloitte.tms.pl.workflow.query.HistoryProcessInstanceQuery;
import com.deloitte.tms.pl.workflow.query.HistoryProcessVariableQuery;
import com.deloitte.tms.pl.workflow.query.HistoryTaskQuery;
import com.deloitte.tms.pl.workflow.query.impl.HistoryProcessInstanceQueryImpl;
import com.deloitte.tms.pl.workflow.query.impl.HistoryProcessVariableQueryImpl;
import com.deloitte.tms.pl.workflow.query.impl.HistoryTaskQueryImpl;
import com.deloitte.tms.pl.workflow.service.HistoryService;

/**
 * @author Jacky.gao
 * @since 2013年9月12日
 */
@Component(HistoryService.BEAN_ID)
public class DefaultHistoryService implements HistoryService {
	@Resource
	private CommandService commandService;
	public List<HistoryActivity> getHistoryActivitysByProcesssInstanceId(long processInstanceId) {
		return commandService.executeCommand(new GetHistoryActivitiyCommand(processInstanceId,true));
	}
	
	public HistoryProcessVariableQuery createHistoryProcessVariableQuery() {
		return new HistoryProcessVariableQueryImpl(commandService);
	}
	
	public HistoryVariable getHistoryVariable(long historyProcessInstanceId,String key) {
		return commandService.executeCommand(new GetHistoryVariableCommand(historyProcessInstanceId,key));
	}

	public List<HistoryActivity> getHistoryActivitysByHistoryProcesssInstanceId(long historyProcessInstanceId) {
		return commandService.executeCommand(new GetHistoryActivitiyCommand(historyProcessInstanceId,false));
	}

	public List<HistoryProcessInstance> getHistoryProcessInstances(long processId) {
		return commandService.executeCommand(new GetListHistoryProcessInstancesCommand(processId));
	}

	public HistoryProcessInstance getHistoryProcessInstance(long processInstanceId) {
		return commandService.executeCommand(new GetHistoryProcessInstanceCommand(processInstanceId));
	}

	public List<HistoryTask> getHistoryTasks(long processInstanceId) {
		return commandService.executeCommand(new GetListHistoryTasksCommand(processInstanceId));
	}
	
	public HistoryProcessInstanceQuery createHistoryProcessInstanceQuery() {
		return new HistoryProcessInstanceQueryImpl(commandService);
	}
	
	public HistoryTaskQuery createHistoryTaskQuery() {
		return new HistoryTaskQueryImpl(commandService);
	}
	
	public List<HistoryVariable> getHistoryVariables(long historyProcessInstanceId) {
		return commandService.executeCommand(new GetHistoryVariablesCommand(historyProcessInstanceId));
	}
	
	public HistoryTask getHistoryTask(long taskId) {
		return commandService.executeCommand(new GetHistoryTaskCommand(taskId));
	}
	public void setCommandService(CommandService commandService) {
		this.commandService = commandService;
	}
}
