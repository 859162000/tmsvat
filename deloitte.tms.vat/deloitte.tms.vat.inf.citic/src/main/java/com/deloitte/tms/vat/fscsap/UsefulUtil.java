package com.deloitte.tms.vat.fscsap;

import java.util.Date;

import com.deloitte.tms.pl.core.commons.utils.AssertHelper;

public class UsefulUtil {

	public static String extraSep="\\^"; //对^这种特殊分隔符的split要用这种写法
	
	

	
public static Date getDate8ByStr(String tempD){//yyyymmdd
		
		Date d = new Date();
		int ny = Integer.parseInt(tempD.subSequence(0, 4).toString());
		int nm = Integer.parseInt(tempD.subSequence(4, 6).toString());
		int nd = Integer.parseInt(tempD.subSequence(6, 8).toString());
		
		d.setYear(ny-1900);
		d.setMonth(nm-1);
		d.setDate(nd);
		
		d.setHours(0);
		d.setMinutes(0);
		d.setSeconds(0);
		
		return d;
	}
	
	public static String getDay8StrByDate(Date d){
		
		if(d==null){
			return "";
		}
		
		int ny=d.getYear()+1900;
		String nyStr=String.valueOf(ny);
		int nm=d.getMonth()+1;
		String nmStr= nm >9 ? String.valueOf(nm)  : "0"+String.valueOf(nm) ;
		
		int nd=d.getDate();
		String ndStr= nm >9 ? String.valueOf(nd)  : "0"+String.valueOf(nd) ;
		
		
		return nyStr+nmStr+ndStr;
	}
	
public  static String getDay8StrBySt(String s){
		//Tue Mar 29 00:00:00 CST 2016
		if(s==null || ""==s.trim()){
			return "";
		}
		Date d=null;
		
		try{
		 d = new Date(s);
		 int ny=d.getYear()+1900;
			String nyStr=String.valueOf(ny);
			int nm=d.getMonth()+1;
			String nmStr= nm >9 ? String.valueOf(nm)  : "0"+String.valueOf(nm) ;
			
			int nd=d.getDate();
			String ndStr= nm >9 ? String.valueOf(nd)  : "0"+String.valueOf(nd) ;
			
			
			return nyStr+nmStr+ndStr;
		}catch(Exception e){
			System.out.println("can't parse str to date");
			e.printStackTrace();
			//todo using str to split
		}
		
		return "";
	}


	public static Object getNotNull(Object o){
	
		if(AssertHelper.empty(o)){
			return "";
		}
		
		return o;
	}
}
