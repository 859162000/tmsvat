package com.deloitte.tms.pl.security.datainit.impl;

import org.hibernate.Session;
import org.springframework.stereotype.Controller;

import com.deloitte.tms.pl.core.context.utils.ContextUtils;
import com.deloitte.tms.pl.core.datainit.impl.BaseMenuGenerator;
import com.deloitte.tms.pl.security.model.impl.DefaultUrl;

@Controller
public class DbConsoleMenuGenerator extends BaseMenuGenerator {
	public void generate(Session session, String rootId) {
		DefaultUrl secondUrl = this.createUrl("数据库浏览器", " url(>skin>common/icons.gif) -300px -0px", rootId, 8, null,ContextUtils.getFixedCompanyId());
		session.save(secondUrl);
		DefaultUrl childUrl = this.createUrl("数据库浏览器", " url(>skin>common/icons.gif) -300px -0px", secondUrl.getId(), 1, "bdf2.dbconsole.view.DbConsoleMaintain.d",ContextUtils.getFixedCompanyId());
		session.save(childUrl);
	}
}
