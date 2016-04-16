package com.deloitte.tms.pl.core.commons.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TaobaoLogger {
	private static final Log clog = LogFactory.getLog("sdk.comm.err");
	private static final Log blog = LogFactory.getLog("sdk.biz.err");

	private static String osName = System.getProperties()
			.getProperty("os.name");
	private static String ip = null;
	private static boolean needEnableLogger = true;

	public static void setNeedEnableLogger(boolean needEnableLogger_) {
		needEnableLogger = needEnableLogger_;
	}

	public static String getIp() {
		if (ip == null) {
			try {
				ip = InetAddress.getLocalHost().getHostAddress();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return ip;
	}

	public static void setIp(String ipstr) {
		ip = ipstr;
	}

	public static void logCommError(Exception e, HttpURLConnection conn,
			String appKey, String method, byte[] content) {
		if (!needEnableLogger) {
			return;
		}
		String contentString = null;
		try {
			contentString = new String(content, "UTF-8");
			logCommError(e, conn, appKey, method, contentString);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	public static void logCommError(Exception e, String url, String appKey,
			String method, byte[] content) {
		if (!needEnableLogger) {
			return;
		}
		String contentString = null;
		try {
			contentString = new String(content, "UTF-8");
			logCommError(e, url, appKey, method, contentString);
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
	}

	public static void logCommError(Exception e, HttpURLConnection conn,
			String appKey, String method, Map<String, String> params) {
		if (!needEnableLogger) {
			return;
		}
		_logCommError(e, conn, null, appKey, method, params);
	}

	public static void logCommError(Exception e, String url, String appKey,
			String method, Map<String, String> params) {
		if (!needEnableLogger) {
			return;
		}
		_logCommError(e, null, url, appKey, method, params);
	}

	private static void logCommError(Exception e, HttpURLConnection conn,
			String appKey, String method, String content) {
		Map params = parseParam(content);
		_logCommError(e, conn, null, appKey, method, params);
	}

	private static void logCommError(Exception e, String url, String appKey,
			String method, String content) {
		Map params = parseParam(content);
		_logCommError(e, null, url, appKey, method, params);
	}

	private static void _logCommError(Exception e, HttpURLConnection conn,
			String url, String appKey, String method, Map<String, String> params) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		df.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		String sdkName = "top-sdk-java-20131008";
		String urlStr = null;
		String rspCode = "";
		if (conn != null) {
			try {
				urlStr = conn.getURL().toString();
				rspCode = "HTTP_ERROR_" + conn.getResponseCode();
			} catch (IOException ioe) {
			}
		} else {
			urlStr = url;
			rspCode = "";
		}
		StringBuilder sb = new StringBuilder();
		sb.append(df.format(new Date()));
		sb.append("^_^");
		sb.append(method);
		sb.append("^_^");
		sb.append(appKey);
		sb.append("^_^");
		sb.append(getIp());
		sb.append("^_^");
		sb.append(osName);
		sb.append("^_^");
		sb.append(sdkName);
		sb.append("^_^");
		sb.append(urlStr);
		sb.append("^_^");
		sb.append(rspCode);
		sb.append("^_^");
		sb.append((e.getMessage() + "").replaceAll("\r\n", " "));
		clog.error(sb.toString());
	}

	private static Map<String, String> parseParam(String contentString) {
		Map params = new HashMap();
		if ((contentString == null) || (contentString.trim().equals(""))) {
			return params;
		}
		String[] paramsArray = contentString.split("\\&");
		if (paramsArray != null) {
			for (String param : paramsArray) {
				String[] keyValue = param.split("=");
				if ((keyValue != null) && (keyValue.length == 2)) {
					params.put(keyValue[0], keyValue[1]);
				}
			}
		}
		return params;
	}

	public static void logBizError(String rsp) {
		if (!needEnableLogger) {
			return;
		}
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		df.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		StringBuilder sb = new StringBuilder();
		sb.append(df.format(new Date()));
		sb.append("^_^");
		sb.append(rsp);
		blog.error(sb.toString());
	}
}