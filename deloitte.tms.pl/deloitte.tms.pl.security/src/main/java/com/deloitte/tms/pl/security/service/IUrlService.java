package com.deloitte.tms.pl.security.service;

import java.util.Collection;
import java.util.List;

import com.deloitte.tms.pl.core.service.IService;
import com.deloitte.tms.pl.security.model.impl.DefaultUrl;
import com.deloitte.tms.pl.security.model.impl.RoleResource;
import com.deloitte.tms.pl.security.model.impl.UrlComponent;

public interface IUrlService extends IService{
	public static final String BEAN_ID="ling2.urlService";
	List<DefaultUrl> loadUrlsByRoleId(String roleId);
	List<UrlComponent> loadComponentUrlsByRoleId(String roleId);
	List<DefaultUrl> loadUrlsByParentId(String parentId);
	public Integer loadChildNumByParentId(String parentId);
	List<UrlComponent> loadUrlsByUrlIdAndroleId(String urlId,String roleId);
	public void saveRoleUrls(String roleId,Collection<String> ids);
//	public Collection<DefaultUrl> loadMeunUrlsFromCache(String parentId);
//	public Collection<DefaultUrl> loadContainChildMeunUrlsFromCache(String parentId);
//	public void cacheNavigatorUrls();
	public List<DefaultUrl> loadAllUrls();
	public void deleteDefaultUrlById(String id);
	List<RoleResource> loadRoleResourceByRoleId(String roleid);
}
