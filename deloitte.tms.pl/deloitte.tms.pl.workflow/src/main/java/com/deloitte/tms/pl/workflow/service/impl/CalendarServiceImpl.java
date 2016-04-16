package com.deloitte.tms.pl.workflow.service.impl;

import java.util.Collection;
import java.util.GregorianCalendar;

import javax.annotation.Resource;

import org.quartz.Calendar;
import org.quartz.impl.calendar.AnnualCalendar;
import org.quartz.impl.calendar.DailyCalendar;
import org.quartz.impl.calendar.HolidayCalendar;
import org.quartz.impl.calendar.MonthlyCalendar;
import org.quartz.impl.calendar.WeeklyCalendar;
import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.workflow.command.CommandService;
import com.deloitte.tms.pl.workflow.command.impl.GetAllCalendarDefCommand;
import com.deloitte.tms.pl.workflow.command.impl.GetCalendarDateCommand;
import com.deloitte.tms.pl.workflow.command.impl.GetCalendarDefCommand;
import com.deloitte.tms.pl.workflow.model.calendar.CalendarDate;
import com.deloitte.tms.pl.workflow.model.calendar.CalendarDef;
import com.deloitte.tms.pl.workflow.model.calendar.CalendarType;
import com.deloitte.tms.pl.workflow.service.CalendarService;

/**
 * @author Jacky.gao
 * @since 2013年9月23日
 */
@Component
public class CalendarServiceImpl implements CalendarService {
	@Resource
	private CommandService commandService;
	public Collection<CalendarDef> getAllCalendarDefs() {
		return commandService.executeCommand(new GetAllCalendarDefCommand());
	}

	public CalendarDef getCalendarDef(long calendarId) {
		return commandService.executeCommand(new GetCalendarDefCommand(calendarId));
	}
	
	public Collection<CalendarDate> getCalendarDate(long calendarId) {
		return commandService.executeCommand(new GetCalendarDateCommand(calendarId));
	}
	
	public Calendar getCalendar(long calendarId) {
		CalendarDef def=getCalendarDef(calendarId);
		Collection<CalendarDate> dates=getCalendarDate(calendarId);
		Calendar baseCalendar=buildCalendar(def,dates);
		return baseCalendar;
	}
	
	private Calendar buildCalendar(CalendarDef calendarDef,Collection<CalendarDate> dates){
		Calendar rootCalendar=null;
		if(calendarDef.getType().equals(CalendarType.holiday)){
			HolidayCalendar calendar=new HolidayCalendar();
			if(dates!=null){
				for(CalendarDate d:dates){
					calendar.addExcludedDate(d.getCalendarDate());
				}
			}
			rootCalendar=calendar;
		}
		if(calendarDef.getType().equals(CalendarType.annual)){
			AnnualCalendar calendar=new AnnualCalendar();
			if(dates!=null){
				for(CalendarDate d:dates){
					java.util.Calendar c=new GregorianCalendar();
					c.set(java.util.Calendar.MONTH,d.getMonthOfYear());
					c.set(java.util.Calendar.DATE,d.getDayOfMonth());
					calendar.setDayExcluded(c,true);
				}
			}
			if(rootCalendar!=null){
				calendar.setBaseCalendar(rootCalendar);				
			}
			rootCalendar=calendar;
		}
		if(calendarDef.getType().equals(CalendarType.monthly)){
			MonthlyCalendar calendar=new MonthlyCalendar();
			if(dates!=null){
				for(CalendarDate d:dates){
					calendar.setDayExcluded(d.getDayOfMonth(),true);
				}
			}
			if(rootCalendar!=null){
				calendar.setBaseCalendar(rootCalendar);				
			}
			rootCalendar=calendar;
		}
		if(calendarDef.getType().equals(CalendarType.weekly)){
			WeeklyCalendar calendar=new WeeklyCalendar();
			calendar.setDayExcluded(java.util.Calendar.SUNDAY, false);
			calendar.setDayExcluded(java.util.Calendar.SATURDAY, false);
			if(dates!=null){
				for(CalendarDate d:dates){
					calendar.setDayExcluded(d.getDayOfWeek(),true);
				}
			}
			if(rootCalendar!=null){
				calendar.setBaseCalendar(rootCalendar);				
			}
			rootCalendar=calendar;
		}
		if(calendarDef.getType().equals(CalendarType.daily)){
			if(dates!=null){
				for(CalendarDate d:dates){
					DailyCalendar calendar=new DailyCalendar(rootCalendar,d.getRangeStartTime(),d.getRangeEndTime());
					rootCalendar=calendar;
				}
			}
		}
		return rootCalendar;			
	}

	public CommandService getCommandService() {
		return commandService;
	}

	public void setCommandService(CommandService commandService) {
		this.commandService = commandService;
	}
}
