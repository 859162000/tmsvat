package com.deloitte.tms.pl.workflow.command.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.deloitte.tms.pl.workflow.command.Command;
import com.deloitte.tms.pl.workflow.env.Context;
import com.deloitte.tms.pl.workflow.model.ProcessInstance;
import com.deloitte.tms.pl.workflow.model.variable.BlobVariable;
import com.deloitte.tms.pl.workflow.model.variable.TextVariable;
import com.deloitte.tms.pl.workflow.model.variable.Variable;

/**
 * @author Jacky.gao
 * @since 2013年8月2日
 */
public class GetProcessInstanceVariableCommand implements Command<Variable> {
	private ProcessInstance processInstance;
	private String key;
	public GetProcessInstanceVariableCommand(String key,ProcessInstance processInstance){
		this.processInstance=processInstance;
		this.key=key;
	}
	public Variable execute(Context context) {
		return getVariable(context,processInstance);
	}
	@SuppressWarnings("unchecked")
	private Variable getVariable(Context context,ProcessInstance pi) {
		Session session=context.getSession();
		Criteria criteria=session.createCriteria(Variable.class).add(Restrictions.eq("processInstanceId", pi.getId())).add(Restrictions.eq("key", key));;
		List<Variable> vars=criteria.list();
		if(vars.size()==0){
			if(pi.getParentId()>0){
				ProcessInstance parentInstance=(ProcessInstance)session.get(ProcessInstance.class,pi.getParentId());
				return getVariable(context, parentInstance);
			}else{
				return null;
			}
		}else{
			for(Variable var:vars){
				if(var instanceof BlobVariable){
					((BlobVariable)var).initValue(context);
				}
				if(var instanceof TextVariable){
					((TextVariable)var).initValue(context);
				}
			}
			return vars.get(0);
		}
	}
}
