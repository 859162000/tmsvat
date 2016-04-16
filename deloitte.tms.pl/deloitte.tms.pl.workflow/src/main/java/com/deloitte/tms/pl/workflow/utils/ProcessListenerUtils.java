package com.deloitte.tms.pl.workflow.utils;

import com.deloitte.tms.pl.workflow.env.Context;
import com.deloitte.tms.pl.workflow.model.ProcessInstance;
import com.deloitte.tms.pl.workflow.process.listener.ProcessListener;

/**
 * @author Jacky.gao
 * @since 2013年11月18日
 */
public class ProcessListenerUtils {
	public static void fireProcessStartListers(ProcessInstance processInstance,Context context){
		fireProcessListers(processInstance,context,true);
	}
	public static void fireProcessEndListers(ProcessInstance processInstance,Context context){
		fireProcessListers(processInstance,context,false);		
	}
	private static void fireProcessListers(ProcessInstance processInstance,Context context,boolean isStart){
		for(ProcessListener listener:context.getApplicationContext().getBeansOfType(ProcessListener.class).values()){
			if(isStart){
				listener.processStart(processInstance, context);
			}else{
				listener.processEnd(processInstance, context);				
			}
		}
	}
}
