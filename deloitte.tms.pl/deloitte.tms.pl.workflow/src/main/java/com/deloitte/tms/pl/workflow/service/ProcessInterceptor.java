package com.deloitte.tms.pl.workflow.service;

import com.deloitte.tms.pl.workflow.model.ProcessDefinition;

/**
 * @author Jacky.gao
 * @since 2013年11月28日
 */
public interface ProcessInterceptor {
	void updateProcess(ProcessDefinition process);
	void deleteProcess(ProcessDefinition process);
}
