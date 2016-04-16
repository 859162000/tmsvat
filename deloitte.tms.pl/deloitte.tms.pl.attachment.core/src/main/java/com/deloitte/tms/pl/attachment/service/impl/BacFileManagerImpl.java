package com.deloitte.tms.pl.attachment.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

import org.apache.commons.fileupload.FileItem;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.baidu.inf.iis.bcs.BaiduBCS;
import com.baidu.inf.iis.bcs.auth.BCSCredentials;
import com.baidu.inf.iis.bcs.model.ObjectMetadata;
import com.baidu.inf.iis.bcs.model.X_BS_ACL;
import com.baidu.inf.iis.bcs.request.PutObjectRequest;
import com.deloitte.tms.pl.attachment.model.Attachment;
import com.deloitte.tms.pl.attachment.service.FileManager;
import com.deloitte.tms.pl.core.commons.exception.BusinessException;
import com.deloitte.tms.pl.core.commons.utils.AssertHelper;

@Component(FileManager.CLOUD_BAIDU_BEAN_ID)
public class BacFileManagerImpl extends BaseFileManagerImpl implements FileManager {
	public static final String SEPARATOR = "/";
	// ----------------------------------------
	@Value("${baidu.host}")
	String host;
	@Value("${baidu.accessKey}")
	String accessKey;
	@Value("${baidu.secretKey}")
	String secretKey;
	@Value("${baidu.bucket}")
	String bucket;
	@Value("${baidu.rootFolder}")
	String rootFolder;
	@Value("${baidu.bacurl}")
	String bacurl;
	// ----------------------------------------
	public Attachment saveFile(MultipartFile file, String relationId,
			String groupId) throws IllegalStateException, IOException {
		Attachment filebean = new Attachment();
		filebean.setRelationId(relationId);
		filebean.setGroupId(groupId);
		filebean.setFileName(file.getOriginalFilename());
		filebean.setFilePath("");
		filebean.setFileTime(new Date());
		filebean.setFileType(file.getContentType());
		filebean.setFileSize(file.getSize());
		filebean.setFlag("1");
		attachmentService.save(filebean);
		if (filebean.getId() == null) {
			throw new BusinessException("文件数据库部分数据保存失败");
		}

		String fileFolder = rootFolder + SEPARATOR + groupId;

		String fileName = filebean.getId() + filebean.getFileName();

		String realpath = fileFolder + SEPARATOR + fileName;

		String relativePath = groupId + SEPARATOR + fileName;

		ObjectMetadata objectMetadata = new ObjectMetadata();
		objectMetadata.setContentType(file.getContentType());
		objectMetadata.setContentLength(file.getSize());
		PutObjectRequest request = new PutObjectRequest(bucket, realpath,
				file.getInputStream(), objectMetadata);
		request.setAcl(X_BS_ACL.PublicRead);
		BCSCredentials credentials = new BCSCredentials(accessKey, secretKey);
		BaiduBCS baiduBCS = new BaiduBCS(credentials, host);
		// baiduBCS.setDefaultEncoding("GBK");
		baiduBCS.setDefaultEncoding("UTF-8"); // Default UTF-8
		ObjectMetadata result = baiduBCS.putObject(request).getResult();
		filebean.setStoreType(FileManager.STORE_BAC);
		filebean.setRelativePath(relativePath);
		attachmentService.update(filebean);
		return filebean;
	}
	public Attachment saveFile(FileItem file, String relationId,
			String groupId) throws IllegalStateException, IOException {
		Attachment filebean = new Attachment();
		filebean.setRelationId(relationId);
		filebean.setGroupId(groupId);
		filebean.setFileName(file.getName());
		filebean.setFilePath("");
		filebean.setFileTime(new Date());
		filebean.setFileType(file.getContentType());
		filebean.setFileSize(file.getSize());
		filebean.setFlag("1");
		attachmentService.save(filebean);
		if (filebean.getId() == null) {
			throw new BusinessException("文件数据库部分数据保存失败");
		}

		String fileFolder = rootFolder + SEPARATOR + groupId;

		String fileName = filebean.getId() + filebean.getFileName();

		String realpath = fileFolder + SEPARATOR + fileName;

		String relativePath = groupId + SEPARATOR + fileName;

		ObjectMetadata objectMetadata = new ObjectMetadata();
		objectMetadata.setContentType(file.getContentType());
		objectMetadata.setContentLength(file.getSize());
		PutObjectRequest request = new PutObjectRequest(bucket, realpath,
				file.getInputStream(), objectMetadata);
		request.setAcl(X_BS_ACL.PublicRead);
		BCSCredentials credentials = new BCSCredentials(accessKey, secretKey);
		BaiduBCS baiduBCS = new BaiduBCS(credentials, host);
		// baiduBCS.setDefaultEncoding("GBK");
		baiduBCS.setDefaultEncoding("UTF-8"); // Default UTF-8
		ObjectMetadata result = baiduBCS.putObject(request).getResult();
		filebean.setStoreType(FileManager.STORE_BAC);
		filebean.setRelativePath(relativePath);
		attachmentService.update(filebean);
		return filebean;
	}
	@Override
	public String getUrlByFileId(String id) {
		if(AssertHelper.notEmpty(id))
		{
			Attachment attachment = getAttachmentById(id);
			String relativePath=attachment.getRelativePath();
			String path="/"+attachment.getRelativePath();
			try {
				//http://bcs.duapp.com/duidui/ecommerce     /xxxx/xxxx
				return bacurl+bucket+rootFolder+URLEncoder.encode(path,"UTF-8");
			} catch (UnsupportedEncodingException e) {
			}
		}
		return "";
	}
	public static void main(String[] args) {
		String resultfolder="/commodity_main_pic/45手机绑定.gif";
		try {
			System.out.println(URLEncoder.encode(resultfolder,"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//http://bcs.duapp.com/duidui/ecommerce%2Fcommodity_main_pic%2F45%E6%89%8B%E6%9C%BA%E7%BB%91%E5%AE%9A.gif
	}
	@Override
	public InputStream getAttachmentInputStreamById(String id, Boolean isDefault) {
		throw new BusinessException("没有支持");
	}
	@Override
	public InputStream getAttachmentInputStreamById(Attachment attachment,
			Boolean isDefault) {
		throw new BusinessException("没有支持");
	}
	@Override
	public File getAttachmentFileById(String id) {
		throw new BusinessException("没有支持");
	}
	@Override
	public File getAttachmentFileById(Attachment attachment) {
		throw new BusinessException("没有支持");
	}
	@Override
	public void deleteAttachment(String id) {
		throw new BusinessException("没有支持");
	}
	@Override
	public Attachment getLastAttachmentById(String relationId, String groupId) {
		// TODO Auto-generated method stub
		return null;
	}
}
