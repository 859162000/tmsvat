package com.deloitte.tms.vat.core.common;

import java.util.Locale;
import com.deloitte.tms.pl.core.commons.utils.SpringUtil;









public class MessageHelp {
	
	
	public static String getMessage(String messageKey,Locale locale){
		String messageString;
		messageString = SpringUtil.getContext().getMessage(messageKey, null, locale);
		return messageString;
	}
	
	public static String getMessage(String messageKey,Object[] objs,Locale locale){
		String messageString;
		messageString = SpringUtil.getContext().getMessage(messageKey, objs, locale);
		return messageString;
	}
	
	

}
