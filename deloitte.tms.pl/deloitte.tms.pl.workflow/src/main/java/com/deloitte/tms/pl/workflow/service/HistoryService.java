package com.deloitte.tms.pl.workflow.service;

import java.util.List;

import com.deloitte.tms.pl.workflow.model.HistoryActivity;
import com.deloitte.tms.pl.workflow.model.HistoryProcessInstance;
import com.deloitte.tms.pl.workflow.model.HistoryTask;
import com.deloitte.tms.pl.workflow.model.HistoryVariable;
import com.deloitte.tms.pl.workflow.query.HistoryProcessInstanceQuery;
import com.deloitte.tms.pl.workflow.query.HistoryProcessVariableQuery;
import com.deloitte.tms.pl.workflow.query.HistoryTaskQuery;

/**
 * @author Jacky.gao
 * @since 2013年8月15日
 */
public interface HistoryService {
	public static final String BEAN_ID="uflo.historyService";
	List<HistoryActivity> getHistoryActivitysByProcesssInstanceId(long processInstanceId);
	List<HistoryActivity> getHistoryActivitysByHistoryProcesssInstanceId(long historyProcessInstanceId);
	List<HistoryProcessInstance> getHistoryProcessInstances(long processId);
	HistoryProcessInstance getHistoryProcessInstance(long processInstanceId);
	List<HistoryTask> getHistoryTasks(long processInstanceId);
	HistoryTask getHistoryTask(long taskId);
	HistoryTaskQuery createHistoryTaskQuery();
	HistoryProcessInstanceQuery createHistoryProcessInstanceQuery();
	HistoryProcessVariableQuery createHistoryProcessVariableQuery();
	List<HistoryVariable> getHistoryVariables(long historyProcessInstanceId);
	HistoryVariable getHistoryVariable(long historyProcessInstanceId,String key);
}
