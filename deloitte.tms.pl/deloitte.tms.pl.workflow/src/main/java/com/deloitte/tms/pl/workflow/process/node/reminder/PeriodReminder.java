package com.deloitte.tms.pl.workflow.process.node.reminder;

import java.util.List;

import com.deloitte.tms.pl.workflow.model.task.DateUnit;

/**
 * @author Jacky.gao
 * @since 2013年8月20日
 */
public class PeriodReminder extends Reminder {
	private static final long serialVersionUID = 1L;
	private int repeat;
	private DateUnit unit;
	private List<CalendarInfo> calendarProviderInfos;
	public int getRepeat() {
		return repeat;
	}
	public void setRepeat(int repeat) {
		this.repeat = repeat;
	}
	public DateUnit getUnit() {
		return unit;
	}
	public void setUnit(DateUnit unit) {
		this.unit = unit;
	}
	public List<CalendarInfo> getCalendarProviderInfos() {
		return calendarProviderInfos;
	}
	public void setCalendarProviderInfos(
			List<CalendarInfo> calendarProviderInfos) {
		this.calendarProviderInfos = calendarProviderInfos;
	}
}
