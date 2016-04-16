package com.deloitte.tms.pl.workflow.query;

import org.hibernate.Criteria;
import org.hibernate.Session;

/**
 * @author Jacky.gao
 * @since 2013年8月14日
 */
public interface QueryJob {
	Criteria getCriteria(Session session,boolean queryCount);
}
