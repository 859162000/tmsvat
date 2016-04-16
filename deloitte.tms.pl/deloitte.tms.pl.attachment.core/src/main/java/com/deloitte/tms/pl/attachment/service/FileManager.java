package com.deloitte.tms.pl.attachment.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.fileupload.FileItem;
import org.springframework.web.multipart.MultipartFile;

import com.deloitte.tms.pl.attachment.model.Attachment;

public interface FileManager {
	public static final String STORE_DISK="STORE_DISK";
	public static final String STORE_BAC="STORE_BAC";
	public static final String DISK_BEAN_ID="diskFileManager";
	public static final String CLOUD_BAIDU_BEAN_ID="cloudBaiduFileManager";
	public Attachment saveFile(MultipartFile file,String relationId,String groupId) throws IllegalStateException, IOException;
	public Attachment saveFile(FileItem file,String relationId,String groupId) throws IllegalStateException, IOException;
	public String getUrlByFileId(String id);
	public Attachment getAttachmentById(String id);
	public Attachment getLastAttachmentById(String relationId,String groupId );	
	public InputStream getAttachmentInputStreamById(String id, Boolean isDefault);
	public InputStream getAttachmentInputStreamById(Attachment attachment, Boolean isDefault);
	public File getAttachmentFileById(String id);
	public File getAttachmentFileById(Attachment attachment);
	public InputStream getDefaultFile();
	
	public String getDefaultFileName();
	
	public void deleteAttachment(String id);
}
