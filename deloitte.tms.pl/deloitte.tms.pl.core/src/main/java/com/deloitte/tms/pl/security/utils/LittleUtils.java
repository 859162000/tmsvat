package com.deloitte.tms.pl.security.utils;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

public class LittleUtils {

	public static String one="1";
	public static String zero="0";
	
	public static Integer pageSize=10;
	public static Integer pageNumber=1;
	
	public static HashMap<String, String> ynMap = new HashMap<String, String>();
	static {
		ynMap.put("0", "否");
		ynMap.put("1", "是");
	}
	
	public static boolean strEmpty(String str){
		
		if(str==null || ""==str.trim()){
			return true;
		}else {
			return false;
		}
	}
	
	public static boolean isDB2(){
		
		Properties properties=null;
		String DBType="";

		try {
		/*	InputStream in = FTPUtil.class.getClassLoader()
					.getResourceAsStream("fscsap.properties");*/
			InputStream in = null ;
			//jdbc.driverClassName=oracle.jdbc.driver.OracleDriver
			in =LittleUtils.class.getResourceAsStream("/config/jdbc.properties");
					
			if (in == null) {
				System.out.print("/config/jdbc.properties");
			} else {
				
				properties = new Properties();
				properties.load(in);
				DBType = properties.getProperty("jdbc.driverClassName");
				if(DBType.contains("oracle")){
					return false;
				}else{
					
					return true;
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return true;
		}
		return true;
	
			}
}
