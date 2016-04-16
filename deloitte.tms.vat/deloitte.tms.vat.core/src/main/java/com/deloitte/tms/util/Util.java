package com.deloitte.tms.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.deloitte.tms.pl.core.commons.utils.AssertHelper;

public class Util {

	
	public static Date getDateByStr(String s){
		
		if(AssertHelper.empty(s)){
			
			return null;
		}
		
		Date d = new Date();
		try{
			
		//from jquery class="easyui-datebox" will return as format MM/dd/yyyy
		SimpleDateFormat dateF = new SimpleDateFormat("MM/dd/yyyy");
		d = dateF.parse(s);
		
		return d;
		}catch(Exception e){
			e.printStackTrace();
			
			return null;
		}
		
		
	}

	public static Boolean getBooleanByObject(Object s){
		
		if(s instanceof Boolean){
			
			return (Boolean)s;
			
		}else if(s instanceof String){
			
			if(AssertHelper.empty(s)){
				return false;
			}
			
			String temps = (String)s;
			
			if(temps==null || "".endsWith(temps.trim())){
				return false;
			}
			
			if("0".equals(temps.trim())){
				return false;
			}else if("1".equals(temps.trim())){
				return true;
			}
			
			if( "false".equalsIgnoreCase(temps.trim() )   ){
				return false;
			}else{
				return true;
			}
			
		}else if(s instanceof Integer){
			
			Integer tempi = (Integer)s;
			
			if(0==tempi){
				return false;
			}else{
				return true;
			}
		}
		
		return false;
	}
}
