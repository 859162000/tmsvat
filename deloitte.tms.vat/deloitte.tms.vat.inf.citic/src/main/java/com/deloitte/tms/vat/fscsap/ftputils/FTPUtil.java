package com.deloitte.tms.vat.fscsap.ftputils;

import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.util.Properties;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;

public class FTPUtil {
	private static Logger logger = Logger.getLogger(FTPUtil.class);

	
	
	/**
	 * 获取FTPClient对象
	 * @param ftpHost FTP主机服务器
	 * @param ftpPassword FTP 登录密码
	 * @param ftpUserName FTP登录用户名
	 * @param ftpPort FTP端口 默认为21
	 * @return
	 */
	public static FTPClient getFTPClient(String ftpHost, String ftpPassword,
			String ftpUserName, int ftpPort) {
		FTPClient ftpClient = null;
		try {
			ftpClient = new FTPClient();
			ftpClient.connect(ftpHost, ftpPort);// 连接FTP服务器
			ftpClient.login(ftpUserName, ftpPassword);// 登陆FTP服务器
			if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
				logger.info("can't login FTP, username or password wrong");
				ftpClient.disconnect();
			} else {
				logger.info("FTP connect ok");
			}
		} catch (SocketException e) {
			e.printStackTrace();
			logger.info("FTP's IP may wrong, please correct");
		} catch (IOException e) {
			e.printStackTrace();
			logger.info("FTP's port may wrong, please correct");
		}
		return ftpClient;
	}
}
