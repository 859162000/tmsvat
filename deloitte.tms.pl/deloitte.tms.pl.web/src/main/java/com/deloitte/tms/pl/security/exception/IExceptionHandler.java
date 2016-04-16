package com.deloitte.tms.pl.security.exception;

import org.springframework.security.web.context.HttpRequestResponseHolder;

/**
 * @since 2013-1-27
 * @author Jacky.gao
 */
public interface IExceptionHandler {
	void handle(HttpRequestResponseHolder requestResponseHolder,Throwable exception);
	boolean support(Throwable exception);
}
