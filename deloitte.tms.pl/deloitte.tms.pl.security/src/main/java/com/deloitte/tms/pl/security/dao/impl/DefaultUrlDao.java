package com.deloitte.tms.pl.security.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.core.dao.impl.BaseDao;
import com.deloitte.tms.pl.security.dao.IUrlDao;
import com.deloitte.tms.pl.security.model.impl.DefaultUrl;
import com.deloitte.tms.pl.security.model.impl.RoleResource;
import com.deloitte.tms.pl.security.model.impl.UrlComponent;
@Component(IUrlDao.BEAN_ID)
public class DefaultUrlDao extends BaseDao implements IUrlDao{
	public List<DefaultUrl> loadUrlsByRoleId(String roleId) {
		StringBuffer query=new StringBuffer();
		Map values=new HashMap<String, Object>();
		query.append("SELECT U FROM "+RoleResource.class.getName()+" R, "+DefaultUrl.class.getName()+" U where R.urlId=U.id and R.roleId=:roleId");
		values.put("roleId", roleId);
		return findBy(query, values);
	}

	public List<UrlComponent> loadComponentUrlsByRoleId(String roleId) {
		StringBuffer query=new StringBuffer();
		Map values=new HashMap<String, Object>();
		query.append("select UC FROM "+UrlComponent.class.getName()+" UC  where UC.roleId=:roleId");
		values.put("roleId", roleId);
		return findBy(query, values);
	}
	public List<DefaultUrl> loadUrlsByParentId(String parentId){
		StringBuffer query=new StringBuffer();
		Map values=new HashMap<String, Object>();
		query.append("SELECT U FROM "+DefaultUrl.class.getName()+" U WHERE U.forNavigation=:forNavigation ");
		values.put("forNavigation", true);
		if (StringUtils.isNotEmpty(parentId)) {
			query.append("AND U.parentId = :parentId ORDER BY U.order ASC ");
			values.put("parentId", parentId);
		} else {
			query.append("AND U.parentId IS NULL ORDER BY U.order ASC ");
		}
		return findBy(query, values);
	}
	public Integer loadChildNumByParentId(String parentId){
		StringBuffer query=new StringBuffer();
		Map values=new HashMap<String, Object>();
		query.append("SELECT count(*) FROM "+DefaultUrl.class.getName()+" U WHERE 1=1 ");
		query.append("AND U.parentId = :parentId");
		values.put("parentId", parentId);
		return queryForInt(query.toString(), values);
	}
	public List<UrlComponent> loadUrlsByUrlIdAndroleId(String urlId,String roleId)
	{
		StringBuffer query=new StringBuffer();
		Map values=new HashMap<String, Object>();
		query.append("from " + UrlComponent.class.getName()+ " uc where uc.urlId=:urlId and uc.roleId=:roleId");
		values.put("urlId", urlId);
		values.put("roleId", roleId);
		return findBy(query, values);
	}

	@Override
	public List<RoleResource> loadRoleResourceByRoleId(String roleId) {
		StringBuffer query=new StringBuffer();
		Map values=new HashMap<String, Object>();
		query.append("from "+RoleResource.class.getName()+" rr where rr.roleId=:roleId");
		values.put("roleId", roleId);
		return findBy(query, values);
	}
	//String sql = "SELECT COUNT(*) FROM BDF2_URL WHERE PARENT_ID_=?";
			//int count = this.getJdbcTemplate().queryForInt(sql, new Object[] { parentId });

	@Override
	public void deleteDefaultUrlById(String id) {
		// TODO Auto-generated method stub
		StringBuffer query=new StringBuffer();
		Map values=new HashMap<String, Object>();
		query.append("delete from DefaultUrl rr where rr.id=:Id or rr.parentId=:parentId");
		values.put("Id", id);
		values.put("parentId", id);
		executeHqlProduce(query.toString(), values);
	}
	public List<DefaultUrl> loadAllUrls(){
		StringBuffer query=new StringBuffer();
		Map values=new HashMap<String, Object>();
		query.append(" from DefaultUrl ");
		return findBy(query, values);
	}
}

