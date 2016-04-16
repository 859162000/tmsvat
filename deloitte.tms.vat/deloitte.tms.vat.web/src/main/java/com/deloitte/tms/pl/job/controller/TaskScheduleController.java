package com.deloitte.tms.pl.job.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;





import net.sf.json.JsonConfig;

import org.quartz.Job;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.deloitte.tms.base.masterdata.model.ClientInParam;
import com.deloitte.tms.pl.core.commons.exception.BusinessException;
import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.core.commons.utils.PageUtils;
import com.deloitte.tms.pl.core.commons.utils.SpringUtil;
import com.deloitte.tms.pl.core.commons.utils.reflect.ReflectUtils;
import com.deloitte.tms.pl.job.model.JobDefinition;
import com.deloitte.tms.pl.job.model.JobDefinitionInParam;
import com.deloitte.tms.pl.job.model.JobHistory;
import com.deloitte.tms.pl.job.model.JobState;
import com.deloitte.tms.pl.job.service.IJobDefinitionService;
import com.deloitte.tms.pl.job.task.JobTest;
import com.deloitte.tms.vat.controller.BaseController;
import com.deloitte.tms.vat.core.common.IdGenerator;
import com.deloitte.tms.vat.core.common.JsonDateValueProcessor;
@Controller
@RequestMapping("jobs")
public class TaskScheduleController extends BaseController{
	@Resource
	IJobDefinitionService jobDefinitionService;
	/**
	 * jobs数据加载
	 * @param response
	 * @param parameter
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/getJobDefinitions")
	public DaoPage loadJobDefinitions(HttpServletResponse response,@RequestParam Map<String,Object> parameter) throws Exception {
		
		DaoPage daoPage=jobDefinitionService.loadJobs(parameter, PageUtils.getPageNumber(parameter), PageUtils.getPageSize(parameter));//(parameter,PageUtils.getPageNumber(parameter),PageUtils.getPageSize(parameter));
		JSONObject result = new JSONObject();
		result.put("total", daoPage.getRecordCount());
		result.put("rows", daoPage.getResult());//daoPage.getr
		result.put("pages", daoPage.getPageCount());
		result.put("success", true);
		retJson(response, result);
		return null;
	}
	/**
	 * 显示历史jobs信息
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/loadhistory")
	public DaoPage getJobByName(HttpServletResponse response,@RequestParam Map<String,Object> parameter) throws Exception {
		StringBuffer sqlBuffer = new StringBuffer();
		System.out.println((String) parameter.get("id"));
		DaoPage  daoPage=jobDefinitionService.getJobByName(parameter,Integer.parseInt(parameter.get("page").toString()),Integer.parseInt(parameter.get("rows").toString()));
		JSONObject result = new JSONObject();
		 JsonConfig jsonConfig = new JsonConfig();
		 jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor()); 
		 JSONArray jsonArray = JSONArray.fromObject(daoPage.getResult(),jsonConfig);
		result.put("total", daoPage.getRecordCount());
		result.put("rows", jsonArray);// daoPage.getr
		result.put("pages", daoPage.getPageCount());
		result.put("success", true);
		retJson(response, result);
		return null;
	}
	
	@RequestMapping(value = "/jobDefinitionsInit", method = RequestMethod.GET)
	public String jobDefinitionsInit()throws Exception{
		return "vat/job/taskSchedule";
	}
	
	@ResponseBody
	@RequestMapping(value = "/getJobDefinitionsList")
	public List<JobDefinition> getJobDefinitionsList(HttpServletResponse response,@RequestParam Map<String,Object> parameter)throws Exception{
		List<JobDefinition> list = new ArrayList<JobDefinition>();
		JobDefinition jobDefinition = new JobDefinition();
		jobDefinition.setId("1");
		jobDefinition.setName("hello job");;
		list.add(jobDefinition);
		
		JobDefinition jobDefinition1 = new JobDefinition();
		jobDefinition1.setId("2");
		jobDefinition1.setName("hello job1");;
		list.add(jobDefinition1);
		
		JobDefinition jobDefinition2 = new JobDefinition();
		jobDefinition2.setId("3");
		jobDefinition2.setName("hello job2");;
		list.add(jobDefinition2);
		
		return list;
	}

	
	@ResponseBody
	@RequestMapping(value = "/saveJobDefinition", method = RequestMethod.POST)
	public JobDefinition saveJobDefinition(@ModelAttribute("jobDefinitionForm") JobDefinition inParam,HttpServletResponse response) throws Exception {
		
		if(AssertHelper.empty(inParam.getId())){
			inParam.setId(IdGenerator.getUUID());
			jobDefinitionService.save(inParam);
		}else{
			jobDefinitionService.update(inParam);
		}
		inParam.setId(inParam.getId());
		return inParam;
	}
	
	@ResponseBody
	@RequestMapping(value = "/removeJobDefinition")
	public void removeJobDefinition(@RequestParam("id") String clientKeys,HttpServletResponse response) throws Exception {
		AssertHelper.notEmpty_assert(clientKeys,"需要删除的job数据不能为空");
		for(String clientId:clientKeys.split(",")){
			JobDefinition entity=(JobDefinition)jobDefinitionService.get(JobDefinition.class,clientId);
			jobDefinitionService.remove(entity);
		}
		JSONObject object = new JSONObject();
		object.put("result", "true");
		object.put("success", "true");
		object.put("msg", "删除成功");
		retJson(response, object);
	}
	
	@ResponseBody
	@RequestMapping(value = "/loadModifyJobDefinition")
	public void loadModifyJobDefinition(@RequestParam Map<String, Object> map,HttpServletResponse response) throws Exception {
		Object id=map.get("id");
		JSONObject jsonObject = new JSONObject();
		if (!AssertHelper.isOrNotEmpty_assert(id)) {
		AssertHelper.notEmpty_assert(id,"需要删除的数据不能为空");
		jsonObject.put("success", "false");
		jsonObject.put("errorMsg", "编辑的主键不能为空");
		}
		else {
			JobDefinition entity=(JobDefinition)jobDefinitionService.get(JobDefinition.class,id.toString());
			jsonObject.put("success", "true");
			JobDefinitionInParam aa = new JobDefinitionInParam();
			
			ReflectUtils.copyProperties(entity, aa);
			jsonObject.put("taskShceduleForm", aa);
			retJson(response, jsonObject);
		}
	}
	
	/**
	 * 立即执行
	 * @param map
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/exeJob", method = RequestMethod.POST)
	public void exeJob(@RequestParam Map<String, Object> map,HttpServletResponse response) throws Exception {
		String selectid=(String)map.get("selectid");
		AssertHelper.notEmpty_assert(selectid,"执行的任务id不能为空");
		JobDefinition jobDefinition=(JobDefinition) jobDefinitionService.get(JobDefinition.class, selectid);
		AssertHelper.notEmpty_assert(jobDefinition,"id为"+selectid+"的job定义没有找到");
		String beanId=jobDefinition.getBeanId();
		AssertHelper.notEmpty_assert(jobDefinition,"id为"+selectid+"的job没有定义beanId");
		Object job=SpringUtil.getBean(beanId);
		AssertHelper.notEmpty_assert(job,"beanId为"+beanId+"的bean没有找到");
		if(!((job instanceof Job)&&(job instanceof JobTest))){
			throw new BusinessException("beanId为"+beanId+"的bean必须实现的是org.quartz.Job和JobTest接口");
		}
		JobTest jobTest=(JobTest)job;
		JSONObject object = new JSONObject();
		object.put("success", "true");
		try {
			jobTest.execute();
			JobHistory jobhistory=new JobHistory();
			jobhistory.setEndDate(jobDefinition.getEndDate());
			jobhistory.setStartDate(jobDefinition.getStartDate());
			jobhistory.setId(IdGenerator.getUUID());
			jobhistory.setJobId(beanId);
			jobhistory.setSuccessful(true);
			jobDefinitionService.save(jobhistory);	
			retJson(response, object);
		} catch (Exception e) {

			e.printStackTrace();
			JobHistory jobhistory=new JobHistory();
			jobhistory.setEndDate(jobDefinition.getEndDate());
			jobhistory.setStartDate(jobDefinition.getStartDate());
			jobhistory.setId(IdGenerator.getUUID());
			jobhistory.setJobId(beanId);
			jobhistory.setSuccessful(false);
			jobDefinitionService.save(jobhistory);	
			object.put("errorMsg", e.getMessage());
			object.put("success", "false");
			retJson(response, object);
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/startJob", method = RequestMethod.POST)
	public void startJob(@RequestParam Map<String, Object> map) throws Exception {
		
		String selectid=(String)map.get("selectid");
		AssertHelper.notEmpty_assert(selectid,"执行的任务id不能为空");
		JobDefinition jobDefinition=(JobDefinition) jobDefinitionService.get(JobDefinition.class, selectid);
		//JobDefinition job=(JobDefinition)jobDefinitionService.get(JobDefinition.class, selectid);
		jobDefinition.setState(JobState.running);	
		jobDefinition.setStartDate(new Date());
		jobDefinitionService.update(jobDefinition);
		try {
			JobHistory jobhistory=new JobHistory();
			jobhistory.setStartDate(jobDefinition.getStartDate());
			jobhistory.setId(IdGenerator.getUUID());
			jobhistory.setJobId(selectid);
			jobhistory.setSuccessful(true);
			jobDefinitionService.save(jobhistory);	
		} catch (Exception e) {

			e.printStackTrace();
			JobHistory jobhistory=new JobHistory();
			jobhistory.setEndDate(jobDefinition.getEndDate());
			jobhistory.setStartDate(jobDefinition.getStartDate());
			jobhistory.setId(IdGenerator.getUUID());
			jobhistory.setJobId(selectid);
			jobhistory.setSuccessful(false);
			jobDefinitionService.save(jobhistory);	
		}
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/stopJob", method = RequestMethod.POST)
	public void stopJob(@RequestParam Map<String, Object> map) throws Exception {
		String selectid=(String)map.get("selectid");
		AssertHelper.notEmpty_assert(selectid,"执行的任务id不能为空");
		JobDefinition jobDefinition=(JobDefinition) jobDefinitionService.get(JobDefinition.class, selectid);
		jobDefinition.setState(JobState.stopping);
		jobDefinition.setEndDate(new Date());
		jobDefinitionService.update(jobDefinition);
		try {
			JobHistory jobhistory=new JobHistory();
			jobhistory.setEndDate(jobDefinition.getEndDate());
			jobhistory.setId(IdGenerator.getUUID());
			jobhistory.setJobId(selectid);
			jobhistory.setSuccessful(true);
			jobDefinitionService.save(jobhistory);	
		} catch (Exception e) {

			e.printStackTrace();
			JobHistory jobhistory=new JobHistory();
			jobhistory.setEndDate(jobDefinition.getEndDate());
			jobhistory.setStartDate(jobDefinition.getStartDate());
			jobhistory.setId(IdGenerator.getUUID());
			jobhistory.setJobId(selectid);
			jobhistory.setSuccessful(false);
			jobDefinitionService.save(jobhistory);	
		}
			
		
	}
	
	public void updateModifyJobDefinition(ClientInParam inParam) throws Exception {
		JobDefinition entity=(JobDefinition)jobDefinitionService.get(JobDefinition.class,inParam.getId());
		ReflectUtils.copyProperties(inParam, entity);
		jobDefinitionService.update(entity);
	}
	/**
	public void loadClientSec(Page<ClientSecInParam> page, Map<String, Object> map) throws Exception {
		DaoPage daoPage=clientService.findClientSecByParams(map, page.getPageNo(), page.getPageSize());
		D7PageUtils.daoPageToPage(daoPage, page);
	}
	*/
//	public Collection loadClientSec(Map<String, Object> map) throws Exception {
//		List result=jobDefinitionService.findClientSecByParams(map);
//		return result;
//	}
//	
	/**
	public void saveClientSec(Collection<ClientSecInParam> objs) throws Exception {
		Map results= D7PageUtils.assembleDatasetMap(objs);
		clientService.saveClientSecDataListsMap(results);
	}
	*/
	
	

}
