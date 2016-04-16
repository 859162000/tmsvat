package com.deloitte.tms.pl.core.commons.springmvc.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

//省略import
public class StringToDateConverter implements Converter<String, Date> {
	private SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
	@Override
	public Date convert(String source) {		
		if(!StringUtils.hasLength(source)) {
			//①如果source为空 返回null
			return null;
		}
		if (StringUtils.hasText(source)) {  
            try {  
                if (source.indexOf(":") == -1 && source.length() == 10) {  
                    return this.dateFormat.parse(source);  
                } else if (source.indexOf(":") > 0 && source.length() == 19) {  
                	return this.datetimeFormat.parse(source);  
                } else if (source.length() == 13) {  
                	return new Date(Long.parseLong(source));  
                }else{  
                    throw new IllegalArgumentException("Could not parse date, date format is error ");  
                }  
            } catch (ParseException ex) {  
                IllegalArgumentException iae = new IllegalArgumentException("Could not parse date: " + ex.getMessage());  
                iae.initCause(ex);  
                throw iae;  
            }  
        } else {  
        	return null;  
        }  
	}
}
