package com.deloitte.tms.pl.workflow.process.handler;

import java.util.Collection;

import com.deloitte.tms.pl.workflow.env.Context;
import com.deloitte.tms.pl.workflow.model.ProcessInstance;

/**
 * @author Jacky.gao
 * @since 2013年8月10日
 */
public interface ForeachHandler {
	Collection<Object> handle(ProcessInstance processInstance,Context context); 
}
