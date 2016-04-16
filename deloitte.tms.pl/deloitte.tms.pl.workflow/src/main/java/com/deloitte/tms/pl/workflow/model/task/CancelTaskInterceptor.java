package com.deloitte.tms.pl.workflow.model.task;

import com.deloitte.tms.pl.workflow.env.Context;

/**
 * @author Jacky.gao
 * @since 2013年7月31日
 */
public interface CancelTaskInterceptor {
	void intercept(Context context,Task task);
}
