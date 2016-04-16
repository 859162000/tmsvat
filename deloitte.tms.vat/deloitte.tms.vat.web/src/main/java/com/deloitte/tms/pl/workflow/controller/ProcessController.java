package com.deloitte.tms.pl.workflow.controller;
//package com.deloitte.tms.pl.workflow.console.view;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Component;
//
//import com.bstek.dorado.annotation.DataProvider;
//import com.bstek.dorado.annotation.DataResolver;
//import com.bstek.dorado.annotation.Expose;
//import com.bstek.dorado.data.provider.Page;
//import com.deloitte.tms.pl.workflow.command.impl.jump.JumpNode;
//import com.deloitte.tms.pl.workflow.model.HistoryProcessInstance;
//import com.deloitte.tms.pl.workflow.model.HistoryTask;
//import com.deloitte.tms.pl.workflow.model.ProcessDefinition;
//import com.deloitte.tms.pl.workflow.model.ProcessInstance;
//import com.deloitte.tms.pl.workflow.model.task.Task;
//import com.deloitte.tms.pl.workflow.model.task.TaskParticipator;
//import com.deloitte.tms.pl.workflow.process.flow.SequenceFlowImpl;
//import com.deloitte.tms.pl.workflow.process.node.Node;
//import com.deloitte.tms.pl.workflow.query.HistoryProcessInstanceQuery;
//import com.deloitte.tms.pl.workflow.query.HistoryTaskQuery;
//import com.deloitte.tms.pl.workflow.query.ProcessInstanceQuery;
//import com.deloitte.tms.pl.workflow.query.ProcessQuery;
//import com.deloitte.tms.pl.workflow.query.TaskQuery;
//import com.deloitte.tms.pl.workflow.service.HistoryService;
//import com.deloitte.tms.pl.workflow.service.ProcessService;
//import com.deloitte.tms.pl.workflow.service.StartProcessInfo;
//import com.deloitte.tms.pl.workflow.service.TaskService;
//import com.deloitte.tms.pl.workflow.utils.EnvironmentUtils;
//
///**
// * @author Jacky.gao
// * @since 2013年9月9日
// */
//@Component("uflo.processMaintain")
//public class ProcessMaintain {
//	@Autowired
//	@Qualifier(ProcessService.BEAN_ID)
//	private ProcessService processService;
//
//	@Autowired
//	@Qualifier(TaskService.BEAN_ID)
//	private TaskService taskService;
//
//	@Autowired
//	@Qualifier(HistoryService.BEAN_ID)
//	private HistoryService historyService;
//
//	@Expose
//	public void releaseTask(long taskId){
//		taskService.release(taskId);
//	}
//	
//	@Expose
//	public void changeTaskAssignee(long taskId,String username){
//		taskService.changeTaskAssignee(taskId, username);
//	}
//	
//	@DataProvider
//	public List<Map<String,Object>> loadAvliableAppointAssigneeTaskNodes(long taskId){
//		List<String> names=taskService.getAvaliableAppointAssigneeTaskNodes(taskId);
//		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
//		for(String name:names){
//			Map<String,Object> map=new HashMap<String,Object>();
//			map.put("name", name);
//			list.add(map);
//		}
//		return list;
//	}
//	
//	@Expose
//	public void appointTaskNodeAssignee(long taskId,String assignee,String taskNodeName){
//		taskService.saveTaskAppointor(taskId, assignee, taskNodeName);
//	}
//	
//	@DataProvider
//	public List<Map<String,Object>> loadTaskAssignees(long taskId,String taskNodeName){
//		List<String> names=taskService.getTaskNodeAssignees(taskId, taskNodeName);
//		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
//		for(String name:names){
//			Map<String,Object> map=new HashMap<String,Object>();
//			map.put("name", name);
//			list.add(map);
//		}
//		return list;
//	}
//	
//	@Expose
//	public void addCountersign(long taskId,String username){
//		taskService.addCountersign(taskId, username);
//	}
//	
//	@Expose
//	public void deleteCountersign(long taskId){
//		taskService.deleteCountersign(taskId);
//	}
//	
//	@DataProvider
//	public List<JumpNode> loadAvliableRollbackTasks(long taskId){
//		return taskService.getAvaliableRollbackTaskNodes(taskId);
//	}
//	
//	@DataProvider
//	public List<JumpNode> loadAvliableForwardTasks(long taskId){
//		return taskService.getAvaliableForwardTaskNodes(taskId);
//	}
//	
//	@Expose
//	public void rollbackTask(long taskId,String nodeName){
//		taskService.rollback(taskId, nodeName);
//	}
//	@Expose
//	public void forwardTask(long taskId,String nodeName){
//		taskService.forward(taskId, nodeName);
//	}
//	
//	@Expose
//	public String withdrawTask(long taskId){
//		if(taskService.canWithdraw(taskId)){
//			taskService.withdraw(taskId);
//			return null;
//		}
//		return "当前任务不可撤回";
//	}
//	
//	@DataResolver
//	public void startProcess(long processId, Collection<Map<String, Object>> variableMaps) {
//		StartProcessInfo startProcessInfo = new StartProcessInfo(EnvironmentUtils.getEnvironment().getLoginUser());
//		startProcessInfo.setCompleteStartTask(false);
//		if (variableMaps != null && variableMaps.size() > 0) {
//			Map<String, Object> variables = buildVariables(variableMaps);
//			startProcessInfo.setVariables(variables);
//		}
//		processService.startProcessById(processId, startProcessInfo);
//	}
//
//	@DataProvider
//	public void loadHistoryProcessInstance(Page<HistoryProcessInstance> page, long processId) {
//		HistoryProcessInstanceQuery historyProcessInstanceQuery = historyService.createHistoryProcessInstanceQuery();
//		historyProcessInstanceQuery.processId(processId);
//		historyProcessInstanceQuery.addOrderDesc("createDate");
//		historyProcessInstanceQuery.page((page.getPageNo() - 1) * page.getPageSize(), page.getPageSize());
//		page.setEntityCount(historyProcessInstanceQuery.count());
//		page.setEntities(historyProcessInstanceQuery.list());
//	}
//
//	@DataProvider
//	public List<HistoryTask> loadHistoryTasks(long historyProcessInstanceId) {
//		HistoryTaskQuery historyTaskQuery = historyService.createHistoryTaskQuery();
//		historyTaskQuery.historyProcessInstanceId(historyProcessInstanceId);
//		return historyTaskQuery.list();
//	}
//
//	@DataProvider
//	public List<TaskParticipator> loadTaskParticipators(long taskId) {
//		return taskService.getTaskParticipators(taskId);
//	}
//
//	private Map<String, Object> buildVariables(Collection<Map<String, Object>> variableMaps) {
//		Map<String, Object> variables = new HashMap<String, Object>();
//		for (Map<String, Object> map : variableMaps) {
//			String key = (String) map.get("key");
//			String value = (String) map.get("value");
//			String type = (String) map.get("type");
//			if (type.equals("boolean")) {
//				variables.put(key, Boolean.valueOf(value));
//			} else if (type.equals("int")) {
//				variables.put(key, Integer.valueOf(value));
//			} else {
//				variables.put(key, value);
//			}
//		}
//		return variables;
//	}
//
//	@DataProvider
//	public Collection<SequenceFlowImpl> loadSequenceFlows(long taskId) {
//		Task task = taskService.getTask(taskId);
//		ProcessDefinition pd = processService.getProcessById(task.getProcessId());
//		Node node = pd.getNode(task.getNodeName());
//		List<SequenceFlowImpl> flows=new ArrayList<SequenceFlowImpl>();
//		List<SequenceFlowImpl> list=node.getSequenceFlows();
//		if(list!=null){
//			for(SequenceFlowImpl flow:list){
//				String flowName=flow.getName();
//				if(flowName!=null && !flowName.startsWith(TaskService.TEMP_FLOW_NAME_PREFIX)){
//					flows.add(flow);
//				}
//			}
//		}
//		return flows;
//	}
//
//	@DataProvider
//	public void loadProcess(Page<ProcessDefinition> page) throws Exception {
//		ProcessQuery query = processService.createProcessQuery();
//		query.addOrderDesc("createDate");
//		query.page((page.getPageNo() - 1) * page.getPageSize(), page.getPageSize());
//		page.setEntityCount(query.count());
//		page.setEntities(query.list());
//	}
//
//	@Expose
//	public void deleteProcess(long processId) throws Exception {
//		processService.deleteProcess(processId);
//	}
//
//	@Expose
//	public void deleteProcessInstance(long processInstanceId) throws Exception {
//		processService.deleteProcessInstanceById(processInstanceId);
//	}
//
//	@DataProvider
//	public void loadTopProcessInstance(Page<ProcessInstance> page, long processId) throws Exception {
//		ProcessInstanceQuery query = processService.createProcessInstanceQuery();
//		query.processId(processId);
//		query.parentId(0);
//		query.page((page.getPageNo() - 1) * page.getPageSize(), page.getPageSize());
//		query.addOrderDesc("createDate");
//		page.setEntityCount(query.count());
//		page.setEntities(query.list());
//	}
//
//	@DataProvider
//	public List<ProcessInstance> loadProcessInstanceByParentId(long parentId) throws Exception {
//		ProcessInstanceQuery query = processService.createProcessInstanceQuery();
//		query.parentId(parentId);
//		return query.list();
//	}
//
//	@DataProvider
//	public List<Task> loadTasks(long processInstanceId) {
//		TaskQuery query = taskService.createTaskQuery();
//		query.processInstanceId(processInstanceId);
//		return query.list();
//	}
//
//	@Expose
//	public void claimTask(long taskId, String username) {
//		taskService.claim(taskId, username);
//	}
//
//	@Expose
//	public void startTask(long taskId) {
//		taskService.start(taskId);
//	}
//
//	@DataResolver
//	public void completeTask(long taskId, String sequenceFlowName, List<Map<String, Object>> variableMaps) {
//		if (variableMaps != null && variableMaps.size() > 0) {
//			Map<String, Object> variables = buildVariables(variableMaps);
//			taskService.complete(taskId, sequenceFlowName, variables);
//		} else {
//			taskService.complete(taskId, sequenceFlowName);
//		}
//	}
//	@DataResolver
//	public void completeTask(long taskId,List<Map<String, Object>> variableMaps) {
//		if (variableMaps != null && variableMaps.size() > 0) {
//			Map<String, Object> variables = buildVariables(variableMaps);
//			taskService.complete(taskId, variables);
//		} else {
//			taskService.complete(taskId);
//		}
//	}
//
//}
