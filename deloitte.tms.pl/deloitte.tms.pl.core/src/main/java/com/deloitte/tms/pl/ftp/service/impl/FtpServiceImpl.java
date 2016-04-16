package com.deloitte.tms.pl.ftp.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.core.commons.exception.BusinessException;
import com.deloitte.tms.pl.ftp.service.FtpService;

@Component("defaultFtpService")
public class FtpServiceImpl implements FtpService {
	Boolean isInit = false;
	private FTPClient ftpClient;
	
	@Value("${ling2.ftp_ip}")
	String ftp_ip;
	@Value("${ling2.ftp_userName}")
	String ftp_userName;
	@Value("${ling2.ftp_password}")
	String ftp_password;
	@Value("${ling2.ftp_port}")
	String ftp_port;
	/**
	 * 上传文件到FTP
	 * 
	 * @param fis
	 *            需要上传的文件流
	 * @param fileName
	 *            文件名字
	 * @param fileType
	 *            文件类型 zip,txt
	 * @return
	 * @throws IOException
	 */
	public String uplodFile(InputStream fis, String dirPath,String fileName,
			Map<String, Object> parameters) throws IOException {
		// 文件所存在的目录路径
		String path = dirPath + "/" + fileName;
		try {
			boolean success = false;
			init();
			isDirExist(ftpClient, dirPath);
			// 设置上传目录
			success = ftpClient.changeWorkingDirectory(dirPath);
			ftpClient.setControlEncoding("UTF-8");
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

			// 保存文件
			success = ftpClient.storeFile(fileName, fis);

			fis.close();
			if (success) {
				System.out.println(path + "上传成功");
			} else {
				System.out.println(path + "失败");
				throw new IOException();
			}
		} catch (Exception e) {
			path = null;
			e.printStackTrace();
			throw new IOException("FTP连接失败！");
		} finally {
			// IOUtils.closeQuietly(outSteam);
			try {
				ftpClient.disconnect();
			} catch (Exception ioe) {
			}
		}
		return path;
	}

	public InputStream getFtpFile(String filePath, String fileName)
			throws IOException {
		InputStream fis = null;
		boolean success = false;
		try {
			init();
			success = ftpClient.changeWorkingDirectory(filePath);
			if (success) {
				fis = ftpClient.retrieveFileStream(fileName);
				System.out.println(filePath + fileName + " 下载成功");
			} else {
				System.out.println(filePath + fileName + " 下载失败");
				throw new IOException("FTP下载失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			fis = null;
			throw new IOException("FTP连接失败！");
		}
		return fis;
	}
	public String getFtpFileAsString(String filePath, String fileName)
			throws IOException {
		InputStream fis=null;
		String content = null;
		try {
			fis = getFtpFile(filePath, fileName);
			if (fis != null) {
				content = new String(inputStreamToByte(fis), "gb2312");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new IOException("FTP连接失败！");
		} finally {
			try {
				ftpClient.disconnect();
				fis.close();
				fis = null;
				// 使用IO包关闭流
			} catch (Exception ioe) {
				
			}
		}
		return content;
	}
	/**
	 * 从FTP相对路径删除文件
	 * 
	 * @param remotePath
	 * @param fileName
	 */
	public void removeFileFromFtp(String remotePath, String fileName) {
		try {
			init();
			ftpClient.changeWorkingDirectory(remotePath);// 转移到FTP服务器目录
			FTPFile[] fs = ftpClient.listFiles();
			for (FTPFile ff : fs) {
				if (ff.getName().equals(fileName)) {
					ftpClient.deleteFile(fileName);
				}
			}
			ftpClient.logout();
		} catch (IOException e) {
			throw new BusinessException("相关工作流文件存储删除失败");
		} finally {
			if (ftpClient.isConnected()) {
				try {
					ftpClient.disconnect();
				} catch (Exception ioe) {
				}
			}
		}
	}

	private void init() {
		try {
			// 链接到ftp服务器
			ftpClient = new FTPClient();
			ftpClient.setControlEncoding("UTF-8");
			ftpClient.connect((String) ftp_ip,Integer.parseInt(ftp_port));
			System.out.println("连接到ftp服务器：" + (String) ftp_ip+ftp_port + " 成功..开始登录");
			// 登录.用户名 密码
			boolean isSuccess = ftpClient.login((String) ftp_userName,
					(String) ftp_password);
			if (isSuccess)
				System.out.println("登录成功.");
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			ftpClient.setFileTransferMode(FTP.STREAM_TRANSFER_MODE);
			isInit = true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("ftp服务器连接异常");
		}
	}

	/**
	 * ＦＴＰ路径是否存在不存在递归创建
	 * 
	 * @param ftpClient
	 * @param dir
	 * @throws IOException
	 */
	private void isDirExist(FTPClient ftp, String dir) {
		try {
			 boolean isDirExist = ftpClient.changeWorkingDirectory(dir);
			 if(!isDirExist){
				 System.out.println(dir + " not exist!");
				 if("".equals(dir)){
					 return;
				 }
				 String preUrl = dir.substring(0, dir.lastIndexOf("/"));
				 isDirExist(ftpClient,preUrl);
				 ftpClient.mkd(dir);
				 System.out.println("create " + dir);
			 }
//			String directory = remote.substring(0, remote.lastIndexOf("/") + 1);
//			// 如果远程目录不存在，则递归创建远程服务器目录
//			if (!directory.equalsIgnoreCase("/")
//					&& !ftp.changeWorkingDirectory(new String(directory))) {
//
//				int start = 0;
//				int end = 0;
//				if (directory.startsWith("/")) {
//					start = 1;
//				} else {
//					start = 0;
//				}
//				end = directory.indexOf("/", start);
//				while (true) {
//					String subDirectory = new String(remote.substring(start,
//							end));
//					if (!ftp.changeWorkingDirectory(subDirectory)) {
//						if (ftp.makeDirectory(subDirectory)) {
//							ftp.changeWorkingDirectory(subDirectory);
//						} else {
//							System.out.println("创建目录失败");
//						}
//					}
//					start = end + 1;
//					end = directory.indexOf("/", start);
//					// 检查所有目录是否创建完毕
//					if (end <= start) {
//						break;
//					}
//				}
//			}
		} catch (IOException e) {
			throw new BusinessException("ftp目录切换失败");
		}
	}

	private byte[] inputStreamToByte(InputStream is) throws IOException {
		ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
		int ch;
		while ((ch = is.read()) != -1) {
			bytestream.write(ch);
		}
		byte data[] = bytestream.toByteArray();
		bytestream.close();
		return data;
	}
}
