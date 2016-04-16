package com.deloitte.tms.pl.workflow.process.node.reminder;

import java.util.List;

/**
 * @author Jacky.gao
 * @since 2014年7月7日
 */
public class DueAction {
	private int day;
	private int hour;
	private int minute;
	private String handlerBean;
	private List<CalendarInfo> calendarInfos;
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
	public String getHandlerBean() {
		return handlerBean;
	}
	public void setHandlerBean(String handlerBean) {
		this.handlerBean = handlerBean;
	}
	public List<CalendarInfo> getCalendarInfos() {
		return calendarInfos;
	}
	public void setCalendarInfos(List<CalendarInfo> calendarInfos) {
		this.calendarInfos = calendarInfos;
	}
}
