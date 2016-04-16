/**    
 * Copyright (C),Deloitte
 * @FileName: CharUtils.java  
 * @Package: com.deloitte.tms.vat.salesinvoice.jobs.socket  
 * @Description: //模块目的、功能描述  
 * @Author stonshi  
 * @Date 2016年3月24日 下午2:00:35  
 * @History: //修改记录
 * 〈author〉      〈time〉      〈version〉       〈desc〉
 * 修改人姓名            修改时间            版本号              描述   
 */

package com.deloitte.tms.vat.salesinvoice.jobs.socket;

import java.util.ArrayList;
import java.util.List;

/**
 * 〈一句话功能简述〉 功能详细描述
 * 
 * @author stonshi
 * @create 2016年3月24日 下午2:00:35
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */

public class CharUtils {

	/**
	 * 将String转化为16进制字符串,以List形式返回
	 * 
	 * @param srcStr
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	public static List<String> convertStringToHex(String srcStr) {
		char[] strChar = srcStr.toCharArray();
		String s = null;
		List<String> list = new ArrayList<String>();
		for (char c : strChar) {
			s = Integer.toHexString(c) + " ";
			list.add(s);
		}
		return list;
	}

	/**
	 * 
	 *〈一句话功能简述〉 
	 * 功能详细描述
	 * @param str
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	public static String padHeaderLeft(String str){
		String pad="010000000000";
		String s = pad.substring(0,10-str.length())+str;
		StringBuffer sb = new StringBuffer(s);
		sb.insert(2," ");
		sb.insert(5," ");
		sb.insert(8," ");
		sb.insert(11," ");
        return sb.toString();
    }
	
	public static String convertHexToString(String srcStr) {
		StringBuffer sb = new StringBuffer(srcStr);
		String s = sb.substring(1, 4);
		List<String> list = new ArrayList<String>();
		char[] c = s.toCharArray();
		
		//Character cc = new Character((char) 111);
		
		return null;
	}

}
