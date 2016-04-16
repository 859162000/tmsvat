package com.deloitte.tms.base.cache.controller;

import javax.annotation.Resource;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import com.deloitte.tms.base.cache.utils.LegalEntityCacheUtils;
import com.deloitte.tms.base.cache.utils.OrgCacheUtils;
import com.deloitte.tms.base.cache.utils.PrintOrgCacheUtils;
import com.deloitte.tms.pl.dictionary.service.DictionaryService;
import com.deloitte.tms.pl.job.task.JobTest;
import com.deloitte.tms.pl.security.security.SecurityUtils;

@Component(RefreshCacheJob.BEAN_ID)
public class RefreshCacheJob implements JobTest, Job {
	
	public static final String BEAN_ID="refreshCacheJob";
	
	@Resource
	DictionaryService dictionaryService;
	
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		execute();
	}

	@Override
	public void execute() {
		SecurityUtils.refreshUrlSecurityMetadata();
		SecurityUtils.refreshComponentSecurityMetadata();
		dictionaryService.reloadDictionary();
		OrgCacheUtils.refreshBizOrgNode();
		LegalEntityCacheUtils.refreshLegalEntityNode();
		PrintOrgCacheUtils.refreshPrintOrgNode();
	}

}
