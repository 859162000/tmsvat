package com.deloitte.tms.pl.workflow.command.impl;

import com.deloitte.tms.pl.workflow.command.Command;
import com.deloitte.tms.pl.workflow.env.Context;
import com.deloitte.tms.pl.workflow.model.variable.Variable;

/**
 * @author Jacky.gao
 * @since 2013年10月11日
 */
public class DeleteProcessVariableCommand implements Command<Object> {
	private String key;
	private long processInstanceId;
	public DeleteProcessVariableCommand(String key,long processInstanceId){
		this.key=key;
		this.processInstanceId=processInstanceId;
	}
	public Object execute(Context context) {
		String hql="delete "+Variable.class.getName()+" where processInstanceId=:pid and key=:key";
		context.getSession().createQuery(hql).setLong("pid", processInstanceId).setString("key",key).executeUpdate();
		context.getExpressionContext().removeContextVariables(processInstanceId, key);
		return null;
	}
}
