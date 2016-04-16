package com.deloitte.tms.pl.workflow.process.node;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.Restrictions;

import com.deloitte.tms.pl.workflow.command.impl.StartProcessInstanceCommand;
import com.deloitte.tms.pl.workflow.env.Context;
import com.deloitte.tms.pl.workflow.model.ProcessDefinition;
import com.deloitte.tms.pl.workflow.model.ProcessInstance;
import com.deloitte.tms.pl.workflow.process.flow.SequenceFlowImpl;
import com.deloitte.tms.pl.workflow.service.ProcessService;
import com.deloitte.tms.pl.workflow.service.StartProcessInfo;
/**
 * @author Jacky.gao
 * @since 2013年8月7日
 */
public class SubprocessNode extends Node {
	private static final long serialVersionUID = 1L;
	private static final Log log=LogFactory.getLog(SubprocessNode.class);
	private SubprocessType subprocessType;
	private String subprocessName;
	private String subprocessKey;
	private String subprocessId;
	private List<SubprocessVariable> inVariables;
	private List<SubprocessVariable> outVariables;
	
	@Override
	public boolean enter(Context context, ProcessInstance processInstance) {
		ProcessService processService=context.getProcessService();
		ProcessDefinition subprocess = getTargetSubprocess(processService);
		Map<String,Object> variables=null;
		if(inVariables!=null && inVariables.size()>0){
			variables=new HashMap<String,Object>();
			for(SubprocessVariable var:inVariables){
				Object obj=processService.getProcessVariable(var.getInParameterKey(), processInstance);
				variables.put(var.getOutParameterKey(),obj);
			}
		}
		StartProcessInfo startProcessInfo=new StartProcessInfo(processInstance.getPromoter());
		startProcessInfo.setBusinessId(processInstance.getBusinessId());
		context.getCommandService().executeCommand(new StartProcessInstanceCommand(subprocess,variables,startProcessInfo,processInstance.getId()));
		return false;
	}
	@Override
	public String leave(Context context, ProcessInstance processInstance,String flowName) {
		SequenceFlowImpl targetFlow=null;
		if(StringUtils.isNotEmpty(flowName)){
			targetFlow=getFlow(flowName);
			if(targetFlow==null){
				throw new IllegalArgumentException("Flow "+flowName+" is not exist!");
			}
		}else{
			targetFlow=getSequenceFlows().get(0);
		}
		targetFlow.execute(context, processInstance);
		return targetFlow.getName();
	}
	@SuppressWarnings("unchecked")
	@Override
	public void cancel(Context context, ProcessInstance processInstance) {
		ProcessService processService=context.getProcessService();
		ProcessDefinition subprocess = getTargetSubprocess(processService);
		if(subprocess==null){
			log.error("SubprocessNode must be specify subprocessKey/subprocessId or subprocessName at least one");
			return;
		}
		List<ProcessInstance> subinstances=context.getSession().createCriteria(ProcessInstance.class).add(Restrictions.eq("parentId", processInstance.getId())).add(Restrictions.eq("processId",subprocess.getId())).list();
		for(ProcessInstance pi:subinstances){
			processService.deleteProcessInstance(pi);
		}
	}
	private ProcessDefinition getTargetSubprocess(ProcessService processService) {
		ProcessDefinition subprocess=null;
		if(subprocessType.equals(SubprocessType.Id)){
			if(StringUtils.isEmpty(subprocessId)){
				throw new IllegalArgumentException("Subprocess type is Id,so subprocess id can not be null.");
			}
			subprocess=processService.getProcessById(Long.valueOf(subprocessId));
		}else if(subprocessType.equals(SubprocessType.Key)){
			if(StringUtils.isEmpty(subprocessKey)){
				throw new IllegalArgumentException("Subprocess type is Key,so subprocess key can not be null.");
			}
			subprocess=processService.getProcessByKey(subprocessKey);
		}else{
			if(StringUtils.isEmpty(subprocessName)){
				throw new IllegalArgumentException("Subprocess type is Name,so subprocess name can not be null.");
			}
			subprocess=processService.getProcessByName(subprocessName);
		}
		return subprocess;
	}
	
	public SubprocessType getSubprocessType() {
		return subprocessType;
	}
	public void setSubprocessType(SubprocessType subprocessType) {
		this.subprocessType = subprocessType;
	}
	public String getSubprocessName() {
		return subprocessName;
	}
	public void setSubprocessName(String subprocessName) {
		this.subprocessName = subprocessName;
	}
	public String getSubprocessKey() {
		return subprocessKey;
	}
	public void setSubprocessKey(String subprocessKey) {
		this.subprocessKey = subprocessKey;
	}
	public List<SubprocessVariable> getInVariables() {
		return inVariables;
	}
	public void setInVariables(List<SubprocessVariable> inVariables) {
		this.inVariables = inVariables;
	}
	public List<SubprocessVariable> getOutVariables() {
		return outVariables;
	}
	public void setOutVariables(List<SubprocessVariable> outVariables) {
		this.outVariables = outVariables;
	}
	public String getSubprocessId() {
		return subprocessId;
	}
	public void setSubprocessId(String subprocessId) {
		this.subprocessId = subprocessId;
	}
}
