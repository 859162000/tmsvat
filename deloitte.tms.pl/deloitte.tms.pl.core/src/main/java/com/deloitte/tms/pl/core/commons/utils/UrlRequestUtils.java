package com.deloitte.tms.pl.core.commons.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class UrlRequestUtils {
	
	static Log log=LogFactory.getLog(UrlRequestUtils.class);
	
	public static String getRequest(String requestUrl,String message) throws IOException{
		log.info("url:"+requestUrl+" message:"+message);
		URL url = new URL(requestUrl);
		URLConnection connection = url.openConnection();
		connection.setUseCaches(false);
		connection.setDoInput(true);		
		if(AssertHelper.notEmpty(message)){
			connection.setDoOutput(true);//初始化访问报文
			//发送报文
			OutputStream out = connection.getOutputStream();
			out.write(message.getBytes());
			out.flush();
			FileUtils.safeClose(out);
		}		
		//获取服务端反馈信息
		InputStream in = connection.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(in,"utf-8"));
		String aLine;
		StringBuffer sb = new StringBuffer();
		while ((aLine = reader.readLine()) != null) {
			sb.append(aLine + "\n");
		}
		//关闭流
		FileUtils.safeClose(in);
		log.info("result:"+sb.toString());
		return sb.toString();
	}
}
