package com.deloitte.tms.pl.workflow.command.impl;

import org.hibernate.criterion.Restrictions;

import com.deloitte.tms.pl.workflow.command.Command;
import com.deloitte.tms.pl.workflow.env.Context;
import com.deloitte.tms.pl.workflow.model.HistoryProcessInstance;

/**
 * @author Jacky.gao
 * @since 2013年9月12日
 */
public class GetHistoryProcessInstanceCommand implements Command<HistoryProcessInstance> {
	private long processInstanceId;
	public GetHistoryProcessInstanceCommand(long processInstanceId){
		this.processInstanceId=processInstanceId;
	}
	public HistoryProcessInstance execute(Context context) {
		return (HistoryProcessInstance)context.getSession().createCriteria(HistoryProcessInstance.class).add(Restrictions.eq("processInstanceId", processInstanceId)).uniqueResult();
	}
}
