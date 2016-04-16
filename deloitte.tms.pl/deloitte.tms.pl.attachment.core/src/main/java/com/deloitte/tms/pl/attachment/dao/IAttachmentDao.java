package com.deloitte.tms.pl.attachment.dao;
// Generated 2013-5-23 15:15:47 by Hibernate Tools 3.2.0.beta8

import java.util.List;
import java.util.Map;

import com.deloitte.tms.pl.attachment.model.Attachment;
import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.dao.IDao;
/**
 * Home object for domain model class Attachment.
 * @see com.Attachment.smp.Attachmentmanager.model.Attachment
 * @author Hibernate Tools
 */
public interface IAttachmentDao extends IDao<Attachment>{
	public static final String BEAN_ID="attachmentDao";
	public DaoPage findAttachmentByParams(Map params, Integer pageIndex,Integer pageSize);
	public List findAttachmentByParams(Map params);
	public String findLastAttachment(String relationId, String groupId);
}

