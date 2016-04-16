package com.deloitte.tms.pl.workflow.console.command;

import com.deloitte.tms.pl.workflow.command.Command;
import com.deloitte.tms.pl.workflow.env.Context;
import com.deloitte.tms.pl.workflow.model.calendar.CalendarDate;

/**
 * 
 * @author Jake.Wang@bstek.com
 * @since Sep 23, 2013
 * 
 */
public class DeleteCalendarDateCommand implements Command<CalendarDate> {
	private CalendarDate date;

	public DeleteCalendarDateCommand(CalendarDate date) {
		this.date = date;
	}

	public CalendarDate execute(Context context) {
		context.getSession().delete(this.date);
		return null;
	}
}
