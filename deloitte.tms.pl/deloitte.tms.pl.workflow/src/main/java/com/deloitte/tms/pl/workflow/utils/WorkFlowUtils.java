package com.deloitte.tms.pl.workflow.utils;

import java.util.List;

import com.deloitte.tms.pl.core.commons.exception.BusinessException;
import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.core.commons.utils.SpringUtil;
import com.deloitte.tms.pl.workflow.model.ProcessInstance;
import com.deloitte.tms.pl.workflow.model.task.Task;
import com.deloitte.tms.pl.workflow.model.task.TaskState;
import com.deloitte.tms.pl.workflow.query.TaskQuery;
import com.deloitte.tms.pl.workflow.service.ProcessService;
import com.deloitte.tms.pl.workflow.service.StartProcessInfo;
import com.deloitte.tms.pl.workflow.service.TaskService;

/**
 * 工作流 工具类
 * @author jasonbwang
 *
 */
public class WorkFlowUtils {

	private static TaskService taskService;
	private static ProcessService processService;
	
	public static TaskService getTaskService() {
		if(taskService==null){
			taskService= SpringUtil.getBean(TaskService.BEAN_ID);
		}
		return taskService;
	}
	
	public static ProcessService getProcessService() {
		if(processService==null){
			processService= SpringUtil.getBean(ProcessService.BEAN_ID);
		}
		return processService;
	}
	/**
	 * 完成任务，返回任务详细信息
	 * @param taskId
	 * @param flag
	 * @return
	 */
	public static TaskTesult completeTask(Long taskId,String flag){
		System.out.println("完成任务开始");
		TaskTesult taskTesult=new TaskTesult();
		Task task;
		try {			
			task=getTaskService().getTask(taskId);
			taskTesult.setCurrentTask(task);
//			if("3".equals(flag)){//				
				Long instanceid=task.getProcessInstanceId();
				if(TaskState.Created==task.getState()){
					getTaskService().start(taskId);
				}					
				getTaskService().complete(taskId);
				TaskQuery query = getTaskService().createTaskQuery();
				query.processInstanceId(instanceid);
				List<Task> tasks=query.list();
				if(tasks.size()>0){
					Task nextTask=tasks.get(0);
					taskTesult.setNextTask(nextTask);
					System.out.println("完成任务结束,返回下一个任务id"+nextTask.getId());
				}				
//			}else if ("3".equals(flag)) {				
//				getTaskService().start(taskId);	
//				getTaskService().complete(taskId);
//			}		
			
		} catch (Exception e) {
			System.out.println("完成任务失败");
			throw new BusinessException("任务启动失败: "+e.getLocalizedMessage());
		}
		taskTesult.setStatu(true);
		return taskTesult;
	}
	/**
	 * 完成任务，返回下一个任务id，如果返回为空，表示任务结束
	 * @param taskId
	 * @return
	 */
	public static Long completeTask(long taskId) {
		TaskTesult taskTesult=completeTask(taskId, null);
		if(taskTesult.getNextTask()!=null){
			return taskTesult.getNextTask().getId();
		}
		return null;
	}
	/**
	 * 根据processkey 开启一个新流程实例
	 * @param processkey
	 * @return
	 */
	public static Long startFlowByKey(String processkey,String userName,String businessId) {
		AssertHelper.notEmpty_assert(processkey,"流程关键字不能为空");
//		AssertHelper.notEmpty_assert(userName,"流程负责人不能为空");
		AssertHelper.notEmpty_assert(businessId,"流程业务主键不能为空");
		System.out.println("任务启动开始");
		Long taskid=null;
		try {
			StartProcessInfo startProcessInfo = new StartProcessInfo(userName);
			startProcessInfo.setCompleteStartTask(true);
			startProcessInfo.setBusinessId(businessId);
//			String processkey="gps-customerchange-contactinfo-1";
			ProcessInstance instance=getProcessService().startProcessByName(processkey, startProcessInfo);
			TaskQuery query = getTaskService().createTaskQuery();
			query.processInstanceId(instance.getId());
			List<Task> tasks=query.list();
			if(tasks.size()>0){
				taskid = tasks.get(0).getId();
				System.out.println("任务启动成功 返回任务id:"+taskid);
			}	
			
		} catch (Exception e) {
			System.out.println("任务启动失败 ");
			e.printStackTrace();
		}		
		return taskid;		
	}
}
