package com.deloitte.tms.pl.security.datainit.controller.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.core.commons.utils.SessionFactoryUtils;
import com.deloitte.tms.pl.core.commons.utils.SpringUtil;
import com.deloitte.tms.pl.core.commons.utils.StringUtils;
import com.deloitte.tms.pl.core.context.ContextHolder;
import com.deloitte.tms.pl.core.context.utils.ContextUtils;
import com.deloitte.tms.pl.core.controller.IController;
import com.deloitte.tms.pl.security.datainit.MenuGenerator;
import com.deloitte.tms.pl.security.model.SecurityUser;
import com.deloitte.tms.pl.security.model.impl.DefaultUrl;
@Component
public class GenerateSystemMenuController implements IController {
	@Value("${ling2.disabledGenerateSystemMenuController}")
	private boolean disabled;
	private Collection<MenuGenerator> menuGenerators;
	public String getUrl() {
		return "/generate.system.menu";
	}
	private Collection<MenuGenerator> getMenuGenerators() {
		if(menuGenerators==null)
		{
			menuGenerators=SpringUtil.getBeansOfType(MenuGenerator.class);
		}
		return menuGenerators;
	}
	public void execute(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException {
		Session session=SessionFactoryUtils.getSessionFactory().openSession();
		try{
			SecurityUser user=ContextUtils.getLoginUser();
			if(!user.isAdministrator()){
				PrintWriter out=response.getWriter();
				out.println("You are not a system administrator,so you can't do this!");
				out.flush();
				out.close();
				return;
			}
			String companyId=user.getCompanyId();
			if(StringUtils.isNotEmpty(ContextUtils.getFixedCompanyId())){
				companyId=ContextUtils.getFixedCompanyId();
			}
			String rootId="root-"+companyId;
			this.deleteSystemUrl(rootId, session);
			session.createQuery("delete "+DefaultUrl.class.getName()+" where id=:id").setString("id",rootId).executeUpdate();
			DefaultUrl root=new DefaultUrl();
			root.setId(rootId);
			root.setCompanyId(companyId);
			root.setForNavigation(true);
			root.setName("系统管理");
			root.setIcon("url(skin>common/icons.gif) -102px -101px");
			root.setOrder(1);
			session.save(root);
			for(MenuGenerator generator:getMenuGenerators()){
				generator.generate(session,rootId);
			}
			PrintWriter pw=response.getWriter();
			try{
				pw.write("<font color='green'>Successful generating system menu</font>");
			}finally{
				pw.flush();
				pw.close();
			}
		}finally{
			session.flush();
			session.close();
		}
//		MainFrame frame=ContextHolder.getBean(MainFrame.BEAN_ID);
//		frame.cacheNavigatorUrls();
	}
	
	@SuppressWarnings("unchecked")
	private void deleteSystemUrl(String parentId,Session session){
		List<DefaultUrl> urls=session.createQuery("from "+DefaultUrl.class.getName()+" where parentId=:parentId").setString("parentId", parentId).list();
		session.createQuery("delete "+DefaultUrl.class.getName()+" where parentId=:parentId").setString("parentId", parentId).executeUpdate();
		for(DefaultUrl url:urls){
			int count=0;
			Query countQuery=session.createQuery("select count(*) from "+DefaultUrl.class.getName()+" where parentId=:parentId").setString("parentId", url.getId());
			Object countObj=countQuery.uniqueResult();
			if(countObj instanceof Long){
				count=((Long)countObj).intValue();
			}else if(countObj instanceof Integer){
				count=((Integer)countObj).intValue();
			}
			if(count>0){
				deleteSystemUrl(url.getId(),session);	
			}
		}
	}

	public boolean anonymousAccess() {
		return false;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public boolean isDisabled(){
		return disabled;
	}
}
