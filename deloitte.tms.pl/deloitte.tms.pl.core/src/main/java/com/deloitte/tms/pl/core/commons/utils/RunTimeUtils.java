package com.deloitte.tms.pl.core.commons.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RunTimeUtils {
	public static void main(String[] args) {
		  browser("http://csdn.net");
		  openFile("notepad");
		  openExeFile("D:/Program Files/IETester/IETester.exe");
		  openFile("C:/abc.doc");
		  openUseNotepad("C:/step.png");
		  openUseWinWord("C:/在相册平台上增加“公开设置”功能.doc");
		  openBat("cmd /c ping ht.iweiju.com");
		  openBat("d:/jboss5/bin/shutdown.bat -S");
		 }
	/**
	 * 执行bat 批处理文件
	 * 
	 * @param batFile
	 */
	public static void openBat(String batFile) {
		Process p = execProcess(batFile);
		if (p == null) {
			return;
		}
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line;
			while ((line = br.readLine()) != null) {
				System.out.println(line); // 输出测试
			}
			p.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 使用记事本打开文件
	 * 
	 * @param path
	 */
	public static void openUseNotepad(String path) {
		exec("notepad " + path);
	}

	/**
	 * 使用word打开
	 * 
	 * @param path
	 */
	public static void openUseWinWord(String path) {
		exec("C:\\Program Files\\Microsoft Office\\OFFICE11\\winword.exe "
				+ path);
	}

	/**
	 * 打开可执行文件
	 * 
	 * @param path
	 */
	public static void openExeFile(String path) {
		exec(path);
	}

	/**
	 * 打开任意文件
	 * 
	 * @param path
	 */
	public static void openFile(String path) {
		exec("cmd /C start " + path);
	}

	/**
	 * 打开浏览器，并打开输入的地址
	 * 
	 * @param url
	 */
	public static void browser(String url) {
		openFile("rundll32 url.dll,FileProtocolHandler " + url);
	}

	private static InputStream exec(String command) {
		Process p = execProcess(command);
		if (p == null) {
			return null;
		}
		return p.getInputStream();
	}

	private static Process execProcess(String command) {
		try {
			return Runtime.getRuntime().exec(command);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
