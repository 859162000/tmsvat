package com.deloitte.tms.pl.workflow.command;


/**
 * @author Jacky.gao
 * @since 2013年8月23日
 */
public interface CommandService {
	public static final String BEAN_ID="uflo.commandService";
	<T> T executeCommand(Command<T> command);
	<T> T executeCommandInNewTransaction(Command<T> command);
}
