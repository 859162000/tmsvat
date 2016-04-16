package com.deloitte.tms.pl.security.security.metadata;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.Assert;

import com.deloitte.tms.pl.cache.CacheUtils;
import com.deloitte.tms.pl.core.commons.utils.SpringUtil;
import com.deloitte.tms.pl.core.context.utils.ContextUtils;
import com.deloitte.tms.pl.security.exception.NoneLoginException;
import com.deloitte.tms.pl.security.model.SecurityUser;
import com.deloitte.tms.pl.security.model.impl.DefaultRole;
import com.deloitte.tms.pl.security.model.impl.DefaultUrl;
import com.deloitte.tms.pl.security.model.impl.DefaultUser;
import com.deloitte.tms.pl.security.security.attribute.AttributeType;
import com.deloitte.tms.pl.security.security.attribute.SecurityConfigAttribute;
import com.deloitte.tms.pl.security.service.IRoleService;
import com.deloitte.tms.pl.security.service.IUrlService;

/**
 * @since 2013-1-23
 * @author Jacky.gao
 */
@Component(UrlMetadataSource.BEAN_ID)
public class UrlMetadataSource implements FilterInvocationSecurityMetadataSource {
	public static final String BEAN_ID="ling2.urlMetadataSource";
	@Resource
	private IRoleService roleService;
	@Resource
	private IUrlService urlService;
	@Value("${ling2.useConservativeAuthorityStrategy}")
	private boolean useConservativeAuthorityStrategy;
	private String urlMetadataCacheKey="url_metadata_";
	private Map<String,ConfigAttribute> anonymousUrlMetadata=new HashMap<String,ConfigAttribute>();
	private AntPathMatcher matcher=new AntPathMatcher();
	Collection<AnonymousUrl> safeUrls=null;
	@SuppressWarnings("unchecked")
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		String url=null;
		if(object instanceof FilterInvocation){
			HttpServletRequest request=((FilterInvocation)object).getRequest();
			url=this.getRequestPath(request);			
		}else{
			url=(String)object;
		}
		Collection<ConfigAttribute> safeUrlAttributes=this.getAnonymousUrlAttributes(url);		
		if(safeUrlAttributes!=null){
//			System.out.println(url+" safeUrlAttributes"+"size:"+safeUrlAttributes.size());
			return safeUrlAttributes;
		}
		SecurityUser loginUser=ContextUtils.getLoginUser();
		if(loginUser==null){
			throw new NoneLoginException("Please login first");
		}
		String orgCompanyId=loginUser.getCompanyId();
		Assert.hasText(orgCompanyId, "current login user["+ContextUtils.getLoginUser().getUsername()+"] is not specified company ID");
		Map<String,Map<String,List<ConfigAttribute>>> metaData=loadMetaData();
		Map<String,List<ConfigAttribute>> map=null;
		String[] companyIds=orgCompanyId.split(",");
		for(String companyId:companyIds){
			map=metaData.get(companyId);
			if(map!=null)break;
		}
		if(map==null){
			if(!useConservativeAuthorityStrategy || loginUser.isAdministrator()){
				return CollectionUtils.EMPTY_COLLECTION;				
			}else{
				throw new AccessDeniedException("Access denied");
			}
		}
//		System.out.println( "process:"+url);
		if(!map.containsKey(url)){
//			System.out.println( "!containsKey:"+url);
			url=url.substring(1,url.length());
			if(!map.containsKey(url)){
				if(!useConservativeAuthorityStrategy || loginUser.isAdministrator()){
					return CollectionUtils.EMPTY_COLLECTION;												
				}else{
					throw new AccessDeniedException("Access denied");
				}
			}else{
				List<ConfigAttribute> attributes=map.get(url);
				if(attributes.size()>0){
					return attributes;					
				}else{
					if(!useConservativeAuthorityStrategy || loginUser.isAdministrator()){
						return attributes;
					}else{
						throw new AccessDeniedException("Access denied");
					}
				}
			}
		}else{
//			System.out.println( "containsKey:"+url);
			List<ConfigAttribute> attributes=map.get(url);
//			System.out.println( "containsKey and attributes size:"+attributes.size());
			if(attributes.size()>0){
				return attributes;					
			}else{
				if(!useConservativeAuthorityStrategy || loginUser.isAdministrator()){
					return attributes;
				}else{
					throw new AccessDeniedException("Access denied");
				}
			}	
		}
	}
	
	private Collection<ConfigAttribute> getAnonymousUrlAttributes(String url){
		Collection<ConfigAttribute> attributes=null;
		for(String patternUrl:getAnonymousUrlMetadata().keySet()){
			if(matcher.match(patternUrl, url)){
				attributes=new ArrayList<ConfigAttribute>();
				attributes.add(getAnonymousUrlMetadata().get(patternUrl));
				break;
			}
		}
		return attributes;
	}

	@SuppressWarnings("unchecked")
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return CollectionUtils.EMPTY_COLLECTION;
	}

	public boolean supports(Class<?> clazz) {
		return FilterInvocation.class.isAssignableFrom(clazz);
	}
	
	@SuppressWarnings("unchecked")
	private Map<String,Map<String,List<ConfigAttribute>>> loadMetaData(){
		Map<String,Map<String,List<ConfigAttribute>>> metaData= (Map<String,Map<String,List<ConfigAttribute>>>)CacheUtils.getCacheObject(urlMetadataCacheKey);
		if(metaData==null){
			initUrlMetaData();
			metaData= (Map<String,Map<String,List<ConfigAttribute>>>)CacheUtils.getCacheObject(urlMetadataCacheKey);
		}
		return metaData;
	}
	
	public void initUrlMetaData(){
		Map<String,Map<String,List<ConfigAttribute>>> metaData=new HashMap<String,Map<String,List<ConfigAttribute>>>();
		for(DefaultRole role:roleService.loadAllRoles()){
			/***************************bo.wang用户量比较多单独处理 其他权限暂时不用***********************/
//			role.setRoleMembers(roleService.loadRoleMemberByRoleId(role.getId()));
			/***************************bo.wang用户量比较多单独处理 其他权限暂时不用***********************/
			role.setUrls(urlService.loadUrlsByRoleId(role.getId()));
			String companyId=role.getCompanyId();
			Map<String,List<ConfigAttribute>> mapAttribute=metaData.get(companyId);
			if(mapAttribute==null){
				mapAttribute=new HashMap<String,List<ConfigAttribute>>();
				metaData.put(companyId, mapAttribute);
			}
			for(DefaultUrl url:role.getUrls()){
				String targetUrl=url.getUrl();
				if(StringUtils.isEmpty(targetUrl)){
					targetUrl=url.getName();
				}
				if(StringUtils.isEmpty(targetUrl)){
					continue;
				}
				targetUrl=processUrl(targetUrl);
				List<ConfigAttribute> attributes=mapAttribute.get(targetUrl);
				if(attributes==null){
					attributes=new ArrayList<ConfigAttribute>();
					mapAttribute.put(targetUrl, attributes);
				}
				this.buildConfigAttributes(role,attributes);
				//System.out.println("initUrlMetaData:"+targetUrl+" attributes size "+attributes.size());
			}
		}
		CacheUtils.putCacheObject(urlMetadataCacheKey, metaData);
	}
	
    private String getRequestPath(HttpServletRequest request) {
        String url = request.getServletPath();
        if (request.getPathInfo() != null) {
            url += request.getPathInfo();
        }
        return url;
    }
	
	private void buildConfigAttributes(DefaultRole role,List<ConfigAttribute> list){
		/***************************bo.wang用户量比较多单独处理***********************/
		List<DefaultUser> roleUsers=roleService.loadRoleUsersByRoleId(role.getId());
		for(DefaultUser user:roleUsers){
			SecurityConfigAttribute attribute=new SecurityConfigAttribute(AttributeType.user,true,role.getCompanyId());
			attribute.setMember(user);
		}
//		for(RoleMember member:role.getRoleMembers()){
//			SecurityConfigAttribute attribute=null;
//			if(member.getUser()!=null){
//				attribute=new SecurityConfigAttribute(AttributeType.user,member.isGranted(),role.getCompanyId());
//				attribute.setMember(member.getUser());
//			}
//			if(member.getDept()!=null){
//				attribute=new SecurityConfigAttribute(AttributeType.dept,member.isGranted(),role.getCompanyId());
//				attribute.setMember(member.getDept());
//			}
//			if(member.getPosition()!=null){
//				attribute=new SecurityConfigAttribute(AttributeType.position,member.isGranted(),role.getCompanyId());
//				attribute.setMember(member.getPosition());
//			}
//			if(member.getGroup()!=null){
//				attribute=new SecurityConfigAttribute(AttributeType.group,member.isGranted(),role.getCompanyId());
//				attribute.setMember(member.getGroup());
//			}
//			if(attribute!=null){
//				list.add(attribute);
//			}			
//		}
		/***************************bo.wang用户量比较多单独处理***********************/
	}
	
	private String processUrl(String targetUrl){
		targetUrl=targetUrl.trim();
		return targetUrl;
	}
	
	private void buildSafeUrlConfigAttributes(Collection<AnonymousUrl> safeUrls){
		for(AnonymousUrl url:safeUrls){
			String pattern=url.getUrlPattern();
			SecurityConfig attribute=new SecurityConfig(AuthenticatedVoter.IS_AUTHENTICATED_ANONYMOUSLY);
			anonymousUrlMetadata.put(pattern, attribute);
		}
		
	}
	
	public Map<String, ConfigAttribute> getAnonymousUrlMetadata() {
		if(safeUrls==null)
		{
			safeUrls=SpringUtil.getContext().getBeansOfType(AnonymousUrl.class).values();
			buildSafeUrlConfigAttributes(safeUrls);
		}
		return anonymousUrlMetadata;
	}

	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}
	public void setUrlService(IUrlService urlService) {
		this.urlService = urlService;
	}

	public boolean isUseConservativeAuthorityStrategy() {
		return useConservativeAuthorityStrategy;
	}

	public void setUseConservativeAuthorityStrategy(
			boolean useConservativeAuthorityStrategy) {
		this.useConservativeAuthorityStrategy = useConservativeAuthorityStrategy;
	}
}