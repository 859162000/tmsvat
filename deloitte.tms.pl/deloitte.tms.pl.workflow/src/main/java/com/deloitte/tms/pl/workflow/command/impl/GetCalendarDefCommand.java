package com.deloitte.tms.pl.workflow.command.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.deloitte.tms.pl.workflow.command.Command;
import com.deloitte.tms.pl.workflow.env.Context;
import com.deloitte.tms.pl.workflow.model.calendar.CalendarDef;

/**
 * @author Jacky.gao
 * @since 2013年9月23日
 */
public class GetCalendarDefCommand implements Command<CalendarDef> {
	private long calendarId;
	public GetCalendarDefCommand(long calendarId){
		this.calendarId=calendarId;
	}
	public CalendarDef execute(Context context) {
		Criteria criteria=context.getSession().createCriteria(CalendarDef.class);
		criteria.add(Restrictions.eq("id", calendarId));
		CalendarDef def=(CalendarDef)criteria.uniqueResult();
		return def;
	}

}
