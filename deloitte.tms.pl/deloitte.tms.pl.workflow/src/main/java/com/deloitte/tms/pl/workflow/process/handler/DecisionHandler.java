package com.deloitte.tms.pl.workflow.process.handler;

import com.deloitte.tms.pl.workflow.env.Context;
import com.deloitte.tms.pl.workflow.model.ProcessInstance;

/**
 * @author Jacky.gao
 * @since 2013年8月8日
 */
public interface DecisionHandler {
	String handle(Context context, ProcessInstance processInstance);
}
