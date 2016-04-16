package com.deloitte.tms.pl.ftp.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public interface FtpService {
	/**
	 * 上传文件到FTP
	 * 
	 * @param fis 需要上传的文件流
	 * @param fileName 文件名字
	 * @param fileType 文件类型 zip,txt
	 * @return
	 * @throws IOException
	 */
	String uplodFile(InputStream fis, String dirPath,String fileName,
			Map<String, Object> parameters) throws IOException;
	/**
	 * 获取ftp服务器上的文件
	 * @param filePath
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	InputStream getFtpFile(String filePath, String fileName)
			throws IOException;
	/**
	 * 获取ftp服务器上的文件并转换为字符串
	 * @param filePath
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public String getFtpFileAsString(String filePath, String fileName)
			throws IOException;
	/**
	 * 移除文件
	 * @param remotePath
	 * @param fileName
	 */
	void removeFileFromFtp(String remotePath, String fileName);
}
