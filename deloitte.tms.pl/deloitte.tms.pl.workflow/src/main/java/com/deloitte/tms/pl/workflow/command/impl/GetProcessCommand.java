package com.deloitte.tms.pl.workflow.command.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.apache.commons.lang.StringUtils;

import com.deloitte.tms.pl.workflow.command.Command;
import com.deloitte.tms.pl.workflow.deploy.ProcessDeployer;
import com.deloitte.tms.pl.workflow.deploy.parse.impl.ProcessParser;
import com.deloitte.tms.pl.workflow.env.Context;
import com.deloitte.tms.pl.workflow.model.Blob;
import com.deloitte.tms.pl.workflow.model.ProcessDefinition;
/**
 * @author Jacky.gao
 * @since 2013年8月2日
 */
public class GetProcessCommand implements Command<ProcessDefinition> {
	private long processId;
	private String processName;
	private int version;
	public GetProcessCommand(long processId,String processName,int version){
		this.processId=processId;
		this.processName=processName;
		this.version=version;
	}
	@SuppressWarnings("unchecked")
	public ProcessDefinition execute(Context context) {
		Session session=context.getSession();
		if(processId>0){
			ProcessDefinition p=(ProcessDefinition)session.get(ProcessDefinition.class, processId);
			return parseProcess(p.getId(),p.getVersion(),p.getName(),session);
		}else if(StringUtils.isNotEmpty(processName)){
			Criteria  criteria=session.createCriteria(ProcessDefinition.class).add(Restrictions.eq("name", processName)).addOrder(Order.desc("version"));
			if(version>0){
				criteria.add(Restrictions.eq("version", version));
				List<ProcessDefinition> processes=criteria.list();
				if(processes.size()>0){
					ProcessDefinition p=processes.get(0);
					return parseProcess(p.getId(),p.getVersion(),p.getName(),session);
				}
			}else{
				List<ProcessDefinition> processes=criteria.list();
				for(ProcessDefinition process:processes){
					Date effectDate=process.getEffectDate();
					if(effectDate==null){
						return parseProcess(process.getId(),process.getVersion(),process.getName(),session);						
					}else{
						if((new Date()).getTime()>effectDate.getTime()){
							return parseProcess(process.getId(),process.getVersion(),process.getName(),session);													
						}
					}
				}
			}
		}
		return null;
	}
	
	private ProcessDefinition parseProcess(long processId,int version,String processName,Session session){
		String hql="from "+Blob.class.getName()+" where processId=:processId and name=:name";
		Blob blob=(Blob)session.createQuery(hql).setLong("processId",processId).setString("name",processName+ProcessDeployer.PROCESS_EXTENSION_NAME).uniqueResult();
		try {
			ProcessDefinition process=ProcessParser.parseProcess(blob.getBlobValue(),processId,true);
			process.setId(processId);
			process.setVersion(version);
			return process;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}
