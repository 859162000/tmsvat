package com.deloitte.tms.pl.job.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.dao.IDao;
import com.deloitte.tms.pl.job.model.JobCalendar;
import com.deloitte.tms.pl.job.model.JobCalendarDate;
import com.deloitte.tms.pl.job.model.JobCalendarRelation;
import com.deloitte.tms.pl.job.model.JobDefinition;
import com.deloitte.tms.pl.job.model.JobHistory;
import com.deloitte.tms.pl.job.model.JobParameter;
import com.deloitte.tms.pl.job.model.JobState;

public interface IJobDefinitionDao extends IDao {
	public static final String BEAN_ID = "ling2.job.jobDefinitionDao";
	public static final String JOB_DEFINITION_ID = "_job_definition_id_";

	List<JobDefinition> loadJobs(JobState state, boolean fillCalendar);

	List<JobCalendar> loadJobCalendar(String jobDefId);

	Collection<JobCalendar> loadCalendars(Map params);

	Collection<JobCalendar> loadCalendarDates(String calendarId);

	Integer findCalenderJobNum(String calendarId);

	Collection<JobParameter> loadJobParameters(String jobId);

	DaoPage loadJobs(Map params, Integer pageIndex, Integer pageSize);

	Collection<JobCalendar> loadAllCalendars();

	Collection<JobCalendar> loadCalendars(String jobId);

	DaoPage loadJobHistories(Map params, Integer pageIndex, Integer pageSize);
	
	public List<JobCalendarDate> findCalendarDatesBycalendarId(String calendarId);
	
	List<JobHistory> findJobHistoryByJobId(String jobId);
	
	List<JobHistory> findJobHistoryById(String Id);
	
	List<JobCalendarRelation> findJobCalendarRelationByJobId(String jobId);
	
	List<JobParameter> findJobParameterByJobId(String jobId);
	
	List<JobCalendarRelation> findJobCalendarRelationByJobIdAndCalenderid(String jobId,String calendarId);
	
	public DaoPage getJobByName(Map<String, Object> parameter, int parseInt,
			int parseInt2);

}
