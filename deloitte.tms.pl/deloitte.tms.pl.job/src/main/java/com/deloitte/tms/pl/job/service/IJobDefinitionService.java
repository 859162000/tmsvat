package com.deloitte.tms.pl.job.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.service.IService;
import com.deloitte.tms.pl.job.model.JobCalendar;
import com.deloitte.tms.pl.job.model.JobDefinition;
import com.deloitte.tms.pl.job.model.JobHistory;
import com.deloitte.tms.pl.job.model.JobParameter;
import com.deloitte.tms.pl.job.model.JobState;

public interface IJobDefinitionService extends IService{
	public static final String BEAN_ID = "ling2.job.jobDefinitionService";
	public static final String JOB_DEFINITION_ID = "_job_definition_id_";

	List<JobDefinition> loadJobs(JobState state, boolean fillCalendar);

	Collection<JobCalendar> loadCalendars(Map params);

	Collection<JobCalendar> loadCalendarDates(String calendarId);

	void saveCalendars(Map datalistsMap,Map childdatalistsMap);
	
	Integer findCalenderJobNum(String calendarId);

	Collection<JobParameter> loadJobParameters(String jobId);

	DaoPage loadJobs(Map params, Integer pageIndex, Integer pageSize);

	Collection<JobCalendar> loadAllCalendars();

	Collection<JobCalendar> loadCalendars(String jobId);

	DaoPage loadJobHistories(Map params, Integer pageIndex, Integer pageSize);
	
	void saveJobParameters(Map datalistsMap);
	
	void saveJobs(Map datalistsMap);
	
	void saveJobCalendars(String calendarId,String jobId,String operation);
	
	public void deleteJobHistory(Collection<String> historyIds);
	
	public DaoPage getJobByName(Map<String, Object> parameter, int parseInt, int parseInt2);
}
