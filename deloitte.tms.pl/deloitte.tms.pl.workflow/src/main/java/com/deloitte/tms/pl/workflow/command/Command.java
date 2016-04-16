package com.deloitte.tms.pl.workflow.command;

import com.deloitte.tms.pl.workflow.env.Context;


public interface Command<T> {
	T execute(Context context);
}
