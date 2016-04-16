package com.deloitte.tms.pl.workflow.command.impl;

import java.util.List;

import org.hibernate.criterion.Restrictions;

import com.deloitte.tms.pl.workflow.command.Command;
import com.deloitte.tms.pl.workflow.env.Context;
import com.deloitte.tms.pl.workflow.model.HistoryProcessInstance;

/**
 * @author Jacky.gao
 * @since 2013年9月12日
 */
public class GetListHistoryProcessInstancesCommand implements Command<List<HistoryProcessInstance>> {
	private long processId;
	public GetListHistoryProcessInstancesCommand(long processId){
		this.processId=processId;
	}
	@SuppressWarnings("unchecked")
	public List<HistoryProcessInstance> execute(Context context) {
		return context.getSession().createCriteria(HistoryProcessInstance.class).add(Restrictions.eq("processId", processId)).list();
	}
}
