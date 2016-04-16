package com.deloitte.tms.pl.job.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.core.dao.impl.BaseDao;
import com.deloitte.tms.pl.job.dao.IJobDefinitionDao;
import com.deloitte.tms.pl.job.model.JobCalendar;
import com.deloitte.tms.pl.job.model.JobCalendarDate;
import com.deloitte.tms.pl.job.model.JobCalendarRelation;
import com.deloitte.tms.pl.job.model.JobDefinition;
import com.deloitte.tms.pl.job.model.JobHistory;
import com.deloitte.tms.pl.job.model.JobParameter;
import com.deloitte.tms.pl.job.model.JobState;
import com.deloitte.tms.pl.job.service.IJobDataService;

/**
 * @author Jacky.gao
 * @since 2013-3-9
 */
@Component(IJobDefinitionDao.BEAN_ID)
public class JobDefinitionDaoImpl extends BaseDao implements IJobDefinitionDao {
	@Resource
	private IJobDataService dataService;
	
	public List<JobDefinition> loadJobs(JobState state,boolean fillCalendar){
		StringBuffer hql=new StringBuffer();
		hql.append("from "+JobDefinition.class.getName()+" where state=:state");
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("state",state);
		if(StringUtils.isNotEmpty(dataService.getCompanyId())){
			hql.append(" and companyId=:companyId");
			map.put("companyId",dataService.getCompanyId());
		}
		List<JobDefinition> jobDefinitions=findBy(hql, map);
		if(fillCalendar){
			for(JobDefinition jobDef:jobDefinitions){
				jobDef.setCalendars(this.loadJobCalendar(jobDef.getId()));
				jobDef.setParameters(this.loadJobParameters(jobDef.getId()));
			}			
		}
		return jobDefinitions;
	}
	public List<JobCalendar> loadJobCalendar(String jobDefId){
		String hql="from "+JobCalendarRelation.class.getName()+" where jobId=:jobId";
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("jobId",jobDefId);
		List<JobCalendarRelation> relations=this.findBy(hql, map);
		List<JobCalendar> result=new ArrayList<JobCalendar>();
		for(JobCalendarRelation relation:relations){
			hql="from "+JobCalendar.class.getName()+" where id=:id";
			Map<String,Object> m=new HashMap<String,Object>();
			m.put("id",relation.getCalendarId());
			List<JobCalendar> calendars=findBy(hql, m);
			for(JobCalendar jc:calendars){
				hql="from "+JobCalendarDate.class.getName()+" where calendarId=:id";
				List<JobCalendarDate> dates=findBy(hql,m);
				jc.setCalendarDates(dates);
			}
			result.addAll(calendars);
		}
		return result;
	}
	
	public List<JobParameter> loadJobParameters(String jobDefId){
		String hql="from "+JobParameter.class.getName()+" where jobId=:jobId";
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("jobId",jobDefId);
		return findBy(hql,map);
	}
	
	public DaoPage getJobByName(Map<String, Object> parameter, int parseInt,
			int parseInt2)
	{StringBuffer query = new StringBuffer();
	Map values = new HashMap();
	query.append(" from JobHistory where 1=1");
	
		query.append(" and jobId = :jobId");
		values.put("jobId",parameter.get("id"));
		DaoPage daopage = pageBy(query, values, parseInt, parseInt2);
		return daopage;
	}
	@Override
	public Collection<JobCalendar> loadCalendars(Map params) {
		String hql="from "+JobCalendar.class.getName()+" where companyId=:companyId";
		String companyId=dataService.getCompanyId();
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("companyId", companyId);
		return findBy(hql,paramMap);
	}
	@Override
	public Collection<JobCalendar> loadCalendarDates(String calendarId) {
		String hql="from "+JobCalendarDate.class.getName()+" where calendarId=:calendarId";
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("calendarId", calendarId);
		return findBy(hql, paramMap);
	}
	@Override
	public Integer findCalenderJobNum(String calendarId) {
		String hql="select count(*) from "+JobCalendarRelation.class.getName()+" where calendarId=:calendarId";
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("calendarId", calendarId);
		int count=this.queryForInt(hql, map);
		return count;
	}
	@Override
	public DaoPage loadJobs(Map params, Integer pageIndex, Integer pageSize) {
		StringBuffer query=new StringBuffer();
		Map values=new HashMap();
		//query.append("from "+JobDefinition.class.getName()+" j where j.companyId=:companyId");
		//need to confirm by tom
		query.append("from "+JobDefinition.class.getName());
	//	values.put("companyId", ContextUtils.getFixedCompanyId());
		return pageBy(query,values, pageIndex, pageSize);
	}
	@Override
	public Collection<JobCalendar> loadAllCalendars() {
		String hql="from "+JobCalendar.class.getName()+" where companyId=:companyId";
		Map<String,Object> values=new HashMap<String,Object>();
		values.put("companyId", dataService.getCompanyId());
		return findBy(hql, values);
	}
	@Override
	public Collection<JobCalendar> loadCalendars(String jobId) {
		String hql="from "+JobCalendarRelation.class.getName()+" j where j.jobId=:jobId";
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("jobId",jobId);
		List<JobCalendar> result=new ArrayList<JobCalendar>();
		List<JobCalendarRelation> relations=findBy(hql, map);
		for(JobCalendarRelation r:relations){
			result.add((JobCalendar)get(JobCalendar.class, r.getCalendarId()));
		}
		return result;
	}
	@Override
	public DaoPage loadJobHistories(Map params, Integer pageIndex, Integer pageSize) {
		StringBuffer query=new StringBuffer();
		Map values=new HashMap();
		query.append("from "+JobHistory.class.getName()+" j where j.jobId=:jobId");
		Object value=params.get("jobId");
		AssertHelper.notEmpty_assert(value, "任務編號不能為空");
		values.put("jobId", value);
		return pageBy(query, values, pageIndex, pageSize);
	}
	@Override
	public List<JobCalendarDate> findCalendarDatesBycalendarId(String calendarId) {
		StringBuffer query=new StringBuffer();
		Map values=new HashMap();
		query.append("from "+JobCalendarDate.class.getName()+" j where j.calendarId=:calendarId");
		AssertHelper.notEmpty_assert(calendarId, "任務編號不能為空");
		values.put("calendarId", calendarId);
		return findBy(query, values);
	}
	@Override
	public List<JobHistory> findJobHistoryByJobId(String jobId) {
		StringBuffer query=new StringBuffer();
		Map values=new HashMap();
		query.append("from "+JobHistory.class.getName()+" j where j.jobId=:jobId");
		AssertHelper.notEmpty_assert(jobId, "任務編號不能為空");
		values.put("jobId", jobId);
		return findBy(query, values);
	}
	@Override
	public List<JobHistory> findJobHistoryById(String Id) {
		StringBuffer query=new StringBuffer();
		Map values=new HashMap();
		query.append("from "+JobHistory.class.getName()+" j where j.id=:Id");
		AssertHelper.notEmpty_assert(Id, "任務編號不能為空");
		values.put("Id", Id);
		return findBy(query, values);
	}
	@Override
	public List<JobCalendarRelation> findJobCalendarRelationByJobId(String jobId) {
		StringBuffer query=new StringBuffer();
		Map values=new HashMap();
		query.append("from "+JobCalendarRelation.class.getName()+" j where j.jobId=:jobId");
		AssertHelper.notEmpty_assert(jobId, "任務編號不能為空");
		values.put("jobId", jobId);
		return findBy(query, values);
	}
	@Override
	public List<JobParameter> findJobParameterByJobId(String jobId) {
		StringBuffer query=new StringBuffer();
		Map values=new HashMap();
		query.append("from "+JobParameter.class.getName()+" j where j.jobId=:jobId");
		AssertHelper.notEmpty_assert(jobId, "任務編號不能為空");
		values.put("jobId", jobId);
		return findBy(query, values);
	}
	@Override
	public List<JobCalendarRelation> findJobCalendarRelationByJobIdAndCalenderid(
			String jobId, String calendarId) {
		StringBuffer query=new StringBuffer();
		Map values=new HashMap();
		query.append("from "+JobCalendarRelation.class.getName()+" j where j.jobId=:jobId and calendarId=:calendarId");
		AssertHelper.notEmpty_assert(jobId, "任務編號不能為空");
		values.put("jobId", jobId);
		AssertHelper.notEmpty_assert(calendarId, "任務編號不能為空");
		values.put("calendarId", jobId);
		return findBy(query, values);
	}
	
	/**   
	 * @return  
	 * @see com.deloitte.tms.pl.job.dao.IJobDefinitionDao#getJobByName()  
	 */
	
	
}
