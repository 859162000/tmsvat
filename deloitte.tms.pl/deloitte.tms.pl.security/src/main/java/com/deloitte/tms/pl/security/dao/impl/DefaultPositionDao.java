package com.deloitte.tms.pl.security.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.dao.impl.BaseDao;
import com.deloitte.tms.pl.security.dao.IPositionDao;
import com.deloitte.tms.pl.security.model.SecurityPosition;
import com.deloitte.tms.pl.security.model.impl.DefaultPosition;
@Component(IPositionDao.BEAN_ID)
public class DefaultPositionDao extends BaseDao implements IPositionDao {
	public SecurityPosition newPositionInstance(String positionId) {
		return new DefaultPosition(positionId);
	}
	public List<SecurityPosition> loadUserPositions(String username) {
		StringBuffer query=new StringBuffer();
		Map values=new HashMap<String, Object>();
		query.append("select p FROM UserPosition up ,DefaultPosition p where up.positionId = p.id and up.username=:username");
		values.put("username", username);
		return findBy(query, values);
	}
	public SecurityPosition loadPositionById(String positionId) {
		StringBuffer query=new StringBuffer();
		Map values=new HashMap<String, Object>();
		query.append("FROM DefaultPosition P WHERE P.id=:id");
		values.put("id", positionId);
		List<SecurityPosition> list=findBy(query, values);
		if(list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	public DaoPage loadPagePositions(Map params, int pageIndex,int pageSize) {
		StringBuffer query=new StringBuffer();
		Map values=new HashMap<String, Object>();
		query.append("from DefaultPosition p WHERE 1=1 ");
//		query.append("and p.companyId=:companyId");
//		values.put("companyId", ContextUtils.getFixedCompanyId());
		DaoPage result= pageBy(query,values, pageIndex, pageSize);
		return result;
	}
	public List<SecurityPosition> loadPagePositions(Map params) {
		StringBuffer query=new StringBuffer();
		Map values=new HashMap<String, Object>();
		query.append("from DefaultPosition p WHERE 1=1 ");
//		query.append("and p.companyId=:companyId");
//		values.put("companyId", ContextUtils.getFixedCompanyId());
		return findBy(query, values);
	}
	public List<SecurityPosition> findAllPosition(){
		StringBuffer query=new StringBuffer();
		Map values=new HashMap<String, Object>();
		query.append("from DefaultPosition p WHERE 1=1 ");
//		query.append("and p.companyId=:companyId");
//		values.put("companyId", ContextUtils.getFixedCompanyId());
		return findBy(query, values);
	}
	@Override
	public String uniqueCheck(String id) {
		String hql = "select count(*) from " + DefaultPosition.class.getName() + " d where d.id = :id";
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("id", id);
		if(this.queryForInt(hql, parameterMap)>0){
			return "岗位ID已存在！";
		}else{
			return null;
		}
	}
}
