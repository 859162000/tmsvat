package com.deloitte.tms.vat.fscsap.ftputils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.log4j.Logger;


public class ReadFTPFile {
	private Logger logger = Logger.getLogger(ReadFTPFile.class);

	/**
	 * 去 服务器的FTP路径下上读取文件
	 * 
	 * @param ftpUserName
	 * @param ftpPassword
	 * @param ftpPath
	 * @param FTPServer
	 * @return
	 */
	public String readConfigFileForFTP(String ftpUserName, String ftpPassword,
			String ftpPath, String ftpHost, int ftpPort, String fileName) {
		StringBuffer resultBuffer = new StringBuffer();
		FileInputStream inFile = null;
		InputStream in = null;
		FTPClient ftpClient = null;
		logger.info("start to read files under path:" + ftpPath);
		try {
			ftpClient = FTPUtil.getFTPClient(ftpHost, ftpPassword, ftpUserName,
					ftpPort);
			ftpClient.setControlEncoding("UTF-8"); // 中文支持
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE); //?
			
			ftpClient.enterLocalPassiveMode();
			ftpClient.changeWorkingDirectory(ftpPath);//  /
			in = ftpClient.retrieveFileStream(fileName);//FSC_20160405_I.OK FSC_20160405_I.OK.DAT
		} catch (FileNotFoundException e) {
			logger.error("no found file:" + ftpPath);
			e.printStackTrace();
			return "error:no foudn file:"+ftpPath;
		} catch (SocketException e) {
			logger.error("SocketException.");
			e.printStackTrace();
			return "error:SocketException.";
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("IOException");
			e.printStackTrace();
			return "error:IOException";
		}catch(Exception e){
			e.printStackTrace();
			return "error:last default error";
		}
		if (in != null) {
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String data = null;
			try {
				while ((data = br.readLine()) != null) {
					resultBuffer.append(data + "\n");
				}
			} catch (Exception e) {
				logger.error("Exception when read ok/data file content, but input stream is ok means input ok/data file exists, so just waining no error needed");
				e.printStackTrace();
				return "warning:Exception when read ok/data file content, but input stream is ok means input ok/data file exists, so just waining no error needed";
			}finally{
				try {
					ftpClient.disconnect();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}else{
			logger.error("inputStream in is null, means input ok file no exists");
			return "error:inputStream in is null, means input ok file no exists";
		}
		return resultBuffer.toString();
	}
}

