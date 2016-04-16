package com.deloitte.tms.pl.core.context;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.deloitte.tms.pl.core.commons.constant.ContextDef;
import com.deloitte.tms.pl.core.commons.utils.IpUtil;
import com.deloitte.tms.pl.core.commons.utils.SpringUtil;
import com.deloitte.tms.pl.core.dao.impl.BaseDaoSimple;
import com.deloitte.tms.pl.security.model.SecurityUser;
public class BaseContextHolder implements IContextHolder,ApplicationContextAware {
	ApplicationContext applicationContext;
	static BaseDaoSimple<?> dao;
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void initUserDetail(SecurityUser user,HttpServletRequest request,HttpServletResponse response) {
		if(dao==null)
		{
			dao = (BaseDaoSimple<?>) SpringUtil
			.getBean("baseDaoSimple");
		}
		Map userCache=user.getCachedProperties();
		userCache.put(ContextDef.CHATYPE,null);
		userCache.put(ContextDef.DEPTCODE,null);
		userCache.put(ContextDef.DEPTNAME,null);
		userCache.put(ContextDef.DIVISIONCODE,null);
		userCache.put(ContextDef.DIVISIONNAME,null);
		userCache.put(ContextDef.USERCODE,user.getUsername());
		userCache.put(ContextDef.USERNAME,user.getCname());
		userCache.put(ContextDef.VERSIONCODE,null);
		if(response!=null){
			userCache.put(ContextDef.IP,IpUtil.getIpAddr(request));
			request.getSession().setAttribute(ContextDef.SESSION, user);
		}
		user.setInit(true);
	}
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext=applicationContext;
	}
	public static BaseDaoSimple<?> getDao() {
		return dao;
	}
	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}
}
