package com.deloitte.tms.pl.workflow.process.node;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.deloitte.tms.pl.workflow.command.impl.SaveProcessInstanceVariablesCommand;
import com.deloitte.tms.pl.workflow.env.Context;
import com.deloitte.tms.pl.workflow.model.ProcessInstance;
import com.deloitte.tms.pl.workflow.model.ProcessInstanceState;
import com.deloitte.tms.pl.workflow.process.handler.ForeachHandler;
import com.deloitte.tms.pl.workflow.utils.IDGenerator;

/**
 * @author Jacky.gao
 * @since 2013年8月8日
 */
public class ForeachNode extends Node {
	private static final long serialVersionUID = 1L;
	private String var;
	private ForeachType foreachType;
	private String in;
	private String handlerBean;
	@SuppressWarnings("unchecked")
	@Override
	public boolean enter(Context context, ProcessInstance processInstance) {
		Collection<Object> coll=null;
		if(StringUtils.isEmpty(var)){
			throw new IllegalArgumentException("ForeachNode var property can not be null");
		}
		if(foreachType.equals(ForeachType.Handler)){
			ForeachHandler handler=(ForeachHandler)context.getApplicationContext().getBean(handlerBean);
			coll=handler.handle(processInstance, context);
			if(coll==null || coll.size()==0){
				throw new IllegalArgumentException("ForeachNode ["+handlerBean+"] return value is invalid!");
			}
		}else{
			if(StringUtils.isEmpty(in)){
				throw new IllegalArgumentException("ForeachNode in property can not be null");
			}
			Object variableObj=context.getExpressionContext().eval(processInstance, in);
			if(variableObj instanceof Object[]){
				coll=new ArrayList<Object>();
				Object objs[]=(Object[])variableObj;
				for(Object obj:objs){
					coll.add(obj);
				}
			}else if(variableObj instanceof Collection){
				coll=(Collection<Object>)variableObj;
			}else{
				throw new IllegalArgumentException("ForeachHandler ["+in+"] return value type must be a collection or a array!");
			}
		}
		createSubprocessInstance(context, processInstance, coll);
		return false;
	}


	private void createSubprocessInstance(Context context,ProcessInstance processInstance,Collection<Object> coll) {
		for(Object obj:coll){
			ProcessInstance subProcessInstance=new ProcessInstance();
			subProcessInstance.setId(IDGenerator.getInstance().nextId());
			subProcessInstance.setProcessId(getProcessId());
			subProcessInstance.setParentId(processInstance.getId());
			subProcessInstance.setCreateDate(new Date());
			subProcessInstance.setState(ProcessInstanceState.Start);
			subProcessInstance.setRootId(processInstance.getRootId());
			subProcessInstance.setParallelInstanceCount(coll.size());
			subProcessInstance.setPromoter(processInstance.getPromoter());
			subProcessInstance.setHistoryProcessInstanceId(processInstance.getHistoryProcessInstanceId());
			subProcessInstance.setCurrentTask(processInstance.getCurrentTask());
			subProcessInstance.setBusinessId(processInstance.getBusinessId());
			subProcessInstance.setTag(processInstance.getTag());
			context.getSession().save(subProcessInstance);
			Map<String,Object> variables=new HashMap<String,Object>();
			variables.put(var, obj);
			context.getCommandService().executeCommand(new SaveProcessInstanceVariablesCommand(subProcessInstance, variables));
			context.getExpressionContext().createContext(subProcessInstance, variables);
			getSequenceFlows().get(0).execute(context, subProcessInstance);
		}
	}


	@Override
	public String leave(Context context, ProcessInstance processInstance,String flowName) {
		return null;
	}
	@Override
	public void cancel(Context context, ProcessInstance processInstance) {
		
	}

	public ForeachType getForeachType() {
		return foreachType;
	}


	public void setForeachType(ForeachType foreachType) {
		this.foreachType = foreachType;
	}


	public String getIn() {
		return in;
	}

	public void setIn(String in) {
		this.in = in;
	}

	public String getVar() {
		return var;
	}

	public void setVar(String var) {
		this.var = var;
	}

	public String getHandlerBean() {
		return handlerBean;
	}

	public void setHandlerBean(String handlerBean) {
		this.handlerBean = handlerBean;
	}
}
