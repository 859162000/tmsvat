package com.deloitte.tms.pl.workflow.command.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.deloitte.tms.pl.workflow.command.Command;
import com.deloitte.tms.pl.workflow.env.Context;
import com.deloitte.tms.pl.workflow.model.ProcessDefinition;

/**
 * @author Jacky.gao
 * @since 2013年8月3日
 */
public class DeployProcessCommand implements Command<ProcessDefinition> {
	private ProcessDefinition process;
	private boolean update=false;
	public DeployProcessCommand(ProcessDefinition process,boolean update){
		this.process=process;
		this.update=update;
	}
	
	@SuppressWarnings("unchecked")
	public ProcessDefinition execute(Context context) {
		Session session=context.getSession();
		String key=process.getKey();
		if(StringUtils.isNotEmpty(key)){
			int size=session.createCriteria(ProcessDefinition.class).add(Restrictions.eq("key", key)).list().size();
			if(size>0){
				throw new IllegalArgumentException("Process definition "+process.getName()+"'s key "+key+" is not the only one!");
			}
		}
		int newVersion=1;
		if(!update){
			List<ProcessDefinition> processes=session.createCriteria(ProcessDefinition.class).add(Restrictions.eq("name",process.getName())).addOrder(Order.desc("version")).list();
			if(processes.size()>0){
				newVersion=processes.get(0).getVersion()+1;
				process.setVersion(newVersion);
			}else{
				process.setVersion(newVersion);
			}
		}
		if(StringUtils.isEmpty(key)){
			key=process.getName()+"-"+newVersion;
			process.setKey(key);
		}
		if(update){
			session.update(process);
		}else{
			session.save(process);			
		}
		return process;
	}

}
