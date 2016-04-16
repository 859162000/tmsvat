package com.deloitte.tms.pl.attachment.service;
// Generated 2013-5-23 15:15:47 by Hibernate Tools 3.2.0.beta8

import java.util.List;
import java.util.Map;

import com.deloitte.tms.pl.attachment.model.Attachment;
import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.service.IService;
/**
 * Home object for domain model class Attachment.
 * @see com.Attachment.smp.Attachmentmanager.model.Attachment
 * @author Hibernate Tools
 */
public interface AttachmentService extends IService{
	
	public static final String BEAN_ID="attachmentService";
	
	public void saveAttachmentDataListsMap(Map dataListsMap);
	
	public DaoPage findAttachmentByParams(Map params, Integer pageIndex,Integer pageSize);
	
	public List findAttachmentByParams(Map params);
	
	public void saveDataListsMap(Map dataListsMap);
	
	public Object  batchDelete(Object parameters) throws Exception ;
	
	public List<Attachment> findAttachment(String relationId,String groupId);
	
	public String findLastAttachment(String relationId,String groupId);
}

