package com.deloitte.tms.pl.job.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.job.model.JobDefinition;
import com.deloitte.tms.pl.job.service.IJobDataService;
@Component
public class DefaultJobDataService implements IJobDataService{

	@Override
	public String getCompanyId() {
		return "deloitte.tms";
	}

	@Override
	public List<JobDefinition> filterJobs(List<JobDefinition> jobs) {
		return jobs;
	}

}
