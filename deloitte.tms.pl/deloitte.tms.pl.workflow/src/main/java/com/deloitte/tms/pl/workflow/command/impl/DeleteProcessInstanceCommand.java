package com.deloitte.tms.pl.workflow.command.impl;

import java.util.List;

import org.hibernate.Session;

import com.deloitte.tms.pl.workflow.command.Command;
import com.deloitte.tms.pl.workflow.env.Context;
import com.deloitte.tms.pl.workflow.model.ProcessDefinition;
import com.deloitte.tms.pl.workflow.model.ProcessInstance;
import com.deloitte.tms.pl.workflow.model.variable.Variable;

/**
 * 递归删除取消指定流程实例及其下子流程实现及他们所在的各个节点
 * 
 * @author Jacky.gao
 * @since 2013年8月8日
 */
public class DeleteProcessInstanceCommand implements Command<Object> {
	private ProcessInstance processInstance;
	public DeleteProcessInstanceCommand(ProcessInstance processInstance){
		this.processInstance=processInstance;
	}
	public Object execute(Context context) {
		ProcessDefinition process=context.getProcessService().getProcessById(processInstance.getProcessId());
		deleteProcessInstance(context,processInstance,process);
		return null;
	}
	@SuppressWarnings("unchecked")
	public Object deleteProcessInstance(Context context,ProcessInstance pi,ProcessDefinition process) {
		Session session=context.getSession();
		List<ProcessInstance> instances=session.createQuery("from "+ProcessInstance.class.getName()+" where parentId=:parentId").setLong("parentId",pi.getId()).list();
		for(ProcessInstance instance:instances){
			deleteProcessInstance(context,instance,process);
			session.createQuery("delete "+Variable.class.getName()+" where processInstanceId=:pIId").setLong("pIId",instance.getId()).executeUpdate();
		}
		session.createQuery("delete "+Variable.class.getName()+" where processInstanceId=:pIId").setLong("pIId",pi.getId()).executeUpdate();
		process.getNode(pi.getCurrentNode()).cancel(context, pi);
		session.delete(pi);
		context.getExpressionContext().removeContext(pi);
		return null;
	}
}
