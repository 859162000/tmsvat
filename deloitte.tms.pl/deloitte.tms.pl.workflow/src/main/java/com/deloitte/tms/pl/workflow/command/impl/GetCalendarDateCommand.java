package com.deloitte.tms.pl.workflow.command.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.deloitte.tms.pl.workflow.command.Command;
import com.deloitte.tms.pl.workflow.env.Context;
import com.deloitte.tms.pl.workflow.model.calendar.CalendarDate;

/**
 * @author Jacky.gao
 * @since 2013年9月23日
 */
public class GetCalendarDateCommand implements Command<List<CalendarDate>> {
	private long calendarId;
	public GetCalendarDateCommand(long calendarId){
		this.calendarId=calendarId;
	}
	@SuppressWarnings("unchecked")
	public List<CalendarDate> execute(Context context) {
		Criteria dateCriteria=context.getSession().createCriteria(CalendarDate.class);
		dateCriteria.add(Restrictions.eq("calendarId", calendarId));
		return dateCriteria.list();
	}

}
