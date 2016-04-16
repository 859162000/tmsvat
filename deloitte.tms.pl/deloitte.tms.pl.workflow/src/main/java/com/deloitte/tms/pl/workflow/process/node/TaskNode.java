package com.deloitte.tms.pl.workflow.process.node;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.quartz.spi.OperableTrigger;
import org.springframework.context.ApplicationContext;

import com.deloitte.tms.pl.workflow.command.impl.SaveHistoryTaskCommand;
import com.deloitte.tms.pl.workflow.env.Context;
import com.deloitte.tms.pl.workflow.env.TaskDueDefinitionProvider;
import com.deloitte.tms.pl.workflow.model.ProcessInstance;
import com.deloitte.tms.pl.workflow.model.task.CancelTaskInterceptor;
import com.deloitte.tms.pl.workflow.model.task.DateUnit;
import com.deloitte.tms.pl.workflow.model.task.Task;
import com.deloitte.tms.pl.workflow.model.task.TaskAppointor;
import com.deloitte.tms.pl.workflow.model.task.TaskParticipator;
import com.deloitte.tms.pl.workflow.model.task.TaskState;
import com.deloitte.tms.pl.workflow.model.task.TaskType;
import com.deloitte.tms.pl.workflow.model.task.reminder.ReminderType;
import com.deloitte.tms.pl.workflow.model.task.reminder.TaskReminder;
import com.deloitte.tms.pl.workflow.process.assign.Assignee;
import com.deloitte.tms.pl.workflow.process.assign.AssigneeProvider;
import com.deloitte.tms.pl.workflow.process.flow.SequenceFlowImpl;
import com.deloitte.tms.pl.workflow.process.handler.AssignmentHandler;
import com.deloitte.tms.pl.workflow.process.handler.CountersignHandler;
import com.deloitte.tms.pl.workflow.process.listener.TaskListener;
import com.deloitte.tms.pl.workflow.process.node.calendar.BusinessCalendar;
import com.deloitte.tms.pl.workflow.process.node.reminder.CalendarInfo;
import com.deloitte.tms.pl.workflow.process.node.reminder.CalendarProvider;
import com.deloitte.tms.pl.workflow.process.node.reminder.DueAction;
import com.deloitte.tms.pl.workflow.process.node.reminder.DueDefinition;
import com.deloitte.tms.pl.workflow.process.node.reminder.PeriodReminder;
import com.deloitte.tms.pl.workflow.process.node.reminder.Reminder;
import com.deloitte.tms.pl.workflow.process.security.Authority;
import com.deloitte.tms.pl.workflow.process.security.ComponentAuthority;
import com.deloitte.tms.pl.workflow.process.swimlane.Swimlane;
import com.deloitte.tms.pl.workflow.service.SchedulerService;
import com.deloitte.tms.pl.workflow.service.TaskService;
import com.deloitte.tms.pl.workflow.utils.EnvironmentUtils;
import com.deloitte.tms.pl.workflow.utils.IDGenerator;

/**
 * @author Jacky.gao
 * @since 2013年7月25日
 */
public class TaskNode extends Node {
	private static final long serialVersionUID = 1L;
	private String taskName;
	private TaskType taskType;
	private boolean allowSpecifyAssignee;
	private int countersignMultiplicity;
	private int countersignPercentMultiplicity;
	private String countersignExpression;
	private String countersignHandler;
	private AssignmentType assignmentType;
	private List<Assignee> assignees;
	private String assignmentHandlerBean;
	private String swimlane;
	private String expression;
	private String url;
	private String formTemplate;
	private String taskListenerBean;
	private DueDefinition dueDefinition;
	private List<ComponentAuthority> componentAuthorities;
	private List<FormElement> formElements;
	private List<UserData> userData;
	@Override
	public boolean enter(Context context,ProcessInstance processInstance) {
		if(StringUtils.isNotEmpty(taskListenerBean)){
			TaskListener taskListener=(TaskListener)context.getApplicationContext().getBean(taskListenerBean);
			boolean doNext=taskListener.beforeTaskCreate(context, processInstance, this);
			if(doNext){
				return true;
			}
		}
		if(allowSpecifyAssignee){
			int size=processAllowSpecifyAssignee(context,processInstance);
			if(size>0){
				return false;
			}
		}
		if(assignmentType.equals(AssignmentType.Handler)){
			processAssignmentHandler(context, processInstance,assignmentHandlerBean);
		}else if(assignmentType.equals(AssignmentType.Swimlane)){
			processSwimlane(context, processInstance);
		}else if(assignmentType.equals(AssignmentType.Assignee)){
			processAssignees(context,processInstance,assignees);
		}else if(assignmentType.equals(AssignmentType.ProcessPromoter)){
			List<String> users=new ArrayList<String>();
			users.add(processInstance.getPromoter());
			createTasks(context, processInstance, users);
		}else if(assignmentType.equals(AssignmentType.Expression)){
			processExpression(context, processInstance,expression);
		}else{
			throw new RuntimeException("Unsupport current assignment type ["+assignmentType+"]");
		}
		return false;
	}

	private int processAllowSpecifyAssignee(Context context, ProcessInstance processInstance) {
		TaskService taskService=(TaskService)context.getApplicationContext().getBean(TaskService.BEAN_ID);
		List<TaskAppointor> appointors=taskService.getTaskAppointors(getName(), processInstance.getRootId());
		if(appointors!=null && appointors.size()>0){
			List<String> owners=new ArrayList<String>();
			for(TaskAppointor ta:appointors){
				owners.add(ta.getOwner());
			}
			if(owners.size()==0){
				return 0;
			}
			createTasks(context,processInstance,owners);			
			return appointors.size();
		}
		return 0;
	}
	
	public List<String> getAssigneeUsers(Context context,ProcessInstance processInstance){
		List<String> resultUsers=new ArrayList<String>();
		ApplicationContext applicationContext=context.getApplicationContext();
		if(assignmentType.equals(AssignmentType.Handler)){
			AssignmentHandler handler=(AssignmentHandler)applicationContext.getBean(assignmentHandlerBean);
			Collection<String> users=handler.handle(this, processInstance, context);
			resultUsers.addAll(users);
		}else if(assignmentType.equals(AssignmentType.Swimlane)){
			Swimlane lane=context.getProcessService().getProcessById(processInstance.getProcessId()).getSwimlane(swimlane);
			if(lane==null){
				throw new IllegalArgumentException("Swimlane ["+swimlane+"] is not exist!");
			}
			if(lane.getAssignmentType().equals(AssignmentType.Handler)){
				AssignmentHandler handler=(AssignmentHandler)applicationContext.getBean(lane.getAssignmentHandlerBean());
				Collection<String> users=handler.handle(this, processInstance, context);
				resultUsers.addAll(users);
			}else if(lane.getAssignmentType().equals(AssignmentType.Expression)){
				Collection<String> users=getExpressionUsers(context, processInstance, lane.getExpression());
				resultUsers.addAll(users);						
			}else if(lane.getAssignmentType().equals(AssignmentType.ProcessPromoter)){
				resultUsers.add(processInstance.getPromoter());
			}else if(lane.getAssignmentType().equals(AssignmentType.Assignee)){
				List<String> users=new ArrayList<String>();
				retriveAssignees(lane.getAssignees(),context,processInstance,users);
				resultUsers.addAll(users);
			}else{
				throw new RuntimeException("Unsupport swimlane ["+swimlane+"] assignment type ["+assignmentType+"]");				
			}
		}else if(assignmentType.equals(AssignmentType.Assignee)){
			List<String> users=new ArrayList<String>();
			retriveAssignees(assignees,context,processInstance,users);
			resultUsers.addAll(users);
		}else if(assignmentType.equals(AssignmentType.ProcessPromoter)){
			resultUsers.add(processInstance.getPromoter());
		}else if(assignmentType.equals(AssignmentType.Expression)){
			Collection<String> users=getExpressionUsers(context, processInstance, expression);
			resultUsers.addAll(users);
		}else{
			throw new RuntimeException("Unsupport current assignment type ["+assignmentType+"]");
		}
		return resultUsers;
	}
	
	private void processAssignmentHandler(Context context,ProcessInstance processInstance,String bean) {
		AssignmentHandler handler=(AssignmentHandler)context.getApplicationContext().getBean(bean);
		Collection<String> users=handler.handle(this, processInstance, context);
		createTasks(context,processInstance,users);				
	}
	
	private void processSwimlane(Context context,ProcessInstance processInstance) {
		Swimlane lane=context.getProcessService().getProcessById(processInstance.getProcessId()).getSwimlane(swimlane);
		if(lane==null){
			throw new IllegalArgumentException("Swimlane ["+swimlane+"] is not exist!");
		}
		if(lane.getAssignmentType().equals(AssignmentType.Handler)){
			processAssignmentHandler(context,processInstance,lane.getAssignmentHandlerBean());
		}else if(lane.getAssignmentType().equals(AssignmentType.Expression)){
			processExpression(context, processInstance,lane.getExpression());
		}else if(lane.getAssignmentType().equals(AssignmentType.ProcessPromoter)){
			List<String> users=new ArrayList<String>();
			users.add(processInstance.getPromoter());
			createTasks(context, processInstance, users);
		}else if(lane.getAssignmentType().equals(AssignmentType.Assignee)){
			processAssignees(context,processInstance,lane.getAssignees());			
		}else{
			throw new RuntimeException("Unsupport swimlane ["+swimlane+"] assignment type ["+assignmentType+"]");				
		}
	}
	
	private void processExpression(Context context,ProcessInstance processInstance,String expr) {
		List<String> users = getExpressionUsers(context, processInstance, expr);
		createTasks(context, processInstance, users);								
	}

	@SuppressWarnings("unchecked")
	private List<String> getExpressionUsers(Context context,
			ProcessInstance processInstance, String expr) {
		Object obj=context.getExpressionContext().eval(processInstance, expr);
		if(obj==null){
			throw new IllegalArgumentException("Expression "+expr+" value is null");
		}
		List<String> users=new ArrayList<String>();
		if(obj instanceof String){
			String user=(String)obj;
			users.add(user);
		}else if(obj instanceof String[]){
			for(String user:(String[])obj){
				users.add(user);
			}		
		}else if(obj instanceof Collection){
			users.addAll((Collection<String>)obj);
		}else{
			throw new RuntimeException("Unsupport expression "+expr+" value type :"+obj.getClass().getName());
		}
		return users;
	}
	
	private void createTasks(Context context,ProcessInstance processInstance,Collection<String> owners) {
		if(owners==null || owners.size()==0){
			throw new IllegalArgumentException("Task node "+getName()+" must be have one assignee at least!");
		}
		TaskListener taskListener=null;
		if(StringUtils.isNotEmpty(taskListenerBean)){
			taskListener=(TaskListener)context.getApplicationContext().getBean(taskListenerBean);
		}
		Session session=context.getSession();
		if(taskType.equals(TaskType.Normal)){
			Task task=createTask(context, processInstance);
			if(owners.size()==1){
				String user=owners.iterator().next();
				task.setAssignee(user);
				task.setOwner(user);
				task.setType(taskType);
				session.save(task);
				Date dueDate=createReminder(processInstance,task,context);
				if(dueDate==null){
					DueDefinition dueDef=getDueDefinition(processInstance, task, context);
					if(dueDef!=null){
						dueDate=computeDueReminderStartdate(context,dueDef);
					}else{
						dueDate=computeDueReminderStartdate(context,dueDefinition);			
					}
				}
				task.setDuedate(dueDate);
				if(taskListener!=null){
					taskListener.onTaskCreate(context, task);
				}
			}else{
				task.setType(TaskType.Participative);
				task.setState(TaskState.Ready);
				session.save(task);
				Date dueDate=createReminder(processInstance,task,context);
				if(dueDate==null){
					DueDefinition dueDef=getDueDefinition(processInstance, task, context);
					if(dueDef!=null){
						dueDate=computeDueReminderStartdate(context,dueDef);
					}else{
						dueDate=computeDueReminderStartdate(context,dueDefinition);			
					}
				}
				task.setDuedate(dueDate);
				long taskId=task.getId();
				for(String user:owners){
					TaskParticipator p=new TaskParticipator();
					p.setId(IDGenerator.getInstance().nextId());
					p.setTaskId(taskId);
					p.setUser(user);
					session.save(p);
				}
				if(taskListener!=null){
					taskListener.onTaskCreate(context, task);
				}
			}
		}else if(taskType.equals(TaskType.Countersign)){
			for(String user:owners){
				Task task=createTask(context, processInstance);
				task.setAssignee(user);
				task.setOwner(user);
				task.setCountersignCount(owners.size());
				task.setType(taskType);
				session.save(task);
				Date dueDate=createReminder(processInstance,task,context);
				if(dueDate==null){
					DueDefinition dueDef=getDueDefinition(processInstance, task, context);
					if(dueDef!=null){
						dueDate=computeDueReminderStartdate(context,dueDef);
					}else{
						dueDate=computeDueReminderStartdate(context,dueDefinition);			
					}
				}
				task.setDuedate(dueDate);
				if(taskListener!=null){
					taskListener.onTaskCreate(context, task);
				}
			}
		}else{
			throw new IllegalArgumentException("Not support current task type :"+taskType);
		}
	}

	private Task createTask(Context context, ProcessInstance processInstance) {
		Task task=new Task();
		task.setId(IDGenerator.getInstance().nextId());
		task.setNodeName(getName());
		if(StringUtils.isNotEmpty(taskName)){
			task.setTaskName(context.getExpressionContext().evalString(processInstance, taskName));
		}else{
			String newTaskName=getLabel();
			if(StringUtils.isEmpty(newTaskName)){
				newTaskName=getName();
			}
			task.setTaskName(newTaskName);
		}
		task.setRootProcessInstanceId(processInstance.getRootId());
		task.setDescription(context.getExpressionContext().evalString(processInstance, getDescription()));
		task.setCreateDate(new Date());
		task.setState(TaskState.Created);
		task.setProcessId(getProcessId());
		task.setProcessInstanceId(processInstance.getId());
		task.setPrevTask(processInstance.getCurrentTask());
		task.setBusinessId(processInstance.getBusinessId());
		String targetUrl=getUrl();
		if(StringUtils.isEmpty(targetUrl)){
			targetUrl=getFormTemplate();
		}
		task.setUrl(targetUrl);
		
		return task;
	}
	
	private DueDefinition getDueDefinition(ProcessInstance processInstance,Task task,Context context){
		Collection<TaskDueDefinitionProvider> colls=context.getApplicationContext().getBeansOfType(TaskDueDefinitionProvider.class).values();
		if(colls.size()==0)return null;
		return colls.iterator().next().getDueDefinition(task, processInstance);
	}

	private Date createReminder(ProcessInstance processInstance,Task task,Context context) {
		Reminder reminder=null;
		DueDefinition dueDef=getDueDefinition(processInstance, task, context);
		Date startReminderDate=null;
		if(dueDef!=null){
			reminder=dueDef.getReminder();
			startReminderDate=computeDueReminderStartdate(context,dueDef);
		}else{
			if(dueDefinition==null)return null;
			startReminderDate=computeDueReminderStartdate(context,dueDefinition);			
			reminder=dueDefinition.getReminder();
		}
		SchedulerService schedulerService=(SchedulerService)context.getApplicationContext().getBean(SchedulerService.BEAN_ID);
		if(reminder!=null){
			ReminderType type=ReminderType.Once;
			String cron=null;
			if(reminder instanceof PeriodReminder){
				type=ReminderType.Period;
				PeriodReminder period=(PeriodReminder)reminder;
				java.util.Calendar calendar = java.util.Calendar.getInstance();
				calendar.setTime(startReminderDate);
				DateUnit unit=period.getUnit();
				int sec=calendar.get(Calendar.SECOND);
				int min=calendar.get(Calendar.MINUTE);
				int hour=calendar.get(Calendar.HOUR);
				if(unit.equals(DateUnit.Minute)){
					cron=""+sec+" 0/"+period.getRepeat()+" * ? * *";
				}else if(unit.equals(DateUnit.Hour)){
					cron=""+sec+" "+min+" 0/"+period.getRepeat()+" ? * *";				
				}else if(unit.equals(DateUnit.Day)){
					cron=""+sec+" "+min+" "+hour+" 1/"+period.getRepeat()+" * ?";								
				}else{
					throw new IllegalArgumentException("Unsupport date unit :"+unit);
				}
			}
			TaskReminder taskReminder=new TaskReminder();
			taskReminder.setId(IDGenerator.getInstance().nextId());
			taskReminder.setTaskId(task.getId());
			taskReminder.setTaskNodeName(getName());
			taskReminder.setProcessId(processInstance.getProcessId());
			taskReminder.setReminderHandlerBean(reminder.getHandlerBean());
			taskReminder.setType(type);
			taskReminder.setStartDate(startReminderDate);
			taskReminder.setCron(cron);
			schedulerService.addReminderJob(taskReminder,processInstance,task);
			context.getSession().save(taskReminder);
		}
		TaskReminder dueActionReminder=buildDueAction(dueDef,startReminderDate,context,task);
		if(dueActionReminder!=null){
			schedulerService.addReminderJob(dueActionReminder,processInstance,task);
			context.getSession().save(dueActionReminder);
		}
		return startReminderDate;
	}
	
	private TaskReminder buildDueAction(DueDefinition dueDef,Date startReminderDate,Context context,Task task){
		DueAction dueAction=null;
		if(dueDef!=null){
			dueAction=dueDef.getDueAction();
		}else{
			if(dueDefinition==null){
				return null;
			}
			dueAction=dueDefinition.getDueAction();
		}
		if(dueAction==null || dueAction.getHandlerBean()==null){
			return null;
		}
		java.util.Calendar endCalendar = java.util.Calendar.getInstance();
		endCalendar.setTime(startReminderDate);
		int day=dueAction.getDay();
		int hour=dueAction.getHour();
		int minute=dueAction.getMinute();
		BusinessCalendar businessCalendar=(BusinessCalendar)EnvironmentUtils.getEnvironment().getApplicationContext().getBean(BusinessCalendar.BEAN_ID);
		if(day>0){
			endCalendar.add(Calendar.HOUR_OF_DAY, day*businessCalendar.getBusinessDayHours());
		}
		if(hour>0){
			endCalendar.add(Calendar.HOUR_OF_DAY, hour);
		}
		if(minute>0){
			endCalendar.add(Calendar.MINUTE, minute);
		}
		List<CalendarInfo> infos=dueAction.getCalendarInfos();
		Date executeActionDate=null;
		if(infos!=null && infos.size()>0){
			java.util.Calendar startCalendar = java.util.Calendar.getInstance();
			startCalendar.setTime(startReminderDate);
			org.quartz.Calendar baseCalendar=buildCalendar(context, infos);
			executeActionDate=businessCalendar.calEndDate(startCalendar,endCalendar,baseCalendar);
		}else{
			executeActionDate=endCalendar.getTime();
		}
		TaskReminder taskReminder=new TaskReminder();
		taskReminder.setId(IDGenerator.getInstance().nextId());
		taskReminder.setTaskId(task.getId());
		taskReminder.setTaskNodeName(getName());
		taskReminder.setProcessId(task.getProcessId());
		taskReminder.setReminderHandlerBean(dueAction.getHandlerBean());
		taskReminder.setType(ReminderType.Once);
		taskReminder.setStartDate(executeActionDate);
		return taskReminder;
	}
		
	private Date computeDueReminderStartdate(Context context,DueDefinition dueDef){
		if(dueDef==null)return null;
		int dueDay=dueDef.getDay();
		int dueHour=dueDef.getHour();
		int dueMinute=dueDef.getMinute();
		java.util.Calendar endCalendar = java.util.Calendar.getInstance();
		BusinessCalendar businessCalendar=(BusinessCalendar)EnvironmentUtils.getEnvironment().getApplicationContext().getBean(BusinessCalendar.BEAN_ID);
		if(dueDay>0){
			endCalendar.add(Calendar.HOUR_OF_DAY, dueDay*businessCalendar.getBusinessDayHours());
		}
		if(dueHour>0){
			endCalendar.add(Calendar.HOUR_OF_DAY, dueHour);
		}
		if(dueMinute>0){
			endCalendar.add(Calendar.MINUTE, dueMinute);
		}
		List<CalendarInfo> infos=dueDef.getCalendarProviderInfos();
		if(infos!=null && infos.size()>0){
			java.util.Calendar startCalendar = java.util.Calendar.getInstance();
			org.quartz.Calendar baseCalendar=buildCalendar(context, infos);
			return businessCalendar.calEndDate(startCalendar, endCalendar,baseCalendar);
		}else{
			return endCalendar.getTime();
		}
	}

	private org.quartz.Calendar buildCalendar(Context context,
			List<CalendarInfo> infos) {
		org.quartz.Calendar baseCalendar=null;
		if(infos!=null){
			Collection<CalendarProvider> providers=context.getApplicationContext().getBeansOfType(CalendarProvider.class).values();
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

    public static Date computeFireTimesWithStart(OperableTrigger trigg,org.quartz.Calendar cal, Date from) {
        OperableTrigger t = (OperableTrigger) trigg.clone();
        if (t.getNextFireTime() == null) {
            t.setStartTime(from);
            t.computeFirstFireTime(cal);
        }
        return t.getNextFireTime();
    }
	
	
	private void processAssignees(Context context,ProcessInstance processInstance,List<Assignee> assigneeList) {
		List<String> users=new ArrayList<String>();
		retriveAssignees(assigneeList,context,processInstance,users);
		createTasks(context,processInstance,users);
	}

	private void retriveAssignees(List<Assignee> assigneeList,Context context,ProcessInstance processInstance,List<String> users) {
		ApplicationContext applicationContext=context.getApplicationContext();
		for(Assignee assignee:assigneeList){
			AssigneeProvider provider=(AssigneeProvider)applicationContext.getBean(assignee.getProviderId());
			Collection<String> coll=provider.getUsers(assignee.getId(),context,processInstance);
			if(coll!=null && coll.size()>0){
				users.addAll(coll);
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public synchronized String leave(Context context,ProcessInstance processInstance,String flowName) {
		if(taskType.equals(TaskType.Countersign)){
			List<Task> countersignTasks=context.getSession().createCriteria(Task.class).add(Restrictions.eq("processInstanceId", processInstance.getId())).list();
			if(countersignTasks.size()>0){
				Task task=countersignTasks.get(0);
				int alreadyCompletedCount=task.getCountersignCount()-countersignTasks.size();
				if(countersignMultiplicity>0){
					if(alreadyCompletedCount>=countersignMultiplicity){
						cancelTasks(context, processInstance, countersignTasks);
					}else{
						return null;
					}
				}else if(countersignPercentMultiplicity>0){
					double percent=Double.valueOf(countersignPercentMultiplicity)/Double.valueOf(100);
					double alreadyCompletedPercent=(Double.valueOf(alreadyCompletedCount)/Double.valueOf(task.getCountersignCount()));
					if(alreadyCompletedPercent>=percent){
						cancelTasks(context, processInstance, countersignTasks);
					}else{
						return null;
					}
				}else if(StringUtils.isNotEmpty(countersignExpression)){
					Object obj=context.getExpressionContext().eval(processInstance.getId(), countersignExpression);
					if(!(obj instanceof Boolean)){
						throw new IllegalArgumentException(getName()+" countersign task expression "+countersignExpression+" value type must be is boolean,current is "+obj+"");
					}
					boolean result=(Boolean)obj;
					if(result){
						cancelTasks(context, processInstance, countersignTasks);
					}else{
						return null;						
					}
				}else if(StringUtils.isNotEmpty(countersignHandler)){
					CountersignHandler handler=(CountersignHandler)context.getApplicationContext().getBean(countersignHandler);
					if(handler.handle(context, processInstance)){
						cancelTasks(context, processInstance, countersignTasks);
					}else{
						return null;						
					}
				}else{
					if(alreadyCompletedCount<task.getCountersignCount()){
						return null;
					}
				}
			}
		}
		if(StringUtils.isNotEmpty(flowName)){
			SequenceFlowImpl flow=getFlow(flowName);
			if(flow==null){
				throw new IllegalArgumentException("Flow "+flowName+" is not exist!");
			}
			flow.execute(context, processInstance);		
			return flow.getName();
		}else{
			return leaveNode(context, processInstance, flowName);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void cancel(Context context, ProcessInstance processInstance) {
		Session session=context.getSession();
		Collection<Task> tasks=session.createCriteria(Task.class).add(Restrictions.eq("processInstanceId", processInstance.getId())).list();
		cancelTasks(context, processInstance, tasks);
	}

	private void cancelTasks(Context context, ProcessInstance processInstance, Collection<Task> tasks) {
		Session session=context.getSession();
		SchedulerService schedulerService=(SchedulerService)context.getApplicationContext().getBean(SchedulerService.BEAN_ID);
		Collection<CancelTaskInterceptor> interceptors=context.getApplicationContext().getBeansOfType(CancelTaskInterceptor.class).values();
		for(Task task:tasks){
			for(CancelTaskInterceptor interceptor:interceptors){
				interceptor.intercept(context, task);
			}
			String hql="delete "+TaskParticipator.class.getName()+" where taskId=:taskId";
			session.createQuery(hql).setLong("taskId", task.getId()).executeUpdate();
			session.delete(task);
			if(dueDefinition!=null){
				schedulerService.removeReminderJob(task);		
			}
			task.setState(TaskState.Canceled);
			context.getCommandService().executeCommand(new SaveHistoryTaskCommand(task,processInstance));
		}
	}
	
	public Authority getComponentAuthority(String component){
		if(componentAuthorities==null || componentAuthorities.size()==0){
			return Authority.ReadAndWrite;
		}
		boolean read=false;
		boolean write=false;
		for(ComponentAuthority componentAuthority:componentAuthorities){
			if(component.equals(componentAuthority.getComponent())){
				if(componentAuthority.getAuthority().equals(Authority.Read)){
					read=true;
				}else if(componentAuthority.getAuthority().equals(Authority.ReadAndWrite)){
					read=true;
					write=true;
					break;
				}
			}
		}
		if(read && write){
			return Authority.ReadAndWrite;
		}
		if(read){
			return Authority.Read;
		}
		return null;
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getFormTemplate() {
		return formTemplate;
	}

	public void setFormTemplate(String formTemplate) {
		this.formTemplate = formTemplate;
	}

	public AssignmentType getAssignmentType() {
		return assignmentType;
	}

	public void setAssignmentType(AssignmentType assignmentType) {
		this.assignmentType = assignmentType;
	}

	public int getCountersignMultiplicity() {
		return countersignMultiplicity;
	}

	public void setCountersignMultiplicity(int countersignMultiplicity) {
		this.countersignMultiplicity = countersignMultiplicity;
	}

	public int getCountersignPercentMultiplicity() {
		return countersignPercentMultiplicity;
	}

	public void setCountersignPercentMultiplicity(int countersignPercentMultiplicity) {
		this.countersignPercentMultiplicity = countersignPercentMultiplicity;
	}

	public String getCountersignExpression() {
		return countersignExpression;
	}

	public void setCountersignExpression(String countersignExpression) {
		this.countersignExpression = countersignExpression;
	}

	public String getCountersignHandler() {
		return countersignHandler;
	}

	public void setCountersignHandler(String countersignHandler) {
		this.countersignHandler = countersignHandler;
	}

	public String getAssignmentHandlerBean() {
		return assignmentHandlerBean;
	}

	public void setAssignmentHandlerBean(String assignmentHandlerBean) {
		this.assignmentHandlerBean = assignmentHandlerBean;
	}

	public List<Assignee> getAssignees() {
		return assignees;
	}

	public void setAssignees(List<Assignee> assignees) {
		this.assignees = assignees;
	}

	public String getSwimlane() {
		return swimlane;
	}

	public void setSwimlane(String swimlane) {
		this.swimlane = swimlane;
	}


	public String getExpression() {
		return expression;
	}


	public void setExpression(String expression) {
		this.expression = expression;
	}


	public List<ComponentAuthority> getComponentAuthorities() {
		return componentAuthorities;
	}


	public void setComponentAuthorities(List<ComponentAuthority> componentAuthorities) {
		this.componentAuthorities = componentAuthorities;
	}

	public TaskType getTaskType() {
		return taskType;
	}

	public void setTaskType(TaskType taskType) {
		this.taskType = taskType;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public DueDefinition getDueDefinition() {
		return dueDefinition;
	}

	public void setDueDefinition(DueDefinition reminderDefinition) {
		this.dueDefinition = reminderDefinition;
	}

	public boolean isAllowSpecifyAssignee() {
		return allowSpecifyAssignee;
	}

	public void setAllowSpecifyAssignee(boolean allowSpecifyAssignee) {
		this.allowSpecifyAssignee = allowSpecifyAssignee;
	}

	public List<FormElement> getFormElements() {
		return formElements;
	}

	public void setFormElements(List<FormElement> formElements) {
		this.formElements = formElements;
	}

	public List<UserData> getUserData() {
		return userData;
	}

	public void setUserData(List<UserData> userData) {
		this.userData = userData;
	}

	public String getTaskListenerBean() {
		return taskListenerBean;
	}

	public void setTaskListenerBean(String taskListenerBean) {
		this.taskListenerBean = taskListenerBean;
	}
}
