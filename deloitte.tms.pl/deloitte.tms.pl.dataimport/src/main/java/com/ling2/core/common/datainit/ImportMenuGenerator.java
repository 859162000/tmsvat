package com.ling2.core.common.datainit;

import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.ling2.core.datainit.impl.BaseMenuGenerator;
import com.ling2.security.model.impl.DefaultUrl;

@Component
public class ImportMenuGenerator extends BaseMenuGenerator{
	@Override
	public void generate(Session session, String parentMenuId) {
		int order=1;
		DefaultUrl importurl=createUrl("数据导入配置", " url(>skin>common/icons.gif) -100px -20px", parentMenuId, order++,null);
		session.save(importurl);
		DefaultUrl childurl=createUrl("对象类型数据导入", " url(>skin>common/icons.gif) -60px -20px", importurl.getId(), order++,"dataimport.view.ExcelClassMaintain.ling");
		session.save(childurl);
		
	}
}
