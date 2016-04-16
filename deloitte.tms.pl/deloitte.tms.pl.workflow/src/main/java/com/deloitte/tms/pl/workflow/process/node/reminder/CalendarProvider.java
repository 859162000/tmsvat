package com.deloitte.tms.pl.workflow.process.node.reminder;

import java.util.List;

import org.quartz.Calendar;

/**
 * @author Jacky.gao
 * @since 2013年8月20日
 */
public interface CalendarProvider {
	Calendar getCalendar(String calendarId);
	List<CalendarInfo> getCalendarInfos();
}
