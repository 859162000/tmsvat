package com.deloitte.tms.pl.workflow.process.flow;



/**
 * @author Jacky.gao
 * @since 2013年8月19日
 */
public interface SequenceFlow {

	String getToNode();

	String getExpression();
	
	String getHandlerBean();

	ConditionType getConditionType();

	String getName();
}