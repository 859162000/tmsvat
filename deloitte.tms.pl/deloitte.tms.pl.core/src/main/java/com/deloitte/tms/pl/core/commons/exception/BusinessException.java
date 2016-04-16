package com.deloitte.tms.pl.core.commons.exception;

import com.deloitte.tms.pl.core.enums.ErrorCode;

/**
 * 
 * 基本异常类
 * 
 * @author dada
 */
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private ErrorCode errorCode = ErrorCode.UNKNOW;

	private String errorMessage = null;

	/** 业务异常构造器，传入错误代码和源异常 **/
	public BusinessException(ErrorCode errorCode, Throwable t) {
		super(t);
		this.errorCode = errorCode;
		this.errorMessage = "[" + errorCode.getMessage() + "]";
	}

	/** 业务异常构造器，传入错误信息和源异常 **/
	public BusinessException(String errorMessage, Throwable t) {
		super(t);
		this.errorMessage = errorMessage;
	}
	
	/** 业务异常构造器，传入错误代码和错误信息和源异常 **/
	public BusinessException(ErrorCode errorCode, String errorMessage, Throwable t) {
		super(t);
		this.errorCode = errorCode;
		this.errorMessage = "[" + errorCode.getMessage() + "] - " + errorMessage;
	}
	
	/** 业务异常构造器，传入错误代码和错误信息 **/
	public BusinessException(ErrorCode errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = "[" + errorCode.getMessage() + "] - " + errorMessage;
	}

	/** 业务异常构造器，传入错误信息 **/
	public BusinessException(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public ErrorCode getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * 返回异常信息
	 * 
	 * @return
	 * @author dada
	 */
	public String getMessage() {
		if (this.errorMessage != null) {
			return this.errorMessage;
		} else {
			return "未知的错误";
		}
	}

	/**
	 * 转化为字符串的方法
	 * 
	 * @author dada
	 */
	public String toString() {
		String s = getClass().getName();
		String message = getMessage();
		return (message != null) ? (s + ": " + message) : s;
	}
}
