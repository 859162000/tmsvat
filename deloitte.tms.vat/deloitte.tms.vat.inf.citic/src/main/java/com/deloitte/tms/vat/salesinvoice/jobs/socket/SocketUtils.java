
/**    
 * Copyright (C),Deloitte
 * @FileName: SocketUtils.java  
 * @Package: com.deloitte.tms.vat.salesinvoice.jobs.socket  
 * @Description: //模块目的、功能描述  
 * @Author stonshi  
 * @Date 2016年3月25日 下午5:18:48  
 * @History: //修改记录
 * 〈author〉      〈time〉      〈version〉       〈desc〉
 * 修改人姓名            修改时间            版本号              描述   
*/  

package com.deloitte.tms.vat.salesinvoice.jobs.socket;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;


/**  
 * Socket交换平台工具类
 * @author stonshi
 * @create 2016年3月25日 下午5:18:48 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */

public class SocketUtils {
	
	/**
	 * 
	 */
	private final static byte[] hex = "0123456789ABCDEF".getBytes();
	
	
	/**
	 * 发送数据到交换平台
	 * @param socketHostIP   
	 * @param socketHostPort
	 * @param headlen
	 * @param data
	 * @param timeout
	 * @return
	 * @throws UnknownHostException
	 * @throws IOException
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	public static byte[] sendDataToPlatform(String socketHostIP, int socketHostPort,
			int headlen, byte[] data,int timeout) throws UnknownHostException, IOException {
		
		 System.out.println("********************sendDataToPlatform   begin*********************");
		 Socket socket = new Socket(socketHostIP,socketHostPort);
		 socket.setSoTimeout(timeout);
		 BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream()); 
		 writeByteArray(bos,data,headlen);
		 DataInputStream underlyingIs = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
		 byte[] bytes=read(underlyingIs,headlen); 
		 bos.close();
		 underlyingIs.close();
		 socket.close();
		return  bytes;
	}
	
	/**
	 * 
	 * @param is
	 * @param headlen
	 * @return
	 * @throws IOException
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	private static byte[] read(InputStream is,int headlen) throws IOException { 
		DataInputStream dis = new DataInputStream(is); 
		System.out.println("***********************read*********************************");
		byte[] lengthByt = new byte[headlen];
		dis.read(lengthByt);
		String b2h = Bytes2HexString(lengthByt);
		//前两位01需要去除
		int length = Integer.valueOf(b2h.substring(2),16);
		byte[] buffer = new byte[length]; 
		System.out.println("***********************readFully*********************************");
		dis.readFully(buffer);
		return buffer;
	}
	
	/**
	 * 写byte数组
	 * @param os
	 * @param data
	 * @param headlen
	 * @throws IOException
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	protected static void writeByteArray(OutputStream os, byte[] data,int headlen)
			throws IOException { 
		
		System.out.println("************writeByteArray*******************");
		DataOutputStream dos = new DataOutputStream(os);
		System.out.println("post message length " + data.length);
		//报文头前两位固定"01"
		byte[] head = addLength(data);
		dos.write(head);
		dos.write(data);
		dos.flush();
	}
	
	/**
	 * 根据平台要求加上报文头
	 * @param xml
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	public static byte[] addLength(byte[] xml) {
		
		System.out.println("***************addLength*******************");
		
			String i2h = "01000000"+Integer.toHexString(xml.length).toUpperCase();

			//十六进制转字节数组
			byte[] h2b = HexString2Bytes(i2h);
			byte[] head = new byte[h2b.length];
			head[0] = 0x01;
			head[1] = h2b[1];
			head[2] = h2b[2];
			head[3] = h2b[3];
			head[4] = h2b[4];
			byte[] message = new byte[xml.length + head.length];
			
			System.arraycopy(head, 0, message, 0, head.length);
			System.out.println("post to core system head length=" + head.length);
			
			System.arraycopy(xml, 0, message, head.length, xml.length);
			System.out.println("post to core system xml legnth=" + xml.length);
			
			return message;
		}
	
	/**
	 * 
	 *〈一句话功能简述〉 
	 * 功能详细描述
	 * @param value
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	public static byte[] intToByteArray(int value) {
		return new byte[] { (byte) (value >> 24 & 0xff),
				(byte) (value >> 16 & 0xff), (byte) (value >> 8 & 0xff),
				(byte) (value & 0xff) };
	}
	
	/**
	 * 
	 *〈一句话功能简述〉 
	 * 功能详细描述
	 * @param c
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	private static int parse(char c) {
		if (c >= 'a')
			return (c - 'a' + 10) & 0x0f;
		if (c >= 'A')
			return (c - 'A' + 10) & 0x0f;
		return (c - '0') & 0x0f;
	}
	
	/**
	 * 从字节数组到十六进制字符串转换
	 * @param b
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	public static String Bytes2HexString(byte[] b) {
		byte[] buff = new byte[2 * b.length];
		for (int i = 0; i < b.length; i++) {
			buff[2 * i] = hex[(b[i] >> 4) & 0x0f];
			buff[2 * i + 1] = hex[b[i] & 0x0f];
		}
		return new String(buff);
	}

	/**
	 * 
	 * 从十六进制字符串到字节数组转换
	 * @param hexstr
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	public static byte[] HexString2Bytes(String hexstr) {
		byte[] b = new byte[hexstr.length() / 2];
		int j = 0;
		for (int i = 0; i < b.length; i++) {
			char c0 = hexstr.charAt(j++);
			char c1 = hexstr.charAt(j++);
			b[i] = (byte) ((parse(c0) << 4) | parse(c1));
		}
		return b;
	}

}
