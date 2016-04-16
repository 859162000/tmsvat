package com.deloitte.tms.pl.attachment.service.impl;
// Generated 2013-5-23 15:15:47 by Hibernate Tools 3.2.0.beta8

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.attachment.core.utils.AttachmentUtils;
import com.deloitte.tms.pl.attachment.dao.IAttachmentDao;
import com.deloitte.tms.pl.attachment.model.Attachment;
import com.deloitte.tms.pl.attachment.service.AttachmentService;
import com.deloitte.tms.pl.attachment.service.UploadFileProcessor;
import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.commons.utils.BatchUtils;
import com.deloitte.tms.pl.core.commons.utils.ConverterUtils;
import com.deloitte.tms.pl.core.dao.IDao;
import com.deloitte.tms.pl.core.service.impl.BaseService;
/**
 * Home object for domain model class Attachment.
 * @see com.Attachment.smp.Attachmentmanager.model.Attachment
 * @author Hibernate Tools
 */
@Component(AttachmentService.BEAN_ID)
public class AttachmentServiceImpl extends BaseService implements AttachmentService{
	@Resource
	IAttachmentDao attachmentDao;

	@Override
	public IDao getDao() {
		return attachmentDao;
	}

	@Override
	public DaoPage findAttachmentByParams(Map params, Integer pageIndex,
			Integer pageSize) {
		return attachmentDao.findAttachmentByParams(params, pageIndex, pageSize);
	}
	@Override
	public List findAttachmentByParams(Map params) {
		return attachmentDao.findAttachmentByParams(params);
	}
	@Override
	public void saveDataListsMap(Map dataListsMap) {
		Collection<Attachment> deleteList = BatchUtils.to(Attachment.class).getDeleteEntities(dataListsMap);
		Collection<Attachment> insertList =  BatchUtils.to(Attachment.class).getInsertEntities(dataListsMap);
		Collection<Attachment> updateList =  BatchUtils.to(Attachment.class).getModifiedEntities(dataListsMap);
		if ((updateList != null) && (updateList.size() > 0)) {
			attachmentDao.updateAll(updateList);
		}
		if ((insertList != null) && (insertList.size() > 0)) {
			attachmentDao.saveAll(insertList);
		}
		if ((deleteList != null) && (deleteList.size() > 0)) {
			attachmentDao.removeAll(deleteList);
		}
		
	}
	@Override
	public Object batchDelete(Object parameters) throws Exception {
		Map inParams = (Map) parameters;
		String selectids = (String) inParams.get("selectids");
		String result="sucess";
		Map outParams = new HashMap();
		if(selectids!=null)
		{
			Long[] ids=ConverterUtils.stringToLongs(selectids);
			deleteAttachment(ids,"0");
		}
		 outParams.put("result", result);
		return outParams;
	}
	public void deleteAttachment(Long[] ids,String statu)
	{
		for(Long id:ids)
		{
			 Attachment  attachment=(Attachment) findById(Attachment.class,id.toString());
			 deleteAttachment(attachment,statu);
		}
	}
	public void deleteAttachment(Attachment attachment,String statu)
	{
		 attachment.setFlag(statu);
		 update(attachment);
	}
	@Override
	public void saveAttachmentDataListsMap(Map dataListsMap) {
		Collection<Attachment> deleteList = BatchUtils.to(Attachment.class).getDeleteEntities(dataListsMap);
		Collection<Attachment> insertList =  BatchUtils.to(Attachment.class).getInsertEntities(dataListsMap);
		Collection<Attachment> updateList =  BatchUtils.to(Attachment.class).getModifiedEntities(dataListsMap);
		if ((updateList != null) && (updateList.size() > 0)) {
			attachmentDao.updateAll(updateList);
		}
		if ((insertList != null) && (insertList.size() > 0)) {
			attachmentDao.saveAll(insertList);
		}
		if ((deleteList != null) && (deleteList.size() > 0)) {
			for(Attachment attachment:deleteList)
			{
				deleteAttachment(attachment,"0");
				UploadFileProcessor processor=AttachmentUtils.getUploadFileProcessor(attachment.getGroupId());
				if(processor!=null){
					processor.remove(attachment);
				}
			}
		}
	}

	@Override
	public List<Attachment> findAttachment(String relationId, String groupId){
		Map params=new HashMap();
		params.put("relationId", relationId);
		params.put("groupId", groupId);
		return attachmentDao.findAttachmentByParams(params);
	}
	@Override
	public String findLastAttachment(String relationId, String groupId){
		Map params=new HashMap();
		params.put("relationId", relationId);
		params.put("groupId", groupId);
		return attachmentDao.findLastAttachment(relationId,groupId);
	}
}

