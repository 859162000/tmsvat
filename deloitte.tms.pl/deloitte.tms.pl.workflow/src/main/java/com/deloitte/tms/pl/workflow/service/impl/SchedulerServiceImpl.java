package com.deloitte.tms.pl.workflow.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

import javax.annotation.Resource;

import org.quartz.Calendar;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.AbstractTrigger;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.quartz.impl.triggers.SimpleTriggerImpl;
import org.quartz.simpl.SimpleThreadPool;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.workflow.deploy.parse.impl.ProcessParser;
import com.deloitte.tms.pl.workflow.env.TaskDueDefinitionProvider;
import com.deloitte.tms.pl.workflow.model.ProcessInstance;
import com.deloitte.tms.pl.workflow.model.task.Task;
import com.deloitte.tms.pl.workflow.model.task.reminder.ReminderType;
import com.deloitte.tms.pl.workflow.model.task.reminder.TaskReminder;
import com.deloitte.tms.pl.workflow.process.handler.ReminderHandler;
import com.deloitte.tms.pl.workflow.process.node.TaskNode;
import com.deloitte.tms.pl.workflow.process.node.reminder.CalendarInfo;
import com.deloitte.tms.pl.workflow.process.node.reminder.CalendarProvider;
import com.deloitte.tms.pl.workflow.process.node.reminder.DueDefinition;
import com.deloitte.tms.pl.workflow.process.node.reminder.PeriodReminder;
import com.deloitte.tms.pl.workflow.process.node.reminder.Reminder;
import com.deloitte.tms.pl.workflow.service.ProcessService;
import com.deloitte.tms.pl.workflow.service.SchedulerService;
import com.deloitte.tms.pl.workflow.service.TaskService;
import com.deloitte.tms.pl.workflow.service.impl.job.ReminderJob;
import com.deloitte.tms.pl.workflow.service.impl.job.ReminderJobDetail;

/**
 * @author Jacky.gao
 * @since 2013年8月21日
 */
@Component(SchedulerService.BEAN_ID)
@DependsOn({ProcessParser.BEAN_ID})
public class SchedulerServiceImpl implements SchedulerService,InitializingBean,ApplicationContextAware{
	private static final Logger log=Logger.getLogger(SchedulerServiceImpl.class.getName());
	private static final String JOB_NAME_PREFIX="reminderJob";
	private static final String JOB_GROUP_PREFIX="remindergroup";
	private static final String REMINDER_CALENDAR_PREFIX="reminderCalendar";
	@Value("${uflo.disableScheduler}")
	private boolean disableScheduler;	
	@Value("${uflo.jobThreadCount}")
	private int threadCount;
	@Resource
	private TaskService taskService;
	@Resource
	private ProcessService processService;	
	private ApplicationContext applicationContext;
	private TaskDueDefinitionProvider provider;
	private Scheduler scheduler;
	public Scheduler getScheduler() {
		return scheduler;
	}

	public void addReminderJob(TaskReminder reminder,ProcessInstance processInstance,Task task) {
		if(disableScheduler)return;
		try {
			ReminderJobDetail jobDetail=new ReminderJobDetail();
			jobDetail.setJobClass(ReminderJob.class);
			ReminderHandler handler=(ReminderHandler)applicationContext.getBean(reminder.getReminderHandlerBean());
			jobDetail.setReminderHandlerBean(handler);
			if(task==null){
				task=taskService.getTask(reminder.getTaskId());				
			}
			jobDetail.setTask(task);
			jobDetail.setProcessInstance(processService.getProcessInstanceById(task.getProcessInstanceId()));
			jobDetail.setKey(new JobKey(JOB_NAME_PREFIX+reminder.getId(),JOB_GROUP_PREFIX));
			AbstractTrigger<? extends Trigger> trigger=null;
			if(reminder.getType().equals(ReminderType.Once)){
				SimpleTriggerImpl simpleTrigger=new SimpleTriggerImpl();
				simpleTrigger.setRepeatCount(0);
				trigger=simpleTrigger;
			}else{
				CronTriggerImpl cronTrigger=new CronTriggerImpl();
				cronTrigger.setCronExpression(reminder.getCron());
				trigger=cronTrigger;
			}
			trigger.setName("trigger_"+reminder.getId());
			trigger.setStartTime(reminder.getStartDate());
			Calendar calendar=getCalendar(reminder,processInstance,task);
			if(calendar!=null){
				String calendarName=REMINDER_CALENDAR_PREFIX+reminder.getId();
				scheduler.addCalendar(calendarName, calendar,false, false);
				trigger.setCalendarName(calendarName);
			}
			scheduler.scheduleJob(jobDetail, trigger);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}	
	}

	public void removeReminderJob(Task task) {
		if(disableScheduler)return;
		List<TaskReminder> reminders=taskService.getTaskReminders(task.getId());
		for(TaskReminder reminder:reminders){
			try {
				scheduler.deleteJob(new JobKey(JOB_NAME_PREFIX+reminder.getId(),JOB_GROUP_PREFIX));
			} catch (SchedulerException e) {
				throw new RuntimeException(e);
			}
			String calendarId=REMINDER_CALENDAR_PREFIX+reminder.getId();
			try {
				scheduler.deleteCalendar(calendarId);
			} catch (SchedulerException e) {
				log.warning(e.getMessage());
			}
			taskService.deleteTaskReminder(reminder.getId());
		}
	}

	public void afterPropertiesSet() throws Exception {
		if(disableScheduler)return;
		StdSchedulerFactory factory=new StdSchedulerFactory();
		Properties mergedProps = new Properties();
		mergedProps.setProperty(StdSchedulerFactory.PROP_THREAD_POOL_CLASS, SimpleThreadPool.class.getName());
		mergedProps.setProperty("org.quartz.scheduler.instanceName", "UFLOScheduler");
		mergedProps.setProperty("org.quartz.scheduler.instanceId", "UFLOScheduler");
		mergedProps.setProperty("org.quartz.threadPool.threadCount", Integer.toString(threadCount));
		factory.initialize(mergedProps);
		scheduler=factory.getScheduler();
		scheduler.start();
		initTaskReminders();
	}

	private void initTaskReminders(){
		List<TaskReminder> reminders=taskService.getAllTaskReminders();
		for(TaskReminder reminder:reminders){
			addReminderJob(reminder,null,null);
		}
	}
	
	private Calendar getCalendar(TaskReminder taskReminder,ProcessInstance processInstance,Task task){
		Calendar baseCalendar=null;
		boolean overwrite=false;
		Reminder reminder=null;
		if(provider!=null){
			if(task==null){
				long taskId=taskReminder.getTaskId();
				task=taskService.getTask(taskId);				
			}
			if(processInstance==null){
				processInstance=processService.getProcessInstanceById(task.getProcessInstanceId());				
			}
			DueDefinition dueDefinition=provider.getDueDefinition(task, processInstance);
			if(dueDefinition!=null){
				reminder=dueDefinition.getReminder();	
				overwrite=true;
			}
		}
		if(!overwrite){
			long processId=taskReminder.getProcessId();
			String taskNodeName=taskReminder.getTaskNodeName();
			TaskNode node=(TaskNode)processService.getProcessById(processId).getNode(taskNodeName);
			DueDefinition dueDefinition=node.getDueDefinition();
			if(dueDefinition!=null){
				reminder=dueDefinition.getReminder();				
			}
		}
		if(reminder==null || !(reminder instanceof PeriodReminder)){
			return baseCalendar;
		}
		List<CalendarInfo> infos=((PeriodReminder)reminder).getCalendarProviderInfos();
		if(infos==null){
			return baseCalendar;
		}
		Collection<CalendarProvider> providers=applicationContext.getBeansOfType(CalendarProvider.class).values();
		for(CalendarInfo info:infos){
			for(CalendarProvider provider:providers){
				org.quartz.Calendar calendar=getCalendar(provider,info.getId());
				if(calendar==null){
					continue;
				}
				if(baseCalendar==null){
					baseCalendar=calendar;
				}else{
					calendar.setBaseCalendar(baseCalendar);
					baseCalendar=calendar;
				}
			}
		}
		return baseCalendar;
	}
	
	private org.quartz.Calendar getCalendar(CalendarProvider provider,String calendarId){
		org.quartz.Calendar calendar=provider.getCalendar(calendarId);
		if(calendar!=null){
			return calendar;
		}
		return null;
	}
	
	public boolean isDisableScheduler() {
		return disableScheduler;
	}

	public void setDisableScheduler(boolean disableScheduler) {
		this.disableScheduler = disableScheduler;
	}

	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
		Collection<TaskDueDefinitionProvider> colls=applicationContext.getBeansOfType(TaskDueDefinitionProvider.class).values();
		if(colls.size()>0){
			provider=colls.iterator().next();
		}
	}

	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}
	
	public void setProcessService(ProcessService processService) {
		this.processService = processService;
	}

	public void setThreadCount(int threadCount) {
		this.threadCount = threadCount;
	}
}
