package com.deloitte.tms.pl.workflow.query;

import java.util.Date;
import java.util.List;

import com.deloitte.tms.pl.workflow.model.task.Task;
import com.deloitte.tms.pl.workflow.model.task.TaskState;
import com.deloitte.tms.pl.workflow.model.task.TaskType;

/**
 * @author Jacky.gao
 * @since 2013年8月14日
 */
public interface TaskQuery extends Query<List<Task>> {
	TaskQuery assignee(String assignee);
	TaskQuery owner(String owner);
	TaskQuery addTaskState(TaskState state);
	TaskQuery addPrevTaskState(TaskState state);
	TaskQuery processInstanceId(long processInstanceId);
	TaskQuery rootProcessInstanceId(long rootProcessInstanceId);
	TaskQuery createDateLessThen(Date date);
	TaskQuery createDateLessThenOrEquals(Date date);
	TaskQuery createDateGreaterThen(Date date);
	TaskQuery createDateGreaterThenOrEquals(Date date);
	TaskQuery dueDateLessThen(Date date);
	TaskQuery dueDateLessThenOrEquals(Date date);
	TaskQuery dueDateGreaterThen(Date date);
	TaskQuery dueDateGreaterThenOrEquals(Date date);
	TaskQuery urlLike(String url);
	TaskQuery countersign(boolean countersign);
	TaskQuery taskType(TaskType type);
	TaskQuery processId(long processId);
	TaskQuery nameLike(String name);
	TaskQuery nodeName(String nodeName);
	TaskQuery businessId(String businessId);
	TaskQuery page(int firstResult, int maxResults);
	TaskQuery addOrderAsc(String property);
	TaskQuery addOrderDesc(String property);
	TaskQuery addParticipator(String user);
}
