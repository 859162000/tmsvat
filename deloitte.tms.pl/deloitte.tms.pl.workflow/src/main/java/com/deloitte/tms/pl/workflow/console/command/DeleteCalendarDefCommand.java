package com.deloitte.tms.pl.workflow.console.command;

import org.hibernate.Session;

import com.deloitte.tms.pl.workflow.command.Command;
import com.deloitte.tms.pl.workflow.env.Context;
import com.deloitte.tms.pl.workflow.model.calendar.CalendarDate;
import com.deloitte.tms.pl.workflow.model.calendar.CalendarDef;

/**
 * 
 * @author Jake.Wang@bstek.com
 * @since Sep 23, 2013
 * 
 */
public class DeleteCalendarDefCommand implements Command<CalendarDef> {
	private CalendarDef def;

	public DeleteCalendarDefCommand(CalendarDef def) {
		this.def = def;
	}

	public CalendarDef execute(Context context) {
		Session session  =context.getSession();
		session.createQuery("delete " + CalendarDate.class.getName() + " where calendarId=:calendarId").setLong("calendarId", this.def.getId()).executeUpdate();
		session.delete(this.def);
		return null;
	}
}
