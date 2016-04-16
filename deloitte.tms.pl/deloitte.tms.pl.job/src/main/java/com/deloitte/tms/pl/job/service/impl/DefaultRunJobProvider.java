package com.deloitte.tms.pl.job.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.job.model.JobDefinition;
import com.deloitte.tms.pl.job.model.JobState;
import com.deloitte.tms.pl.job.model.RunJob;
import com.deloitte.tms.pl.job.service.IJobDefinitionService;
import com.deloitte.tms.pl.job.service.IRunJobProvider;

/**
 * @author Jacky.gao
 * @since 2013-3-29
 */
@Component
public class DefaultRunJobProvider implements IRunJobProvider {
	@Resource
	private IJobDefinitionService jobDefinitionService;
	private final Log logger=LogFactory.getLog(getClass());
	public Collection<RunJob> getRunJobs() {		
		List<RunJob> jobs=new ArrayList<RunJob>();
		List<JobDefinition> jobDefinitions=jobDefinitionService.loadJobs(JobState.run,true);
		logger.info("There are "+jobDefinitions.size()+" jobs to run for startup");
		for(JobDefinition jobDef:jobDefinitions){
			RunJob job=new RunJob();
			job.setJobDefinition(jobDef);
			jobs.add(job);
		}
		return jobs;
	}
	public IJobDefinitionService getJobDefinitionService() {
		return jobDefinitionService;
	}
	public void setJobDefinitionService(IJobDefinitionService jobDefinitionService) {
		this.jobDefinitionService = jobDefinitionService;
	}
}
