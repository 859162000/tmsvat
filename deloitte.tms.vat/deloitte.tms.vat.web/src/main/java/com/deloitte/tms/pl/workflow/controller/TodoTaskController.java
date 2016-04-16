package com.deloitte.tms.pl.workflow.controller;
//package com.deloitte.tms.pl.workflow.console.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.deloitte.tms.pl.core.commons.exception.BusinessException;
import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.commons.utils.PageUtils;
import com.deloitte.tms.pl.core.commons.utils.reflect.ReflectUtils;
import com.deloitte.tms.pl.core.context.utils.ContextUtils;
import com.deloitte.tms.pl.workflow.model.task.Task;
import com.deloitte.tms.pl.workflow.model.task.TaskState;
import com.deloitte.tms.pl.workflow.model.variable.Variable;
import com.deloitte.tms.pl.workflow.query.HistoryTaskQuery;
import com.deloitte.tms.pl.workflow.query.TaskQuery;
import com.deloitte.tms.pl.workflow.service.HistoryService;
import com.deloitte.tms.pl.workflow.service.TaskService;
import com.deloitte.tms.pl.workflow.utils.IDGenerator;
import com.deloitte.tms.pl.workflow.utils.TaskTesult;
import com.deloitte.tms.pl.workflow.utils.WorkFlowUtils;
import com.deloitte.tms.vat.controller.BaseController;
import com.deloitte.tms.vat.core.common.JsonDateValueProcessor;
import com.deloitte.tms.vat.salesinvoice.model.TaskInParam;

/**
 * 
 * @author Jake.Wang@bstek.com
 * @since Sep 22, 2013
 * 
 */
@Controller
public class TodoTaskController extends BaseController{
	@Resource
	private TaskService taskService;


	@Resource
	private HistoryService historyService;
	
	@RequestMapping(value = "/todoTaskInit", method = RequestMethod.GET)
	public String jobDefinitionsInit()throws Exception{
		return "workflow/workflow";
	}

	@RequestMapping(value = "toDoTask/getTodoTask",method=RequestMethod.POST)
	public void loadTodoTask(HttpServletResponse response,@RequestParam("pageNumber") String pageNumber,@RequestParam("pageSize") String pageSize) throws IOException {
		JSONObject result = new JSONObject();
		TaskQuery todoQuery = taskService.createTaskQuery();
		if(ContextUtils.getCurrentUser()!=null){
			todoQuery.assignee(ContextUtils.getCurrentUserCode());
		}
		//todoQuery.assignee("admin");
		int pageNumberInt =  Integer.valueOf(pageNumber)-1;
		int pageSizeInt = Integer.valueOf(pageSize);
		DaoPage daoPage=new DaoPage();
		todoQuery.page(pageNumberInt * pageSizeInt, pageSizeInt);
		daoPage.setRecordCount(todoQuery.count());//todoQuery.
		List<Task> list = todoQuery.list();
		List<TaskInParam> taskInParams = new ArrayList<TaskInParam>();
		for(Task task:list){
			TaskInParam taskInParam = new TaskInParam();
			ReflectUtils.copyProperties(task, taskInParam);
			String id = String.valueOf(task.getId());
			taskInParam.setId(id);
			taskInParam.setState(task.getState().name());
			taskInParam.setType(task.getType().name());			
			taskInParams.add(taskInParam);
		}
		daoPage.setResult(taskInParams);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class,
				new JsonDateValueProcessor("yyyy-MM-dd HH:mm:ss"));
		JSONArray jsonArray = JSONArray.fromObject(daoPage.getResult(), jsonConfig);		
		result.put("total", daoPage.getRecordCount());
		result.put("rows", jsonArray);
		result.put("success", true);
		retJson(response, result);
	}
	
	@RequestMapping(value = "toDoTask/loadhistory",method=RequestMethod.POST)
	public void loadHistoryTask(HttpServletResponse response,@RequestParam("pageNumber") String pageNumber,@RequestParam("pageSize") String pageSize) throws IOException {
		JSONObject result = new JSONObject();
		HistoryTaskQuery historyTaskQuery = historyService.createHistoryTaskQuery();
		DaoPage daoPage=new DaoPage();
		int pageNumberInt =  Integer.valueOf(pageNumber)-1;
		int pageSizeInt = Integer.valueOf(pageSize);
		historyTaskQuery.assignee(ContextUtils.getCurrentUserCode());
		historyTaskQuery.page(pageNumberInt * pageSizeInt, pageSizeInt);
		daoPage.setRecordCount(historyTaskQuery.count());//todoQuery.
		daoPage.setResult(historyTaskQuery.list());
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class,
				new JsonDateValueProcessor("yyyy-MM-dd HH:mm:ss"));
		JSONArray jsonArray = JSONArray.fromObject(daoPage.getResult(), jsonConfig);		
		result.put("total", daoPage.getRecordCount());
		result.put("rows", jsonArray);
		result.put("success", true);
		retJson(response, result);
	}
	
	@RequestMapping(value = "toDoTask/executeTask", method = RequestMethod.POST)
	public void executeTask(HttpServletResponse response,@RequestParam Map<String, Object> map) throws Exception {
		String taskId=(String)map.get("taskId");
		String[] ids = taskId.split(",");
		for(String id:ids){
			WorkFlowUtils.completeTask(Long.parseLong(id));
		}		
		JSONObject result = new JSONObject();
		result.put("success", true);
		retJson(response, result);
	}
	@ResponseBody
	@RequestMapping(value = "/executeTestTask", method = RequestMethod.POST)
	public void executeTestTask(@RequestParam Map<String, Object> map) throws Exception {
		WorkFlowUtils.startFlowByKey("salesinvoice", "admin", 1+"");
	}
	@ResponseBody
	@RequestMapping(value = "/testTask", method = RequestMethod.POST)
	public void testTask(@RequestParam String type,@RequestParam String taskId) throws Exception {
		System.out.println("完成任务开始");
		TaskTesult taskTesult=new TaskTesult();
		Task task;
		Long taskidLong=Long.parseLong(taskId);
		Map<String, String> params=new HashMap();
		params.put("type", taskId);
		try {			
			task=taskService.getTask(taskidLong);
			if(params!=null){
				for(String key:params.keySet()){
					Variable variable=Variable.newVariable(params.get(key),null);
					variable.setId(IDGenerator.getInstance().nextId());
					variable.setKey(key);
					variable.setProcessInstanceId(task.getProcessInstanceId());
					variable.setRootProcessInstanceId(task.getRootProcessInstanceId());
				}
			}
			taskTesult.setCurrentTask(task);
//			if("3".equals(flag)){//				
				Long instanceid=task.getProcessInstanceId();
				if(TaskState.Created==task.getState()){
					taskService.start(taskidLong);
				}					
				taskService.complete(taskidLong);
				TaskQuery query = taskService.createTaskQuery();
				query.processInstanceId(instanceid);
				List<Task> tasks=query.list();
				if(tasks.size()>0){
					Task nextTask=tasks.get(0);
					taskTesult.setNextTask(nextTask);
					System.out.println("完成任务结束,返回下一个任务id"+nextTask.getId());
				}				
//			}else if ("3".equals(flag)) {				
//				taskService.start(taskId);	
//				taskService.complete(taskId);
//			}		
			
		} catch (Exception e) {
			System.out.println("完成任务失败");
			throw new BusinessException("任务启动失败: "+e.getLocalizedMessage());
		}
		taskTesult.setStatu(true);
	}
}
