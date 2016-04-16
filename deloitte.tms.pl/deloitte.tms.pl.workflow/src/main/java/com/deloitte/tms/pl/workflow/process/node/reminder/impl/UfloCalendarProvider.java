package com.deloitte.tms.pl.workflow.process.node.reminder.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.quartz.Calendar;
import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.workflow.model.calendar.CalendarDef;
import com.deloitte.tms.pl.workflow.process.node.reminder.CalendarInfo;
import com.deloitte.tms.pl.workflow.process.node.reminder.CalendarProvider;
import com.deloitte.tms.pl.workflow.service.CalendarService;

/**
 * @author Jacky.gao
 * @since 2013年9月23日
 */
@Component
public class UfloCalendarProvider implements CalendarProvider {
	@Resource
	private CalendarService calendarService;
	public Calendar getCalendar(String calendarId) {
		return calendarService.getCalendar(Long.valueOf(calendarId));
	}

	public List<CalendarInfo> getCalendarInfos() {
		Collection<CalendarDef> defs=calendarService.getAllCalendarDefs();
		List<CalendarInfo> list=new ArrayList<CalendarInfo>();
		for(CalendarDef def:defs){
			CalendarInfo info=new CalendarInfo();
			info.setId(String.valueOf(def.getId()));
			info.setName(def.getName());
			list.add(info);
		}
		return list;
	}

	public CalendarService getCalendarService() {
		return calendarService;
	}

	public void setCalendarService(CalendarService calendarService) {
		this.calendarService = calendarService;
	}
}
