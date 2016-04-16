//package com.ling2.job.view.job;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Date;
//import java.util.List;
//import java.util.Map;
//import java.util.UUID;
//
//import javax.annotation.Resource;
//
//import org.hibernate.Session;
//import org.quartz.CronExpression;
//import org.quartz.Job;
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ApplicationContextAware;
//import org.springframework.stereotype.Component;
//
//import com.bstek.dorado.annotation.DataProvider;
//import com.bstek.dorado.annotation.DataResolver;
//import com.bstek.dorado.annotation.Expose;
//import com.bstek.dorado.data.entity.EntityState;
//import com.bstek.dorado.data.entity.EntityUtils;
//import com.bstek.dorado.data.provider.Criteria;
//import com.bstek.dorado.data.provider.Page;
//import com.ling2.core.commons.exception.BusinessException;
//import com.ling2.core.commons.support.DaoPage;
//import com.ling2.core.commons.utils.AssertHelper;
//import com.ling2.core.commons.utils.D7PageUtils;
//import com.ling2.core.commons.utils.SessionFactoryUtils;
//import com.ling2.core.commons.utils.SpringUtil;
//import com.ling2.job.model.JobCalendar;
//import com.ling2.job.model.JobCalendarRelation;
//import com.ling2.job.model.JobDefinition;
//import com.ling2.job.model.JobHistory;
//import com.ling2.job.model.JobParameter;
//import com.ling2.job.model.JobState;
//import com.ling2.job.service.IJobDataService;
//import com.ling2.job.service.IJobDefinitionService;
//import com.ling2.job.service.JobTest;
//
///**
// * @author bo.wang
// * @since 2013-3-10
// */
//@Component("ling2.jobMaintain")
//public class JobMaintain implements InitializingBean,ApplicationContextAware{
//	@Resource
//	private IJobDataService dataService;
//	private Collection<String> jobBeanIds;
//	@Resource
//	IJobDefinitionService jobDefinitionService;
//	@DataProvider
//	public Collection<JobParameter> loadJobParameters(String jobId) throws Exception{
//		return jobDefinitionService.loadJobParameters(jobId);
//	}
//	
//	@DataResolver
//	public void saveJobParameters(Collection<JobParameter> parameters){
//		Session session=SessionFactoryUtils.getSessionFactory().openSession();
//		try{
//			for(JobParameter p:parameters){
//				EntityState state=EntityUtils.getState(p);
//				if(state.equals(EntityState.NEW)){
//					p.setId(UUID.randomUUID().toString());
//					session.save(p);
//				}
//				if(state.equals(EntityState.MODIFIED)){
//					session.update(p);
//				}
//				if(state.equals(EntityState.DELETED)){
//					session.delete(p);
//				}
//			}
//		}finally{
//			session.flush();
//			session.close();
//		}
//	}
//	
//	@DataProvider
//	public void loadJobs(Page<JobDefinition> page,Criteria criteria) throws Exception{
//		DaoPage daoPage= jobDefinitionService.loadJobs(D7PageUtils.buildQueryParams(criteria), page.getPageNo(), page.getPageSize());
//		D7PageUtils.daoPageToPage(daoPage, page);
//	}
//	@DataProvider
//	public Collection<JobCalendar> loadAllCalendars(){
//		return jobDefinitionService.loadAllCalendars();
//	}
//	@DataProvider
//	public Collection<JobCalendar> loadCalendars(String jobId){
//		return jobDefinitionService.loadCalendars(jobId);
//	}
//	
//	@DataProvider
//	public void loadJobHistories(Page<JobDefinition> page,Criteria criteria,String jobDefId) throws Exception{
//		Map params=D7PageUtils.buildQueryParams(criteria);
//		params.put("jobId", jobDefId);
//		DaoPage daoPage= jobDefinitionService.loadJobHistories(params, page.getPageNo(), page.getPageSize());
//		D7PageUtils.daoPageToPage(daoPage, page);
//	}
//	
//	@DataProvider
//	public Collection<JobInfo> loadJobInfos() throws Exception{
//		List<JobInfo> infos=new ArrayList<JobInfo>();
//		for(String beanId:jobBeanIds){
//			JobInfo info=new JobInfo();
//			info.setBeanId(beanId);
//			infos.add(info);
//		}
//		return infos;
//	}
//	
//	@DataProvider
//	public Collection<CronDate> parseCronExpression(String cron) throws Exception{
//		CronExpression expr=new CronExpression(cron);
//		List<CronDate> dates=new ArrayList<CronDate>();
//		Date startDate=new Date();
//		for(int i=0;i<50;i++){
//			startDate=expr.getNextValidTimeAfter(startDate);
//			CronDate cd=new CronDate();
//			cd.setDate(startDate);
//			dates.add(cd);
//		}
//		return dates;
//	}
//	
//	@Expose
//	public void stopJob(String jobDefinitionId){
//		Session session=SessionFactoryUtils.getSessionFactory().openSession();
//		try{
//			JobDefinition job=(JobDefinition)session.get(JobDefinition.class, jobDefinitionId);
//			job.setState(JobState.stopping);
//			session.update(job);
//		}finally{
//			session.flush();
//			session.close();
//		}
//	}
//
//	@Expose
//	public void runJob(String jobDefinitionId){
//		Session session=SessionFactoryUtils.getSessionFactory().openSession();
//		try{
//			JobDefinition job=(JobDefinition)session.get(JobDefinition.class, jobDefinitionId);
//			job.setState(JobState.running);				
//			session.update(job);
//		}finally{
//			session.flush();
//			session.close();
//		}
//	}	
//	
//	
//	@DataResolver
//	public void saveJobs(Collection<JobDefinition> jobs) throws Exception{
//		Session session=SessionFactoryUtils.getSessionFactory().openSession();
//		try{
//			for(JobDefinition job:jobs){
//				EntityState state=EntityUtils.getState(job);
//				if(state.equals(EntityState.NEW)){
//					job.setId(UUID.randomUUID().toString());
//					job.setState(JobState.ready);
//					job.setCompanyId(dataService.getCompanyId());
//					session.save(job);
//				}
//				if(state.equals(EntityState.MODIFIED)){
//					session.update(job);
//				}
//				if(state.equals(EntityState.DELETED)){
//					String hql="delete "+JobHistory.class.getName()+" where jobId=:jobId";
//					session.createQuery(hql).setString("jobId",job.getId()).executeUpdate();
//					hql="delete "+JobCalendarRelation.class.getName()+" where jobId=:jobId";
//					session.createQuery(hql).setString("jobId", job.getId()).executeUpdate();
//					hql="delete "+JobParameter.class.getName()+" where jobId=:jobId";
//					session.createQuery(hql).setString("jobId", job.getId()).executeUpdate();
//					session.delete(job);
//				}
//			}
//		}finally{
//			session.flush();
//			session.close();
//		}
//	}
//	
//	@Expose
//	public void saveJobCalendars(String calendarId,String jobId,String operation){
//		Session session=SessionFactoryUtils.getSessionFactory().openSession();
//		try{
//			if(operation.equals("add")){
//				JobCalendarRelation relation=new JobCalendarRelation();
//				relation.setId(UUID.randomUUID().toString());
//				relation.setCalendarId(calendarId);
//				relation.setJobId(jobId);
//				session.save(relation);
//			}else{
//				String hql="delete "+JobCalendarRelation.class.getName()+" where calendarId=:calendarId and jobId=:jobId";
//				session.createQuery(hql).setString("calendarId", calendarId).setString("jobId", jobId).executeUpdate();
//			}			
//		}finally{
//			session.flush();
//			session.close();
//		}
//	}
//	
//	@Expose
//	public void deleteJobHistory(Collection<String> historyIds){
//		Session session=SessionFactoryUtils.getSessionFactory().openSession();
//		try{		
//			for(String historyId:historyIds){
//				String hql="delete "+JobHistory.class.getName()+" where id=:id";
//				session.createQuery(hql).setString("id", historyId).executeUpdate();
//			}
//		}finally{
//			session.flush();
//			session.close();
//		}
//	}
//
//	@Override
//	public void afterPropertiesSet() throws Exception {
//		Map<String,IJobDataService> map=applicationContext.getBeansOfType(IJobDataService.class);
//		if(map.isEmpty()){
//			throw new RuntimeException("Job module need a ["+IJobDataService.class.getName()+"] interface implementation");
//		}
//		dataService=map.values().iterator().next();
//		jobBeanIds=this.applicationContext.getBeansOfType(Job.class).keySet();
//	}
//	private ApplicationContext applicationContext;
//	@Override
//	public void setApplicationContext(ApplicationContext applicationContext)
//			throws BeansException {
//		this.applicationContext=applicationContext;
//	}
//	@Expose
//	public void exeImmediately(Map parmas)
//	{
//		if(parmas==null)
//		{
//			throw new BusinessException("未定义bean");
//		}
//		String beanId=(String)parmas.get("beanId");
//		if(AssertHelper.notEmpty(beanId))
//		{
//			JobTest jobTest=SpringUtil.getBean(beanId);
//			if(jobTest!=null&&(jobTest instanceof JobTest)){
//				jobTest.execute();
//			}else{
//				throw new BusinessException("错误的beanid");
//			}
//		}else{
//			throw new BusinessException("未定义bean");
//		}
//	}
//}
