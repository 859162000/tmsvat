package com.deloitte.tms.pl.workflow.query.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.deloitte.tms.pl.workflow.command.CommandService;
import com.deloitte.tms.pl.workflow.command.impl.QueryCountCommand;
import com.deloitte.tms.pl.workflow.command.impl.QueryListCommand;
import com.deloitte.tms.pl.workflow.model.ProcessDefinition;
import com.deloitte.tms.pl.workflow.query.ProcessQuery;
import com.deloitte.tms.pl.workflow.query.QueryJob;
import com.deloitte.tms.pl.workflow.utils.EnvironmentUtils;

/**
 * @author Jacky.gao
 * @since 2013年8月14日
 */
public class ProcessQueryImpl implements ProcessQuery,QueryJob{
	private long id;
	private String name;
	private String categoryId;
	private int version;
	private int firstResult;
	private int maxResults;
	private Date createDateLessThen;
	private Date createDateLessThenOrEquals;
	private Date createDateGreaterThen;
	private Date createDateGreaterThenOrEquals;
	private List<String> ascOrders=new ArrayList<String>();
	private List<String> descOrders=new ArrayList<String>();
	private CommandService commandService;
	public ProcessQueryImpl(CommandService commandService){
		this.commandService=commandService;
	}
	public Criteria getCriteria(Session session,boolean queryCount) {
		Criteria criteria=session.createCriteria(ProcessDefinition.class);
		buildCriteria(criteria,queryCount);
		return criteria;
	}
	
	public List<ProcessDefinition> list() {
		return commandService.executeCommand(new QueryListCommand<List<ProcessDefinition>>(this));
	}

	public int count() {
		return commandService.executeCommand(new QueryCountCommand(this));
	}

	private void buildCriteria(Criteria criteria,boolean queryCount){
		if(!queryCount && firstResult>0){
			criteria.setFirstResult(firstResult);			
		}
		if(!queryCount && maxResults>0){
			criteria.setMaxResults(maxResults);			
		}
		if(id>0){
			criteria.add(Restrictions.eq("id", id));
		}
		if(StringUtils.isNotEmpty(name)){
			criteria.add(Restrictions.like("name", name));
		}
		if(createDateLessThen!=null){
			criteria.add(Restrictions.lt("createDate", createDateLessThen));
		}
		if(createDateGreaterThen!=null){
			criteria.add(Restrictions.gt("createDate", createDateGreaterThen));
		}
		if(createDateLessThenOrEquals!=null){
			criteria.add(Restrictions.le("createDate", createDateLessThenOrEquals));
		}
		if(createDateGreaterThenOrEquals!=null){
			criteria.add(Restrictions.ge("createDate", createDateGreaterThenOrEquals));
		}
		if(StringUtils.isNotEmpty(categoryId)){
			criteria.add(Restrictions.eq("categoryId", categoryId));
		}else{
			categoryId=EnvironmentUtils.getEnvironment().getCategoryId();
			if(StringUtils.isNotEmpty(categoryId)){
				criteria.add(Restrictions.eq("categoryId", categoryId));
			}
		}
		if(version>0){
			criteria.add(Restrictions.eq("version", Integer.valueOf(version)));
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
	
	public ProcessQuery createDateGreaterThen(Date date) {
		this.createDateGreaterThen=date;
		return this;
	}
	public ProcessQuery createDateGreaterThenOrEquals(Date date) {
		this.createDateGreaterThenOrEquals=date;
		return this;
	}
	public ProcessQuery createDateLessThen(Date date) {
		this.createDateLessThen=date;
		return this;
	}
	public ProcessQuery createDateLessThenOrEquals(Date date) {
		this.createDateLessThenOrEquals=date;
		return this;
	}
	
	public ProcessQuery addOrderAsc(String property){
		ascOrders.add(property);
		return this;
	}

	public ProcessQuery addOrderDesc(String property){
		descOrders.add(property);
		return this;
	}
	
	public ProcessQuery id(long id) {
		this.id=id;
		return this;
	}
	
	public ProcessQuery categoryId(String categoryId) {
		this.categoryId=categoryId;
		return this;
	}

	public ProcessQuery nameLike(String name) {
		this.name=name;
		return this;
	}

	public ProcessQuery version(int version) {
		this.version=version;
		return this;
	}

	public ProcessQuery page(int firstResult, int maxResults) {
		this.firstResult=firstResult;
		this.maxResults=maxResults;
		return this;
	}
}
