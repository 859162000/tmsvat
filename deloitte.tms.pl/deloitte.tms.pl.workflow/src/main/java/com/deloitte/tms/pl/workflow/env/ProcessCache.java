package com.deloitte.tms.pl.workflow.env;

/**
 * @author Jacky.gao
 * @since 2013年9月17日
 */
public interface ProcessCache {
	public static final String BEAN_ID="uflo.processCache";
	public static final String PROCESS_KEY="process_definition_key";
	public static final String CONTEXT_KEY="process_context_key";
	
	void store(String key,Object obj);
	Object retrive(String key);
}
