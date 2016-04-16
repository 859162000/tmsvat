package com.deloitte.tms.pl.workflow.command.impl;

import org.hibernate.Session;

import com.deloitte.tms.pl.workflow.command.Command;
import com.deloitte.tms.pl.workflow.deploy.ProcessDeployer;
import com.deloitte.tms.pl.workflow.deploy.parse.impl.ProcessParser;
import com.deloitte.tms.pl.workflow.env.Context;
import com.deloitte.tms.pl.workflow.model.Blob;
import com.deloitte.tms.pl.workflow.model.ProcessDefinition;

/**
 * @author Jacky.gao
 * @since 2013年9月6日
 */
public class GetProcessByKeyCommand implements Command<ProcessDefinition> {
	private String processKey;
	public GetProcessByKeyCommand(String processKey){
		this.processKey=processKey;
	}
	public ProcessDefinition execute(Context context) {
		Session session=context.getSession();
		ProcessDefinition p=(ProcessDefinition)session.createQuery("from "+ProcessDefinition.class.getName()+" where key=:key").setString("key", processKey).uniqueResult();
		if(p==null){
			return null;
		}else{
			return parseProcess(p.getId(),p.getName(),session);			
		}
	}
	
	private ProcessDefinition parseProcess(long processId,String processName,Session session){
		String hql="from "+Blob.class.getName()+" where processId=:processId and name=:name";
		Blob blob=(Blob)session.createQuery(hql).setLong("processId",processId).setString("name",processName+ProcessDeployer.PROCESS_EXTENSION_NAME).uniqueResult();
		try {
			ProcessDefinition process=ProcessParser.parseProcess(blob.getBlobValue(),processId,true);
			process.setId(processId);
			return process;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}
