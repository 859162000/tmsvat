package com.deloitte.tms.pl.workflow.expr;

import java.util.Map;

import org.apache.commons.jexl2.MapContext;

import com.deloitte.tms.pl.workflow.model.ProcessInstance;

/**
 * @author Jacky.gao
 * @since 2013年8月8日
 */
public interface ExpressionContext {
	public static final String BEAN_ID="uflo.expressionContext";
	MapContext createContext(ProcessInstance processInstance,Map<String, Object> variables);
	void addContextVariables(ProcessInstance processInstance,Map<String, Object> variables);
	boolean removeContext(ProcessInstance processInstance);
	void moveContextToParent(ProcessInstance processInstance);
	void removeContextVariables(long processInstanceId,String key);
	Object eval(ProcessInstance processInstance,String expression);
	Object eval(long processInstanceId, String expression);
	String evalString(ProcessInstance processInstance,String str);
}