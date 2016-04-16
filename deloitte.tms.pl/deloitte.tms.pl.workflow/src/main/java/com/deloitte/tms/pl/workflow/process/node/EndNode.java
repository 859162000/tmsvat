package com.deloitte.tms.pl.workflow.process.node;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.deloitte.tms.pl.workflow.command.impl.SaveHistoryProcessInstanceCommand;
import com.deloitte.tms.pl.workflow.command.impl.SaveProcessInstanceVariablesCommand;
import com.deloitte.tms.pl.workflow.env.Context;
import com.deloitte.tms.pl.workflow.model.HistoryBlob;
import com.deloitte.tms.pl.workflow.model.HistoryVariable;
import com.deloitte.tms.pl.workflow.model.ProcessDefinition;
import com.deloitte.tms.pl.workflow.model.ProcessInstance;
import com.deloitte.tms.pl.workflow.model.ProcessInstanceState;
import com.deloitte.tms.pl.workflow.model.variable.BlobVariable;
import com.deloitte.tms.pl.workflow.model.variable.DateVariable;
import com.deloitte.tms.pl.workflow.model.variable.TextVariable;
import com.deloitte.tms.pl.workflow.model.variable.Variable;
import com.deloitte.tms.pl.workflow.process.handler.ProcessEventHandler;
import com.deloitte.tms.pl.workflow.query.ProcessVariableQuery;
import com.deloitte.tms.pl.workflow.query.impl.ProcessVariableQueryImpl;
import com.deloitte.tms.pl.workflow.service.ProcessService;
import com.deloitte.tms.pl.workflow.utils.ProcessListenerUtils;

/**
 * @author Jacky.gao
 * @since 2013年7月31日
 */
public class EndNode extends Node{
	private static final long serialVersionUID = 1L;
	private boolean terminate=true;
	@Override
	public void cancel(Context context,ProcessInstance processInstance) {
	}
	@Override
	public boolean enter(Context context,ProcessInstance processInstance) {
		return true;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public synchronized String leave(Context context,ProcessInstance processInstance,String flowName) {
		Session session=context.getSession();
		ProcessService processService=context.getProcessService();
		ProcessDefinition process=processService.getProcessById(processInstance.getProcessId());
		boolean isRootProcessInstance=(processInstance.getId()==processInstance.getRootId());
		List<ProcessInstance> children=session.createCriteria(ProcessInstance.class).add(Restrictions.eq("rootId",processInstance.getRootId())).list();
		if(isRootProcessInstance){
			ProcessListenerUtils.fireProcessEndListers(processInstance, context);
			completeProcessInstance(context,process,processInstance,processInstance,children);
		}else if(isTerminate()){
			ProcessInstance rootProcessInstance=(ProcessInstance)session.get(ProcessInstance.class,processInstance.getRootId());
			ProcessListenerUtils.fireProcessEndListers(rootProcessInstance, context);
			completeProcessInstance(context,process,processInstance,rootProcessInstance,children);
		}else{
			if(children.size()==1){
				ProcessInstance rootProcessInstance=(ProcessInstance)session.get(ProcessInstance.class,processInstance.getRootId());
				ProcessListenerUtils.fireProcessEndListers(rootProcessInstance, context);
				completeProcessInstance(context,process,processInstance,rootProcessInstance,children);
			}else{
				doLeaveEventHandler(context, processInstance);
				completeActivityHistory(context, processInstance,null);
				session.delete(processInstance);
				context.getExpressionContext().removeContext(processInstance);
			}
		}
		return null;
	}
	
	private void completeProcessInstance(Context context,ProcessDefinition process,ProcessInstance processInstance, ProcessInstance rootProcessInstance,List<ProcessInstance> children) {
		doLeaveEventHandler(context, processInstance);
		completeActivityHistory(context, processInstance,null);
		//执行流程级别结束事件
		String processEventHandlerBean=process.getEventHandlerBean();
		if(StringUtils.isNotEmpty(processEventHandlerBean)){
			ProcessEventHandler bean=(ProcessEventHandler)context.getApplicationContext().getBean(processEventHandlerBean);
			bean.end(rootProcessInstance, context);
		}
		Session session=context.getSession();
		for(ProcessInstance pi:children){
			if(pi.getId()==rootProcessInstance.getId()){
				continue;
			}
			context.getProcessService().deleteProcessInstance(pi);
		}
		ProcessVariableQuery query=new ProcessVariableQueryImpl(context.getCommandService());
		query.rootprocessInstanceId(rootProcessInstance.getRootId());
		List<Variable> variables=query.list();
		saveHistoryProcessInstanceVariables(rootProcessInstance,context,variables);
		
		if(rootProcessInstance.getParentId()>0){
			//表明当前为子流程
			executeParentProcessInstance(context, rootProcessInstance);
		}else{
			context.getExpressionContext().removeContext(rootProcessInstance);
		}
		rootProcessInstance.setState(ProcessInstanceState.End);
		session.delete(rootProcessInstance);
		context.getCommandService().executeCommand(new SaveHistoryProcessInstanceCommand(rootProcessInstance));
		
	}
	
	private void executeParentProcessInstance(Context context,ProcessInstance rootProcessInstance) {
		ProcessService processService=context.getProcessService();
		ProcessInstance parentProcessInstance=processService.getProcessInstanceById(rootProcessInstance.getParentId());
		ProcessDefinition parentProcess=processService.getProcessById(parentProcessInstance.getProcessId());
		
		//检查当前父流程实例所在节点是否为子流程节点
		Node node=parentProcess.getNode(parentProcessInstance.getCurrentNode());
		if(node instanceof SubprocessNode){
			SubprocessNode subprocessNode=(SubprocessNode)node;
			Map<String,Object> vars=null;
			List<SubprocessVariable> outVariables=subprocessNode.getOutVariables();
			if(outVariables!=null && outVariables.size()>0){
				//将指定需要从子流程返回到父流程的变量取出来写入到父流程当中
				vars=new HashMap<String,Object>();
				for(SubprocessVariable var:outVariables){
					String key=var.getInParameterKey();
					Object obj=context.getExpressionContext().eval(rootProcessInstance, "${"+key+"}");
					if(obj==null)obj=processService.getProcessVariable(key, rootProcessInstance);
					if(obj==null){
						throw new IllegalArgumentException("Variable "+key+" value is not found in process instance "+rootProcessInstance.getId());
					}
					vars.put(var.getOutParameterKey(), obj);
				}
			}
			if(vars!=null){
				context.getCommandService().executeCommand(new SaveProcessInstanceVariablesCommand(parentProcessInstance, vars));
				context.getExpressionContext().addContextVariables(parentProcessInstance, vars);
			}
		}
		context.getExpressionContext().removeContext(rootProcessInstance);
		String leaveFlowName=node.leave(context, parentProcessInstance,null);
		node.completeActivityHistory(context, parentProcessInstance,leaveFlowName);
	}
	
	private void saveHistoryProcessInstanceVariables(ProcessInstance rootProcessInstance,Context context,List<Variable> variables) {
		Session session=context.getSession();
		for(Variable var:variables){
			HistoryVariable hisVar=new HistoryVariable();
			hisVar.setId(var.getId());
			hisVar.setKey(var.getKey());
			hisVar.setHistoryProcessInstanceId(rootProcessInstance.getHistoryProcessInstanceId());
			if(var instanceof BlobVariable){
				BlobVariable blobVar=(BlobVariable)var;
				long blobId=blobVar.getBlobId();
				hisVar.setStringValue(String.valueOf(blobId));
				HistoryBlob hisBlob=new HistoryBlob(blobVar.getValue());
				hisBlob.setId(blobId);
				session.save(hisBlob);
				session.delete(blobVar.getBlob());
			}else if(var instanceof TextVariable){
				TextVariable textVar=(TextVariable)var;
				long blobId=textVar.getBlobId();
				hisVar.setStringValue(String.valueOf(blobId));
				HistoryBlob hisBlob=new HistoryBlob(textVar.getValue());
				hisBlob.setId(blobId);
				session.save(hisBlob);
				session.delete(textVar.getBlob());
			}else if(var instanceof DateVariable){
				SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
				hisVar.setStringValue(sd.format(((Date)var.getValue())));
			}else{
				String stringValue=(var.getValue()==null?null:var.getValue().toString());
				if(stringValue!=null && stringValue.length()>250){
					stringValue=stringValue.substring(0,250);
				}
				hisVar.setStringValue(stringValue);										
			}
			hisVar.setType(var.getType());
			session.save(hisVar);
			session.delete(var);
		}
	}
	
	
	public boolean isTerminate() {
		return terminate;
	}
	public void setTerminate(boolean terminate) {
		this.terminate = terminate;
	}
}
