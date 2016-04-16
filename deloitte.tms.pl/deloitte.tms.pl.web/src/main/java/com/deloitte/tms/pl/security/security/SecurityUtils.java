package com.deloitte.tms.pl.security.security;

import java.util.Collection;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;

import com.deloitte.tms.pl.core.commons.utils.PropertiesUtils;
import com.deloitte.tms.pl.core.context.ContextHolder;
import com.deloitte.tms.pl.security.controller.impl.UserUrlCacheUtils;
import com.deloitte.tms.pl.security.enums.AuthorityType;
import com.deloitte.tms.pl.security.security.decision.ComponentAccessDecisionManager;
import com.deloitte.tms.pl.security.security.decision.UrlAccessDecisionManager;
import com.deloitte.tms.pl.security.security.metadata.ComponentMetadataSource;
import com.deloitte.tms.pl.security.security.metadata.UrlMetadataSource;
import com.deloitte.tms.pl.security.service.IUrlService;

/**
 * @since 2013-1-28
 * @author Jacky.gao
 */
public class SecurityUtils {
	/**
	 * 用于检查当前给定的用户是否有权限访问给定的URL
	 * @param authentication 要访问该资源的用户认证对象
	 * @param url 目标URL
	 * @throws AccessDeniedException
	 */
	public static void checkUrl(UserAuthentication authentication,String url) throws AccessDeniedException{
		UrlMetadataSource metadata=ContextHolder.getBean(UrlMetadataSource.BEAN_ID);
		Collection<ConfigAttribute> configAttributes=metadata.getAttributes(url);
		//System.out.println(url+"size:"+configAttributes.size());
		if(configAttributes==null || configAttributes.size()==0){
			return;
		}
		UrlAccessDecisionManager decisionManager=ContextHolder.getBean(UrlAccessDecisionManager.BEAN_ID);
		decisionManager.decide(authentication,null, configAttributes);
	}
	/**
	 * 判断指定人对指定的URL下的组件有没有访问权限
	 * @param authentication 要访问该URL下的组件的用户认证对象
	 * @param type 要访问该目标组件的哪个权限属性，目前有两个，既读和写
	 * @param componentId 组件的ID，注意，这里的组件ID必须包含其所属的URL，格式为url+"|"+componentId+"|"+AuthorityType+"|"+granted
	 * @return 返回true表示有权限，false则表示无权限
	 */
	public static boolean checkComponent(UserAuthentication authentication,AuthorityType type,String componentId){
		boolean componentPermissionWithoutURL=PropertiesUtils.getBoolean("ling2.enableComponentPermissionWithoutURL");
		boolean isRapidoComponent=false;
		String checkComponentId=componentId+"|"+type.toString();
		Collection<ConfigAttribute> configAttributes = getAttribute(checkComponentId);
		if(configAttributes==null || configAttributes.size()==0){
			if(componentPermissionWithoutURL){
				String rapidoCompoentId=checkComponentId.substring(checkComponentId.indexOf("|"));
				configAttributes = getAttribute(rapidoCompoentId);
				if(configAttributes==null || configAttributes.size()==0){
					return true;
				}else{
					isRapidoComponent=true;
				}
			}else{
				return true;
			}
		}
		ComponentAccessDecisionManager decisionManager=ContextHolder.getBean(ComponentAccessDecisionManager.BEAN_ID);
		boolean granted=decisionManager.decide(authentication,configAttributes);
		if(type.equals(AuthorityType.read) && !granted){
			Collection<ConfigAttribute> writeConfigAttributes;
			checkComponentId=componentId+"|"+AuthorityType.write.toString();
			if(isRapidoComponent){
				String rapidoCompoentId=checkComponentId.substring(checkComponentId.indexOf("|"));
				writeConfigAttributes = getAttribute(rapidoCompoentId);
				if(configAttributes!=null && configAttributes.size()>0){
					if(!decisionManager.decide(authentication,writeConfigAttributes)){
						//在读写权限都没有的情况下，默认将直接给用户不能读的权限，也就是不允许用户看到组件
						return false;					
					}else{
						return true;
					}
				}
			}else{
				writeConfigAttributes = getAttribute(checkComponentId);
				if(writeConfigAttributes!=null && writeConfigAttributes.size()>0){
					if(!decisionManager.decide(authentication,writeConfigAttributes)){
						//在读写权限都没有的情况下，默认将直接给用户不能读的权限，也就是不允许用户看到组件
						return false;					
					}else{
						return true;
					}
				}
			}
		}
		return granted;
	}
	
	public static int getComponentAttributeSize(String componentId,AuthorityType type){
		String checkComponentId=componentId+"|"+type.toString();
		Collection<ConfigAttribute> configAttributes = getAttribute(checkComponentId);
		if(configAttributes==null || configAttributes.size()==0){
			return 0;
		}else{
			return configAttributes.size();
		}
	}
	
	private static Collection<ConfigAttribute> getAttribute(String componentId) {
		ComponentMetadataSource metadata=ContextHolder.getBean(ComponentMetadataSource.BEAN_ID);
		Collection<ConfigAttribute> configAttributes=metadata.getAttributes(componentId);
		if(configAttributes==null || configAttributes.size()==0){
			componentId=componentId.substring(1,componentId.length());
			configAttributes=metadata.getAttributes(componentId);
		}
		return configAttributes;
	}
	
	public static void refreshUrlSecurityMetadata(){
		UrlMetadataSource metadata=ContextHolder.getBean(UrlMetadataSource.BEAN_ID);
		metadata.initUrlMetaData();
		UserUrlCacheUtils.cacheNavigatorUrls();
	}
	
	public static void refreshComponentSecurityMetadata(){
		ComponentMetadataSource metadata=ContextHolder.getBean(ComponentMetadataSource.BEAN_ID);
		metadata.initComponentMetadata();
	}
}
