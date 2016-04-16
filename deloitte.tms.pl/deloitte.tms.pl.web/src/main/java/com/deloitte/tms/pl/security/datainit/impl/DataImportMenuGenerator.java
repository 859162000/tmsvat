package com.deloitte.tms.pl.security.datainit.impl;

import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.core.context.utils.ContextUtils;
import com.deloitte.tms.pl.core.datainit.impl.BaseMenuGenerator;
import com.deloitte.tms.pl.security.model.impl.DefaultUrl;

@Component
public class DataImportMenuGenerator extends BaseMenuGenerator{

	public void generate(Session session,String rootId) {
		DefaultUrl secondUrl=this.createUrl("数据导入管理", "url(skin>common/icons.gif) -100px -20px", rootId, 1,null,ContextUtils.getFixedCompanyId());
		session.save(secondUrl);
		DefaultUrl childUrl=this.createUrl("bdf模板维护", "url(skin>common/icons.gif) -62px -141px", secondUrl.getId(),1,"dataimport.view.ExcelMaintain.ling",ContextUtils.getFixedCompanyId());
		session.save(childUrl);
		childUrl=this.createUrl("类转换模板维护", "url(skin>common/icons.gif) -142px -101px", secondUrl.getId(),2,"dataimport.view.ExcelClassMaintain.ling",ContextUtils.getFixedCompanyId());
		session.save(childUrl);	
	}
}
