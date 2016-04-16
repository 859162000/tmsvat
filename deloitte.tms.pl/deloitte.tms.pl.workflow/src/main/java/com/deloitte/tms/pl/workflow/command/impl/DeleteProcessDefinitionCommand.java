package com.deloitte.tms.pl.workflow.command.impl;

import java.util.List;

import org.hibernate.Session;

import com.deloitte.tms.pl.workflow.command.Command;
import com.deloitte.tms.pl.workflow.env.Context;
import com.deloitte.tms.pl.workflow.model.Blob;
import com.deloitte.tms.pl.workflow.model.HistoryActivity;
import com.deloitte.tms.pl.workflow.model.HistoryProcessInstance;
import com.deloitte.tms.pl.workflow.model.HistoryTask;
import com.deloitte.tms.pl.workflow.model.HistoryVariable;
import com.deloitte.tms.pl.workflow.model.ProcessDefinition;
import com.deloitte.tms.pl.workflow.model.ProcessInstance;
import com.deloitte.tms.pl.workflow.model.variable.Variable;
import com.deloitte.tms.pl.workflow.query.ProcessInstanceQuery;
import com.deloitte.tms.pl.workflow.service.ProcessService;

/**
 * @author Jacky.gao
 * @since 2013年9月9日
 */
public class DeleteProcessDefinitionCommand implements Command<Object> {
	private ProcessDefinition processDefinition;
	public DeleteProcessDefinitionCommand(ProcessDefinition processDefinition){
		this.processDefinition=processDefinition;
	}
	@SuppressWarnings("unchecked")
	public Object execute(Context context) {
		ProcessService processService=context.getProcessService();
		ProcessInstanceQuery query=context.getProcessService().createProcessInstanceQuery();
		query.processId(processDefinition.getId());
		Session session=context.getSession();
		for(ProcessInstance pi:query.list()){
			processService.deleteProcessInstance(pi);
			session.createQuery("delete "+Variable.class.getName()+" where processInstanceId=:processInstanceId").setLong("processInstanceId", pi.getId()).executeUpdate();
		}
		
		List<HistoryProcessInstance> hisInstances=session.createQuery("from "+HistoryProcessInstance.class.getName()+" where processId=:processId").setLong("processId", processDefinition.getId()).list();
		for(HistoryProcessInstance instance:hisInstances){
			session.createQuery("delete "+HistoryVariable.class.getName()+" where historyProcessInstanceId=:historyProcessInstanceId").setLong("historyProcessInstanceId", instance.getId()).executeUpdate();
		}
		
		session.createQuery("delete "+Blob.class.getName()+" where processId=:processId").setLong("processId", processDefinition.getId()).executeUpdate();
		session.createQuery("delete "+HistoryProcessInstance.class.getName()+" where processId=:processId").setLong("processId", processDefinition.getId()).executeUpdate();
		session.createQuery("delete "+HistoryTask.class.getName()+" where processId=:processId").setLong("processId", processDefinition.getId()).executeUpdate();
		session.createQuery("delete "+HistoryActivity.class.getName()+" where processId=:processId").setLong("processId", processDefinition.getId()).executeUpdate();
		session.delete(processDefinition);
		return null;
	}
}
