package com.deloitte.tms.pl.workflow.query.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.deloitte.tms.pl.workflow.command.CommandService;
import com.deloitte.tms.pl.workflow.command.impl.QueryCountCommand;
import com.deloitte.tms.pl.workflow.command.impl.QueryListCommand;
import com.deloitte.tms.pl.workflow.model.HistoryVariable;
import com.deloitte.tms.pl.workflow.query.HistoryProcessVariableQuery;
import com.deloitte.tms.pl.workflow.query.QueryJob;

/**
 * @author Jacky.gao
 * @since 2013年11月23日
 */
public class HistoryProcessVariableQueryImpl implements QueryJob,HistoryProcessVariableQuery {
	private long historyProcessInstanceId;
	private String key;
	private int firstResult;
	private int maxResults;
	private List<String> ascOrders=new ArrayList<String>();
	private List<String> descOrders=new ArrayList<String>();
	private CommandService commandService;
	
	public HistoryProcessVariableQueryImpl(CommandService commandService){
		this.commandService=commandService;
	}
	public Criteria getCriteria(Session session,boolean queryCount) {
		Criteria criteria=session.createCriteria(HistoryVariable.class);
		buildCriteria(criteria,queryCount);
		return criteria;
	}

	private void buildCriteria(Criteria criteria,boolean queryCount){
		if(!queryCount && firstResult>0){
			criteria.setFirstResult(firstResult);			
		}
		if(!queryCount && maxResults>0){
			criteria.setMaxResults(maxResults);			
		}
		if(historyProcessInstanceId>0){
			criteria.add(Restrictions.eq("historyProcessInstanceId",historyProcessInstanceId));
		}
		if(StringUtils.isNotEmpty(key)){
			criteria.add(Restrictions.eq("key", key));
		}
		
		if(!queryCount){
			for(String ascProperty:ascOrders){
				criteria.addOrder(Order.asc(ascProperty));
			}
			for(String descProperty:descOrders){
				criteria.addOrder(Order.desc(descProperty));
			}
		}
	}
	
	public List<HistoryVariable> list() {
		return commandService.executeCommand(new QueryListCommand<List<HistoryVariable>>(this));
	}

	public int count() {
		return commandService.executeCommand(new QueryCountCommand(this));
	}

	public HistoryProcessVariableQuery historyProcessInstanceId(long historyProcessInstanceId) {
		this.historyProcessInstanceId=historyProcessInstanceId;
		return this;
	}

	public HistoryProcessVariableQuery key(String key) {
		this.key=key;
		return this;
	}

	public HistoryProcessVariableQuery page(int firstResult, int maxResults) {
		this.firstResult=firstResult;
		this.maxResults=maxResults;
		return this;
	}

	public HistoryProcessVariableQuery addOrderAsc(String property) {
		ascOrders.add(property);
		return this;
	}

	public HistoryProcessVariableQuery addOrderDesc(String property) {
		descOrders.add(property);
		return this;
	}

}
