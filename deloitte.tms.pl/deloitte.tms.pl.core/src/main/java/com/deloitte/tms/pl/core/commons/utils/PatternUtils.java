package com.deloitte.tms.pl.core.commons.utils;

import java.io.UnsupportedEncodingException;
import java.util.regex.Pattern;

/**
 * 正则表达式验证工具类
 * @author gjx
 *
 */
public class PatternUtils {
	
    /**
     * 验证是否为数字
     * @author gjx
     * @param str
     * @return
     */
    public static boolean isNumber(String str) {
        return Pattern.matches("^\\d+$", str);
    }
    
    /**
     * 验证代码（可用字符:A-Z,a-z,0-9,()-_）
     * @param str
     * @return
     */
    public static boolean isCode(String str){
    	return Pattern.matches("^[A-z][A-z0-9_\\-()]*$", str);
    }
    
    /**
     * 代码规范
     * @return
     */
    public static String getCodePattern(){
    	return "代码规范：以英文字母开头!可用字符:A-Z,a-z,0-9,()-_";
    }
    
    /**
     * 判断字节是否合法
     * @param str
     * @param maxLength
     * @return
     */
    public static Boolean validateBytes(String str,String charsetName,int maxLength){
    	int a = 0;
		try {
			a = str.getBytes(charsetName).length;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return a <= maxLength;
    }
    
    /**
     * 验证字符串是否可以存入数据库
     * @param str
     * @param maxLength
     * @return
     */
    public static Boolean validateBytesUTF8(String str,int maxLength){
    	return validateBytes(str, "UTF8", maxLength);
    }
}
