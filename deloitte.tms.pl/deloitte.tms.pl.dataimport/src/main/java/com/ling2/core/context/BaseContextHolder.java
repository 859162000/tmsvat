//package com.ling2.core.context;
//
//import java.util.Map;
//
//import org.springframework.beans.BeansException;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ApplicationContextAware;
//import org.springframework.stereotype.Component;
//
//import com.ling2.channel.core.utils.constant.ContextDef;
//import com.ling2.core.IContextHolder;
//import com.ling2.core.commons.utils.SpringUtil;
//import com.ling2.core.dao.impl.BaseDaoSimple;
//import com.ling2.orgpath.model.OrgPath;
//import com.ling2.security.model.User;
//@Component(IContextHolder.BEAN_ID)
//public class BaseContextHolder implements IContextHolder,ApplicationContextAware {
//	ApplicationContext applicationContext;
//	static BaseDaoSimple<?> dao;
//	@SuppressWarnings({ "unchecked", "rawtypes" })
//	@Override
//	public void initUserDetail(User user) {
//		if(dao==null)
//		{
//			dao = (BaseDaoSimple<?>) SpringUtil
//			.getBean("baseDaoSimple");
//		}
//		Map userCache=user.getCachedProperties();
//		userCache.put(ContextDef.CHATYPE,null);
//		userCache.put(ContextDef.DEPTCODE,null);
//		userCache.put(ContextDef.DEPTNAME,null);
//		userCache.put(ContextDef.DIVISIONCODE,null);
//		userCache.put(ContextDef.DIVISIONNAME,null);
//		OrgPath orgPath=(OrgPath) dao.get(OrgPath.class,"1");
//		userCache.put(ContextDef.ORGPATH,orgPath);
//		userCache.put(ContextDef.USERCODE,null);
//		userCache.put(ContextDef.USERNAME,null);
//		userCache.put(ContextDef.VERSIONCODE,null);
//		userCache.put(ContextDef.TOPDIVISIONCODE,"1");
//		userCache.put(ContextDef.TOPDIVISIONCODE,"太平人寿保险有限公司");
//	}
//	@Override
//	public void setApplicationContext(ApplicationContext applicationContext)
//			throws BeansException {
//		this.applicationContext=applicationContext;
//	}
//	
//}
