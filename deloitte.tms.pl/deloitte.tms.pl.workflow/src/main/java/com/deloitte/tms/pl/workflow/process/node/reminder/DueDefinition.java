package com.deloitte.tms.pl.workflow.process.node.reminder;

import java.util.List;

/**
 * @author Jacky.gao
 * @since 2013年8月20日
 */
public class DueDefinition implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private int day;
	private int hour;
	private int minute;
	private List<CalendarInfo> calendarProviderInfos;
	private Reminder reminder;
	private DueAction dueAction;
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public int getHour() {
		return hour;
	}
	public void setHour(int hour) {
		this.hour = hour;
	}
	public int getMinute() {
		return minute;
	}
	public void setMinute(int minute) {
		this.minute = minute;
	}
	public List<CalendarInfo> getCalendarProviderInfos() {
		return calendarProviderInfos;
	}
	public void setCalendarProviderInfos(
			List<CalendarInfo> calendarProviderInfos) {
		this.calendarProviderInfos = calendarProviderInfos;
	}
	public Reminder getReminder() {
		return reminder;
	}
	public void setReminder(Reminder reminder) {
		this.reminder = reminder;
	}
	public DueAction getDueAction() {
		return dueAction;
	}
	public void setDueAction(DueAction dueAction) {
		this.dueAction = dueAction;
	}
}
