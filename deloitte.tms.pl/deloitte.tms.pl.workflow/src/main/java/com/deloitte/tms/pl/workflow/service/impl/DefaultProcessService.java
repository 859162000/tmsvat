package com.deloitte.tms.pl.workflow.service.impl;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

import javax.annotation.Resource;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.workflow.command.CommandService;
import com.deloitte.tms.pl.workflow.command.impl.DeleteProcessDefinitionCommand;
import com.deloitte.tms.pl.workflow.command.impl.DeleteProcessInstanceCommand;
import com.deloitte.tms.pl.workflow.command.impl.DeleteProcessVariableCommand;
import com.deloitte.tms.pl.workflow.command.impl.GetExpressionValueCommand;
import com.deloitte.tms.pl.workflow.command.impl.GetProcessByKeyCommand;
import com.deloitte.tms.pl.workflow.command.impl.GetProcessCommand;
import com.deloitte.tms.pl.workflow.command.impl.GetProcessInstanceCommand;
import com.deloitte.tms.pl.workflow.command.impl.GetProcessInstanceVariableCommand;
import com.deloitte.tms.pl.workflow.command.impl.SaveProcessInstanceVariablesCommand;
import com.deloitte.tms.pl.workflow.command.impl.StartProcessInstanceCommand;
import com.deloitte.tms.pl.workflow.deploy.ProcessDeployer;
import com.deloitte.tms.pl.workflow.model.ProcessDefinition;
import com.deloitte.tms.pl.workflow.model.ProcessInstance;
import com.deloitte.tms.pl.workflow.model.variable.Variable;
import com.deloitte.tms.pl.workflow.query.ProcessInstanceQuery;
import com.deloitte.tms.pl.workflow.query.ProcessQuery;
import com.deloitte.tms.pl.workflow.query.ProcessVariableQuery;
import com.deloitte.tms.pl.workflow.query.impl.ProcessInstanceQueryImpl;
import com.deloitte.tms.pl.workflow.query.impl.ProcessQueryImpl;
import com.deloitte.tms.pl.workflow.query.impl.ProcessVariableQueryImpl;
import com.deloitte.tms.pl.workflow.service.ProcessInterceptor;
import com.deloitte.tms.pl.workflow.service.ProcessService;
import com.deloitte.tms.pl.workflow.service.StartProcessInfo;

/**
 * @author Jacky.gao
 * @since 2013年7月29日
 */
@Component(ProcessService.BEAN_ID)
public class DefaultProcessService implements ProcessService,ApplicationContextAware{
	private Collection<ProcessInterceptor> processInterceptors;
	@Resource	
	private ProcessDeployer processDeployer;
	@Resource
	private CommandService commandService;
	private Map<String,ProcessDefinition> processMap=new HashMap<String,ProcessDefinition>();
	
	public synchronized ProcessDefinition getProcessById(long processId) {
		if(processMap.containsKey(String.valueOf(processId))){
			return processMap.get(String.valueOf(processId));
		}else{
			ProcessDefinition process=commandService.executeCommand(new GetProcessCommand(processId,null,0));
			processMap.put(String.valueOf(process.getId()),process);
			return process;
		}
	}
	
	public void deleteProcessVariable(String key, long processInsanceId) {
		commandService.executeCommand(new DeleteProcessVariableCommand(key,processInsanceId));
	}
	
	public ProcessDefinition getProcessByKey(String key) {
		for(ProcessDefinition pd:processMap.values()){
			if(pd.getKey()==null)continue;
			if(pd.getKey().equals(key)){
				return pd;
			}
		}
		ProcessDefinition process=commandService.executeCommand(new GetProcessByKeyCommand(key));
		if(process!=null){
			processMap.put(String.valueOf(process.getId()),process);			
		}
		return process;
	}

	public ProcessDefinition getProcessByName(String processName) {
		return commandService.executeCommand(new GetProcessCommand(0,processName,0));
	}

	public  synchronized ProcessDefinition getProcessByName(String processName, final int version) {
		ProcessDefinition target=null;
		for(ProcessDefinition p:processMap.values()){
			if(p.getName().equals(processName) && p.getVersion()==version){
				target=p;
				break;
			}
		}
		if(target!=null){
			return target;
		}else{
			ProcessDefinition process=commandService.executeCommand(new GetProcessCommand(0,processName,version));
			processMap.put(String.valueOf(process.getId()),process);
			return process;
		}
	}
	
	public ProcessInstance startProcessByKey(String key,StartProcessInfo startProcessInfo) {
		ProcessDefinition process=getProcessByKey(key);
		if(process==null){
			throw new IllegalArgumentException("Process definition ["+key+"] was not exist!");
		}
		checkProcessEffectDate(process);
		return startProcess(process,startProcessInfo,startProcessInfo.getVariables());
	}

	public ProcessInstance startProcessById(long processId,StartProcessInfo startProcessInfo) {
		ProcessDefinition process=getProcessById(processId);
		if(process==null){
			throw new IllegalArgumentException("Process definition ["+processId+"] was not exist!");
		}
		checkProcessEffectDate(process);
		return startProcess(process,startProcessInfo,startProcessInfo.getVariables());
	}
	
	private void checkProcessEffectDate(ProcessDefinition process){
		Date effectDate=process.getEffectDate();
		if(effectDate==null){
			return;
		}
		SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(effectDate.getTime()>(new Date()).getTime()){
			throw new IllegalStateException("Process definition "+process.getName()+" effect date is "+sd.format(effectDate)+".");
		}
	}
	
	public ProcessInstance startProcessByName(String processName,StartProcessInfo startProcessInfo) {
		ProcessDefinition process=getProcessByName(processName);
		if(process==null){
			throw new IllegalArgumentException("Process definition ["+processName+"] was not exist!");
		}
		checkProcessEffectDate(process);
		return startProcess(process,startProcessInfo,startProcessInfo.getVariables());
	}

	private ProcessInstance startProcess(ProcessDefinition process,StartProcessInfo startProcessInfo,Map<String,Object> variables){
		return commandService.executeCommand(new StartProcessInstanceCommand(process,variables,startProcessInfo,0));
	}

	public ProcessInstance startProcessByName(String processName,StartProcessInfo startProcessInfo, int version) {
		ProcessDefinition process=getProcessByName(processName,version);
		if(process==null){
			throw new IllegalArgumentException("Process definition ["+processName+"-"+version+"] was not exist!");
		}
		checkProcessEffectDate(process);
		return startProcess(process,startProcessInfo,startProcessInfo.getVariables());
	}

	public synchronized ProcessDefinition deployProcess(ZipInputStream zipInputStream) {
		return processDeployer.deploy(zipInputStream);
	}
	public synchronized ProcessDefinition deployProcess(InputStream inputStream) {
		return processDeployer.deploy(inputStream);
	}
	
	public ProcessDefinition deployProcess(InputStream inputStream,long processId) {
		int version=getProcessById(processId).getVersion();
		ProcessDefinition process=processDeployer.deploy(inputStream,version,processId);
		processMap.put(String.valueOf(processId), process);
		doProcessInterceptors(process,true);
		return process;
	}

	public synchronized ProcessInstance getProcessInstanceById(long processInstanceId) {
		return commandService.executeCommand(new GetProcessInstanceCommand(processInstanceId));
	}
	public void setProcessDeployer(ProcessDeployer processDeployer) {
		this.processDeployer = processDeployer;
	}

	public void setCommandService(CommandService commandService) {
		this.commandService = commandService;
	}

	public void saveProcessVariable(long processInstanceId,String key, Object value) {
		Map<String,Object> variables=new HashMap<String,Object>();
		variables.put(key, value);
		saveProcessVariables(processInstanceId, variables);
	}
	
	public void saveProcessVariables(long processInstanceId,Map<String, Object> variables) {
		ProcessInstance pi=getProcessInstanceById(processInstanceId);
		commandService.executeCommand(new SaveProcessInstanceVariablesCommand(pi,variables));
	}
	
	public List<Variable> getProcessVariables(long processInstanceId) {
		ProcessVariableQuery query=new ProcessVariableQueryImpl(commandService);
		query.processInstanceId(processInstanceId);
		return query.list();
	}

	public List<Variable> getProcessVariables(ProcessInstance processInstance) {
		ProcessVariableQuery query=new ProcessVariableQueryImpl(commandService);
		query.rootprocessInstanceId(processInstance.getRootId());
		return query.list();
	}

	public Object getProcessVariable(String key,ProcessInstance processInstance) {
		Object obj=commandService.executeCommand(new GetExpressionValueCommand(processInstance.getId(),key));
		if(obj!=null)return obj;
		Variable var=commandService.executeCommand(new GetProcessInstanceVariableCommand(key,processInstance));
		if(var!=null){
			return var.getValue();
		}
		return null;
	}

	public Object getProcessVariable(String key, long processInstanceId) {
		Object obj=commandService.executeCommand(new GetExpressionValueCommand(processInstanceId,key));
		if(obj!=null)return obj;
		ProcessInstance pi=getProcessInstanceById(processInstanceId);
		return getProcessVariable(key, pi);
	}

	public void deleteProcessInstance(ProcessInstance processInstance) {
		commandService.executeCommand(new DeleteProcessInstanceCommand(processInstance));
	}

	public void deleteProcessInstanceById(long processInstanceId) {
		deleteProcessInstance(getProcessInstanceById(processInstanceId));
	}

	public ProcessInstanceQuery createProcessInstanceQuery() {
		return new ProcessInstanceQueryImpl(commandService);
	}

	public ProcessVariableQuery createProcessVariableQuery() {
		return new ProcessVariableQueryImpl(commandService);
	}
	
	public ProcessQuery createProcessQuery() {
		return new ProcessQueryImpl(commandService);
	}
	
	public void deleteProcess(long processId) {
		ProcessDefinition processDefinition=getProcessById(processId);
		deleteProcess(processDefinition);
	}
	public void deleteProcess(String processKey) {
		ProcessDefinition processDefinition=getProcessByKey(processKey);
		deleteProcess(processDefinition);
	}
	
	public void updateProcessForMemory(long processId) {
		ProcessDefinition process=commandService.executeCommand(new GetProcessCommand(processId,null,0));
		if(process!=null){
			processMap.put(String.valueOf(process.getId()),process);			
		}
	}
	
	public void deleteProcess(ProcessDefinition processDefinition) {
		commandService.executeCommand(new DeleteProcessDefinitionCommand(processDefinition));
		processMap.remove(String.valueOf(processDefinition.getId()));
		doProcessInterceptors(processDefinition,false);
	}
	
	public void deleteProcessFromMemory(long processId) {
		processMap.remove(String.valueOf(processId));
	}
	public void deleteProcessFromMemory(ProcessDefinition processDefinition) {
		processMap.remove(String.valueOf(processDefinition.getId()));
	}
	public void deleteProcessFromMemory(String processKey) {
		ProcessDefinition processDefinition=getProcessByKey(processKey);
		processMap.remove(String.valueOf(processDefinition.getId()));
	}
	
	private void doProcessInterceptors(ProcessDefinition process,boolean update){
		for(ProcessInterceptor interceptor:processInterceptors){
			if(update){
				interceptor.updateProcess(process);
			}else{
				interceptor.deleteProcess(process);
			}
		}
	}
	
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		processInterceptors=applicationContext.getBeansOfType(ProcessInterceptor.class).values();
	}
}
