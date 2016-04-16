package com.deloitte.tms.pl.attachment.controller.impl;

import javax.annotation.Resource;
import javax.servlet.*;  
import javax.servlet.http.*;  

import java.io.*;  
import java.util.*;  

import org.apache.commons.fileupload.*;  
import org.apache.commons.fileupload.servlet.*;  
import org.apache.commons.fileupload.disk.*;  
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.attachment.model.Attachment;
import com.deloitte.tms.pl.attachment.service.AttachmentService;
import com.deloitte.tms.pl.attachment.service.FileManager;
import com.deloitte.tms.pl.core.commons.exception.BusinessException;
import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.core.commons.utils.RequestUtils;
import com.deloitte.tms.pl.core.commons.utils.SpringUtil;
import com.deloitte.tms.pl.core.controller.IController;

@Component
public class FileUploadController implements IController{  
	@Value("${ling2.storeType}")
	String storeType;
	
	@Resource
	AttachmentService attachmentService;
  
	public String getUrl() {
		return "/attachment.upload";
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		List<FileItem> fileList=getFileLists(request, response);
		Map<String, String> params=getPostParams(request,fileList);
		if(fileList.size()>0){
			String groupId = params.get("groupId");
			String relationId = params.get("relationId");
			if(groupId==null||relationId==null||"".equals(relationId)||"".equals(groupId))
			{
				throw new BusinessException("分组编号和关系编号不能为空");
			}
			
			FileManager fileManager=getFileManager();
			
			for(FileItem item:fileList){		
				if(AssertHelper.notEmpty(item.getName())){
					Attachment filebean=fileManager.saveFile(item, relationId, groupId);
//					String result="{'fileId':'"+filebean.getId()+"','url':'"+fileManager.getUrlByFileId(filebean.getId().toString())+"'}";
					String result=filebean.getId()+"";
					response.getWriter().print(result);
				}			
			}
		} 
	}
	
	@Override
	public boolean anonymousAccess() {
		return false;
	}
	
	@Override
	public boolean isDisabled() {
		return false;
	}  
	public Map<String, String> getPostParams(HttpServletRequest request,List<FileItem> items) {
		Map<String, String> result = new HashMap<String, String>();
		result.putAll(RequestUtils.getParameterMap(request));
		System.out.println(request.getHeader("relationId"));
		Iterator<FileItem> iter = items.iterator();
		while (iter.hasNext()) {
			FileItem item = iter.next();
			if (item.isFormField() && null == item.getName()) {
				try {
					result.put(item.getFieldName(),
							inputStream2String(item.getInputStream()));
				} catch (Exception e) {
	//				e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	private String inputStream2String(InputStream is) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		StringBuffer sb = new StringBuffer();
		String line = "";
		while (null != (line = bf.readLine())) {
			sb.append(line);
		}
		return sb.toString();
	}
	
	private FileManager getFileManager(){
		FileManager fileManager;
		if(FileManager.STORE_BAC.equals(storeType))
		{
			fileManager=SpringUtil.getBean(FileManager.CLOUD_BAIDU_BEAN_ID);
		}else {
			fileManager=SpringUtil.getBean(FileManager.DISK_BEAN_ID);
		}
		return fileManager;
	}
	
	private List<FileItem> getFileLists(HttpServletRequest request, HttpServletResponse response) {
		DiskFileItemFactory fac = new DiskFileItemFactory();
	    ServletFileUpload upload = new ServletFileUpload(fac);
	    upload.setHeaderEncoding("utf-8");
	    List<FileItem> fileList = new ArrayList<FileItem>();
	    try {
	        fileList = upload.parseRequest(request);
	    } catch (FileUploadException ex) {
	    }
	    return fileList;
//		// 判断enctype属性是否为multipart/form-data  
//		boolean isMultipart = ServletFileUpload.isMultipartContent(request);  
//		  
//		// Create a factory for disk-based file items  
//		DiskFileItemFactory factory = new DiskFileItemFactory();  
//		  
//		// 当上传文件太大时，因为虚拟机能使用的内存是有限的，所以此时要通过临时文件来实现上传文件的保存  
//		// 此方法是设置是否使用临时文件的临界值（单位：字节）  
//		//factory.setSizeThreshold(yourMaxMemorySize);  
//		  
//		// 与上一个结合使用，设置临时文件的路径（绝对路径）  
//		//factory.setRepository(yourTempDirectory);  
//		  
//		// Create a new file upload handler  
//		ServletFileUpload upload = new ServletFileUpload(factory);  
//		upload.setHeaderEncoding("utf-8");
//		// 设置上传内容的大小限制（单位：字节）  
//		//upload.setSizeMax(yourMaxRequestSize);  
//		  
//		// Parse the request  
//		List<FileItem> fileList = new ArrayList<FileItem>();  
//		List<?> items;
//		try {
//			items = upload.parseRequest(request);
//			Iterator<?> iter = items.iterator();  
//			while (iter.hasNext()) {  
//			    FileItem item = (FileItem) iter.next();  
//			  
//			    if (item.isFormField()) {  
//			        //如果是普通表单字段  
//			        String name = item.getFieldName();  
//			        String value = item.getString();  
//			        System.out.println(name);
//			        System.out.println(value);
//			    } else {  
//			        //如果是文件字段  
////			        String fieldName = item.getFieldName();  
////			        String fileName = item.getName();  
////			        String contentType = item.getContentType();  
////			        boolean isInMemory = item.isInMemory();  
////			        long sizeInBytes = item.getSize();  
//			    	fileList.add(item);
//			    }  
//			} 
//		} catch (FileUploadException e) {
//			
//		}  
//		return fileList;
	}
}  