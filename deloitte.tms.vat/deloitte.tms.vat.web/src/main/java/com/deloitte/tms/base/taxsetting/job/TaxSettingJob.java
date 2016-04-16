package com.deloitte.tms.base.taxsetting.job;

import javax.annotation.Resource;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import com.deloitte.tms.base.taxsetting.dao.SalesDataDao;
import com.deloitte.tms.base.taxsetting.model.TaxSettingParam;
import com.deloitte.tms.pl.core.context.utils.ContextUtils;
import com.deloitte.tms.pl.job.task.JobTest;


@Component("getHSdataJob")
public class TaxSettingJob implements Job, JobTest {
	
	@Resource
	SalesDataDao salesDataDao;
	@Override
	public void execute() {
		TaxSettingParam param = new TaxSettingParam();
		param.setP_biz_org_code(ContextUtils.getCurrentOrgId());
		param.setP_created_by(ContextUtils.getCurrentUserCode());
		param.setP_last_updated_by(ContextUtils.getCurrentUserCode());
		param.setP_record_version("0");
		param.setP_deleted_flag("1");
		param.setP_proc_hs_inf_flag("Y");
		param.setP_proc_ss_inf_flag("Y");
		param.setP_archive_base_date("2018-01-01");
		salesDataDao.SalesDataJob(param);
	}
	
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		this.execute();
	}
}