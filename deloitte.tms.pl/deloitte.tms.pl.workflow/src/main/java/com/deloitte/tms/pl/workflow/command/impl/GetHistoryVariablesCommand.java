package com.deloitte.tms.pl.workflow.command.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.deloitte.tms.pl.workflow.command.Command;
import com.deloitte.tms.pl.workflow.env.Context;
import com.deloitte.tms.pl.workflow.model.HistoryVariable;

/**
 * @author Jacky.gao
 * @since 2013年9月28日
 */
public class GetHistoryVariablesCommand implements Command<List<HistoryVariable>> {
	private long historyProcessInstanceId;
	public GetHistoryVariablesCommand(long historyProcessInstanceId){
		this.historyProcessInstanceId=historyProcessInstanceId;
	}
	@SuppressWarnings("unchecked")
	public List<HistoryVariable> execute(Context context) {
		Criteria criteria=context.getSession().createCriteria(HistoryVariable.class);
		criteria.add(Restrictions.eq("historyProcessInstanceId", historyProcessInstanceId));
		List<HistoryVariable> vars=criteria.list();
		for(HistoryVariable var:vars){
			var.init(context);
		}
		return vars;
	}
}
