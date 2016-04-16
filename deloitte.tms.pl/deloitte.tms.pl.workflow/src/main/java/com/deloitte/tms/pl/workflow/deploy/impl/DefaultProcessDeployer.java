package com.deloitte.tms.pl.workflow.deploy.impl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.annotation.Resource;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.deloitte.tms.pl.workflow.command.CommandService;
import com.deloitte.tms.pl.workflow.command.impl.DeployProcessCommand;
import com.deloitte.tms.pl.workflow.command.impl.DeployProcessResourceCommand;
import com.deloitte.tms.pl.workflow.deploy.ProcessDeployer;
import com.deloitte.tms.pl.workflow.deploy.parse.impl.ProcessParser;
import com.deloitte.tms.pl.workflow.deploy.validate.ProcessValidateException;
import com.deloitte.tms.pl.workflow.deploy.validate.impl.ProcessValidator;
import com.deloitte.tms.pl.workflow.model.ProcessDefinition;
import com.deloitte.tms.pl.workflow.utils.IDGenerator;

/**
 * @author Jacky.gao
 * @since 2013年8月3日
 */
@Component(ProcessDeployer.BEAN_ID)
public class DefaultProcessDeployer implements ProcessDeployer{
	private DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
	@Resource
	private ProcessValidator processValidator;
	@Resource
	private CommandService commandService;
	public ProcessDefinition deploy(InputStream inputStream) {
		ProcessDefinition process=null;
		long processId=IDGenerator.getInstance().nextId();
		if(inputStream instanceof ZipInputStream){
			//表示通过zip打包上传的流程模版文件，其中可能包含流程图片文件(png格式)
			ZipInputStream zipInputStream=(ZipInputStream)inputStream;
			process = deployZip(zipInputStream,processId);
		}else{
			//认为是一个以uflo.xml结尾的普通的流程模版文件
			process = deployFile(inputStream,null,processId,false);
		}
		return process;
	}
	
	public ProcessDefinition deploy(InputStream inputStream,int version, long processId) {
		ProcessDefinition process=deployFile(inputStream,version,processId,true);
		return process;
	}

	private ProcessDefinition deployFile(InputStream inputStream,Integer version,long processId,boolean update) {
		ProcessDefinition process=null;
		try{
			byte[] bytes=IOUtils.toByteArray(inputStream);
			validateProcess(new ByteArrayInputStream(bytes));
			process=ProcessParser.parseProcess(bytes,processId,update);
			process.setCreateDate(new Date());
			if(update){
				process.setVersion(version);				
			}
			commandService.executeCommand(new DeployProcessCommand(process,update));
			commandService.executeCommand(new DeployProcessResourceCommand(bytes,process.getName()+PROCESS_EXTENSION_NAME,processId,update));
		}catch(Exception ex){
			throw new RuntimeException(ex);
		}finally{
			IOUtils.closeQuietly(inputStream);
		}
		return process;
	}
	
	
	private ProcessDefinition deployZip(ZipInputStream zipInputStream,long processId) {
		ProcessDefinition process=null;
		try {
			ZipEntry zipEntry = zipInputStream.getNextEntry();
			while (zipEntry != null) {
				String entryName = zipEntry.getName();
				if(entryName.endsWith(PROCESS_EXTENSION_NAME)){
					byte[] bytes = IOUtils.toByteArray(zipInputStream);
					validateProcess(new ByteArrayInputStream(bytes));
					process=ProcessParser.parseProcess(bytes,processId,false);
					commandService.executeCommand(new DeployProcessCommand(process,false));
					commandService.executeCommand(new DeployProcessResourceCommand(bytes,entryName,processId,false));
				}else if(entryName.endsWith(PROCESSIMG_EXTENSION_NAME)){
					byte[] bytes = IOUtils.toByteArray(zipInputStream);
					commandService.executeCommand(new DeployProcessResourceCommand(bytes,entryName,processId,false));
				}
				zipEntry = zipInputStream.getNextEntry();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			IOUtils.closeQuietly(zipInputStream);
		}
		return process;
	}

	private void validateProcess(InputStream inputStream) {
		StringBuffer errorInfo=new StringBuffer();
        try {
        	Document document = documentBuilderFactory.newDocumentBuilder().parse(inputStream);
        	List<String> errors=new ArrayList<String>();
        	List<String> nodeNames=new ArrayList<String>();
        	Element element=document.getDocumentElement();
        	if(processValidator.support(element)){
        		processValidator.validate(element, errors, nodeNames);
        		if(errors.size()>0){
        			for(int i=0;i<errors.size();i++){
        				errorInfo.append((i+1)+"."+errors.get(i)+"\r\r");
        			}
        		}
        	}else{
        		errorInfo.append("当前XML文件不是一个合法的UFLO流程模版文件");
        	}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
       String msg=errorInfo.toString();
       if(StringUtils.isNotEmpty(msg)){
    	   throw new ProcessValidateException(msg);
       }
	}
	
	public void setProcessValidator(ProcessValidator processValidator) {
		this.processValidator = processValidator;
	}

	public void setCommandService(CommandService commandService) {
		this.commandService = commandService;
	}
}
