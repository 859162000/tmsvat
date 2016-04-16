//package com.deloitte.tms.pl.workflow.console.view;
//
//import java.util.Collection;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Component;
//
//import com.bstek.dorado.annotation.DataProvider;
//import com.bstek.dorado.annotation.DataResolver;
//import com.bstek.dorado.data.entity.EntityState;
//import com.bstek.dorado.data.entity.EntityUtils;
//import com.deloitte.tms.pl.workflow.command.Command;
//import com.deloitte.tms.pl.workflow.command.CommandService;
//import com.deloitte.tms.pl.workflow.command.impl.GetAllCalendarDefCommand;
//import com.deloitte.tms.pl.workflow.command.impl.GetCalendarDateCommand;
//import com.deloitte.tms.pl.workflow.console.command.DeleteCalendarDateCommand;
//import com.deloitte.tms.pl.workflow.console.command.DeleteCalendarDefCommand;
//import com.deloitte.tms.pl.workflow.console.command.SaveCalendarDateCommand;
//import com.deloitte.tms.pl.workflow.console.command.SaveCalendarDefCommand;
//import com.deloitte.tms.pl.workflow.model.calendar.CalendarDate;
//import com.deloitte.tms.pl.workflow.model.calendar.CalendarDef;
//import com.deloitte.tms.pl.workflow.utils.IDGenerator;
//
///**
// * 
// * @author Jake.Wang@bstek.com
// * @since Sep 23, 2013
// * 
// */
//@Component("uflo.calendarMaintain")
//public class CalendarMaintain {
//	@Autowired
//	@Qualifier("uflo.commandService")
//	private CommandService commandService;
//
//	@DataProvider
//	public Collection<CalendarDef> loadCalendarDef() {
//		return execute(new GetAllCalendarDefCommand());
//	}
//
//	@DataProvider
//	public Collection<CalendarDate> loadCalendarDate(Long calendarId) {
//		return execute(new GetCalendarDateCommand(calendarId));
//	}
//
//	@DataResolver
//	public void saveCalendar(List<CalendarDef> calendars) {
//		for (CalendarDef def : calendars) {
//			EntityState state = EntityUtils.getState(def);
//			if (state.equals(EntityState.NEW)) {
//				def.setId(IDGenerator.getInstance().nextId());
//				execute(new SaveCalendarDefCommand(def));
//			}
//			if (state.equals(EntityState.MODIFIED)) {
//				execute(new SaveCalendarDefCommand(def));
//			}
//			if (state.equals(EntityState.DELETED)) {
//				execute(new DeleteCalendarDefCommand(def));
//			} else if (def.getCalendarDates() != null) {
//				this.saveCalendarDates(def.getCalendarDates());
//			}
//		}
//	}
//
//	private void saveCalendarDates(Collection<CalendarDate> dates) {
//		for (CalendarDate date : dates) {
//			EntityState state = EntityUtils.getState(date);
//			if (state.equals(EntityState.NEW)) {
//				date.setId(IDGenerator.getInstance().nextId());
//				execute(new SaveCalendarDateCommand(date));
//			}
//			if (state.equals(EntityState.MODIFIED)) {
//				execute(new SaveCalendarDateCommand(date));
//			}
//			if (state.equals(EntityState.DELETED)) {
//				execute(new DeleteCalendarDateCommand(date));
//			}
//		}
//	}
//
//	private <T> T execute(Command<T> command) {
//		return commandService.executeCommand(command);
//	}
//}
