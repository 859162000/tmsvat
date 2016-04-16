package com.deloitte.tms.pl.workflow.service;

import java.util.Collection;

import org.quartz.Calendar;

import com.deloitte.tms.pl.workflow.model.calendar.CalendarDate;
import com.deloitte.tms.pl.workflow.model.calendar.CalendarDef;

/**
 * @author Jacky.gao
 * @since 2013年9月23日
 */
public interface CalendarService {
	public static final String BEAN_ID="uflo.calendarService";
	Collection<CalendarDef> getAllCalendarDefs();
	CalendarDef getCalendarDef(long calendarId);
	Collection<CalendarDate> getCalendarDate(long calendarId);
	Calendar getCalendar(long calendarId);
}
