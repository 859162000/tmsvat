package com.deloitte.tms.pl.file.service.impl;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.core.commons.utils.FileUtils;
import com.deloitte.tms.pl.file.service.SystemRootFolderService;
@Component(SystemRootFolderService.BEAN_ID)
public class DefaultSystemRootFolderServiceImpl implements SystemRootFolderService{
	@Value("${ling2.systemrootfolder}")
	String rootPath;
	String SEPARATOR=File.separator;
	@Override
	public String getRootPath(String type) {
		rootPath=FileUtils.ensureFolderEnd(rootPath);
		return rootPath;
	}

}
