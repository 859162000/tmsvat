package com.deloitte.tms.pl.job.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Calendar;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerKey;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.calendar.AnnualCalendar;
import org.quartz.impl.calendar.HolidayCalendar;
import org.quartz.impl.calendar.MonthlyCalendar;
import org.quartz.impl.calendar.WeeklyCalendar;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.core.commons.exception.BusinessException;
import com.deloitte.tms.pl.core.dao.IDao;
import com.deloitte.tms.pl.core.service.impl.BaseService;
import com.deloitte.tms.pl.job.dao.IJobDefinitionDao;
import com.deloitte.tms.pl.job.executor.SpringBeanJobExecutor;
import com.deloitte.tms.pl.job.model.CalendarType;
import com.deloitte.tms.pl.job.model.JobCalendar;
import com.deloitte.tms.pl.job.model.JobCalendarDate;
import com.deloitte.tms.pl.job.model.JobDefinition;
import com.deloitte.tms.pl.job.model.JobHistory;
import com.deloitte.tms.pl.job.model.JobParameter;
import com.deloitte.tms.pl.job.model.JobState;
import com.deloitte.tms.pl.job.model.ScanJobExecutorDetail;
import com.deloitte.tms.pl.job.model.SpringBeanJobExecutorDetail;
import com.deloitte.tms.pl.job.service.IJobDefinitionService;
import com.deloitte.tms.pl.job.service.IJobService;
import com.deloitte.tms.pl.job.service.ISchedulerService;

/**
 * @author Jacky.gao
 * @since 2013-3-8
 */
@Component(IJobService.BEAN_ID)
public class JobServiceImpl extends BaseService implements IJobService,ApplicationContextAware{
	public static final String JOB_PREFIX="job";
	public static final String TRIGGER_PREFIX="trigger";
	public static final String CALENDAR_PREFIX="calendar";
	protected final Log logger = LogFactory.getLog(getClass());
	@Resource
	private ISchedulerService schedulerService;
	@Resource
	private IJobDefinitionDao jobDefinitionDao;
	private ApplicationContext applicationContext;
	public void addJobToScheduler(JobDefinition jobDef) throws SchedulerException {
		addJobToScheduler(jobDef,null);
	}
	public void addJobToScheduler(JobDefinition jobDef,JobDetailImpl jobDetail) throws SchedulerException {
		String jobDefId=jobDef.getId();
		Scheduler scheduler=schedulerService.retrieveScheduler();
		if(jobDetail==null){
			jobDetail=new SpringBeanJobExecutorDetail(applicationContext,jobDef.getBeanId());	
		}
		if (jobDetail.getJobClass() == null) {
			jobDetail.setJobClass(SpringBeanJobExecutor.class);	
        }
		jobDetail.setKey(new JobKey(JOB_PREFIX+jobDefId,jobDef.getCompanyId()));
		jobDetail.setDescription(jobDef.getName()+","+jobDef.getDesc());
		JobDataMap jobDataMap=jobDetail.getJobDataMap();
		if(jobDataMap==null){
			jobDataMap=new JobDataMap();
		}
		jobDataMap.put(IJobDefinitionService.JOB_DEFINITION_ID, jobDefId);
		for(JobParameter p:jobDef.getParameters()){
			jobDataMap.put(p.getName(), p.getValue());
		}
		jobDetail.setJobDataMap(jobDataMap);
		CronTriggerImpl trigger=new CronTriggerImpl();
		try {
			trigger.setCronExpression(jobDef.getCronExpression());
		} catch (ParseException e1) {
			e1.printStackTrace();
			throw new SchedulerException(e1);
		}
		trigger.setName(TRIGGER_PREFIX+jobDefId);
		trigger.setGroup(jobDef.getCompanyId());
		try {
			trigger.setCronExpression(jobDef.getCronExpression());
		} catch (ParseException e) {
			e.printStackTrace();
			throw new SchedulerException(e);
		}
		JobKey jobKey=new JobKey(JOB_PREFIX+jobDefId,jobDef.getCompanyId());
		TriggerKey triggerKey=new TriggerKey(TRIGGER_PREFIX+jobDefId,jobDef.getCompanyId());
		if(scheduler.checkExists(jobKey)){
			scheduler.pauseTrigger(triggerKey);
			scheduler.unscheduleJob(triggerKey);
			scheduler.deleteJob(jobKey);
		}
		List<JobCalendar> calendars=jobDef.getCalendars();
		if(calendars==null)return;
		Calendar calendar=this.buildCalendar(calendars);
		String calendarName=CALENDAR_PREFIX+jobDefId;
		if(calendar!=null){
			trigger.setCalendarName(calendarName);
			scheduler.addCalendar(calendarName, calendar, true, true);			
		}
		scheduler.scheduleJob(jobDetail, trigger);
	}
	
	private Calendar buildCalendar(List<JobCalendar> calendars){
		Calendar rootCalendar=null;
		for(JobCalendar calendarDef:calendars){
			if(calendarDef.getType().equals(CalendarType.holiday)){
				HolidayCalendar calendar=new HolidayCalendar();
				List<JobCalendarDate> dates=calendarDef.getCalendarDates();
				if(dates!=null){
					for(JobCalendarDate d:dates){
						calendar.addExcludedDate(d.getCalendarDate());
					}
				}
				if(rootCalendar!=null){
					calendar.setBaseCalendar(rootCalendar);
				}
				rootCalendar=calendar;
			}
			if(calendarDef.getType().equals(CalendarType.annual)){
				AnnualCalendar calendar=new AnnualCalendar();
				List<JobCalendarDate> dates=calendarDef.getCalendarDates();
				if(dates!=null){
					ArrayList<java.util.Calendar> excludedDates=new ArrayList<java.util.Calendar>();
					for(JobCalendarDate d:dates){
						java.util.Calendar c=new GregorianCalendar();
						c.add(java.util.Calendar.MONTH,d.getMonthOfYear());
						c.add(java.util.Calendar.DAY_OF_MONTH,d.getDayOfMonth());
						excludedDates.add(c);
					}
					calendar.setDaysExcluded(excludedDates);
				}
				if(rootCalendar!=null){
					calendar.setBaseCalendar(rootCalendar);
				}
				rootCalendar=calendar;
			}
			if(calendarDef.getType().equals(CalendarType.monthly)){
				MonthlyCalendar calendar=new MonthlyCalendar();
				List<JobCalendarDate> dates=calendarDef.getCalendarDates();
				if(dates!=null){
					for(JobCalendarDate d:dates){
						calendar.setDayExcluded(d.getDayOfMonth(),true);
					}
				}
				if(rootCalendar!=null){
					calendar.setBaseCalendar(rootCalendar);
				}
				rootCalendar=calendar;
			}
			if(calendarDef.getType().equals(CalendarType.weekly)){
				WeeklyCalendar calendar=new WeeklyCalendar();
				List<JobCalendarDate> dates=calendarDef.getCalendarDates();
				if(dates!=null){
					for(JobCalendarDate d:dates){
						calendar.setDayExcluded(d.getDayOfWeek(),true);
					}
				}
				if(rootCalendar!=null){
					calendar.setBaseCalendar(rootCalendar);
				}
				rootCalendar=calendar;
			}
			
		}
		return rootCalendar;			
	}

	public void removeJobFromScheduler(String jobDefId,String companyId) throws SchedulerException {
		Scheduler scheduler=schedulerService.retrieveScheduler();
		JobKey jobKey=new JobKey(JOB_PREFIX+jobDefId,companyId);
		TriggerKey triggerKey=new TriggerKey(TRIGGER_PREFIX+jobDefId,companyId);
		if(scheduler.checkExists(jobKey)){
			scheduler.pauseTrigger(triggerKey);
			scheduler.unscheduleJob(triggerKey);
			scheduler.deleteJob(jobKey);
		}
	}
	public void resumeJob(String jobDefId,String companyId) throws SchedulerException {
		Scheduler scheduler=schedulerService.retrieveScheduler();
		scheduler.resumeJob(new JobKey(JOB_PREFIX+jobDefId,companyId));
	}
	public void pauseJob(String jobDefId,String companyId) throws SchedulerException {
		Scheduler scheduler=schedulerService.retrieveScheduler();
		scheduler.pauseJob(new JobKey(JOB_PREFIX+jobDefId,companyId));
	}
	public JobDetail getJobFromScheduler(String jobDefId,String companyId) throws SchedulerException {
		Scheduler scheduler=schedulerService.retrieveScheduler();
		return scheduler.getJobDetail(new JobKey(JOB_PREFIX+jobDefId,companyId));
	}
	
	public void updateTrigger(String jobDefId,String companyId,String cronExpression) throws SchedulerException {
		Scheduler scheduler=schedulerService.retrieveScheduler();
		CronTriggerImpl trigger=(CronTriggerImpl)scheduler.getTrigger(new TriggerKey(TRIGGER_PREFIX+jobDefId,companyId));
		try {
			trigger.setCronExpression(cronExpression);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new SchedulerException(e);
		}
		scheduler.scheduleJob(trigger);
	}
	
	public void updateCalendar(String jobDefId,String companyId,List<JobCalendar> calendars) throws SchedulerException {
		Calendar calendar=this.buildCalendar(calendars);
		if(calendar==null)return;
		Scheduler scheduler=schedulerService.retrieveScheduler();
		scheduler.addCalendar(CALENDAR_PREFIX+jobDefId, calendar, true, true);
		
	}
	public void setSchedulerService(ISchedulerService schedulerService) {
		this.schedulerService = schedulerService;
	}

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext=applicationContext;
	}
	public void executeJobHistory(JobExecutionContext context,
			String exception,Date start)
	{
		JobDetail jobDetail=context.getJobDetail();
		if(!(jobDetail instanceof SpringBeanJobExecutorDetail)){
			return;
		}
		JobDataMap map=jobDetail.getJobDataMap();
		if(!map.containsKey(IJobDefinitionService.JOB_DEFINITION_ID)){
			return;
		}
		Date end=new Date();
		
		JobHistory history=new JobHistory();
		history.setSuccessful(exception==null?true:false);
		if(exception!=null){
			history.setExceptionMessage(exception.length()>1500?exception.substring(0, 1500):exception);			
		}
		history.setEndDate(end);
		history.setStartDate(start);
		history.setId(UUID.randomUUID().toString());
		history.setJobId(map.getString(IJobDefinitionService.JOB_DEFINITION_ID));
	}
	public void executeScanJobExecutor(JobExecutionContext context)
	{
		ScanJobExecutorDetail jobDetail=(ScanJobExecutorDetail)context.getJobDetail();
		try{
			IJobDefinitionService jobDefinitionService=jobDetail.getJobDefinitionService();
			List<JobDefinition> jobDefinitions=jobDefinitionService.loadJobs(JobState.running,true);
			logger.info("There are "+jobDefinitions.size()+" jobs to run");
			IJobService jobService=jobDetail.getJobService();
			for(JobDefinition jobDef:jobDefinitions){
				jobService.addJobToScheduler(jobDef);
				jobDef.setState(JobState.run);
				update(jobDef);
			}
			jobDefinitions=jobDefinitionService.loadJobs(JobState.stopping,false);
			logger.info("There are "+jobDefinitions.size()+" jobs to stop");
			for(JobDefinition jobDef:jobDefinitions){
				jobService.removeJobFromScheduler(jobDef.getId(), jobDef.getCompanyId());
				jobDef.setState(JobState.stop);
				update(jobDef);
			}
		}catch(Exception ex){
			ex.printStackTrace();
			throw new BusinessException("扫描任务失败");
		}
	}
	@Override
	public IDao getDao() {
		return jobDefinitionDao;
	}
}
