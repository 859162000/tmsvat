package com.deloitte.tms.pl.workflow.command.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import com.deloitte.tms.pl.workflow.command.Command;
import com.deloitte.tms.pl.workflow.env.Context;
import com.deloitte.tms.pl.workflow.model.ProcessInstance;
import com.deloitte.tms.pl.workflow.model.variable.BlobVariable;
import com.deloitte.tms.pl.workflow.model.variable.TextVariable;
import com.deloitte.tms.pl.workflow.model.variable.Variable;
import com.deloitte.tms.pl.workflow.query.ProcessVariableQuery;
import com.deloitte.tms.pl.workflow.query.impl.ProcessVariableQueryImpl;
import com.deloitte.tms.pl.workflow.utils.IDGenerator;

/**
 * @author Jacky.gao
 * @since 2013年7月31日
 */
public class SaveProcessInstanceVariablesCommand implements Command<Object> {
	private Map<String,Object> variables;
	private ProcessInstance processInstance;
	public SaveProcessInstanceVariablesCommand(ProcessInstance processInstance,Map<String,Object> variables){
		this.processInstance=processInstance;
		this.variables=variables;
	}
	public Object execute(Context context) {
		Session session=context.getSession();
		for(String key:variables.keySet()){
			ProcessVariableQuery query=new ProcessVariableQueryImpl(context.getCommandService());
			query.processInstanceId(processInstance.getId());
			query.key(key);
			List<Variable> oldVars=query.list();
			for(Variable var:oldVars){
				session.delete(var);
				if(var instanceof TextVariable){
					session.delete(((TextVariable)var).getBlob());
				}
				if(var instanceof BlobVariable){
					session.delete(((BlobVariable)var).getBlob());
				}
			}
			Object obj=variables.get(key);
			if(obj==null){
				throw new IllegalArgumentException("Variable ["+key+"] value can not be null.");
			}
			Variable var=Variable.newVariable(obj, context);
			var.setId(IDGenerator.getInstance().nextId());
			var.setKey(key);
			var.setProcessInstanceId(processInstance.getId());
			var.setRootProcessInstanceId(processInstance.getRootId());
			context.getExpressionContext().addContextVariables(processInstance, variables);
			session.save(var);
		}
		return null;
	}

}
	