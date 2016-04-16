package com.deloitte.tms.pl.workflow.command.impl;

import java.util.List;

import org.hibernate.Criteria;

import com.deloitte.tms.pl.workflow.command.Command;
import com.deloitte.tms.pl.workflow.env.Context;
import com.deloitte.tms.pl.workflow.model.variable.BlobVariable;
import com.deloitte.tms.pl.workflow.model.variable.TextVariable;
import com.deloitte.tms.pl.workflow.query.QueryJob;

/**
 * @author Jacky.gao
 * @since 2013年8月14日
 */
public class QueryListCommand<T> implements Command<T> {
	private QueryJob job;
	public QueryListCommand(QueryJob job){
		this.job=job;
	}
	@SuppressWarnings("unchecked")
	public T execute(Context context) {
		Criteria criteria=job.getCriteria(context.getSession(),false);
		List<Object> list=criteria.list();
		for(Object obj:list){
			if(obj instanceof BlobVariable){
				((BlobVariable)obj).initValue(context);
			}
			if(obj instanceof TextVariable){
				((TextVariable)obj).initValue(context);
			}
		}
		return (T)list;
	}
}
