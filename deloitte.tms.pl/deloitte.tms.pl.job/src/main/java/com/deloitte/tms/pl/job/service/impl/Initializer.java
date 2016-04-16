package com.deloitte.tms.pl.job.service.impl;

import java.util.Collection;

import javax.annotation.Resource;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.job.model.RunJob;
import com.deloitte.tms.pl.job.service.IJobService;
import com.deloitte.tms.pl.job.service.IRunJobProvider;
import com.deloitte.tms.pl.job.service.ISchedulerService;
import com.deloitte.tms.pl.job.service.ISystemJobProvider;


/**
 * @author Jacky.gao
 * @since 2013-3-30
 */
@Component(Initializer.BEAN_ID)
public class Initializer implements ApplicationContextAware,InitializingBean{
	public static final String BEAN_ID="ling2.job.initializer";
	@Resource
	private ISchedulerService schedulerService;
	@Resource
	private IJobService jobService;
	private Collection<ISystemJobProvider> systemJobProviders;
	private Collection<IRunJobProvider> runJobProviders;
	ApplicationContext applicationContext;
	public void initRunJobsForStartup() throws SchedulerException{
		for(IRunJobProvider p:runJobProviders){
			for(RunJob runJob:p.getRunJobs()){
				if(runJob.getJobDefinition()!=null){
					if(runJob.getJobDetail()!=null){
						jobService.addJobToScheduler(runJob.getJobDefinition(),runJob.getJobDetail());															
					}else{
						jobService.addJobToScheduler(runJob.getJobDefinition());																					
					}
				}
			}
		}
	}
	public void initSystemJobs() throws Exception{
		Scheduler scheduler=schedulerService.retrieveScheduler();
		for(ISystemJobProvider provider:systemJobProviders){
			scheduler.scheduleJob(provider.getJobDetail(), provider.getTrigger());			
		}			
	}

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext=applicationContext;
	}
	
	public void startJobInstance() throws Exception{
		if(schedulerService.isRunJobInCurrentInstance()){
			initRunJobsForStartup();
			initSystemJobs();
			Scheduler scheduler=schedulerService.retrieveScheduler();
			if(scheduler.isInStandbyMode()){
				System.out.println("Start scheduler ["+scheduler.getSchedulerName()+"]...");
				scheduler.start();
			}
		}
	}
	
	public void afterPropertiesSet() throws Exception {
		systemJobProviders=this.applicationContext.getBeansOfType(ISystemJobProvider.class).values();
		runJobProviders=this.applicationContext.getBeansOfType(IRunJobProvider.class).values();
		if(schedulerService.isRunJobInCurrentInstance()){
			startJobInstance();
		}
	}
	
	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	public ISchedulerService getSchedulerService() {
		return schedulerService;
	}
	public void setSchedulerService(ISchedulerService schedulerService) {
		this.schedulerService = schedulerService;
	}
	public IJobService getJobService() {
		return jobService;
	}
	public void setJobService(IJobService jobService) {
		this.jobService = jobService;
	}
}
