package com.deloitte.tms.pl.workflow.process.handler;

import com.deloitte.tms.pl.workflow.env.Context;
import com.deloitte.tms.pl.workflow.model.ProcessInstance;

/**
 * 流程实例开始后及结束后触发的事件
 * @author Jacky.gao
 * @since 2013年8月20日
 */
public interface ProcessEventHandler {
	/**
	 * 流程实例开始后触发的方法
	 * @param processInstance 流程实例
	 * @param context 流程实例上下文
	 */
	void start(ProcessInstance processInstance,Context context);
	
	/**
	 * 流程实例结束后触发的方法
	 * @param processInstance 流程实例
	 * @param context 流程实例上下文
	 */
	void end(ProcessInstance processInstance,Context context);
}
