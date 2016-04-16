package com.deloitte.tms.pl.attachment.service;

import org.springframework.web.multipart.MultipartFile;

import com.deloitte.tms.pl.attachment.model.Attachment;

public interface UploadFileProcessor {
	public Boolean isProcesserOfGroupId(String groupId);
	public void validate(MultipartFile file);
	public Object process(Attachment attachment);
	public Object remove(Attachment attachment);
}
