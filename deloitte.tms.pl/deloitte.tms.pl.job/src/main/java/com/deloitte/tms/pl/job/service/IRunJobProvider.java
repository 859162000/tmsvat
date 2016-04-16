package com.deloitte.tms.pl.job.service;

import java.util.Collection;

import com.deloitte.tms.pl.job.model.RunJob;

/**
 * @author Jacky.gao
 * @since 2013-3-29
 */
public interface IRunJobProvider {
	Collection<RunJob> getRunJobs();
}
