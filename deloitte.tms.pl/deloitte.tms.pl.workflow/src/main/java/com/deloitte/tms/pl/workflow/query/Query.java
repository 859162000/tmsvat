package com.deloitte.tms.pl.workflow.query;


/**
 * @author Jacky.gao
 * @since 2013年8月14日
 */
public interface Query<T> {
	T list();
	int count();
}
