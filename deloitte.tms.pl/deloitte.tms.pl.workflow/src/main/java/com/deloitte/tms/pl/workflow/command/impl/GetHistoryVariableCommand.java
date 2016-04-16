package com.deloitte.tms.pl.workflow.command.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.deloitte.tms.pl.workflow.command.Command;
import com.deloitte.tms.pl.workflow.env.Context;
import com.deloitte.tms.pl.workflow.model.HistoryVariable;

/**
 * @author Jacky.gao
 * @since 2013年9月28日
 */
public class GetHistoryVariableCommand implements Command<HistoryVariable> {
	private long historyProcessInstanceId;
	private String key;
	public GetHistoryVariableCommand(long historyProcessInstanceId,String key){
		this.historyProcessInstanceId=historyProcessInstanceId;
		this.key=key;
	}
	public HistoryVariable execute(Context context) {
		Criteria criteria=context.getSession().createCriteria(HistoryVariable.class);
		criteria.add(Restrictions.eq("historyProcessInstanceId", historyProcessInstanceId));
		criteria.add(Restrictions.eq("key", key));
		HistoryVariable hisVar=(HistoryVariable)criteria.uniqueResult();
		if(hisVar!=null){
			hisVar.init(context);			
		}
		return hisVar;
	}
}
