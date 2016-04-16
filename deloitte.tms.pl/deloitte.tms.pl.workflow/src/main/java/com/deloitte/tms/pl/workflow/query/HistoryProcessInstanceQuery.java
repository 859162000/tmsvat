package com.deloitte.tms.pl.workflow.query;

import java.util.Date;
import java.util.List;

import com.deloitte.tms.pl.workflow.model.HistoryProcessInstance;

/**
 * @author Jacky.gao
 * @since 2013年9月17日
 */
public interface HistoryProcessInstanceQuery extends Query<List<HistoryProcessInstance>>{
	HistoryProcessInstanceQuery processId(long processId);

	HistoryProcessInstanceQuery page(int firstResult, int maxResults);

	HistoryProcessInstanceQuery addOrderAsc(String property);

	HistoryProcessInstanceQuery addOrderDesc(String property);

	HistoryProcessInstanceQuery createDateLessThen(Date date);

	HistoryProcessInstanceQuery createDateLessThenOrEquals(Date date);

	HistoryProcessInstanceQuery createDateGreaterThen(Date date);

	HistoryProcessInstanceQuery createDateGreaterThenOrEquals(Date date);
	
	HistoryProcessInstanceQuery businessId(String businessId);
	
	HistoryProcessInstanceQuery tag(String businessId);
	
	HistoryProcessInstanceQuery promoter(String promoter);
}
