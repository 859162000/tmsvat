package com.deloitte.tms.pl.workflow.deploy.validate;

/**
 * @author Jacky.gao
 * @since 2013年11月29日
 */
public class ProcessValidateException extends RuntimeException {
	private static final long serialVersionUID = -8700678017516761037L;
	private String message;
	public ProcessValidateException(String message){
		this.message=message;
	}
	public String getMessage() {
		return message;
	}	
}
