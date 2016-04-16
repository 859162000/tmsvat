package com.deloitte.tms.pl.workflow.process.handler;

import com.deloitte.tms.pl.workflow.env.Context;
import com.deloitte.tms.pl.workflow.model.ProcessInstance;

/**
 * @author Jacky.gao
 * @since 2013年10月5日
 */
public interface CountersignHandler {
	boolean handle(Context context,ProcessInstance processInstance);
}
