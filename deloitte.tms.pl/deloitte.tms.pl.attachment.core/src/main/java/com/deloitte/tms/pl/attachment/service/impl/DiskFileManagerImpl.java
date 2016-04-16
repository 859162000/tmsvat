package com.deloitte.tms.pl.attachment.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.fileupload.FileItem;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.deloitte.tms.pl.attachment.model.Attachment;
import com.deloitte.tms.pl.attachment.service.FileManager;
import com.deloitte.tms.pl.core.commons.exception.BusinessException;
import com.deloitte.tms.pl.core.commons.utils.FileUtils;
import com.deloitte.tms.pl.file.service.AttachmentRootFolderService;
import com.deloitte.tms.pl.file.service.SystemRootFolderService;

@Component(FileManager.DISK_BEAN_ID)
public class DiskFileManagerImpl extends BaseFileManagerImpl implements FileManager{
	public static final String SEPARATOR = File.separator;
	@Resource
	AttachmentRootFolderService attachmentRootFolderService;
	@Value("${ling2.dounload.url}")
	String downloadUrl;
	
	public Attachment saveFile(MultipartFile file,String relationId,String groupId) throws IllegalStateException, IOException{
		Attachment filebean=new Attachment();
		filebean.setRelationId(relationId);
		filebean.setGroupId(groupId);
		filebean.setFileName(file.getOriginalFilename());
		filebean.setFilePath("");
		filebean.setFileTime(new Date());
		filebean.setFileType(file.getContentType());
		filebean.setFileSize(file.getSize());
		filebean.setFlag("1");
		attachmentService.save(filebean);
		if(filebean.getId()==null)
		{
			throw new BusinessException("文件数据库部分数据保存失败");
		}
		String rootFolder=attachmentRootFolderService.getRootPath(SystemRootFolderService.ATTACHMENT);
		
		String fileFolder=rootFolder+SEPARATOR+groupId;
		
		String fileName=filebean.getId()+filebean.getFileName();

		String realpath=fileFolder+SEPARATOR+fileName;
		
		String relativePath=groupId+SEPARATOR+fileName;
		
		File uploadfile= new File(realpath);
		FileUtils.ensureExistence(uploadfile);
		file.transferTo(uploadfile);
		filebean.setStoreType(FileManager.STORE_DISK);
		filebean.setRelativePath(relativePath);
		attachmentService.update(filebean);
		return filebean;
	}
	public Attachment saveFile(FileItem file,String relationId,String groupId) throws IllegalStateException, IOException{
		Attachment filebean=new Attachment();
		filebean.setRelationId(relationId);
		filebean.setGroupId(groupId);
		filebean.setFileName(file.getName());
		filebean.setFilePath("");
		filebean.setFileTime(new Date());
		filebean.setFileType(file.getContentType());
		filebean.setFileSize(file.getSize());
		filebean.setFlag("1");
		attachmentService.save(filebean);
		if(filebean.getId()==null)
		{
			throw new BusinessException("文件数据库部分数据保存失败");
		}
		String rootFolder=attachmentRootFolderService.getRootPath(SystemRootFolderService.ATTACHMENT);
		
		String fileFolder=rootFolder+SEPARATOR+groupId;
		
		String fileName=filebean.getId()+filebean.getFileName();

		String realpath=fileFolder+SEPARATOR+fileName;
		
		String relativePath=groupId+SEPARATOR+fileName;
		
		File uploadfile= new File(realpath);
		FileUtils.ensureExistence(uploadfile);
		try {
			file.write(uploadfile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		filebean.setStoreType(FileManager.STORE_DISK);
		filebean.setRelativePath(relativePath);
		attachmentService.update(filebean);
		return filebean;
	}
	@Override
	public String getUrlByFileId(String id) {
		Attachment attachment = getAttachmentById(id);
		String path=FileUtils.ensureFolderEnd(downloadUrl);
		return path+attachment.getRelativePath();
	}
	public InputStream getAttachmentInputStreamById(String id, Boolean isDefault){
		Attachment attachment = getAttachmentById(id);
		return getAttachmentInputStreamById(attachment, isDefault);
	}
	public InputStream getAttachmentInputStreamById(Attachment attachment, Boolean isDefault){
		File file=getAttachmentFileById(attachment);
		InputStream result=null;
		if(file.exists())
		{
			try {
				result = new FileInputStream(file);
			} catch (FileNotFoundException e) {
				throw new BusinessException("文件:"+file.getPath()+"没有找到");
			}
		}else{
			if(isDefault){
				result = getDefaultFile();
			}			
		}	
		if(result==null){
			throw new BusinessException("文件:"+file.getPath()+"没有找到");
		}
		return result;
	}
	public File getAttachmentFileById(Attachment attachment){		
		String rootfolder=FileUtils.ensureFolderEnd(attachmentRootFolderService.getRootPath(SystemRootFolderService.ATTACHMENT));
		String filePath = rootfolder+ attachment.getRelativePath();
		File file = new File(filePath);
		return file;
	}
	public File getAttachmentFileById(String id){
		Attachment attachment=getAttachmentById(id);
		return getAttachmentFileById(attachment);
	}
	@Override
	public void deleteAttachment(String id) {
		File file=getAttachmentFileById(id);
		if(file.exists())
		{
			file.deleteOnExit();
		}
	}
	@Override
	public Attachment getLastAttachmentById(String relationId, String groupId) {
		String id=attachmentService.findLastAttachment(relationId, groupId);
		return getAttachmentById(id);
	}
}
