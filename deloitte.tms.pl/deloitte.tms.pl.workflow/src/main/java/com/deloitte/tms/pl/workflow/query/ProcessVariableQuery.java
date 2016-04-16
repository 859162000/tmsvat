package com.deloitte.tms.pl.workflow.query;

import java.util.List;

import com.deloitte.tms.pl.workflow.model.variable.Variable;

/**
 * @author Jacky.gao
 * @since 2013年8月14日
 */
public interface ProcessVariableQuery extends Query<List<Variable>>{
	ProcessVariableQuery processInstanceId(long processInstanceId);
	ProcessVariableQuery rootprocessInstanceId(long rootProcessInstanceId);
	ProcessVariableQuery key(String key);
	ProcessVariableQuery page(int firstResult, int maxResults);
	ProcessVariableQuery addOrderAsc(String property);
	ProcessVariableQuery addOrderDesc(String property);
}
