package com.deloitte.tms.pl.security.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.core.dao.IDao;
import com.deloitte.tms.pl.core.service.impl.BaseService;
import com.deloitte.tms.pl.security.dao.IUrlDao;
import com.deloitte.tms.pl.security.model.impl.DefaultUrl;
import com.deloitte.tms.pl.security.model.impl.RoleResource;
import com.deloitte.tms.pl.security.model.impl.UrlComponent;
import com.deloitte.tms.pl.security.service.IUrlService;
@Component(IUrlService.BEAN_ID)
public class DefaultUrlService extends BaseService implements IUrlService{
	
	@Resource
	IUrlDao urlDao;

	public List<DefaultUrl> loadUrlsByRoleId(String roleId) {
		return urlDao.loadUrlsByRoleId(roleId);
	}

	public List<UrlComponent> loadComponentUrlsByRoleId(final String roleId) {
		return urlDao.loadComponentUrlsByRoleId(roleId);
	}
	public List<DefaultUrl> loadUrlsByParentId(String parentId)
	{
		return urlDao.loadUrlsByParentId(parentId);
	}
	@Override
	public IDao getDao() {
		return urlDao;
	}

	@Override
	public List<UrlComponent> loadUrlsByUrlIdAndroleId(String urlId,
			String roleId) {
		return urlDao.loadUrlsByUrlIdAndroleId(urlId, roleId);
	}

	@Override
	public void saveRoleUrls(String roleId, Collection<String> ids) {
		List<RoleResource> roleResources=urlDao.loadRoleResourceByRoleId(roleId);
		urlDao.removeAll(roleResources);
		for(String urlId:ids){
			RoleResource rr=new RoleResource();
			rr.setId(UUID.randomUUID().toString());
			rr.setRoleId(roleId);
			rr.setUrlId(urlId);
			urlDao.save(rr);			
		}
		
	}

	@Override
	public Integer loadChildNumByParentId(String parentId) {
		return urlDao.loadChildNumByParentId(parentId);
	}

	@Override
	public List<DefaultUrl> loadAllUrls() {
		return urlDao.find("from DefaultUrl where 1=1");
	}

	@Override
	public void deleteDefaultUrlById(String id) {
		urlDao.deleteDefaultUrlById(id);
	}

	@Override
	public List<RoleResource> loadRoleResourceByRoleId(String roleid) {
		return urlDao.loadRoleResourceByRoleId(roleid);
	}
}
