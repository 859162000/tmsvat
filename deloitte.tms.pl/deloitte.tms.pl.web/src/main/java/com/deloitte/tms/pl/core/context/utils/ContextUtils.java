package com.deloitte.tms.pl.core.context.utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.Assert;

import com.deloitte.tms.pl.core.commons.constant.ContextDef;
import com.deloitte.tms.pl.core.commons.exception.BusinessException;
import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.core.commons.utils.PropertiesUtils;
import com.deloitte.tms.pl.core.commons.utils.SpringUtil;
import com.deloitte.tms.pl.core.context.ContextHolder;
import com.deloitte.tms.pl.core.context.IContextHolder;
import com.deloitte.tms.pl.core.dao.impl.BaseDaoSimple;
import com.deloitte.tms.pl.security.model.SecurityRole;
import com.deloitte.tms.pl.security.model.SecurityUser;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

public class ContextUtils implements ApplicationContextAware,InitializingBean {
	
	ApplicationContext applicationContext;
	
	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}
	static BaseDaoSimple<?> dao;
	@Override
	public void afterPropertiesSet() throws Exception {
		 dao= (BaseDaoSimple<?>) SpringUtil
					.getBean("baseDaoSimple");
	}
	
	public static BaseDaoSimple<?> getDao() {
		return dao;
	}
	public static SecurityUser getLoginUser(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Object principal = authentication == null ? null : authentication.getPrincipal();
		SecurityUser user=null;
		if(principal != null && principal instanceof SecurityUser){
			user= (SecurityUser) principal;
		}		
		return user;
	}
	public static boolean isLogin() {
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		if((authentication==null) || (authentication instanceof AnonymousAuthenticationToken)){
			return false;
		}else{
			return true;
		}
	}
	public static String getLoginUserName(){
		return getLoginUser().getUsername();
	}
	/**
	 * 获取当前用户的信息
	 * 
	 * @return
	 */
	public static SecurityUser getCurrentUserWithAssert() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Object principal = authentication == null ? null : authentication.getPrincipal();
		Assert.state(principal != null && principal instanceof SecurityUser, "用户没有登录!");
		SecurityUser user= (SecurityUser) principal;
		return user;
	}
	public static SecurityUser getCurrentUser () {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Object principal = authentication == null ? null : authentication.getPrincipal();
		SecurityUser user=null;
		if(principal != null && principal instanceof SecurityUser){
			user= (SecurityUser) principal;
		}		
		if(user!=null&&!user.isInit()){
			IContextHolder contextHolder=SpringUtil.getBean(IContextHolder.BEAN_ID);
			contextHolder.initUserDetail(user, null, null);
		}
		return user;
	}
	public static Collection<SecurityRole> getCurrentUserRoles () {
		SecurityUser user=getCurrentUser();
		Map userCache=user.getCachedProperties();
		Collection<SecurityRole> roles=(Collection<SecurityRole>) userCache.get(ContextDef.ROLES);
		return roles;
	}
	public static Boolean hasRole (String roleName) {
		Collection<SecurityRole> roles=getCurrentUserRoles();
		for(SecurityRole role:roles){
			if(roleName.equals(role.getName())){
				return true;
			}
		}
		return false;
	}
	/**
	 * 获取当前用户缓存的属性集
	 * 
	 * @return
	 */
	public static Map<String, Object> getCachedProperties() {
		if(getCurrentUser()==null){
//			throw new BusinessException("没有登录或登录已经过期,请重新登录");
			return new HashMap();
		}
		Map<String, Object> map = getCurrentUser().getCachedProperties();
		if (map == null) {
			map = new LinkedHashMap<String, Object>();
		}
		return map;
	}

	/**
	 * 获取当前用户缓存的属性值
	 * 
	 * @param key
	 * @return
	 */
	public static <X> X getCachedProperty(String key) {
		return (X)getCachedProperties().get(key);
	}

	/**
	 * 缓存属性
	 * 
	 * @param key
	 * @param object
	 * @return
	 */
	public static Object cacheProperty(String key, Object object) {
		return getCachedProperties().put(key, object);
	}
	
	public static String getFixedCompanyId() {
		SecurityUser user = getLoginUser();
		String companyId=null;
		if (user == null) {
//			throw new NoneLoginException("Please login first");
		}else{
			companyId=user.getCompanyId();
			if(!AssertHelper.notEmpty(companyId))
			{
				companyId= (String)PropertiesUtils.get(ContextDef.FIXED_COMPANY_ID);
			}			
		}
		if(companyId==null)
		{
			companyId="ling2";
		}
		return companyId;
	}
	public static String getDataOwner()
	{
		return getCachedProperty(ContextDef.DATA_OWNER_CODE);
	}
	public static String getOperationOrgCode()
	{
		return getCachedProperty(ContextDef.DIVISIONCODE);
	}
	public static String getCurrentOrgId()
	{
		return getCachedProperty(ContextDef.DIVISIONCODE);
	}
//	public static String getDataOwner() {
//		return getCachedProperty(ContextDef.DIVISIONCODE);
//	}
	public static String getCurrentUserCode() {
		return getCachedProperty(ContextDef.USERCODE);
//		return "Program";
	}

	public static String getCurrentUserName() {
		return getCachedProperty(ContextDef.USERCODE);
	}
	public static String getRoles() {
		return getCachedProperty(ContextDef.ROLES_STR);
	}
}
