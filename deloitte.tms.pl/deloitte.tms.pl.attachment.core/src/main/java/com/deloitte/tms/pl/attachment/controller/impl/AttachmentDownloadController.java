package com.deloitte.tms.pl.attachment.controller.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.attachment.core.utils.AttachmentUtils;
import com.deloitte.tms.pl.attachment.model.Attachment;
import com.deloitte.tms.pl.attachment.service.FileManager;
import com.deloitte.tms.pl.core.commons.exception.BusinessException;
import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.core.commons.utils.FileUtils;
import com.deloitte.tms.pl.core.controller.IController;
import com.deloitte.tms.pl.file.service.AttachmentRootFolderService;

@Component
public class AttachmentDownloadController implements IController {
	private static final Log log = LogFactory.getLog(AttachmentDownloadController.class);
	@Resource(name=FileManager.DISK_BEAN_ID)
	private FileManager fileManager;
	@Resource
	AttachmentRootFolderService attachmentRootFolderService;

	private boolean disabled = false;

	public String getUrl() {
		return "/attachment.download";
	}

	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		File file=null;
		InputStream result=null;
		try {
			String id = request.getParameter("fileId");
			Attachment attachment=null;
			if(AssertHelper.notEmpty(id))
			{
				attachment = fileManager.getAttachmentById(id);
			}else{
				String relationId = request.getParameter("relationId");
				String groupId = request.getParameter("groupId");
				if(AssertHelper.notEmpty(relationId)&&AssertHelper.notEmpty(groupId)){
					attachment=fileManager.getLastAttachmentById(relationId, groupId);
				}
			}
			if(AssertHelper.notEmpty(attachment))
			{
				String fileName = attachment.getFileName();
				String fileType = attachment.getFileType();
				long fileSize = attachment.getFileSize();
				file=fileManager.getAttachmentFileById(attachment);
				if(file.exists())
				{
					try {
						result = new FileInputStream(file);
					} catch (FileNotFoundException e) {
						throw new BusinessException("文件:"+file.getPath()+"没有找到");
					}
				}else{
					fileName=fileManager.getDefaultFileName();
					result = fileManager.getDefaultFile();			
				}	
				if(result==null){
					throw new BusinessException("文件:"+file.getPath()+"没有找到");
				}
				// System.err.println("fileName="+fileName+";	fileType="+fileType+";	ftpPath="+ftpPath);
				response.setContentType(fileType); // 设置返回的文件类型
				//response.reset();
				response.setContentType("application/x-msdownload");
				response.setContentLength((int) fileSize);
				response.addHeader("Content-Disposition", "attachment; filename=\""
						+ toUtf8String(fileName) + "\"");
				Long length = download4System(result, response.getOutputStream()); // 得到向客户端输出二进制数据的对象
				response.setContentLength(length.intValue());
			}
		} catch (IOException ex) {
			System.out.println(file.getPath());
			ex.printStackTrace();
		}finally{
//			FileUtils.safeClose(file);
			FileUtils.safeClose(result);
		}
	}

	private Long download4System(InputStream fis, OutputStream out)
			throws IOException {
		Long length = 0L;
		try {
			length = (long) fis.available();			
			byte bufferArray[]=new byte[10240];
			if(fis!=null)
			{
				int byteLength=fis.read(bufferArray);
				while(byteLength!=-1)
				{
					out.write(bufferArray,0,byteLength);
					byteLength=fis.read(bufferArray);
				}
			}
			out.flush();
			out.close();

		} catch (Exception e) {
			//e.printStackTrace();
			log.error("文件下载失败！");
		} finally {
			// 使用IO包关闭流
			IOUtils.closeQuietly(out);
		}
		return length;
	}

	private String toUtf8String(String s) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c >= 0 && c <= 255) {
				sb.append(c);
			} else {
				byte[] b;
				try {
					b = Character.toString(c).getBytes("utf-8");
				} catch (Exception ex) {
					System.out.println(ex);
					b = new byte[0];
				}
				for (int j = 0; j < b.length; j++) {
					int k = b[j];
					if (k < 0)
						k += 256;
					sb.append("%" + Integer.toHexString(k).toUpperCase());
				}
			}
		}
		return sb.toString();
	}

	public boolean anonymousAccess() {
		return true;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}
}
