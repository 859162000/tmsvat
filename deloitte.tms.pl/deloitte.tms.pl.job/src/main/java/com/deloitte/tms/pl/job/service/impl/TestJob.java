package com.deloitte.tms.pl.job.service.impl;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.job.task.JobTest;

@Component(value="testJob")
public class TestJob implements Job,JobTest{

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		execute();
	}

	@Override
	public void execute() {
		System.out.println("test");
	}

}
