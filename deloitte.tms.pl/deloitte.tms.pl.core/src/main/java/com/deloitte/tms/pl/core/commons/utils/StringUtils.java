package com.deloitte.tms.pl.core.commons.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public final class StringUtils
{
  private static final String STANDARD_CHARSET = "ISO8859-1";

  public static String translateFromISO(String s, String charsetName)
    throws UnsupportedEncodingException
  {
    if (s != null) {
      return new String(s.getBytes("ISO8859-1"), charsetName);
    }

    return null;
  }
  /**
	 * 将字符串转换为Long型
	 * 
	 * @param str
	 * @return
	 */
	public static Long convertToLong(String str) {
		if (isLong(str)) {
			return Long.valueOf(str);
		} else {
			return null;
		}
	}
	/**
	 * 判断字符串是否是一个long型的字符串
	 * 
	 * @return
	 */
	public static boolean isLong(String str) {
		boolean retval = true;
		try {
			Long.valueOf(str);
		} catch (Exception e) {
			retval = false;
		}
		return retval;
	}
  public static String translate(String s, String originCharsetName, String charsetName)
    throws UnsupportedEncodingException
  {
    if (!originCharsetName.equals(charsetName)) {
      if (s != null) {
        return new String(s.getBytes(originCharsetName), charsetName);
      }

      return null;
    }

    return s;
  }

  public static boolean isNotEmpty(String s)
  {
    return (s != null) && (s.length() > 0);
  }

  public static String validate(String s)
  {
    if (s == null) {
      return "";
    }

    return s;
  }

  public static boolean isValueLegal(String s, String invalidChar)
  {
    int len = invalidChar.length();
    for (int i = 0; i < len; i++) {
      if (!isValueLegal(s, invalidChar.charAt(i))) {
        return false;
      }
    }
    return true;
  }

  private static boolean isValueLegal(String s, char invalidChar) {
    int len = s.length();
    for (int i = 0; i < len; i++) {
      if (s.charAt(i) == invalidChar) {
        return false;
      }
    }
    return true;
  }

  public static String toValidString(Object o)
  {
    if (o == null) {
      return "";
    }

    return o.toString();
  }
	public static String toFirstLetterUpperCase(String str) {
		if (str == null || str.length() < 2) {
			return str;
		}
		String firstLetter = str.substring(0, 1).toUpperCase();
		return firstLetter + str.substring(1, str.length());
	}
	public static String toFirstLetterLowerCase(String str) {
		if (str == null || str.length() < 2) {
			return str;
		}
		String firstLetter = str.substring(0, 1).toLowerCase();
		return firstLetter + str.substring(1, str.length());
	}
	/**
	 * 判断字符串是否不为空，若是返回false
	 * 
	 * @param string
	 * @return
	 */
	public static boolean isValid(String string) {
		return !(string == null || string.equals(""));
	}

	/**
	 * 字符0常量数组
	 */
	private static String[] ZEROS = { "", "0", "00", "000", "0000", "00000", "000000", "0000000", "00000000", "000000000", "0000000000", "00000000000", "000000000000", "0000000000000",
			"00000000000000", "000000000000000", "0000000000000000", "00000000000000000", "0000000000000000" };

	/**
	 * 返回切割后最后的字符串
	 * 
	 * @param str
	 * @param split
	 * @return
	 */
	public static String splitLast(String str, String split) {
		String strs[] = str.split("[" + split + "]");
		return strs[strs.length - 1];
	}

	/**
	 * 返回切割后最前的字符串
	 * 
	 * @param str
	 * @param split
	 * @return
	 */
	public static String splitFirst(String str, String split) {
		String strs[] = str.split("[" + split + "]");
		return strs[0];
	}

	/**
	 * 删除最后分割号后的内容(含分割号)
	 * 
	 * @param str
	 * @param split
	 * @return
	 */
	public static String deleteLast(String str, String split) {
		String strs[] = str.split("[" + split + "]");
		String result = "";
		for (int i = 0; i < strs.length - 1; i++) {
			if (i == strs.length - 2) {
				result = result + strs[i];
			} else {
				result = result + strs[i] + split;
			}
		}
		return result;
	}

	/**
	 * String 首字母小写
	 * 
	 * @param str
	 * @return
	 */
	public static String firstLowerCase(String str) {
		String firstChar = str.substring(0, 1);
		firstChar = firstChar.toLowerCase();
		String result = firstChar + str.substring(1, str.length());
		return result;
	}

	/**
	 * String转换为Long
	 * 
	 * @param object
	 * @return Long型
	 */
	public static Long getLong(Object object) {
		if (object != null) {
			String str = getString(object);
			if (str.trim().equals("")) {
				return null;
			}
			return Long.valueOf(str);
		}
		return null;
	}

	/**
	 * String转换为Double
	 * 
	 * @param object
	 * @return Double型
	 */
	public static Double getDouble(Object object) {
		if (object != null) {
			String str = getString(object);
			if (str.trim().equals("")) {
				return null;
			}
			return Double.valueOf((String) object);
		}
		return null;
	}

	/**
	 * String转换为String
	 * 
	 * @param object
	 * @return String型
	 */
	public static String getString(Object object) {
		if (object != null) {
			return String.valueOf(object);
		} else {
			return "";
		}
	}

	/**
	 * String转换为Integer
	 * 
	 * @param object
	 * @return Integer型
	 */
	public static Integer getInteger(Object object) {
		if (object != null) {
			String str = getString(object);
			if (str.trim().equals(str)) {
				return null;
			}
			return Integer.valueOf((String) object);
		}
		return null;
	}

	/**
	 * 转换object为String类型
	 * 
	 * @param object
	 * @return
	 */
	public static String convertToString(Object object) {
		if (object instanceof String) {
			return (String) object;
		} else {
			return null;
		}
	}


	public static boolean isDouble(String str) {
		boolean retval = true;
		try {
			Double.valueOf(str);
		} catch (Exception e) {
			retval = false;
		}
		return retval;
	}

	public static boolean isBoolean(String str) {
		boolean retval = true;
		if (str == null || (!str.equalsIgnoreCase("true") && !str.equalsIgnoreCase("false"))) {
			retval = false;
		}
		return retval;
	}


	/**
	 * String 类型转换 具体约定见ParameterSet.genSingles()
	 * 
	 * @see ParameterSet
	 * @param str
	 * @return
	 */
	public static Object convert(String str) {
		if (isLong(str)) {
			return Long.valueOf(str);
		}
		if (isDouble(str)) {
			return Double.valueOf(str);
		}
		if (isBoolean(str)) {
			return Boolean.valueOf(str);
		}
		return str;
	}

	public static String getSpace(int num) {
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < num; i++) {
			stringBuffer.append(" ");
		}
		return stringBuffer.toString();
	}

	public static String remove(String tgtStr, String removeStr) {
		return tgtStr.replaceFirst(removeStr, "");
	}

	/**
	 * check srcStr is or not contain tgtStr
	 * 
	 * @param srcStr
	 * @param tgtStr
	 * @return
	 */
	public static boolean isBeginWith(String srcStr, String tgtStr) {
		int index = srcStr.indexOf(tgtStr);
		return index == 0 ? true : false;
	}

	/**
	 * 
	 * 字符串补零函数
	 * 
	 * @param s
	 * @param length
	 * @return
	 * @author xingchang.zhang
	 * 
	 */
	public static String toPaddedString(String s, int length) {
		return ZEROS[length - s.length()] + s;
	}
	
	/**
	 * 去空格,null则返回null，非空返回左右去空格后的值
	 * 
	 * @param val
	 * @return
	 * 
	 * @author kui.jiang
	 */
	public static String trim(String val) {
		return val == null ? null : val.trim();
	}
	
	/**
	 * 判断字符串是否在数组中
	 * 
	 * @param val
	 * @param collection
	 * @return
	 * 
	 * @author kui.jiang
	 */
	public static boolean isInCollection(String val, Collection collection) {
		boolean retval = false;
		if (isEmpty(val) || collection.isEmpty()) {
			return retval;
		}
		Iterator iterator = collection.iterator();
		while(iterator.hasNext()) {
			if (val.equals(iterator.next())) {
				retval = true;
				break;
			}
		}
		return retval;
	}

	/**
	 * 判断字符串是否在数组中
	 * 
	 * @param val
	 * @param array
	 * @return
	 * 
	 * @author kui.jiang
	 */
	public static boolean isInCollection(String val, String[] array) {
		boolean retval = false;
		if (isEmpty(val) || array == null) {
			return retval;
		}
		for (int i = 0; i < array.length; i ++) {
			if (val.equals(array[i])) {
				retval = true;
				break;
			}
		}
		
		return retval;
	}
	
	public static Date parseDate(String s) {
	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	    Date date = null;
	    try {
			date = format.parse(s);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	    return date;
	}
//protected static final Logger logger = LoggerFactory.getLogger(StringUtils.class);
	
	private final static String[] hex = {
		"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "0A", "0B", "0C", "0D", "0E", "0F", 
		"10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "1A", "1B", "1C", "1D", "1E", "1F", 
		"20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "2A", "2B", "2C", "2D", "2E", "2F", 
		"30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "3A", "3B", "3C", "3D", "3E", "3F", 
		"40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "4A", "4B", "4C", "4D", "4E", "4F", 
		"50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "5A", "5B", "5C", "5D", "5E", "5F", 
		"60", "61", "62", "63", "64", "65", "66", "67", "68", "69", "6A", "6B", "6C", "6D", "6E", "6F", 
		"70", "71", "72", "73", "74", "75", "76", "77", "78", "79", "7A", "7B", "7C", "7D", "7E", "7F", 
		"80", "81", "82", "83", "84", "85", "86", "87", "88", "89", "8A", "8B", "8C", "8D", "8E", "8F", 
		"90", "91", "92", "93", "94", "95", "96", "97", "98", "99", "9A", "9B", "9C", "9D", "9E", "9F", 
		"A0", "A1", "A2", "A3", "A4", "A5", "A6", "A7", "A8", "A9", "AA", "AB", "AC", "AD", "AE", "AF", 
		"B0", "B1", "B2", "B3", "B4", "B5", "B6", "B7", "B8", "B9", "BA", "BB", "BC", "BD", "BE", "BF", 
		"C0", "C1", "C2", "C3", "C4", "C5", "C6", "C7", "C8", "C9", "CA", "CB", "CC", "CD", "CE", "CF", 
		"D0", "D1", "D2", "D3", "D4", "D5", "D6", "D7", "D8", "D9", "DA", "DB", "DC", "DD", "DE", "DF", 
		"E0", "E1", "E2", "E3", "E4", "E5", "E6", "E7", "E8", "E9", "EA", "EB", "EC", "ED", "EE", "EF", 
		"F0", "F1", "F2", "F3", "F4", "F5", "F6", "F7", "F8", "F9", "FA", "FB", "FC", "FD", "FE", "FF"
	};
	
	private final static byte[] val = {
		0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 
		0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 
		0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 
		0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 
		0x3F, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E, 0x0F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 
		0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 
		0x3F, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E, 0x0F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 
		0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 
		0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 
		0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 
		0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 
		0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 
		0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 
		0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 
		0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 
		0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F
	};
	
	/**
	 * 判断两个字符串是否相同
	 * 
	 * @param str
	 * @param another
	 * @return
	 */
	public static boolean equals(String str, String another) {
		if (str == another || (null != str && str.equals(another))) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 判断字符串是否为空
	 * 
	 * @param source
	 * @return
	 */
	public static boolean empty(String str) {
		if (null == str || str.trim().length() <= 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 判断字符串是否为空<br>
	 * 如果字符串不为空返回原字符串，如果字符串为空则返回默认值
	 * 
	 * @param str
	 * @param def
	 * @return
	 */
	public static String empty(String str, String def) {
		return empty(str) ? def : str;
	}
	
	/**
	 * 大写头文字
	 * 
	 * @param str
	 */
	public static String upperCaseInitial(String str) {
		if (empty(str)) {
			return "";
		} else {
			char chars[] = str.toCharArray();
			if (chars[0] >= 'a' || chars[0] <= 'z') {
				chars[0] -= 32;
			}
			return String.valueOf(chars);
		}
	}
	
	/**
	 * 分割字符串
	 * 
	 * @param str
	 * @param regex
	 * @return
	 */
	public static List<String> split(String str, String regex) {
		List<String> strList = new ArrayList<String>();
		if (!empty(str)) {
			String strs[] = str.split(regex);
			
			for (String s: strs) {
				strList.add(s);
			}
		}
		return strList;
	}
	
	
	/**
	 * 进行 MD5 加密
	 * 
	 * @param str
	 * @return
	 */
	public static String md5(String str) {
		try {
			StringBuffer md5 = new StringBuffer(32);
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte bytes[] = md.digest(str.getBytes("utf-8"));
			
			for (int i = 0; i < bytes.length; i++) {
				md5.append(Integer.toHexString((bytes[i] & 0xFF) | 0x100).substring(1, 3));
			}
			return md5.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * UUID
	 * 
	 * @return
	 */
	public static String uuid() {
		return UUID.randomUUID().toString();
	}
	
	/**
	 * UUID
	 * 
	 * @param prefix
	 * @return
	 */
	public static String uuid(String prefix) {
		StringBuffer uuid = new StringBuffer(50);
		uuid.append(empty(prefix) ? "" : prefix + ":").append(UUID.randomUUID().toString());
		return uuid.toString();
	}
	
	/**
	 * 字符串数组
	 * 
	 * @param strs
	 * @return
	 */
	public static String[] array(String... strs) {
		return strs;
	}
	
	/**
	 * 字符串列表
	 * 
	 * @param strs
	 * @return
	 */
	public static List<String> list(String... strs) {
		List<String> strList = new ArrayList<String>();
		for (String s: strs) {
			strList.add(s);
		}
		return strList;
	}
	
	/**
	 * 生成编号
	 * 
	 * @return
	 */
	public static String id() {
		return StringUtils.md5(StringUtils.uuid());
	}
	
	/**
	 * 生成随机字符串<br>
	 * 类型一：全小写字母，
	 * 类型二：全大写字母，
	 * 类型三：数字，
	 * 类型四：符号
	 * 
	 * @param length
	 * @param types
	 * @return
	 */
	public static String random(int length, int... types) {
		String[] symbols = array("abcdefghijklmnopqrstuvwxyz", "ABCDEFGHIJKLMNOPQRSTUVWXYZ", "1234567890", "!@#$%_-+=");
		StringBuffer chars = new StringBuffer(26);
		StringBuffer result = new StringBuffer(length);
		Random random = new Random(System.currentTimeMillis());
		
		if (types.length <= 0) {
			chars.append(symbols[0]).append(symbols[2]);
		} else {
			for (int type: types) {
				type = (type < 0) ? 0 : (type > 3) ? 3 : type;
				chars.append(symbols[type]);
			}
		}
		
		int max = chars.length() - 1;
		for (int i = 0; i < length; i++) {
			result.append(chars.charAt(random.nextInt(max)));
		}
		return result.toString();
	}
	
	/**
	 * 输出字符串<br>
	 * 如果字符串为空则输出默认值
	 * 
	 * @param source
	 * @param def
	 * @return
	 */
	public static String out(String source, String def) {
		return (empty(source)) ? def : source;
	}
	
	/**
	 * 判断两个对象是否相同
	 * 
	 * @param obj
	 * @param anthor
	 * @return
	 */
	public static boolean equals(Object obj, Object another) {
		
		if (obj == another) {
			return true;
		} else if (null != obj && obj.equals(another)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 加密字符串
	 * 
	 * @param source
	 * @return
	 */
	public static String escape(String source) {
		
		StringBuffer sb = new StringBuffer();
		int len = source.length();
		
		for (int i = 0; i < len; i++) {
			int ch = source.charAt(i);
			
			if (ch == ' ') {
				sb.append('+');
			} else if ('A' <= ch && ch <= 'Z') {
				sb.append((char)ch);
			} else if ('a' <= ch && ch <= 'z') {
				sb.append((char)ch);
			} else if ('0' <= ch && ch <= '9') {
				sb.append((char)ch);
			} else if (ch == '-' || ch == '_' || ch == '.' || ch == '!' || ch == '~' || ch == '*' || ch == '\'' || ch == '(' || ch == ')') {
				sb.append((char)ch);
			} else if (ch <= 0x007F) {
				sb.append('%');
				sb.append(hex[ch]);
			} else {
				sb.append('%');
				sb.append('u');
				sb.append(hex[(ch >>> 8)]);
				sb.append(hex[(0x00FF & ch)]);
			}
		}
		
		return sb.toString();
	}
	
	/**
	 * 解析字符串
	 * 
	 * @param source
	 * @return
	 */
	public static String unescape(String source) {
		
		/* 初始化 */
		StringBuffer sb = new StringBuffer();
		int i = 0;
		int len = source.length();
		
		/* 循环处理字符串 */
		while (i < len) {
			
			/* 初始化 */
			int ch = source.charAt(i);
			
			if (ch == '+') {
				sb.append(' ');
			} else if ('A' <= ch && ch <= 'Z') {
				sb.append((char)ch);
			} else if ('a' <= ch && ch <= 'z') {
				sb.append((char)ch);
			} else if ('0' <= ch && ch <= '9') {
				sb.append((char)ch);
			} else if (ch == '-' || ch == '_' || ch == '.' || ch == '!' || ch == '~' || ch == '*' || ch == '\'' || ch == '(' || ch == ')') {
				sb.append((char)ch);
			} else if (ch == '%') {
				int cint = 0;
				
				if ('u' != source.charAt(i + 1)) {
					cint = (cint << 4) | val[source.charAt(i + 1)];
					cint = (cint << 4) | val[source.charAt(i + 2)];
					i += 2;
				} else {
					cint = (cint << 4) | val[source.charAt(i + 2)];
					cint = (cint << 4) | val[source.charAt(i + 3)];
					cint = (cint << 4) | val[source.charAt(i + 4)];
					cint = (cint << 4) | val[source.charAt(i + 5)];
					i += 5;
				}
				
				sb.append((char)cint);
			}
			
			i++;
		}
		
		return sb.toString();
	}
	
	/**
	 * 解析字符串<br>
	 * 将字符串中的'{}'符号替换成具体内容
	 * 
	 * @param str
	 * @param params
	 * @return
	 */
	public static String parseString(String str, String... params) {
		
		/* 初始化 */
		int i = 0;
		int length = params.length;
		
		/* 循环处理字符串 */
		while (str.indexOf("{}") > -1 && i < length) {
			str = str.replaceFirst("\\{}", String.valueOf(params[i]));
			i++;
		}
		
		/* 返回结果 */
		return str;
	}
	
	/**
	 * 生成主键<br>
	 * UUID.hex
	 * 
	 * @return
	 */
	public static String genId() {
		return md5(UUID.randomUUID().toString());
	}
	
	/**
	 * 
	 * @Title getSplitStr 
	 * @Description TODO将字符串分割并以字符串方式返回
	 * @param sourceStr源字符串
	 * @param splitStr分割符
	 * @return  String
	 */
	public static String getSplitStr(String sourceStr,String splitStr){
		
		List<String> strList=split(sourceStr, splitStr);
		String strBack="";
		for (String strTemp : strList) {
			
			strBack=strBack+strTemp;
			
		}
		return strBack;
	}
	
	/**
	 * 
	 * @Title converNullStr 
	 * @Description TODO 将空的字符转换为double类型，为空则默认给0
	 * @param strConvert
	 * @return  double
	 */
	public static double converNullStr(String strConvert){
		double dbRpt = 0;
		if (!empty(strConvert)) {
			dbRpt=Double.valueOf(strConvert);
		}
		return dbRpt;
		
	}
	
	/**
	 * 
	 * @Title trimOrBckZero 
	 * @Description TODO用于将空字符串清空空格，若为null，则转换为0的字条串
	 * @param str
	 * @return  String
	 */
	public static String trimOrBckZero(String str){
		if (str==null) {
			return "0";
					
		}else {
			return str.trim();
		}
		
	}
	
	/**
	 * 合并字符串列表
	 * 
	 * @param strs
	 * @param symbol
	 * @return
	 */
	public static String implode(Collection<String> strs, String symbol) {
		
		/* 初始化 */
		StringBuffer sb = new StringBuffer(200);
		
		/* 循环组装字符串 */
		for (String s: strs) {
			sb.append(symbol).append(s);
		}
		
		/* 返回结果 */
		if (sb.length() > 0) {
			return sb.toString().substring(symbol.length());
		}
		return null;
	}
	/**
	 * 检查指定的字符串是否为空。
	 * <ul>
	 * <li>SysUtils.isEmpty(null) = true</li>
	 * <li>SysUtils.isEmpty("") = true</li>
	 * <li>SysUtils.isEmpty("   ") = true</li>
	 * <li>SysUtils.isEmpty("abc") = false</li>
	 * </ul>
	 * 
	 * @param value 待检查的字符串
	 * @return true/false
	 */
	public static boolean isEmpty(String value) {
		int strLen;
		if (value == null || (strLen = value.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if ((Character.isWhitespace(value.charAt(i)) == false)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 检查指定的字符串列表是否不为空。
	 * 
	 * @param values 字符串列表
	 * @return true/false
	 */
	public static boolean areNotEmpty(String... values) {
		boolean result = true;
		if (values == null || values.length == 0) {
			result = false;
		} else {
			for (String value : values) {
				result &= !isEmpty(value);
			}
		}
		return result;
	}
	public static String join(String left,String right,String split){
		if(AssertHelper.empty(left)){
			return right;
		}else {
			if(AssertHelper.empty(right)){
				return left;
			}
			return left+split+right;
		}
	}
	/**
     * 半角转全角
     * @param input String.
     * @return 全角字符串.
     */
    public static String ToSBC(String input) {
             char c[] = input.toCharArray();
             for (int i = 0; i < c.length; i++) {
               if (c[i] == ' ') {
                 c[i] = '\u3000';
               } else if (c[i] < '\177') {
                 c[i] = (char) (c[i] + 65248);

               }
             }
             return new String(c);
    }

    /**
     * 全角转半角
     * @param input String.
     * @return 半角字符串
     */
    public static String ToDBC(String input) {
        

             char c[] = input.toCharArray();
             for (int i = 0; i < c.length; i++) {
               if (c[i] == '\u3000') {
                 c[i] = ' ';
               } else if (c[i] > '\uFF00' && c[i] < '\uFF5F') {
                 c[i] = (char) (c[i] - 65248);

               }
             }
        String returnString = new String(c);
        
             return returnString;
    }
    /**
	 * 查找content中第index(0,1,2)个str的开始位置
	 * @param content
	 * @param str
	 * @param index
	 * @return
	 */
	private static int searchStrWithIndex(String content,String str,int index){
		String emp=content;
		int tdpos=emp.indexOf(str);
		int searchindex=0;
		int result=-1;
		while (tdpos>=0) {	
			result=result<0?tdpos:result+tdpos;
			if(searchindex==index){
				break;
			}
			emp=emp.substring(tdpos+str.length());
			tdpos=emp.indexOf(str);
			searchindex++;	
			
		}
		return result>0?result+searchindex*str.length():result;
	}
	/**
	 * 查找并替换content中第index(0,1,2)个str的开始字符串
	 * @param content
	 * @param str
	 * @param index
	 * @param replace
	 * @return
	 */
	private static String replaceStrWithIndex(String content,String str,int index,String replace){
		int pos=searchStrWithIndex(content, str, index);
		if(pos>-1){
			String front=content.substring(0, pos);
			String behind=content.substring(pos,content.length());
			behind=behind.replaceFirst(str, replace);
			content= front+behind;
		}
		return content;
	}
}