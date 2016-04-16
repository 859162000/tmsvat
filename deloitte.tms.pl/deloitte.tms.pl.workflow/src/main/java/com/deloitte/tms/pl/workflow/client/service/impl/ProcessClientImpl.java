package com.deloitte.tms.pl.workflow.client.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.workflow.client.service.ProcessClient;
import com.deloitte.tms.pl.workflow.console.rest.RestBase;
import com.deloitte.tms.pl.workflow.model.ProcessDefinition;
import com.deloitte.tms.pl.workflow.model.ProcessInstance;
import com.deloitte.tms.pl.workflow.model.variable.Variable;
import com.deloitte.tms.pl.workflow.query.ProcessInstanceQuery;
import com.deloitte.tms.pl.workflow.query.ProcessQuery;
import com.deloitte.tms.pl.workflow.query.ProcessVariableQuery;
import com.deloitte.tms.pl.workflow.service.ProcessService;
import com.deloitte.tms.pl.workflow.service.StartProcessInfo;

/**
 * @author Jacky.gao
 * @since 2013年9月22日
 */
@Component(ProcessClient.BEAN_ID)
public class ProcessClientImpl implements ProcessClient {
	@Resource
	private RestBase rest;
	@Resource
	private ProcessService processService;
	
	public ProcessDefinition getProcessById(long processId) {
		return processService.getProcessById(processId);
	}

	public ProcessDefinition getProcessByKey(String key) {
		return processService.getProcessByKey(key);
	}

	public ProcessDefinition getProcessByName(String processName) {
		return processService.getProcessByName(processName);
	}

	public ProcessDefinition getProcessByName(String processName, int version) {
		return processService.getProcessByName(processName, version);
	}

	public ProcessInstance startProcessById(long processId,StartProcessInfo startProcessInfo) {
		if(StringUtils.isNotEmpty(rest.getBaseUrl())){
			String uri="/uflo/process/start/id/"+processId+"";
			ResponseEntity<ProcessInstance> responseEntity=rest.post(uri, startProcessInfo,ProcessInstance.class);
			return responseEntity.getBody();			
		}else{
			return processService.startProcessById(processId, startProcessInfo);
		}
	}

	public ProcessInstance startProcessByKey(String key,StartProcessInfo startProcessInfo) {
		if(StringUtils.isNotEmpty(rest.getBaseUrl())){
			String uri="/uflo/process/start/key/"+key+"";
			ResponseEntity<ProcessInstance> responseEntity=rest.post(uri, startProcessInfo,ProcessInstance.class);
			return responseEntity.getBody();			
		}else{
			return processService.startProcessByKey(key, startProcessInfo);
		}
	}

	public ProcessInstance startProcessByName(String processName,StartProcessInfo startProcessInfo) {
		if(StringUtils.isNotEmpty(rest.getBaseUrl())){
			String uri="/uflo/process/start/name/"+processName+"";
			ResponseEntity<ProcessInstance> responseEntity=rest.post(uri, startProcessInfo,ProcessInstance.class);
			return responseEntity.getBody();
		}else{
			return processService.startProcessByName(processName, startProcessInfo);
		}
	}
	
	public void deleteProcessInstanceById(long processInstanceId) {
		if(StringUtils.isNotEmpty(rest.getBaseUrl())){
			String uri="/uflo/processinstance/delete/"+processInstanceId+"";
			rest.post(uri, null,null);			
		}else{
			processService.deleteProcessInstanceById(processInstanceId);
		}
	}


	public ProcessInstance getProcessInstanceById(long processInstanceId) {
		return processService.getProcessInstanceById(processInstanceId);
	}

	public List<Variable> getProcessVariables(long processInsanceId) {
		return processService.getProcessVariables(processInsanceId);
	}

	public List<Variable> getProcessVariables(ProcessInstance processInsance) {
		return processService.getProcessVariables(processInsance);
	}

	public Object getProcessVariable(String key, ProcessInstance processInstance) {
		return processService.getProcessVariable(key, processInstance);
	}

	public Object getProcessVariable(String key, long processInsanceId) {
		return processService.getProcessVariable(key, processInsanceId);
	}

	public ProcessInstanceQuery createProcessInstanceQuery() {
		return processService.createProcessInstanceQuery();
	}

	public ProcessVariableQuery createProcessVariableQuery() {
		return processService.createProcessVariableQuery();
	}

	public ProcessQuery createProcessQuery() {
		return processService.createProcessQuery();
	}

	public void deleteProcess(long processId) {
		if(StringUtils.isNotEmpty(rest.getBaseUrl())){
			String uri="/uflo/process/delete/id/"+processId+"";
			rest.post(uri, null,null);
			processService.deleteProcessFromMemory(processId);
		}else{
			processService.deleteProcess(processId);
		}
	}

	public void deleteProcess(String processKey) {
		if(StringUtils.isNotEmpty(rest.getBaseUrl())){
			String uri="/uflo/process/delete/key/"+processKey+"";
			rest.post(uri, null,null);
			processService.deleteProcessFromMemory(processKey);
		}else{
			processService.deleteProcess(processKey);
		}
	}

	public void deleteProcess(ProcessDefinition processDefinition) {
		if(StringUtils.isNotEmpty(rest.getBaseUrl())){
			String uri="/uflo/process/delete/id/"+processDefinition.getId()+"";
			rest.post(uri,null,null);
			processService.deleteProcessFromMemory(processDefinition.getId());
		}else{
			processService.deleteProcess(processDefinition);
		}
	}
	
	public RestBase getRest() {
		return rest;
	}

	public void setRest(RestBase rest) {
		this.rest = rest;
	}

	public ProcessService getProcessService() {
		return processService;
	}

	public void setProcessService(ProcessService processService) {
		this.processService = processService;
	}
}
