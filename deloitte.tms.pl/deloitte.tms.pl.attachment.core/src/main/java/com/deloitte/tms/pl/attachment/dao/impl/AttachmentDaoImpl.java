package com.deloitte.tms.pl.attachment.dao.impl;
// Generated 2013-5-23 15:15:47 by Hibernate Tools 3.2.0.beta8

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.attachment.dao.IAttachmentDao;
import com.deloitte.tms.pl.attachment.model.Attachment;
import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.dao.impl.BaseDao;
/**
 * Home object for domain model class Attachment.
 * @see com.Attachment.smp.Attachmentmanager.model.Attachment
 * @author Hibernate Tools
 */
@Component(IAttachmentDao.BEAN_ID)
public class AttachmentDaoImpl extends BaseDao<Attachment> implements IAttachmentDao{
	public DaoPage findAttachmentByParams(Map params, Integer pageIndex,Integer pageSize)
	{
		StringBuffer query=new StringBuffer();
		Map values=new HashMap();
		buildQuery(params, query, values);
		return pageBy(query, values, pageIndex, pageSize);
	}	
	public List findAttachmentByParams(Map params)
	{
		StringBuffer query=new StringBuffer();
		Map values=new HashMap();
		buildQuery(params, query, values);
		return findBy(query, values);
	}
	private void buildQuery(Map params, StringBuffer query, Map values) {
		query.append(" from Attachment where 1=1 ");
		Object value=params.get("relationId");
		if(value!=null&&!"".equals(value))
		{
			query.append(" and relationId=:relationId");
			values.put("relationId", value);
		}
		value=params.get("groupId");
		if(value!=null)
		{
			query.append(" and groupId=:groupId");
			values.put("groupId", value);
		}
		value=params.get("used");
		if(value!=null)
		{
			query.append(" and used=:used");
			values.put("used", value);
		}
		query.append(" and flag=:flag");
		values.put("flag", "1");
		query.append(" order by sortOrder asc,fileTime desc ");
	}
	public String findLastAttachment(String relationId, String groupId)
	{
		StringBuffer query=new StringBuffer();
		Map values=new HashMap();
		query.append(" select max(id) from Attachment where 1=1 ");

		query.append(" and relationId=:relationId");
		values.put("relationId", relationId);
		
		query.append(" and groupId=:groupId");
		values.put("groupId", groupId);
		
		query.append(" and flag=:flag");
		values.put("flag", "1");
		Object result=getFirstRecord(findBy(query, values));
		return result==null?null:result.toString();
	}
}

