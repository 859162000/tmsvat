package com.deloitte.tms.pl.workflow.query;

import java.util.Date;
import java.util.List;

import com.deloitte.tms.pl.workflow.model.ProcessDefinition;

/**
 * @author Jacky.gao
 * @since 2013年8月14日
 */
public interface ProcessQuery extends Query<List<ProcessDefinition>>{
	ProcessQuery id(long id);
	ProcessQuery categoryId(String id);
	ProcessQuery createDateLessThen(Date date);
	ProcessQuery createDateLessThenOrEquals(Date date);
	ProcessQuery createDateGreaterThen(Date date);
	ProcessQuery createDateGreaterThenOrEquals(Date date);
	ProcessQuery nameLike(String name);
	ProcessQuery version(int version);
	ProcessQuery page(int firstResult, int maxResults);
	ProcessQuery addOrderAsc(String property);
	ProcessQuery addOrderDesc(String property);
}
