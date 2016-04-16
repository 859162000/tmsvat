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
public class WorkFlowMenuGenerator extends BaseMenuGenerator{

	public void generate(Session session,String rootId) {
		DefaultUrl secondUrl=this.createUrl("工作流管理", "dorado/res/icons/jbpm.gif", rootId, 2,null,ContextUtils.getFixedCompanyId());
		session.save(secondUrl);
		DefaultUrl childUrl=this.createUrl("配置与监控", "url(skin>common/icons.gif) -102px -21px", secondUrl.getId(),2,"uflo.console.view.ProcessMaintain.ling",ContextUtils.getFixedCompanyId());
		session.save(childUrl);
		childUrl=this.createUrl("待办任务", "url(skin>common/icons.gif) -62px -21px", secondUrl.getId(), 1,"uflo.console.view.TodoTaskMaintain.ling",ContextUtils.getFixedCompanyId());
		session.save(childUrl);
		childUrl=this.createUrl("节假日设定", "url(skin>common/icons.gif) -62px -21px", secondUrl.getId(), 1,"uflo.console.view.CalendarMaintain.ling",ContextUtils.getFixedCompanyId());
		session.save(childUrl);
		childUrl=this.createUrl("开始流程", "url(skin>common/icons.gif) -62px -21px", secondUrl.getId(), 1,"uflo.form.view.process.ProcessList.ling",ContextUtils.getFixedCompanyId());
		session.save(childUrl);
		childUrl=this.createUrl("模板在线设计", "url(skin>common/icons.gif) -62px -21px", secondUrl.getId(), 1,"uflo.designer.view.UfloDesignerMaintain.ling",ContextUtils.getFixedCompanyId());
		session.save(childUrl);
		
		
	}
}
