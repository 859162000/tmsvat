package com.deloitte.tms.pl.workflow.command.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;

import com.deloitte.tms.pl.workflow.command.Command;
import com.deloitte.tms.pl.workflow.env.Context;
import com.deloitte.tms.pl.workflow.query.QueryJob;

/**
 * @author Jacky.gao
 * @since 2013年8月14日
 */
public class QueryCountCommand implements Command<Integer> {
	private QueryJob job;
	public QueryCountCommand(QueryJob job){
		this.job=job;
	}
	public Integer execute(Context context) {
		Criteria criteria=job.getCriteria(context.getSession(),true);
		Object obj=criteria.setProjection(Projections.rowCount()).uniqueResult();
		if(obj==null)return 0;
		if(obj instanceof Integer){
			return (Integer)obj;
		}else{
			return ((Long)obj).intValue();
		}
	}
}
