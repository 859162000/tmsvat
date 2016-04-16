package com.deloitte.tms.pl.job.service;

import java.util.List;

import com.deloitte.tms.pl.job.model.JobDefinition;

/**
 * @author Jacky.gao
 * @since 2013-3-10
 */
public interface IJobDataService {
	String getCompanyId();
	List<JobDefinition> filterJobs(List<JobDefinition> jobs);
}
