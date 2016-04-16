package com.deloitte.tms.pl.core.commons.utils;

import java.math.BigDecimal;
import java.util.Map;

public class ConverterUtils {

	public static String getString(Map<String, Object> parameters,
			String propertyName) {
		return Converter.to(String.class).convert(parameters, propertyName);
	}

	public static Long getLong(Map<String, Object> parameters,
			String propertyName) {
		return Converter.to(Long.class).convert(parameters, propertyName);
	}
	public static Integer getInter(Map<String, Object> parameters,
			String propertyName) {
		return Converter.to(Integer.class).convert(parameters, propertyName);
	}
	public static Integer getInter(String value) {
		return Converter.to(Integer.class).convert(value);
	}
	public static Boolean getBoolean(String value) {
		return Converter.to(Boolean.class).convert(value);
	}
	public static String getString(byte[] content) {
		if(content==null){
			return null;
		}
		return new String(content);
	}
	
	public static byte[] getByte(String content) {
		return content.getBytes();
	}
	public static String[] stringTostrings(String ids)
	{
		if(ids!=null)
		{
			String[] pidStrings=ids.split(",");
			String[] pidlist=new String[pidStrings.length];
			int i=0;
			for(String str:pidStrings)
			{
				pidlist[i]=str.trim();
				i++;
			}
			return pidlist;
		}
		return null;
	}
	public static Long[] stringToLongs(String ids)
	{
		if(ids!=null)
		{
			String[] pidStrings=ids.split(",");
			Long[] pidlist=new Long[pidStrings.length];
			int i=0;
			for(String str:pidStrings)
			{
				pidlist[i]=Long.parseLong(str);
				i++;
			}
			return pidlist;
		}
		return null;
	}
	public static Integer[] stringToIntegers(String ids)
	{
		if(ids!=null)
		{
			String[] pidStrings=ids.split(",");
			Integer[] pidlist=new Integer[pidStrings.length];
			int i=0;
			for(String str:pidStrings)
			{
				str=str.trim();
				pidlist[i]=Integer.parseInt(str);
				i++;
			}
			return pidlist;
		}
		return null;
	}
	public static double round(double v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b = new BigDecimal(Double.toString(v));

		BigDecimal one = new BigDecimal("1");

		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();

	}
}
