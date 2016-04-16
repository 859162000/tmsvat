package com.deloitte.tms.pl.workflow.process.handler;

import com.deloitte.tms.pl.workflow.env.Context;
import com.deloitte.tms.pl.workflow.model.ProcessInstance;
import com.deloitte.tms.pl.workflow.process.flow.SequenceFlow;

/**
 * @author Jacky.gao
 * @since 2013年7月31日
 */
public interface ConditionHandler {
	boolean handle(Context context,ProcessInstance processInstance,SequenceFlow flow);
}
