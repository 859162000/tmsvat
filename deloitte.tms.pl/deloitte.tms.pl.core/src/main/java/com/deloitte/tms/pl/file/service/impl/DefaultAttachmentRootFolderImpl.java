package com.deloitte.tms.pl.file.service.impl;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.file.service.AttachmentRootFolderService;
@Component(AttachmentRootFolderService.BEAN_ID)
public class DefaultAttachmentRootFolderImpl implements AttachmentRootFolderService{
	@Value("${ling2.attachmentroot}")
	String rootPath;
	String SEPARATOR=File.separator;
	@Override
	public String getRootPath(String type) {
		if(!rootPath.endsWith(SEPARATOR))
		{
			rootPath=rootPath+SEPARATOR;
		}
		return rootPath;
	}

}
