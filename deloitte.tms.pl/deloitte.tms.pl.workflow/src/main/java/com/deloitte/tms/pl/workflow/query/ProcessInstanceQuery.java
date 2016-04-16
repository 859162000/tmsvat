package com.deloitte.tms.pl.workflow.query;

import java.util.Date;
import java.util.List;

import com.deloitte.tms.pl.workflow.model.ProcessInstance;

/**
 * @author Jacky.gao
 * @since 2013年8月14日
 */
public interface ProcessInstanceQuery extends Query<List<ProcessInstance>>{

	ProcessInstanceQuery processId(long processId);
	
	ProcessInstanceQuery parentId(long parentId);
	
	ProcessInstanceQuery rootId(long rootId);

	ProcessInstanceQuery page(int firstResult, int maxResults);

	ProcessInstanceQuery businessId(String businessId);
	
	ProcessInstanceQuery promoter(String businessId);
	
	ProcessInstanceQuery addOrderAsc(String property);

	ProcessInstanceQuery addOrderDesc(String property);

	ProcessInstanceQuery createDateLessThen(Date date);

	ProcessInstanceQuery createDateLessThenOrEquals(Date date);

	ProcessInstanceQuery createDateGreaterThen(Date date);

	ProcessInstanceQuery createDateGreaterThenOrEquals(Date date);

}