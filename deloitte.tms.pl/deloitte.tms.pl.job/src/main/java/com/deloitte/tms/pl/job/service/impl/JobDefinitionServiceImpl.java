package com.deloitte.tms.pl.job.service.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.commons.utils.BatchUtils;
import com.deloitte.tms.pl.core.dao.IDao;
import com.deloitte.tms.pl.core.service.impl.BaseService;
import com.deloitte.tms.pl.job.dao.IJobDefinitionDao;
import com.deloitte.tms.pl.job.model.JobCalendar;
import com.deloitte.tms.pl.job.model.JobCalendarDate;
import com.deloitte.tms.pl.job.model.JobCalendarRelation;
import com.deloitte.tms.pl.job.model.JobDefinition;
import com.deloitte.tms.pl.job.model.JobHistory;
import com.deloitte.tms.pl.job.model.JobParameter;
import com.deloitte.tms.pl.job.model.JobState;
import com.deloitte.tms.pl.job.service.IJobDataService;
import com.deloitte.tms.pl.job.service.IJobDefinitionService;

/**
 * @author Jacky.gao
 * @since 2013-3-9
 */
@Component(IJobDefinitionService.BEAN_ID)
public class JobDefinitionServiceImpl extends BaseService implements IJobDefinitionService {
	@Resource
	private IJobDataService dataService;
	@Resource
	private IJobDefinitionDao jobDefinitionDao;
	public List<JobDefinition> loadJobs(JobState state,boolean fillCalendar){
		List<JobDefinition> jobDefinitions=jobDefinitionDao.loadJobs(state, fillCalendar);
		jobDefinitions=dataService.filterJobs(jobDefinitions);
		return jobDefinitions;
	}
	@Override
	public Collection<JobCalendar> loadCalendars(Map params) {
		return jobDefinitionDao.loadCalendars(params);
	}
	@Override
	public Collection<JobCalendar> loadCalendarDates(String calendarId) {
		return jobDefinitionDao.loadCalendarDates(calendarId);
	}
	@Override
	public void saveCalendars(Map dataListsMap,Map childdataListsMap) {
		Collection<JobCalendar> deleteList = BatchUtils.to(JobCalendar.class).getDeleteEntities(dataListsMap);
		Collection<JobCalendar> insertList =  BatchUtils.to(JobCalendar.class).getInsertEntities(dataListsMap);
		Collection<JobCalendar> updateList =  BatchUtils.to(JobCalendar.class).getModifiedEntities(dataListsMap);
		for(JobCalendar c:insertList)
		{
			c.setId(UUID.randomUUID().toString());
			c.setCompanyId(dataService.getCompanyId());
			jobDefinitionDao.save(c);
		}
		for(JobCalendar c:updateList)
		{
			jobDefinitionDao.update(c);
		}
		for(JobCalendar c:deleteList)
		{
			String hql="select count(*) from "+JobCalendarRelation.class.getName()+" where calendarId=:calendarId";
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("calendarId", c.getId());
			int count=jobDefinitionDao.queryForInt(hql, map);
			if(count>0){
				throw new RuntimeException("当前日期有Job正在使用，不能被删除");
			}
			List jobCalendarDates=jobDefinitionDao.findCalendarDatesBycalendarId(c.getId());
			jobDefinitionDao.removeAll(jobCalendarDates);
		}
		saveCalendarDates(childdataListsMap);
	}
	public void saveCalendarDates(Map dataListsMap) {
		Collection<JobCalendarDate> deleteList = BatchUtils.to(JobCalendarDate.class).getDeleteEntities(dataListsMap);
		Collection<JobCalendarDate> insertList =  BatchUtils.to(JobCalendarDate.class).getInsertEntities(dataListsMap);
		Collection<JobCalendarDate> updateList =  BatchUtils.to(JobCalendarDate.class).getModifiedEntities(dataListsMap);
		for(JobCalendarDate c:insertList)
		{
			c.setId(UUID.randomUUID().toString());
			jobDefinitionDao.save(c);
		}
		for(JobCalendarDate c:updateList)
		{
			jobDefinitionDao.update(c);
		}
		for(JobCalendarDate c:deleteList)
		{
			
		}
	}
	@Override
	public Integer findCalenderJobNum(String calendarId) {
		return jobDefinitionDao.findCalenderJobNum(calendarId);
	}
	@Override
	public Collection<JobParameter> loadJobParameters(String jobId) {
		return jobDefinitionDao.loadJobParameters(jobId);
	}
	@Override
	public DaoPage loadJobs(Map params, Integer pageIndex, Integer pageSize) {
		return jobDefinitionDao.loadJobs(params, pageIndex, pageSize);
	}
	@Override
	public Collection<JobCalendar> loadAllCalendars() {
		return jobDefinitionDao.loadAllCalendars();
	}
	@Override
	public Collection<JobCalendar> loadCalendars(String jobId) {
		return jobDefinitionDao.loadCalendars(jobId);
	}
	@Override
	public DaoPage loadJobHistories(Map params, Integer pageIndex,
			Integer pageSize) {
		return jobDefinitionDao.loadJobHistories(params, pageIndex, pageSize);
	}
	@Override
	public void saveJobParameters(Map dataListsMap) {
		Collection<JobParameter> deleteList = BatchUtils.to(JobParameter.class).getDeleteEntities(dataListsMap);
		Collection<JobParameter> insertList =  BatchUtils.to(JobParameter.class).getInsertEntities(dataListsMap);
		Collection<JobParameter> updateList =  BatchUtils.to(JobParameter.class).getModifiedEntities(dataListsMap);
		for(JobParameter c:insertList)
		{
			c.setId(UUID.randomUUID().toString());
			jobDefinitionDao.save(c);
		}
		for(JobParameter c:updateList)
		{
			jobDefinitionDao.update(c);
		}
		for(JobParameter c:deleteList)
		{
			jobDefinitionDao.remove(c);
		}
	}
	@Override
	public IDao getDao() {
		return jobDefinitionDao;
	}
	@Override
	public void saveJobs(Map dataListsMap) {
		Collection<JobDefinition> deleteList = BatchUtils.to(JobDefinition.class).getDeleteEntities(dataListsMap);
		Collection<JobDefinition> insertList =  BatchUtils.to(JobDefinition.class).getInsertEntities(dataListsMap);
		Collection<JobDefinition> updateList =  BatchUtils.to(JobDefinition.class).getModifiedEntities(dataListsMap);
		for(JobDefinition job:insertList)
		{
			job.setId(UUID.randomUUID().toString());
			job.setState(JobState.ready);
			job.setCompanyId(dataService.getCompanyId());
			jobDefinitionDao.save(job);
		}
		for(JobDefinition c:updateList)
		{
			jobDefinitionDao.update(c);
		}
		for(JobDefinition c:deleteList)
		{
			jobDefinitionDao.remove(c);
			String jobId=c.getId();
			List<JobHistory> histories=jobDefinitionDao.findJobHistoryByJobId(jobId);
			removeAll(histories);
			List<JobCalendarRelation> jobCalendarRelations=jobDefinitionDao.findJobCalendarRelationByJobId(jobId);
			removeAll(jobCalendarRelations);
			List<JobParameter> jobParameters=jobDefinitionDao.findJobParameterByJobId(jobId);
			removeAll(jobParameters);
		}
	}
	@Override
	public void saveJobCalendars(String calendarId,String jobId,String operation) {
		if(operation.equals("add")){
			JobCalendarRelation relation=new JobCalendarRelation();
			relation.setId(UUID.randomUUID().toString());
			relation.setCalendarId(calendarId);
			relation.setJobId(jobId);
			jobDefinitionDao.save(relation);
		}else{
			List<JobCalendarRelation> jobCalendarRelations=jobDefinitionDao.findJobCalendarRelationByJobIdAndCalenderid(jobId, calendarId);
			jobDefinitionDao.removeAll(jobCalendarRelations);
		}
	}
	public void deleteJobHistory(Collection<String> historyIds){
		for(String historyId:historyIds){
			JobHistory jobHistory=(JobHistory) findById(JobHistory.class, historyId);
			remove(jobHistory);
		}
	}
	
	public DaoPage getJobByName(Map<String, Object> parameter,int parseInt, int parseInt2)
	{
		return jobDefinitionDao.getJobByName(parameter,parseInt,parseInt2);
	}
	
	
}
