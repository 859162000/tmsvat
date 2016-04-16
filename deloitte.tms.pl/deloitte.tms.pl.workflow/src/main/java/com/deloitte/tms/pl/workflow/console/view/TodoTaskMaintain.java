//package com.deloitte.tms.pl.workflow.console.view;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Component;
//
//import com.bstek.dorado.annotation.DataProvider;
//import com.bstek.dorado.annotation.Expose;
//import com.bstek.dorado.core.Configure;
//import com.bstek.dorado.data.provider.Criteria;
//import com.bstek.dorado.data.provider.Criterion;
//import com.bstek.dorado.data.provider.Order;
//import com.bstek.dorado.data.provider.Page;
//import com.bstek.dorado.data.provider.filter.SingleValueFilterCriterion;
//import com.deloitte.tms.pl.workflow.model.HistoryTask;
//import com.deloitte.tms.pl.workflow.model.task.Task;
//import com.deloitte.tms.pl.workflow.model.task.TaskState;
//import com.deloitte.tms.pl.workflow.model.task.TaskType;
//import com.deloitte.tms.pl.workflow.query.HistoryTaskQuery;
//import com.deloitte.tms.pl.workflow.query.TaskQuery;
//import com.deloitte.tms.pl.workflow.service.HistoryService;
//import com.deloitte.tms.pl.workflow.service.TaskService;
//import com.deloitte.tms.pl.workflow.utils.EnvironmentUtils;
//
///**
// * 
// * @author Jake.Wang@bstek.com
// * @since Sep 22, 2013
// * 
// */
//@Component("uflo.todoTaskMaintain")
//public class TodoTaskMaintain {
//	@Autowired
//	@Qualifier(TaskService.BEAN_ID)
//	private TaskService taskService;
//
//	@Autowired
//	@Qualifier(HistoryService.BEAN_ID)
//	private HistoryService historyService;
//
//	@DataProvider
//	public void loadTodoTask(Page<Task> page, Criteria criteria) {
//		TaskQuery todoQuery = taskService.createTaskQuery();
//		if (criteria == null || criteria.getCriterions().isEmpty()) {
//			todoQuery.assignee(EnvironmentUtils.getEnvironment().getLoginUser());
//		}
//		buildTodoCriteriaQuery(criteria, todoQuery);
//		query(page, criteria, todoQuery);
//	}
//
//	@DataProvider
//	public void loadParticipatingTask(Page<Task> page, Criteria criteria) {
//		TaskQuery todoQuery = taskService.createTaskQuery();
//		todoQuery.addParticipator(EnvironmentUtils.getEnvironment().getLoginUser());
//		todoQuery.addTaskState(TaskState.Ready);
//		buildParticipatingCriteriaQuery(criteria, todoQuery);
//		query(page, criteria, todoQuery);
//	}
//
//	private void buildParticipatingCriteriaQuery(Criteria criteria, TaskQuery todoQuery) {
//		Map<String, String> propertyValueMap = getPropertyValueMap(criteria);
//		for (String key : propertyValueMap.keySet()) {
//			todoQuery.nameLike("%" + propertyValueMap.get(key) + "%");
//		}
//
//		buildTodoCriteriaOrderQuery(criteria, todoQuery);
//	}
//
//	@DataProvider
//	public void loadExpiredTask(Page<Task> page, Criteria criteria) {
//		TaskQuery todoQuery = taskService.createTaskQuery();
//		todoQuery.dueDateLessThen(new Date());
//		todoQuery.assignee(EnvironmentUtils.getEnvironment().getLoginUser());
//		buildExpiredCriteriaQuery(criteria, todoQuery);
//		query(page, criteria, todoQuery);
//	}
//
//	private void buildExpiredCriteriaQuery(Criteria criteria, TaskQuery todoQuery) {
//		Map<String, String> propertyValueMap = getPropertyValueMap(criteria);
//		for (String key : propertyValueMap.keySet()) {
//			todoQuery.nameLike("%" + propertyValueMap.get(key) + "%");
//		}
//
//		buildTodoCriteriaOrderQuery(criteria, todoQuery);
//	}
//
//	private void query(Page<Task> page, Criteria criteria, TaskQuery todoQuery) {
//		todoQuery.page((page.getPageNo() - 1) * page.getPageSize(), page.getPageSize());
//		page.setEntityCount(todoQuery.count());
//		page.setEntities(todoQuery.list());
//	}
//
//	private void buildTodoCriteriaQuery(Criteria criteria, TaskQuery todoQuery) {
//		Map<String, String> propertyValueMap = getPropertyValueMap(criteria);
//		for (String key : propertyValueMap.keySet()) {
//			if ("taskName".equals(key)) {
//				todoQuery.nameLike("%" + propertyValueMap.get(key) + "%");
//				todoQuery.assignee(EnvironmentUtils.getEnvironment().getLoginUser());
//			} else {
//				String value = propertyValueMap.get(key);
//				if (TaskType.Participative.name().equals(value)) {
//					todoQuery.addParticipator(EnvironmentUtils.getEnvironment().getLoginUser());
//					todoQuery.assignee(EnvironmentUtils.getEnvironment().getLoginUser());
//				} else if (TaskType.Normal.name().equals(value)) {
//					todoQuery.assignee(EnvironmentUtils.getEnvironment().getLoginUser());
//					todoQuery.taskType(TaskType.Normal);
//				} else if (TaskType.Countersign.name().equals(value)) {
//					todoQuery.assignee(EnvironmentUtils.getEnvironment().getLoginUser());
//					todoQuery.taskType(TaskType.Countersign);
//				}
//			}
//		}
//
//		buildTodoCriteriaOrderQuery(criteria, todoQuery);
//	}
//
//	private void buildTodoCriteriaOrderQuery(Criteria criteria, TaskQuery todoQuery) {
//		Map<String, Boolean> propertyOrderMap = getQueryOrder(criteria);
//		for (String property : propertyOrderMap.keySet()) {
//			if (propertyOrderMap.get(property)) {
//				todoQuery.addOrderAsc(property);
//			} else {
//				todoQuery.addOrderDesc(property);
//			}
//		}
//		if (propertyOrderMap.isEmpty()) {
//			todoQuery.addOrderAsc("duedate");
//			todoQuery.addOrderDesc("createDate");
//		}
//	}
//
//	@Expose
//	public Long getTaskAlarmTime() {
//		Long millisecondsBeforeDueDateToRemind = Configure.getLong("uflo.minutesBeforeDueDateToRemind") * 60 * 1000;
//		return System.currentTimeMillis() + millisecondsBeforeDueDateToRemind;
//	}
//
//	@DataProvider
//	public void loadHistoryTask(Page<HistoryTask> page, Criteria criteria) {
//		HistoryTaskQuery historyTaskQuery = historyService.createHistoryTaskQuery();
//		buildHistoryCriteriaQuery(criteria, historyTaskQuery);
//		historyTaskQuery.assignee(EnvironmentUtils.getEnvironment().getLoginUser());
//		historyTaskQuery.page((page.getPageNo() - 1) * page.getPageSize(), page.getPageSize());
//		page.setEntityCount(historyTaskQuery.count());
//		page.setEntities(historyTaskQuery.list());
//	}
//
//	private void buildHistoryCriteriaQuery(Criteria criteria, HistoryTaskQuery historyTaskQuery) {
//		Map<String, String> propertyValueMap = getPropertyValueMap(criteria);
//		for (String key : propertyValueMap.keySet()) {
//			if ("taskName".equals(key)) {
//				historyTaskQuery.nameLike("%" + propertyValueMap.get(key) + "%");
//				break;
//			}
//		}
//
//		Map<String, Boolean> propertyOrderMap = getQueryOrder(criteria);
//		for (String property : propertyOrderMap.keySet()) {
//			if (propertyOrderMap.get(property)) {
//				historyTaskQuery.addOrderAsc(property);
//			} else {
//				historyTaskQuery.addOrderDesc(property);
//			}
//		}
//		if (propertyOrderMap.isEmpty()) {
//			historyTaskQuery.addOrderDesc("createDate");
//		}
//	}
//
//	private Map<String, String> getPropertyValueMap(Criteria criteria) {
//		Map<String, String> propertyValueMap = new HashMap<String, String>();
//		if (criteria == null) {
//			return propertyValueMap;
//		}
//		List<Criterion> criterions = criteria.getCriterions();
//		for (Criterion criterion : criterions) {
//			if (criterion instanceof SingleValueFilterCriterion) {
//				SingleValueFilterCriterion singleValueFilterCriterion = (SingleValueFilterCriterion) criterion;
//				propertyValueMap.put(singleValueFilterCriterion.getProperty(), (String) singleValueFilterCriterion.getValue());
//			}
//		}
//		return propertyValueMap;
//	}
//
//	private Map<String, Boolean> getQueryOrder(Criteria criteria) {
//		Map<String, Boolean> propertyOrderMap = new HashMap<String, Boolean>();
//		if (criteria == null) {
//			return propertyOrderMap;
//		}
//		List<Order> orderList = criteria.getOrders();
//		for (int i = 0; i < orderList.size(); i++) {
//			Order order = orderList.get(i);
//			propertyOrderMap.put(order.getProperty(), order.isDesc() ? false : true);
//		}
//		return propertyOrderMap;
//	}
//
//	@Expose
//	public void claimTask(long taskId) {
//		taskService.claim(taskId, EnvironmentUtils.getEnvironment().getLoginUser());
//	}
//
//	@Expose
//	public void releaseTask(long taskId) {
//		taskService.release(taskId);
//	}
//}
