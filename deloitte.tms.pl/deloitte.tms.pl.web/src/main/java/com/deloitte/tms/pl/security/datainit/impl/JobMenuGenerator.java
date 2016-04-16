package com.deloitte.tms.pl.security.datainit.impl;

import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.core.context.utils.ContextUtils;
import com.deloitte.tms.pl.core.datainit.impl.BaseMenuGenerator;
import com.deloitte.tms.pl.security.model.impl.DefaultUrl;

/**
 * @author Jacky.gao
 * @since 2013-4-11
 */
@Component
public class JobMenuGenerator extends BaseMenuGenerator {

	public void generate(Session session,String rootId) {
		DefaultUrl secondUrl=this.createUrl("任务调度", "url(skin>common/icons.gif) -122px -101px", rootId, 3,null,ContextUtils.getFixedCompanyId());
		session.save(secondUrl);
		DefaultUrl childUrl=this.createUrl("Job定义", "url(skin>common/icons.gif) -122px -41px", secondUrl.getId(), 1,"job.view.job.JobMaintain.ling",ContextUtils.getFixedCompanyId());
		session.save(childUrl);
		childUrl=this.createUrl("调度服务监控", "url(skin>common/icons.gif) -222px -81px", secondUrl.getId(), 3,"job.view.analysis.SchedulerAnalysis.ling",ContextUtils.getFixedCompanyId());
		session.save(childUrl);
		childUrl=this.createUrl("节假日设定", "url(skin>common/icons.gif) -102px -41px", secondUrl.getId(), 2,"job.view.calendar.CalendarMaintain.ling",ContextUtils.getFixedCompanyId());
		session.save(childUrl);
	}
}
