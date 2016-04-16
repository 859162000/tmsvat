package com.ling2.test.meeting.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.workflow.env.Context;
import com.deloitte.tms.pl.workflow.model.ProcessInstance;
import com.deloitte.tms.pl.workflow.process.handler.ForeachHandler;
@Component("meetingpersons")
public class Meetingpersons implements ForeachHandler{

	@Override
	public Collection<Object> handle(ProcessInstance processInstance,
			Context context) {
		Collection<Object> result=new ArrayList<Object>();
		result.add("11111");
		result.add("222222");
		return result;
	}

}
