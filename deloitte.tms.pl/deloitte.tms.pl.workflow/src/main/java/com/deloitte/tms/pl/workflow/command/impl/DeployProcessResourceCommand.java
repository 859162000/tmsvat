package com.deloitte.tms.pl.workflow.command.impl;

import org.hibernate.Query;
import org.hibernate.Session;

import com.deloitte.tms.pl.workflow.command.Command;
import com.deloitte.tms.pl.workflow.env.Context;
import com.deloitte.tms.pl.workflow.model.Blob;
import com.deloitte.tms.pl.workflow.utils.IDGenerator;

/**
 * @author Jacky.gao
 * @since 2013年8月4日
 */
public class DeployProcessResourceCommand implements Command<Blob> {
	private byte[] processRes;
	private String name;
	private long processId;
	private boolean update=false;
	public DeployProcessResourceCommand(byte[] processRes,String name,long processId,boolean update){
		this.processRes=processRes;
		this.name=name;
		this.processId=processId;
		this.update=update;
	}
	public Blob execute(Context context) {
		Session session=context.getSession();
		if(update){
			Query query=session.createQuery("delete from "+Blob.class.getName()+" where processId=:processId");
			query.setLong("processId", processId);
			query.executeUpdate();
		}
		Blob lob=new Blob();
		lob.setId(IDGenerator.getInstance().nextId());
		lob.setBlobValue(processRes);
		lob.setName(name);
		lob.setProcessId(processId);
		session.save(lob);
		return lob;
	}

}
