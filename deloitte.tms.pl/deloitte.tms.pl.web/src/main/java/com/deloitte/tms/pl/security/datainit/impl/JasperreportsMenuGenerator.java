package com.deloitte.tms.pl.security.datainit.impl;

import org.hibernate.Session;

import com.deloitte.tms.pl.core.context.utils.ContextUtils;
import com.deloitte.tms.pl.core.datainit.impl.BaseMenuGenerator;
import com.deloitte.tms.pl.security.model.impl.DefaultUrl;


/**
 * @author Jacky.gao
 * @since 2013-5-22
 */
public class JasperreportsMenuGenerator extends BaseMenuGenerator {

	public void generate(Session session,String rootId) {
		DefaultUrl secondUrl=this.createUrl("报表管理", "url(skin>common/icons.gif) -162px -41px", rootId, 10,null,ContextUtils.getFixedCompanyId());
		session.save(secondUrl);
		DefaultUrl childUrl=this.createUrl("报表维护", "url(skin>common/icons.gif) -282px -41px", secondUrl.getId(), 1,"bdf2.jasperreports.view.report.ReportMaintain.d",ContextUtils.getFixedCompanyId());
		session.save(childUrl);
	}
}
