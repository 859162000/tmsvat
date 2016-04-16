package com.deloitte.tms.pl.workflow.command.impl;

import com.deloitte.tms.pl.workflow.command.Command;
import com.deloitte.tms.pl.workflow.env.Context;

/**
 * @author Jacky.gao
 * @since 2013年10月6日
 */
public class GetExpressionValueCommand implements Command<Object> {
	private long processInstanceId;
	private String key;
	public GetExpressionValueCommand(long processInstanceId,String key){
		this.processInstanceId=processInstanceId;
		this.key=key;
	}
	public Object execute(Context context) {
		return context.getExpressionContext().eval(processInstanceId, "${"+key+"}");
	}
}
