package com.ling2.test.meeting.service;

import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.workflow.env.Context;
import com.deloitte.tms.pl.workflow.model.ProcessInstance;
import com.deloitte.tms.pl.workflow.process.handler.ActionHandler;
@Component
public class Meetingbegin implements ActionHandler{

	@Override
	public void handle(ProcessInstance processInstance, Context context) {
		
	}

}
