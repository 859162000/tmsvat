package com.deloitte.tms.pl.attachment.core.utils;

import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.deloitte.tms.pl.attachment.model.Attachment;
import com.deloitte.tms.pl.attachment.service.AttachmentService;
import com.deloitte.tms.pl.attachment.service.FileManager;
import com.deloitte.tms.pl.attachment.service.UploadFileProcessor;
import com.deloitte.tms.pl.attachment.service.impl.BacFileManagerImpl;
import com.deloitte.tms.pl.core.commons.exception.BusinessException;
import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.core.commons.utils.PropertiesUtils;
import com.deloitte.tms.pl.core.commons.utils.SpringUtil;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

public class AttachmentUtils{
	public static AttachmentService attachmentService;
	private static final Log log = LogFactory.getLog(AttachmentUtils.class);
	public static String getAttachmentUrl(String id){
		//防止输出异常
		try {
			if(AssertHelper.notEmpty(id)){
				return getAttachmentUrl(Long.parseLong(id));
			}
		} catch (Exception e) {
			log.error("编号为:"+id+"的文件读取错误");
//			return "";
		}
		return null;
//		return (String) PropertiesUtils.get("ling2.defaultfile.url");
	}
	public static String getAttachmentUrl(Attachment attachment){
		AssertHelper.notEmpty_assert(attachment,"文件不能为空");
		if(FileManager.STORE_BAC.equals(attachment.getStoreType()))
		{
			FileManager fileManager=SpringUtil.getBean(FileManager.CLOUD_BAIDU_BEAN_ID);
			return fileManager.getUrlByFileId(attachment.getId().toString());
		}else if (FileManager.STORE_DISK.equals(attachment.getStoreType())) {
			FileManager fileManager=SpringUtil.getBean(FileManager.DISK_BEAN_ID);
			return fileManager.getUrlByFileId(attachment.getId().toString());
		}
		return null;
	}
	public static String getAttachmentUrl(Long id){
		Attachment attachment=getFilePath(id);
		return getAttachmentUrl(attachment);
	}
	public static AttachmentService getAttachmentService() {
		if(attachmentService==null)
		{
			attachmentService=SpringUtil.getBean(AttachmentService.BEAN_ID);
		}
		return attachmentService;
	}
	public static Attachment getFilePath(Long id) throws BusinessException {
		Attachment attachment = (Attachment) getAttachmentService().findById(Attachment.class, id);
		if(attachment==null)
		{
			throw new BusinessException("文件編號錯誤");
		}
		return attachment;
	}
	public static UploadFileProcessor getUploadFileProcessor(String groupId){
		Collection<UploadFileProcessor> processors=SpringUtil.getBeansOfType(UploadFileProcessor.class);
		for(UploadFileProcessor processor:processors){
			if(processor.isProcesserOfGroupId(groupId)){
				return processor;
			}
		}
		return null;
	}
}
