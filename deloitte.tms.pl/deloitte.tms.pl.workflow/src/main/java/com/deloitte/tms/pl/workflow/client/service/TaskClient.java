package com.deloitte.tms.pl.workflow.client.service;

import java.util.List;
import java.util.Map;

import com.deloitte.tms.pl.workflow.command.impl.jump.JumpNode;
import com.deloitte.tms.pl.workflow.model.task.Task;
import com.deloitte.tms.pl.workflow.model.task.TaskAppointor;
import com.deloitte.tms.pl.workflow.model.task.TaskParticipator;
import com.deloitte.tms.pl.workflow.model.task.reminder.TaskReminder;
import com.deloitte.tms.pl.workflow.query.TaskQuery;
import com.deloitte.tms.pl.workflow.service.TaskOpinion;

/**
 * @author Jacky.gao
 * @since 2013年9月22日
 */
public interface TaskClient {
	public static final String BEAN_ID="uflo.taskClient";
	
	/**
	 * 设置处理处理进度值，正常情况下该值应该在0~100之间，同时当任务正常完成时，任务进度将自动设置为100
	 * @param progress 任务进度值
	 */
	void setProgress(int progress);
	
	/**
	 * 根据给出的任务ID，获取当前任务节点下可指定任务处理人的任务节点名
	 * @param taskId 任务ID
	 * @return 可指定任务处理人的任务节点名列表
	 */
	List<String> getAvaliableAppointAssigneeTaskNodes(long taskId);
	
	/**
	 * 获取指定任务节点下配置的任务处理人列表
	 * @param taskId 任务ID
	 * @param taskNodeName 任务节点名称
	 * @return 返回当前节点配置的任务处理人列表
	 */
	List<String> getTaskNodeAssignees(long taskId,String taskNodeName);

	/**
	 * 在某个任务中指定下一个指定任务节点上的任务处理人
	 * @param taskId 具体任务对象ID
	 * @param assignee 要指定的任务处理人
	 * @param taskNodeName 指定任务处理人的任务节点名称
	 */
	void saveTaskAppointor(long taskId,String assignee,String taskNodeName);
	
	/**
	 * 在某个任务中指定下一个指定任务节点上的任务处理人,可以为多个处理人
	 * @param taskId 具体任务对象ID
	 * @param assignees 要指定的任务处理人集合
	 * @param taskNodeName 指定任务处理人的任务节点名称
	 */
	void saveTaskAppointor(long taskId,String[] assignees,String taskNodeName);
	
	/**
	 * 向现有的会签任务中再添加一个新的会签任务
	 * @param taskId 参考的任务ID
	 * @param username 新的任务的处理人
	 * @return 返回加签产生的新的任务对象
	 */
	Task addCountersign(long taskId,String username);
	
	/**
	 * 删除一个会签任务
	 * @param taskId 要删除的会签任务的ID
	 */
	void deleteCountersign(long taskId);

	/**
	 * 完成指定ID的任务，回退到指定的目标节点，同时设置回写到流程实例中的变量集合
	 * @param taskId 任务ID
	 * @param targetNodeName 指定的目标节点名称
	 * @param variables 变量集合
	 * @param opinion 任务处理意见
	 */
	void rollback(long taskId,String targetNodeName,Map<String,Object> variables,TaskOpinion opinion);
	
	/**
	 * 完成指定ID的任务，回退到指定的目标节点，同时设置回写到流程实例中的变量集合
	 * @param task 任务对象
	 * @param targetNodeName 指定的目标节点名称
	 * @param variables 变量集合
	 * @param opinion 任务处理意见
	 */
	void rollback(Task task,String targetNodeName,Map<String,Object> variables,TaskOpinion opinion);
	
	/**
	 * 完成指定ID的任务，回退到指定的目标节点，同时设置回写到流程实例中的变量集合
	 * @param taskId 任务ID
	 * @param targetNodeName 指定的目标节点名称
	 * @param variables 变量集合
	 */
	void rollback(long taskId,String targetNodeName,Map<String,Object> variables);
	
	/**
	 * 完成指定ID的任务，回退到指定的目标节点
	 * @param taskId 任务ID
	 * @param targetNodeName 指定的目标节点名称
	 * @param variables 变量集合
	 */
	void rollback(long taskId,String targetNodeName);
	
	/**
	 * 获取当前任务可以跳转的任务节点名称
	 * @param taskId 任务ID
	 * @return 可跳转的目标任务节点集合
	 */
	List<JumpNode> getAvaliableForwardTaskNodes(long taskId);
	
	/**
	 * 获取指定任务ID对应的可回退的目标任务节点名列表
	 * @param taskId 要回退的任务ID
	 * @return 返回可回退的目标任务节点名列表
	 */
	List<JumpNode> getAvaliableRollbackTaskNodes(long taskId);
	
	/**
	 * 批量完成指定ID的任务，并写入指定的流程变量
	 * @param taskIds 要完成的任务的ID集合
	 * @param variables 回写到要完成任务的变量集合
	 */
	void batchComplete(List<Long> taskIds,Map<String,Object> variables);
	
	/**
	 * 批量开始一批任务
	 * @param taskIds 要开始的任务的ID集合
	 */
	void batchStart(List<Long> taskIds);
	
	/**
	 * 批量开始并完成一批指定的任务，并可写入指定的流程变量
	 * @param taskIds 要完成的任务的ID集合
	 * @param variables 要回写的流程变量
	 */
	void batchStartAndComplete(List<Long> taskIds,Map<String,Object> variables);
	
	/**
	 * 完成指定ID的任务，同时设置下一步流向名称
	 * @param taskId 要完成的任务ID
	 * @param flowName 下一步流向名称
	 */
	void complete(long taskId, String flowName);
	
	/**
	 * 批量完成指定ID的任务，并写入指定的流程变量
	 * @param taskIds 要完成的任务的ID集合
	 * @param variables 回写到要完成任务的变量集合
	 * @param opinion 任务处理意见
	 */
	void batchComplete(List<Long> taskIds,Map<String,Object> variables,TaskOpinion opinion);


	/**
	 * 批量开始并完成一批指定的任务，并可写入指定的流程变量
	 * @param taskIds 要完成的任务的ID集合
	 * @param variables 要回写的流程变量
	 * @param opinion 任务处理意见
	 */
	void batchStartAndComplete(List<Long> taskIds,Map<String,Object> variables,TaskOpinion opinion);

	/**
	 * 完成指定ID的任务，同时设置下一步流向名称
	 * @param taskId 要完成的任务ID
	 * @param flowName 下一步流向名称
	 * @param opinion 任务处理意见
	 */
	void complete(long taskId, String flowName,TaskOpinion opinion);
	
	/**
	 * 完成指定ID的任务，同时设置下一步流向名称及回写到流程实例中的变量集合
	 * @param taskId 任务ID
	 * @param flowName 下一步流向名称
	 * @param variables 回写的变量集合
	 * @param opinion 任务处理意见
	 */
	void complete(long taskId, String flowName,Map<String,Object> variables,TaskOpinion opinion);
	
	
	/**
	 * 完成指定ID的任务
	 * @param taskId 要完成的任务ID
	 * @param opinion 任务处理意见
	 */
	void complete(long taskId,TaskOpinion opinion);
	
	/**
	 * 完成指定ID的任务，同时设置回写到流程实例中的变量集合
	 * @param taskId 任务ID
	 * @param variables 变量集合
	 * @param opinion 任务处理意见
	 */
	void complete(long taskId,Map<String,Object> variables,TaskOpinion opinion);
	
	/**
	 * 完成指定ID的任务，同时设置下一步流向名称及回写到流程实例中的变量集合
	 * @param taskId 任务ID
	 * @param flowName 下一步流向名称
	 * @param variables 回写的变量集合
	 */
	void complete(long taskId, String flowName,Map<String,Object> variables);
	
	/**
	 * 完成指定ID的任务
	 * @param taskId 要完成的任务ID
	 */
	void complete(long taskId);
	
	/**
	 * 完成指定ID的任务，同时设置回写到流程实例中的变量集合
	 * @param taskId 任务ID
	 * @param variables 变量集合
	 */
	void complete(long taskId,Map<String,Object> variables);
	
	/**
	 * 完成指定ID的任务，跳转到指定的目标节点
	 * @param taskId 任务ID
	 * @param targetNodeName 指定的目标节点名称
	 */
	void forward(long taskId,String targetNodeName);
	
	/**
	 * 完成指定ID的任务，跳转到指定的目标节点
	 * @param taskId 任务ID
	 * @param targetNodeName 指定的目标节点名称
	 * @param opinion 任务处理意见
	 */
	void forward(long taskId,String targetNodeName,TaskOpinion opinion);
	
	
	/**
	 * 完成指定ID的任务，跳转到指定的目标节点，同时设置回写到流程实例中的变量集合
	 * @param taskId 任务ID
	 * @param targetNodeName 指定的目标节点名称
	 * @param variables 变量集合
	 * @param opinion 任务处理意见
	 */
	void forward(long taskId,String targetNodeName,Map<String,Object> variables,TaskOpinion opinion);
	
	/**
	 * 完成指定ID的任务，跳转到指定的目标节点，同时设置回写到流程实例中的变量集合
	 * @param taskId 任务ID
	 * @param targetNodeName 指定的目标节点名称
	 * @param variables 变量集合
	 */
	void forward(long taskId,String targetNodeName,Map<String,Object> variables);
	
	/**
	 * 将指定ID的任务撤回到上一个任务节点，并填充变量
	 * @param taskId 任务的ID
	 * @param variables 变量集合
	 */
	void withdraw(long taskId,Map<String,Object> variables);
	
	/**
	 * 将指定ID的任务撤回到上一个任务节点
	 * @param taskId 任务的ID
	 */
	void withdraw(long taskId);
	
	/**
	 * 将指定ID的任务撤回到上一个任务节点
	 * @param taskId 任务的ID
	 * @param opinion 任务处理意见
	 */
	void withdraw(long taskId,TaskOpinion opinion);
	
	/**
	 * 将指定ID的任务撤回到上一个任务节点，并填充变量
	 * @param taskId 任务的ID
	 * @param variables 变量集合
	 * @param opinion 任务处理意见
	 */
	void withdraw(long taskId,Map<String,Object> variables,TaskOpinion opinion);
	
	/**
	 * 判断当前任务是否可回退到上一任务节点
	 * @param taskId 任务的ID
	 * @return
	 */
	boolean canWithdraw(long taskId);
	
	/**
	 * 根据ID获取一个任务对象
	 * @param taskId 任务ID
	 * @return 返回任务对象
	 */
	Task getTask(long taskId);
	
	/**
	 * 认领一个任务
	 * @param taskId 要认领任务的ID
	 * @param username 认领任务的人的用户名
	 */
	void claim(long taskId, String username);
	/**
	 * 对认领后的任务进行释放，从而允许其它人认领
	 * @param taskId 要释放任务的ID
	 * @param username 任务的释放人
	 */
	void release(long taskId);
	/**
	 * 开始处理一个任务
	 * @param taskId 任务的ID
	 */
	void start(long taskId);
	/**
	 * 将一个任务挂起
	 * @param taskId 要挂起的任务的ID
	 * @param username 扶起任务的人的用户名
	 */
	void suspend(long taskId);
	/**
	 * 让处于挂起状态的任务恢复正常
	 * @param taskId 要操作的任务的ID
	 * @param username
	 */
	void resume(long taskId);
	
	/**
	 * 根据任务ID取得当前任务潜在处理人列表
	 * @param taskId 任务ID
	 * @return 处理人列表
	 */
	List<TaskParticipator> getTaskParticipators(long taskId);
	
	/**
	 * 获取指定流程实例下任务节点的通过指派的任务处理人信息
	 * @param taskNodeName 任务节点名称
	 * @param processInstanceId 流程实例ID
	 * @return TaskAppointor集合
	 */
	List<TaskAppointor> getTaskAppointors(String taskNodeName,long processInstanceId);

	/**
	 * 更改任务处理人
	 * @param taskId 任务ID
	 * @param username 新的处理人用户名
	 */
	void changeTaskAssignee(long taskId,String username);
	
	/**
	 * 查找指定任务节点上指定key对象的UserData的值
	 * @param task 任务对象
	 * @param key UserData的key值
	 * @return UserData对应的value值
	 */
	String getUserData(Task task,String key);
	
	/**
	 * 查找指定任务节点上指定key对象的UserData的值
	 * @param processId 流程模版ID
	 * @param taskNodeName 任务节点名称
	 * @param key UserData的key值
	 * @return UserData对应的value值
	 */
	String getUserData(long processId,String taskNodeName,String key);
	
	TaskQuery createTaskQuery();
	
	List<TaskReminder> getTaskReminders(long taskId);
	
	void deleteTaskReminder(long taskReminderId);
	
	List<TaskReminder> getAllTaskReminders();
}
