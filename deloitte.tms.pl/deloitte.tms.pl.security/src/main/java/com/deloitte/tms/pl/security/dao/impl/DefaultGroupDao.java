package com.deloitte.tms.pl.security.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.core.dao.impl.BaseDao;
import com.deloitte.tms.pl.security.dao.IGroupDao;
import com.deloitte.tms.pl.security.model.SecurityGroup;
import com.deloitte.tms.pl.security.model.impl.DefaultGroup;
import com.deloitte.tms.pl.security.model.impl.GroupMember;

/**
 * @since 2013-1-30
 * @author Jacky.gao
 */
@Component(IGroupDao.BEAN_ID)
public class DefaultGroupDao extends BaseDao implements IGroupDao {

	public List<SecurityGroup> loadUserGroups(String username) {
		StringBuffer query=new StringBuffer();
		Map values=new HashMap<String, Object>();
		query.append("SELECT g FROM GroupMember gm,"+DefaultGroup.class.getName()+" g where gm.groupId = g.id and gm.username=:username");
		values.put("username", username);
		return findBy(query, values);
	}

	@Override
	public DaoPage loadGroups(Map params, Integer pageIndex,
			Integer pageSize) {
		StringBuffer query=new StringBuffer();
		Map values=new HashMap<String, Object>();
		query.append(" from DefaultGroup g where 1=1 ");
		query.append(" order by g.createDate desc");
		return pageBy(query, values, pageIndex,pageSize);
	}
	@Override
	public DaoPage loadGroupMembers(Map params, Integer pageIndex,
			Integer pageSize) {
		StringBuffer query=new StringBuffer();
		Map values=new HashMap<String, Object>();
		query.append(" from "+GroupMember.class.getName()+" gm where gm.groupId=:groupId ");
		Object value=params.get("type");
		if(value.equals("user")){
			query.append(" and gm.username is not null");
		}else if(value.equals("dept")){
			query.append(" and gm.deptId is not null");
		}else if(value.equals("position")){
			query.append(" and gm.positionId is not null");
		}
		value=params.get("groupId");
		AssertHelper.notEmpty_assert(value,"groupId不能为空");
		values.put("groupId", value);
		return pageBy(query, values, pageIndex,pageSize);
	}
	public List<GroupMember> loadGroupMembers(Map params)
	{
		StringBuffer query=new StringBuffer();
		Map values=new HashMap<String, Object>();
		query.append(" from "+GroupMember.class.getName()+" gm where gm.groupId=:groupId ");
		Object  value=params.get("groupId");
		AssertHelper.notEmpty_assert(value,"groupId不能为空");
		values.put("groupId", value);
		
		value=params.get("type");
		if(value.equals("user")){
			if(AssertHelper.notEmpty(params.get("username"))){
				query.append(" and gm.username=:username");
				values.put("username",params.get("username"));
			}else{
				query.append(" and gm.username is not null");
			}			
		}else if(value.equals("dept")){
			if(AssertHelper.notEmpty(params.get("deptId"))){
				query.append(" and gm.deptId=:deptId");
				values.put("deptId",params.get("deptId"));
			}else{
				query.append(" and gm.deptId is not null");
			}
		}else if(value.equals("position")){
			if(AssertHelper.notEmpty(params.get("positionId"))){
				query.append(" and gm.positionId=:positionId");
				values.put("positionId",params.get("positionId"));
			}else{
				query.append(" and gm.positionId is not null");
			}			
		}
		return findBy(query, values);
	}
}
