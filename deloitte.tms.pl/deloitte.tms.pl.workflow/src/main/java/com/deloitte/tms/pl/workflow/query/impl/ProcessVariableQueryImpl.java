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
import com.deloitte.tms.pl.workflow.model.variable.Variable;
import com.deloitte.tms.pl.workflow.query.ProcessVariableQuery;
import com.deloitte.tms.pl.workflow.query.QueryJob;

/**
 * @author Jacky.gao
 * @since 2013年8月14日
 */
public class ProcessVariableQueryImpl implements ProcessVariableQuery, QueryJob {
	private long processInstanceId;
	private long rootProcessInstanceId;
	private String key;
	private int firstResult;
	private int maxResults;
	private List<String> ascOrders=new ArrayList<String>();
	private List<String> descOrders=new ArrayList<String>();
	private CommandService commandService;
	public ProcessVariableQueryImpl(CommandService commandService){
		this.commandService=commandService;
	}
	public List<Variable> list() {
		return commandService.executeCommand(new QueryListCommand<List<Variable>>(this));
	}
	
	public int count() {
		return commandService.executeCommand(new QueryCountCommand(this));
	}
	public Criteria getCriteria(Session session,boolean queryCount) {
		Criteria criteria=session.createCriteria(Variable.class);
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
		if(processInstanceId>0){
			criteria.add(Restrictions.eq("processInstanceId", processInstanceId));
		}
		if(rootProcessInstanceId>0){
			criteria.add(Restrictions.eq("rootProcessInstanceId", rootProcessInstanceId));
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
	
	
	public ProcessVariableQuery addOrderAsc(String property){
		ascOrders.add(property);
		return this;
	}

	public ProcessVariableQuery addOrderDesc(String property){
		descOrders.add(property);
		return this;
	}
	
	public ProcessVariableQuery processInstanceId(long processInstanceId) {
		this.processInstanceId=processInstanceId;
		return this;
	}

	public ProcessVariableQuery rootprocessInstanceId(long rootProcessInstanceId) {
		this.rootProcessInstanceId=rootProcessInstanceId;
		return this;
	}

	public ProcessVariableQuery key(String key) {
		this.key=key;
		return this;
	}

	public ProcessVariableQuery page(int firstResult, int maxResults) {
		this.firstResult=firstResult;
		this.maxResults=maxResults;
		return this;
	}


}
