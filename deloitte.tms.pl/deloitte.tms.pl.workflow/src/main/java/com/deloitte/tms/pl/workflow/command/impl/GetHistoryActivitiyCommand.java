package com.deloitte.tms.pl.workflow.command.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.deloitte.tms.pl.workflow.command.Command;
import com.deloitte.tms.pl.workflow.env.Context;
import com.deloitte.tms.pl.workflow.model.HistoryActivity;

/**
 * @author Jacky.gao
 * @since 2013年9月12日
 */
public class GetHistoryActivitiyCommand implements Command<List<HistoryActivity>> {
	private long instanceId;
	private boolean isProcessInstanceId;
	public GetHistoryActivitiyCommand(long instanceId,boolean isProcessInstanceId){
		this.instanceId=instanceId;
		this.isProcessInstanceId=isProcessInstanceId;
	}
	@SuppressWarnings("unchecked")
	public List<HistoryActivity> execute(Context context) {
		Criteria criteria=context.getSession().createCriteria(HistoryActivity.class);
		if(isProcessInstanceId){
			criteria.add(Restrictions.eq("rootProcessInstanceId", instanceId));
		}else{
			criteria.add(Restrictions.eq("historyProcessInstanceId", instanceId));
		}
		criteria.addOrder(Order.desc("endDate"));
		return criteria.list();
	}
}
