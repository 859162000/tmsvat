package com.deloitte.tms.pl.workflow.command.impl;

import java.util.List;

import org.hibernate.Session;

import com.deloitte.tms.pl.workflow.command.Command;
import com.deloitte.tms.pl.workflow.env.Context;
import com.deloitte.tms.pl.workflow.model.ContextProperty;

/**
 * @author Jacky.gao
 * @since 2013年7月30日
 */
public class AcquireDbidCommand implements Command<Long>{
	private static final String ID_KEY="dbid";
	private int blockSize;
	public AcquireDbidCommand(int blockSize){
		this.blockSize=blockSize;
	}
	@SuppressWarnings("unchecked")
	public Long execute(Context context) {
		long nextId=0;
		Session session=context.getSession();
		List<ContextProperty> list=session.createQuery("from "+ContextProperty.class.getName()+" where key=:key").setString("key", ID_KEY).list();
		if(list.size()>0){
			ContextProperty prop=list.get(0);
			nextId=Long.valueOf(prop.getValue());
			prop.setValue(String.valueOf(nextId+blockSize));
			session.update(prop);
		}else{
			ContextProperty prop=new ContextProperty();
			prop.setKey(ID_KEY);
			prop.setValue(String.valueOf(blockSize));
			session.save(prop);
		}
		return nextId+1;
	}
}
