package com.deloitte.tms.pl.workflow.process.listener;

import com.deloitte.tms.pl.workflow.env.Context;
import com.deloitte.tms.pl.workflow.model.ProcessInstance;

/**
 * @author Jacky.gao
 * @since 2013年11月18日
 */
public interface ProcessListener {
	void processStart(ProcessInstance processInstance,Context context);
	void processEnd(ProcessInstance processInstance,Context context);
}
