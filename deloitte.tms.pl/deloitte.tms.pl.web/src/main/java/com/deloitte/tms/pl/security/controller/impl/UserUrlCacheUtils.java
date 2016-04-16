
/**    
 * Copyright (C),Deloitte
 * @FileName: UserUrlCache.java  
 * @Package: com.deloitte.tms.pl.security.controller.impl  
 * @Description: //模块目的、功能描述  
 * @Author bo.wang  
 * @Date 2016年3月17日 下午4:23:57  
 * @History: //修改记录
 * 〈author〉      〈time〉      〈version〉       〈desc〉
 * 修改人姓名            修改时间            版本号              描述   
*/  

package com.deloitte.tms.pl.security.controller.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.access.AccessDeniedException;

import com.deloitte.tms.pl.cache.CacheUtils;
import com.deloitte.tms.pl.core.commons.utils.SpringUtil;
import com.deloitte.tms.pl.core.context.utils.ContextUtils;
import com.deloitte.tms.pl.security.exception.NoneLoginException;
import com.deloitte.tms.pl.security.model.SecurityUser;
import com.deloitte.tms.pl.security.model.impl.DefaultUrl;
import com.deloitte.tms.pl.security.security.SecurityUtils;
import com.deloitte.tms.pl.security.security.UserAuthentication;
import com.deloitte.tms.pl.security.service.IUrlService;


/**  
 * 
 * @author bo.wang
 * @create 2016年3月17日 下午4:23:57 
 * @version 1.0.0
 */

public class UserUrlCacheUtils {
	
	public static final String URL_FOR_NAVI_CACHE_KEY="url_for_navi_cache_key_";
	
	static IUrlService urlService;
	
	public static IUrlService getUrlService(){
		if(urlService==null){
			urlService=SpringUtil.getBean(IUrlService.BEAN_ID);
		}
		return urlService;
	}
	
	@SuppressWarnings("unchecked")
	public static Collection<DefaultUrl> loadMeunUrlsFromCache(String parentId) {
		SecurityUser user = ContextUtils.getLoginUser();
		if (user == null) {
			throw new NoneLoginException("Please login first");
		}
		String companyId=user.getCompanyId();
		List<DefaultUrl> cacheUrls=(List<DefaultUrl>)CacheUtils.getCacheObject(URL_FOR_NAVI_CACHE_KEY);
		if(cacheUrls==null){
			cacheNavigatorUrls();
			cacheUrls=(List<DefaultUrl>)CacheUtils.getCacheObject(URL_FOR_NAVI_CACHE_KEY);
		}
		Collection<DefaultUrl> urls = getCacheUrls(cacheUrls,companyId,parentId);
		UserAuthentication authentication = new UserAuthentication(user);
		Collection<DefaultUrl> result = new ArrayList<DefaultUrl>();
		authorityCheck(urls,authentication,result);
		return result;
	}
	public  static Collection<DefaultUrl> loadContainChildMeunUrlsFromCache(String parentId) {
		Collection<DefaultUrl> result = loadMeunUrlsFromCache(parentId);
		loadContainChildMeunUrls(result, parentId);
		return result;
	}
	private static void authorityCheck(Collection<DefaultUrl> urls,UserAuthentication authentication,Collection<DefaultUrl> result){
		for (DefaultUrl url : urls) {
			String targetUrl = url.getUrl();
			List<DefaultUrl> children=url.getChildren();
			int childrenCount = 0;
			if(children!=null){
				childrenCount=children.size();
			}
			if (childrenCount==0 && StringUtils.isEmpty(targetUrl)) {
				continue;
			}
			if(StringUtils.isEmpty(targetUrl)){
				targetUrl = url.getName();				
			}
			try {
//				System.out.println(targetUrl);
				SecurityUtils.checkUrl(authentication, targetUrl);
				DefaultUrl newUrl=buildNewUrl(url);
				result.add(newUrl);
				if(children!=null){
					List<DefaultUrl> childrenUrls=new ArrayList<DefaultUrl>();
					newUrl.setChildren(childrenUrls);
					authorityCheck(children,authentication,childrenUrls);				
				}
			} catch (AccessDeniedException ex) {}
		}
	}
	

	private static void loadContainChildMeunUrls(Collection<DefaultUrl> result, String parentId) {
		for (DefaultUrl url : result) {
			List<DefaultUrl> childList = new ArrayList<DefaultUrl>();
			childList.addAll(loadMeunUrlsFromCache(url.getId()));
			url.setChildren(childList);
			loadContainChildMeunUrls(childList, url.getId());
		}
	}

	private static int fetchChildrenCount(List<DefaultUrl> cacheUrls,String parentId,String companyId) {
//		int count = urlService.loadChildNumByParentId(parentId);
		return getCacheUrls(cacheUrls,companyId, parentId).size();
	}

	private static List<DefaultUrl> getCacheUrls(List<DefaultUrl> urls,String companyId,String parentId){
		List<DefaultUrl> resultUrls=new ArrayList<DefaultUrl>();
		buildCacheUrls(urls,resultUrls,companyId, parentId);
		return resultUrls;
	}	
	
	private static void buildCacheUrls(List<DefaultUrl> urls,List<DefaultUrl> resultUrls,String companyId,String parentId){
		for(DefaultUrl url:urls){
			if(url.getChildren()!=null&&url.getChildren().size()>0){
				url.setHasChild(true);
			}else {
				url.setHasChild(false);
			}
			if(StringUtils.isEmpty(parentId)){
				if(StringUtils.isEmpty(url.getParentId()) && url.getCompanyId()!=null && url.getCompanyId().equals(companyId)){
					resultUrls.add(url);
				}
			}else{
				if(StringUtils.isNotEmpty(url.getParentId()) && url.getParentId().equals(parentId)){
					resultUrls.add(url);
				}
			}
			if(url.getChildren()!=null){
				buildCacheUrls(url.getChildren(), resultUrls, companyId, parentId);
			}
		}
	}
	
	public static void cacheNavigatorUrls(){
		List<DefaultUrl> urls = loadUrls(null);
		CacheUtils.putCacheObject(URL_FOR_NAVI_CACHE_KEY, urls);
	}
	
	private static List<DefaultUrl> loadUrls(String parentId){
		List<DefaultUrl> urls = new ArrayList<DefaultUrl>();
		urls= getUrlService().loadUrlsByParentId(parentId);
		for(DefaultUrl url:urls){
			url.setChildren(loadUrls(url.getId()));
		}
		return urls;
	}
	private static DefaultUrl buildNewUrl(DefaultUrl oldUrl){
		DefaultUrl url=new DefaultUrl();
		url.setId(oldUrl.getId());
		url.setName(oldUrl.getName());
		url.setDesc(oldUrl.getDesc());
		url.setUrl(oldUrl.getUrl());
		url.setIcon(oldUrl.getIcon());
		url.setParentId(oldUrl.getParentId());
		url.setCompanyId(oldUrl.getCompanyId());
		return url;
	}
}
