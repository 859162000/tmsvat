package com.deloitte.tms.pl.workflow.query;

import java.util.Date;
import java.util.List;

import com.deloitte.tms.pl.workflow.model.HistoryTask;
import com.deloitte.tms.pl.workflow.model.task.TaskState;
import com.deloitte.tms.pl.workflow.model.task.TaskType;

/**
 * @author Jacky.gao
 * @since 2013年8月14日
 */
public interface HistoryTaskQuery extends Query<List<HistoryTask>> {
	HistoryTaskQuery assignee(String assignee);
	HistoryTaskQuery owner(String owner);
	HistoryTaskQuery addTaskState(TaskState state);
	HistoryTaskQuery addPrevTaskState(TaskState state);
	HistoryTaskQuery processInstanceId(long processInstanceId);
	HistoryTaskQuery rootProcessInstanceId(long rootProcessInstanceId);
	HistoryTaskQuery historyProcessInstanceId(long historyProcessInstanceId);
	HistoryTaskQuery createDateLessThen(Date date);
	HistoryTaskQuery createDateLessThenOrEquals(Date date);
	HistoryTaskQuery createDateGreaterThen(Date date);
	HistoryTaskQuery createDateGreaterThenOrEquals(Date date);
	HistoryTaskQuery endDateLessThen(Date date);
	HistoryTaskQuery endDateLessThenOrEquals(Date date);
	HistoryTaskQuery endDateGreaterThen(Date date);
	HistoryTaskQuery endDateGreaterThenOrEquals(Date date);
	HistoryTaskQuery urlLike(String url);
	HistoryTaskQuery countersign(boolean countersign);
	HistoryTaskQuery taskType(TaskType type);
	HistoryTaskQuery processId(long processId);
	HistoryTaskQuery taskId(long taskId);
	HistoryTaskQuery nameLike(String name);
	HistoryTaskQuery businessId(String businessId);
	HistoryTaskQuery nodeName(String nodeName);
	HistoryTaskQuery page(int firstResult, int maxResults);
	HistoryTaskQuery addOrderAsc(String property);
	HistoryTaskQuery addOrderDesc(String property);
}
