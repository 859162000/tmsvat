package com.deloitte.tms.pl.core.enums;

/**
* <p>Title: ErrorCode.java</p>
* <p>Description: TODO</p>
* <p>Copyright: Copyright (c) 2007</p>
* <p>Company: LTGames</p>
* @author 102010cncger
* @date 2012-9-19
* @version 1.0
*/
public enum ErrorCode {

	/** 未知错误 **/
	UNKNOW("未知错误"),
	
	/** 基础数据错误 **/
	DATA("数据错误"),

	/** 校验错误 **/
	VALIDATE("校验错误");
	
	/** 异常信息 **/
	String message;

	private ErrorCode(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	
}
