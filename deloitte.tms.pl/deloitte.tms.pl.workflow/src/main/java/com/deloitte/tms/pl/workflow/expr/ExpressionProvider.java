package com.deloitte.tms.pl.workflow.expr;

import java.util.Map;

import com.deloitte.tms.pl.workflow.model.ProcessInstance;

/**
 * @author Jacky.gao
 * @since 2013年8月8日
 */
public interface ExpressionProvider {
	Map<String,Object> getData(ProcessInstance processInstance);
	boolean support(ProcessInstance processInstance);
}
