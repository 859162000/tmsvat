package com.deloitte.tms.pl.core.commons.utils;

import com.deloitte.tms.pl.core.commons.exception.BusinessException;


/**
 * Exception helper
 * 
 * @author lx
 * 
 */
public class ExceptionHelper {
	public static void throwRuntimeException() {
		throw new RuntimeException();
	}

	public static void throwRuntimeException(String errorMsg) {
		throw new RuntimeException(errorMsg);
	}
	
	public static void throwBusinessException(String errorMsg) {
		throw new BusinessException(errorMsg);
	}

}
