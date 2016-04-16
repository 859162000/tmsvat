package com.deloitte.tms.pl.file.service;

public interface SystemRootFolderService {
	public static final String ATTACHMENT = "ATTACHMENT";
	public static final String BEAN_ID="systemRootFolderService";
	public String getRootPath(String type);
}
