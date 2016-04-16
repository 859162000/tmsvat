package com.deloitte.tms.pl.security.dao;

import java.util.List;

import com.deloitte.tms.pl.core.dao.IDao;
import com.deloitte.tms.pl.security.model.impl.DefaultUrl;
import com.deloitte.tms.pl.security.model.impl.RoleResource;
import com.deloitte.tms.pl.security.model.impl.UrlComponent;

public interface IUrlDao extends IDao {
	public static final String BEAN_ID="ling2.urlDao";
	List<DefaultUrl> loadUrlsByRoleId(String roleId);
	List<UrlComponent> loadComponentUrlsByRoleId(String roleId);
	List<DefaultUrl> loadUrlsByParentId(String parentId);
	public Integer loadChildNumByParentId(String parentId);
	List<UrlComponent> loadUrlsByUrlIdAndroleId(String urlId,String roleId);
	List<RoleResource> loadRoleResourceByRoleId(String roleid);
	public void deleteDefaultUrlById(String id);
	public List<DefaultUrl> loadAllUrls();
}
