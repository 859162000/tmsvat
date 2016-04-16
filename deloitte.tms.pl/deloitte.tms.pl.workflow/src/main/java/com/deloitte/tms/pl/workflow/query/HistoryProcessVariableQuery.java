package com.deloitte.tms.pl.workflow.query;

import java.util.List;

import com.deloitte.tms.pl.workflow.model.HistoryVariable;

/**
 * @author Jacky.gao
 * @since 2013年8月14日
 */
public interface HistoryProcessVariableQuery extends Query<List<HistoryVariable>>{
	HistoryProcessVariableQuery historyProcessInstanceId(long historyProcessInstanceId);
	HistoryProcessVariableQuery key(String key);
	HistoryProcessVariableQuery page(int firstResult, int maxResults);
	HistoryProcessVariableQuery addOrderAsc(String property);
	HistoryProcessVariableQuery addOrderDesc(String property);
}
