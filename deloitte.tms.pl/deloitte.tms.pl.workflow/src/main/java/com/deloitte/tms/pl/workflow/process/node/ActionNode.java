package com.deloitte.tms.pl.workflow.process.node;

import com.deloitte.tms.pl.workflow.env.Context;
import com.deloitte.tms.pl.workflow.model.ProcessInstance;
import com.deloitte.tms.pl.workflow.process.handler.ActionHandler;

/**
 * @author Jacky.gao
 * @since 2013年8月12日
 */
public class ActionNode extends Node {
	private static final long serialVersionUID = 1L;
	private String handlerBean;
	@Override
	public boolean enter(Context context, ProcessInstance processInstance) {
		return true;
	}
	
	@Override
	public String leave(Context context, ProcessInstance processInstance,String flowName) {
		ActionHandler handler=(ActionHandler)context.getApplicationContext().getBean(handlerBean);
		handler.handle(processInstance, context);
		return leaveNode(context, processInstance, flowName);
	}

	@Override
	public void cancel(Context context, ProcessInstance processInstance) {
	}

	public String getHandlerBean() {
		return handlerBean;
	}

	public void setHandlerBean(String handlerBean) {
		this.handlerBean = handlerBean;
	}
}
