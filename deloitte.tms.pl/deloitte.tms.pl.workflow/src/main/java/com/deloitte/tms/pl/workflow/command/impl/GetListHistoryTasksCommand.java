package com.deloitte.tms.pl.workflow.command.impl;

import java.util.List;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.deloitte.tms.pl.workflow.command.Command;
import com.deloitte.tms.pl.workflow.env.Context;
import com.deloitte.tms.pl.workflow.model.HistoryTask;

/**
 * @author Jacky.gao
 * @since 2013年9月12日
 */
public class GetListHistoryTasksCommand implements Command<List<HistoryTask>> {
	private long processInstanceId;

	public GetListHistoryTasksCommand(long processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	@SuppressWarnings("unchecked")
	public List<HistoryTask> execute(Context context) {
		return context.getSession().createCriteria(HistoryTask.class)
				.add(Restrictions.eq("processInstanceId", processInstanceId))
				.addOrder(Order.desc("endDate")).list();
	}
}
