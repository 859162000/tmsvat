package com.deloitte.tms.pl.attachment.service.impl;

import java.io.InputStream;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.attachment.model.Attachment;
import com.deloitte.tms.pl.attachment.service.AttachmentService;
import com.deloitte.tms.pl.attachment.service.FileManager;
import com.deloitte.tms.pl.core.commons.exception.BusinessException;
import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
@Component(FileManager.DISK_BEAN_ID)
public abstract class BaseFileManagerImpl implements FileManager{
	@Resource
	AttachmentService attachmentService;
	@Value("${ling2.defaultfile}")
	String defaultfile;
	
	private static final Log log = LogFactory.getLog(BacFileManagerImpl.class);
	
	public Attachment getAttachmentById(String id) throws BusinessException {
		AssertHelper.notEmpty_assert(id,"文件编号不能为空");
		Attachment attachment = (Attachment) attachmentService.findById(Attachment.class, Long.parseLong(id));
		if(attachment==null)
		{
			throw new BusinessException("文件編號錯誤");
		}
		return attachment;
	}
	public InputStream getDefaultFile(){
		InputStream is=this.getClass().getResourceAsStream(defaultfile);  
		return is;
	}
	public String getDefaultFileName(){
		return "file_not_found.png";
	}
}
