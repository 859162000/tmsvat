package com.deloitte.tms.vat.core.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.deloitte.tms.pl.core.commons.utils.DateUtils;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class JsonDateValueProcessor implements JsonValueProcessor {

	 private String format =DateUtils.ISO_DATETIME_FORMAT2;  
	       
	     public JsonDateValueProcessor() {  
	         super();  
	     }  
	       
	     public JsonDateValueProcessor(String format) {  
	         super();  
	         this.format = format;  
	     }  
	   
	     @Override  
	     public Object processArrayValue(Object paramObject,  
	             JsonConfig paramJsonConfig) {  
	         return process(paramObject);  
	     }  
	   
	     @Override  
	     public Object processObjectValue(String paramString, Object paramObject,  
	             JsonConfig paramJsonConfig) {  
	         return process(paramObject);  
	     }  
	       
	       
	     private Object process(Object value){  
	         if(value instanceof Date){    
	             SimpleDateFormat sdf = new SimpleDateFormat(format,Locale.CHINA);    
	             return sdf.format(value);  
	         }    
	         return value == null ? "" : value.toString();    
	     }  


}
