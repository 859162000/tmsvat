package com.deloitte.tms.pl.security.datainit.impl;

import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.core.context.utils.ContextUtils;
import com.deloitte.tms.pl.core.datainit.impl.BaseMenuGenerator;
import com.deloitte.tms.pl.security.model.impl.DefaultUrl;

/**
 * @author Jacky.gao
 * @since 2013-3-20
 */
@Component
public class CoreMenuGenerator extends BaseMenuGenerator {

	public void generate(Session session,String rootId) {
		DefaultUrl sysurl=this.createUrl("系统默认基本信息维护", "url(skin>common/icons.gif) -100px -20px", rootId, 1,null,ContextUtils.getFixedCompanyId());
		session.save(sysurl);
		DefaultUrl categoryUrl=this.createUrl("大小类维护", "url(>skin>common/icons.gif) -160px -40px", sysurl.getId(),1,"system.view.sysCategorymanager.ling",ContextUtils.getFixedCompanyId());
		session.save(categoryUrl);
//		childUrl=this.createUrl("消息模版维护", "url(skin>common/icons.gif) -302px -61px", secondUrl.getId(),5,"message.view.messagetemplate.MessageTemplateMaintain.ling");
//		session.save(childUrl);
		
		DefaultUrl securityUrl=this.createUrl("权限管理", "url(skin>common/icons.gif) -42px -41px", rootId, 2,null,null);
		session.save(securityUrl);		
		DefaultUrl childUrl=this.createUrl("用户维护", "url(skin>common/icons.gif) -142px -101px", securityUrl.getId(),1,"security.view.user.UserMaintain.ling",ContextUtils.getFixedCompanyId());
		session.save(childUrl);
		childUrl=this.createUrl("角色维护", "url(skin>common/icons.gif) -240px -80px", securityUrl.getId(), 2,"security.view.role.RoleMaintain.ling",ContextUtils.getFixedCompanyId());
		session.save(childUrl);
		childUrl=this.createUrl("菜单维护", "url(skin>common/icons.gif) -62px -141px", securityUrl.getId(),3,"security.view.url.UrlMaintain.ling",ContextUtils.getFixedCompanyId());
		session.save(childUrl);
		childUrl=this.createUrl("菜单权限维护", "url(skin>common/icons.gif) -262px -100px", securityUrl.getId(), 4,"security.view.role.url.RoleUrlMaintain.ling",ContextUtils.getFixedCompanyId());
		session.save(childUrl);
		childUrl=this.createUrl("角色成员维护", "url(skin>common/icons.gif) -102px -21px", securityUrl.getId(),5,"security.view.role.member.RoleMemberMaintain.ling",ContextUtils.getFixedCompanyId());
		session.save(childUrl);
		childUrl=this.createUrl("页面级权限维护", "url(skin>common/icons.gif) -181px -41px", securityUrl.getId(),6,"profile.view.component.ComponentMaintain.ling",ContextUtils.getFixedCompanyId());
		session.save(childUrl);
		
		DefaultUrl userproperty=this.createUrl("用户属性维护", "url(skin>common/icons.gif) -181px -41px", securityUrl.getId(),3,null,ContextUtils.getFixedCompanyId());
		session.save(userproperty);
		childUrl=this.createUrl("部门维护", "url(skin>common/icons.gif) -42px -41px", userproperty.getId(),1,"security.view.dept.DeptMaintain.ling",ContextUtils.getFixedCompanyId());
		session.save(childUrl);
		childUrl=this.createUrl("岗位维护", "url(skin>common/icons.gif) -262px -41px", userproperty.getId(),2,"security.view.position.PositionMaintain.ling",ContextUtils.getFixedCompanyId());
		session.save(childUrl);
		childUrl=this.createUrl("群组维护", "url(skin>common/icons.gif) -101px -121px", userproperty.getId(),3,"security.view.group.GroupMaintain.ling",ContextUtils.getFixedCompanyId());
		session.save(childUrl);
		
		
	}
}
