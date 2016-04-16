package com.deloitte.tms.pl.workflow.process.handler;

import com.deloitte.tms.pl.workflow.env.Context;
import com.deloitte.tms.pl.workflow.model.ProcessInstance;
import com.deloitte.tms.pl.workflow.process.node.Node;

/**
 * 节点事件监听接口
 * @author Jacky.gao
 * @since 2013年8月20日
 */
public interface NodeEventHandler {
	/**
	 * 进入节点后触发的方法
	 * @param node 当前节点对象
	 * @param processInstance 当前流程实例对象
	 * @param context 流程上下文
	 */
	void enter(Node node,ProcessInstance processInstance,Context context);
	
	/**
	 * 离开节点后触发的方法
	 * @param node 当前节点对象
	 * @param processInstance 当前流程实例对象
	 * @param context 流程上下文
	 */
	void leave(Node node,ProcessInstance processInstance,Context context);
}
