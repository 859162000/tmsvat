package com.deloitte.tms.pl.security.utils;

public class LittleUtils {

	public static String one="1";
	public static String zero="0";
	
	public static Integer pageSize=10;
	public static Integer pageNumber=1;
	
	public static boolean strEmpty(String str){
		
		if(str==null || ""==str.trim()){
			return true;
		}else {
			return false;
		}
	}
}
