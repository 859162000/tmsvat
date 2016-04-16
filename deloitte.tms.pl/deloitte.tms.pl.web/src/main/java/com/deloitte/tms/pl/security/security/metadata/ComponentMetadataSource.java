package com.deloitte.tms.pl.security.security.metadata;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.cache.CacheUtils;
import com.deloitte.tms.pl.core.context.utils.ContextUtils;
import com.deloitte.tms.pl.security.model.SecurityUser;
import com.deloitte.tms.pl.security.model.impl.DefaultRole;
import com.deloitte.tms.pl.security.model.impl.RoleMember;
import com.deloitte.tms.pl.security.model.impl.UrlComponent;
import com.deloitte.tms.pl.security.security.attribute.AttributeType;
import com.deloitte.tms.pl.security.security.attribute.SecurityConfigAttribute;
import com.deloitte.tms.pl.security.service.IRoleService;
import com.deloitte.tms.pl.security.service.IUrlService;

/**
 * @since 2013-1-28
 * @author Jacky.gao
 */
@Component(ComponentMetadataSource.BEAN_ID)
public class ComponentMetadataSource {
	public static final String BEAN_ID="ling2.componentMetadataSource";
	private String componentMetadataCacheKey="component_metadata_";
	@Resource
	private IRoleService roleService;
	@Resource
	private IUrlService urlService;
	public Collection<ConfigAttribute> getAttributes(String urlComponentId)
			throws IllegalArgumentException {
		SecurityUser user=ContextUtils.getLoginUser();
		if(user==null){
			return null;
		}
		Map<String,Collection<Map<String,Collection<ConfigAttribute>>>> metadata=getMetadata();
		String orgCompanyId=user.getCompanyId();
		Collection<Map<String,Collection<ConfigAttribute>>> attributeMaps=null;
		String[] companyIds=orgCompanyId.split(",");
		for(String companyId:companyIds){
			attributeMaps=metadata.get(companyId);
			if(attributeMaps!=null)break;
		}
		if(attributeMaps==null){
			return null;
		}
		Collection<ConfigAttribute> attributes=null;
		for(Map<String,Collection<ConfigAttribute>> map:attributeMaps){
			if(map.containsKey(urlComponentId)){
				attributes=map.get(urlComponentId);
				break;
			}
		}
		Collection<ConfigAttribute> attributesResult=new ArrayList<ConfigAttribute>();
		if(attributes==null){
			return attributesResult;
		}	
		return attributes;
	}

	@SuppressWarnings("unchecked")
	private Map<String,Collection<Map<String,Collection<ConfigAttribute>>>> getMetadata(){
		Map<String,Collection<Map<String,Collection<ConfigAttribute>>>> metadata= (Map<String,Collection<Map<String,Collection<ConfigAttribute>>>>)CacheUtils.getCacheObject(componentMetadataCacheKey);
		if(metadata==null){
			initComponentMetadata();
			metadata= (Map<String,Collection<Map<String,Collection<ConfigAttribute>>>>)CacheUtils.getCacheObject(componentMetadataCacheKey);
		}
		return metadata;
	}
	
	public void initComponentMetadata(){
		/**
		 * metadata这个map中key为companyId,value是一Collection,这个collection中的map的key为url+|+componentId,value为ConfigAttribute集合
		 * */
		Map<String,Collection<Map<String,Collection<ConfigAttribute>>>> metadata=new HashMap<String,Collection<Map<String,Collection<ConfigAttribute>>>>();
		for(DefaultRole role:roleService.loadAllRoles()){
			role.setRoleMembers(roleService.loadRoleMemberByRoleId(role.getId()));
			role.setUrlComponents(urlService.loadComponentUrlsByRoleId(role.getId()));
		
			String companyId=role.getCompanyId();
			Collection<Map<String,Collection<ConfigAttribute>>> urlComponentAttributes=null;
			if(!metadata.containsKey(companyId)){
				urlComponentAttributes=new ArrayList<Map<String,Collection<ConfigAttribute>>>();
				metadata.put(companyId, urlComponentAttributes);
			}else{
				urlComponentAttributes=metadata.get(companyId);
			}
			for(UrlComponent uc:role.getUrlComponents()){
				Collection<ConfigAttribute> configAttributes=this.buildConfigAttributes(role.getRoleMembers(),uc,role.getCompanyId());
				String targetUrl=uc.getUrl().getUrl();
				targetUrl=((targetUrl==null)?"":targetUrl);
				String urlCmoponentId=targetUrl+"|"+uc.getComponent().getComponentId()+"|"+uc.getAuthorityType().toString();
				Collection<ConfigAttribute> attributes=null;
				
				for(Map<String,Collection<ConfigAttribute>> map:urlComponentAttributes){
					if(map.containsKey(urlCmoponentId)){
						attributes=map.get(urlCmoponentId);
						break;
					}
				}
				if(attributes==null){
					attributes=configAttributes;
					Map<String,Collection<ConfigAttribute>> map=new HashMap<String,Collection<ConfigAttribute>>();
					map.put(urlCmoponentId, attributes);
					urlComponentAttributes.add(map);
				}else{
					attributes.addAll(configAttributes);						
				}
			}
		}
		CacheUtils.putCacheObject(componentMetadataCacheKey, metadata);
	}
	
	private Collection<ConfigAttribute> buildConfigAttributes(List<RoleMember> members,UrlComponent uc,String companyId){
		Collection<ConfigAttribute> attributes=new ArrayList<ConfigAttribute>();
		for(RoleMember member:members){
			AttributeType type=null;
			Object obj=null;
			if(member.getUser()!=null){
				type=AttributeType.user;
				obj=member.getUser();
			}
			if(member.getDept()!=null){
				type=AttributeType.dept;
				obj=member.getDept();
			}
			if(member.getPosition()!=null){
				type=AttributeType.position;
				obj=member.getPosition();
			}
			if(member.getGroup()!=null){
				type=AttributeType.group;
				obj=member.getGroup();
			}
			SecurityConfigAttribute att=new SecurityConfigAttribute(type,member.isGranted(),companyId);
			att.setMember(obj);
			attributes.add(att);
		}
		return attributes;
	}
	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}

	public void setUrlService(IUrlService urlService) {
		this.urlService = urlService;
	}
}
